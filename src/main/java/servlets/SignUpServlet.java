package servlets;

import java.io.File;
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
// @javax.servlet.annotation.MultipartConfig
// @MultipartConfig
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter pr = response.getWriter();
		String fname = new String(StringEscapeUtils.escapeHtml(request.getParameter("fname")));
		String lname = new String(StringEscapeUtils.escapeHtml(request.getParameter("lname")));
		String email = new String(StringEscapeUtils.escapeHtml(request.getParameter("email")));
		String pass = new String(StringEscapeUtils.escapeHtml(request.getParameter("pass")));
		String uname = new String(StringEscapeUtils.escapeHtml(request.getParameter("uname")));

		// byte[] file =request.getParameter("profilpic").getBytes();
		pr.println(request.getParameter("fname"));
		pr.println(lname);
		pr.println(email);
		pr.println(pass);
		pr.println(uname);

		if (!UserDAO.checkIfExists(email) && request.getSession(false) == null) {
			User user = new User(fname, lname, email, pass);
			PrintWriter out = response.getWriter();

			long userID = UserDAO.insertUser(user);
			request.getSession().setAttribute("email", email);
			request.getSession().setAttribute("id", (long) userID);
			if (request.getSession(false) != null) {
				response.sendRedirect("./index.jsp");
			}
			out.close();
		} else {
			response.sendRedirect("./index.html?error=userEmailExists");
		}
		response.setContentType("text/html");

	}
}
