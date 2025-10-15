package Pack1;
 
import org.testng.annotations.Test;
 
public class TC009 {
  @Test(priority=3)
  public void yahootest() {
	  System.out.println("This is test1");
  }
  
  @Test(priority=4)
  public void aaaa() {
	  System.out.println("This is test2");
  }
  
  @Test(priority=1)
  public void zohotest() {
	  System.out.println("This is test3");
  }
  
  @Test(priority=2)
  public void amazontest() {
	  System.out.println("This is test4");
  }
  
}
 
 