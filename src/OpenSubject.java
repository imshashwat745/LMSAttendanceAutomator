import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OpenSubject implements Runnable {
  private Details details;
  
  private WebDriver driver;
  
  public OpenSubject(Details details, WebDriver driver) {
    this.details = details;
    this.driver = driver;
  }
  
  private void openSubject() {
    int index = this.details.getIndex();
    try {
      WebElement codeBox = this.driver.findElement(By.name("qrpass"));
      codeBox.sendKeys("");
      codeBox.sendKeys(new CharSequence[] { MainActivity.textField.getText() });
      codeBox.sendKeys(new CharSequence[] { (CharSequence)Keys.ENTER });
      try {
        WebElement ele = this.driver.findElement(By.xpath("//button[text()='Continue']"));
        ele.click();
      } catch (Exception e) {
        MarkMultipleAccounts.marked[index] = true;
        MainActivity.numberOfMarked++;
        MainActivity.updateMarked();
      } 
    } catch (Exception e) {
      MainActivity.canAskForRefresh++;
      MainActivity.canAskForRefresh %= App.list.size();
      if (MainActivity.canAskForRefresh == 1)
        MainActivity.askRefresh(); 
    } 
    
    ++MarkMultipleAccounts.numberOfMarked;
    if(MarkMultipleAccounts.numberOfMarked==MarkMultipleAccounts.toMark) {
    	MarkMultipleAccounts.isMarking=false;
    }
  }
  
  public void run() {
    openSubject();
  }
}
