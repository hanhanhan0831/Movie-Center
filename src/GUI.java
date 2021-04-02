import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class GUI extends JFrame implements ActionListener{
	// ======================= Parameters
	static JPanel panel = new JPanel();
	static JMenuBar menuBar = new JMenuBar();
//	static JFrame folderSelect;
	static JFrame loginWindow;
	static MovieCenterDB database = new MovieCenterDB("db.txt");
	// ======================= Constructors
	public GUI() {
		setSize(1000, 1000);
		setTitle("Movie Center");
		setJMenuBar(menuBar);
		add(panel);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setAlwaysOnTop(true);
		setVisible(true);
	}
	
	
	// ======================= Getters/Setters
	// ======================= Methods
	public static void main(String[] args) {
		buildMenuBar();
		buildPanel();
		new GUI();
		openLoginWindow();
	}
	public static void buildPanel() {
		
	}
	public static void buildMenuBar() {
		// =================== Objects
		JMenu file = new JMenu("File");
		JMenu help = new JMenu("Help");
//		JMenuItem newFolder = new JMenuItem("New");
//		JMenuItem openFolder = new JMenuItem("Open");
//		JMenuItem saveFolder = new JMenuItem("Save");
//		JMenuItem saveAsFolder = new JMenuItem("Save As...");
//		JMenuItem removeFolder = new JMenuItem("Remove");
//		JMenuItem howTo = new JMenuItem("How To...");
//		JMenuItem contactMe = new JMenuItem("Contact Me");
//		file.add(newFolder);
//		file.add(openFolder);
//		file.add(saveFolder);
//		file.add(saveAsFolder);
//		file.add(removeFolder);
//		help.add(howTo);
//		help.add(contactMe);
		menuBar.add(file);
		menuBar.add(help);
//		
//		newFolder.setActionCommand("newFolder");
//		openFolder.setActionCommand("openFolder");
//		saveFolder.setActionCommand("saveFolder");
//		saveAsFolder.setActionCommand("saveAsFolder");
//		removeFolder.setActionCommand("removeFolder");
//		howTo.setActionCommand("howTo");
//		contactMe.setActionCommand("contactMe");
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		switch (cmd) {
//		case "newFolder":
//			newFolderWindow();
//			break;
//		case "openFolder":
//			openFolderWindow();
//			break;
		}
		
	}

	private static void openLoginWindow() {
		loginWindow = new JFrame("Login or Create Account:");
		LoginDialog loginDlg = new LoginDialog(loginWindow);
        loginDlg.setVisible(true);
        // if logon successfully
        if(loginDlg.loginSuccess()){
//            log.setText("Hi " + loginDlg.getUn() + "!");
        }
        loginWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginWindow.setSize(300, 100);
        loginWindow.setLayout(new FlowLayout());
//        loginWindow.getContentPane().add(btnLogin);
        loginWindow.setVisible(true);
	}
	@SuppressWarnings("unlikely-arg-type")
	protected static boolean authenticate(String username, String password) {
		for (Account a : database.userList.keySet()) {
			if (a.equals(username) && database.userList.get(a).equals(password)) {
				return true;
			} 
		}
		return false;
	}

	
}

class LoginDialog extends JDialog {
	private JTextField unField;
	private JPasswordField pwField;
	private JLabel unLabel;
	private JLabel pwLabel;
	private JButton loginBtn;
	private JButton newAcntBtn;
	private JButton cancelBtn;
	private boolean login;
	
	public LoginDialog(JFrame parent) {
		super(parent, "Login", true);

		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints cs = new GridBagConstraints();

		cs.fill = GridBagConstraints.HORIZONTAL;

		unLabel = new JLabel("Username: ");
		cs.gridx = 0;
		cs.gridy = 0;
		cs.gridwidth = 1;
		panel.add(unLabel, cs);

		unField = new JTextField(20);
		cs.gridx = 1;
		cs.gridy = 0;
		cs.gridwidth = 2;
		panel.add(unField, cs);

		pwLabel = new JLabel("Password: ");
		cs.gridx = 0;
		cs.gridy = 1;
		cs.gridwidth = 1;
		panel.add(pwLabel, cs);

		pwField = new JPasswordField(20);
		cs.gridx = 1;
		cs.gridy = 1;
		cs.gridwidth = 2;
		panel.add(pwField, cs);
		panel.setBorder(new LineBorder(Color.GRAY));
		
		loginBtn = new JButton("Login");
		loginBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (GUI.authenticate(getUn(), getPw())) {
					JOptionPane.showMessageDialog(LoginDialog.this,  "Hi " + getUn() + "! You have successfully logged in.",
							"Login", JOptionPane.INFORMATION_MESSAGE);
					login = true;
					dispose();
				} else {
					JOptionPane.showMessageDialog(LoginDialog.this, "Invalid username or password",
							"Login", JOptionPane.ERROR_MESSAGE);
					
					unField.setText("");
					pwField.setText("");
					login = false;
				}
			}
			
		});
		
		cancelBtn = new JButton("Cancel");
        cancelBtn.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e) {
                dispose();
                System.exit(EXIT_ON_CLOSE);
            }
        });
        
        newAcntBtn = new JButton("New Account");
        newAcntBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!(getUn().equals("") && !(getPw().equals("")))) {
					GUI.database.userList.put(new Account(UserType.USER, getUn()), getPw());
					JOptionPane.showMessageDialog(LoginDialog.this,  "Hi " + getUn() + "! You have successfully created an account.",
							"Login", JOptionPane.INFORMATION_MESSAGE);
					login = true;
					dispose();
				} else {
					JOptionPane.showMessageDialog(LoginDialog.this, "Make sure you create a new Username and Password!",
							"Login", JOptionPane.ERROR_MESSAGE);
					
					unField.setText("");
					pwField.setText("");
					login = false;
				}
				
			}
        	
        });
        
        
        JPanel bp = new JPanel();
        bp.add(loginBtn);
        bp.add(cancelBtn);
        bp.add(newAcntBtn);
 
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(bp, BorderLayout.PAGE_END);
 
        pack();
        setResizable(false);
        setLocationRelativeTo(parent);
    }
 
    public String getUn() {
        return unField.getText().trim();
    }
 
    public String getPw() {
        return new String(pwField.getPassword());
    }
    public boolean loginSuccess() {
        return login;
    }

}