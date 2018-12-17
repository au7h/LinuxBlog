package nowinski.linuxblog.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import nowinski.linuxblog.entities.Message;
import nowinski.linuxblog.entities.User;

public class MessageDAO {

	private EntityManager em;

	public MessageDAO(EntityManager em) {
		this.em = em;
	}

	@SuppressWarnings("unchecked")
	public List<Message> getMessages() {
		List<Message> messages = em.createQuery("SELECT m from Message m").getResultList();
		return messages;
	}
	
	@SuppressWarnings("unchecked")
	public List<Message> getUserMessages(int id){
		List<Message> messages = em.createQuery("SELECT m from Message m WHERE user.id = :id").setParameter("id", id).getResultList();
		return messages;
	}

	public boolean messageTo(User author, String login, String content) {
		Message message = new Message();
		EntityTransaction et = em.getTransaction();
		try {
			User user = (User) em.createQuery("SELECT u from User u WHERE login=:login").setParameter("login", login).getSingleResult();
			message.setAuthor(author.getLogin());
			message.setUser(user);
			message.setContent(content);
			et.begin();
			em.persist(message);
			et.commit();
			return true;
		} catch (Exception ex) {
			//ex.printStackTrace();
			et.rollback();
			return false;
		}
	}
}
