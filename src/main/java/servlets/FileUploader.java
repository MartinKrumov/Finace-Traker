package servlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

/**
 * Servlet implementation class FileUploader
 */
public class FileUploader extends FileUtils {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Part filePart = request.getPart("profilpic");
		String path = "";
		FileItemFactory itemfactory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(itemfactory);
		PrintWriter out = response.getWriter();
		if (!ServletFileUpload.isMultipartContent(request)) {
			out.println("Nothing to upload");
			return;
		}
		try {
			List<FileItem> items = upload.parseRequest(request);
			for (FileItem item : items) {

				String contentType = item.getContentType();

				if (!item.getFieldName().equals("profilpic")) {
					out.println("only png format image files supported");
					continue;
				}

				// 10275942_797222240295519_2409051625224068118_n.jpg
				File uploadDir = new File(
						"C:/Program Files(x86)/apache-tomcat-9.0.0.M26/webapps/Finance-Traker/ImageUpload/");
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
				out.println("file name uploadet: " + file.getName());
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
		out.close();
		doGet(request, response);
		
		
		File picpath = new File(
				"C:/Program Files(x86)/apache-tomcat-9.0.0.M26/webapps/Finance-Traker/ImageUpload/");
		File[] pics = picpath.listFiles();
		response.setContentType("text/html");

		for (File f : pics) {
			out.println("<img src = '../../ImageUpload/" + f.getName() + "'height='42' width='42' >");
		}
	}

}
