package selanium.tabHandling;

import java.sql.Driver;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Tab_Handling {
	WebDriver driver;
	
	
	@Before
	public void setup() {
		//call Chrome driver
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		 
		//call demo site which we want test
		driver.get("https://demoqa.com/browser-windows");
		System.out.println(driver.getTitle());
	}
	
	@Test
	public void tab_Handling() throws InterruptedException {
	
		
		WebElement Tab_Btn1_Element=driver.findElement(By.id("tabButton"));
		Tab_Btn1_Element.click();
		System.out.println("child Tab= "+driver.getTitle());
		
		Set<String> tabHandling=driver.getWindowHandles();
		Iterator<String> itr=tabHandling.iterator();
		System.out.println(itr);
		String parentTab_ID=itr.next();
		String childTab1_ID=itr.next();
		String childTab2_ID=itr.next();
		
		System.out.println(parentTab_ID);
		System.out.println(childTab2_ID);
		
		driver.switchTo().window(childTab1_ID);
		
		WebElement child= driver.findElement(By.xpath("//h1[@id='sampleHeading']"));
		System.out.println("CHILD TAB TITLE"+driver.getTitle());
		System.out.println(child.getText());
		System.out.println("CHILD TAB = "+childTab1_ID);
	
		//System.out.println("PARENT_TAB ="+ Tab_Btn1_Element);
		//driver.navigate().back();
	
		
		driver.switchTo().window(parentTab_ID);
		WebElement Tab_Btn2_Element=driver.findElement(By.id("tabButton"));
		Tab_Btn1_Element.click();
		driver.switchTo().window(childTab2_ID);
		Thread.sleep(2000);
		
		String Third_tab=itr.next();
		System.out.println(driver.getTitle());
		System.out.println(Third_tab);
		
		System.out.println(driver.getTitle() );
		
		//WebElement new_Window=driver.findElement(By.id("windowButton"));
		//new_Window.click();
				
	}
	
	@After
	public void close_Window() throws InterruptedException {
		Thread.sleep(2000);
		driver.quit();
	}


}
