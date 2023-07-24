import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class MainActivity {
  private int mouseX;
  
  private int mouseY;
  
  public static JLabel refreshLabel;
  
  private static JFrame frame;
  
  private boolean canLogin;
  
  public static JLabel loginBtn;
  
  public static JTextField textField;
  
  public static int subjectIndex;
  
  private JComboBox comboBox;
  
  private JButton submitBtn;
  
  private static JLabel marked;
  
  public static int numberOfMarked;
  
  public static String[] servers = new String[] { "ada", "ramanujan", "archimedes", "murphy", "lms" };
  
  public static JComboBox serverMenu;
  
  private JLabel txtLmsMultipleAccounts;
  
  private JButton txtI;
  
  private JLabel lblNewLabel_2;
  
  private Panel panel;
  
  private Panel panel_1;
  
  private JButton close;
  
  private JButton exit;
  
  private JButton maximize;
  
  private JButton minimize;
  
  private JLabel label;
  
  private JButton contact;
  
  public static int canAskForRefresh;
  
  static void updateMarked() {
    marked.setText("Number of maked:" + numberOfMarked + "/" + MarkMultipleAccounts.list.size());
  }
  
  public static void askRefresh() {
    JOptionPane.showMessageDialog(frame, "REFRESH the page or the attendance is over", 
        "Refresh", 0);
  }
  
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
          public void run() {
            try {
              MainActivity window = new MainActivity();
              MainActivity.frame.setVisible(true);
            } catch (Exception e) {
              e.printStackTrace();
            } 
          }
        });
  }
  
  public MainActivity() {
    initialize();
    canAskForRefresh = 0;
  }
  
  private void initialize() {
    this.canLogin = true;
    numberOfMarked = 0;
    App.init();
    frame = new JFrame();
    frame.setUndecorated(true);
    frame.getContentPane().setBackground(new Color(255, 255, 255));
    loginBtn = new JLabel("All Accounts not logged in", 0);
    loginBtn.setBounds(178, 60, 268, 28);
    loginBtn.setFont(new Font("Tahoma", 0, 17));
    loginBtn.setForeground(new Color(255, 0, 0));
    frame.setBounds(100, 100, 905, 585);
    frame.setDefaultCloseOperation(3);
    JButton loginButton = new JButton("Login Accounts");
    loginButton.setBounds(336, 215, 123, 30);
    loginButton.setForeground(new Color(255, 255, 255));
    loginButton.setBackground(new Color(0, 0, 0));
    loginButton.setBorderPainted(false);
    loginButton.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            if (MainActivity.this.canLogin) {
              MainActivity.subjectIndex = MainActivity.this.comboBox.getSelectedIndex();
              if (MainActivity.subjectIndex == 4) {
                JOptionPane.showMessageDialog(MainActivity.frame, "Please Select a subject", 
                    "Error", 0);
                return;
              } 
              App.login(null);
              MainActivity.this.canLogin = false;
            } 
          }
        });
    String[] subjects = { "TOC", "CG", "OT", "MicroProcessor", "Select Subject" };
    this.comboBox = new JComboBox(subjects);
    this.comboBox.setBounds(191, 115, 268, 30);
    this.comboBox.setCursor(Cursor.getPredefinedCursor(0));
    this.comboBox.setBackground(new Color(255, 255, 255));
    this.comboBox.setBorder((Border)null);
    this.comboBox.setSelectedIndex(4);
    textField = new JTextField();
    textField.setBounds(191, 325, 118, 30);
    textField.setText("Enter Code");
    textField.setForeground(Color.GRAY);
    textField.addFocusListener(new FocusAdapter() {
          public void focusGained(FocusEvent e) {
            if (MainActivity.textField.getText().equals("Enter Code")) {
              MainActivity.textField.setText("");
            } else {
              MainActivity.textField.setText(MainActivity.textField.getText());
            } 
          }
          
          public void focusLost(FocusEvent e) {
            if (MainActivity.textField.getText().equals("Enter Code") || MainActivity.textField.getText().length() == 0) {
              MainActivity.textField.setText("Enter Code");
              MainActivity.textField.setForeground(Color.GRAY);
            } else {
              MainActivity.textField.setText(MainActivity.textField.getText());
              MainActivity.textField.setForeground(Color.BLACK);
            } 
          }
        });
    textField.setColumns(10);
    this.submitBtn = new JButton("Submit");
    this.submitBtn.setBounds(336, 325, 123, 30);
    this.submitBtn.setBorderPainted(false);
    this.submitBtn.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            if (MainActivity.this.canLogin) {
              JOptionPane.showMessageDialog(MainActivity.frame, "First Login all accounts", 
                  "Error", 0);
              return;
            } 
            if (LoginAccounts.semaphore < MarkMultipleAccounts.list.size()) {
              JOptionPane.showMessageDialog(MainActivity.frame, "App is setting up login Please Wait||", 
                  "Wait", 0);
              return;
            } 
            
            if(MarkMultipleAccounts.isMarking) {
            	JOptionPane.showMessageDialog(MainActivity.frame, "App is marking,Please wait||", 
                        "Wait", 0);
                    return;
            }
            if(MarkMultipleAccounts.isRefreshing) {
            	JOptionPane.showMessageDialog(MainActivity.frame, "App is Refreshing,Please wait||", 
            			"Wait", 0);
            	return;
            }
            
            MainActivity.loginBtn.setText("All Accounts Logged in");
            MainActivity.loginBtn.setForeground(new Color(0, 255, 0));
            App.mark();
          }
        });
    this.submitBtn.setForeground(new Color(255, 255, 255));
    this.submitBtn.setBackground(new Color(30, 144, 255));
    serverMenu = new JComboBox(servers);
    serverMenu.setBounds(191, 215, 118, 30);
    serverMenu.setBackground(new Color(255, 255, 255));
    serverMenu.setSelectedIndex(2);
    this.panel = new Panel();
    this.panel.setBounds(159, 455, 746, 186);
    this.panel.setBackground(new Color(0, 0, 0));
    this.txtLmsMultipleAccounts = new JLabel("LMS Multiple Accounts Attendance Marker", 0);
    this.txtLmsMultipleAccounts.setBounds(0, 25, 746, 54);
    this.txtLmsMultipleAccounts.setFont(new Font("Palatino Linotype", 0, 20));
    this.txtLmsMultipleAccounts.setBackground(new Color(0, 0, 0));
    this.txtLmsMultipleAccounts.setForeground(new Color(255, 255, 255));
    JLabel lblNewLabel_3 = new JLabel("2023 - 2024 TheOriginals - All Rights Reserved.", 0);
    lblNewLabel_3.setBounds(0, 89, 746, 30);
    lblNewLabel_3.setFont(new Font("Tahoma", 0, 13));
    lblNewLabel_3.setForeground(new Color(255, 255, 255));
    this.label = new JLabel("New label");
    this.label.setBounds(653, 122, 45, 13);
    this.panel_1 = new Panel();
    this.panel_1.setBounds(0, 20, 159, 575);
    this.panel_1.setBackground(new Color(30, 144, 255));
    JLabel lblNewLabel = new JLabel("Subject", 0);
    lblNewLabel.setBounds(0, 95, 159, 30);
    lblNewLabel.setFont(new Font("Tahoma", 0, 21));
    lblNewLabel.setAlignmentX(0.5F);
    lblNewLabel.setBackground(new Color(255, 255, 255));
    lblNewLabel.setForeground(new Color(255, 255, 255));
    this.txtI = new JButton();
    this.txtI.setBounds(0, 40, 57, 28);
    this.txtI.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(MainActivity.frame, "1.First select subject then server \n2.Then click login accounts \n3.Then enter code\n4.Click Submit(No.of marked will show how many people are marked\n4.Now for next subject to mark attandance,first restart app,then do the process again:)", 
                
                "Guide", 1);
          }
        });
    this.txtI.setFont(new Font("Segoe Print", 0, 20));
    this.txtI.setBackground(new Color(0, 0, 0));
    this.txtI.setForeground(new Color(255, 255, 255));
    this.txtI.setText("i");
    this.txtI.setBorderPainted(false);
    this.txtI.setContentAreaFilled(false);
    this.txtI.setFocusPainted(false);
    this.txtI.setOpaque(false);
    JLabel lblNewLabel_1 = new JLabel("Enter Code", 0);
    lblNewLabel_1.setBounds(0, 305, 159, 30);
    lblNewLabel_1.setFont(new Font("Tahoma", 0, 21));
    lblNewLabel_1.setForeground(new Color(255, 255, 255));
    this.lblNewLabel_2 = new JLabel("Server", 0);
    this.lblNewLabel_2.setBounds(0, 195, 159, 30);
    this.lblNewLabel_2.setFont(new Font("Tahoma", 0, 21));
    this.lblNewLabel_2.setForeground(new Color(255, 255, 255));
    marked = new JLabel("Number of marked: " + numberOfMarked + "/" + App.list.size(), 0);
    marked.setBounds(0, 481, 159, 30);
    marked.setFont(new Font("Tahoma", 0, 12));
    marked.setForeground(new Color(255, 255, 0));
    this.contact = new JButton();
    this.contact.setBounds(0, 521, 159, 28);
    this.contact.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(MainActivity.frame, "Contact \"theoriginals.software@gmail.com\" for feedback and queries", 
                "Contact", 1);
          }
        });
    this.contact.setText("Contact");
    this.contact.setOpaque(false);
    this.contact.setForeground(Color.WHITE);
    this.contact.setFont(new Font("Tahoma", 0, 20));
    this.contact.setFocusPainted(false);
    this.contact.setContentAreaFilled(false);
    this.contact.setBorderPainted(false);
    this.contact.setBackground(Color.BLACK);
    frame.getContentPane().setLayout((LayoutManager)null);
    frame.getContentPane().add(this.panel_1);
    this.panel_1.setLayout((LayoutManager)null);
    this.panel_1.add(this.txtI);
    this.panel_1.add(lblNewLabel);
    this.panel_1.add(this.lblNewLabel_2);
    this.panel_1.add(lblNewLabel_1);
    this.panel_1.add(marked);
    this.panel_1.add(this.contact);
    frame.getContentPane().add(loginBtn);
    frame.getContentPane().add(this.comboBox);
    frame.getContentPane().add(this.panel);
    this.panel.setLayout((LayoutManager)null);
    this.panel.add(this.txtLmsMultipleAccounts);
    this.panel.add(lblNewLabel_3);
    this.panel.add(this.label);
    frame.getContentPane().add(serverMenu);
    frame.getContentPane().add(textField);
    frame.getContentPane().add(loginButton);
    frame.getContentPane().add(this.submitBtn);
    Panel panel_2 = new Panel();
    panel_2.addMouseMotionListener(new MouseMotionAdapter() {
          public void mouseDragged(MouseEvent e) {
            MainActivity.frame.setLocation(MainActivity.frame.getX() + e.getX() - MainActivity.this.mouseX, MainActivity.frame.getY() + e.getY() - MainActivity.this.mouseY);
          }
        });
    panel_2.addMouseListener(new MouseAdapter() {
          public void mousePressed(MouseEvent e) {
            MainActivity.this.mouseX = e.getX();
            MainActivity.this.mouseY = e.getY();
          }
        });
    panel_2.setBackground(new Color(0, 0, 0));
    panel_2.setBounds(0, 0, 905, 20);
    frame.getContentPane().add(panel_2);
    panel_2.setLayout((LayoutManager)null);
    this.minimize = new JButton("");
    this.minimize.setBounds(864, 5, 10, 10);
    panel_2.add(this.minimize);
    this.minimize.setBorderPainted(false);
    this.minimize.setBackground(new Color(255, 255, 0));
    this.exit = new JButton("");
    this.exit.setBounds(884, 5, 10, 10);
    panel_2.add(this.exit);
    this.exit.setBorderPainted(false);
    this.exit.setBackground(new Color(255, 0, 0));
    JButton btnRefresh = new JButton("Refresh");
    btnRefresh.setActionCommand("Refresh");
    btnRefresh.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            if (MainActivity.this.canLogin) {
              JOptionPane.showMessageDialog(MainActivity.frame, "First Login all accounts", 
                  "Error", 0);
              return;
            } 
            if (LoginAccounts.semaphore < MarkMultipleAccounts.list.size()) {
              JOptionPane.showMessageDialog(MainActivity.frame, "App is setting up login Please Wait||", 
                  "Wait", 0);
              return;
            } 
            if(MarkMultipleAccounts.isMarking) {
            	JOptionPane.showMessageDialog(MainActivity.frame, "App is marking,Please wait||", 
                        "Wait", 0);
                    return;
            }
            if(MarkMultipleAccounts.isRefreshing) {
            	JOptionPane.showMessageDialog(MainActivity.frame, "App is Refreshing,Please wait||", 
            			"Wait", 0);
            	return;
            }
            App.refresh();
          }
        });
    btnRefresh.setForeground(new Color(255, 255, 255));
    btnRefresh.setBorderPainted(false);
    btnRefresh.setBackground(new Color(255, 128, 0));
    btnRefresh.setBounds(191, 270, 118, 30);
    frame.getContentPane().add(btnRefresh);
    refreshLabel = new JLabel("");
    refreshLabel.setFont(new Font("Tahoma", 0, 17));
    refreshLabel.setBounds(336, 270, 174, 30);
    frame.getContentPane().add(refreshLabel);
    this.exit.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            if (!MainActivity.this.canLogin)
              App.close(); 
            System.exit(0);
          }
        });
    this.minimize.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            MainActivity.frame.setExtendedState(1);
          }
        });
  }
}
