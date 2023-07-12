package com.example.IdentityReconciliation.serviceImpl;

import com.example.IdentityReconciliation.entitiy.ContactEntity;
import com.example.IdentityReconciliation.entitiy.LinkPrecedence;
import com.example.IdentityReconciliation.helper.ContactServiceHelper;
import com.example.IdentityReconciliation.models.ContactWebRequest;
import com.example.IdentityReconciliation.models.ContactWebResponse;
import com.example.IdentityReconciliation.repository.ContactRepo;
import com.example.IdentityReconciliation.service.ContactService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Slf4j
@Service
public class ContactServiceImpl implements ContactService {
    @Autowired
    ContactRepo contactRepo;

    @Override
    public ContactWebResponse identifyContactsOrAdd(ContactWebRequest contactWebRequest) {
        /*
        Get contacts by emails or phone numbers:
         */
        List<ContactEntity> contactEntityList = contactRepo.findByEmailOrPhoneNumber(contactWebRequest.getEmail(),
                contactWebRequest.getPhoneNumber());

        ContactEntity contactEntity = new ContactEntity();
        contactEntity.setEmail(contactWebRequest.getEmail());
        contactEntity.setPhoneNumber(contactWebRequest.getPhoneNumber());

        /*
        If there are no emails and phone numbers connected to
        data then it is a fresh isolated node.
         */

        if (CollectionUtils.isEmpty(contactEntityList)) {
            contactEntity.setLinkPrecedence(LinkPrecedence.PRIMARY);
            contactEntityList.add(contactEntity);
            contactRepo.save(contactEntity);
            return ContactServiceHelper.buildResponse(contactEntityList);
        }

        /*
        group them by emails cluster and phoneNumber cluster,
        If either of them is null,(It not possible that both
        are null as we have validated it
        in the previous step and make sure it is linked with primary
        and not secondary)  connect them directly to the other
        [NOTE:All email/phoneNumber cluster must
        have same linkedId or null]
         */
        Boolean isAlreadyPresent = Boolean.FALSE;
        contactEntity.setLinkPrecedence(LinkPrecedence.SECONDARY);
        List<ContactEntity> contactsGroupedByEmail
                = ContactServiceHelper.groupByEmail(contactEntityList, contactWebRequest.getEmail());
        List<ContactEntity> contactsGroupedByPhoneNumber
                = ContactServiceHelper.groupByPhoneNumber(contactEntityList, contactWebRequest.getPhoneNumber());
        if(contactsGroupedByPhoneNumber.size()+contactsGroupedByEmail.size()!=contactEntityList.size()){
            isAlreadyPresent = Boolean.TRUE;
        }
        contactEntityList.clear();

        if(CollectionUtils.isEmpty(contactsGroupedByEmail)){
            Long linkedId= ContactServiceHelper
                    .getPrimaryId(contactsGroupedByPhoneNumber);
            contactsGroupedByPhoneNumber=
                    contactRepo.findByIdOrLinkedId(linkedId,linkedId);
            contactEntity.setLinkedId(linkedId);
            contactEntityList.add(contactEntity);
            if(!isAlreadyPresent) {
                contactRepo.save(contactEntity);
            }
            contactEntityList.addAll(contactsGroupedByPhoneNumber);
            return ContactServiceHelper.buildResponse(contactEntityList);
        }else if(CollectionUtils.isEmpty(contactsGroupedByPhoneNumber)){
            Long linkedId =  ContactServiceHelper
                    .getPrimaryId(contactsGroupedByEmail);
            contactsGroupedByEmail=contactRepo.findByIdOrLinkedId(linkedId,linkedId);
            contactEntity.setLinkedId(linkedId);
            contactEntityList.add(contactEntity);
            contactEntityList.addAll(contactsGroupedByEmail);
            if(!isAlreadyPresent) {
                contactRepo.save(contactEntity);
            }
            return ContactServiceHelper.buildResponse(contactEntityList);
        }

        /*
        Both email and phone number clusters is not null.
        Find the primary contact for email and phone Number
        respectively if not fetch it from repo.
        (There must be a match for both)
         */

        Long primaryMailId =  ContactServiceHelper
                .getPrimaryId(contactsGroupedByEmail);
        contactsGroupedByEmail=contactRepo.findByIdOrLinkedId(primaryMailId,primaryMailId);

        Long primaryPhoneId =  ContactServiceHelper
                .getPrimaryId(contactsGroupedByPhoneNumber);
        contactsGroupedByPhoneNumber=
                contactRepo.findByIdOrLinkedId(primaryPhoneId,primaryPhoneId);

        ContactEntity emailPrimaryAccount = getPrimaryAccount(contactsGroupedByEmail);
        ContactEntity phoneNumberPrimaryAccount = getPrimaryAccount(contactsGroupedByPhoneNumber);

        if (primaryPhoneId.equals(primaryMailId)) {
            contactEntity.setLinkedId(primaryMailId);
            if(!isAlreadyPresent) {
                contactRepo.save(contactEntity);
                contactEntityList.add(contactEntity);
            }
            contactEntityList.addAll(contactsGroupedByEmail);
            contactEntityList.addAll(contactsGroupedByPhoneNumber);
        } else {
            if (emailPrimaryAccount.getCreatedDate().before(phoneNumberPrimaryAccount.getCreatedDate())) {
                contactEntity.setLinkedId(emailPrimaryAccount.getId());
                ContactServiceHelper.setNewLinkedIdAndLinkPrecedence(contactsGroupedByPhoneNumber, emailPrimaryAccount.getId());
            } else {
                contactEntity.setLinkedId(phoneNumberPrimaryAccount.getId());
                ContactServiceHelper.setNewLinkedIdAndLinkPrecedence(contactsGroupedByEmail, phoneNumberPrimaryAccount.getId());
            }
            if(!isAlreadyPresent) {
                contactEntityList.add(contactEntity);
            }
            contactEntityList.addAll(contactsGroupedByEmail);
            contactEntityList.addAll(contactsGroupedByPhoneNumber);
            contactRepo.saveAll(contactEntityList);
        }
        return ContactServiceHelper.buildResponse(contactEntityList);
    }

    private ContactEntity getPrimaryAccount(List<ContactEntity> contactEntityList) {
        for(ContactEntity contact:contactEntityList){
            if(LinkPrecedence.PRIMARY.equals(contact.getLinkPrecedence())){
                return contact;
            }
        }
        return new ContactEntity();
    }
}
