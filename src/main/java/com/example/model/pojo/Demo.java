package com.example.model.pojo;

import com.example.model.dao.BudgetDAO;
import com.example.model.dao.BudgetsHasTransactionsDAO;
import com.example.model.dao.CategoryDAO;
import com.example.model.dao.TransactionDAO;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class Demo {
//    @Autowired
//    static CategoryDAO categoryDAO ;

//    @Autowired
//    static BudgetDAO budgetDAO;

//    @Autowired
//    static TransactionDAO transactionDAO;
//
//    @Autowired
//    static BudgetsHasTransactionsDAO budgetsHasTransactionsDAO;

    public static void main(String[] args) {
//        Category c = new Category("TONKA SERVLETA99", TransactionType.EXPENCE, "asdasd", "yes", 1, 1);

//        Category c = new Category("TONKA SERVLETA99", TransactionType.EXPENCE, "asdasd", "yes", 1, 1);

        Budget b = new Budget("Test it", BigDecimal.valueOf(55), BigDecimal.valueOf(20), LocalDateTime.now(), LocalDateTime.now(), 1, 1, new ArrayList<>());
//        BudgetDAO budgetDAO = new BudgetDAO();
//        try {
//            System.out.println("DJABA");
//            budgetDAO.insertBudget(b);
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }


        Date date = new Date();
        date.setMonth(date.getMonth()+1);


    }
}
