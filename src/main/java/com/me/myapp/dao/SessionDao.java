package com.me.myapp.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.jsp.jstl.core.Config;

public class SessionDao {

	private static final Logger log = Logger.getAnonymousLogger();
    
	private static final ThreadLocal<Session> sessionThread = new ThreadLocal<Session>();
   
   private static final SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();


    protected SessionDao() {
    }

    public static Session getSession()
    {
        Session session = (Session) SessionDao.sessionThread.get();
        
        if (session == null)
        {
            session = sessionFactory.openSession();
            SessionDao.sessionThread.set(session);
        }
        return session;
    }

    protected void begin() {
        getSession().beginTransaction();
    }

    protected void commit() {
        getSession().getTransaction().commit();
    }

    protected void rollback() {
        try {
            getSession().getTransaction().rollback();
        } catch (HibernateException e) {
            log.log(Level.WARNING, "Cannot rollback", e);
        }
        try {
            getSession().close();
        } catch (HibernateException e) {
            log.log(Level.WARNING, "Cannot close", e);
        }
        SessionDao.sessionThread.set(null);
    }

    public static void close() {
        getSession().close();
        SessionDao.sessionThread.set(null);
    }
}
