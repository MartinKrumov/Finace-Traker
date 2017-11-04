package com.example.model.dao;

import com.example.model.dao.DBConnection;
import com.example.model.pojo.Budget;
import com.example.model.pojo.Category;
import com.example.model.pojo.Transaction;
import com.example.model.pojo.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Null;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Component
public class WalletDAO {
    @Autowired
    DBConnection connection;

    @Autowired
    CategoryDAO categoryDAO;

    @Autowired
    private TransactionDAO transactionDAO;

    @Autowired
    private BudgetDAO budgetDAO;

    private static final String INSERT_INTO_WALLET = "INSERT INTO `wallets` VALUES (null,?,?,?);";
    private static final String SELECT_FROM_WALLET = "SELECT * FROM wallets WHERE user_id = ?;";
    private static final String DELETE_USER_FROM_WALLET = "DELETE FROM `wallets` WHERE user_id = ?;";
    private static final String SELECT_USER_WALLET = "SELECT * FROM `wallets` WHERE user_id = ?;";


    public long insertWallet(Wallet wallet , long userId) throws SQLException {
        Connection conn = null;
        try {
            conn = connection.getConnection();
            System.out.println("wallet name " + wallet.getName());
            System.out.println(wallet.getAmount());
            System.out.println(userId);
            PreparedStatement preparedStatement = conn.prepareStatement(INSERT_INTO_WALLET, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, wallet.getName());
            preparedStatement.setBigDecimal(2, wallet.getAmount());
            preparedStatement.setLong(3, userId);
            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            if ( rs.next() ) {
                long walletId = rs.getLong(1);
                System.out.println("wallet id in wallets " + walletId);
                return walletId;
            }
        } catch ( SQLException e ) {
            e.printStackTrace();
            return -1;
        }
        return 0;
    }

    public ResultSet selecttCurrentUserWallet(int userId) {
        try {
            Connection conn = connection.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(SELECT_FROM_WALLET);
            preparedStatement.setInt(1, userId);
            return preparedStatement.executeQuery();
        } catch ( SQLException e ) {
            System.out.println("Wallet Trouble.");
        }
        return null;
    }

    public boolean deleteWallets(Integer userId) {
        boolean returnBool = false;
        try {
            Connection conn = connection.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(DELETE_USER_FROM_WALLET);
            preparedStatement.setInt(1, userId);
            returnBool = preparedStatement.execute();
        } catch ( SQLException e ) {
            System.out.println("Wallet Trouble.");
        }
        return returnBool;
    }

    public Set< Wallet > selectUserWallets(long userId) {
        Set< Wallet > wallets = new TreeSet<>();
        try {
            Connection conn = connection.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(SELECT_USER_WALLET);
            preparedStatement.setLong(1, userId);
            ResultSet set = preparedStatement.executeQuery();

            while ( set.next() ) {
//                System.out.println("user id : " + userId);
                int wallet_id = set.getInt("wallet_id");
                String name = set.getString("name");
                BigDecimal amount = set.getBigDecimal("amount");
                int user_id = set.getInt("user_id");
//                System.out.println("wallet id pri wallets: " + wallet_id);
                try {
//                    Set< Category > categories = CategoryDAO.selectUserCategories(wallet_id, connection);
////                    System.out.println("categorie set : " + categories);
                    Wallet wallet = new Wallet(wallet_id, name, amount, user_id);
//                    wallet.setCategories(categories);
                    wallets.add(wallet);
                } catch ( NullPointerException e ) {
                    e.printStackTrace();
                }
            }
            return wallets;

        } catch ( SQLException e ) {
            System.out.println("Wallet Trouble.");
            return null;
        }
    }

    public Set<Wallet> getAllWalletsByUserId(long userId) throws SQLException {
        Set<Wallet> wallets = new HashSet<>();

        String sql = "SELECT wallet_id, name, amount FROM wallets WHERE user_id = ?;";

        PreparedStatement ps = connection.getConnection().prepareStatement(sql);
        ps.setLong(1, userId);

        ResultSet res = ps.executeQuery();

        while(res.next()) {
            long walletId = res.getInt("wallet_id");
            String name = res.getString("name");
            BigDecimal amount = new BigDecimal(res.getDouble("amount"));
            List<Transaction> transactions = transactionDAO.getAllTransactionsByWalletId(walletId);
            List<Budget> budgets = budgetDAO.getAllBudgetsByWalletId(walletId);

            Wallet wallet = new Wallet(name, amount, userId, transactions);
            wallet.setWallettID((int)walletId);

            wallets.add(wallet);

        }

        return wallets;
    }

    public Wallet getWalletByUserIDAndWalletName(long userId, String name) throws SQLException {
        String sql = "SELECT wallet_id, amount FROM wallets WHERE user_id = ?;";

        PreparedStatement ps = connection.getConnection().prepareStatement(sql);
        ps.setLong(1, userId);
  /*      ps.setString(2, name);*/

        ResultSet res = ps.executeQuery();
        res.next();

        long walletId = res.getLong("wallet_id");
        BigDecimal amount = res.getBigDecimal("amount");

        Wallet wallet = new Wallet(name, amount, userId);
        wallet.setWallettID((int) walletId);

        return wallet;
    }


//    public static ResultSet selectWalletsForAdmin(int userId) {
//        try {
//            conn = DBConnection.getInstance().getConnection();
//            preparedStatement = conn.prepareStatement(SELECT_FROM_WALLET);
//            preparedStatement.setInt(1,userId);
//            return preparedStatement.executeQuery();
//        } catch ( SQLException e ) {
//            System.out.println("Wallet Trouble.");
//        }
//        return null;
//    }
}
