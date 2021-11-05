package com.adel.thesis.adelthesis.controller;

import com.adel.thesis.adelthesis.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdelThesisController {

    @Autowired
    private RegistrationService registrationService;

    @GetMapping(value = "/customer/registration", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> registration(
                          @RequestHeader(value = "uuid", required = false) final String uuid,
                          @RequestHeader(value = "sourceapp", required = false) final String sourceapp,
                          @RequestBody(required = false) String body) {

        return registrationService.registration(body, uuid, sourceapp);
    }
}
