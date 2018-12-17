package nowinski.linuxblog.servlets;

import java.io.IOException;

import javax.persistence.NoResultException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nowinski.linuxblog.dao.RoleDAO;
import nowinski.linuxblog.dao.UserDAO;
import nowinski.linuxblog.utils.StringOperations;
import nowinski.linuxblog.entities.Role;
import nowinski.linuxblog.entities.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/loginPage.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String login = request.getParameter("user");
		String password = request.getParameter("pass");
		if (!"".equals(login) && !"".equals("password")) {
			UserDAO userDAO = (UserDAO) request.getAttribute("userDAO");
			RoleDAO roleDAO = (RoleDAO) request.getAttribute("roleDAO");
			String passMd5 = StringOperations.stringToMd5(password);
			User user = null;
			try {
				user = userDAO.getUser(login);
			} catch (NoResultException ex) {
				ex.printStackTrace();
				request.setAttribute("error", "Brak użytkownika w bazie");
				doGet(request, response);
				return;
			}
			Role role = roleDAO.getUserRole(user);
			if (passMd5.equals(user.getPassword())) {
				request.getSession().setAttribute("user", user);
				request.getSession().setAttribute("role", role);
				response.sendRedirect(request.getContextPath() + "/home");
			} else {
				request.setAttribute("error", "Błędne dane logowania");
				doGet(request, response);
				return;
			}
		} else {
			doGet(request, response);
			return;
		}
	}
}
