package nowinski.linuxblog.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import nowinski.linuxblog.entities.Role;
import nowinski.linuxblog.entities.User;
import nowinski.linuxblog.utils.StringOperations;

public class UserDAO {
	private EntityManager em;
	
	public UserDAO(EntityManager em) {
		this.em = em;
	}
	
	@SuppressWarnings("unchecked")
	public List<User> getAllUsers(){
		List<User> users = em.createQuery("SELECT u from User u").getResultList();
		return users;
	}
	
	public User getUser(int id){
		User user = (User) em.createQuery("SELECT u from User u WHERE u.id = :id").setParameter("id", id).getSingleResult();
		return user;
	}
	
	public User getUser(String login){
		User user = (User) em.createQuery("SELECT u from User u WHERE u.login = :login").setParameter("login", login).getSingleResult();
		return user;
	}
	
	public boolean addUser(User user, String roleName) {
		user.setPassword(StringOperations.stringToMd5(user.getPassword()));
		Role role = new Role();
		role.setUser(user);
		role.setRoleName(roleName);
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			em.persist(user);
			em.persist(role);
			et.commit();
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			et.rollback();
			return false;
		}
	}
	
	public boolean updateUser(User user) {
		EntityTransaction et = em.getTransaction();
		try {
		et.begin();
		em.merge(user);
		et.commit();
		return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			et.rollback();
			return false;
		}
	}
	
	public boolean deleteUser(int id) {
		if((int) em.createQuery("DELETE u from User u WHERE u.id = :id").setParameter("id", id).getSingleResult() != 0)
			return true;
		else
			return false;
	}
}
