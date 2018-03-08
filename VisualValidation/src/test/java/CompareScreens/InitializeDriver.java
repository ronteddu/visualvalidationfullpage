package CompareScreens;

import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import com.applitools.eyes.RectangleSize;

public class InitializeDriver {
	//VIEWPORT SIZES DEFINED
	
		public static RectangleSize XLG = new RectangleSize(1920, 1080);
		public static RectangleSize LG = new RectangleSize(1440, 900);
		public static RectangleSize MD = new RectangleSize(1024,768);
		public static RectangleSize SM  = new RectangleSize(768, 1024);
		public static RectangleSize XS = new RectangleSize(360, 640);
		
		
		public void InitPlatform(String OName,String Brname) throws Exception 
		{

			TotalCompare Runcompare = new TotalCompare();
			DesiredCapabilities caps = new DesiredCapabilities();
			WebDriver driver = null;
			if(OName.equals("Windows") && Brname.equals("IE"))
			{	
				caps.setBrowserName("internet explorer");
				caps.setPlatform(Platform.WINDOWS);	
				driver = new RemoteWebDriver(new URL("http://140.159.197.22:5511/wd/hub"), caps);
				Runcompare.CompScreens(driver,OName, Brname,LG);
			}
			if(OName.equals("Windows") && Brname.equals("Firefox"))
			{	
				caps.setCapability("browserName","firefox");
				caps.setPlatform(Platform.WINDOWS);
				driver = new RemoteWebDriver(new URL("http://140.159.197.22:5511/wd/hub"), caps);
				Runcompare.CompScreens(driver,OName, Brname,LG);
			}
			if(OName.equals("Windows") && Brname.equals("Chrome"))
			{
					caps.setBrowserName("chrome");
					caps.setPlatform(Platform.WINDOWS);	
					driver = new RemoteWebDriver(new URL("http://140.159.197.22:5511/wd/hub"), caps);
					Runcompare.CompScreens(driver,OName, Brname,LG);
					
			}
			if(OName.equals("MAC") && Brname.equals("Chrome"))
			{	
				
				caps.setCapability("browserName", "chrome");
				caps.setCapability("platform", "MAC");
				URL url = new URL("http://140.159.197.37:5588/wd/hub");
				driver = new RemoteWebDriver(url, caps);	
				driver.manage().deleteAllCookies();
				Runcompare.CompScreens(driver,OName, Brname,LG);
				
			}
			if(OName.equals("MAC") && Brname.equals("Safari")) 
			{
		/*		SafariOptions sOptions = new SafariOptions();
				sOptions.setUseTechnologyPreview(true);
				caps.setPlatform(Platform.MAC);
				caps.setBrowserName("safari");
		//		caps.setCapability("browserName", "safari");
		//		caps.setCapability("platform", "MAC");
			//	caps.setCapability("technologyPreview", false);
				caps.setCapability(SafariOptions.CAPABILITY, sOptions);
				URL url = new URL("http://140.159.197.37:5588/wd/hub");  
				driver = new RemoteWebDriver(url, caps);	 */
				caps.setBrowserName("safari");
				caps.setPlatform(Platform.MAC);
		//		desiredCapabilities.setCapability("OS", "MAC");
				URL url = new URL("http://140.159.197.37:5588/wd/hub");
				driver = new RemoteWebDriver(url, caps);
				Runcompare.CompScreens(driver,OName, Brname,XLG);
			}
			if(OName.equals("Android") && Brname.equals("Chrome"))
			{
				caps.setCapability("platformName", "ANDROID");
				caps.setCapability("browserName", "Chrome");
				caps.setCapability("applicationName", "Galaxy S6");
				caps.setCapability("platform", "ANDROID");
				caps.setCapability("deviceName", "Galaxy S6");
				URL url = new URL("http://140.159.197.22:4723/wd/hub"); 
			    driver = new RemoteWebDriver(url, caps);	
				Runcompare.CompScreens(driver,OName, Brname,XS);
			}
			if(OName.equals("Iphone") && Brname.equals("Chrome"))
			{
				
			}
			if(OName.equals("Iphone") && Brname.equals("Safari"))
			{
				
			}
		}
		
}
