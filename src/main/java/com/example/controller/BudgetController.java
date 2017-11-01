package com.example.controller;

import com.example.model.dao.*;
import com.example.model.pojo.Budget;
import com.example.model.pojo.Category;
import com.example.model.pojo.User;
import com.example.model.pojo.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Controller
public class BudgetController {

    @Autowired
    private CategoryDAO categoryDao;

    @Autowired
    BudgetDAO budgetDao;

    @Autowired
    private WalletDAO walletDAO;

    @Autowired
    private BudgetsHasTransactionsDAO budgetsHasTransactionsDAO;

    @Autowired
    private TransactionDAO transactionDAO;

    @RequestMapping(value ="/addBudget", method = RequestMethod.POST)
    public String addBudget(HttpServletRequest request, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");



        return "redirect:/budgets";
    }
}
