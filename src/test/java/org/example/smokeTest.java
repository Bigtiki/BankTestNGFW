package org.example;

import Pages.BankHome;
import Pages.BankMaster;
import Pages.BankProfile;
import Pages.Base;
import Utility.SharedConfig;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

public class smokeTest extends Base {

    BankMaster masterPage;
    @Test
    public void gotoBankHome(){
        Map<String,String >config=SharedConfig.config;
        masterPage = new BankMaster(getDriver());
       BankHome home= masterPage.getBankHome(config.get("URL"));
        System.out.println("*******************************\n\t\tTest 1");
    }
    @Test
    public void loginBankProfile(){
        Map<String,String >config=SharedConfig.config;
        String role = config.get("role");

        masterPage = new BankMaster(getDriver());
        BankHome home= masterPage.getBankHome(config.get("URL"));

        BankProfile user=home.login("username","password");
        user.getProfile();
        Assert.assertEquals(user.getProfile(),role,"Did not passXXXXX");
        System.out.println("*******************************\n\t\tTest 2");
    }
    @Test
    public void addNewCustomer(){
        Map<String,String >config=SharedConfig.config;
        String role = config.get("role");
        String activity= config.get("activity");

        masterPage = new BankMaster(getDriver());
        BankHome home= masterPage.getBankHome(config.get("URL"));

        BankProfile user=home.login("username","password");
        String actualUser=user.getProfile();
        Assert.assertEquals(actualUser,role,"Did not passXXXXX");
        String addACust=user.getCustomerForm();
        Assert.assertEquals(addACust,activity,"Did not passXXXXX");
        System.out.println("*******************************\n\t\tTest 3");
    }
}
