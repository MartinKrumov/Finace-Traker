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
		String fname = StringEscapeUtils.escapeHtml(request.getParameter("fname"));
		String sname = StringEscapeUtils.escapeHtml(request.getParameter("sname"));
		String uname = StringEscapeUtils.escapeHtml(request.getParameter("uname"));
		String email = StringEscapeUtils.escapeHtml(request.getParameter("email"));
		String pass = StringEscapeUtils.escapeHtml(request.getParameter("pass"));
		// String username, String password, String email, String firstName,
		// String lastName

		response.setContentType("text/html");
		User user = new User(fname, pass, email, fname, sname);
		String tableCss = "";
		String table = "<table border = 3>" + "<tr>" + "<th>First name</th>" + "<th>Second name</th>"
				+ "<th>User name</th>" + "<th>Email</th>" + "<th>Passworth</th>" + "</th></tr>" + "<tr><td>" + fname
				+ "</td>" + "<td>" + sname + "</td>" + "<td>" + uname + "</td>" + "<td>" + email + "</td>" + "<td>"
				+ pass + "</td></tr>" + "</table>";
		pr.println(table);
		// if (UserDAO.insertUser(user)) {
		// pr.println(fname);
		// pr.println(sname);
		// pr.println(uname);
		// pr.println(email);
		// pr.println(pass);
		// pr.println("INSERTED ");
		// } else {
		// pr.println("TROUBLE ");
		// }
		ResultSet res = UserDAO.selecttUser();
		String table2 = "<table border = 3>" + "<tr><th>First name</th>" + "<th>Second name</th>" + "<th>User name</th>"
				+ "<th>Email</th>" + "<th>Passworth</th>" + "</th></tr><tr>";
		String end = "</table>";
		// sb.append(table2);
		String str;

		try {
			while (res.next()) {
				if (res.getString("username").equals(uname)) {
					session = request.getSession();
					session.setAttribute("username", uname);
					pr.println(session.getAttribute("username") + " te tova e sesia");
					break;
				}
				table2 += ("<tr><td>" + res.getString("id") + "</td>");
				table2 += ("<td>" + res.getString("fname") + "</td>");
				table2 += ("<td>" + res.getString("sname") + "</td>");
				table2 += ("<td>" + res.getString("username") + "</td>");
				table2 += ("<td>" + res.getString("pass") + "</td></tr>");
			}

			table2 += end;
			// sb.append(end);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (session != null) {
			pr.println(request.getSession(false).getAttribute("username") + " dolniq else");
		} else {
			response.sendRedirect("./index.jsp");
			return;
		}
		String logout = "<a href = './logout'>Logout</a>";
		pr.println(logout);
		pr.println(table2);
		// request.setAttribute("result", table2);
		// request.getRequestDispatcher("login.jsp");
		// ((RequestDispatcher) request).forward(request, response);
		// response.setAttribute("result", table);
		// response.getRequestDispatcher("welcome.jsp").forward(request,
		// response);
		// response.setHeader( "result", table2);
		// response.sendRedirect("/Finance-Tracker/login.jsp");

		// HttpServletResponse.setHeader("Location", "/your/new/location.jsp");
		// request.setAttribute("result", user);
		// getServletContext().getRequestDispatcher("/login.jsp").forward(request,
		// response);

		String st = StringEscapeUtils.escapeHtml("<img src=\"\">");
		// pr.println(st);
		// doGet(request, response);
	}

}
