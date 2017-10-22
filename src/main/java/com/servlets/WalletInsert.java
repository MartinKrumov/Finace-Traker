package com.servlets;

import com.dao.classes.WalletDAO;
import com.model.Wallet;
import org.apache.commons.lang.StringEscapeUtils;
import java.math.BigDecimal;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet( "/wallet" )
public class WalletInsert extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter pr = response.getWriter();
        String walletName = new String(StringEscapeUtils.escapeHtml(request.getParameter("wallet_name")));
        String option = new String(StringEscapeUtils.escapeHtml(request.getParameter("categories")));
        String checkbox = new String(StringEscapeUtils.escapeHtml(request.getParameter("check")));
        pr.println(walletName);
        pr.println(option);
        pr.println( checkbox== null ? "null": checkbox);
        Long sessionUserID = (Long) request.getSession(false).getAttribute("user_id");
        Wallet wallet = new Wallet(walletName,BigDecimal.valueOf(0).movePointLeft(2),sessionUserID);
        Long wallet_id =WalletDAO.insertWallet(wallet);
        request.getSession(false).setAttribute("wallet_id",wallet_id);
        pr.println(request.getSession(false).getAttribute("wallet_id"));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
