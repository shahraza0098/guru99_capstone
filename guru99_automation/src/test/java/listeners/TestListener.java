package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.*;
import base.BaseTest;
import base.DriverFactory;
import utilities.ExtentManager;
import utilities.ScreenshotUtil;

public class TestListener implements ITestListener {

    private static ExtentReports extent =
            ExtentManager.getInstance();

    private static ThreadLocal<ExtentTest> test =
            new ThreadLocal<>();

    @Override
    public void onStart(ITestContext context) {

        System.out.println("Execution Started");
    }

    @Override
    public void onTestStart(ITestResult result) {
    	System.out.println("Listener: Test Started -> "
                + result.getMethod().getMethodName());

        ExtentTest extentTest =
                extent.createTest(
                        result.getMethod()
                                .getMethodName());

        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {

    	System.out.println("Listener: Test Passed -> "
                + result.getMethod().getMethodName());
        test.get().pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {

    	 System.out.println("Listener: Test Failed -> "
    	            + result.getMethod().getMethodName());
        test.get().fail(result.getThrowable());

        try {

//            Object currentClass =
//                    result.getInstance();
//
//            BaseTest base =
//                    (BaseTest) currentClass;

            String screenshotPath =
                    ScreenshotUtil.captureScreenshot(
                    		DriverFactory.getDriver(),
                            result.getMethod()
                                    .getMethodName());

            test.get().addScreenCaptureFromPath(screenshotPath);

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        test.get().skip("Test Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {

        extent.flush();

        System.out.println("Execution Completed");
    }
}