package com.adel.thesis.adelthesis.service.dao;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.adel.thesis.adelthesis.model.request.RegistrationRequest;
import com.adel.thesis.adelthesis.utility.AdelThesisUtility;

import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DaoOperations {

    @Autowired
    AdelThesisUtility adelThesisUtility;

    public int writeToJson(JSONObject requestBodyAsJson) throws IOException, ParseException {

        int statusCode;

        JSONParser parser = new JSONParser();

        JSONArray profilesArrayFromFile = new JSONArray();

        File databaseJson = new File("database.json");

        if(new File("database.json").length() != 0) {

            Object obj = parser.parse(new FileReader("database.json"));
            String jsonObject = obj.toString();

            if(!jsonObject.isEmpty() && jsonObject != null) {

                profilesArrayFromFile = new JSONArray(jsonObject);
            }
        } else {
            profilesArrayFromFile = new JSONArray();
        }

        System.out.println(profilesArrayFromFile.toString());

        //String arrayAsString = jsonObject.get("profiles").toString();

        if(databaseJson.exists() && databaseJson.isFile()) {
            System.out.println("MIAFASZ");
            databaseJson.delete();
        }

        databaseJson.createNewFile();

        try {

            BufferedWriter out = new BufferedWriter(new FileWriter("database.json", false));

            profilesArrayFromFile.put(requestBodyAsJson);

            out.write(profilesArrayFromFile.toString());
            out.close();
            statusCode = 200;
        } catch (IOException e) {
            statusCode = 500;
            e.printStackTrace();
        }

        return statusCode;
    }

    public org.json.JSONObject readFromJsonForRegistration(RegistrationRequest request) {

        JSONParser parser = new JSONParser();

        Object obj = new Object();

        JSONArray profilesArrayFromFile = new JSONArray();

        try {
            obj = parser.parse(new FileReader("database.json"));
            String objAsString = obj.toString();
            profilesArrayFromFile = new JSONArray(objAsString);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return adelThesisUtility.getUserFromJSONArrayForRegistration(profilesArrayFromFile, request);
    }

    public org.json.JSONObject readFromJsonForActivation(org.json.JSONObject request) {

        JSONParser parser = new JSONParser();

        Object obj = new Object();

        JSONArray profilesArrayFromFile = new JSONArray();

        try {
            obj = parser.parse(new FileReader("database.json"));
            String objAsString = obj.toString();
            profilesArrayFromFile = new JSONArray(objAsString);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return new org.json.JSONObject();
    }

    public org.json.JSONObject readFromJsonForLogin(org.json.JSONObject request) {

        JSONParser parser = new JSONParser();

        Object obj = new Object();

        JSONArray profilesArrayFromFile = new JSONArray();

        try {
            obj = parser.parse(new FileReader("database.json"));
            String objAsString = obj.toString();
            profilesArrayFromFile = new JSONArray(objAsString);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return new org.json.JSONObject();
    }
}
