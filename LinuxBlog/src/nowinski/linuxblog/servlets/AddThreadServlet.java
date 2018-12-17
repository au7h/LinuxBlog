package nowinski.linuxblog.servlets;

import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import nowinski.linuxblog.dao.RoleDAO;
import nowinski.linuxblog.dao.ThreadDAO;
import nowinski.linuxblog.utils.FileUploaderToDb;
import nowinski.linuxblog.entities.Thread;
import nowinski.linuxblog.entities.User;

/**
 * Servlet implementation class AddThreadServlet
 */
@WebServlet("/addThread")
@MultipartConfig(maxFileSize = 51322)
public class AddThreadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String whoCanSee = "admin";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		RoleDAO roleDAO = (RoleDAO) request.getAttribute("roleDAO");
		if (user != null && roleDAO.getUserRole(user).getRoleName().equals(whoCanSee)) {
			request.getRequestDispatcher("/WEB-INF/view/addThread.jsp").forward(request, response);
		} else {
			response.sendRedirect(request.getContextPath() + "/home");
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String topic = request.getParameter("threadTopic");
		String content = request.getParameter("threadContent");

		if ("".equals(topic) || "".equals(content) || request.getSession().getAttribute("user") == null) {
			request.setAttribute("error", "Błędnie wypełniony formularz lub sesja wygasła");
			doGet(request, response);
			return;
		} else {
			ThreadDAO threadDAO = (ThreadDAO) request.getAttribute("threadDAO");
			Thread thread = new Thread();
			User user = (User) request.getSession().getAttribute("user");
			if (user == null) {
				request.setAttribute("error", "Sesja wygasła, zaloguj się ponownie");
				doGet(request, response);
				return;
			}
			
			Part filePart = null;
			try {
				filePart = request.getPart("photo");
			} catch (IllegalStateException ex) {
				ex.printStackTrace();
				request.setAttribute("error", "Rozmiar pliku nie może przekraczać 50 kB");
				doGet(request, response);
			}
			
			if(filePart.getInputStream().available() == 0) {
				request.setAttribute("error", "Nie wybrano pliku");
				doGet(request, response);
				return;
			}
			
			String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
			if(!FileUploaderToDb.checkFileType(fileName)) {
				request.setAttribute("error", "Niezgodny format pliku");
				doGet(request, response);
				return;
			}

			byte[] image = FileUploaderToDb.uploadPhoto(filePart, 350, 200);

			thread.setTopic(topic);
			thread.setAvatar(image);
			thread.setContent(content);
			thread.setAddDate(new Timestamp(new Date().getTime()));
			thread.setUser(user);
			if (threadDAO.addNewThread(thread))
				response.sendRedirect(request.getContextPath() + "/home");
		}

	}
}
