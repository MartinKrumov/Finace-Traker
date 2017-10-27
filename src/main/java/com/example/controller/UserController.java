package com.example.controller;

import com.example.model.dao.UserDAO;
import com.example.model.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
//@SessionAttributes("user")
public class UserController {

//    @RequestMapping( value = "/index", method = RequestMethod.GET )
//    public String sayHello(@ModelAttribute User user) {
////        model.addAttribute("kekst", "Hi !");
//        System.out.println(user.getUsername());
//        System.out.println(user.getPassword());
//        return "index";
//    }
//
//    @RequestMapping( value = "/lowindex", method = RequestMethod.POST )
//    @ModelAttribute( "user" )
//    public String user(@ModelAttribute User user) {
////        model.addAttribute( new User());
//        System.out.println(user.getUsername());
//        System.out.println(user.getPassword());
//        return "test";
//    }
@RequestMapping( value = "/login", method = RequestMethod.POST )
public String loginUser(@ModelAttribute User user, HttpServletRequest request) {

    System.out.println(user.getEmail());
    System.out.println(user.getUsername());
    if ( user.getEmail() != null ) {
        user = UserDAO.login(user.getEmail(), "", user.getPassword());
    } else if ( user.getUsername() != null ) {
        user = UserDAO.login("", user.getUsername(), user.getPassword());
    }

    if ( user == null ) {
        return "redirect:index";
    }
    System.out.println(user.getEmail());
    System.out.println(user.getUsername());


    HttpSession session = request.getSession();
    session.setAttribute("user_id", 2);
    session.setAttribute("user_email", user.getEmail());
    session.setAttribute("username", user.getUsername());
    session.setAttribute("user_rights", user.getRights());
    session.setAttribute("user_blocked", user.getBlocked());
    return "home";
}
}
