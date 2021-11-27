package com.adel.thesis.adelthesis.service;

import com.adel.thesis.adelthesis.constants.ErrorCodes;
import com.adel.thesis.adelthesis.service.dao.DaoOperations;
import com.adel.thesis.adelthesis.utility.AdelThesisUtility;
import com.adel.thesis.adelthesis.utility.LoginUtility;
import com.adel.thesis.adelthesis.utility.SchemaValidationUtility;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ActivationService {

    @Autowired
    public HeaderValidationService headerValidationService;

    @Autowired
    public AdelThesisUtility adelThesisUtility;

    @Autowired
    public SchemaValidationUtility schemaValidationUtility;

    @Autowired
    public DaoOperations daoOperations;

    @Autowired
    public LoginUtility loginUtility;

    public ResponseEntity<String> activation(String body, String uuid, String sourceapp) {

        int headerValidationStatusCode = headerValidationService.headerValidation(uuid, sourceapp);
        int schemaValidationStatusCode = 200;

        if(headerValidationStatusCode == 500) {
            return new ResponseEntity<String>(
                adelThesisUtility.createResponseBody("4000", "Invalid uuid and/or sourceapp", "HeaderValidationService"),
                adelThesisUtility.createReponseHeaders(uuid, sourceapp), HttpStatus.BAD_REQUEST);
        }

        JSONObject requestAsJson = new JSONObject(body);

        schemaValidationStatusCode = schemaValidationUtility.validateActivationRequest(requestAsJson);

        if(schemaValidationStatusCode != 200) {
            return new ResponseEntity<String>(
                adelThesisUtility.createResponseBody(String.valueOf(schemaValidationStatusCode), "SchemaValidationException", "SchemaValidationUtility"),
                adelThesisUtility.createReponseHeaders(uuid, sourceapp), HttpStatus.BAD_REQUEST);
        }

        org.json.simple.JSONObject profileFromDatabase = daoOperations.readFromJson();

        String profileFromDatabaseAsString = profileFromDatabase.toJSONString();

        JSONObject profileFromDatabaseAsJSON = new JSONObject(profileFromDatabaseAsString);

        int statusCodeOfUsernameCheck = loginUtility.checkUserNameMatch(requestAsJson, profileFromDatabaseAsJSON);

        if(statusCodeOfUsernameCheck != 200) {

            return new ResponseEntity<String>(
            adelThesisUtility.createResponseBody(
                ErrorCodes.INVALID_USERNAME_AT_LOGIN, "User was not found", "ActivationService"),
            adelThesisUtility.createReponseHeaders(uuid, sourceapp), HttpStatus.BAD_REQUEST);
        }

        int statusFromActiveIndCheck = loginUtility.checkActiveIndIfActive(profileFromDatabaseAsJSON);

        if(statusFromActiveIndCheck != 200) {

            return new ResponseEntity<String>(
            adelThesisUtility.createResponseBody(
                ErrorCodes.USER_IS_ALREADY_ACTIVE, "The user is already active", "ActivationService"),
            adelThesisUtility.createReponseHeaders(uuid, sourceapp), HttpStatus.BAD_REQUEST);
        }

        JSONObject activatedProfileAsJson = adelThesisUtility.modifyActiveInd(profileFromDatabaseAsJSON);

        daoOperations.writeToJson(adelThesisUtility.stringToJson(activatedProfileAsJson.toString()));

        return new ResponseEntity<String>(
            adelThesisUtility.createResponseBody(ErrorCodes.SUCCESSFUL_ACTIVATION, "Activation was successful", "ActivationService"),
            adelThesisUtility.createReponseHeaders(uuid, sourceapp), HttpStatus.OK);
    }

}
