package com.example;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestSalesInsert 
{
    public String name = "name"+" "+new Random().nextInt(1000);
    public String phone = "4081234567";
    public String oppStage = "Closed Won";
    public String closeDate = "2022-07-07";

    @Test
    public void testSalesInsert()
    {
        InsertAccount ia = new InsertAccount();
        System.out.println("Inserting account record.");

        String accountId = ia.insertAccount(name, phone);
        System.out.println("Inserted account's id is: "+accountId);
        
        InsertContact ic = new InsertContact();
        System.out.println("Inserting Contact record.");

        String contactId = ic.insertContact(accountId, name);
        System.out.println("Inserted Contact's id is: "+contactId);

        InsertOpportunity ip = new InsertOpportunity();
        System.out.println("Inserting Opportunity record.");

        String opportunityId = ip.insertOpportunity(accountId, name, oppStage, closeDate);
        System.out.println("Inserted Opportunity's id is: "+opportunityId);

        //Test account name

        TestRecordsUsingSOQL sl = new TestRecordsUsingSOQL();
        String accountNameReturned = sl.getSoqlResult("SELECT Name from Account WHERE id = '"+accountId+"'", "Name");
        System.out.println("Account name is: "+accountNameReturned);
        Assert.assertEquals(accountNameReturned, name);

        //Test contact name from account
        String contactNameReturned = sl.getSoqlResult("SELECT Name from Contact WHERE Accountid = '"+accountId+"'", "Name");
        System.out.println("Contact name is: "+contactNameReturned);
        Assert.assertEquals(contactNameReturned, name);

        //Test opportunity stage from account
        String opportunityStageReturned = sl.getSoqlResult("SELECT StageName from Opportunity WHERE Accountid = '"+accountId+"'", "StageName");
        System.out.println("Opportunity Stage is: "+opportunityStageReturned);
        Assert.assertEquals(opportunityStageReturned, oppStage);

    }
    
}

