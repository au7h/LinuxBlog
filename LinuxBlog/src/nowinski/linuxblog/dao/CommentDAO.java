package nowinski.linuxblog.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import nowinski.linuxblog.entities.Comment;

public class CommentDAO {
	private EntityManager entityManager;

	public CommentDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public boolean addNewComment(Comment comment) {
		EntityTransaction entityTransaction = entityManager.getTransaction();
		try {
			entityTransaction.begin();
			entityManager.persist(comment);
			entityTransaction.commit();
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			entityTransaction.rollback();
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Comment> getComments(int threadID) {
		List<Comment> comments = entityManager.createQuery("SELECT c from Comment c WHERE c.thread.id = :threadID")
				.setParameter("threadID", threadID).getResultList();
		return comments;
	}
}
