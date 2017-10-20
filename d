[1mdiff --cc src/main/java/servlets/SignUpServlet.java[m
[1mindex d7ce7db,dcee7c0..0000000[m
[1m--- a/src/main/java/servlets/SignUpServlet.java[m
[1m+++ b/src/main/java/servlets/SignUpServlet.java[m
[36m@@@ -28,21 -28,20 +28,25 @@@[m [mpublic class SignUpServlet extends Http[m
  			throws ServletException, IOException {[m
  [m
  		PrintWriter pr = response.getWriter();[m
[31m- 		String fname = new String(StringEscapeUtils.escapeHtml(request.getParameter("fname")));[m
[31m- 		String lname = new String(StringEscapeUtils.escapeHtml(request.getParameter("lname")));[m
[32m+ 		String firstName = new String(StringEscapeUtils.escapeHtml(request.getParameter("fname")));[m
[32m+ 		String lastName = new String(StringEscapeUtils.escapeHtml(request.getParameter("lname")));[m
  		String email = new String(StringEscapeUtils.escapeHtml(request.getParameter("email")));[m
[31m- 		String pass = new String(StringEscapeUtils.escapeHtml(request.getParameter("pass")));[m
[31m- 		String uname = new String(StringEscapeUtils.escapeHtml(request.getParameter("uname")));[m
[32m+ 		char[] pass = StringEscapeUtils.escapeHtml(request.getParameter("pass")).toCharArray();[m
[32m+ 		String username = new String(StringEscapeUtils.escapeHtml(request.getParameter("uname")));[m
  [m
[31m- 		// byte[] file =request.getParameter("profilpic").getBytes();[m
  		pr.println(request.getParameter("fname"));[m
[31m- 		pr.println(lname);[m
[32m+ 		pr.println(lastName);[m
  		pr.println(email);[m
  		pr.println(pass);[m
[31m- 		pr.println(uname);[m
[32m+ 		pr.println(username);[m
  [m
[32m++<<<<<<< HEAD[m
[32m +		if (!UserDAO.checkIfExists(email)) {[m
[32m +			User user = new User(fname, lname, email, pass);[m
[32m++=======[m
[32m+ 		if (!UserDAO.checkIfExists(email) && request.getSession(false) == null) {[m
[32m+ 			User user = new User(username, pass, email, firstName, lastName);[m
[32m++>>>>>>> fb39693ed21afe21898db49cd17357eefb46dd01[m
  			PrintWriter out = response.getWriter();[m
  [m
  			long userID = UserDAO.insertUser(user);[m
