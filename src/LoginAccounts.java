import java.awt.Color;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginAccounts implements Runnable {
  private Details details;
  
  private WebDriver driver;
  
  public static int semaphore;
  
  public LoginAccounts(Details details, WebDriver driver) {
    this.details = details;
    this.driver = driver;
  }
  
  private void login() {
    String uname = this.details.getUsername();
    String pwd = this.details.getPassword();
    String subjectLink = this.details.getSubjectLink();
    this.driver.get(subjectLink);
    int index = this.details.getIndex();
    boolean done = false;
    while (!done) {
      try {
        WebElement username = this.driver.findElement(By.id("username"));
        WebElement password = this.driver.findElement(By.id("password"));
        WebElement login = this.driver.findElement(By.id("loginbtn"));
        username.sendKeys(new CharSequence[] { uname });
        password.sendKeys(new CharSequence[] { pwd });
        login.click();
        try {
          this.driver.findElement(By.id("loginbtn"));
        } catch (Exception e) {
          done = true;
          semaphore++;
          MarkMultipleAccounts.loggedIn[index] = true;
        } 
        if (semaphore == MarkMultipleAccounts.list.size()) {
          MainActivity.loginBtn.setText("All Accounts Logged in");
          MainActivity.loginBtn.setForeground(new Color(0, 255, 0));
        } 
      } catch (Exception exception) {}
    } 
  }
  
  public void run() {
    login();
  }
}
