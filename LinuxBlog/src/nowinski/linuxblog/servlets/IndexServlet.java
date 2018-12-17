package nowinski.linuxblog.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nowinski.linuxblog.dao.ThreadDAO;
import nowinski.linuxblog.entities.Thread;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/home")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int threadsOnPage = 4;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ThreadDAO threadDAO = (ThreadDAO) request.getAttribute("threadDAO");
		Integer pageNumber = null;
		try {
			pageNumber = Integer.parseInt(request.getParameter("p"));
		} catch (NumberFormatException ex) {
			pageNumber = 0;
		}
		pageNumber--;
		if (pageNumber < 0)
			pageNumber = 0;
		
		Long pages = 0L;
		if ((pages = threadDAO.getThreadCount() / threadsOnPage) != 0)
			pages++;
		if (pages == 0)
			pages = 1L;

		List<Thread> threads = threadDAO.getThreads(pageNumber * threadsOnPage, threadsOnPage);
		request.setAttribute("threads", threads);
		request.setAttribute("allThreads", pages);
		request.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
