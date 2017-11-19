package bot.db.dao.abstraction;

import java.util.List;

import org.hibernate.Session;

import bot.db.HibernateUtil;

public abstract class DAOManager {
	
	protected String classname;
	
	protected List executeHQLQuery(String query){
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		List result = session.createQuery(query).list();
		session.getTransaction().commit();
		return result;
	}
	
	public void addValue(DBObject object)
	{
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		session.save(object);
		session.getTransaction().commit();
	}
	
	public List getEntireTable(){
		return executeHQLQuery(String.format("From %s", classname));
	}

}
