package CompareScreens;


import org.testng.annotations.Test;
import com.applitools.eyes.RectangleSize;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;

public class CompareScreenTest {
	

	
	@Test(description="Run Windows n IE validations")	
	public void WindowsIE() throws Exception
		{
			InitializeDriver InitDriver = new InitializeDriver();
			InitDriver.InitPlatform("Windows","IE");
		}

	@Test (description =  "OperatingSystem = Windows and Browser = Firefox")
	public void WindowsFF() throws Exception
		{
			InitializeDriver InitDriver = new InitializeDriver();
			InitDriver.InitPlatform("Windows","Firefox");
		}  

	@Test(description="OperatingSystem = Windows and Browser = Chrome")
	public void WindowsChrome() throws Exception
		{
			InitializeDriver InitDriver = new InitializeDriver();		
			InitDriver.InitPlatform("Windows","Chrome");
			
		}  
	@Parameters("MAC")
	@Test(description = "OperatingSystem = MAC and Browser = Chrome")
	public void MacChrome() throws Exception
		{			
			InitializeDriver InitDriver = new InitializeDriver();
			InitDriver.InitPlatform("MAC","Chrome");		
		
		} 

	@Test(description = "OperatingSystem = MAC and Browser = Safari")
	public void MacSafari()throws Exception
		{
			InitializeDriver InitDriver = new InitializeDriver();
			InitDriver.InitPlatform("MAC","Safari");
		
		} 
	
	@Test(description = "OperatingSystem = Android and Browser = Chrome")
	public void AndroidChrome() throws Exception
	{
		InitializeDriver InitDriver = new InitializeDriver();
		InitDriver.InitPlatform("Android","Chrome");
	}  
 
 
}

