package ru.sibers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import ru.sibers.core.Portal;

/**
 * Servlet implementation class LoadServlet
 */
@MultipartConfig
@WebServlet("/loadServlet")
public class LoadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoadServlet() {
		super();
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		
		
		String title = request.getParameter("title").replace("\"", "").replace("'", "").trim(); 
		String body = request.getParameter("body").replace("\"", "").replace("'", "").trim();
		String description = request.getParameter("description").replace("\"", "").replace("'", "").trim();
		Part filePart = request.getPart("file"); 
		Portal s = Portal.getPortal();

		String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); 
		if (!fileName.isEmpty()) {
			InputStream is = filePart.getInputStream();
			String path = getServletContext().getRealPath("/");
			File f = new File(path + File.separator + "image");
			if (!f.exists())
				f.mkdir();
			fileName = f.getName() + new Date().getTime() + " " + "_new.jpg";
			File savedFile = new File(f.getPath() + File.separator + fileName);
			FileOutputStream fos = new FileOutputStream(savedFile);
			int x = 0;
			byte[] b = new byte[1024];
			while ((x = is.read(b)) != -1) {
				fos.write(b, 0, x);
			}
			fos.flush();
			
			try {
				s.addNews(title, body, "image/"+ fileName, description);
			} catch (ClassNotFoundException | SQLException e) {
				
				e.printStackTrace();
			}
		
			
			
			
		} else {
			try {
				s.addNews(title, body);
			} catch (ClassNotFoundException | SQLException e) {
				
				e.printStackTrace();
			}
			catch(IllegalArgumentException e) {
				request.setAttribute("error", "Неправильные данные");
				request.getRequestDispatcher("index.html").forward(request, response);
			}
		}
		
		response.sendRedirect("index.html");
	}
}
