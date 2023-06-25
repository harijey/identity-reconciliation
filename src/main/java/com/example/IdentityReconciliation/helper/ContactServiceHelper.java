package com.example.IdentityReconciliation.helper;

import com.example.IdentityReconciliation.entitiy.ContactEntity;
import com.example.IdentityReconciliation.entitiy.LinkPrecedence;
import com.example.IdentityReconciliation.models.ContactWebResponse;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class ContactServiceHelper {
    public static List<ContactEntity> groupByEmail(List<ContactEntity> contactEntityList, String email){
        return contactEntityList.stream()
                .filter(contactEntity -> contactEntity.getEmail().equals(email))
                .collect(Collectors.toList());
    }

    public static List<ContactEntity> groupByPhoneNumber(List<ContactEntity> contactEntityList,String phoneNumber){
        return contactEntityList.stream()
                .filter(contactEntity -> contactEntity.getPhoneNumber().equals(phoneNumber))
                .collect(Collectors.toList());
    }

    public static ContactWebResponse buildResponse(List<ContactEntity> contactEntityList){

        List<ContactEntity> primaryContact = contactEntityList.stream()
                .filter(contactEntity -> contactEntity.getLinkPrecedence()== LinkPrecedence.PRIMARY)
                .collect(Collectors.toList());

        Set<ContactEntity> secondaryContacts = contactEntityList.stream()
                .filter(contactEntity -> contactEntity.getLinkPrecedence()==LinkPrecedence.SECONDARY)
                .collect(Collectors.toSet());

        String primaryEmail = primaryContact.get(0).getEmail();
        String primaryPhoneNumber = primaryContact.get(0).getPhoneNumber();

        List<String> secondaryEmails = secondaryContacts.stream()
                .filter(contactEntity -> contactEntity.getEmail()!=null)
                .map(ContactEntity::getEmail)
                .collect(Collectors.toList());

        List<String> secondaryPhoneNumbers = secondaryContacts.stream()
                .filter(contactEntity -> contactEntity.getPhoneNumber()!=null)
                .map(ContactEntity::getPhoneNumber)
                .collect(Collectors.toList());

        Set<String> emails = new LinkedHashSet<>();
        emails.add(primaryEmail);
        emails.addAll(secondaryEmails);

        Set<String> phoneNumbers = new LinkedHashSet<>();
        phoneNumbers.add(primaryPhoneNumber);
        phoneNumbers.addAll(secondaryPhoneNumbers);

        List<Long> secondaryIds = secondaryContacts.stream()
                .filter(contactEntity -> contactEntity.getEmail()!=null
                &&contactEntity.getPhoneNumber()!=null)
                .map(ContactEntity::getId)
                .collect(Collectors.toList());

        ContactWebResponse contactWebResponse = new ContactWebResponse();
        contactWebResponse.setPrimaryContactId(primaryContact.get(0).getId());
        contactWebResponse.setPhoneNumbers(phoneNumbers);
        contactWebResponse.setEmails(emails);
        contactWebResponse.setSecondaryContactIds(secondaryIds);
        return contactWebResponse;

    }

    public static void setNewLinkedIdAndLinkPrecedence(List<ContactEntity> contactEntityList, Long linkedId){
        contactEntityList.forEach(contactEntity -> {
            contactEntity.setLinkedId(linkedId);
            contactEntity.setLinkPrecedence(LinkPrecedence.SECONDARY);
        });
    }

    //Contact Entity List can not be null or empty as per the logic.
    public static Long getPrimaryId(List<ContactEntity> contactEntityList){
        if(contactEntityList.get(0).getLinkPrecedence().equals(LinkPrecedence.PRIMARY)){
            return contactEntityList.get(0).getId();
        }else{
            return contactEntityList.get(0).getLinkedId();
        }
    }
}
