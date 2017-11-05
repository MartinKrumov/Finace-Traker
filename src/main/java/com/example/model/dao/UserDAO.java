package com.example.model.dao;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;
import java.util.Date;
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
    private final static String INSERT = "INSERT INTO `users`( `username`, `password`, `email`, `first_name`, `last_name`,`date`)  VALUES(?,SHA2(?,256),?,?,?,?)";
    private final static String SELECT_ALL = "SELECT * FROM users";
    private final static String SELECT_CHECK = "SELECT email FROM users WHERE email =?";
    private final static String SELECT_LOGIN_USERNAME = "SELECT * FROM users WHERE username = ? AND password = SHA2(?,256)";
    private final static String DELETE_USER = "DELETE FROM `users` WHERE user_id = ?";
    private static final String INSERT_USER_CATEGORIES = "INSERT INTO users_has_categories Values(?,?);";

    public long insertUser(User user) {

        try {
            conn = connection.getConnection();
            PreparedStatement statement = conn.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getFirstName());
            statement.setString(5, user.getLastName());
            statement.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));
            statement.executeUpdate();


            ResultSet rs = statement.getGeneratedKeys();
            if ( rs.next() ) {
                long userId = rs.getLong(1);

                for ( int i = 1; i <= 14; i++ ) {
                    PreparedStatement preparedStm = conn.prepareStatement(INSERT_USER_CATEGORIES);
                    preparedStm.setLong(1, userId);
                    preparedStm.setLong(2, i);
                    preparedStm.executeUpdate();
                    System.out.println("i: " + i);
                }
                return userId;
            }

        } catch ( SQLException e ) {
            System.out.println(e.getMessage());
            return -1;
        }
        return 0;
    }

    public Set< User > selecttUsers() {

        try {
            conn = connection.getConnection();
            PreparedStatement prs = conn.prepareStatement(SELECT_ALL);
            ResultSet set = prs.executeQuery();
            Set< User > users = new TreeSet<>();
            while ( set.next() ) {
                User user = new User(set.getLong("user_id"), set.getString("username"), set.getString("email"), set.getString("first_name"), set.getString("last_name"), set.getInt("blocked"), set.getInt("rights"), set.getDate("date"));
                users.add(user);
            }
            System.out.println("USers size in user dao "+users.size());
            return users;
        } catch ( SQLException e ) {
            System.out.println("Problem with result set.");
        }
        return null;

    }

    public boolean checkIfExists(String email) {
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

    public User login(String username, String pass) {
        ResultSet set;
        if ( checkString(username) && checkString(pass) ) {
            try {
                conn = connection.getConnection();
                PreparedStatement prs = conn.prepareStatement(SELECT_LOGIN_USERNAME);
                prs.setString(1, username);
                prs.setString(2, pass);
                set = prs.executeQuery();
                if ( set.next() ) {
                    User user = makeUser(set);
                    try {
                        Set< Wallet > walletss = walletDAO.selectUserWallets(user.getUserId());
                        if ( !walletss.isEmpty() ) {
                            user.setWallets(walletss);
                        }
                    } catch ( Exception e ) {
                        System.out.println(e.getMessage());
                    }

                    return user;
                }
            } catch ( SQLException e ) {
                System.out.println(e.getMessage());
                return null;
            }
        }
        return null;
    }

    public boolean checkString(String str) {
        return str != null && !str.isEmpty();
    }

    private User makeUser(ResultSet set) throws SQLException {

        long user_id = set.getInt("user_id");
        int rights = set.getInt("rights");
        String username = set.getString("username");
        String email = set.getString("email");
        String firstname = set.getString("first_name");
        String lastName = set.getString("last_name");
        int blocked = set.getInt("blocked");
        User user = new User(user_id, username, email, firstname, lastName, blocked, rights, new Date());
        return user;
    }


    public boolean delUser(long user_id) throws SQLException {
        boolean returnbool = false;
        try {
            conn.setAutoCommit(false);
            conn = connection.getConnection();

            PreparedStatement prs = conn.prepareStatement("DELETE FROM users_has_categories WHERE user_id = ?");
            prs.setLong(1, user_id);
            prs.executeUpdate();

            prs = conn.prepareStatement("SELECT * FROM wallets WHERE user_id = ?");
            prs.setLong(1, user_id);
            ResultSet set = prs.executeQuery();

            ArrayList<Long> walletsId = new ArrayList<>();
            while(set.next()){
                long walletId = set.getLong("wallet_id");
                walletsId.add(walletId);
                System.out.println("wallet id in userdelete "+walletId);
                prs = conn.prepareStatement("DELETE FROM transactions WHERE wallet_id = ?");
                prs.setLong(1, walletId);
                prs.executeUpdate();

                prs = conn.prepareStatement("DELETE FROM budgets WHERE wallet_id = ?");
                prs.setLong(1, walletId);
                prs.executeUpdate();
            }
            System.out.println("wallet id sizeeeee "+walletsId.size());
            for ( Long walletId: walletsId) {
                prs = conn.prepareStatement("DELETE FROM wallets WHERE wallet_id = ?");
                prs.setLong(1, walletId);
                prs.executeUpdate();
            }
            prs = conn.prepareStatement(DELETE_USER);
            prs.setLong(1, user_id);
            prs.executeUpdate();

            conn.commit();
        } catch ( Exception e ) {
            System.out.println(e.getMessage());
            System.out.println(" trouble with the delete ");
            conn.rollback();
            return false;
        }
        return returnbool;
    }
}
