package nowinski.linuxblog.servlets;

import java.io.IOException;
import java.io.OutputStream;

import javax.persistence.NoResultException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nowinski.linuxblog.dao.ThreadDAO;
import nowinski.linuxblog.dao.UserDAO;
import nowinski.linuxblog.entities.User;
import nowinski.linuxblog.entities.Thread;

/**
 * Servlet implementation class ImageServlet
 */
@WebServlet("/imageServlet")
public class ImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String type = null;
		Integer id = null;
		try {
			type = request.getParameter("type");
			id = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException ex) {
			response.sendRedirect(request.getContextPath() + "/home");
			return;
		}
		OutputStream o = response.getOutputStream();
		switch (type) {
		case "user":
			UserDAO userDAO = (UserDAO) request.getAttribute("userDAO");
			User user = null;
			try {
				user = userDAO.getUser(id);
				response.setContentType("image/png");
				o.write(user.getAvatar());
				o.flush();
				o.close();
			} catch (NoResultException ex) {
				response.sendRedirect(request.getContextPath() + "/home");
				return;
			}
			break;
		case "thread":
			ThreadDAO threadDAO = (ThreadDAO) request.getAttribute("threadDAO");
			Thread thread = null;
			try {
				thread = threadDAO.getThread(id);
				response.setContentType("image/png");
				o.write(thread.getAvatar());
				o.flush();
				o.close();
			} catch (NoResultException ex) {
				response.sendRedirect(request.getContextPath() + "/home");
				return;
			}
			break;
		default:
			response.sendRedirect(request.getContextPath() + "/home");
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
