package com.example.controller;

import com.example.model.dao.UserDAO;
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

//@SessionAttributes()
@Controller
//@Scope("session")
public class LoginSignUpController {
    @Autowired
    UserDAO userDAO;

    @RequestMapping( value = "/index", method = RequestMethod.GET )
    public String sayHello(@ModelAttribute User user,Model model) {
        model.addAttribute("user",new User());
        return "index";
    }

    @RequestMapping( value = "/signup", method = RequestMethod.POST )
    public String signupUser(@ModelAttribute User user,  HttpSession session) {
        if ( user == null ) {
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
            String userjson= json.toJson(user);
            System.out.println(userjson);
            session.setAttribute("user", user);
        }
        return "home";
    }

    @RequestMapping( value = "/logout" )
    public String logout(HttpServletRequest request, HttpServletResponse response ,Model model)
            throws ServletException, IOException {
       if( model.containsAttribute("user")){
            model.addAttribute("user",null);
        }
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
        }
        return "redirect:index";
    }

}
