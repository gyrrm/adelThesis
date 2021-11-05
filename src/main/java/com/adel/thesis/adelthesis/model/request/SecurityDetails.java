package com.adel.thesis.adelthesis.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SecurityDetails {

    public String activeInd;

    public String activationCode;

    public String password;

    public SecurityDetails(String activeInd, String activationCode, String password) {
        this.activeInd = activeInd;
        this.activationCode = activationCode;
        this.password = password;
    }

    public void setActiveInd(String activeInd) {
        this.activeInd = activeInd;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getActiveInd() {
        return activeInd;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public String getPassword() {
        return password;
    }
}
