package com.model;

import com.dao.classes.CategoryDAO;
import com.dao.classes.TransactionDAO;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class Demo {
    public static void main(String[] args) {
//        Category c = new Category("TONKA SERVLETA99", TransactionType.EXPENCE, "asdasd", "yes", 1, 1);
//
//        try {
//            CategoryDAO.insertCategory(c);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        Transaction t = new Transaction(TransactionType.INCOME, BigDecimal.ONE.valueOf(47.69), LocalDateTime.now(), "Aidee**", 14);

        try {
            TransactionDAO dao = TransactionDAO.getInstance();
//            dao.insertTransaction(t);
            dao.updateTransaction(t);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}