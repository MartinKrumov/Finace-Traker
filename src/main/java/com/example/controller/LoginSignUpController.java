package com.example.controller;

import com.example.model.dao.UserDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.example.model.pojo.User;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

//@SessionAttributes()
@Controller
//@Scope("session")
public class LoginSignUpController {
    @RequestMapping( value = "/index", method = RequestMethod.GET )
    public String sayHello(Model model) {
        model.addAttribute("user",new User());
        return "index";
    }

    @RequestMapping( value = "/signup", method = RequestMethod.POST )
    public String signupUser(@ModelAttribute User user,  HttpServletRequest request) {
        if ( user == null ) {
            return "index";
        }
        if ( !UserDAO.checkIfExists(user.getEmail()) ) {
            user.setProfilePic("a");
            user.setRights(3);
            user.setUserId(UserDAO.insertUser(user));
            user.setBlocked(0);
            request.getSession().setAttribute("user_id", user.getUserId());
            request.getSession(false).setAttribute("user_email", user.getEmail());
            request.getSession(false).setAttribute("username", user.getUsername());
            request.getSession(false).setAttribute("user_rights", user.getRights());
            request.getSession(false).setAttribute("user_blocked", user.getBlocked());
        }
        return "home";
    }

    @RequestMapping( value = "/logout" )
    public String logout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if ( request.getSession(false) != null ) {
            request.getSession().setAttribute("id", null);
            request.getSession().setAttribute("email", null);
            request.getSession().invalidate();
            response.setHeader("Pragma", "No-cache");
            response.setDateHeader("Expires", -1);
            response.setHeader("Cache-Control", "no-cache");
            response.setContentType("text/html");
            Cookie[] cookies = request.getCookies();
            if ( cookies != null )
                for ( Cookie cookie : cookies ) {
                    cookie.setValue("");
                    cookie.setPath("/");
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            if ( request.getSession(false) == null ) {
                return "index";
            }
        } else {
            return "index";
        }
        return "index";
    }

}
