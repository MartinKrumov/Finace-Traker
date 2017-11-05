package com.example.controller;

import com.example.model.dao.*;
import com.example.model.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
public class BudgetController {

    @Autowired
    private CategoryDAO categoryDao;

    @Autowired
    private BudgetDAO budgetDao;

    @Autowired
    private WalletDAO walletDAO;

    @RequestMapping(value = "/addBudget", method = RequestMethod.GET)
    public String createBudget(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");

        try {
            Set<Wallet> wallets = walletDAO.getAllWalletsByUserId(user.getUserId());
            Set<String> categories = categoryDao.getAllCategoriesByType(user.getUserId(),0);

            model.addAttribute("wallets", wallets);
            model.addAttribute("categories", categories);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return "error500";
        }

        return "addBudget";
    }

    @RequestMapping(value = "/addBudget", method = RequestMethod.POST)
    public String addBudget(HttpServletRequest request, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");

        try {
            String name = request.getParameter("name");
            Wallet wallet = walletDAO.getWalletByUserIDAndWalletName(user.getUserId(), request.getParameter("wallet"));
            Category category = categoryDao.getCategoryByName(request.getParameter("category"));
            BigDecimal amount = new BigDecimal(request.getParameter("amount"));
            String date = request.getParameter("date");

            

            String[] inputDate = date.split("-");

            int monthTo = Integer.valueOf(inputDate[1]);

            int dayOfMonthTo = Integer.valueOf(inputDate[2]);

            int yearTo = Integer.valueOf(inputDate[0]);

            LocalDateTime dateFrom = LocalDateTime.now();
            LocalDateTime dateTo = LocalDateTime.of(yearTo, monthTo, dayOfMonthTo, 0, 0, 0);

            if (dateFrom.isAfter(dateTo) && name != null && !name.isEmpty()) {
                Set<Wallet> wallets = walletDAO.getAllWalletsByUserId(user.getUserId());
                Set<String> categories = categoryDao.getAllCategoriesByType(user.getUserId(),0);

                model.addAttribute("wallets", wallets);
                model.addAttribute("categories", categories);

                System.err.println("Enter valid date: The date must be after today's date :)");
                return "addBudget";
            }

            Budget b = new Budget(name.trim(), amount, BigDecimal.valueOf(0), dateFrom, dateTo, wallet.getWalletId(), category.getCategoryId(), new ArrayList<>());

            budgetDao.insertBudget(b);

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
            return "error500";
        }

        return "redirect:/budgets";
    }

    @RequestMapping(value = "/budgets", method = RequestMethod.GET)
    public String showBudgets(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        Set<Budget> allBudgets = new HashSet<>();

        try {
            allBudgets = budgetDao.getAllBudgetsByUserId(user.getUserId());
            model.addAttribute("budgets", allBudgets);

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
//            return "error500";
        }
        return "budgets";
    }

    @RequestMapping(value = "/budget/{budgetId}", method = RequestMethod.GET)
    public String showBudgets(@PathVariable("budgetId") Long budgetId, Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        try {
            Budget b = budgetDao.getBudgetById(budgetId);
            List<Transaction> transactions = b.getTransactions();

            for (Transaction t: transactions) {
                System.out.println(t.getDescription());
            }
            model.addAttribute("transactions", transactions);


        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
            //return "error500";
        }
        return  "budgetTransactions";
    }
}
