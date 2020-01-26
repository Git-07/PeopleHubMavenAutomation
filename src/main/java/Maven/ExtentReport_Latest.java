package Maven;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentReport_Latest implements IReporter{
	private ExtentHtmlReporter reporter;
	ExtentReports extent = new ExtentReports();
	public void buildTestNodes(IResultMap tests, Status status) throws IOException {
		//ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		ExtentTest test;
		if (tests.size() > 0) {
			for (ITestResult result : tests.getAllResults()) {
				test = extent.createTest(result.getName());
				//test = extent.createTest("Test The new Latest Extent Report code");	
				/*
				  test.getTest(). = getTime(result.getStartMillis());
				  test.getTest().endedTime = getTime(result.getEndMillis());
				 */

				for (String group : result.getMethod().getGroups())
					test.assignCategory(group);
                
				String message = "Test " + status.toString().toLowerCase() + "ed";				
				//System.out.println("jkbaojfbjefbvwiohbvhiwebvewhivb " + message);
				if (result.getThrowable() != null)
					message = result.getThrowable().getMessage();
	/*			String screens = System.getProperty("user.dir") + "/ScreenShots/";
				//System.Diagnostics.Stopwatch sw = new System.Diagnostics.Stopwatch();
			    File directory = new File(screens);
			    Image image = ImageIO.read(directory);*/
				//[-]*[0-9]*\\.(png|jpg|jpeg)
				//String screens = System.getProperty("user.dir") + "/ScreenShots";
				 String screens = "C:\\Users\\mohit\\.jenkins\\workspace\\Maven_Github_Project\\ScreenShots";
				//String screens =  "ScreenShots";
				File directory = new File(screens);
				//FileFilter f = new WildcardFileFilter(result.getName());
				File[] paths = directory.listFiles();
				for(int i=0; i<paths.length; i++){
			/*		System.out.println("sdncjsncjldsncdscljnds " + paths.length);
					System.out.println(paths[i]);*/
					if(paths[i].toString().contains(result.getName())){
						//if (result.getThrowable() != null)
							//message = result.getThrowable().getMessage();
						//String pat----------------h = System.getProperty("user.dir") + "/ScreenShots/"+result.getName()+".png";				
					 // String image = test.addScreenCapture(paths[i].toString());	
					    
			test.log(status, message,
					//MediaEntityBuilder.createScreenCaptureFromPath(paths[i].toString()).build());
					MediaEntityBuilder.createScreenCaptureFromBase64String(paths[i].toString()).build());
             
					}
				}
			/*	String path = System.getProperty("user.dir") + "/ScreenShots/"+result.getName()+".png";				
				String image = test.addScreenCapture(path);
				test.log(status, result.getMethod().getMethodName(), image);
				test.log(status, message);*/
				//}
				
				
		}			
		}
		
	}

	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String  outputDirectory) {
		//extent = new ExtentReports("./Report/extent_report.html", true);          
		reporter = new ExtentHtmlReporter(outputDirectory + File.separator+ "extent_report.html");
		for (ISuite suite : suites) {
			Map<String, ISuiteResult> result = suite.getResults();

			for (ISuiteResult r : result.values()) {
				ITestContext context = r.getTestContext();
				try {
					buildTestNodes(context.getPassedTests(),Status.PASS);
					//System.out.println("ekjfwb vjkrw vbkjwr jkwr kjlb rkj bjkwr klb rjl " + Status.PASS);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					buildTestNodes(context.getFailedTests(), Status.FAIL);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					buildTestNodes(context.getSkippedTests(),Status.SKIP);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
		extent.flush();	
	}
}
