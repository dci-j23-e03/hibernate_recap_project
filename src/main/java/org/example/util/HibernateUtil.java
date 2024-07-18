package org.example.util;

import org.example.entity.onetomany.Customer;
import org.example.entity.onetomany.PhoneNumber;
import org.example.entity.onetoone.Employee;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

  private static final SessionFactory SESSION_FACTORY = buildSessionFactory();

  private static SessionFactory buildSessionFactory() {
    Configuration configuration = new Configuration();
    configuration.configure("hibernate-config.xml");
    configuration.addAnnotatedClass(Customer.class);
    configuration.addAnnotatedClass(PhoneNumber.class);
//    Properties properties = configuration.getProperties();
//    ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(properties).build();
    return configuration.buildSessionFactory();
  }

  public static SessionFactory getSessionFactory() {
    return SESSION_FACTORY;
  }
}
