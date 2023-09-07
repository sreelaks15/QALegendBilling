package com.QALegendBilling.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.QALegendBilling.automationcore.Base;
import com.QALegendBilling.extentmanager.ExtentManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class TestListener extends Base implements ITestListener{
	//Extent Report Declarations
		private static final ExtentReports extent = ExtentManager.createInstance();
		private static final ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();

		@Override
		public synchronized void onStart(ITestContext context) {
		    System.out.println("Extent Reports Version 3 Test Suite started!");
		}

		@Override
		public synchronized void onFinish(ITestContext context) {
		    System.out.println(("Extent Reports Version 3  Test Suite is ending!"));
		    extent.flush();
		}

		@Override
		public synchronized void onTestStart(ITestResult result) {
		    System.out.println((result.getMethod().getMethodName() + " started!"));
		    ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName(), result.getMethod().getDescription());
		    test.set(extentTest);
		}

		@Override
		public synchronized void onTestSuccess(ITestResult result) {
		    System.out.println((result.getMethod().getMethodName() + " passed!"));
		    test.get().pass("Test passed");
		}

		@Override
		public synchronized void onTestFailure(ITestResult result) {
		    System.out.println((result.getMethod().getMethodName() + " failed!"));
		    test.get().fail(result.getThrowable());
		}

		@Override
		public synchronized void onTestSkipped(ITestResult result) {
		    System.out.println((result.getMethod().getMethodName() + " skipped!"));
		    test.get().skip(result.getThrowable());
		}

		@Override
		public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		    System.out.println(("onTestFailedButWithinSuccessPercentage for " + result.getMethod().getMethodName()));
		}

		public static ThreadLocal<ExtentTest> getTestInstance(){
		    return test;
		}

}
