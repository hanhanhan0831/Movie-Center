
public class Movie {

		//============================== Properties
		String name;
		String genre;
		String director;
		private String movieType;
		int runtime;

		//============================== Constructors
		public Movie(String title, String genre, String director, int runtime) {
			setName(name);
			setGenre(genre);
			setDirector(director);
			setRuntime(runtime);
		}

		//============================== Methods
		public String type() {
			return movieType;
		}

		//============================== Getters/Setters
		protected String getName() {
			return name;
		}

		protected void setName(String name) {
			this.name = name;
		}

		protected String getGenre() {
			return genre;
		}

		protected void setGenre(String genre) {
			this.genre = genre;
		}

		protected String getDirector() {
			return director;
		}

		protected void setDirector(String director) {
			this.director = director;
		}

		protected int getRuntime() {
			return runtime;
		}

		protected void setRuntime(int runtime) {
			this.runtime = runtime;
		}

		protected String getMovieType() {
			return movieType;
		}

		protected void setMovieType(String movieType) {
			this.movieType = movieType;
		}
}
