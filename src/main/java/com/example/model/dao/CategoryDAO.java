package com.example.model.dao;

import com.example.model.pojo.Category;
import com.example.model.dao.DBConnection;
import com.example.model.pojo.Transaction;
import com.example.model.pojo.TransactionType;
import com.example.model.pojo.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

@Component
public class CategoryDAO {
    @Autowired
    DBConnection conn;

    public static final String INSERT_CATEGORY = "INSERT INTO categories (name, type, img_path, isActive, wallet_id, user_id) VALUES (?, ?, ?, ?, ?, ?);";
    private final static String CHECK = "SELECT * FROM categories WHERE wallet_id = ?;";
    private final static String SELECT_CATEGORIES = "SELECT c.category_id , c.name , c.type , c.img_path FROM categories c , users_has_categories u WHERE c.category_id= u.category_id AND u.user_id = ?;";
    private static Connection connection;


    public Set< Category > selectUserCategories(long userId, long walletId, Connection conn) {
        Set< Category > categories = new TreeSet< Category >();
        Set< Transaction > transactions = new TreeSet< Transaction >();
        try {
            PreparedStatement preparedStatement;
            preparedStatement = conn.prepareStatement(SELECT_CATEGORIES);
            preparedStatement.setLong(1, userId);
            ResultSet set = preparedStatement.executeQuery();
            while ( set.next() ) {
                long category_id = set.getLong("category_id");
                preparedStatement = conn.prepareStatement("SELECT * FROM  transactions WHERE category_id = ? AND wallet_id = ?");
                preparedStatement.setLong(1, category_id);
                preparedStatement.setLong(2, walletId);
                ResultSet tranSet = preparedStatement.executeQuery();

                while ( tranSet.next() ) {
//(long transactionId, TransactionType type, BigDecimal amount, Date date, String description, long categoryId, long walletId) {
                    Transaction transaction = new Transaction(tranSet.getLong("transaction_id"), tranSet.getInt("type") == 0 ? TransactionType.EXPENSE : TransactionType.INCOME, tranSet.getBigDecimal("amount"), tranSet.getDate("date"), tranSet.getString("description"), category_id, walletId);
                    transactions.add(transaction);
                }

                Category category = new Category(category_id, set.getString("name"), set.getInt("type") == 1 ? TransactionType.INCOME : TransactionType.EXPENSE, set.getString("img_path"), walletId, userId, transactions);

                categories.add(category);
            }

            System.out.println("CATEGORIII SIZE  " + categories);
            return categories;
        } catch ( SQLException e ) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return categories;
        }
    }


    public void insertCategory(Category c) throws SQLException {

        if ( checkIfCategoryExist(c) ) {
            System.out.println("The category already exists.");
            return;
        }
        connection = conn.getConnection();
        PreparedStatement ps = connection.prepareStatement(INSERT_CATEGORY, Statement.RETURN_GENERATED_KEYS);

        ps.setString(1, c.getName());
        ps.setString(2, c.getType().toString());
        ps.setString(3, c.getImagePath());
        ps.setLong(4, c.getWalletId());
        ps.setLong(5, c.getUserId());

        ps.executeUpdate();
        ResultSet resultSet = ps.getGeneratedKeys();

        if ( resultSet.next() ) {
            System.out.println("Success");
        }

        c.setCategoryId(resultSet.getInt(1));
    }

    private boolean checkIfCategoryExist(Category category) {
        ResultSet resultSet;
        try {
            connection = conn.getConnection();
            PreparedStatement ps = connection.prepareStatement(CHECK);
            ps.setLong(1, category.getUserId());

            resultSet = ps.executeQuery();

            while ( resultSet.next() ) {
                if ( category.getName().equals(resultSet.getString("name")) ) {
                    return true;
                }
            }
        } catch ( SQLException e ) {
            System.out.println("Problem in the function.");
            e.printStackTrace();
        }
        return false;
    }
}
