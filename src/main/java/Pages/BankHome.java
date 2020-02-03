package Pages;

import Utility.SharedConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Map;

public class BankHome extends BankMaster{
    Map<String,String > config= SharedConfig.config;
    public BankHome(WebDriver driver) {

        super(driver);
       PageFactory.initElements(driver,this);
    }
    @FindBy(name="uid")
    private WebElement userId;
    @FindBy(name="password")
   private  WebElement password;
    @FindBy(name="btnLogin")
    private WebElement loginBtn;
    @FindBy(xpath = "//marquee")
    WebElement welcome;

    public void enterUserName(){
        userId.sendKeys(config.get("username"));

    }
    public void enterPassword(){
        password.sendKeys(config.get("password"));

    }
    public void clickLogin(){
        loginBtn.click();

    }
    public BankProfile login(String user, String pass){
        userId.sendKeys(config.get(user));
        password.sendKeys(config.get(pass));
        loginBtn.click();
        waitFor(10000);
        if(welcome.isDisplayed()){
            System.out.println(welcome.getText());
        return new BankProfile(driver);
        }else{
            System.out.println("Log in failed");
    return null;
    }
    }

}
