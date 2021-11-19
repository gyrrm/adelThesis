package com.adel.thesis.adelthesis.service.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

@Component
public class DaoOperations {

    public int writeToJson(JSONObject requestBodyAsJson) {

        int statusCode;

        try {
            FileWriter fileWriter = new FileWriter("database.json");
            fileWriter.write(requestBodyAsJson.toJSONString());
            fileWriter.close();
            statusCode = 200;
        } catch (IOException e) {
            statusCode = 500;
            e.printStackTrace();
        }

        return statusCode;
    }

    public JSONObject readFromJson() {

        JSONParser parser = new JSONParser();

        JSONObject obj = new JSONObject();

        try {
            obj = (JSONObject) parser.parse(new FileReader("database.json"));
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

        return (JSONObject) obj;
    }
}
