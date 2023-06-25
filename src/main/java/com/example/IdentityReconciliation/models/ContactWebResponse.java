package com.example.IdentityReconciliation.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContactWebResponse {
    private Long primaryContactId;
    private Iterable<String> phoneNumbers = new ArrayList<>();
    private Iterable<String> emails = new ArrayList<>();
    private Iterable<Long> secondaryContactIds = new ArrayList<>();
}
