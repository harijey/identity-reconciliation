package com.example.IdentityReconciliation.entitiy;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "Contact")
public class ContactEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Long id;
    private String phoneNumber;
    private String email;
    private Long linkedId;
    @Enumerated(EnumType.STRING)
    private LinkPrecedence linkPrecedence;
    @CreatedDate
    private Date createdDate;
    @LastModifiedDate
    private Date updatedDate;

    public ContactEntity(ContactEntity contactEntity) {
        this.id = contactEntity.id;
        this.phoneNumber = contactEntity.phoneNumber;
        this.email = contactEntity.email;
        this.linkedId = contactEntity.linkedId;
        this.linkPrecedence = contactEntity.linkPrecedence;
        this.createdDate = contactEntity.createdDate;
        this.updatedDate = contactEntity.updatedDate;
    }
}
