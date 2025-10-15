package Pack1;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TC005 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.get("https://letcode.in/window");
		System.out.println("parent window:"+driver.getWindowHandle());
		driver.findElement(By.id("multi")).click();
		Set<String> windows=driver.getWindowHandles();
		System.out.println("no of windows:"+windows.size());
		for(String childwindow: windows)
		{
			driver.switchTo().window(childwindow);
			System.out.println("URL is:"+driver.getCurrentUrl());
			if(driver.getCurrentUrl().equals("https://letcode.in/alert"))
			{
				driver.findElement(By.id("confirm")).click();
				Alert confirmalert=driver.switchTo().alert();
				System.out.println("message is:"+confirmalert.getText());
				confirmalert.accept();
				driver.findElement(By.id("prompt")).click();
				Alert promptalert=driver.switchTo().alert();
		        System.out.println("message is:"+promptalert.getText());
		        promptalert.sendKeys("priya");
		        promptalert.accept();
			}
			if(driver.getCurrentUrl().equals("https://letcode.in/dropdowns"))
			{
				System.out.println("drop down activity here");
				WebElement show=driver.findElement(By.id("fruits"));
				
				Select sle1=new Select(show);
				//sle.selectByIndex(3);
				List<WebElement> showalloptions=sle1.getOptions();
				
				for(WebElement option1:showalloptions)
				{
					System.out.println("show options:"+option1.getText());
				}
				
			}
		}	
	}
}
