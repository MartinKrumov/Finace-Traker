package com.dao.classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.model.DBConnection;
import com.model.User;

public class UserDAO {

    private static Connection conn;

    private final static String INSERT = "INSERT INTO `users`( `username`, `password`, `email`, `first_name`, `last_name`, `profile_pic`)  VALUES(?,?,?,?,?,?)";
    private final static String SELECT_ALL = "SELECT * FROM users";
    private final static String SELECT_CHECK = "SELECT email FROM users WHERE email =?";
    private final static String SELECT_LOGIN_MAIL = "SELECT * FROM users WHERE email = ? AND password = ?";
    private final static String SELECT_LOGIN_USERNAME = "SELECT * FROM users WHERE username = ? AND password = ?";
    private final static String DELETE_USER = "DELETE FROM `users` WHERE user_id = ?";

    public static long insertUser(User user) {

        try {
            conn = DBConnection.getInstance().getConnection();
            // `username`, `password`, `email`, `first_name`, `last_name`,
            // `profile_pic`
            PreparedStatement statement = conn.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword().toString());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getFirstName());
            statement.setString(5, user.getLastName());
            statement.setString(6, "aa");
//			statement.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if ( rs.next() ) {
                return rs.getLong(1);
            }

        } catch ( SQLException e ) {
            System.out.println(e.getMessage());
            return -1;
        }
        return 0;
    }

    public static ResultSet selecttUsers() {

        try {
            conn = DBConnection.getInstance().getConnection();
            PreparedStatement prs = conn.prepareStatement(SELECT_ALL);
            return prs.executeQuery();

        } catch ( SQLException e ) {
            System.out.println("Problem with result set.");
        }
        return null;

    }

    public static boolean checkIfExists(String email) {
        boolean isExist = false;
        ResultSet set;
        try {

            conn = DBConnection.getInstance().getConnection();
            PreparedStatement prs = conn.prepareStatement(SELECT_CHECK);

            prs.setString(1, email);
            set = prs.executeQuery();
            if ( set.next() ) {
                if ( set.getString("email").equals(email) ) {
                    isExist = true;
                    System.out.println("isExist " + isExist);
                }
            }
        } catch ( SQLException e ) {

            return false;
        }
        return isExist;
    }

    public static long[] loginCheckByEmail(String email, String pass) {
        ResultSet set;
        long[] returnArray = new long[2];
        try {

            conn = DBConnection.getInstance().getConnection();
            PreparedStatement prs = conn.prepareStatement(SELECT_LOGIN_MAIL);

            prs.setString(1, email);
            prs.setString(2, pass);
            set = prs.executeQuery();
            if ( set.next() ) {
                returnArray[0] = set.getInt("user_id");
                returnArray[1] = ( long ) set.getInt("rights");
                return returnArray;
            }
        } catch ( SQLException e ) {
            return returnArray;
        }
        return returnArray;
    }

    public static long[] loginCheckByUserName(String uname, String pass) {
        ResultSet set;
        long[] returnArray = new long[2];
        try {

            conn = DBConnection.getInstance().getConnection();
            PreparedStatement prs = conn.prepareStatement(SELECT_LOGIN_USERNAME);

            prs.setString(1, uname);
            prs.setString(2, pass);
            set = prs.executeQuery();
            if ( set.next() ) {
                returnArray[0] = set.getLong(1);
                returnArray[1] = set.getInt("rights");
                return returnArray;
            }
        } catch ( SQLException e ) {
            return returnArray;
        }
        return returnArray;
    }

    public static void delUser(Integer user_id) {
        try {
            conn = DBConnection.getInstance().getConnection();
            PreparedStatement prs = conn.prepareStatement(DELETE_USER);
            prs.setInt(1, user_id);
            prs.execute();
        } catch ( SQLException e ) {
            System.out.println(" trouble with the delete ");
            return;
        }
    }
}
