package com.example.controller;

import com.example.model.dao.TransactionDAO;
import com.example.model.pojo.Transaction;
import com.example.model.pojo.TransactionType;
import com.example.model.pojo.User;
import com.example.model.pojo.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Set;

@Controller
public class TransActionController {
    @Autowired
    TransactionDAO transactionDAO;

    //    public Transaction(long transactionId, TransactionType type, BigDecimal amount, LocalDateTime date, String description, long categoryId) {
    @RequestMapping( value = "/transactionInsert", method = RequestMethod.POST )
    public String transaction(HttpSession session, HttpServletRequest request, HttpServletResponse response, Model model) {
        User user = ( User ) session.getAttribute("user");

        String income = request.getParameter("type");
        String amount = request.getParameter("amount");
        String descrip = request.getParameter("description");
        String catId = request.getParameter("categoryId");
        String walletID = request.getParameter("walletId");
        long walletId = Long.parseLong(walletID);
        BigDecimal amounts = new BigDecimal(amount);
        Set<Wallet> wallets = user.getWallets();

        System.out.println("wallets.size() "+ wallets.size());
        for ( Wallet w :  wallets) {
            System.out.println("wallet iddd out of the if "+w.getWalletId());
            if ( w.getWalletId() == walletId ) {
                System.out.println("wallet iddd "+w.getWalletId());
                BigDecimal walletAmount = w.getAmount();
                Transaction transaction = new Transaction(TransactionType.INCOME, amounts, descrip, Long.parseLong(catId), walletId);
                System.out.println(walletAmount);

                try {
                    System.out.println("Trans amount " + transaction.getAmount());
                    transactionDAO.insertTransaction(transaction , walletAmount);
                } catch ( SQLException e ) {
                    e.printStackTrace();
                }
                break;
            }
        }

//        if ( amount.trim().matches("[0-9]") ) {


//        }
        System.out.println("type " + income);
        System.out.println("category_id " + catId);
        System.out.println("amount " + descrip);

        return "redirect:home";
    }

    @RequestMapping( value = "/transaction", method = RequestMethod.GET )
    public String transactionList(HttpSession session, HttpServletRequest request, HttpServletResponse response, Model model) {

    return "transaction";
    }
}

