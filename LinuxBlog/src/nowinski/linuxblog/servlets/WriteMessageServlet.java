package nowinski.linuxblog.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nowinski.linuxblog.dao.MessageDAO;
import nowinski.linuxblog.entities.User;

/**
 * Servlet implementation class WriteMessageServlet
 */
@WebServlet("/writeMessage")
public class WriteMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.getRequestDispatcher("/WEB-INF/view/writeMessage.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MessageDAO messageDAO = (MessageDAO) request.getAttribute("messageDAO");
		if(!messageDAO.messageTo((User) request.getSession().getAttribute("user"), request.getParameter("target"), request.getParameter("content"))) {
			request.setAttribute("error", "Brak użytkownika w bazie");
		}
		doGet(request, response);
	}

}
