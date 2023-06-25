package com.example.IdentityReconciliation.service;

import com.example.IdentityReconciliation.models.ContactWebRequest;
import com.example.IdentityReconciliation.models.ContactWebResponse;

import java.util.List;

public interface ContactService {
    ContactWebResponse identifyContactsOrAdd(ContactWebRequest contactWebRequest);
}
