package Pack1;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TC002 {
	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.amazon.in/");
		List<WebElement> amazonlinks=driver.findElements(By.tagName("a"));
		System.out.println("Total no of links:"+amazonlinks.size());
		for(WebElement link: amazonlinks)
		{
			System.out.println("Link is:"+link.getText());
		}
	}
}
