package com.adel.thesis.adelthesis.service;

import java.io.IOException;

import com.adel.thesis.adelthesis.model.request.LoginDetails;
import com.adel.thesis.adelthesis.model.request.LoginRequest;
import com.adel.thesis.adelthesis.utility.AdelThesisUtility;
import com.adel.thesis.adelthesis.utility.SchemaValidationUtility;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class LoginService {


    @Autowired
    public HeaderValidationService headerValidationService;

    @Autowired
    public AdelThesisUtility adelThesisUtility;

    @Autowired
    public SchemaValidationUtility schemaValidationUtility;

    @Autowired
    public ObjectMapper mapper;

    public ResponseEntity<String> login(String body, String uuid, String sourceapp) {

        int headerValidationStatusCode = headerValidationService.headerValidation(uuid, sourceapp);
        int schemaValidationStatusCode = 200;

        if(headerValidationStatusCode == 500) {
            return new ResponseEntity<String>(
                adelThesisUtility.createResponseBody("4000", "Invalid uuid and/or sourceapp", "HeaderValidationService"),
                adelThesisUtility.createReponseHeaders(uuid, sourceapp), HttpStatus.BAD_REQUEST);
        }

        LoginRequest loginRequest = new LoginRequest();

        System.out.println(body);

        try {
            loginRequest = mapper.readValue(body, LoginRequest.class);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        schemaValidationStatusCode = schemaValidationUtility.validateLoginRequest(loginRequest);

        if(schemaValidationStatusCode != 200) {
            return new ResponseEntity<String>(
                adelThesisUtility.createResponseBody(String.valueOf(schemaValidationStatusCode), "SchemaValidationException", "SchemaValidationUtility"),
                adelThesisUtility.createReponseHeaders(uuid, sourceapp), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<String>(
            adelThesisUtility.createResponseBody("1100", "Registration was successful", "RegistrationService"),
            adelThesisUtility.createReponseHeaders(uuid, sourceapp), HttpStatus.OK);
    }

}
