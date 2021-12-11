package com.example;
import static io.restassured.RestAssured.*;

public class EstablishSalesforceConnection
{
    public String establishConnection()
    {
        return
        given().urlEncodingEnabled(true)
        .param("username", "gm4afsal@gmail.com")
        .param("password", "Automation@123+your security token")
        .param("client_id", "your consumer key")
        .param("client_secret", "your consumer secret")
        .param("grant_type", "password")
        .header("Accept", "application/json")
        .when().
        post("https://login.salesforce.com/services/oauth2/token").
        then().
        assertThat().statusCode(200).log().body().extract().path("access_token");

    }
}