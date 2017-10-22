package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	public static DBConnection instance;
	private Connection connection;
	private static final String USERNAME = "root";
    private static final String PASSWORD = "123456";
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/Finance-Tracker";
    
    private DBConnection() {
        try {
            Class.forName(DBConnection.JDBC_DRIVER);
            this.connection = DriverManager.getConnection(DBConnection.DB_URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.out.println("Unable to load database driver: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Unable to connect to database: " + e.getMessage());
        }
    }
    
    public static DBConnection getInstance() {
    	if (instance == null) {
    		synchronized (DBConnection.class) {
    			if (instance == null) {
    				instance = new DBConnection();
    			}
			}
		}
    	return instance;
    }
    
    public Connection getConnection() {
        return this.connection;	
    }
    
    public void closeConnection() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            System.err.println("Problem closing connection.");
        }
    }
}
