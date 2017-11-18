package bot.db.dao;

import org.hibernate.Session;

import bot.db.HibernateUtil;

import java.util.List;

public class MessageManager {
	
	public List listMessages(){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List result = session.createQuery("from Message").list();
		session.getTransaction().commit();
		return result;
	}
}
