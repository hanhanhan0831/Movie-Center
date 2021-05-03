import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class PosterData {

	ArrayList<Poster> posters = new ArrayList<Poster>();
	
	public PosterData() {
		readData();
	}
	private void readData() {
		Scanner fin = null;
		try {
			fin = new Scanner(new File("posterData.txt"));
			while (fin.hasNextLine()) {
				String s = fin.nextLine();
				String[] parts = s.split(", ");
				posters.add(new Poster(MovieData.getMovieByNameAndDirector(parts[0], parts[1]), parts[2]));
			}
		}  catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			try {
				fin.close();
			} catch (Exception e) {}
		}
	}
	
	public void writeData() {
		PrintWriter fout = null;
		try {
			fout = new PrintWriter(new File("posterData.txt"));
			for (Poster p : posters) {
				fout.println(p);
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			try {
				fout.close();
			} catch (Exception e) {}
		}
	}
	
	public ImageIcon getPoster(Movie m) {
		for (Poster p: posters) {
			if (p.matchesMovie(m)) return p.poster;
		} 
			BufferedImage img = null;
			try {
			    img = ImageIO.read(new File("moviePosters/samplePoster.jpg"));
			} catch (IOException e) {
			    e.printStackTrace();
			}
			Image dimg = img.getScaledInstance(200, 300,
			        Image.SCALE_SMOOTH);
			return new ImageIcon(dimg);
	}

	
}
