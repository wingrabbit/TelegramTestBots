package bot.db.dao;

import org.hibernate.Session;

import bot.db.HibernateUtil;
import bot.db.dao.abstraction.DAOManager;

import java.util.List;

public class MessageManager extends DAOManager{
	
	public MessageManager()
	{
		this.classname = "Message";
	}
}
