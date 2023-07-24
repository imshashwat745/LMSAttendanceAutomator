import java.awt.Color;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class MarkMultipleAccounts {
  public static List<Details> list;
  
  private static WebDriver[] driver;
  
  public static boolean[] marked;
  
  public static boolean[] loggedIn;
  
  public static boolean isRefreshing;
  public static boolean isMarking;
  
  public static int numberOfRefreshed;
  public static int numberOfMarked;
  public static int toMark;
  
  MarkMultipleAccounts(List<Details> list) {
    MarkMultipleAccounts.list = list;
    driver = new WebDriver[list.size()];
    marked = new boolean[list.size()];
    loggedIn = new boolean[list.size()];
    for (int i = 0; i < list.size(); ) {
      loggedIn[i] = false;
      marked[i] = false;
      i++;
    } 
    isRefreshing = false;
    isMarking=false;
  }
  
  public static void login() {
    LoginAccounts[] loginAccounts = new LoginAccounts[list.size()];
    int i = 0;
    EdgeOptions op = new EdgeOptions();
    op.addArguments(new String[] { "--disable-gpu", "--blink-settings=imagesEnabled=false","--remote-allow-origins=*" });
    LoginAccounts.semaphore = 0;
    for (Details details : list) {
      driver[i] = (WebDriver)new EdgeDriver(op);
      driver[i].manage().window().minimize();
      (new Thread(new LoginAccounts(details, driver[i]))).start();
      i++;
    } 
  }
  
  public static void mark() {
    if (LoginAccounts.semaphore < list.size() || isRefreshing)
      return; 
    isMarking=true;
    numberOfMarked=0;
    toMark=0;
    for (int i = 0; i < list.size(); i++) {
      if (!marked[i])
    	  toMark++;
        try {
          (new Thread(new OpenSubject(list.get(i), driver[i]))).start();
        } catch (Exception exception) {} 
    } 
  }
  
  public void refresh() {
    if (LoginAccounts.semaphore < list.size()||isMarking)
      return; 
    isRefreshing = true;
    numberOfRefreshed = 0;
    MainActivity.refreshLabel.setForeground(new Color(255, 0, 0));
    MainActivity.refreshLabel.setText("Refereshing wait...");
    for (int i = 0; i < list.size(); i++) {
      try {
        (new Thread(new Refresh(driver[i]))).start();
      } catch (Exception exception) {
    	  numberOfRefreshed++;
    	  if(numberOfRefreshed==App.list.size()) {
    		  isRefreshing=false;
    		  MainActivity.refreshLabel.setForeground(new Color(0, 255, 0));
    	      MainActivity.refreshLabel.setText("Done Refreshing");
    	  }
      }
    } 
  }
  
  public void close() {
    byte b;
    int i;
    WebDriver[] arrayOfWebDriver;
    for (i = (arrayOfWebDriver = driver).length, b = 0; b < i; ) {
      WebDriver webDriver = arrayOfWebDriver[b];
      try {
        webDriver.close();
      } catch (Exception exception) {}
      b++;
    } 
  }
}
