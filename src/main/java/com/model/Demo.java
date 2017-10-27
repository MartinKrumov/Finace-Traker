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

        Transaction t = new Transaction(TransactionType.EXPENCE, BigDecimal.ONE.valueOf(20.5), LocalDateTime.now(), "Just runn", 14);

        try {
            TransactionDAO dao = TransactionDAO.getInstance();
            dao.insertTransaction(t);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
