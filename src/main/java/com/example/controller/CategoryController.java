package com.example.controller;

import com.example.model.dao.CategoryDAO;
import com.example.model.pojo.Category;
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
import javax.servlet.http.HttpSession;
import java.sql.SQLException;


@Controller
public class CategoryController {
    @Autowired
    CategoryDAO categoryDAO;

    @RequestMapping( value = "/addcategory", method = RequestMethod.POST )
    public String InsertWallet(HttpServletRequest request, HttpSession session, Model model) {
        User user = ( User ) session.getAttribute("user");
        String name = request.getParameter("name");
        String income = request.getParameter("income");
        Category category = new Category(name, income == null ? TransactionType.EXPENSE : TransactionType.INCOME);
        try {
            categoryDAO.insertCategory(category, user.getUserId());
        } catch ( SQLException e ) {
            e.printStackTrace();
        }
        return "home";
    }
    @RequestMapping( value = "/test", method = RequestMethod.GET )
    public String Inse(HttpServletRequest request, HttpSession session, Model model) {

        return "test";
    }
}
