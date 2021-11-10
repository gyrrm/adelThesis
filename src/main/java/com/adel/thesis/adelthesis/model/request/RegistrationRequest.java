package com.adel.thesis.adelthesis.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(value = Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegistrationRequest {

    @JsonProperty("profile")
    public UserProfile userProfile;

    public RegistrationRequest(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public RegistrationRequest() {
        super();
    }

    public UserProfile getUserProfile() {
        return this.userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }
}
