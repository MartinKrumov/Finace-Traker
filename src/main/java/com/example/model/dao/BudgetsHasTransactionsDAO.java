package com.example.model.dao;

import com.example.model.pojo.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class BudgetsHasTransactionsDAO {
    @Autowired
    private DBConnection dbConnection;

    @Autowired
    private TransactionDAO transactionDAO;

    public static final String INSERT_TRANSACTION_BUDGET = "INSERT INTO budgets_has_transactions (budget_id, transaction_id) VALUES (?, ?)";
    public static final String SELECT_TRANSACTION_BY_BUDGET_ID = "SELECT transaction_id FROM budgets_has_transactions WHERE budget_id = ?";

    public void insertTransactionBudget(long budgetId, long transactionId) throws SQLException {

        PreparedStatement ps = dbConnection.getConnection().prepareStatement(INSERT_TRANSACTION_BUDGET);
        ps.setLong(1, budgetId);
        ps.setLong(2, transactionId);
        ps.executeUpdate();
    }

    public List<Transaction> getAllTransactionsByBudgetId(long budgetId) throws SQLException {

        PreparedStatement ps = dbConnection.getConnection().prepareStatement(SELECT_TRANSACTION_BY_BUDGET_ID);
        ps.setLong(1, budgetId);
        ResultSet res = ps.executeQuery();

        List<Transaction> transactions = new ArrayList<>();

//        while(res.next()) {
//            transactions.add(transactionDAO.getTransactionByTransactionId(res.getLong("transaction_id")));
//        }

        return transactions;
    }
}
