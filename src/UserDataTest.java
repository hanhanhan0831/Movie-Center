import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UserDataTest {

	@Test
	void testAddNewAccount() {
		Account a = new Account(UserType.USER,"testUser");
		UserData.addNewAccount(a, "testPassword");
		assertEquals(UserData.login("testUser", "testPassword"),UserType.USER);
		UserData.removeAccount(a);
	}

	@Test
	void testLogin() {
		assertEquals(UserData.login("notAUser", "notAPassword"), null);
		assertEquals(UserData.login("username", "password"), UserType.USER);
	}

	@Test
	void testRemoveAccount() {
		Account a = new Account(UserType.USER,"testUser");
		UserData.addNewAccount(a, "testPassword");
		assertEquals(UserData.login("testUser", "testPassword"),UserType.USER);
		UserData.removeAccount(a);
		assertEquals(UserData.login("testUser", "testPassword"),null);
	}

}
