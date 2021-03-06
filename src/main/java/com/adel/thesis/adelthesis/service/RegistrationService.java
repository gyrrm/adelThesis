package com.adel.thesis.adelthesis.service;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.adel.thesis.adelthesis.constants.ErrorCodes;
import com.adel.thesis.adelthesis.model.request.RegistrationRequest;
import com.adel.thesis.adelthesis.service.dao.DaoOperations;
import com.adel.thesis.adelthesis.utility.AdelThesisUtility;
import com.adel.thesis.adelthesis.utility.SchemaValidationUtility;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONObject;
import org.json.simple.parser.ParseException;
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
    public SchemaValidationUtility schemaValidationUtility;

    @Autowired
    public DaoOperations daoOperations;

    @Autowired
    public ObjectMapper mapper;

    public ResponseEntity<String> registration(String body, String uuid, String sourceapp) throws IOException, ParseException {

        int headerValidationStatusCode = headerValidationService.headerValidation(uuid, sourceapp);
        int schemaValidationStatusCode = 200;

        RegistrationRequest registrationRequest = new RegistrationRequest();

        if(headerValidationStatusCode == 500) {
            return new ResponseEntity<String>(
                adelThesisUtility.createResponseBody("4000", "Invalid uuid and/or sourceapp", "HeaderValidationService"),
                adelThesisUtility.createReponseHeaders(uuid, sourceapp), HttpStatus.BAD_REQUEST);
        }

        try {
            registrationRequest = mapper.readValue(body, RegistrationRequest.class);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        schemaValidationStatusCode = schemaValidationUtility.validateRegistrationRequest(registrationRequest);

        if(schemaValidationStatusCode != 200) {
            return new ResponseEntity<String>(
                adelThesisUtility.createResponseBody(String.valueOf(schemaValidationStatusCode), "SchemaValidationException", "SchemaValidationUtility"),
                adelThesisUtility.createReponseHeaders(uuid, sourceapp), HttpStatus.BAD_REQUEST);
        }

        if(!daoOperations.readFromJsonForRegistration(registrationRequest).toString().equals("{}")) {
            return new ResponseEntity<String>(
            adelThesisUtility.createResponseBody(ErrorCodes.USER_IS_ALREADY_REGISTERED, "Registration was not successful", "RegistrationService"),
            adelThesisUtility.createReponseHeaders(uuid, sourceapp), HttpStatus.BAD_REQUEST);
    }

        daoOperations.writeToJson(adelThesisUtility.stringToJson(body));

        return new ResponseEntity<String>(
            adelThesisUtility.createResponseBody(ErrorCodes.SUCCESSFUL_REGISTRATION, "Registration was successful", "RegistrationService"),
            adelThesisUtility.createReponseHeaders(uuid, sourceapp), HttpStatus.OK);
    }
}
