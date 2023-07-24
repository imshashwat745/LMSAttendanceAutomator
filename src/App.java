import java.util.ArrayList;
import java.util.List;

public class App {
  public static String[] links = new String[] { "https://ada-lms.thapar.edu/moodle/mod/attendance/view.php?id=106949", 
      "https://ada-lms.thapar.edu/moodle/mod/attendance/view.php?id=106249", 
      "https://ada-lms.thapar.edu/moodle/mod/attendance/view.php?id=105843", 
      "https://ada-lms.thapar.edu/moodle/mod/attendance/view.php?id=105887" };
  
  private static MarkMultipleAccounts markMultipleAccounts;
  
  static List<Details> list;
  
  public static void init() {
    list = new ArrayList<>();
    list.add(new Details("sshukla_be20@thapar.edu", "Maa@9630", 0));
    list.add(new Details("psingh2_be20@thapar.edu", "Pran*1347", 1));
    list.add(new Details("bsingh3_be20@thapar.edu", "TIET149718#lms", 2));
    list.add(new Details("vverma_be20@thapar.edu", "13oct2k1@A", 3));
    list.add(new Details("avohra_be20@thapar.edu", "TIET137821#lms", 4));
    list.add(new Details("sagarwal_be20@thapar.edu", "TIET164079#lms", 5));
    markMultipleAccounts = new MarkMultipleAccounts(list);
  }
  
  public static void login(String[] args) {
    String link = links[MainActivity.subjectIndex];
    if (MainActivity.serverMenu.getSelectedIndex() > 0) {
      link = link.replaceFirst("ada-", (MainActivity.serverMenu.getSelectedIndex() == 4) ? "" : (
          String.valueOf(MainActivity.servers[MainActivity.serverMenu.getSelectedIndex()]) + "-"));
      System.out.println(link);
    } 
    Details.subjectLink = link;
    MarkMultipleAccounts.login();
  }
  
  public static void mark() {
    MarkMultipleAccounts.mark();
  }
  
  public static void close() {
    markMultipleAccounts.close();
  }
  
  public static void refresh() {
    markMultipleAccounts.refresh();
  }
}
