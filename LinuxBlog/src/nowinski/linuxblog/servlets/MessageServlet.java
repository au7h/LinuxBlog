package nowinski.linuxblog.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nowinski.linuxblog.dao.MessageDAO;
import nowinski.linuxblog.entities.Message;
import nowinski.linuxblog.entities.User;

/**
 * Servlet implementation class MessageServlet
 */
@WebServlet("/messages")
public class MessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		MessageDAO messageDAO = (MessageDAO) request.getAttribute("messageDAO");
		User user = (User) request.getSession().getAttribute("user");
		List<Message> messages = null;
		try {
			messages = messageDAO.getUserMessages(user.getId());
			request.setAttribute("messages", messages);
		} catch (NullPointerException ex) {
			request.setAttribute("error", "Nie jesteś zalogowany");
		}
		request.getRequestDispatcher("/WEB-INF/view/messages.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
