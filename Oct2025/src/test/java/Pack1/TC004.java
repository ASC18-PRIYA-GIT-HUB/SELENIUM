package Pack1;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TC004 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.get("https://mail.rediff.com/cgi-bin/login.cgi");
		driver.findElement(By.name("proceed")).click();
		Alert simplealert=driver.switchTo().alert();
		System.out.println("Message is:"+simplealert.getText());
		simplealert.accept();
		driver.get("https://letcode.in/alert");
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

}