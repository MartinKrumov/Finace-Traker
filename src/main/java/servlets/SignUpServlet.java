package servlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringEscapeUtils;

import model.User;

/**
 * Servlet implementation class SignUpServlet
 */
@WebServlet("/signups")
//@javax.servlet.annotation.MultipartConfig
@MultipartConfig
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter pr = response.getWriter();
		String fname = StringEscapeUtils.escapeHtml(request.getParameter("fname"));
		String lname = StringEscapeUtils.escapeHtml(request.getParameter("lname"));
		String email = StringEscapeUtils.escapeHtml(request.getParameter("email"));
		String pass = StringEscapeUtils.escapeHtml(request.getParameter("pass"));
		// byte[] file =request.getParameter("profilpic").getBytes();
		pr.println(request.getParameter("fname"));
		pr.println(lname);
		pr.println(email);
		pr.println(pass);
		pr.println("dsfvdvdfsv");
		Part filePart = request.getPart("profilpic");
		
		// if (!UserDAO.checkIfExists(email)) {
		User user = new User(fname, lname, email, pass);
		PrintWriter out = response.getWriter();
//		MultipartFormDataRequest mrequest;
		 if (!ServletFileUpload.isMultipartContent(request)) {
		 out.println("Nothing to upload");
		 return;
		 }
		 String path="";
		 FileItemFactory itemfactory = new DiskFileItemFactory();
		 ServletFileUpload upload = new ServletFileUpload(itemfactory);
		 try {
		 List<FileItem> items = upload.parseRequest( request);
		 for (FileItem item : items) {
			 
		 String contentType = item.getContentType();
		
		 if (!item.getFieldName().equals("profilpic")) {
		 out.println("only png format image files supported");
		 continue;
		 }
		
		 // 10275942_797222240295519_2409051625224068118_n.jpg
		 File uploadDir = new File( "C:/Program Files(x86)/apache-tomcat-9.0.0.M26/webapps/Finance-Traker/ImageUpload/");
		 // if(!uploadDir.exists()){
		 // uploadDir.createNewFile();
		 // }
		 out.println("name: " + item.getName());
		 out.println("size: " + item.getSize());
		 out.println("formfield: " + item.isFormField());
		 out.println("Content type: " + item.getContentType());
		 // out.println("name: "+item.);
		
		 File file = File.createTempFile(item.getFieldName(), ".jpg", uploadDir);
		 item.write(file);
		 out.println("file name uploadet: "+file.getName());
		 out.println("File Saved Successfully");
		 path = file.getName();
		 }
		 } catch (FileUploadException e) {
		 out.println(e.getMessage());
		 out.println("upload fail");
		 } catch (Exception ex) {
		 out.println(ex.getMessage());
		 ex.printStackTrace();
		 out.println("can't save");
		 }
		// File picpath = new File(
		// "C:/Program Files
		// (x86)/apache-tomcat-9.0.0.M26/webapps/Finance-Traker/ImageUpload/");
		// File[] pics = picpath.listFiles();
		// response.setContentType("text/html");
		//
		// for(File f : pics){
		// out.println("<img src = '../../ImageUpload/"+f.getName()+"'
		// height='42' width='42' >");
		// }

		// long userID = UserDAO.insertUser(user, path);
		// request.getSession().setAttribute("email", email);
		// request.getSession().setAttribute("id", (long) userID);
		// response.sendRedirect("./index.jsp");
		// } else {
		// response.sendRedirect("./index.html?error=userEmailExists");
		// }
		// response.setContentType("text/html");
		out.close();
	}
}