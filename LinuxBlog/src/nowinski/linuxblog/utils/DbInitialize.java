package nowinski.linuxblog.utils;

import javax.persistence.EntityManager;
import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

import nowinski.linuxblog.dao.CommentDAO;
import nowinski.linuxblog.dao.MessageDAO;
import nowinski.linuxblog.dao.RoleDAO;
import nowinski.linuxblog.dao.ThreadDAO;
import nowinski.linuxblog.dao.UserDAO;

/**
 * Application Lifecycle Listener implementation class DbInitialize
 *
 */

@WebListener
public class DbInitialize implements ServletRequestListener {

    public void requestDestroyed(ServletRequestEvent arg0)  {

    }

    //method request
    public void requestInitialized(ServletRequestEvent arg0)  {
         EntityManager em = DbConfig.createEntityManager();
         UserDAO userDAO = new UserDAO(em);
         RoleDAO roleDAO = new RoleDAO(em);
         ThreadDAO threadDAO = new ThreadDAO(em);
         CommentDAO commentDAO = new CommentDAO(em);
         MessageDAO messageDAO = new MessageDAO(em);
         ServletRequest req = arg0.getServletRequest();
         req.setAttribute("userDAO", userDAO);
         req.setAttribute("roleDAO", roleDAO);
         req.setAttribute("threadDAO", threadDAO);
         req.setAttribute("commentDAO", commentDAO);
         req.setAttribute("messageDAO", messageDAO);
    }

}
