package ru.sibers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ru.sibers.core.Count;
import ru.sibers.core.News;
import ru.sibers.core.Portal;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet(urlPatterns = { "/index.html", "/readServlet" })
public class ReadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReadServlet() {
		super();

	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<News> news = null;
		String startStr = request.getParameter("start");
		String countStr = request.getParameter("count");
		Count count = null;
		int pageNumber = 0;
		int start = 0;
		if (countStr != null && !countStr.isEmpty()) {
			System.out.println("count=" + countStr);
			count = Count.getValue(countStr);
		}
		if (count == null) {
			count = Count.TEN;
		}
		if (startStr != null && !startStr.isEmpty()) {
			System.out.println("start " + startStr);
			try {
				start = Integer.parseInt(startStr);

			} catch (NumberFormatException e) {
				start = 0;
				e.printStackTrace();
			}
		}
		if(start==0) {
			start = 1;
		}
		
		start = (start - 1) * count.getCount();
		
		try {
			news = Portal.getPortal().getNews(start, count);
			
			int countOfnotes = Portal.getPortal().getCount();
			int countOnPage = count.getCount();
			pageNumber = countOfnotes / countOnPage;
			if(countOfnotes % countOnPage > 0 || pageNumber == 0) {				
				pageNumber++;
			}
			
		} catch (ClassNotFoundException | SQLException e1) {

			e1.printStackTrace();
		}
		String html = "";
		if (news != null) {

			for (News e : news) {
				html += e.html();
			}
		}
		request.setAttribute("html", html);
		request.setAttribute("page", pageNumber);
		request.getRequestDispatcher("index.jsp").forward(request, response);
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(html);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		processRequest(request, response);
	}

}
