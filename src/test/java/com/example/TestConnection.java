package com.example;
import org.testng.annotations.Test;

public class TestConnection 
{
    @Test
    public void testConnection()
    {
        EstablishSalesforceConnection ec = new EstablishSalesforceConnection();
        ec.establishConnection();
        System.out.println("Connection succesfull");
    }       
}