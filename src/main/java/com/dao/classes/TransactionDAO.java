package com.dao.classes;

import com.model.Budget;
import com.model.DBConnection;
import com.model.Transaction;
import com.model.TransactionType;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.Set;

public class TransactionDAO {
    private static final Connection connection = DBConnection.getInstance().getConnection();

    public synchronized void insertTransaction(Transaction t) throws SQLException {

        String query = "INSERT INTO finace_tracker.transactions (type, amount, description, date, category_id) VALUES (?, ?, ?, ?, )";
        PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, t.getType().toString());
        statement.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
        statement.setBigDecimal(3, t.getAmount());
        statement.setString(4, t.getDescription());
        statement.setLong(5, t.getCategoryId());
        statement.executeUpdate();

        ResultSet resultSet = statement.getGeneratedKeys();
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
