package nowinski.linuxblog.servlets;

import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.NoResultException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import nl.captcha.Captcha;
import nowinski.linuxblog.dao.UserDAO;
import nowinski.linuxblog.entities.User;
import nowinski.linuxblog.utils.FileUploaderToDb;
import nowinski.linuxblog.utils.StringOperations;

@WebServlet("/register")
@MultipartConfig(maxFileSize = 51322)
public class RegisterServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/register.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Captcha captcha = (Captcha) session.getAttribute(Captcha.NAME);
		request.setCharacterEncoding("UTF-8");

		String name = request.getParameter("firstName");
		String surname = request.getParameter("lastName");
		String address = request.getParameter("address");
		String birthDate = request.getParameter("birthDate");
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String passwordRe = request.getParameter("retypePassword");
		String email = request.getParameter("email");
		String answer = request.getParameter("answer");
		Part filePart = null;
		try {
			filePart = request.getPart("photo");
		} catch (IllegalStateException ex) {
			ex.printStackTrace();
			request.setAttribute("error", "Rozmiar pliku nie może przekraczać 50 kB");
			doGet(request, response);
		}
		String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

		if (name != null && surname != null && address != null && birthDate != null && email != null
				&& password.equals(passwordRe) && !"".equals(password) && captcha.isCorrect(answer)
				&& filePart.getSize() > 0 && FileUploaderToDb.checkFileType(fileName)) {
			UserDAO userDAO = (UserDAO) request.getAttribute("userDAO");
			try {	
				userDAO.getUser(login);
				request.setAttribute("error", "Uzytkownik o podanym loginie już istnieje");
				doGet(request, response);
				return;
			} catch (NoResultException ex) {
				System.out.println(filePart.getName());
				byte[] image = FileUploaderToDb.uploadPhoto(filePart, 200, 200);
				User user = new User();

				user.setLogin(login);
				user.setPassword(password);
				user.setAddress(address);
				user.setEmail(email);
				user.setName(name);
				user.setSurname(surname);
				user.setBirthDate(StringOperations.parseDate(birthDate));
				user.setRegisterDate(new Timestamp(new Date().getTime()));
				user.setAvatar(image);
				if (userDAO.addUser(user, "user"))
					response.sendRedirect(request.getContextPath() + "/home");
				else
					request.setAttribute("error", "Nie udało się dokończyć procesu rejestracji użytkownika");
			}
		} else {
			if ("".equals(name))
				request.setAttribute("error", "Pole imię nie może być puste");
			else if ("".equals(surname))
				request.setAttribute("error", "Pole nazwisko nie może być puste");
			else if ("".equals(address))
				request.setAttribute("error", "Pole adres nie może być puste");
			else if ("".equals(birthDate))
				request.setAttribute("error", "Pole data urodzenia nie może być puste");
			else if ("".equals(email))
				request.setAttribute("error", "Pole adres e-mail nie może być puste");
			else if ("".equals(password))
				request.setAttribute("error", "Pole hasło nie może być puste");
			else if ("".equals(passwordRe))
				request.setAttribute("error", "Pole powtórz hasło nie możę być puste");
			else if (!password.equals(passwordRe))
				request.setAttribute("error", "Wprowadzone hasła nie są takie same");
			else if (!captcha.isCorrect(answer))
				request.setAttribute("error", "Nieprawidłowy kod z obrazka");
			else if (filePart.getSize() == 0)
				request.setAttribute("error", "Nie wrzuciłeś obrazka");
			else if (!FileUploaderToDb.checkFileType(fileName))
				request.setAttribute("error", "Niezgodny format pliku");
			doGet(request, response);
		}
	}
	
	/*
	private boolean checkDataLength(Set<String> data) {
		boolean result = false;
		for(String tmp : data) {
			if(tmp.length() > 6)
				result = true;
		}
		return result;
	}
	*/
}
