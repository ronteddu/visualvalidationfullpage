package CompareScreens;


import java.io.File;
import java.io.FileInputStream;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.selenium.StitchMode;
import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.TestResults;
import com.applitools.eyes.exceptions.*;


public class TotalCompare {
	private String appName = "VU - NewTheme - CoursePageEssentials - 2018";
	private String testName = "Visual Regression ";
	private String apiKey = "9n7r1015D58urNEZFZVeMno8Ai70IYYcrpjPFXdyhmQv4110";
	private BatchInfo batchName;
	private Integer BatchID;
	private int SprintNo;
	public Eyes eyes = new Eyes();
	public String OName;
	//private int numberOfMisMatches = 0;
	//private int numberOfMatches = 0;
	private RectangleSize ViewPort;

	public TotalCompare()
	{
		
		eyes.setForceFullPageScreenshot(true);
		eyes.setStitchMode(StitchMode.CSS);
		eyes.setApiKey(apiKey);
		BatchID = (int) (Integer.MAX_VALUE * Math.random());
		SprintNo = 47;

	}

	public void CompScreens(WebDriver driver, String Os, String BName,  RectangleSize Vsize) throws Exception {
		
	
		this.OName = Os;
	//	BatchFileHandler batchFileHandler = new BatchFileHandler();
	//	this.batchName = new BatchInfo( "Sprint:"+SprintNo+" PW-VicUni-Regression Sprint# " + Os+" " + BName);
	//	this.batchName = new BatchInfo("PW-VicUni-Regression Sprint  " + SprintNo);	
		this.batchName = new BatchInfo("PW-VicUni-Regression Sprint 49");	
	//	batchFileHandler.addIdentifier(this.batchName.toString());
		this.ViewPort = Vsize;
		this.eyes.setBatch(batchName);		
	//	this.batchName.setId(batchFileHandler.getBatchId());
		batchName.setId("Regression");
	
		File Bfile = new File("");
		String filePath  = Bfile.getAbsolutePath()+"/TestData";
        String fileName = "TestData.xlsx";
        String sheetName = "URLSheet";
        File file =    new File(filePath+"/"+fileName);
      

        FileInputStream inputStream = new FileInputStream(file);
        Workbook URLWorkbook = null;
        String fileExtensionName = fileName.substring(fileName.indexOf("."));


        if(fileExtensionName.equals(".xlsx")){                          
                                    URLWorkbook = new XSSFWorkbook(inputStream); 
                                    }
        else if(fileExtensionName.equals(".xls")){
            URLWorkbook = new HSSFWorkbook(inputStream);
        }
       
        Sheet URLSheet = URLWorkbook.getSheet(sheetName);
        int rowCount = URLSheet.getLastRowNum()-URLSheet.getFirstRowNum();
   //rowCount+1     
        for (int i = 1 ; i < 246   ; i++) 
        {
	        	Row row = URLSheet.getRow(i);
		    	DataFormatter formatter = new DataFormatter();
	    	try {
	    		 driver.get("https://"+formatter.formatCellValue(row.getCell(2)));
	    	}
	    	catch(Exception e) {
	    		
		    		driver.navigate().refresh();
		    		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);	
	    	}
	    	finally {
	    		 String ScrName =  driver.getTitle();
	    		 compareScreenshotAgainstBaseline(driver, i+" "+ScrName, Vsize);
	    	}
}
        driver.quit();

	}
	
	public void compareScreenshotAgainstBaseline(WebDriver driver, String ScnName, RectangleSize Viewport) throws Exception {


		try 
		{
			/*	if (this.OName.equals("Android"))
			{
				WebElement elementLogo = DriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("logo-element")));
				Assert.assertTrue(elementLogo.isDisplayed());				
			}
			else
			{
				 System.out.print("The Driver is in Android chrome Diamond logo exception");
				WebElement elementLogo = DriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("diamond")));
				Assert.assertTrue(elementLogo.isDisplayed());	
			} */
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);	
			this.eyes.open(driver, this.appName, ScnName, this.ViewPort);
			this.eyes.checkWindow(ScnName);
			this.eyes.close(false);
		

		} 
		catch(Exception e)
		{
			
			if (this.OName.equals("Android"))
			{
		
			this.eyes.open(driver, this.appName, ScnName);
			}
			else
			{
				driver.navigate().refresh();
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);	
				this.eyes.open(driver, this.appName, ScnName, this.ViewPort);
				
			}
			
			this.eyes.checkWindow(ScnName);
			this.eyes.close(false); 
			
		}
		finally 
		{
			this.eyes.abortIfNotClosed();
		
		}

		
	}
		
	}

