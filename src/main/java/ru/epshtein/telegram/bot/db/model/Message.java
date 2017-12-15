package ru.epshtein.telegram.bot.db.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import ru.epshtein.telegram.bot.db.dao.abstraction.DBObject;

@Entity
@Table(name="message")
public class Message extends DBObject{
	
	public Message(){}
	
	public Message(long chatId, long userId, String userMessage, String reply, int messageType) {
		this.chatId = chatId;
		this.userId = userId;
		this.userMessage = userMessage;
		this.reply = reply;
		this.messageType = messageType;
	}

	@Id
	@Column(name="message_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="chat_id")
	private long chatId;
	
	@Column(name="user_id")
	private long userId;
	
	@Column(name="user_message")
	private String userMessage;
	
	@Column(name="reply")
	private String reply;
	
	@Column(name="message_type")
	private int messageType;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getChatId() {
		return chatId;
	}

	public void setChatId(long chatId) {
		this.chatId = chatId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	

	public String getUserMessage() {
		return userMessage;
	}

	public void setUserMessage(String userMessage) {
		this.userMessage = userMessage;
	}

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	public int getMessageType() {
		return messageType;
	}

	public void setMessageType(int messageType) {
		this.messageType = messageType;
	}
	
	
}
