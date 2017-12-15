package ru.epshtein.telegram.bot.db.dao;

import org.hibernate.Session;

import ru.epshtein.telegram.bot.db.HibernateUtil;
import ru.epshtein.telegram.bot.db.dao.abstraction.DAOManager;

import java.util.List;

public class MessageManager extends DAOManager{
	
	public MessageManager()
	{
		this.classname = "Message";
	}
}
