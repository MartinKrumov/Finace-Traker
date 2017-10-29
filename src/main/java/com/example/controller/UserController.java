package com.example.controller;

import com.example.model.dao.UserDAO;
import com.example.model.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
//@SessionAttributes("user")
public class UserController {
    @Autowired
    UserDAO userDAO;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginUser(@ModelAttribute User user, HttpSession session) {

        if (user.getEmail() != null) {
            user = userDAO.login(user.getEmail(), "", user.getPassword());
        } else if (user.getUsername() != null) {
            user = userDAO.login("", user.getUsername(), user.getPassword());
        }

        if (user == null) {
            return "redirect:index";
        }

        session.setAttribute("user", user);
        return "home";
    }
}
