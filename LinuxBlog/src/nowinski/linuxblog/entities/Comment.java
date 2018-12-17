package nowinski.linuxblog.entities;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="comments")
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Lob()
	private String content;
	private Timestamp addDate;
	@ManyToOne
	@JoinColumn(name="user")
	private User user;
	@ManyToOne
	@JoinColumn(name="thread")
	private Thread thread;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getAddDate() {
		return addDate;
	}
	public void setAddDate(Timestamp addDate) {
		this.addDate = addDate;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Thread getThread() {
		return thread;
	}
	public void setThread(Thread thread) {
		this.thread = thread;
	}
	
}
