package com.example.controller;

import com.example.model.dao.UserDAO;
import com.example.model.pojo.Wallet;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.example.model.pojo.User;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

//@SessionAttributes()
@Controller
//@Scope("session")
public class LoginSignUpController {
    @Autowired
    UserDAO userDAO;

    @RequestMapping( value = "/index", method = RequestMethod.GET )
    public String sayHello( Model model) {
//        model.addAttribute("user",new User());
        return "index";
    }

    @RequestMapping( value = "/signup", method = RequestMethod.POST )
    public String signupUser(@ModelAttribute User user, HttpSession session ,Model model) {
        if ( user.getPassword() == null ) {
            return "redirect:index";
        }

        if ( !userDAO.checkIfExists(user.getEmail()) ) {
            user.setProfilePic("a");
            user.setRights(3);
            user.setUserId(userDAO.insertUser(user));
            user.setBlocked(0);
            System.out.println(user.getUserId());
            System.out.println(user.getEmail());
            System.out.println(user.getRights());
            Gson json = new Gson();
            String userjson = json.toJson(user);
            System.out.println(userjson);
        }
        if ( user != null || user.getUserId() != 0 ) {
            session.setAttribute("user", user);
            Set< Wallet > wallets = new TreeSet<>();
            model.addAttribute("wallets", wallets);
        }
        return "redirect:home";
    }



}
