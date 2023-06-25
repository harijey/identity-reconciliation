package com.example.IdentityReconciliation.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@AllArgsConstructor
@Builder
public class ContactWebRequest {
    private String email;
    private String phoneNumber;
}
