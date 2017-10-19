import static org.junit.Assert.*;

import org.junit.Test;

import dao.classes.UserDAO;

public class TestUserFDao {

	@Test
	public void testCheckIfExists() {

		boolean bool = UserDAO.checkIfExists("mail");
		assertTrue(bool);
	}

}
