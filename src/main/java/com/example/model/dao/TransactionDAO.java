package com.example.model.dao;

import com.example.model.pojo.Budget;
import com.example.model.pojo.Transaction;
import com.example.model.pojo.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Component
public class TransactionDAO {
    @Autowired
    private DBConnection dbConnection;

    @Autowired
    private BudgetDAO budgetDAO;

    @Autowired
    private BudgetsHasTransactionsDAO budgetsHasTransactionsDAO;

    @Autowired
    private CategoryDAO categoryDAO;

    public static final String SELECT_TRANSACTIONS_FOR_CURRENT_WALLET = "SELECT * FROM transactions WHERE wallet_id = ?";

    public static final String SELECT_TRANSACTION_BY_TRAN_ID = "SELECT type, amount, date, description, category_id, wallet_id FROM transactions WHERE transaction_id = ?";

    private static final String INSERT_TRANSACTION = "INSERT INTO transactions (type, amount, description, date, category_id, wallet_id) VALUES (?, ?, ?, ?, ?, ?)";


    public synchronized void insertTransaction(Transaction transaction , BigDecimal walletAmount) throws SQLException {


        Connection conn = dbConnection.getConnection();
        conn.setAutoCommit(false);
        PreparedStatement prepareStatement = conn.prepareStatement(INSERT_TRANSACTION, Statement.RETURN_GENERATED_KEYS);
//        transaction.getType() == TransactionType.EXPENSE ? 0 : 1
        prepareStatement.setInt(1, transaction.getType() == TransactionType.EXPENSE ? 0 : 1);
        prepareStatement.setBigDecimal(2, transaction.getAmount());
        prepareStatement.setString(3, transaction.getDescription());
        prepareStatement.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
        prepareStatement.setLong(5, transaction.getCategoryId());
        prepareStatement.setLong(6, transaction.getWalletId());
        prepareStatement.executeUpdate();

        ResultSet resultSet = prepareStatement.getGeneratedKeys();
        resultSet.next();
        transaction.setTransactionId(resultSet.getLong(1));

        walletAmount = walletAmount.subtract(transaction.getAmount());
        System.out.println("In trans wallet amount "+walletAmount);

        prepareStatement = conn.prepareStatement("UPDATE  wallets SET amount = ? WHERE wallet_id = ?");
        prepareStatement.setBigDecimal(1, walletAmount);
        prepareStatement.setLong(2, transaction.getWalletId());
        prepareStatement.executeUpdate();

        Date date = new Date();
        boolean hasBudget = budgetDAO.existsBudgetBetwen(date, transaction.getCategoryId(), transaction.getWalletId(), transaction.getAmount());
        System.out.println("has budget "+hasBudget);
            conn.commit();


    }

    public Transaction getTransactionByTransactionId(long transactionId) throws SQLException {


        PreparedStatement ps = dbConnection.getConnection().prepareStatement(SELECT_TRANSACTION_BY_TRAN_ID);
        ps.setLong(1, transactionId);

        ResultSet resultSet = ps.executeQuery();
        resultSet.next();

        TransactionType transactionType = resultSet.getInt("type") == 1 ? TransactionType.EXPENSE : TransactionType.INCOME;
        BigDecimal amount = resultSet.getBigDecimal("amount");
        Date date = resultSet.getDate("date");
        String description = resultSet.getString("description");
        int categoryId = resultSet.getInt("category_id");
        int walletId = resultSet.getInt("wallet_id");


        Transaction transaction = new Transaction(transactionType, amount, date, description, categoryId, walletId);

        return transaction;
    }

    public List<Transaction> getAllTransactionsByWalletId(long walletId) throws SQLException {
        PreparedStatement ps = dbConnection.getConnection().prepareStatement("SELECT * FROM transactions WHERE wallet_id = ?;");
        ps.setLong(1, walletId);

        List<Transaction> transactions = new ArrayList<>();
        ResultSet resultSet = ps.executeQuery();

        while(resultSet.next()) {
            long transactionId = resultSet.getLong("transaction_id");

            TransactionType transactionType = resultSet.getInt("type") == 1 ? TransactionType.INCOME:TransactionType.EXPENSE;
            BigDecimal amount = resultSet.getBigDecimal("amount");
            Date date = resultSet.getDate("date");
            int categoryId = resultSet.getInt("category_id");

            Transaction t = new Transaction(transactionId, transactionType, amount, date,  categoryId, walletId);
            transactions.add(t);
        }

        return transactions;

    }
}
