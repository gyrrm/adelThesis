package com.adel.thesis.adelthesis.utility;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class LoginUtility {


    public int checkPasswordMatch(JSONObject request, JSONObject profileFromDatabase) {

        int statusCodeOfPasswordCheck = 200;

        if (!request.getJSONObject("details").getString("password")
                .equals(profileFromDatabase.getJSONObject("profile").getJSONObject("security").getString("password"))) {

                    statusCodeOfPasswordCheck = 500;
                }

        return statusCodeOfPasswordCheck;
    }

    public int checkUserNameMatch(JSONObject request, JSONObject profileFromDatabase) {

        int statusCodeOfUsernameCheck = 200;

        if (!request.getJSONObject("details").getString("username")
                .equals(profileFromDatabase.getJSONObject("profile").getJSONObject("details").getString("userName"))) {

                    statusCodeOfUsernameCheck = 500;
                }

        return statusCodeOfUsernameCheck;
    }
}