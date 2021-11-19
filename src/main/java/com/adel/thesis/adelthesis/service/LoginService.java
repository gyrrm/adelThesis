package com.adel.thesis.adelthesis.service;

import java.io.IOException;

import com.adel.thesis.adelthesis.constants.ErrorCodes;
import com.adel.thesis.adelthesis.model.request.LoginRequest;
import com.adel.thesis.adelthesis.service.dao.DaoOperations;
import com.adel.thesis.adelthesis.utility.AdelThesisUtility;
import com.adel.thesis.adelthesis.utility.LoginUtility;
import com.adel.thesis.adelthesis.utility.SchemaValidationUtility;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONObject;
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
    public LoginUtility loginUtility;

    @Autowired
    public DaoOperations daoOperations;

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

        JSONObject requestAsJson = new JSONObject(body);

        schemaValidationStatusCode = schemaValidationUtility.validateLoginRequest(requestAsJson);

        if(schemaValidationStatusCode != 200) {
            return new ResponseEntity<String>(
                adelThesisUtility.createResponseBody(String.valueOf(schemaValidationStatusCode), "SchemaValidationException", "SchemaValidationUtility"),
                adelThesisUtility.createReponseHeaders(uuid, sourceapp), HttpStatus.BAD_REQUEST);
        }

        org.json.simple.JSONObject profileFromDatabase = daoOperations.readFromJson();

        String kurvaAnyad = profileFromDatabase.toJSONString();

        JSONObject kurvaAnyadAsJson = new JSONObject(kurvaAnyad);

        int statusCodeOfUsernameCheck = loginUtility.checkUserNameMatch(requestAsJson, kurvaAnyadAsJson);

        if(statusCodeOfUsernameCheck != 200) {

            return new ResponseEntity<String>(
            adelThesisUtility.createResponseBody(
                ErrorCodes.INVALID_USERNAME_AT_LOGIN, "Login was not successful", "LoginService"),
            adelThesisUtility.createReponseHeaders(uuid, sourceapp), HttpStatus.BAD_REQUEST);
        }

        int statusCodeOfPasswordCheck = loginUtility.checkPasswordMatch(requestAsJson, kurvaAnyadAsJson);

        if(statusCodeOfPasswordCheck != 200) {

            return new ResponseEntity<String>(
            adelThesisUtility.createResponseBody(
                ErrorCodes.INVALID_PASSWORD_AT_LOGIN, "Login was not successful", "LoginService"),
            adelThesisUtility.createReponseHeaders(uuid, sourceapp), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<String>(
            adelThesisUtility.createLoginResponseBody(
                ErrorCodes.SUCCESSFUL_LOGIN, "Registration was successful", "RegistrationService", kurvaAnyadAsJson),
            adelThesisUtility.createReponseHeaders(uuid, sourceapp), HttpStatus.OK);
    }

}
