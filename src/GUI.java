import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.BorderFactory;
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
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class GUI extends JFrame implements ActionListener {
	// ======================= Parameters
	static JPanel panel = new JPanel();
	static JMenuBar menuBar = new JMenuBar();
	static JFrame movieSelect;
	static JFrame loginWindow;
	static MovieCenterDB database = new MovieCenterDB("db.txt");

	// ======================= Constructors
	public GUI() {
		setSize(500, 500);
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
		TitledBorder border = new TitledBorder("Welcome to Movie Center");
	    border.setTitleJustification(TitledBorder.CENTER);
	    border.setTitlePosition(TitledBorder.TOP);
		panel.setBorder(border);

//		JList<String> movieList = new JList<String>(database.movieList.toArray(new String[database.movieList.size()]));
		String[] arr = {"Titanic", "The Terminator", "Toy Story", "Citizen Kane",
				"Amazing Grace", "Casablance", "Finding Nemo", "Parasite",
				"Selma", "Alien", "Get Out", "The Godfather",
				"Good Will Hunting", "Hamilton", "JAWS", "Moonlight",
				"Once Upon a Time in Hollywood", "Sunset Boulevard", "Toy Story 3", "Up",
				"The Wizard of Oz", "Aliens", "Knives Out", "Monty Python and the Holy Grail",
				"Schindler's List", "Spirited Away", "Black Panther", "Back to the Future"};
		JList<String> movieList = new JList<String>(arr);
		JScrollPane scrollPane = new JScrollPane(movieList);
		scrollPane.setVisible(true);
		movieList.setLayoutOrientation(JList.VERTICAL);
		panel.add(movieList);
		panel.add(scrollPane, BorderLayout.CENTER);
	}

	public static void buildMenuBar() {
		// =================== Objects
		JMenu file = new JMenu("File");
		JMenu help = new JMenu("Help");
		JMenuItem howTo = new JMenuItem("How To...");
		JMenuItem contactMe = new JMenuItem("Contact Us");
		help.add(howTo);
		help.add(contactMe);
		menuBar.add(file);
		menuBar.add(help);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		switch (cmd) {
		// From a previous project, use this to manage any actions done in JPanel i.e. movie selection
//		case "newFolder":
//			newFolderWindow();
//			break;
		}

	}

	private static void openLoginWindow() {
		loginWindow = new JFrame("Login or Create Account:");
		LoginDialog loginDlg = new LoginDialog(loginWindow);
		loginDlg.setVisible(true);
		loginWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginWindow.setSize(300, 100);
		loginWindow.setLayout(new FlowLayout());
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
					JOptionPane.showMessageDialog(LoginDialog.this,
							"Hi " + getUn() + "! You have successfully logged in.", "Login",
							JOptionPane.INFORMATION_MESSAGE);
					login = true;
					dispose();
				} else {
					JOptionPane.showMessageDialog(LoginDialog.this, "Invalid username or password", "Login",
							JOptionPane.ERROR_MESSAGE);

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
					JOptionPane.showMessageDialog(LoginDialog.this,
							"Hi " + getUn() + "! You have successfully created an account.", "Login",
							JOptionPane.INFORMATION_MESSAGE);
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

class MovieWindow{
	 //Separate class for pulling in and managing all the movie data
	
}