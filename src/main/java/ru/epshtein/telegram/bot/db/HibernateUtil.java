package ru.epshtein.telegram.bot.db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	 
    private static final Session session = getHibernateSession();
 
	private static Session getHibernateSession() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            //return new Configuration().configure().buildSessionFactory();
        	final SessionFactory sf = new Configuration().configure().buildSessionFactory();//new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        	final Session session = sf.openSession();
            return session;
        }
        catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
 
    public static Session getSession() {
        return session;
    }
 
}
