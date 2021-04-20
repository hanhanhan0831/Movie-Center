import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class CommentData {
	private static File data = new File("commentData.txt");
	
	protected static void addComment(Comment c) {
		try {
			FileWriter writer = new FileWriter(data, true);
			writer.write(c.getMovie().getName()+"|delimiter|"+c.getUser()+"|delimiter|"+c.getText()+"|delimiter|"+c.getRating()+"\n");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected static ArrayList<Comment> getCommentsByMovie(Movie m){
		ArrayList<Comment> results = new ArrayList<>();
		try {
			Scanner reader = new Scanner(data);
			while(reader.hasNextLine()) {
				String[] pieces = reader.nextLine().split("\\|delimiter\\|");
				//index 0:movie, index 1:user, index 2:text, index 3:rating
				if(pieces[0].equals(m.getName())) {
					results.add(new Comment(MovieData.getMovieByTitle(pieces[0]), pieces[1], pieces[2], Double.parseDouble(pieces[3])));
				}
			}
			reader.close();
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		return results;
	}
	
	protected static ArrayList<Comment> getCommentsByUser(String user){
		ArrayList<Comment> results = new ArrayList<>();
		try {
			Scanner reader = new Scanner(data);
			while(reader.hasNextLine()) {
				String[] pieces = reader.nextLine().split("\\|delimiter\\|");
				//index 0:movie, index 1:user, index 2:text, index 3:rating
				if(pieces[1].equals(user)) {
					results.add(new Comment(MovieData.getMovieByTitle(pieces[0]), pieces[1], pieces[2], Double.parseDouble(pieces[3])));
				}
			}
			reader.close();
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		return results;
	}
	
	protected static void removeComment(Movie m, Account a) {
		try {
			StringBuffer sb = new StringBuffer("");
			BufferedReader reader = new BufferedReader(new FileReader(data));
			String line;
			while((line=reader.readLine())!=null) {
				String[] pieces = line.split("\\|delimiter\\|");
				if(!pieces[0].equals(m.getName())||!pieces[1].equals(a.getUsername())) {
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
		Movie m = MovieData.getMovieByTitle("movie");
		Comment c = new Comment(m, "username", "test comment", 9);
		Comment c2 = new Comment(m, "anotherUser", "another test", 6.8);
		Movie m2 = MovieData.getMovieByTitle("anotherMovie");
		Comment c3 = new Comment(m2, "username", "third test", 7.2);
		addComment(c);
		addComment(c2);
		addComment(c3);
		Account a = new Account(UserType.USER,"username");
		removeComment(m, a);
	}
}
