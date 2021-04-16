import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Vector;


import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class GUIVersion2 extends JFrame implements ActionListener{
	// ======================= Parameters
	static JPanel panel = new JPanel();
	static JMenuBar menuBar = new JMenuBar();
	static JFrame movieSelect;
	static JFrame loginWindow;
	static Account User = null;
	static JList<Movie> movieList;
	static JScrollPane movieScrollPane;
	static DefaultListModel<Movie> model = new DefaultListModel<>();

	// ======================= Constructors
	public GUIVersion2() {
		setSize(500, 500); 
		setTitle("Movie Center");
		setJMenuBar(menuBar);
		add(panel);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
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
		Movie[] arr = new Movie[allMovies.size()];
		for(int i=0;i<arr.length;i++) {
			arr[i] = allMovies.get(i);
		}
		movieList = new JList<Movie>(model);
		for(Movie m : arr) {
			model.addElement(m);
		}
		movieScrollPane = new JScrollPane(movieList);
		movieScrollPane.setVisible(true);
		panel.add(movieScrollPane, BorderLayout.CENTER);
		
		
		//--------Filtering Movies
		
		JLabel filterLabel = new JLabel("Filter:");
		JList<String> filterList = new JList<>(new String[] {"title","director","runtime","year","genre"});
		JScrollPane filters = new JScrollPane(filterList);
		filters.setPreferredSize(new Dimension(100,50));
		JPanel filterPanel = new JPanel();
		filterPanel.setPreferredSize(new Dimension(125, 275));
		JTextField filterText = new JTextField(10);
		JLabel textLabel = new JLabel("Search Term:");
		JButton apply = new JButton("Apply Filter");
		JTextArea directions = new JTextArea();
		directions.append("To filter by runtime\nand year. ");
		directions.append("Enter\ntwo comma\nseperated numbers,\n");
		directions.append("Maximum and then\nminimum");
		directions.setEditable(false);
		apply.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//Apply Filter and update Movies shown
				String filterChoice = filterList.getSelectedValue();
				ArrayList<Movie> results = new ArrayList<>();
				switch(filterChoice) {
				case "title":
					results = MovieData.getMoviesByTitle(filterText.getText());
					break;
				case "director":
					results = MovieData.getMoviesByDirector(filterText.getText());
					break;
				case "runtime":
					String[] temp = filterText.getText().split(",");
					results = MovieData.getMoviesByRuntime(Double.parseDouble(temp[0]), Double.parseDouble(temp[1]));
					break;
				case "genre":
					results = MovieData.getMoviesByGenre(filterText.getText());
					break;
				case "year":
					String[] temp2 = filterText.getText().split(",");
					results = MovieData.getMoviesByYear(Integer.parseInt(temp2[0]), Integer.parseInt(temp2[1]));
					break;
				}
				
				Movie[] array = new Movie[results.size()];
				for(int i=0;i<array.length;i++) {
					array[i] = results.get(i);
				}
				model.removeAllElements();
				for(Movie m : array) {
					model.addElement(m);
				}
			}
		});
		filterPanel.add(filterLabel, BorderLayout.NORTH);
		filterPanel.add(filters, BorderLayout.CENTER);
		filterPanel.add(textLabel, BorderLayout.SOUTH);
		filterPanel.add(filterText, BorderLayout.SOUTH);
		filterPanel.add(apply);
		filterPanel.add(directions);
		panel.add(filterPanel, BorderLayout.EAST);
		
		
		//------ Visit movie page
		JButton selectMovie = new JButton("View movie details");
		selectMovie.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				openMoviePage(movieList.getSelectedValue());
			}
		});
		panel.add(selectMovie, BorderLayout.WEST);

	}

	public static void buildMenuBar() {
		// =================== Objects
		JMenu file = new JMenu("File");
		JMenu help = new JMenu("Help");
		JMenuItem login = new JMenuItem("Login");
		JMenuItem howTo = new JMenuItem("How To...");
		JMenuItem contactMe = new JMenuItem("Contact Us");
		help.add(howTo);
		help.add(contactMe);
		file.add(login);
		menuBar.add(file);
		menuBar.add(help);

		
		howTo.setActionCommand("howTo");
		contactMe.setActionCommand("contactUs");
		login.setActionCommand("login");
		
		//Trying to figure out how to make the action listeners work bc they dont work
//		menuBar.add(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				String cmd = e.getActionCommand();
//				switch (cmd) {
//				case "login":
//					System.out.println("jauiosafhj");
//					loginWindow.setVisible(true);
//					break;
//					
//				case "contactUs":
//					try {
//						java.awt.Desktop.getDesktop().browse(new URI("https://github.com/hanhanhan0831/Movie-Center"));
//					} catch (IOException | URISyntaxException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
//					break;
//				}
//			}
//		});
	}


	private static void openLoginWindow() {
		loginWindow = new JFrame("Login or Create Account:");
		loginWindow.setSize(300, 200);
		loginWindow.setLayout(new FlowLayout());
		
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints cs = new GridBagConstraints();

		cs.fill = GridBagConstraints.HORIZONTAL;

		JLabel unLabel = new JLabel("Username: ");
		cs.gridx = 0;
		cs.gridy = 0;
		cs.gridwidth = 1;
		panel.add(unLabel, cs);

		JTextField unField = new JTextField(20);
		cs.gridx = 1;
		cs.gridy = 0;
		cs.gridwidth = 2;
		panel.add(unField, cs);

		JLabel pwLabel = new JLabel("Password: ");
		cs.gridx = 0;
		cs.gridy = 1;
		cs.gridwidth = 1;
		panel.add(pwLabel, cs);

		JPasswordField pwField = new JPasswordField(20);
		cs.gridx = 1;
		cs.gridy = 1;
		cs.gridwidth = 2;
		panel.add(pwField, cs);
		panel.setBorder(new LineBorder(Color.GRAY));

		JButton loginBtn = new JButton("Login");
		loginBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//Will check fields were filled
				String username = unField.getText();
				String password = String.valueOf(pwField.getPassword());
				//V. 1 of login system
				UserType type = UserData.login(username, password);
				if(type == null) {
					//login fails
					loginWindow.dispose();
					//Alerts the user that they couldn't log in
					//Tells user to use File -> Login
					JOptionPane.showMessageDialog(null, "Unable to login user.\nPlease use File -> Login to create a new account\nor try logging in again.");
				}else {
					GUIVersion2.User = new Account(type, username);
					loginWindow.dispose();
				}
			}
		});

		JButton cancelBtn = new JButton("Cancel");
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginWindow.dispose();
				JOptionPane.showMessageDialog(null, "Certain functionality will not be usable without login.\nPlease use File -> Login to create a new account\nor try logging in again.");
			}
		});

		JButton newAcntBtn = new JButton("New Account");
		newAcntBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Will check fields were filled
				String username = unField.getText();
				String password = String.valueOf(pwField.getPassword());
				Account a = new Account(UserType.USER, username);
				//Will then call The UserData class once implemented
				try {
					UserData.addNewAccount(a, password);
				}catch(InputMismatchException error) {
					//Handle account failing to be added
					JOptionPane.showMessageDialog(null, "Unable to create user.\nPlease use File -> Login to create a new account\nor try logging in again.");

				}
				GUIVersion2.User = new Account(UserType.USER, username);
				loginWindow.dispose();
			}
		});

		JPanel bp = new JPanel();
		bp.add(loginBtn);
		bp.add(cancelBtn);
		bp.add(newAcntBtn);
		
		loginWindow.add(panel);
		loginWindow.add(bp);
		
		
		loginWindow.setVisible(true);
	}
	
	private static void openMoviePage(Movie m) {
		//Create a new JFrame that displays the page for a single movie
		JFrame movieWindow = new JFrame(m.getName());
		JLabel movieTitle = new JLabel("Movie Title: " + m.getName());
		JLabel movieDirector = new JLabel("Directed By: " + m.getDirector());
		JLabel movieGenre = new JLabel("Genre: " + m.getGenre());
		JLabel movieRun = new JLabel("Runtime: "+m.getRuntime());
		JLabel movieRelease = new JLabel("Released in: "+m.getYearReleased());
		movieWindow.setSize(500,500);
		movieWindow.setLayout(new GridLayout(0,1));
		movieWindow.add(movieTitle);
		movieWindow.add(movieDirector);
		movieWindow.add(movieGenre);
		movieWindow.add(movieRun);
		movieWindow.add(movieRelease);
		movieWindow.setVisible(true);
	}
	
	private static void openFavoritesPage() {
		//Create a new JFrame that displays the active user's favorite movies
	}
	
	private static void openRatingsPage() {
		//Create a new JFrame that displays the active user's ratings on movies
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		switch (cmd) {
		case "login":
			System.out.println("jauiosafhj");
			loginWindow.setVisible(true);
			break;
			
		case "contactUs":
			try {
				java.awt.Desktop.getDesktop().browse(new URI("https://github.com/hanhanhan0831/Movie-Center"));
			} catch (IOException | URISyntaxException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		}
	}


}

