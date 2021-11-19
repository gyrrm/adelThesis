package com.adel.thesis.adelthesis.constants;

public final class ErrorCodes {

    //Schema validation error codes
    public static final String INVALID_HEADERS = "4000";
    public static final String INVALID_FIRST_OR_LAST_NAME_OR_USERNAME = "4001";
    public static final String INVALID_USER_ID = "4002";
    public static final String INVALID_PASSWORD_IN_SCHEMA = "4003";
    public static final String INVALID_ACTIVATION_CODE_IN_SCHEMA = "4004";
    public static final String INVALID_ACTIVEIND_IN_SCHEMA = "4005";

    //Not error codes
    public static final String SUCCESSFUL_REGISTRATION = "1100";
    public static final String SUCCESSFUL_LOGIN = "1200";

    //Real error codes
    public static final String INVALID_PASSWORD_AT_LOGIN = "4103";
    public static final String INVALID_USERNAME_AT_LOGIN = "4101";

}
