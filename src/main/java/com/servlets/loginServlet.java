package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringEscapeUtils;

import com.dao.classes.UserDAO;

/**
 * Servlet implementation class loginServlet
 */
//@WebServlet("/loginsss")
public class loginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private HttpSession session;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter pr = response.getWriter();
        String uname = new String(StringEscapeUtils.escapeHtml(request.getParameter("uname")));
        String email = new String(StringEscapeUtils.escapeHtml(request.getParameter("email")));
        String pass = new String(StringEscapeUtils.escapeHtml(request.getParameter("pass")));
        pr.println(uname);
        pr.println(email);
        pr.println(pass);
//		loginCheckByUserName(
        long userID = -1;
        boolean unameLoginCheck = false;
        long[] futureVal = new long[2];
        if ( uname != null && !uname.isEmpty() ) {
            System.out.println("user name login");
            futureVal = UserDAO.loginCheckByUserName(uname, pass);
            System.out.println(" userID " + futureVal[0]);
            unameLoginCheck = true;
        } else if ( email != null && !email.isEmpty() ) {
            System.out.println("email login");
            futureVal = UserDAO.loginCheckByEmail(email, pass);
            System.out.println(" userID " + futureVal[0]);
        }
        if ( futureVal [0] > 0 && !unameLoginCheck ) {
            request.getSession().setAttribute("email", email);
            request.getSession(false).setAttribute("user_id", ( long ) futureVal[0]);
            request.getSession(false).setAttribute("rights", ( long ) futureVal[1]);
            pr.println("OK");
            response.sendRedirect("./index.jsp");
        } else if ( futureVal [0] > 0 && unameLoginCheck ) {
            request.getSession().setAttribute("email", uname);
            request.getSession(false).setAttribute("user_id", ( long ) futureVal[0]);
            request.getSession(false).setAttribute("rights", ( long ) futureVal[1]);
            pr.println("OK");
            response.sendRedirect("./index.jsp");
        } else {
            response.sendRedirect("./index.html?error=loginerror");
        }
        // response.setContentType("text/html");

    }

}
