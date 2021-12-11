package com.example;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;

public class InsertAccount 
{
   public String insertAccount(String accName, String phone)
   {

    EstablishSalesforceConnection ec = new EstablishSalesforceConnection();

    Map<String,Object> mapper = new HashMap<String,Object>();

    mapper.put("Name", accName);
    mapper.put("Phone", phone);

    JSONObject requester = new JSONObject(mapper); 

    System.out.println("Account JSON file is: "+requester.toString());

    return
		 given().
		 contentType(ContentType.JSON).
		 accept(ContentType.JSON).
		 header("Authorization", "Bearer " + ec.establishConnection()).
		 header("Content-Type","application/json").
		 body(requester.toString()).
		 when().post("https://afsal-dev-ed.my.salesforce.com/services/data/v51.0/sobjects/Account").
		 then().statusCode(201).log().body().extract().path("id");
   } 
}
