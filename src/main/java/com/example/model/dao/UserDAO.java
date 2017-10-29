package com.example.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Set;

import com.example.model.pojo.User;
import com.example.model.pojo.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDAO {
    @Autowired
    DBConnection connection;

    @Autowired
    WalletDAO walletDAO;

    private static Connection conn;
    private final static String INSERT = "INSERT INTO `users`( `username`, `password`, `email`, `first_name`, `last_name`, `profile_pic`)  VALUES(?,SHA2(?,256),?,?,?,?)";
    private final static String SELECT_ALL = "SELECT * FROM users";
    private final static String SELECT_CHECK = "SELECT email FROM users WHERE email =?";
    private final static String SELECT_LOGIN_MAIL = "SELECT * FROM users WHERE email = ? AND password = SHA2(?,256)";
    private final static String SELECT_LOGIN_USERNAME = "SELECT * FROM users WHERE username = ? AND password = SHA2(?,256)";
    private final static String DELETE_USER = "DELETE FROM `users` WHERE user_id = ?";

    public int insertUser(User user) {

        try {
            conn = connection.getConnection();
            if ( conn == null ) {
                System.out.println("nullll ");
            }
            PreparedStatement statement = conn.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getFirstName());
            statement.setString(5, user.getLastName());
            statement.setString(6, "aa");
//			statement.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if ( rs.next() ) {
                return rs.getInt(1);
            }

        } catch ( SQLException e ) {
            System.out.println(e.getMessage());
            return -1;
        }
        return 0;
    }

    public ResultSet selecttUsers() {

        try {
            conn = connection.getConnection();
            PreparedStatement prs = conn.prepareStatement(SELECT_ALL);
            return prs.executeQuery();

        } catch ( SQLException e ) {
            System.out.println("Problem with result set.");
        }
        return null;

    }

    public  boolean checkIfExists(String email) {
        boolean isExist = false;
        ResultSet set;
        try {

            conn = connection.getConnection();
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

    public User login(String email, String username, String pass) {
        ResultSet set;
        String select = "";
        String login = "";
        if ( email != null && !email.isEmpty() ) {
            select = SELECT_LOGIN_MAIL;
            login = email;
        } else if ( username != null && !username.isEmpty() ) {
            select = SELECT_LOGIN_USERNAME;
            login = username;
        }
        try {
            conn = connection.getConnection();

            PreparedStatement prs = conn.prepareStatement(select);
            prs.setString(1, login);
            prs.setString(2, pass);
            set = prs.executeQuery();
            if ( set.next() ) {
                User user = makeUser(set);
                try {
                    Set<Wallet> walletss= walletDAO.selectUserWallets(user.getUserId());
                    if(!walletss.isEmpty()){
                        user.wallets.addAll(walletss);
                    }
                }catch(Exception e){
                    System.out.println(e.getMessage());
                }

                return user;
            }
        } catch ( SQLException e ) {
            System.out.println(e.getMessage());
            return null;
        }
        return null;
    }

    private  User makeUser(ResultSet set) throws SQLException {

        int user_id = set.getInt("user_id");
        int rights = set.getInt("rights");
        String username = set.getString("username");
        String email = set.getString("email");
        String firstname = set.getString("first_name");
        String lastName = set.getString("last_name");
        int blocked = set.getInt("blocked");
        String profilePic = set.getString("profile_pic");
        User user = new User(user_id, username, email, firstname, lastName, profilePic, blocked, rights, LocalDateTime.now());
        return user;
    }


    public  boolean delUser(Integer user_id) {
        boolean returnbool = false;
        try {
            conn = connection.getConnection();
            PreparedStatement prs = conn.prepareStatement(DELETE_USER);
            prs.setInt(1, user_id);
            returnbool = prs.execute();
        } catch ( SQLException e ) {
            System.out.println(e.getMessage());
            System.out.println(" trouble with the delete ");
            return false;
        }
        return returnbool;
    }
}
