package com.dao.classes;

import com.model.Budget;
import com.model.DBConnection;
import com.model.Transaction;
import com.model.TransactionType;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.Set;

public class TransactionDAO {
    private static Connection connection;

    public synchronized void insertTransaction(Transaction t) throws SQLException {
        connection = DBConnection.getInstance().getConnection();
        String query = "INSERT INTO finace_tracker.transactions (type, amount, description, date, category_id) VALUES (?, ?, ?, ?, )";

        PreparedStatement prepareStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
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
            connection.setAutoCommit(false);

            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw new SQLException();
        } finally {
            connection.setAutoCommit(true);
        }
    }
}
