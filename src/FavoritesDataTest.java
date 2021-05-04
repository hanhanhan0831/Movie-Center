import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;


public class FavoritesDataTest {
	Movie m = new Movie("movieTitle", "movieGenre", "movieDirector", 2.1, 2010);
	@Test
	void testGetUserFavorites() {
		ArrayList<Movie> list = FavoritesData.getUserFavorites(new Account(UserType.USER, "username"));
		assertEquals(list.size(), 1);
	}
	
	@Test
	void testAddFavorite() {
		FavoritesData.addFavorite(new Account(UserType.USER, "username"), m);
		ArrayList<Movie> list = FavoritesData.getUserFavorites(new Account(UserType.USER, "username"));
		assertTrue(list.get(0).equals(m));
	}
	
	@Test 
	void testRemoveFavorite(){
		FavoritesData.removeFavorite(new Account(UserType.USER, "username"), m);
		ArrayList<Movie> list = FavoritesData.getUserFavorites(new Account(UserType.USER, "username"));
		assertFalse(list.contains(m));
	}
}
