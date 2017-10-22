package com.dao.classes;

import com.model.DBConnection;
import com.model.Wallet;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WalletDAO {

    private long wallettID;
    private String name;
    private BigDecimal amount;
    private long userId;
    private static Connection conn;
    private static PreparedStatement preparedStatement;
    private static final String INSERT_INTO_WALLET = "INSERT INTO `wallets`( `name`, `amount`, `user_id`) VALUES (?,?,?);";
    private static final String SELECT_FROM_WALLET = "SELECT  * FROM wallets WHERE user_id = ?;";


    public static long insertWallet(Wallet wallet) {
        try {
            conn = DBConnection.getInstance().getConnection();
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

    public static ResultSet selecttCurrentUserWallet(int userId) {
        try {
            conn = DBConnection.getInstance().getConnection();
            preparedStatement = conn.prepareStatement(SELECT_FROM_WALLET);
            preparedStatement.setInt(1,userId);
            return preparedStatement.executeQuery();
        } catch ( SQLException e ) {
            System.out.println("Wallet Trouble.");
        }
        return null;
    }
    public static ResultSet selectWalletsForAdmin(int userId) {
        try {
            conn = DBConnection.getInstance().getConnection();
            preparedStatement = conn.prepareStatement(SELECT_FROM_WALLET);
            preparedStatement.setInt(1,userId);
            return preparedStatement.executeQuery();
        } catch ( SQLException e ) {
            System.out.println("Wallet Trouble.");
        }
        return null;
    }
}
