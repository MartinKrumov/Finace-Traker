package com.example.model.dao;

import com.example.model.dao.DBConnection;
import com.example.model.pojo.Category;
import com.example.model.pojo.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Null;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;
import java.util.TreeSet;

@Component
public class WalletDAO {
    @Autowired
    DBConnection connection;
    CategoryDAO categoryDAO;
    private static final String INSERT_INTO_WALLET = "INSERT INTO `wallets`( `name`, `amount`, `user_id`) VALUES (?,?,?);";
    private static final String SELECT_FROM_WALLET = "SELECT * FROM wallets WHERE user_id = ?;";
    private static final String DELETE_USER_FROM_WALLET = "DELETE FROM `wallets` WHERE user_id = ?;";
    private static final String SELECT_USER_WALLET = "SELECT * FROM `wallets` WHERE user_id = ?;";

    public long insertWallet(Wallet wallet) {
        try {
            Connection conn = connection.getConnection();
            System.out.println(wallet.getName());
            System.out.println(wallet.getAmount());
            System.out.println(wallet.getUserId());
            PreparedStatement preparedStatement = conn.prepareStatement(INSERT_INTO_WALLET, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, wallet.getName());
            preparedStatement.setBigDecimal(2, wallet.getAmount());
            preparedStatement.setLong(3, wallet.getUserId());
//			statement.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if ( rs.next() ) {
                long walletId = rs.getLong(1);
                System.out.println(walletId);
                System.out.println(wallet.getUserId());
                CategoryDAO.insertDefaultCategories( walletId, wallet.getUserId() ,connection);
                return walletId;
            }
        } catch ( SQLException e ) {
            System.out.println(e.getMessage());
            return -1;
        }
        return 0;
    }

    public ResultSet selecttCurrentUserWallet(int userId) {
        try {
            Connection conn = connection.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(SELECT_FROM_WALLET);
            preparedStatement.setInt(1, userId);
            return preparedStatement.executeQuery();
        } catch ( SQLException e ) {
            System.out.println("Wallet Trouble.");
        }
        return null;
    }

    public boolean deleteWallets(Integer userId) {
        boolean returnBool = false;
        try {
            Connection conn = connection.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(DELETE_USER_FROM_WALLET);
            preparedStatement.setInt(1, userId);
            returnBool = preparedStatement.execute();
        } catch ( SQLException e ) {
            System.out.println("Wallet Trouble.");
        }
        return returnBool;
    }

    public Set< Wallet > selectUserWallets(int userId) {
        Set< Wallet > wallets = new TreeSet<>();
        try {
            Connection conn = connection.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(SELECT_USER_WALLET);
            preparedStatement.setInt(1, userId);
            ResultSet set = preparedStatement.executeQuery();
            while ( set.next() ) {
                System.out.println("user id : " + userId);
                int wallet_id = set.getInt("wallet_id");
                String name = set.getString("name");
                BigDecimal amount = set.getBigDecimal("amount");
                int user_id = set.getInt("user_id");
                System.out.println("wallet id pri wallets: " + wallet_id);
                try {
                    Set< Category > categories = CategoryDAO.selectUserCategories(wallet_id, connection);
                    System.out.println("categorie set : " + categories);
                    Wallet wallet = new Wallet(wallet_id, name, amount, user_id);
                    wallet.setCategories(categories);
                    wallets.add(wallet);
                } catch ( NullPointerException e ) {
                    e.printStackTrace();
                }
            }
            return wallets;

        } catch ( SQLException e ) {
            System.out.println("Wallet Trouble.");
            return null;
        }
    }


//    public static ResultSet selectWalletsForAdmin(int userId) {
//        try {
//            conn = DBConnection.getInstance().getConnection();
//            preparedStatement = conn.prepareStatement(SELECT_FROM_WALLET);
//            preparedStatement.setInt(1,userId);
//            return preparedStatement.executeQuery();
//        } catch ( SQLException e ) {
//            System.out.println("Wallet Trouble.");
//        }
//        return null;
//    }
}
