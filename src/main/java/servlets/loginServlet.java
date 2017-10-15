package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringEscapeUtils;

import dao.classes.UserDAO;
import model.User;

/**
 * Servlet implementation class loginServlet
 */
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public loginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter pr = response.getWriter();
		String fname = StringEscapeUtils.escapeHtml(request.getParameter("fname"));
		String sname = StringEscapeUtils.escapeHtml(request.getParameter("sname"));
		String uname = StringEscapeUtils.escapeHtml(request.getParameter("uname"));
		String email = StringEscapeUtils.escapeHtml(request.getParameter("email"));
		String pass = StringEscapeUtils.escapeHtml(request.getParameter("pass"));
//		String username, String password, String email, String firstName, String lastName
		User user = new User(fname,pass,email,fname,sname);
		if(UserDAO.insertUser(user)){
			pr.println(fname);
			pr.println(sname);
			pr.println(uname);
			pr.println(email);
			pr.println(pass);
			pr.println("INSERTED ");
		}else{
			pr.println("TROUBLE ");

		}
		
		

		String st = StringEscapeUtils.escapeHtml("sdf");
		doGet(request, response);
	}

}
