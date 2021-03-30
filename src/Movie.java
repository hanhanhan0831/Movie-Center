
public class Movie {

		//============================== Properties
		private String name;
		private String genre;
		private String director;
		private String movieType;
		private int runtime;

		//============================== Constructors
		protected Movie(String title, String genre, String director, int runtime) {
			setName(title);
			setGenre(genre);
			setDirector(director);
			setRuntime(runtime);
		}

		//============================== Methods
		public String type() {
			return movieType;
		}

		//============================== Getters/Setters
		public String getName() {
			return name;
		}

		protected void setName(String name) {
			this.name = name;
		}

		public String getGenre() {
			return genre;
		}

		protected void setGenre(String genre) {
			this.genre = genre;
		}

		public String getDirector() {
			return director;
		}

		protected void setDirector(String director) {
			this.director = director;
		}

		public int getRuntime() {
			return runtime;
		}

		protected void setRuntime(int runtime) {
			this.runtime = runtime;
		}

		public String getMovieType() {
			return movieType;
		}

		protected void setMovieType(String movieType) {
			this.movieType = movieType;
		}
		
		
		protected String forFile() {
			return this.name+","+this.director+","+this.genre+","+this.runtime+","+this.movieType+"\n";
		}
}