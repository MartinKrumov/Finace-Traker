package com.example.model.dao;

import com.example.model.pojo.*;
import com.example.model.dao.DBConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.*;

@Component
public class CategoryDAO {
    @Autowired
    private DBConnection dbConnection;

    @Autowired
    private BudgetDAO budgetDao;

    @Autowired
    private TransactionDAO transactionDAO;

    public static final String INSERT_CATEGORY = "INSERT INTO categories (name, type, img_path) VALUES (?, ?, ?);";
    private final static String CHECK = "SELECT * FROM categories WHERE name = ? AND type = ?;";
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
                System.out.println("categoryId " + categoryId);
                System.out.println("walletId " + walletId);
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


    public void insertCategory(Category c, long userId) throws SQLException {
        long category_id = checkIfCategoryExist(c);
        System.out.println("category_id  "+category_id );
        if ( category_id == 0 ) {

            PreparedStatement ps = dbConnection.getConnection().prepareStatement(INSERT_CATEGORY, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, c.getName());
            ps.setInt(2, c.getType() == TransactionType.EXPENSE ? 0 : 1);
            ps.setString(3, "img_path");

            ps.executeUpdate();
            ResultSet resultSet = ps.getGeneratedKeys();

            if ( resultSet.next() ) {
                System.out.println("Success");

                long catId = resultSet.getLong(1);
                c.setCategoryId(catId);

                ps = dbConnection.getConnection().prepareStatement("INSERT INTO users_has_categories (user_id , category_id) VALUES(?,?)");

                ps.setLong(1, userId);
                ps.setLong(2, c.getCategoryId());

                ps.executeUpdate();
            }
        } else {
            PreparedStatement ps = dbConnection.getConnection().prepareStatement("INSERT INTO users_has_categories (user_id , category_id) VALUES (?,?)");

            ps.setLong(1, userId);
            ps.setLong(2, category_id);
            ps.executeUpdate();
        }
    }

    private boolean checkIfCategoryExist(Category category) {
        ResultSet resultSet;
        long catId = 0;
        try {
            PreparedStatement ps = dbConnection.getConnection().prepareStatement(CHECK);
            ps.setString(1, category.getName());
            ps.setInt(2, category.getType() == TransactionType.EXPENSE ? 0 : 1);
            resultSet = ps.executeQuery();

            if ( resultSet.next() ) {
                catId = resultSet.getLong("category_id");
                System.out.println("cat id ima go " + catId);
                return catId;
            }
        } catch ( SQLException e ) {
            System.out.println("Problem in the function.");
            e.printStackTrace();
        }
        System.out.println("cat id nqma go " + catId);
        return catId;
    }

    public Set<String> getAllCategoriesByType(long userId, int type) throws SQLException {
        Set<String> categoriesNames = new HashSet<>();
        String query = "SELECT * FROM users_has_categories m , categories c WHERE m.category_id = c.category_id AND m.user_id = ? AND c.type = ?;";
        PreparedStatement ps = dbConnection.getConnection().prepareStatement(query);
        ps.setLong(1, userId);
        ps.setInt(2, type);

        ResultSet resultSet = ps.executeQuery();
        while(resultSet.next()) {
            String name = resultSet.getString("name");
            categoriesNames.add(name);
        }

        return categoriesNames;
    }

    public Category getCategoryByName(String categoryName) throws SQLException {
        String query = "SELECT * FROM categories WHERE name = ?";
        PreparedStatement statement = dbConnection.getConnection().prepareStatement(query);
        statement.setString(1, categoryName);

        ResultSet resultSet = statement.executeQuery();
        resultSet.next();

        long categoryId = resultSet.getLong("category_id");
        String name = resultSet.getString("name");
        TransactionType type = resultSet.getInt("type") == 0 ? TransactionType.EXPENSE : TransactionType.INCOME;
        String imgPath = resultSet.getString("img_path");

        Category category = new Category(categoryId, name, type, imgPath);
        category.setCategoryId(categoryId);

        return category;
    }
}
