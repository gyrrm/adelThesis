package com.adel.thesis.adelthesis.service;

import com.adel.thesis.adelthesis.service.dao.DaoOperations;
import com.adel.thesis.adelthesis.utility.AdelThesisUtility;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    @Autowired
    public HeaderValidationService headerValidationService;

    @Autowired
    public AdelThesisUtility adelThesisUtility;

    @Autowired
    public DaoOperations daoOperations;

    @Autowired
    public ObjectMapper mapper;

    public ResponseEntity<String> registration(String body, String uuid, String sourceapp) {

        int headerValidationStatusCode = headerValidationService.headerValidation(uuid, sourceapp);

        if(headerValidationStatusCode == 500) {
            return new ResponseEntity<String>(
                adelThesisUtility.createResponseBody("4000", "Invalid uuid and/or sourceapp", "HeaderValidationService"),
                adelThesisUtility.createReponseHeaders(uuid, sourceapp), HttpStatus.BAD_REQUEST);
        }

        daoOperations.writeToJson(adelThesisUtility.stringToJson(body));

        return new ResponseEntity<String>(
            adelThesisUtility.createResponseBody("1100", "Registration was successful", "RegistrationService"),
            adelThesisUtility.createReponseHeaders(uuid, sourceapp), HttpStatus.OK);
    }
}
