package Pack1;
 
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
 
public class login_pagefactory {
	WebDriver driver;
	@FindBy(name="username")
		WebElement	uname;
	//By uname=By.name("username");
//By pword=By.name("password");
 
@FindBy(name="password")
WebElement	pword;
 
//By submitbutton=By.xpath("//button[@type='submit']");
 
@FindBy(xpath="//button[@type='submit']")
WebElement	submitbutton;
 
//By dashboard=By.xpath("//h6[text()='Dashboard']");
 
@FindBy(xpath="//h6[text()='Dashboard']")
WebElement	dashboard;
 
public void enterusername(String username)
{
	uname.sendKeys(username);
}
public void enterpassword(String password)
{
	pword.sendKeys(password);
}
 
public void clickonsubmit()
{
	submitbutton.click();
}
 
public boolean dashboarddisplayed()
{
	return dashboard.isDisplayed();
}
 
}
 
	
 
 