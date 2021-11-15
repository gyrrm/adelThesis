package com.adel.thesis.adelthesis.utility;

import com.adel.thesis.adelthesis.model.request.LoginRequest;
import com.adel.thesis.adelthesis.model.request.RegistrationRequest;
import com.adel.thesis.adelthesis.constants.ErrorCodes;
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
                if(body.getUserProfile().getUserSecurity().getActivationCode().isEmpty()) {
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

    public int validateLoginRequest(LoginRequest body) {

        int statusOfDetails = 200;

        if(body != null) {
            if(body.getLoginDetails() != null) {
                if(body.getLoginDetails().getUserName().isEmpty()) {
                    statusOfDetails = Integer.parseInt(ErrorCodes.INVALID_FIRST_OR_LAST_NAME_OR_USERNAME);
                } else {
                    if(body.getLoginDetails().getPassword().isEmpty()) {
                        statusOfDetails = Integer.parseInt(ErrorCodes.INVALID_PASSWORD_IN_SCHEMA);
                    }
                }
            }
        }

        return statusOfDetails;
    }
}
