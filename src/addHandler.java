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
	
	protected static void addPending(Movie m, String url) {
		try {
			FileWriter writer = new FileWriter(data, true);
			writer.write(url+",");
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
				Movie m = new Movie(pieces[1], pieces[3], pieces[2], Double.parseDouble(pieces[4]), Integer.parseInt(pieces[5]));
				mList.add(m);
			}
			reader.close();
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		return mList;
	}
	
	protected static String getUrl(Movie m) {
		String s = null;
		try {
			Scanner reader = new Scanner(data);
			while(reader.hasNextLine()) {
				String line = reader.nextLine();
				String pieces[] = line.split(",");
				if(pieces[1].equals(m.getName())) {
					reader.close();
					return pieces[0];
				}
			}
			reader.close();	
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
		return s;
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
