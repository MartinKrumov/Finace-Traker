package com.example.controller;

import com.example.model.pojo.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class TransActionController {


    //    public Transaction(long transactionId, TransactionType type, BigDecimal amount, LocalDateTime date, String description, long categoryId) {
    @RequestMapping( value = "/transaction", method = RequestMethod.POST )
    public String insertTransaction(HttpServletRequest request, HttpServletResponse response, Model model) {
        String income = request.getParameter("isIncome");
        String descrip = request.getParameter("description");
        String catId = request.getParameter("categoryId");

        System.out.println("type " + income);
        System.out.println("category_id " + catId);
        System.out.println("amount " + descrip);
        return "home?parameter=wallets";
    }


}
