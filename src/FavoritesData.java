import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FavoritesData {

	private static File data= new File("FavoritesData.txt");
	public static void addFavorite(Account account, Movie m) {
		try {
			FileWriter writer = new FileWriter(data, true);
			removeFavorite(account, m);
			writer.write(account.getUsername()+","+m.getName()+"\n");
			writer.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
//To remove errors from Tester

	public static ArrayList<Movie> getUserFavorites(Account account) {
		ArrayList<Movie> result = null;
		try {
			Scanner reader = new Scanner(data);
			result = new ArrayList<>();
			while(reader.hasNextLine()) {
				String[] pieces = reader.nextLine().split(",");
				if(pieces[0].equals(account.getUsername())) {
					result.add(MovieData.getMovieByTitle(pieces[1]));
				}
			}
			reader.close();
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public static void removeFavorite(Account account, Movie m) {
		try {
			StringBuffer sb = new StringBuffer("");
			BufferedReader reader = new BufferedReader(new FileReader(data));
			String line;
			while((line=reader.readLine())!=null) {
				String[] pieces = line.split(",");
				if(pieces[0].equals(account.getUsername()) && pieces[1].equals(m.getName())) {

				}else {
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
	
	public static boolean favorited(Account a, Movie m) {
		for(Movie movie : getUserFavorites(a)) {
			if(movie.equals(m)) {
				return true;
			}
		}
		return false;
	}
	
	
	public static void main(String[] args) {
		
	}
}