package com.example.model.dao;

import com.example.model.dao.DBConnection;
import com.example.model.pojo.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    private static Connection conn;
    private static PreparedStatement preparedStatement;
    private static final String INSERT_INTO_WALLET = "INSERT INTO `wallets`( `name`, `amount`, `user_id`) VALUES (?,?,?);";
    private static final String SELECT_FROM_WALLET = "SELECT * FROM wallets WHERE user_id = ?;";
    private static final String DELETE_USER_FROM_WALLET = "DELETE FROM `wallets` WHERE user_id = ?;";
    private static final String SELECT_USER_WALLET = "SELECT * FROM `wallets` WHERE user_id = ?;";

    public long insertWallet(Wallet wallet) {
        try {
            conn = connection.getConnection();
            preparedStatement = conn.prepareStatement(INSERT_INTO_WALLET, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, wallet.getName());
            preparedStatement.setBigDecimal(2, wallet.getAmount());
            preparedStatement.setLong(3, wallet.getUserId());
//			statement.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if ( rs.next() ) {
                return rs.getLong(1);
            }
        } catch ( SQLException e ) {
            System.out.println(e.getMessage());
            return -1;
        }
        return 0;
    }

    public  ResultSet selecttCurrentUserWallet(int userId) {
        try {
            conn = connection.getConnection();
            preparedStatement = conn.prepareStatement(SELECT_FROM_WALLET);
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
            conn = connection.getConnection();
            preparedStatement = conn.prepareStatement(DELETE_USER_FROM_WALLET);
            preparedStatement.setInt(1, userId);
            returnBool = preparedStatement.execute();
        } catch ( SQLException e ) {
            System.out.println("Wallet Trouble.");
        }
        return returnBool;
    }


    public Set<Wallet> selectUserWallets(long userId) throws NullPointerException {
        Set<Wallet> wallets = new TreeSet<>();
        try {
            conn = connection.getConnection();
            preparedStatement = conn.prepareStatement(SELECT_USER_WALLET);
            preparedStatement.setInt(1,(int) userId);
            ResultSet set  = preparedStatement.executeQuery();
//            private int wallettID;
//            private String name;
//            private BigDecimal amount;
//            private int userId;
//    public Wallet(int wallettID, String name, BigDecimal amount, int userId, Set< Category > categories) {

            while(set.next()){
                Wallet wallet = new Wallet(set.getInt("wallet_id"),set.getString("name"),set.getBigDecimal("amount"),set.getInt("user_id"));
                wallets.add(wallet);
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
