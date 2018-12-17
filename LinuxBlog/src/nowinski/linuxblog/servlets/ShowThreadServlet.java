package nowinski.linuxblog.servlets;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nowinski.linuxblog.dao.CommentDAO;
import nowinski.linuxblog.dao.ThreadDAO;
import nowinski.linuxblog.entities.Comment;
import nowinski.linuxblog.entities.Thread;
import nowinski.linuxblog.entities.User;

/**
 * Servlet implementation class ShowThreadServlet
 */
@WebServlet("/thread")
public class ShowThreadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Integer threadID;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			threadID = Integer.parseInt(request.getParameter("id"));
			ThreadDAO threadDAO = (ThreadDAO) request.getAttribute("threadDAO");
			CommentDAO commentDAO = (CommentDAO) request.getAttribute("commentDAO");
			Thread thread = threadDAO.getThread(threadID);
			List<Comment> comments = commentDAO.getComments(threadID);
			request.setAttribute("thread", thread);
			request.setAttribute("comments", comments);
			request.getRequestDispatcher("/WEB-INF/view/showThread.jsp").forward(request, response);
		} catch (Exception ex) {
			// possible exceptions: NumberFormatException, NoResultException,
			// NullPointerException
			ex.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/home");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String content = request.getParameter("commentContent");
		User user = (User) request.getSession().getAttribute("user");
		ThreadDAO threadDAO = (ThreadDAO) request.getAttribute("threadDAO");
		CommentDAO commentDAO = (CommentDAO) request.getAttribute("commentDAO");
		//validate data ..
		Thread thread = threadDAO.getThread(threadID);
		Comment comment = new Comment();
		comment.setAddDate(new Timestamp(new Date().getTime()));
		comment.setContent(content);
		comment.setThread(thread);
		comment.setUser(user);
		commentDAO.addNewComment(comment);
		doGet(request, response);
	}
}
