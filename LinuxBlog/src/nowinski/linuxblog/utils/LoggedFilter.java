package nowinski.linuxblog.utils;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import nowinski.linuxblog.dao.RoleDAO;
import nowinski.linuxblog.dao.UserDAO;
import nowinski.linuxblog.entities.Role;
import nowinski.linuxblog.entities.User;

/**
 * Servlet Filter implementation class LoggedFilter
 */
@WebFilter("/login")
public class LoggedFilter implements Filter {
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		//HttpServletResponse res = (HttpServletResponse) response;
		req.setCharacterEncoding("UTF-8");
		String login = req.getRemoteUser();
		System.out.println("Logged user: "+login);
		if(login != null) {
			User user = (User) req.getSession().getAttribute("user");
			Role role = (Role) req.getSession().getAttribute("role");
			if(user == null) {
				UserDAO userDAO = (UserDAO) req.getAttribute("userDAO");
				RoleDAO roleDAO = (RoleDAO) req.getAttribute("roleDAO");
				user = userDAO.getUser(login);
				role = roleDAO.getUserRole(user);
				req.getSession().setAttribute("user", user);
				req.getSession().setAttribute("role", role);
			}
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
	}
	
	@Override
	public void destroy() {

	}
}
