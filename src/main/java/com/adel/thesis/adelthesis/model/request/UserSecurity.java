package com.adel.thesis.adelthesis.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserSecurity {

    public String activeInd;
    public String activationCode;
    public String password;

    public UserSecurity(String activeInd, String activationCode, String password) {

        this.activeInd = activeInd;
        this.activationCode = activationCode;
        this.password = password;
    }

    public UserSecurity(){
        super();
    }

    public String getActiveInd() {
        return this.activeInd;
    }

    public String getActivationCode() {
        return this.activationCode;
    }

    public String getPassword() {
        return this.password;
    }
}
