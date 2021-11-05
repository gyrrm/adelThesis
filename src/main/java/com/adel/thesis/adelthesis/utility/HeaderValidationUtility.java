package com.adel.thesis.adelthesis.utility;

import org.springframework.stereotype.Component;

@Component
public class HeaderValidationUtility {

    public int validateUUID(String uuid) {

        int statusCode;

        if (uuid == null || uuid.isEmpty()) {

            statusCode = 500;

        } else {

            statusCode = 200;
        }

        return statusCode;
     }

     public int validateSourceapp (String sourceapp) {

        int statusCode;

        if (sourceapp == null || sourceapp.isEmpty()) {

            statusCode = 500;

        } else {

            if(sourceapp.equalsIgnoreCase("Mobile") || sourceapp.equalsIgnoreCase("Web")) {

                statusCode = 200;
            } else {

                statusCode = 500;
            }

        }


        return statusCode;
     }

}
