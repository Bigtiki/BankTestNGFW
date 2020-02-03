package Pages;

import org.openqa.selenium.WebDriver;

public class BankProfile extends BankMaster {

    public BankProfile(WebDriver driver) {
        super(driver);
    }
    String profileLinks="Xpath://ul[@class=\"menusubnav\"]//a";
    String customer="Xpath://a[text()=\"New Customer\"]";
    String addCV="Xpath://p[text()=\"Add New Customer\"]";


    public String getProfile(){

        return getElementTexts(profileLinks).get(0);

    }
    public String getCustomerForm(){
        waitFor(2000);
        driver.manage().window().fullscreen();
    clickElement(customer);
    String textAdd=getElementText(addCV);
    class AddC{

    }
return textAdd;
    }
}
