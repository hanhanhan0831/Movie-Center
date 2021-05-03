import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
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


@SuppressWarnings("serial")
public class GUIVersion2 extends JFrame implements ActionListener {
	// ======================= Parameters
	static JPanel panel = new JPanel();
	static JMenuBar menuBar = new JMenuBar();
	static JFrame movieSelect;
	static JFrame loginWindow;
	static Account User = null;
	static JList<Movie> movieList;
	static JScrollPane movieScrollPane;
	static DefaultListModel<Movie> modelMovie = new DefaultListModel<>();
	static DefaultListModel<Comment> modelComment = new DefaultListModel<>();

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
		panel.setLayout(new BorderLayout());
		TitledBorder border = new TitledBorder("Welcome to Movie Center");
	    border.setTitleJustification(TitledBorder.CENTER);
	    border.setTitlePosition(TitledBorder.TOP);
		panel.setBorder(border);
		
		//------- Display all movies to start
		
		ArrayList<Movie> allMovies = MovieData.getAllMovies();
		movieList = new JList<Movie>(modelMovie);
		for(Movie m : allMovies) {
			modelMovie.addElement(m);
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
//		filterPanel.setLayout(new BorderLayout());
		JTextField filterText = new JTextField(10);
		JLabel textLabel = new JLabel("Search Term:");
		JButton apply = new JButton("Apply Filter");
		JTextArea directions = new JTextArea();
		directions.append("To filter by runtime\nand year, ");
		directions.append("enter\ntwo comma\nseperated numbers,\n");
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
				
				modelMovie.removeAllElements();
				for(Movie m : results) {
					modelMovie.addElement(m);
				}
			}
		});
		filterPanel.add(filterLabel, BorderLayout.NORTH);
		filterPanel.add(filters, BorderLayout.NORTH);
		filterPanel.add(textLabel, BorderLayout.NORTH);
		filterPanel.add(filterText, BorderLayout.NORTH);
		filterPanel.add(apply, BorderLayout.NORTH);
		filterPanel.add(directions, BorderLayout.AFTER_LAST_LINE);
		panel.add(filterPanel, BorderLayout.EAST);
		
		
		JPanel buttonPanel = new JPanel();
		
		//------ Visit movie page
		JButton selectMovie = new JButton("View movie details");
		selectMovie.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				openMoviePage(movieList.getSelectedValue());
			}
		});
		buttonPanel.add(selectMovie);
		
		
		//------ Visit Add Movie Page
		JButton addMovie = new JButton("Add Movie");
		addMovie.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				openAddMoviePage();
			}
		});
		buttonPanel.add(addMovie);
		
		//------ View favorites
		JButton viewFavorites = new JButton("View Favorites");
		viewFavorites.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(User != null) {
					openViewFavorites(User);
				}else {
					JOptionPane.showMessageDialog(null,"Must log in to view favorites.");
				}

			}
		});
		buttonPanel.add(viewFavorites);
		
		panel.add(buttonPanel, BorderLayout.SOUTH);

	}
	
	
	public static void openViewFavorites(Account a) {
		JFrame faveFrame = new JFrame("Favorites Window");
		faveFrame.setSize(150, 200);
		JPanel favePanel = new JPanel();
		favePanel.setLayout(new BorderLayout());
		ArrayList<Movie> faves = FavoritesData.getUserFavorites(a);
		DefaultListModel<Movie> faveModel = new DefaultListModel<>();
		JList<Movie> faveList = new JList<>(faveModel);
		for(Movie m:faves) {
			faveModel.addElement(m);
		}
		JScrollPane favePane = new JScrollPane(faveList);
		favePane.setVisible(true);
		favePanel.add(favePane, BorderLayout.CENTER);
		favePanel.setVisible(true);
		
		JButton viewButton = new JButton("View Movie Details");
		viewButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				openMoviePage(faveList.getSelectedValue());
			}
		});
		favePanel.add(viewButton, BorderLayout.SOUTH);
		
		faveFrame.add(favePanel);
		faveFrame.setVisible(true);
	}
	
	

	public static void buildMenuBar() {
		// =================== Objects
		JMenu file = new JMenu("File");
		JMenu help = new JMenu("Help");
		JMenuItem login = new JMenuItem("Login");
		login.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				loginWindow.setVisible(true);
			}
		});
		JMenuItem howTo = new JMenuItem("How To...");
		JMenuItem contactMe = new JMenuItem("Contact Us");
		contactMe.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					java.awt.Desktop.getDesktop().browse(new URI("https://github.com/hanhanhan0831/Movie-Center"));
				} catch (IOException | URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		help.add(howTo);
		help.add(contactMe);
		file.add(login);
		menuBar.add(file);
		menuBar.add(help);

		
//		howTo.setActionCommand("howTo");
//		contactMe.setActionCommand("contactUs");
//		login.setActionCommand("login");
//		

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
				//Will then call The UserData class once implemented
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
	
	private static void openAddMoviePage() {
		if(User==null) {
			return;
		}
		JFrame addWindow = new JFrame("Add Movie");
		if(User.getType().equals(UserType.USER)) {
			addWindow.setSize(500,300);
			addWindow.setLayout(new GridLayout(0,1));
			JPanel userAddPanel = new JPanel();
			userAddPanel.setVisible(true);
			userAddPanel.setLayout(new GridLayout(0,2));
			JLabel nameLabel = new JLabel("Movie Name: ");
			JTextField movieName = new JTextField(30);
			userAddPanel.add(nameLabel);
			userAddPanel.add(movieName);
			JLabel genreLabel = new JLabel("Movie Genre: ");
			JTextField movieGenre = new JTextField(30);
			userAddPanel.add(genreLabel);
			userAddPanel.add(movieGenre);
			JLabel directorLabel = new JLabel("Movie Director: ");
			JTextField movieDirector = new JTextField(30);
			userAddPanel.add(directorLabel);
			userAddPanel.add(movieDirector);
			JLabel runtimeLabel = new JLabel("Movie Runtime: ");
			JTextField movieRuntime = new JTextField(30);
			userAddPanel.add(runtimeLabel);
			userAddPanel.add(movieRuntime);
			JLabel yearLabel = new JLabel("Movie Year: ");
			JTextField movieYear = new JTextField(30);
			userAddPanel.add(yearLabel);
			userAddPanel.add(movieYear);
			JLabel posterLabel = new JLabel("PosterUrl: ");
			JTextField posterUrl = new JTextField(50);
			userAddPanel.add(posterLabel);
			userAddPanel.add(posterUrl);
			
			addWindow.add(userAddPanel);
			
			JButton submitMovie = new JButton("Submit for approval");
			submitMovie.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					Movie m = new Movie(movieName.getText(), movieGenre.getText(), movieDirector.getText(), Double.parseDouble(movieRuntime.getText()), Integer.parseInt(movieYear.getText()));
					addHandler.addPending(m, posterUrl.getText());
					addWindow.dispose();
				}
			});
			
			addWindow.add(submitMovie);
			
			addWindow.setVisible(true);
		}else {
			addWindow.setSize(500,500);
			addWindow.setLayout(new GridLayout(0,2));
			
			JPanel pendingPanel = new JPanel();
			ArrayList<Movie> pending = addHandler.getPending();
			DefaultListModel<Movie> pendingModel = new DefaultListModel<>();
			JList<Movie> pendingList = new JList<>(pendingModel);
			pendingModel.clear();
			for(Movie m : pending) {
				pendingModel.addElement(m);
			}
			JScrollPane pendingPane = new JScrollPane(pendingList);
			pendingPane.setSize(200, 200);
			pendingPane.setVisible(true);
			pendingPanel.add(pendingPane);
			addWindow.add(pendingPanel);
			
			
			JPanel buttons = new JPanel();
			buttons.setLayout(new GridLayout(0,1));
			JButton checkMovie = new JButton("Review Submission");
			checkMovie.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					Movie movie = pendingList.getSelectedValue();
					JFrame reviewWindow = new JFrame("review movie info");
					reviewWindow.setSize(300,200);
					reviewWindow.setLayout(new GridLayout(0,1));
					JLabel name = new JLabel("Name:"+movie.getName());
					JLabel director = new JLabel("Director:"+movie.getDirector());
					JLabel genre = new JLabel("Genre:"+movie.getGenre());
					JLabel runtime = new JLabel("Runtime:"+movie.getRuntime());
					JLabel year = new JLabel("Year:"+movie.getYearReleased());
					JLabel url = new JLabel("Poster");
					url.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
					url.addMouseListener(new MouseAdapter() {
						 
					    @Override
					    public void mouseClicked(MouseEvent e) {
					        try {
					            
					            Desktop.getDesktop().browse(new URI(addHandler.getUrl(movie)));
					             
					        } catch (IOException | URISyntaxException e1) {
					            e1.printStackTrace();
					        }
					    }
					    @Override
					    public void mouseEntered(MouseEvent e) {
					    }
					    @Override
					    public void mouseExited(MouseEvent e) {
					    }
					});
					
					
					
					addHandler.getUrl(movie);
					reviewWindow.add(name);
					reviewWindow.add(director);
					reviewWindow.add(genre);
					reviewWindow.add(runtime);
					reviewWindow.add(year);
					reviewWindow.add(url);
					reviewWindow.setVisible(true);
				}
			});
			
			JButton addMovie = new JButton("Accept Submission");
			addMovie.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					Movie movie = pendingList.getSelectedValue();
					MovieData.storeMovie(movie);
				}
			});
			
			buttons.add(checkMovie);
			JTextArea text = new JTextArea("It is admin responsibility to follow submitted url and add jpg to proper folder");
			text.setEditable(false);
			text.setLineWrap(true);
			buttons.add(text);
			
			buttons.add(addMovie);
			
			
			addWindow.add(buttons);
			
			
			addWindow.setVisible(true);
		}
		
		
	}
	
	
	
	
	
	private static void openMoviePage(Movie m) {
		//PosterData posters = new PosterData();
		
		//Create a new JFrame that displays the page for a single movie
		JFrame movieWindow = new JFrame(m.getName());
		JLabel movieTitle = new JLabel("Movie Title: " + m.getName());
		JLabel movieDirector = new JLabel("Directed By: " + m.getDirector());
		JLabel movieGenre = new JLabel("Genre: " + m.getGenre());
		JLabel movieRun = new JLabel("Runtime: "+m.getRuntime());
		JLabel movieRelease = new JLabel("Released in: "+m.getYearReleased());
		movieWindow.setSize(500,500);
		movieWindow.setLayout(new BorderLayout());
		JPanel movieInfo = new JPanel(new GridLayout(0,1));
		movieInfo.add(movieTitle);
		movieInfo.add(movieDirector);
		movieInfo.add(movieGenre);
		movieInfo.add(movieRun);
		movieInfo.add(movieRelease);
		
		movieWindow.add(movieInfo, BorderLayout.NORTH);
		
		JPanel commentPanel = new JPanel(new GridLayout(0,1));
		ArrayList<Comment> comments = CommentData.getCommentsByMovie(m);
		JList<Comment> commentList = new JList<>(modelComment);
		modelComment.clear();
		for(Comment c : comments) {
			modelComment.addElement(c);
		}
		JScrollPane commentPane = new JScrollPane(commentList);
		commentPane.setVisible(true);
		commentPanel.add(commentPane);
		JButton selectComment = new JButton("Read Comment");
		JButton addComment = new JButton("Add Comment");
		JTextArea commentDisplay = new JTextArea("**Added comments will not appear unless page is refreshed**");
		commentDisplay.setLineWrap(true);
		commentDisplay.setWrapStyleWord(true);
		commentDisplay.setEditable(false);
		selectComment.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				Comment c = commentList.getSelectedValue();
				commentDisplay.setText(c.getText());
			}
		});
		addComment.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(User==null) {
					commentDisplay.setText("Cannot add comment, login and try again");
				}else {
					JFrame getCommentFromUser = new JFrame("Create Comment");
					getCommentFromUser.setSize(new Dimension(400, 400));
					JPanel addCommentPanel = new JPanel();
					JTextArea commentReader = new JTextArea("Type comment text here");
					commentReader.setPreferredSize(new Dimension(200, 50));
					commentReader.setLineWrap(true);
					commentReader.setWrapStyleWord(true);
					JTextField ratingReader = new JTextField("Give a number between 0 to 10");
					JButton submitComment = new JButton("Submit Comment");
					addCommentPanel.add(commentReader);
					addCommentPanel.add(ratingReader);
					addCommentPanel.add(submitComment);
					getCommentFromUser.add(addCommentPanel);
					getCommentFromUser.setVisible(true);
					submitComment.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							try {
								Comment c = new Comment(m, User.getUsername(), commentReader.getText(), Double.parseDouble(ratingReader.getText()));
								CommentData.addComment(c);
								getCommentFromUser.dispose();
							}catch(NumberFormatException error) {
								ratingReader.setText("Invalid rating format");
							}
						}
					});
				}
			}
		});
