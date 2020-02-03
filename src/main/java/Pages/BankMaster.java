package Pages;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BankMaster {
    WebDriver driver;
    public BankMaster(WebDriver driver){
        this.driver=driver;

    }
    public BankHome getBankHome(String URL){
        driver.get(URL);
        BankHome home= new BankHome(driver);

        return home;
    }
    public void waitFor(int time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public List<String> getElementTexts(String locator){
        List<String> actualOptionResultList = new ArrayList<>();


        if(locator.contains("Xpath")) {
            List<WebElement> alloptionsinUI = driver.findElements(By.xpath(locator.split(":")[1]));
            System.out.println("Total List Size " + alloptionsinUI.size() );
            for(int i=0;i<alloptionsinUI.size();i++) {
                System.out.println(alloptionsinUI.get(i).getText());
                actualOptionResultList.add(alloptionsinUI.get(i).getText());
            }
        }


        return actualOptionResultList;
    }

    public void clickElement(String locatores) {
        if(locatores.contains("ID")) {
            driver.findElement(By.id(locatores.split(":")[1])).click();
            waitFor(1000);
        } else if(locatores.contains("Xpath")) {
            driver.findElement(By.xpath(locatores.split(":")[1])).click();
            waitFor(1000);
        } else if(locatores.contains("Name")) {
            driver.findElement(By.name(locatores.split(":")[1])).click();
            waitFor(1000);
        }

    }
    public String getElementText(String locatores) {

        try {
            if(locatores.contains("ID")) {
                return driver.findElement(By.id(locatores.split(":")[1])).getText();
            }  else if(locatores.contains("Xpath")) {
                return driver.findElement(By.xpath(locatores.split(":")[1])).getText();
            } else if(locatores.contains("Name")) {
                return driver.findElement(By.name(locatores.split(":")[1])).getText();
            }
        }catch(Exception e) {
            return "NULL";
        }

        return "NULL";

    }
    public void waitUntilClickAble(By locator){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    public void waitUntilVisible(By locator){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    public void waitUntilSelectable(By locator){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        boolean element = wait.until(ExpectedConditions.elementToBeSelected(locator));
    }
    //get WebElements
    public List<WebElement> getWebElements(String locator){
        List<WebElement> elements = driver.findElements(By.cssSelector(locator));
        return elements;
    }
    //get texts
    public List<String> getElementTexts2(String  locator){
        List<String> texts = new ArrayList<String>();
        List<WebElement> element = new ArrayList<WebElement>();
        element = getWebElements(locator);
        for(WebElement st:element){
            texts.add(st.getText());
        }
        return texts;
    }

    //Keys
    public void clearInputBox(String locator){
        driver.findElement(By.cssSelector(locator)).clear();
    }

    public void mouseHoverByCSS(String locator){
        try {
            WebElement element = driver.findElement(By.cssSelector(locator));
            Actions action = new Actions(driver);
            Actions hover = action.moveToElement(element);
        }catch(Exception ex){
            System.out.println("First attempt has been done, This is second try");
            WebElement element = driver.findElement(By.cssSelector(locator));
            Actions action = new Actions(driver);
            action.moveToElement(element).perform();

        }

    }
    public void mouseHoverByXpath(String locator){
        try {
            WebElement element = driver.findElement(By.xpath(locator));
            Actions action = new Actions(driver);
            Actions hover = action.moveToElement(element);
        }catch(Exception ex){
            System.out.println("First attempt has been done, This is second try");
            WebElement element = driver.findElement(By.cssSelector(locator));
            Actions action = new Actions(driver);
            action.moveToElement(element).perform();

        }

    }
    //handling Alert
    public void okAlert(){
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }
    public void cancelAlert(){
        Alert alert = driver.switchTo().alert();
        alert.dismiss();
    }

    //iFrame Handle
    public void iframeHandle(WebElement element){
        driver.switchTo().frame(element);
    }

    public void goBackToHomeWindow(){
        driver.switchTo().defaultContent();
    }

    //get Links
    public void getLinks(String locator){
        driver.findElement(By.linkText(locator)).findElement(By.tagName("a")).getText();
    }

    //Taking Screen shots
    public void takeScreenShot(String test)throws IOException {
        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file,new File(test+".png"));
    }
}
