
public class Movie {

		//============================== Properties
		private String name;
		private String genre;
		private String director;
		private int yearReleased;
		private double runtime;

		//============================== Constructors
		protected Movie(String title, String genre, String director, double runtime, int year) {
			setName(title);
			setGenre(genre);
			setDirector(director);
			setRuntime(runtime);
			setYearReleased(year);
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

		public double getRuntime() {
			return runtime;
		}

		protected void setRuntime(double runtime) {
			this.runtime = runtime;
		}

		public int getYearReleased() {
			return yearReleased;
		}

		protected void setYearReleased(int yearReleased) {
			this.yearReleased = yearReleased;
		}
		
		protected String forFile() {
			return this.name+","+this.director+","+this.genre+","+this.runtime+","+this.yearReleased+"\n";
		}
		
		@Override
		public String toString() {
		return "" + getName() + ", " + getDirector();
		}
		
		public boolean equals(Movie m) {
			return this.getName().equals(m.getName()) && this.getDirector().equals(m.getDirector());
		}
}