//		commentPanel.add(selectComment);
//		commentPanel.add(addComment);
		commentPanel.add(commentDisplay);
		
		JPanel actionPanel = new JPanel(new GridLayout(1, 0));
		
		actionPanel.add(addComment);
		actionPanel.add(selectComment);
		
		Poster curr = new Poster(m);
		JLabel poster = new JLabel(curr.poster);
		movieWindow.add(poster, BorderLayout.WEST);
//		BufferedImage img = null;
//		try {
//		    img = ImageIO.read(new File("moviePosters/samplePoster.jpg"));
//		} catch (IOException e) {
//		    e.printStackTrace();
//		}
//		Image dimg = img.getScaledInstance(200, 300,
//		        Image.SCALE_SMOOTH);
//		ImageIcon imageIcon = new ImageIcon(dimg);
//		poster.setIcon(imageIcon);
//		movieWindow.add(new JLabel(new ImageIcon("moviePosters/samplePoster.jpg").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)).getIcon(), BorderLayout.WEST);
		
		movieWindow.add(commentPanel, BorderLayout.EAST);
		movieWindow.add(actionPanel, BorderLayout.SOUTH);
		
		//This is the favorite button Section
		JButton favoriteButton = new JButton("Favorite Movie");
		favoriteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(User != null) {
					FavoritesData.addFavorite(User, m);
				}else {
					JOptionPane.showMessageDialog(null, "Cannot add favorite, not logged in");
				}
			}
		});
		JButton removeFavorite = new JButton("Unfavorite Movie");
		removeFavorite.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(User != null) {
					FavoritesData.removeFavorite(User, m);
				}else {
					JOptionPane.showMessageDialog(null, "Cannot add favorite, not logged in");
				}
			}
		});
		if(User != null) {
			if(FavoritesData.favorited(User, m)) {
//				movieInfo.add(removeFavorite);
				actionPanel.add(removeFavorite);
			}else {
//				movieInfo.add(favoriteButton);
				actionPanel.add(favoriteButton);
			}
		}
		
	
		
		movieWindow.setVisible(true);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}


}
