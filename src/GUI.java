import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GUI extends JFrame implements ActionListener{
	// ======================= Parameters
	static JPanel panel = new JPanel();
	static JMenuBar menuBar = new JMenuBar();
	static JFrame folderSelect;
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


//	private void openFolderWindow() {
//		folderSelect = new JFrame("Folder Select:");
//		JPanel p = new JPanel();
//		JList b = new JList(folderList.keySet().toArray());
//	}


//	private static void newFolderWindow() {
//		
//		
//	}
	
}