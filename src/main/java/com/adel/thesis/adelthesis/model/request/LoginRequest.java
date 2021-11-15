package com.adel.thesis.adelthesis.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginRequest {

    @JsonProperty("details")
    public LoginDetails details;

    public LoginRequest(LoginDetails details) {
        this.details = details;
    }

    public LoginRequest() {
        super();
    }

    public LoginDetails getLoginDetails() {
        return this.details;
    }

    public void setLoginDetails(LoginDetails details) {
        this.details = details;
    }

}
