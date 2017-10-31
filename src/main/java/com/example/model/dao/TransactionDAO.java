package com.example.model.dao;

import com.example.model.pojo.Transaction;
import com.example.model.pojo.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;

@Component
public class TransactionDAO {
    @Autowired
    DBConnection connection;
    private static Connection conn;

    private static final String INSERT_TRANSACTION = "INSERT INTO transactions (transaction_type, amount, description, date, category_id) VALUES (?, ?, ?, ?, ?)";
    public static final String SELECT_TRANSACTIONS_FOR_CURRENT_WALLET = "SELECT * FROM transactions WHERE wallet_id = ?";

    public void insertTransaction(Transaction t) throws SQLException {
        conn = connection.getConnection();
        System.out.println("in the tranaction " + t.getAmount());
        PreparedStatement prepareStatement = conn.prepareStatement(INSERT_TRANSACTION, Statement.RETURN_GENERATED_KEYS);
        prepareStatement.setString(1, t.getType().toString());
        prepareStatement.setBigDecimal(2, t.getAmount());
        prepareStatement.setString(3, t.getDescription());
        prepareStatement.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
        prepareStatement.setLong(5, t.getCategoryId());
        prepareStatement.executeUpdate();

        ResultSet resultSet = prepareStatement.getGeneratedKeys();
        resultSet.next();
        t.setTransactionId(resultSet.getLong(1));

        try {
            conn.setAutoCommit(false);
            conn.commit();
        } catch ( SQLException e ) {
            conn.rollback();
            throw new SQLException();
        } finally {
            conn.setAutoCommit(true);
        }
    }

//    public void getAllTransactionsByWallet() throws SQLException {
//        PreparedStatement ps = null;
//        ps = connection.getConnection().prepareStatement(SELECT_TRANSACTIONS_FOR_CURRENT_WALLET);
//        ps.setLong(1, wallet.getWalletId());
//        ResultSet resultSet = ps.executeQuery();
//        while (resultSet.next()) {
//            long transactionId = resultSet.getInt("transaction_id");
//            String type = resultSet.getString("transaction_type");
//            TransactionType transactionType = TransactionType.valueOf(type);
//            LocalDateTime date = resultSet.getTimestamp("date").toLocalDateTime();
//            BigDecimal amount = resultSet.getBigDecimal("amount");
//            String description = resultSet.getString("description");
//            int categoryId = resultSet.getInt("category_id");
//            Transaction t = new Transaction(transactionId, transactionType, amount, date, description, categoryId );
//        }
//    }
}
