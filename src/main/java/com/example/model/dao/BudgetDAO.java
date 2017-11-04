package com.example.model.dao;

import com.example.model.pojo.Budget;
import com.example.model.pojo.Transaction;
import com.example.model.pojo.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Date;

@Component
public class BudgetDAO {
    @Autowired
    private DBConnection dbConnection;

    @Autowired
    BudgetsHasTransactionsDAO budgetsHasTransactionsDAO;

    private static DBConnection conn;
    public static final String INSERT_BUDGET = "INSERT INTO budgets (name, initial_amount, amount, from_date, to_date, wallet_id, category_id) VALUES (?, ?, ?, STR_TO_DATE(?, '%Y-%m-%d %H:%i:%s'), STR_TO_DATE(?, '%Y-%m-%d %H:%i:%s'), ?, ?)";

    public static final String UPDATE_BUDGET = "UPDATE budgets SET name = ?, initial_amount = ?, amount = ?, from_date = STR_TO_DATE(?, '%Y-%m-%d %H:%i:%s'), to_date = STR_TO_DATE(?, '%Y-%m-%d %H:%i:%s'), wallet_id = ?, category_id = ? WHERE budget_id = ?";

    public static final String SELECT_BUDGETS = "SELECT budget_id, name, initial_amount, amount, from_date, to_date, category_id FROM budgets WHERE wallet_id = ?";

    public void insertBudget(Budget b) throws SQLException {
        Connection con = dbConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(INSERT_BUDGET, Statement.RETURN_GENERATED_KEYS);


        ps.setString(1, b.getName());
        ps.setBigDecimal(2, b.getInitialAmount());
        ps.setBigDecimal(3, b.getAmount());
        ps.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
        ps.setTimestamp(5, Timestamp.valueOf(b.getToDate().withNano(0)));
        ps.setLong(6, b.getWalletId());
        ps.setLong(7, b.getCategoryID());
        ps.executeUpdate();

        ResultSet resultSet = ps.getGeneratedKeys();
        if ( resultSet.next() ) {
            System.out.println("Success");
        }

        b.setBudgetId(resultSet.getLong(1));

    }

    public void updateBudget(Budget b) throws SQLException {

        PreparedStatement ps = dbConnection.getConnection().prepareStatement(UPDATE_BUDGET);
        ps.setString(1, b.getName());
        ps.setBigDecimal(2, b.getInitialAmount());
        ps.setBigDecimal(3, b.getAmount());
        ps.setTimestamp(4, Timestamp.valueOf(b.getFromDate().withNano(0)));
        ps.setTimestamp(5, Timestamp.valueOf(b.getToDate().withNano(0)));
        ps.setLong(6, b.getWalletId());
        ps.setLong(7, b.getWalletId());
        ps.setLong(8, b.getWalletId());
        ps.executeUpdate();
    }

    public List< Budget > getAllBudgetsByWalletId(long walletId) throws SQLException {
        PreparedStatement ps = dbConnection.getConnection().prepareStatement(SELECT_BUDGETS);
        ps.setLong(1, walletId);

        List< Budget > budgets = new ArrayList<>();

        ResultSet resultSet = ps.executeQuery();

        while ( resultSet.next() ) {
            long budgetId = resultSet.getLong("budget_id");
            String name = resultSet.getString("name");
            BigDecimal initialAmount = resultSet.getBigDecimal("initial_amount");
            BigDecimal amount = resultSet.getBigDecimal("amount");
            LocalDateTime fromDate = resultSet.getTimestamp("from_date").toLocalDateTime();
            LocalDateTime toDate = resultSet.getTimestamp("to_date").toLocalDateTime();
            long categoryId = resultSet.getLong("category_id");
            List< Transaction > transactions = budgetsHasTransactionsDAO.getAllTransactionsByBudgetId(budgetId);

            Budget budget = new Budget(budgetId, name, initialAmount, amount, fromDate, toDate, walletId, categoryId, transactions);

            budgets.add(budget);
        }

        return budgets;
    }

    public synchronized boolean existsBudgetBetwen(Date date, long categoryId, long walletId, BigDecimal amount) throws SQLException {
        String sql = "SELECT budget_id,from_date, to_date, wallet_id, category_id , amount FROM budgets WHERE category_id = ? AND wallet_id = ?;";

        PreparedStatement ps = dbConnection.getConnection().prepareStatement(sql);
        ps.setLong(1, categoryId);
        ps.setLong(2, walletId);
        ResultSet res = ps.executeQuery();

        while ( res.next() ) {
            Date fromDate = res.getDate("from_date");
            Date toDate = res.getDate("to_date");
            long budget_id = res.getLong("budget_id");
            System.out.println("fromDate " + fromDate);
            System.out.println("toDate " + toDate);
            System.out.println("DAte given " + date);
            if ( isBetweenTwoDates(date, fromDate, toDate) ) {
                BigDecimal amountBudget = res.getBigDecimal("amount");
                amountBudget = amountBudget.subtract(amount);
                System.out.println("amount to substract " + amount);
                System.out.println("amount budjet " + amountBudget);
                ps = dbConnection.getConnection().prepareStatement("UPDATE budgets SET amount = ? WHERE category_id = ? AND wallet_id = ? AND budget_id =?");
                ps.setBigDecimal(1, amountBudget);
                ps.setLong(2, categoryId);
                ps.setLong(3, walletId);
                ps.setLong(4, walletId);
                int result = ps.executeUpdate();
                System.out.println("RESULT " + result);
                return result > 0 ? true : false;
            }
        }
        return false;
    }

    private boolean isBetweenTwoDates(Date currentDate, Date fromDate, Date toDate) {

        if ( currentDate.after(fromDate) && currentDate.before(toDate) ) {
            return true;
        }
        return false;
    }

//    public Set< Budget > getBudgetsByWalletDateCategory(LocalDateTime date, long categoryId, long walletId) throws SQLException {
//        String sql = "SELECT budget_id, name, initial_amount, amount, from_date, to_date, wallet_id, category_id FROM budgets WHERE category_id = ? AND wallet_id = ?;";
//
//        PreparedStatement ps = dbConnection.getConnection().prepareStatement(sql);
//        ps.setLong(1, categoryId);
//        ps.setLong(2, walletId);
//
//        ResultSet res = ps.executeQuery();
//        Set< Budget > budgets = new HashSet<>();
//
//        while ( res.next() ) {
//            long budgetId = res.getLong("budget_id");
//            String name = res.getString("name");
//            BigDecimal initialAmount = res.getBigDecimal("initial_amount");
//            BigDecimal amount = res.getBigDecimal("amount");
//            LocalDateTime fromDate = res.getTimestamp("from_date").toLocalDateTime();
//            LocalDateTime toDate = res.getTimestamp("to_date").toLocalDateTime();
//            List< Transaction > transactions = budgetsHasTransactionsDAO.getAllTransactionsByBudgetId(budgetId);
//
//            Budget b = new Budget(budgetId, name, initialAmount, amount, fromDate, toDate, walletId, categoryId, transactions);
//
//            if ( isBetweenTwoDates(date, fromDate, toDate) ) {
//                budgets.add(b);
//            }
//        }
//
//        return budgets;
//    }


}
