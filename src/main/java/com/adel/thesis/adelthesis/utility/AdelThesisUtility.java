package com.adel.thesis.adelthesis.utility;

import java.util.LinkedHashMap;
import java.util.Map;

import com.adel.thesis.adelthesis.model.request.RegistrationRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
public class AdelThesisUtility {

    @Autowired
    ObjectMapper objectMapper;

    public int generateUserId(){

        return 0;
    }

    public String createResponseBody(String status, String message, String service) {

        return new JSONObject()
                    .put("status", status)
                    .put("message", message)
                    .put("service", service).toString();

    }
//
    public HttpHeaders createReponseHeaders(String uuid, String sourceapp) {

        HttpHeaders headers = new HttpHeaders();
        headers.add("uuid", uuid);
        headers.add("sourceapp", sourceapp);

        return headers;
    }

    public org.json.JSONObject stringToJson(String body) {

        org.json.JSONObject json = new JSONObject(body);

        return json;
    }

    public String createLoginResponseBody(String status, String message, String service, JSONObject profileFromDatabase) {

        Map obj = new LinkedHashMap();

        obj.put("status", status);
        obj.put("message", message);
        obj.put("service", service);
        obj.put("profile", profileFromDatabase.getJSONObject("profile").getJSONObject("details"));

        String stringObject = new JSONObject(obj).toString();

        return stringObject;
    }

    public JSONObject modifyActiveInd(JSONObject profileFromDatabase) {

        profileFromDatabase.getJSONObject("profile").getJSONObject("security").remove("activationCode");
        profileFromDatabase.getJSONObject("profile").getJSONObject("security").remove("activeInd");

        profileFromDatabase.getJSONObject("profile").getJSONObject("security").put("activationCode", "");
        profileFromDatabase.getJSONObject("profile").getJSONObject("security").put("activeInd", "A");

        return profileFromDatabase;

    }

    public JSONObject getUserFromJSONArrayForRegistration(JSONArray array, RegistrationRequest request) {

        JSONObject user = new JSONObject();

        for(int i = 0; i < array.length(); i++) {


            String userNameFromDatabase = array.getJSONObject(i).getJSONObject("profile").getJSONObject("details").getString("userName");

                if(userNameFromDatabase.equals(request.getUserProfile().getUserDetails().getUserName())) {
                    user = array.getJSONObject(i);
                }
        }
            return user;
    }

    public JSONObject getUserFromJSONArrayForLogin(JSONArray array, JSONObject request) {

        JSONObject user = new JSONObject();

        for(int i = 0; i < array.length(); i++) {


            String userNameFromDatabase = array.getJSONObject(i).getJSONObject("profile").getJSONObject("details").getString("userName");

                if(userNameFromDatabase.equals(request.getJSONObject("details").getString("username"))) {
                    user = array.getJSONObject(i);
                }
        }
            return user;
    }
}
