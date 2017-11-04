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
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

@Component
public class CategoryDAO {
    @Autowired
    DBConnection dbConnection;

    public static final String INSERT_CATEGORY = "INSERT INTO categories (name, type, img_path, isActive, wallet_id, user_id) VALUES (?, ?, ?, ?, ?, ?);";
    private final static String CHECK = "SELECT * FROM categories WHERE wallet_id = ?;";
    private final static String SELECT_CATEGORIES = "SELECT * FROM users_has_categories m , categories c WHERE m.category_id = c.category_id AND m.user_id = ?;";

    public static Set< Category > selectUserCategories(long walletId, long userId, Connection conn) throws NullPointerException {
        Set< Category > categories = new TreeSet< Category >();
        Set< Transaction > transactions = new TreeSet< Transaction >();
        try {
            PreparedStatement preparedStatement;
            preparedStatement = conn.prepareStatement(SELECT_CATEGORIES);
            preparedStatement.setLong(1, userId);
            ResultSet set = preparedStatement.executeQuery();
//            System.out.println("wallet id out of the loop: " + walletId);
            while ( set.next() ) {
                long categoryId = set.getLong("category_id");
                preparedStatement = conn.prepareStatement("SELECT * FROM transactions WHERE category_id = ? AND wallet_id = ?");
                preparedStatement.setLong(1, categoryId);
                preparedStatement.setLong(2, walletId);
                ResultSet tranSet = preparedStatement.executeQuery();
                System.out.println("categoryId "+categoryId);
                System.out.println("walletId "+walletId);
                while ( tranSet.next() ) {
                    Transaction transaction = new Transaction(tranSet.getLong("transaction_id"), tranSet.getInt("type") == 0 ? TransactionType.EXPENSE : TransactionType.INCOME, tranSet.getBigDecimal("amount"), tranSet.getDate("date"), tranSet.getString("description"), categoryId, walletId);
                    transactions.add(transaction);
                }
//    public Category(long categoryId, String name, TransactionType type, String imagePath, long userId, Set< Transaction > transactions) {

                Category category = new Category(categoryId, set.getString("name"), set.getInt("type") == 1 ? TransactionType.INCOME : TransactionType.EXPENSE, set.getString("img_path"), walletId, userId, transactions);

                categories.add(category);
            }

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

        PreparedStatement ps = dbConnection.getConnection().prepareStatement(INSERT_CATEGORY, Statement.RETURN_GENERATED_KEYS);

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
            PreparedStatement ps = dbConnection.getConnection().prepareStatement(CHECK);
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

    public Set<String> getAllCategoriesByType(long userId, String type) throws SQLException {
        Set<String> categoriesNames = new HashSet<>();
        String query = "SELECT name, user_id, type FROM categories WHERE user_id = ?  AND type = ?";
        PreparedStatement ps = dbConnection.getConnection().prepareStatement(query);
        ps.setLong(1, userId);
        ps.setString(2, type);

        ResultSet resultSet = ps.executeQuery();
        while(resultSet.next()) {
            String name = resultSet.getString("name");
            categoriesNames.add(name);
        }

        return categoriesNames;
    }
}
