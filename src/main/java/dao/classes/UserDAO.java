package dao.classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import model.DBConnection;
import model.User;

public class UserDAO {

	private final static String INSERT = "INSERT INTO financetracker.users (`fname`, `lname`, `email`, `pass`, `profilpic`, `date`) VALUES(?,?,?,?,?,?)";

	private final static String SELECT = "SELECT * FROM financetracker.users";
	// AND pass = ?
	private final static String SELECT_CHECK = "SELECT email FROM financetracker.users WHERE email =?";
	// private long userId;
	// private String username;
	// private String password;
	// private String email;
	// private String firstName;
	// private String lastName;

	public static long insertUser(User user,String path) {

		try {
			Connection conn = DBConnection.getInstance().getConnection();
			PreparedStatement prs = conn.prepareStatement(INSERT, 1);
			prs.setString(1, user.getFirstName());
			prs.setString(2, user.getLastName());
			prs.setString(3, user.getEmail());
			prs.setString(4, user.getpass());
			prs.setString(5, path);
			prs.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));
			prs.executeUpdate();
			ResultSet rs = prs.getGeneratedKeys();
			if (rs.next()) {
				return rs.getLong(1);
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return -1;
		}
		return 0;
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

	public static boolean checkIfExists(String email) {
		boolean returnBool = false;
		ResultSet set;
		try {

			Connection conn = DBConnection.getInstance().getConnection();
			PreparedStatement prs = conn.prepareStatement(SELECT_CHECK);

			prs.setString(1, email);
			set = prs.executeQuery();
			if (set.next()) {
				if (set.getString("email").equals(email)) {
					returnBool = true;
				}
			}
		} catch (SQLException e) {
			
			return false;
		}
		return returnBool;
	}

}
