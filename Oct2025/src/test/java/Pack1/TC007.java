package Pack1;
 
import java.time.Duration;
 
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
 
import io.github.bonigarcia.wdm.WebDriverManager;
 
public class TC007 {
 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
 
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://amazon.in");
		JavascriptExecutor js=(JavascriptExecutor)driver;
		
		//js.executeScript("alert('welcome to javascriptexe');");
		js.executeScript("window.scrollBy(0,700)");
		//js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		
	}
 
}
 