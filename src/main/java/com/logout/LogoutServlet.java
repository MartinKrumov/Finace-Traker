package com.logout;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LogoutServlet
 */
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getSession(false) != null) {
			request.getSession().setAttribute("id", null);
			request.getSession().setAttribute("email", null);
			request.getSession().invalidate();
			response.setHeader("Pragma", "No-cache");
			response.setDateHeader("Expires", -1);
			response.setHeader("Cache-Control", "no-cache");
			response.setContentType("text/html");
			Cookie[] cookies = request.getCookies();
			// JSESSIONID
			if (cookies != null)
				for (Cookie cookie : cookies) {
//					cookie.getName().equals("JSESSIONID");
					
					cookie.setValue("");
					cookie.setPath("/");
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}
			if (request.getSession(false) == null) {
				response.sendRedirect("./index.jsp");
			}
		} else {
			response.sendRedirect("./index.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
