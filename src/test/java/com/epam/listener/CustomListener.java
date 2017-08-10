package com.epam.listener;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class CustomListener implements ITestListener{
    private static Logger logger = Logger.getLogger(CustomListener.class);

    public void onTestStart(ITestResult iTestResult) {

        logger.info("Test : " +iTestResult.getName() +" Method : " +iTestResult.getMethod());
    }

    public void onTestSuccess(ITestResult iTestResult) {
        logger.info("Test : " +iTestResult.getName() +" Method : " +iTestResult.getMethod());
    }

    public void onTestFailure(ITestResult iTestResult) {
        logger.warn("Test : " +iTestResult.getName() +" Method : " +iTestResult.getMethod());
        logger.warn(iTestResult.getThrowable());
    }

    public void onTestSkipped(ITestResult iTestResult) {
        logger.info("Test : " +iTestResult.getName() +" Method : " +iTestResult.getMethod());
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        logger.info("Test : " +iTestResult.getName() +" Method : " +iTestResult.getMethod());
    }

    public void onStart(ITestContext iTestContext) {
        logger.info("Starting tests : " +iTestContext.getName()
                +" , Output directory : " +iTestContext.getOutputDirectory()
                +" , Test Suite : " +iTestContext.getSuite());
    }

    public void onFinish(ITestContext iTestContext) {
        logger.info("Starting tests : " +iTestContext.getName()
                +" , Output directory : " +iTestContext.getOutputDirectory()
                +" , Test Suite : " +iTestContext.getSuite());
    }
}
