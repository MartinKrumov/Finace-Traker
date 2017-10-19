package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringEscapeUtils;

import dao.classes.UserDAO;
import model.User;

/**
 * Servlet implementation class loginServlet
 */
@WebServlet("/loginsss")
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
		long userID =-10;
		if(uname != null && !uname.isEmpty()){
			userID = UserDAO.loginCheckByUserName(uname, pass);
		}else if(email != null && !email.isEmpty()){
			userID = UserDAO.loginCheckByEmail(email, pass);
		}
		if (userID > 0) {
			request.getSession().setAttribute("email", email);
			request.getSession().setAttribute("id", (long) userID);
			pr.println("OK");
			response.sendRedirect("./index.jsp");
		} else {
			response.sendRedirect("./index.html?error=loginerror");
		}
		// response.setContentType("text/html");

	}

}
