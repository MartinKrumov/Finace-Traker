package com.example.controller;

import org.apache.tomcat.util.net.jsse.openssl.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class LogoutController {

    @RequestMapping( value = "/logout"  )
    public String logout(HttpSession session, HttpServletResponse response, HttpServletRequest request, Model model) throws ServletException, IOException {
        session.removeAttribute("user");
        session.invalidate();
        response.setHeader("Pragma", "No-cache");
        response.setDateHeader("Expires", -1);
        response.setHeader("Cache-Control", "no-cache");
        response.setContentType("text/html");
        Cookie[] cookies = request.getCookies();
        if ( cookies != null ) for ( Cookie cookie : cookies ) {
            cookie.setValue("");
            cookie.setPath("/");
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }
//        if ( model.containsAttribute("user") ) model.asMap().remove("user");

        return "redirect:home";
    }


}
