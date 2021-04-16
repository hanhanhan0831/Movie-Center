import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;


public class FavoritesDataTest {
	Movie m = new Movie("movieTitle", "movieGenre", "movieDirector", 2.3, 1993);
	@Test
	void testGetUserFavorites() {
		ArrayList<Movie> list = FavoritesData.getUserFavorites(new Account(UserType.USER, "username"));
		assertEquals(list.size(), 3);
	}
	
	@Test
	void testAddFavorite() {
		FavoritesData.addFavorite(new Account(UserType.USER, "username"), m);
		ArrayList<Movie> list = FavoritesData.getUserFavorites(new Account(UserType.USER, "username"));
		assertTrue(list.contains(m));
	}
	
	@Test 
	void testRemoveFavorite(){
		FavoritesData.removeFavorite(new Account(UserType.USER, "username"), m);
		ArrayList<Movie> list = FavoritesData.getUserFavorites(new Account(UserType.USER, "username"));
		assertFalse(list.contains(m));
	}
}
