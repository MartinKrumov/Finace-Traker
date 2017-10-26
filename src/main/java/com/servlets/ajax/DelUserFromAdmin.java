package com.servlets.ajax;

import com.dao.classes.UserDAO;
import com.dao.classes.WalletDAO;

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
        int user_id = Integer.parseInt(request.getParameter("del_user_id"));
        System.out.println("user id to delete " + user_id);
        boolean delWallet = WalletDAO.deleteWallets(user_id);
        boolean delUser = UserDAO.delUser(user_id);
        if ( delWallet && delUser ) {
            System.out.println("DELETED");
        } else {
            System.out.println("No delete ");
        }

    }

}
