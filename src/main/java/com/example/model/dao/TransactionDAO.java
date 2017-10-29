package com.example.model.dao;

import com.example.model.dao.DBConnection;
import com.example.model.pojo.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.time.LocalDateTime;

@Component
public class TransactionDAO {
    @Autowired
    DBConnection connection;
    private static Connection conn;

    public synchronized void insertTransaction(Transaction t) throws SQLException {
        conn = connection.getConnection();
        String query = "INSERT INTO finace_tracker.transactions (type, amount, description, date, category_id) VALUES (?, ?, ?, ?, )";

        PreparedStatement prepareStatement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        prepareStatement.setString(1, t.getType().toString());
        prepareStatement.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
        prepareStatement.setBigDecimal(3, t.getAmount());
        prepareStatement.setString(4, t.getDescription());
        prepareStatement.setLong(5, t.getCategoryId());
        prepareStatement.executeUpdate();

        ResultSet resultSet = prepareStatement.getGeneratedKeys();
        resultSet.next();
        t.setTransactionId(resultSet.getLong(1));

        try {
            conn.setAutoCommit(false);

            conn.commit();
        } catch (SQLException e) {
            conn.rollback();
            throw new SQLException();
        } finally {
            conn.setAutoCommit(true);
        }
    }
}
