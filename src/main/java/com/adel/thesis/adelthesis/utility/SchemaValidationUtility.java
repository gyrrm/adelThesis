package com.adel.thesis.adelthesis.utility;

import com.adel.thesis.adelthesis.model.request.LoginRequest;
import com.adel.thesis.adelthesis.model.request.RegistrationRequest;
import com.adel.thesis.adelthesis.constants.ErrorCodes;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class SchemaValidationUtility {

    public int validateRegistrationRequest(RegistrationRequest body) {

        int statusOfUserDetails = 200;
        int statusOfSecurity = 200;

        if(body.getUserProfile() != null) {
            if(body.getUserProfile().getUserDetails() != null) {
                if(body.getUserProfile().getUserDetails().getFirstName().isEmpty()
                || body.getUserProfile().getUserDetails().getLastName().isEmpty()
                || body.getUserProfile().getUserDetails().getUserName().isEmpty()) {
                    statusOfUserDetails = Integer.parseInt(ErrorCodes.INVALID_FIRST_OR_LAST_NAME_OR_USERNAME);
                } else {
                    if(body.getUserProfile().getUserDetails().getUserId() == 0) {
                        statusOfUserDetails = Integer.parseInt(ErrorCodes.INVALID_USER_ID);
                    }
                }
            }
            if(body.getUserProfile().getUserSecurity() != null) {
                if(body.getUserProfile().getUserSecurity().getActivationCode().isEmpty() ||
                   !body.getUserProfile().getUserSecurity().getActivationCode().equals("N")) {
                    statusOfSecurity = Integer.parseInt(ErrorCodes.INVALID_ACTIVATION_CODE_IN_SCHEMA);
                } else {
                    if(body.getUserProfile().getUserSecurity().getActiveInd().isEmpty()) {
                        statusOfSecurity = Integer.parseInt(ErrorCodes.INVALID_ACTIVEIND_IN_SCHEMA);
                    } else {
                        if(body.getUserProfile().getUserSecurity().getPassword().isEmpty()) {
                            statusOfSecurity = Integer.parseInt(ErrorCodes.INVALID_PASSWORD_IN_SCHEMA);
                        }
                    }
                }
            }
        }

        if(statusOfSecurity == 200  && statusOfUserDetails == 200) {
            return 200;
        } else {
            if(statusOfSecurity != 200) {
                return statusOfSecurity;
            } else {
                return statusOfUserDetails;
            }
        }
    }

    public int validateLoginRequest(JSONObject body) {

        int statusOfDetails = 200;

        if(body != null) {
            if(body.getJSONObject("details") != null) {
                if(body.getJSONObject("details").getString("username").isEmpty()) {
                    statusOfDetails = Integer.parseInt(ErrorCodes.INVALID_FIRST_OR_LAST_NAME_OR_USERNAME);
                } else {
                    if(body.getJSONObject("details").getString("password").isEmpty()) {
                        statusOfDetails = Integer.parseInt(ErrorCodes.INVALID_PASSWORD_IN_SCHEMA);
                    }
                }
            }
        }
        return statusOfDetails;
    }

    public int validateActivationRequest(JSONObject body) {

        int statusOfDetails = 200;
        int statusOfSecurity = 200;

        if(body != null) {
            if(body.getJSONObject("details") != null) {
                if(body.getJSONObject("details").getString("username").isEmpty()) {
                    statusOfDetails = Integer.parseInt(ErrorCodes.INVALID_FIRST_OR_LAST_NAME_OR_USERNAME);
                }
            }
            if(body.getJSONObject("security") != null) {
                if(body.getJSONObject("security").getString("activationCode") == null ||
                   body.getJSONObject("security").getString("activationCode").isEmpty()) {
                    statusOfSecurity = Integer.parseInt(ErrorCodes.INVALID_ACTIVATION_CODE_IN_SCHEMA);
                }
            }
        }
        if(statusOfSecurity == 200  && statusOfDetails == 200) {
            return 200;
        } else {
            if(statusOfSecurity != 200) {
                return statusOfSecurity;
            } else {
                return statusOfDetails;
            }
        }
    }
}
