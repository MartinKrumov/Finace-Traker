package com.example.model.dao;

import com.example.model.pojo.Budget;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.*;
import java.time.LocalDateTime;

public class BudgetDAO {
    @Autowired
    DBConnection dbConnection;

    private static DBConnection conn;
    public static final String INSERT_BUDGET = "INSERT INTO budgets (name, initial_amount, amount, from_date, to_date, wallet_id, category_id) VALUES (?, ?, ?, STR_TO_DATE(?, '%Y-%m-%d %H:%i:%s'), STR_TO_DATE(?, '%Y-%m-%d %H:%i:%s'), ?, ?)";

    public void insertBudget(Budget b) throws SQLException {

        PreparedStatement ps = dbConnection.getConnection().prepareStatement(INSERT_BUDGET, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, b.getName());
        ps.setBigDecimal(2, b.getInitialAmount());
        ps.setBigDecimal(3, b.getAmount());
        ps.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
        ps.setTimestamp(5, Timestamp.valueOf(b.getToDate().withNano(0)));
        ps.setLong(6, b.getWalletId());
        ps.setLong(7, b.getCategoryID());
        ps.executeUpdate();

        ResultSet resultSet = ps.getGeneratedKeys();
        if (resultSet.next()) {
            System.out.println("Success");
        }

        b.setBudgetId(resultSet.getLong(1));

    }

}
