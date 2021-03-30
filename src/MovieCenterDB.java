import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class MovieCenterDB {

	//============================== Properties
	String dbFileName;
	ArrayList<Movie> movieList = new ArrayList<Movie>();
	HashMap<Account, String> userList = new HashMap<Account, String>();
	Scanner io = null;

	//============================== Constructors
	public MovieCenterDB(String fileName) {
		dbFileName = fileName;
		read();
	}
	
	//============================== Methods
	private void read() {
		try {
			io = new Scanner(new File (dbFileName));
			while (io.hasNextLine()) {
				// Do some sort of decoding of files here
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			try {
				io.close();
			} catch (Exception e) {}
		}
	}
	public void write() {
		try {
			io = new Scanner(new File (dbFileName));
			while (io.hasNextLine()) {
				// Do some sort of encoding of files here
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			try {
				io.close();
			} catch (Exception e) {}
		}
	}
	
	//============================== Getters/Setters
	
}
