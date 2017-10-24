package com.servlets.ajax;

import com.dao.classes.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet( "/DelUserFromAdmin" )
public class DelUserFromAdmin extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter pr = response.getWriter();
        Integer user_id = Integer.parseInt(request.getParameter("id"));
        UserDAO.delUser(user_id);
        System.out.println("DELETE");
    }

}
