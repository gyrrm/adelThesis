package com.adel.thesis.adelthesis.service;

import com.adel.thesis.adelthesis.utility.HeaderValidationUtility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HeaderValidationService {

    @Autowired
    public HeaderValidationUtility headerValidationUtility;

    public int headerValidation(String uuid, String sourceapp) {

        int statusCode;

        int statuscodeFromSourceappValidation = headerValidationUtility.validateSourceapp(sourceapp);
        int statusCodeFromUUIDValidation = headerValidationUtility.validateUUID(uuid);

        if(statusCodeFromUUIDValidation == 200 && statuscodeFromSourceappValidation == 200) {

            statusCode = 200;
        } else {
            statusCode = 500;
        }

        return statusCode;
    }

}
