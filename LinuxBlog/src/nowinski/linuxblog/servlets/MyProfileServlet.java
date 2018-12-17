package nowinski.linuxblog.servlets;

import java.util.List;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nowinski.linuxblog.dao.ThreadDAO;
import nowinski.linuxblog.entities.Thread;
import nowinski.linuxblog.entities.User;

/**
 * Servlet implementation class MyProfileServlet
 */
@WebServlet("/myProfile")
public class MyProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		if (user != null) {
			ThreadDAO threadDAO = (ThreadDAO) request.getAttribute("threadDAO");
			List<Thread> userThreads = threadDAO.getUserThreads(user.getId());
			request.setAttribute("userThreads", userThreads);
			request.getRequestDispatcher("/WEB-INF/view/myProfile.jsp").forward(request, response);
		} else {
			response.sendRedirect(request.getContextPath() + "/home");
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
