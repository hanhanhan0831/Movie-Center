
public class Comment {

	private Movie movie;
	private String user;
	private String text;
	private double rating;
	
	public Comment(Movie m, String a, String comment, double rating) {
		this.setMovie(m);
		this.setUser(a);
		this.setText(comment);
		this.setRating(rating);
	}
	
	
	public Movie getMovie() {
		return movie;
	}
	private void setMovie(Movie movie) {
		this.movie = movie;
	}
	public String getUser() {
		return user;
	}
	private void setUser(String user) {
		this.user = user;
	}
	public String getText() {
		return text;
	}
	private void setText(String text) {
		this.text = text;
	}
	public double getRating() {
		return rating;
	}
	private void setRating(double rating) {
		if(rating<=10 && rating >=0) this.rating = rating;
		else throw new IllegalArgumentException("Rating must be between 0 and 10");
		
	}
}
