package com.sangam.utilities;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Listeners extends TestListenerAdapter
{
 
 public ExtentHtmlReporter htmlReporter;
 public ExtentReports extent;
 public ExtentTest logger;
 
  
 public void onStart(ITestContext testContext)
 {
  htmlReporter=new ExtentHtmlReporter(System.getProperty("user.dir")+ "/test-output/myReport.html");
  htmlReporter.loadXMLConfig(System.getProperty("user.dir")+ "/extent-config.xml");
  extent=new ExtentReports();
  extent.attachReporter(htmlReporter);
  extent.setSystemInfo("Host name","My Shopify");
  extent.setSystemInfo("Environemnt","QA");
  extent.setSystemInfo("user","nitish");
  
  htmlReporter.config().setDocumentTitle("Automation Report"); 
  htmlReporter.config().setReportName("Automation Testing"); 
  htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP); 
  htmlReporter.config().setTheme(Theme.STANDARD);
 }
 
 public void onTestSuccess(ITestResult tr)
 {
  logger=extent.createTest(tr.getName()); // create new entry in th report
  logger.log(Status.PASS,MarkupHelper.createLabel(tr.getName(),ExtentColor.GREEN)); 
 }
 
 public void onTestFailure(ITestResult tr)
 {
  logger=extent.createTest(tr.getName()); // create new entry in th report
  logger.log(Status.FAIL,MarkupHelper.createLabel(tr.getName(),ExtentColor.RED));
  
  String screenshotPath=System.getProperty("user.dir")+"\\Screenshots\\"+tr.getName()+".png";
  try {
	  
	  TakesScreenshot scrShot =((TakesScreenshot)new ChromeDriver());
	  File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
      FileUtils.copyFile(SrcFile,new File(screenshotPath));

	  
   logger.fail("Screenshot is below:" + logger.addScreenCaptureFromPath(screenshotPath));
  } catch (IOException e) {
    e.printStackTrace();
  } 
 }
 
 public void onTestSkipped(ITestResult tr)
 {
  logger=extent.createTest(tr.getName()); // create new entry in th report
  logger.log(Status.SKIP,MarkupHelper.createLabel(tr.getName(),ExtentColor.ORANGE));
 }
 
 public void onFinish(ITestContext testContext)
 {
  extent.flush();
 }
 
 }