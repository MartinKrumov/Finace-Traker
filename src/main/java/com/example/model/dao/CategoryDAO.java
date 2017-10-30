package com.example.model.dao;

import com.example.model.pojo.Category;
import com.example.model.dao.DBConnection;
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
    private final static String SELECT_CATEGORYES = "SELECT * FROM categories WHERE wallet_id = ?;";

    private final static String INSERT_CATEGORYES_HOME = "INSERT INTO categories (name, type, img_path, isActive, wallet_id, user_id) VALUES ('Home', 0 , 'img', 1 ,?, ?);";
    private final static String INSERT_CATEGORYES_FOOD = "INSERT INTO categories (name, type, img_path, isActive, wallet_id, user_id) VALUES ('Food', 0 , 'img', 1 ,?, ?);";
    private final static String INSERT_CATEGORYES_CAR = "INSERT INTO categories (name, type, img_path, isActive, wallet_id, user_id) VALUES ('Car', 0 , 'img', 1 ,?, ?);";
    private final static String INSERT_CATEGORYES_BILS = "INSERT INTO categories (name, type, img_path, isActive, wallet_id, user_id) VALUES ('Bills', 0 , 'img', 1 ,?, ?);";
    private final static String INSERT_CATEGORYES_FUN = "INSERT INTO categories (name, type, img_path, isActive, wallet_id, user_id) VALUES ('Fun', 0 , 'img', 1 ,?, ?);";
    private final static String INSERT_CATEGORYES_FAMILY = "INSERT INTO categories (name, type, img_path, isActive, wallet_id, user_id) VALUES ('Family', 0 , 'img', 1 ,?, ?);";
    private final static String INSERT_CATEGORYES_HEALTH = "INSERT INTO categories (name, type, img_path, isActive,wallet_id, user_id) VALUES ('Health', 0 , 'img', 1 , ?, ?);";
    private final static String INSERT_CATEGORYES_GIGTS = "INSERT INTO categories (name, type, img_path, isActive, wallet_id, user_id) VALUES ('Gifts', 1 , 'img', 1 ,?, ?);";
    private final static String INSERT_CATEGORYES_SHOP = "INSERT INTO categories (name, type, img_path, isActive, wallet_id, user_id) VALUES ('Shop', 0 , 'img', 1 ,?, ?);";
    private final static String INSERT_CATEGORYES_SALARY = "INSERT INTO categories (name, type, img_path, isActive, wallet_id, user_id) VALUES ('Salary', 1 , 'img', 1 ,?, ?);";
    private final static String INSERT_CATEGORYES_INCOME = "INSERT INTO categories ( name, type, img_path, isActive,wallet_id, user_id) VALUES ('Another income', 1 , 'img', 1 ,?, ?);";
    private final static String INSERT_DCATEGORYES = "INSERT INTO categories (name, type, img_path, isActive, wallet_id, user_id) VALUES ('Another expense', 0 , 'img', 1 ,?, ?);";
    private static final String[] insertDefQueerys = {INSERT_CATEGORYES_HOME,
            INSERT_CATEGORYES_FOOD,INSERT_CATEGORYES_CAR,INSERT_CATEGORYES_BILS,INSERT_CATEGORYES_FUN
            ,INSERT_CATEGORYES_FAMILY,INSERT_CATEGORYES_HEALTH,INSERT_CATEGORYES_GIGTS,INSERT_CATEGORYES_SHOP,INSERT_CATEGORYES_SALARY
            ,INSERT_CATEGORYES_INCOME,INSERT_DCATEGORYES};

    private static Connection connection;
    private static PreparedStatement preparedStatement;

    public static void insertDefaultCategories(long walletId, long userId, DBConnection conn) {
        try {
//            System.out.println("Wallet id " + walletId);
//            System.out.println("UserID : " + userId);
            connection = conn.getConnection();
            for(String s: insertDefQueerys){
                preparedStatement = connection.prepareStatement(s);
                preparedStatement.setInt(1, ( int ) walletId);
                preparedStatement.setInt(2, ( int ) userId);
                preparedStatement.executeUpdate();
            }

//            System.out.println("wallet id out of the loop: " + walletId);
        } catch ( SQLException e ) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return;
        }
    }


    public static Set< Category > selectUserCategories(int walletId, DBConnection conn) throws NullPointerException {
        Set< Category > categories = new TreeSet< Category >();
        try {
            connection = conn.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_CATEGORYES);
            preparedStatement.setInt(1, walletId);
            ResultSet set = preparedStatement.executeQuery();
//            System.out.println("wallet id out of the loop: " + walletId);
            while ( set.next() ) {
//                System.out.println("wallet id pri categoriite : " + walletId);
                Category category = new Category(set.getInt("categories_id"), set.getString("name"), set.getInt("type") == 1 ? TransactionType.INCOME : TransactionType.EXPENCE, set.getString("img_path"), set.getString("isActive"), set.getInt("wallet_id"), set.getInt("user_id"));
                categories.add(category);
            }
//            System.out.println(categories.isEmpty());
//            System.out.println(categories.toString());
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
        ps.setString(4, c.isActive());
        ps.setLong(5, c.getWalletId());
        ps.setLong(6, c.getUserId());

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
