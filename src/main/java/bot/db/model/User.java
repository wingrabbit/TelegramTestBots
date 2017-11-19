package bot.db.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import bot.db.dao.abstraction.DBObject;

@Entity
@Table(name="user")
public class User extends DBObject {
	
	public User(){}
	
	public User(long telegramUserId, String username, String firstName, String lastName) {
		this.telegramUserId = telegramUserId;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	@Id
	@Column(name="user_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="telegram_user_id")
	private long telegramUserId;
	
	@Column(name="username")
	private String username;
	
	@Column(name="firstname")
	private String firstName;
	
	@Column(name="lastname")
	private String lastName;
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public long getTelegramUserId() {
		return telegramUserId;
	}

	public void setTelegramUserId(long telegramUserId) {
		this.telegramUserId = telegramUserId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
