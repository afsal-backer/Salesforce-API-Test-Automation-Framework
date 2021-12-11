package com.example;

import static io.restassured.RestAssured.given;

import java.util.List;
import java.util.Map;

import io.restassured.http.ContentType;

public class TestRecordsUsingSOQL 
{
    public String getSoqlResult(String soql, String fieldName)
    {
        System.out.println("SOQL to execute is: "+soql);

        EstablishSalesforceConnection ec = new EstablishSalesforceConnection();

        List<Map<String, Object>> recordsArray = 
            given().
            contentType(ContentType.JSON).
            header("Authorization","Bearer "+ ec.establishConnection()).
            get("https://afsal-dev-ed.my.salesforce.com/services/data/v32.0/query?q="+soql+"").
            then().extract().path("records");
           
        String fieldValue = (String) recordsArray.get(0).get(fieldName);
        return fieldValue;   
    }    
}
