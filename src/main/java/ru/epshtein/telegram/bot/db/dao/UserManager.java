package ru.epshtein.telegram.bot.db.dao;

import java.util.List;

import org.hibernate.Session;

import ru.epshtein.telegram.bot.db.HibernateUtil;
import ru.epshtein.telegram.bot.db.dao.abstraction.DAOManager;
import ru.epshtein.telegram.bot.db.model.User;

public class UserManager extends DAOManager {

	public UserManager(){
		this.classname = "User";
	}
	
	
	public User getUserById(long userId)
	{
		List<User> users = executeHQLQuery(String.format("from User U where U.telegramUserId=%d", userId));
		if(!users.isEmpty())
			return users.get(0);
		else 
			return null;
	}

}
