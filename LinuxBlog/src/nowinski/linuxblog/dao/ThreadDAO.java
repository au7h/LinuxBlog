package nowinski.linuxblog.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import nowinski.linuxblog.entities.Thread;

public class ThreadDAO {
	private EntityManager entityManager;

	public ThreadDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public Thread getThread(int id) {
		Thread thread = (Thread) entityManager.createQuery("SELECT t from Thread t WHERE t.id = :id").setParameter("id", id)
				.getSingleResult();
		return thread;
	}

	@SuppressWarnings("unchecked")
	public List<Thread> getUserThreads(int idOwner) {
		List<Thread> thread = entityManager.createQuery("SELECT t from Thread t WHERE t.user.id = :idOwner")
				.setParameter("idOwner", idOwner).getResultList();
		return thread;
	}

	@SuppressWarnings("unchecked")
	public List<Thread> getAllThreads() {
		List<Thread> thread = entityManager.createQuery("SELECT t from Thread t").getResultList();
		return thread;
	}
	
	@SuppressWarnings("unchecked")
	public List<Thread> getThreads(int first, int limit) {
		List<Thread> thread = entityManager.createQuery("SELECT t from Thread t").setFirstResult(first).setMaxResults(limit).getResultList();
		return thread;
	}
	
	public Long getThreadCount() {
		return (Long) entityManager.createQuery("SELECT COUNT(*) from Thread t").getSingleResult();
	}
	public boolean addNewThread(Thread thread) {
		EntityTransaction entityTransaction = entityManager.getTransaction();
		try {
			entityTransaction.begin();
			entityManager.persist(thread);
			entityTransaction.commit();
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			entityTransaction.rollback();
			return false;
		}
	}
}
