import java.awt.Color;
import org.openqa.selenium.WebDriver;

public class Refresh implements Runnable {
  WebDriver driver;
  
  Refresh(WebDriver driver) {
    this.driver = driver;
  }
  
  private void refresh() {
    this.driver.get(this.driver.getCurrentUrl());
//	  this.driver.navigate().refresh();
//    boolean done = false;
//    while (!done) {
//      try {
//        this.driver.get(this.driver.getCurrentUrl());
//        done = true;
//      } catch (Exception e) {
//        done = false;
//      } 
//    } 
    MarkMultipleAccounts.numberOfRefreshed++;
    if (MarkMultipleAccounts.numberOfRefreshed == App.list.size()) {
      MainActivity.refreshLabel.setForeground(new Color(0, 255, 0));
      MainActivity.refreshLabel.setText("Done Refreshing");
      MarkMultipleAccounts.isRefreshing = false;
    } 
  }
  
  public void run() {
    refresh();
  }
}
