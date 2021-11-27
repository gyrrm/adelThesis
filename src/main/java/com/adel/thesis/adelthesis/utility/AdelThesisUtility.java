package com.adel.thesis.adelthesis.utility;

import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

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

    public org.json.simple.JSONObject stringToJson(String body) {

        org.json.simple.JSONObject json = new org.json.simple.JSONObject();

        JSONParser parser = new JSONParser(); try {
            json = (org.json.simple.JSONObject) parser. parse(body);
        } catch (ParseException e) {
            e.printStackTrace();
        }

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

        profileFromDatabase.getJSONObject("profile").getJSONObject("security").remove("acivationCode");
        profileFromDatabase.getJSONObject("profile").getJSONObject("security").remove("activeInd");

        profileFromDatabase.getJSONObject("profile").getJSONObject("security").put("acivationCode", "");
        profileFromDatabase.getJSONObject("profile").getJSONObject("security").put("activeInd", "A");

        return profileFromDatabase;

    }
}
