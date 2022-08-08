package ABC_DataProvider;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TC01_Addlocation {
	
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
	     data[0][3]= "London";
	     data[0][4]= "United Kingdom";
	      
	     // Second student details
	     data[1][0]= "https://opensource-demo.orangehrmlive.com/"; 
	     data[1][1]= "Admin";
	     data[1][2]= "admin123";
	     data[1][3]= "Delhi";
	     data[1][4]= "India";
	     
	     //third
	     //data[0][0]= "https://opensource-demo.orangehrmlive.com/"; 
	     //data[0][1]= "Admin";
	     //data[0][2]= "admin123";
	     //data[0][3]= "Hyderabad";
	     //data[0][4]= "India";
	     
	     return data;
	      
	      
	 }
	 
	
  @Test(dataProvider="TestDataProvider2") //Step2 
  public void LocationTest(String TestURL,String UserName,String Password,String City,String Country)throws Exception {
	  
	  TC01_Addlocation T1=new TC01_Addlocation();
	  
	  T1.OpenChromeBrowser();
	  T1.OpenOrangeHRM(TestURL); //Step2
	  T1.Login(UserName,Password);//step2
	T1.Addlocation(City,Country);//Step2
	
	
	  
  }
  
  
  
  
  
  public void OpenChromeBrowser()throws Exception {
	  
	  System.setProperty("webdriver.chrome.driver","C:\\chromedriver_win32\\chromedriver.exe");
		driver =new ChromeDriver();
		driver.manage().window().maximize() ;	
  }
  
  
  public void OpenOrangeHRM(String TestURL1)throws Exception {
	  

		driver.get(TestURL1);
  }
  
  public void Login(String UserName1,String Password1)throws Exception {
	  
		findElement(By.id("txtUsername")).sendKeys(UserName1);
		findElement(By.id("txtPassword")).sendKeys(Password1);
		findElement(By.id("btnLogin")).click();
  }
  
  public void Addlocation(String City1,String Country1)throws Exception {
	  
	  findElement(By.id("menu_admin_viewAdminModule")).click();
		findElement(By.id("menu_admin_Organization")).click();
		findElement(By.id("menu_admin_viewLocations")).click();
		//findElement(By.linkText("Location")).click();
		findElement(By.id("btnAdd")).click();
		findElement(By.name("location[name]")).sendKeys(City1);
		findElement(By.id("location_country")).sendKeys(Country1);
		//btnSave
		findElement(By.id("btnSave")).click();
		
		//Select dropdown2 = new Select(driver.findElement(By.name("location_country")));
		//dropdown2.selectByVisibleText(Country1);
		
		//findElement(By.id("btnSave")).click();
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
