package com.example.IdentityReconciliation.controller;

import com.example.IdentityReconciliation.models.ContactWebRequest;
import com.example.IdentityReconciliation.models.ContactWebResponse;
import com.example.IdentityReconciliation.models.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.IdentityReconciliation.service.ContactService;

@RestController
@RequestMapping("/contact")
public class ContactController {
    @Autowired
    ContactService contactService;
    @PostMapping(value = "/identify")
    public Response<ContactWebResponse> identifyContactsOrAdd(
            @RequestBody ContactWebRequest contactWebRequest) {
        return Response.<ContactWebResponse>builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.name())
                .data(contactService.identifyContactsOrAdd(contactWebRequest))
                .build();
    }
}
