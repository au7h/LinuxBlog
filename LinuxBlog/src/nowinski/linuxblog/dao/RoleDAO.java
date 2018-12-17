package nowinski.linuxblog.dao;

import javax.persistence.EntityManager;

import nowinski.linuxblog.entities.Role;
import nowinski.linuxblog.entities.User;

public class RoleDAO {
	private EntityManager em;
	
	public RoleDAO(EntityManager em) {
		this.em = em;
	}
	
	public Role getUserRole(User user) {
		Role role = (Role) em.createQuery("SELECT r from Role r WHERE r.user.id = :userId").setParameter("userId", user.getId()).getSingleResult();
		return role;
	}
}
