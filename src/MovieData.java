import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileWriter;

public class MovieData {
	private static File data = new File("movieData.txt");
	
	protected static void storeMovie(Movie m) {
		try {
			FileWriter writer = new FileWriter(data, true);
			writer.write(m.forFile());
			writer.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	protected static Movie getMovieByTitle(String title) {
		try {
			Scanner reader = new Scanner(data);
			while(reader.hasNextLine()) {
				String line = reader.nextLine();
				String[] pieces = line.split(",");
				if(pieces[0].equals(title)) {
					//Index 0 is title, Index 1 is director, Index 2 is genre, Index 3 is runtime, Index 4 is type
					Movie m = new Movie(pieces[0], pieces[2], pieces[1], Integer.parseInt(pieces[3]));
					return m;
				}
			}
			reader.close();
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		Movie m = new Movie("Movie Not Found", null, null, 0);
		return m;
	}
	
	
	public static void main(String[] args) {
		Movie m = new Movie("movieTitle", "movieGenre", "movieDirector", 10);
		storeMovie(m);
		System.out.println(getMovieByTitle("movieTitle").forFile());
		System.out.println(getMovieByTitle("not Movie").forFile());

	}

}
