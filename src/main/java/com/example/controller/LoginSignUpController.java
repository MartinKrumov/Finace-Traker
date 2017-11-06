package com.example.controller;

import com.example.model.dao.UserDAO;
import com.example.model.pojo.Wallet;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.example.model.pojo.User;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.validation.Valid;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Set;
import java.util.TreeSet;

//@SessionAttributes()
@Controller
//@Scope("session")
public class LoginSignUpController {
    @Autowired
    private UserDAO userDAO;

/**-------------------------------------------------------------------------------------------------------------------*/
    @RequestMapping(value="/index", method=RequestMethod.GET)
    public String getRegister(Model m) {
        User user = new User();

        m.addAttribute("user", user);

        return "index";
    }

    @RequestMapping(value="/signup", method=RequestMethod.POST)
    public String register(HttpSession session, Model viewModel, @Valid @ModelAttribute("user") User user, BindingResult bindingResult){

        if (bindingResult.hasErrors()) {
            viewModel.addAttribute("index", "Could not create profile. Please, enter a valid username!");

            User u = new User();
            viewModel.addAttribute("user", u);

            return "index";
        }

        try {
            if (!userDAO.checkIfExists(user.getEmail())) {
                user.setRights(3);
                user.setUserId(userDAO.insertUser(user));
                user.setBlocked(0);

                session.setAttribute("user", user);

                Gson json = new Gson();
                String userjson = json.toJson(user);
                System.out.println(userjson);
                if ( (user.getUserId() > 0) ) {
                    Set< Wallet > wallets = new TreeSet<>();
                    viewModel.addAttribute("wallets", wallets);
                }

                return "redirect:home";
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return "error500";
        }
        return "index";
    }
}
