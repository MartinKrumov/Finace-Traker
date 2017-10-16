package dao.classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.DBConnection;
import model.User;

public class UserDAO {

	private final static String INSERT = "INSERT INTO userslogin.ulog VALUES(null,?,?,?,?)";

	private final static String SELECT = "SELECT * FROM userslogin.ulog";
	// private long userId;
	// private String username;
	// private String password;
	// private String email;
	// private String firstName;
	// private String lastName;

	public static boolean insertUser(User user) {

		try {
			Connection conn = DBConnection.getInstance().getConnection();
			PreparedStatement prs = conn.prepareStatement(INSERT);
			prs.setString(1, user.getFirstName());
			prs.setString(2, user.getLastName());
			prs.setString(3, user.getUsername());
			prs.setString(4, user.getEmail());
			// prs.setString(5, user.getPassword());
			if (prs.execute()) {
				return true;
			}

		} catch (SQLException e) {

			return false;
		}

		return true;
	}

	public static ResultSet selecttUser() {

		try {
			Connection conn = DBConnection.getInstance().getConnection();
			PreparedStatement prs = conn.prepareStatement(SELECT);
			// prs.setString(1, user.getFirstName());
			// prs.setString(2, user.getLastName());
			// prs.setString(3, user.getUsername());
			// prs.setString(4, user.getEmail());
			// prs.setString(5, user.getPassword());
			return prs.executeQuery();

		} catch (SQLException e) {

		}
		return null;

	}
}
