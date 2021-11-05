package com.adel.thesis.adelthesis.service.dao;

import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject;
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

    public void readFromJson(){

    }
}
