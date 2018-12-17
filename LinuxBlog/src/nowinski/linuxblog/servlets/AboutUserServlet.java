package nowinski.linuxblog.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nowinski.linuxblog.dao.ThreadDAO;
import nowinski.linuxblog.dao.UserDAO;
import nowinski.linuxblog.entities.Thread;
import nowinski.linuxblog.entities.User;

/**
 * Servlet implementation class AboutUserServlet
 */
@WebServlet("/user")
public class AboutUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer userId = Integer.parseInt(request.getParameter("id"));
		UserDAO userDAO = (UserDAO) request.getAttribute("userDAO");
		ThreadDAO threadDAO = (ThreadDAO) request.getAttribute("threadDAO");
		User user = userDAO.getUser(userId);
		List<Thread> userThreads = threadDAO.getUserThreads(user.getId());
		request.setAttribute("userObj", user);
		request.setAttribute("userThreads", userThreads);
		request.getRequestDispatcher("/WEB-INF/view/aboutUser.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
