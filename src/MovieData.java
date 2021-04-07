import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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
	
	
	protected static void storeMovies(Movie[] movies) {
		for(Movie m : movies) {
			storeMovie(m);
		}
	}
	
	
	protected static ArrayList<Movie>getAllMovies(){
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
	
	protected static Movie getMovieByTitle(String title) {
		try {
			Scanner reader = new Scanner(data);
			while(reader.hasNextLine()) {
				String line = reader.nextLine();
				String[] pieces = line.split(",");
				if(pieces[0].equals(title)) {
					//Index 0 is title, Index 1 is director, Index 2 is genre, Index 3 is runtime, Index 4 is year
					Movie m = new Movie(pieces[0], pieces[2], pieces[1], Double.parseDouble(pieces[3]), Integer.parseInt(pieces[4]));
					reader.close();
					return m;
				}
			}
			reader.close();
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		Movie m = new Movie("Movie Not Found", null, null, 0,0000);
		return m;
	}
	
	
	
	protected static ArrayList<Movie> getMoviesByTitle(String search) {
		ArrayList<Movie> mList = new ArrayList<>();
		try {
			Scanner reader = new Scanner(data);
			while(reader.hasNextLine()) {
				String line = reader.nextLine();
				String[] pieces = line.split(",");
				if(pieces[0].contains(search)) {
					//Index 0 is title, Index 1 is director, Index 2 is genre, Index 3 is runtime, Index 4 is year
					Movie m = new Movie(pieces[0], pieces[2], pieces[1], Double.parseDouble(pieces[3]), Integer.parseInt(pieces[4]));
					mList.add(m);
				}
			}
			reader.close();
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		return mList;
	}
	
	protected static ArrayList<Movie> getMoviesByTitle(String search, ArrayList<Movie> movies){
		ArrayList<Movie> mList = new ArrayList<>();
		for(Movie m : movies) {
			if(m.getName().contains(search)) {
				mList.add(m);
			}
		}
		return mList;
	}
	
	
	protected static ArrayList<Movie> getMoviesByDirector(String director) {
		ArrayList<Movie> mList = new ArrayList<>();
		try {
			Scanner reader = new Scanner(data);
			while(reader.hasNextLine()) {
				String line = reader.nextLine();
				String[] pieces = line.split(",");
				if(director.equals(pieces[1])) {
					//Index 0 is title, Index 1 is director, Index 2 is genre, Index 3 is runtime, Index 4 is year
					Movie m = new Movie(pieces[0], pieces[2], pieces[1], Double.parseDouble(pieces[3]), Integer.parseInt(pieces[4]));
					mList.add(m);
				}
			}
			reader.close();
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		return mList;
	}
	
	
	protected static ArrayList<Movie> getMoviesByDirector(String director, ArrayList<Movie> movies){
		ArrayList<Movie> mList = new ArrayList<>();
		for(Movie m : movies) {
			if(m.getDirector().equals(director)) {
				mList.add(m);
			}
		}
		return mList;
	}
	
	
	protected static ArrayList<Movie> getMoviesByGenre(String genre) {
		ArrayList<Movie> mList = new ArrayList<>();
		try {
			Scanner reader = new Scanner(data);
			while(reader.hasNextLine()) {
				String line = reader.nextLine();
				String[] pieces = line.split(",");
				if(genre.equals(pieces[2])) {
					//Index 0 is title, Index 1 is director, Index 2 is genre, Index 3 is runtime, Index 4 is year
					Movie m = new Movie(pieces[0], pieces[2], pieces[1], Double.parseDouble(pieces[3]), Integer.parseInt(pieces[4]));
					mList.add(m);
				}
			}
			reader.close();
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		return mList;
	}
	
	
	
	protected static ArrayList<Movie> getMoviesByGenre(String genre, ArrayList<Movie> movies){
		ArrayList<Movie> mList = new ArrayList<>();
		for(Movie m : movies) {
			if(m.getGenre().equals(genre)) {
				mList.add(m);
			}
		}
		return mList;
	}
	
	
	
	
	protected static ArrayList<Movie> getMoviesByRuntime(double max, double min) {
		ArrayList<Movie> mList = new ArrayList<>();
		try {
			Scanner reader = new Scanner(data);
			while(reader.hasNextLine()) {
				String line = reader.nextLine();
				String[] pieces = line.split(",");
				double run = Double.parseDouble(pieces[3]);
				if(run<=max && run>=min) {
					//Index 0 is title, Index 1 is director, Index 2 is genre, Index 3 is runtime, Index 4 is year
					Movie m = new Movie(pieces[0], pieces[2], pieces[1], Double.parseDouble(pieces[3]), Integer.parseInt(pieces[4]));
					mList.add(m);
				}
			}
			reader.close();
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		return mList;
	}
	
	
	
	protected static ArrayList<Movie> getMoviesByRuntime(double max, double min, ArrayList<Movie> movies){
		ArrayList<Movie> mList = new ArrayList<>();
		for(Movie m : movies) {
			if(m.getRuntime()<=max && m.getRuntime()>=min) {
				mList.add(m);
			}
		}
		return mList;
	}
	
	
	
	protected static ArrayList<Movie> getMoviesByYear(int max, int min) {
		ArrayList<Movie> mList = new ArrayList<>();
		try {
			Scanner reader = new Scanner(data);
			while(reader.hasNextLine()) {
				String line = reader.nextLine();
				String[] pieces = line.split(",");
				int year = Integer.parseInt(pieces[4]);
				if(year>=min&&year<=max) {
					//Index 0 is title, Index 1 is director, Index 2 is genre, Index 3 is runtime, Index 4 is year
					Movie m = new Movie(pieces[0], pieces[2], pieces[1], Double.parseDouble(pieces[3]), Integer.parseInt(pieces[4]));
					mList.add(m);
				}
			}
			reader.close();
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		return mList;
	}
	
	
	protected static ArrayList<Movie> getMoviesByYear(int max, int min, ArrayList<Movie> movies){
		ArrayList<Movie> mList = new ArrayList<>();
		for(Movie m : movies) {
			if(m.getYearReleased()<=max&&m.getYearReleased()>=min) {
				mList.add(m);
			}
		}
		return mList;
	}
	
	protected static void removeMovieTitleYear(String title, int year) {
		try {
			StringBuffer sb = new StringBuffer("");
			BufferedReader reader = new BufferedReader(new FileReader(data));
			String line;
			while((line=reader.readLine())!=null) {
				String[] pieces = line.split(",");
				if(!pieces[0].equals(title)||Integer.parseInt(pieces[4])!=year) {
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
		System.out.println(getMovieByTitle("movieTitle").forFile());
		System.out.println(getMovieByTitle("not Movie").forFile());
		removeMovieTitleYear("string", 1);
	}

}
