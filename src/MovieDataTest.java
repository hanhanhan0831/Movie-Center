import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class MovieDataTest {

	@Test
	void testStoreMovie() {
		Movie m = new Movie("testMovie","testGenre","testDirector",1.3,2020);
		MovieData.storeMovie(m);
		Movie m2 = MovieData.getMovieByTitle("testMovie");
		assertTrue(m.equals(m2));
		MovieData.removeMovieTitleYear("testMovie", 2020);
	}

	@Test
	void testStoreMovies() {
		Movie[] m = {new Movie("testMovie","testGenre","testDirector",1.3,2020), new Movie("testMovie2","testGenre2","testDirector2",1.4,2021)};
		MovieData.storeMovies(m);
		assertTrue(MovieData.getMovieByTitle("testMovie").equals(m[0]));
		assertTrue(MovieData.getMovieByTitle("testMovie2").equals(m[1]));
		MovieData.removeMovieTitleYear("testMovie", 2020);
		MovieData.removeMovieTitleYear("testMovie2", 2021);
	}

	@Test
	void testGetAllMovies() {
		ArrayList<Movie> m = MovieData.getAllMovies();
		assertEquals(m.size(), 3);
	}

	@Test
	void testGetMovieByTitle() {
		Movie m = MovieData.getMovieByTitle("movieTitle");
		Movie m2 = new Movie("movieTitle","movieGenre","movieDirector",2.3,1993);
		assertTrue(m.equals(m2));
	}

	@Test
	void testGetMoviesByTitleString() {
		ArrayList<Movie> m = MovieData.getMoviesByTitle("Movie");
		assertEquals(m.size(),1);
	}

	@Test
	void testGetMoviesByTitleStringMovieArray() {
		ArrayList<Movie> m = MovieData.getMoviesByTitle("Movie");
		ArrayList<Movie> filteredAgain = MovieData.getMoviesByTitle("a", m);
		assertEquals(filteredAgain.size(),1);
		
	}

	@Test
	void testGetMoviesByDirectorString() {
		ArrayList<Movie> m = MovieData.getMoviesByDirector("director");
		assertEquals(m.size(),1);
	}

	@Test
	void testGetMoviesByDirectorStringMovieArray() {
		ArrayList<Movie> m = MovieData.getMoviesByTitle("Movie");
		ArrayList<Movie> filteredAgain = MovieData.getMoviesByDirector("anotherDirector", m);
		assertEquals(filteredAgain.size(),1);
	}

	@Test
	void testGetMoviesByGenreString() {
		ArrayList<Movie> m = MovieData.getMoviesByGenre("genre");
		assertEquals(m.size(),1);
	}

	@Test
	void testGetMoviesByGenreStringMovieArray() {
		ArrayList<Movie> m = MovieData.getMoviesByTitle("Movie");
		ArrayList<Movie> filteredAgain = MovieData.getMoviesByGenre("anotherGenre", m);
		assertEquals(filteredAgain.size(),1);
	}

	@Test
	void testGetMoviesByRuntimeDoubleDouble() {
		ArrayList<Movie> m = MovieData.getMoviesByRuntime(2.3,1.7);
		assertEquals(m.size(),2);
	}

	@Test
	void testGetMoviesByRuntimeDoubleDoubleMovieArray() {
		ArrayList<Movie> m = MovieData.getMoviesByTitle("Movie");
		ArrayList<Movie> filteredAgain = MovieData.getMoviesByRuntime(1.7,1.4, m);
		assertEquals(filteredAgain.size(),1);
	}

	@Test
	void testGetMoviesByYearDoubleDouble() {
		ArrayList<Movie> m = MovieData.getMoviesByYear(2000,1995);
		assertEquals(m.size(),1);
	}

	@Test
	void testGetMoviesByYearDoubleDoubleMovieArray() {
		ArrayList<Movie> m = MovieData.getMoviesByTitle("Movie");
		ArrayList<Movie> filteredAgain = MovieData.getMoviesByYear(2000,1995, m);
		assertEquals(filteredAgain.size(),0);
	}

	@Test
	void testRemoveMovieTitleYear() {
		Movie m = new Movie("testMovie","testGenre","testDirector",1.3,2020);
		MovieData.storeMovie(m);
		Movie m2 = MovieData.getMovieByTitle("testMovie");
		assertTrue(m.equals(m2));
		MovieData.removeMovieTitleYear("testMovie", 2020);
		m2 = MovieData.getMovieByTitle("testMovie");
		assertTrue(m2.getName().equals("Movie Not Found"));
	}
	
}
