package com.example.controller;

import com.example.model.dao.*;
import com.example.model.pojo.Budget;
import com.example.model.pojo.Category;
import com.example.model.pojo.User;
import com.example.model.pojo.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
    private BudgetDAO budgetDao;

    @Autowired
    private WalletDAO walletDAO;

    @Autowired
    private BudgetsHasTransactionsDAO budgetsHasTransactionsDAO;

    @Autowired
    private TransactionDAO transactionDAO;

    @RequestMapping(value = "/addBudget", method = RequestMethod.GET)
    public String createBudget(Model model) {
        Budget b = new Budget();
        model.addAttribute("budget", b);
        return "addBudget";
    }

    @RequestMapping(value ="/addBudget", method = RequestMethod.POST)
    public String addBudget(@ModelAttribute Budget budget, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");

        try {
            model.addAttribute("emp", budget);
            budgetDao.insertBudget(budget);
        } catch (SQLException e) {
            System.err.println("=========Failled=========");
            e.printStackTrace();
        }


        return "redirect:/budgets";
    }
}
