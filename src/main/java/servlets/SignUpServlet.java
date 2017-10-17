package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringEscapeUtils;

import dao.classes.UserDAO;
import model.User;

/**
 * Servlet implementation class SignUpServlet
 */
@WebServlet("/signups")
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter pr = response.getWriter();
		String fname = StringEscapeUtils.escapeHtml(request.getParameter("fname"));
		String lname = StringEscapeUtils.escapeHtml(request.getParameter("lname"));
		String email = StringEscapeUtils.escapeHtml(request.getParameter("email"));
		String pass = StringEscapeUtils.escapeHtml(request.getParameter("pass"));
		if (!UserDAO.checkIfExists(email)) {
			User user = new User(fname, lname, email, pass);
			long userID = UserDAO.insertUser(user);
			request.getSession().setAttribute("username", email);
			request.getSession().setAttribute("id", (long) userID);
			response.sendRedirect("./index.jsp");
		} else {
			response.sendRedirect("./index.html?error=userEmailExists");
		}
		response.setContentType("text/html");
		pr.println(fname);
		pr.println(lname);
		pr.println(email);
		pr.println(pass);

	}

}
