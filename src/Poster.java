import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Poster {

	Movie m;
	String url;
	ImageIcon poster;
	
	public Poster(Movie movie, String posterUrl) {
		setM(movie);
		setUrl(posterUrl);
		processImage();
	}

	
	public void processImage() {
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new URL(url));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		Image dimg = img.getScaledInstance(200, 300,
		        Image.SCALE_SMOOTH);
		poster = new ImageIcon(dimg);
	}
	
	public boolean matchesMovie(Movie matchedMovie) {
		return m.equals(matchedMovie);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return m.toString() + ", " + url;
	}
	
	protected Movie getM() {
		return m;
	}

	protected void setM(Movie m) {
		this.m = m;
	}

	protected String getUrl() {
		return url;
	}

	protected void setUrl(String url) {
		this.url = url;
	}
	
	
}
