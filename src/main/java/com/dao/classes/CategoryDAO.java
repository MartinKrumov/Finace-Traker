package com.dao.classes;

import com.model.Category;
import com.model.DBConnection;

import java.sql.*;

public class CategoryDAO {
    public static final String INSERT_CATEGORY = "INSERT INTO categories (name, type, img_path, isActive, wallet_id, user_id) VALUES (?, ?, ?, ?, ?, ?)";
    private final static String CHECK = "SELECT * FROM categories WHERE categories_id = ?";
    private static Connection connection;

    public static void insertCategory(Category c) throws SQLException {

        if (checkIfCategoryExist(c)){
            System.out.println("The category already exists.");
            return;
        }
        connection = DBConnection.getInstance().getConnection();
        PreparedStatement ps = connection.prepareStatement(INSERT_CATEGORY, Statement.RETURN_GENERATED_KEYS);

        ps.setString(1, c.getName());
        ps.setString(2, c.getType().toString());
        ps.setString(3, c.getImagePath());
        ps.setString(4, c.isActive());
        ps.setLong(5, c.getCategoryId());
        ps.setLong(6, c.getUserId());

        ps.executeUpdate();
        ResultSet resultSet = ps.getGeneratedKeys();

        resultSet.next();

        c.setCategoryId(resultSet.getLong(1));
    }

    private static boolean checkIfCategoryExist(Category category) {
        ResultSet resultSet;
        try {
            connection = DBConnection.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(CHECK);

            resultSet = ps.executeQuery();

            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Problem in the function.");
        }
        return true;
    }
}
