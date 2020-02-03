package listenerTestNG;



import Pages.BankMaster;
import Pages.Base;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class ListenerTestNG implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Test Started and the test Case is --> "+ result.getName());
    }
    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test Failed and the test Case is --> "+ result.getName());
    }
    @Override
    public void onTestSuccess(ITestResult result) {
        try {
            BankMaster obj= new BankMaster(Base.getDriver());

            obj.takeScreenShot(result.getName());
            System.out.println("Test Success and the test Case is --> "+ result.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test Skipped and the test Case is --> "+ result.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
         System.out.println("Test case finish and the test Case is --> "+ context.getName());
    }
}
