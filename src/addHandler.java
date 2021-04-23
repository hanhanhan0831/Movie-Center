import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class addHandler {
	private static File data = new File("PendingMovies.txt");
	
	protected static void addPending(Movie m) {
		try {
			FileWriter writer = new FileWriter(data, true);
			writer.write(m.forFile());
			writer.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	protected static ArrayList<Movie> getPending(){
		ArrayList<Movie> mList = new ArrayList<>();
		try {
			Scanner reader = new Scanner(data);
			while(reader.hasNextLine()) {
				String line = reader.nextLine();
				String[] pieces = line.split(",");
				Movie m = new Movie(pieces[0], pieces[2], pieces[1], Double.parseDouble(pieces[3]), Integer.parseInt(pieces[4]));
				mList.add(m);
			}
			reader.close();
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		return mList;
	}
	
	protected static void removePending(Movie m) {
		try {
			StringBuffer sb = new StringBuffer("");
			BufferedReader reader = new BufferedReader(new FileReader(data));
			String line;
			while((line=reader.readLine())!=null) {
				String[] pieces = line.split(",");
				if(!pieces[0].equals(m.getName())||Integer.parseInt(pieces[4])!=m.getYearReleased()) {
					sb.append(line);
					sb.append("\n");
				}
			}
			reader.close();
			FileWriter writer=new FileWriter(data);
			writer.write(sb.toString());
			writer.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	public static void main(String[] args) {
	}
	
}
