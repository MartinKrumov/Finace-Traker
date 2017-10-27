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
    private static TransactionDAO instance;

    public static final String INSERT_TRANSACTION ="INSERT INTO transactions (transaction_type, amount, date, description, category_id) VALUES (?, ?, ?, ?, ?)";
    public static final String UPDATE_TRANSACTION = "UPDATE transactions SET transaction_type = ?, date = STR_TO_DATE(?, '%Y-%m-%d %H:%i:%s'), amount = ?, description = ?, category_id = ? WHERE transaction_id = ?";

    public synchronized void insertTransaction(Transaction t) throws SQLException {
        connection = DBConnection.getInstance().getConnection();

        PreparedStatement ps = connection.prepareStatement(INSERT_TRANSACTION, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, t.getType().toString());
        ps.setBigDecimal(2, t.getAmount());
        ps.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
        ps.setString(4, t.getDescription());
        ps.setLong(5, t.getCategoryId());
        ps.executeUpdate();

        ResultSet resultSet = ps.getGeneratedKeys();
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
    public synchronized void updateTransaction(Transaction t) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();

        PreparedStatement ps = connection.prepareStatement(UPDATE_TRANSACTION);
        ps.setString(1, t.getType().toString());
        ps.setBigDecimal(2, t.getAmount());
        ps.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
        ps.setString(4, t.getDescription());
        ps.setLong(5, t.getCategoryId());
        ps.setLong(6, t.getTransactionId());
        ps.executeUpdate();

        System.out.println("*** Success ***");
    }

    public synchronized static TransactionDAO getInstance() throws SQLException {
        if (instance == null) {
            instance = new TransactionDAO();
        }
        return instance;
    }


}
