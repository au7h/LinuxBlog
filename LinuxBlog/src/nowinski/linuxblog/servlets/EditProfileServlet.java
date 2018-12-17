package nowinski.linuxblog.servlets;

import java.io.IOException;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import nowinski.linuxblog.dao.UserDAO;
import nowinski.linuxblog.entities.User;
import nowinski.linuxblog.utils.FileUploaderToDb;
import nowinski.linuxblog.utils.StringOperations;

/**
 * Servlet implementation class EditProfileServlet
 */
@WebServlet("/editProfile")
@MultipartConfig(maxFileSize = 51322)
public class EditProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		if (user != null) {
			request.getRequestDispatcher("/WEB-INF/view/editProfile.jsp").forward(request, response);
		} else {
			response.sendRedirect(request.getContextPath() + "/home");
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		UserDAO userDAO = (UserDAO) request.getAttribute("userDAO");
		User user = (User) request.getSession().getAttribute("user");

		String name = request.getParameter("firstName");
		String surname = request.getParameter("lastName");
		String address = request.getParameter("address");
		String birthDate = request.getParameter("birthDate");
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String email = request.getParameter("email");

		Part filePart = null;
		try {
			filePart = request.getPart("photo");
		} catch (IllegalStateException ex) {
			ex.printStackTrace();
			request.setAttribute("error", "Rozmiar pliku nie może przekraczać 50 kB");
			doGet(request, response);
		}
		String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
		if (filePart.getSize() > 0 && FileUploaderToDb.checkFileType(fileName)) {
			byte[] image = FileUploaderToDb.uploadPhoto(filePart, 200, 200);
			user.setAvatar(image);
		}

		// detect changes
		if (!user.getName().equals(name))
			user.setName(name);
		if (!user.getSurname().equals(surname))
			user.setSurname(surname);
		if (!user.getAddress().equals(address))
			user.setAddress(address);
		if (!user.getBirthDate().equals(StringOperations.parseDate(birthDate)))
			user.setBirthDate(StringOperations.parseDate(birthDate));
		if (!user.getLogin().equals(login))
			user.setLogin(login);
		if (!user.getEmail().equals(email))
			user.setEmail(email);
		if (!"".equals(password))
			user.setPassword(StringOperations.stringToMd5(password));

		userDAO.updateUser(user);
		response.sendRedirect(request.getContextPath() + "/home");
	}

}
