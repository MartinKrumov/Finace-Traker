package com.example.controller;

import com.example.model.dao.TransactionDAO;
import com.example.model.pojo.Transaction;
import com.example.model.pojo.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDateTime;

@Controller
public class TransActionController {
@Autowired
    TransactionDAO transactionDAO;

    //    public Transaction(long transactionId, TransactionType type, BigDecimal amount, LocalDateTime date, String description, long categoryId) {
    @RequestMapping( value = "/transactionInsert", method = RequestMethod.POST )
    public String transaction(HttpServletRequest request, HttpServletResponse response, Model model) {

        String income = request.getParameter("type");
        String amount = request.getParameter("amount");
        String descrip = request.getParameter("description");
        String catId = request.getParameter("categoryId");
        String walletID = request.getParameter("walletId");
//        if ( amount.trim().matches("[0-9]") ) {
            BigDecimal amounts = new BigDecimal(amount);
            Transaction transaction = new Transaction( TransactionType.INCOME, amounts, descrip, Long.parseLong(catId),Long.parseLong(walletID));
            try {
                System.out.println("Trans amount "+transaction.getAmount());
                transactionDAO.insertTransaction(transaction);
            } catch ( SQLException e ) {
                e.printStackTrace();
            }
//        }
        System.out.println("type " + income);
        System.out.println("category_id " + catId);
        System.out.println("amount " + descrip);

        return "redirect:home";
    }
}

