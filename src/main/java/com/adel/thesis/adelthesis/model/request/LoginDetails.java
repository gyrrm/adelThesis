package com.adel.thesis.adelthesis.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginDetails {

    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;

    public LoginDetails(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public LoginDetails() {
        super();
    }

    public String getUserName() {
        return this.username;
    }

    public void setUserName(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setUsername(String password) {
        this.password = password;
    }
}
