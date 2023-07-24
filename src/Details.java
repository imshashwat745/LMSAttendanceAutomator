public class Details {
  private String username;
  
  private String password;
  
  public static String subjectLink;
  
  private int index;
  
  public Details(String username, String password, int index) {
    this.username = username;
    this.password = password;
    this.index = index;
  }
  
  public int getIndex() {
    return this.index;
  }
  
  public String getUsername() {
    return this.username;
  }
  
  public String getPassword() {
    return this.password;
  }
  
  public String getSubjectLink() {
    return subjectLink;
  }
}
