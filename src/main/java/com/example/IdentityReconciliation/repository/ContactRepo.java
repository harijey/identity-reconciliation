package com.example.IdentityReconciliation.repository;

import com.example.IdentityReconciliation.entitiy.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepo extends JpaRepository<ContactEntity, Long>, JpaSpecificationExecutor<ContactEntity> {

    List<ContactEntity> findByEmailOrPhoneNumber(String email, String phoneNumber);

    List<ContactEntity> findByIdOrLinkedId(Long linkedId, Long linkedId1);
}