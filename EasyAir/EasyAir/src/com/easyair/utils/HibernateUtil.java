package com.easyair.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

public class HibernateUtil {
	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		try {
			// Create the SessionFactory from hibernate.cfg.xml
			return new AnnotationConfiguration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * 
	 * @param obj
	 */
	public static void persist(Object obj) {
		Session sess = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = null;
		try {
			tx = sess.beginTransaction();
			sess.save(obj);
			sess.flush(); // allow evict to work
			sess.evict(obj);
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw e;
		} finally {
			tx.commit();
		}
	}

	/**
	 * 
	 * @param obj
	 */
	public static void update(Object obj) {
		Session sess = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = null;
		try {
			tx = sess.beginTransaction();
			sess.update(obj);
			sess.flush(); // allow evict to work
			sess.evict(obj);
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw e;
		} finally {
			tx.commit();
		}
	}

	/**
	 * 
	 * @param obj
	 */
	public static void delete(Object obj) {
		Session sess = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = null;
		try {
			tx = sess.beginTransaction();
			sess.delete(obj);
			sess.flush(); // allow evict to work
			sess.evict(obj);
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw e;
		} finally {
			tx.commit();
		}
	}

	/**
	 * 
	 * @return
	 */
	public static Object getBean(Object objClass) {
		Session sess = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = null;
		Object obj = null;
		try {
			tx = sess.beginTransaction();
			obj = sess.load(objClass.getClass(), new Long(10));
			sess.flush(); // allow evict to work
			sess.evict(obj);
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw e;
		} finally {
			tx.commit();
		}
		return obj;
	}
}
