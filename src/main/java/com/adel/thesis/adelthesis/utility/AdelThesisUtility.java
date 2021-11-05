package com.adel.thesis.adelthesis.utility;

import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
public class AdelThesisUtility {

    public int generateUserId(){

        return 0;
    }

    public String createResponseBody(String status, String message, String service) {

        return new JSONObject()
                    .put("status", status)
                    .put("message", message)
                    .put("service", service).toString();

    }

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
}
