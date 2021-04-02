import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class GUIVersion2 extends JFrame implements ActionListener {
	// ======================= Parameters
	static JPanel panel = new JPanel();
	static JMenuBar menuBar = new JMenuBar();
	static JFrame movieSelect;
	static JFrame loginWindow;
	static Account User = null;

	// ======================= Constructors
	public GUIVersion2() {
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
		new GUIVersion2();
		openLoginWindow();
	}

	public static void buildPanel() {
		TitledBorder border = new TitledBorder("Welcome to Movie Center");
	    border.setTitleJustification(TitledBorder.CENTER);
	    border.setTitlePosition(TitledBorder.TOP);
		panel.setBorder(border);
		
		//------- Display all movies to start
		
		ArrayList<Movie> allMovies = MovieData.getAllMovies();
		String[] arr = new String[allMovies.size()];
		for(int i=0;i<arr.length;i++) {
			arr[i] = allMovies.get(i).toString();
		}
		JList<String> movieList = new JList<>(arr);
		JScrollPane scrollPane = new JScrollPane(movieList);
		scrollPane.setVisible(true);
		movieList.setLayoutOrientation(JList.VERTICAL);
		//panel.add(movieList);
		panel.add(scrollPane, BorderLayout.CENTER);
		
		
		//--------Filtering Movies
		
		JLabel filterLabel = new JLabel("Filter:");
		JList<String> filterList = new JList<>(new String[] {"title","director","runtime","year","genre"});
		JScrollPane filters = new JScrollPane(filterList);
		filters.setPreferredSize(new Dimension(100,50));
		JPanel filterPanel = new JPanel();
		filterPanel.setPreferredSize(new Dimension(125, 250));
		JTextField filterText = new JTextField(10);
		JLabel textLabel = new JLabel("Search Term:");
		JButton apply = new JButton("Apply Filter");
		apply.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//Apply Filter and update Movies shown
			}
		});
		
		filterPanel.add(filterLabel, BorderLayout.NORTH);
		filterPanel.add(filters, BorderLayout.CENTER);
		filterPanel.add(textLabel, BorderLayout.SOUTH);
		filterPanel.add(filterText, BorderLayout.SOUTH);
		filterPanel.add(apply);
		
		panel.add(filterPanel, BorderLayout.EAST);

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
	
	private static void openMoviePage(Movie m) {
		//Create a new JFrame that displays the page for a single movie
	}
	
	private static void openFavoritesPage() {
		//Create a new JFrame that displays the active user's favorite movies
	}
	
	private static void openRatingsPage() {
		//Create a new JFrame that displays the active user's ratings on movies
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
				//Will check fields were filled
				//Will then call The UserData class once implemented
			}
		});

		cancelBtn = new JButton("Cancel");
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parent.dispose();
			}
		});

		newAcntBtn = new JButton("New Account");
		newAcntBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Will check fields were filled
				//Will then call The UserData class once implemented
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

}