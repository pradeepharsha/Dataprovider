package ABC_DataProvider;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Day_034_TestNG_Listeners.TestBrowser;
import ExcelUtil.ExcelApiTest4;

public class TC01_Add_Skills8 {
	
	static WebDriver driver;
	
	
	
	//Step1 - copy and paste dataprovider synatx - Test Data modification
	 @DataProvider(name = "TestDataProvider1")
	  public static Object[][] Test1() throws Exception {
	 
	        return new Object[][] { 
	        	{ "https://opensource-demo.orangehrmlive.com/","Admin", "admin123","Java1","Java1 Descr" },
	        	{ "https://opensource-demo.orangehrmlive.com/","Admin", "admin123","Java2","Java2 Descr"  },
	           	{ "https://opensource-demo.orangehrmlive.com/","Admin", "admin123","Java3","Java3 Descr"  },
	           	{ "https://opensource-demo.orangehrmlive.com/","Admin", "admin123","Java4","Java4 Descr"  }
	        	};
	  }
	 
	 
	 @DataProvider(name = "TestDataProvider2")
	 public Object[] myDataProvider() {
	      
	     Object data[][]=  new Object[2][5];
	     // First student details
	     data[0][0]= "https://opensource-demo.orangehrmlive.com/"; 
	     data[0][1]= "Admin";
	     data[0][2]= "admin123";
	     data[0][3]= "Java5";
	     data[0][4]= "Java5 descr";
	      
	     // Second student details
	     data[1][0]= "https://opensource-demo.orangehrmlive.com/"; 
	     data[1][1]= "Admin";
	     data[1][2]= "admin123";
	     data[1][3]= "Java6";
	     data[1][4]= "Java6 descr";
	     
	     return data;
	      
	      
	 }
	 
	 @DataProvider(name = "TC01_Add_Skills")
		public static Object[][] Authentication1() throws Exception {
			
			ExcelApiTest4 eat = new ExcelApiTest4();
			Object[][] testObjArray = eat.getTableArray("C://HTML Report//OrangeHRM6//TC01_Skills88.xlsx", "Sheet1");
			
			
			System.out.println(testObjArray.length);
			return (testObjArray);
		}
	 
	 
	 @DataProvider(name = "TC01_Add_Skills1")
		public static Object[][] TestData2() throws Exception {
			
			ExcelApiTest4 eat = new ExcelApiTest4();
			Object[][] testObjArray = eat.getTableArray("C://HTML Report//OrangeHRM6//TC01_Skills89.xlsx", "Sheet1");
			
			
			System.out.println(testObjArray.length);
			return (testObjArray);
		}
	 
	
  @Test(dataProvider="TC01_Add_Skills1") //Step2 
  public void SkillsTest(String Browser,String TestURL,String USerName,String Password,String SkillName,String SkillDescr)throws Exception {
	  
	  TC01_Add_Skills8 T1=new TC01_Add_Skills8();
	  
	 //T1.OpenChromeBrowser();
	  
	  if(Browser.equalsIgnoreCase("Chrome"))
	  {
		  driver=TestBrowser.OpenChromeBrowser();
	  }
	  
	  if(Browser.equalsIgnoreCase("FireFox"))
	  {
		  driver=TestBrowser.FirefoxBrowser();
	  }
	  
	  
	   driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  T1.OpenOrangeHRM(TestURL); //Step2
	  T1.Login(USerName,Password);//step2
	T1.AddSKills(SkillName,SkillDescr);//Step2
	
	  
  }
  
  
  
  
  
  public void OpenChromeBrowser()throws Exception {
	  
	  System.setProperty("webdriver.chrome.driver","C:\\chromedriver_win32\\chromedriver.exe");
		driver =new ChromeDriver();
		driver.manage().window().maximize() ;	
  }
  
  
  public void OpenOrangeHRM(String TestURL1)throws Exception {
	  

		driver.get(TestURL1);
  }
  
  public void Login(String USerName1,String Password1)throws Exception {
	  
		findElement(By.id("txtUsername")).sendKeys(USerName1);
		findElement(By.id("txtPassword")).sendKeys(Password1);
		findElement(By.id("btnLogin")).click();
  }
  
  public void AddSKills(String SkillName1,String SkillDescr1)throws Exception {
	  
	  
	   driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		findElement(By.id("menu_admin_viewAdminModule")).click();
		
		//findElement(By.id("menu_admin_Qualifications")).click();
		//findElement(By.id("menu_admin_viewSkills")).click();
		
		WebElement Qualifications=findElement(By.id("menu_admin_Qualifications"));
		
		
		
	    
	    
	    Actions action = new Actions(driver);
	    action.moveToElement(Qualifications).build().perform();
	    
	    WebDriverWait wait = new WebDriverWait(driver, 10);
	    
	    WebElement Skills =wait.until(ExpectedConditions.elementToBeClickable(By.id("menu_admin_viewSkills"))); 
	    action.moveToElement(Skills).click().build().perform();
	    
		// JavascriptExecutor js = (JavascriptExecutor)driver;	
		// js.executeScript("arguments[0].click();", Qualifications);
		 //js.executeScript("arguments[0].click();", Skills);
	    
	    Thread.sleep(3000);
		
		findElement(By.id("btnAdd")).click();
		findElement(By.id("skill_name")).sendKeys(SkillName1);
		findElement(By.id("skill_description")).sendKeys(SkillDescr1);
		findElement(By.id("btnSave")).click();
  }
  
  public  WebElement findElement(By by) throws Exception 
	{

		WebElement elem = driver.findElement(by);  
		
		if (driver instanceof JavascriptExecutor) 
		{
		 ((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid blue'", elem);
	 
		}
		return elem;
	}



  
}
