package com.hibernate;


//import org.hibernate.HibernateException;
//import org.hibernate.Session;
import org.hibernate.SessionFactory;
//import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Configuration;

import com.rwrs.hibernate.entity.Subject;

//import com.ecrsiwrs.util.CriticalErrorHandler;


/**
 * Configures and provides access to Hibernate sessions, tied to the
 * current thread of execution.  Follows the Thread Local Session
 * pattern, see {@link http://hibernate.org/42.html}.
 */
public class HibernateDataUtil {
	// --------------------------------------------------------- Instance Variables
	// get a logger instance 
	

	/** 
	 * Location of hibernate.cfg.xml file.
	 * NOTICE: Location should be on the classpath as Hibernate uses
	 * #resourceAsStream style lookup for its configuration file. That
	 * is place the config file in a Java package - the default location
	 * is the default Java package.<br><br>
	 * Examples: <br>
	 * <code>CONFIG_FILE_LOCATION = "/hibernate.conf.xml". 
	 * CONFIG_FILE_LOCATION = "/com/foo/bar/myhiberstuff.conf.xml".</code> 
	 */
	private static String CONFIG_FILE_LOCATION = "/hibernate.cfg.xml";

	/** Holds a single instance of Session */
	//private static final ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();

	/** The single instance of hibernate configuration */
	//private static final Configuration cfg = new Configuration();

	/** The single instance of hibernate SessionFactory */
	private static SessionFactory sessionFactory;

	static {
		try {
			sessionFactory = new Configuration()
					.configure(CONFIG_FILE_LOCATION)
					.addAnnotatedClass(Subject.class)
					.buildSessionFactory();
		} catch (Throwable ex) {
		  			
			throw new ExceptionInInitializerError(ex);
		}
	}
	
	public static SessionFactory getSessionFactory() {
//		Alternatively, you could look up in JNDI here
		return sessionFactory;
	}
	public static void shutdown() {
//		Close caches and connection pools
		getSessionFactory().close();
	}

	/**
	 * Default constructor.
	 */
	private HibernateDataUtil() {
	}

}