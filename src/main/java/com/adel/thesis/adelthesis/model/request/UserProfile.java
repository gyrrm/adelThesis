package com.adel.thesis.adelthesis.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserProfile {

    @JsonProperty("details")
    public UserDetails userDetails;

    @JsonProperty("security")
    public UserSecurity userSecurity;

    public void setUserDetails(UserDetails userDetails, UserSecurity userSecurity) {
        this.userDetails = userDetails;
        this.userSecurity = userSecurity;
    }

    public UserProfile(){
        super();
    }

    public UserDetails getUserDetails() {
        return this.userDetails;
    }

    public UserSecurity getUserSecurity() {
        return this.userSecurity;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public void setUserSecurity(UserSecurity userSecurity) {
        this.userSecurity = userSecurity;
    }
}
