package com.example.controller;

import com.example.model.dao.UserDAO;
import com.example.model.dao.WalletDAO;
import com.example.model.pojo.User;
import com.example.model.pojo.Wallet;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Set;

@Controller
//@SessionAttributes("user")
public class UserController {
    @Autowired
    UserDAO userDAO;
    @Autowired
    WalletDAO walletDAO;
    @RequestMapping( value = "/login", method = RequestMethod.POST )
    public String loginUser(@ModelAttribute User user, HttpSession session) {

        if ( user.getEmail() != null ) {
            user = userDAO.login(user.getEmail(), "", user.getPassword());
            session.setAttribute("user", user);
        } else if ( user.getUsername() != null ) {
            user = userDAO.login("", user.getUsername(), user.getPassword());
        }
        if ( user != null || user.getUserId() != 0 ) {
            session.setAttribute("user", user);
        }
        if ( user.getUsername() == null ) {
            return "redirect:index";
        }

        Gson json = new Gson();
        String userjson = json.toJson(user);
//        model.addAttribute("wallets", wallets);
        System.out.println(userjson);
        return "home";
    }

    @RequestMapping( value = "/login", method = RequestMethod.GET )
    public String loginGet(HttpSession session , Model model) {
        User user = ( User ) session.getAttribute("user");
        if ( user != null ) {
            System.out.println(user.getUserId());
            Set< Wallet > wallets = walletDAO.selectUserWallets(user.getUserId());
            model.addAttribute("wallets", wallets);
            return "redirect:home";
        }
        return "redirect:index?error=errorLogin";
    }
    @RequestMapping( value = "/home", method = RequestMethod.GET )
    public String homeGet(HttpSession session,Model model) {
        User user = ( User ) session.getAttribute("user");
//        System.out.println("USER ID GET: "+user.getUserId());
        if ( user != null ) {
            System.out.println("in the wallets");
                System.out.println(user.getUserId());
                Set< Wallet > wallets = walletDAO.selectUserWallets(user.getUserId());
                model.addAttribute("wallets", wallets);
            return "home";
        }
        return "redirect:index?error=errorLogin";
    }

    @RequestMapping( value = "/home", method = RequestMethod.POST )
    public String homePost(HttpSession session) {

        User user = ( User ) session.getAttribute("user");
        System.out.println("USER ID POST: "+user.getUserId());
        if ( user.getUsername() != null ) {
            return "home";
        }
        return "index";
    }
}
