package org.example.util;

import java.io.File;
import org.example.entity.Employee;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

  private static final SessionFactory SESSION_FACTORY = buildSessionFactory();

  private static SessionFactory buildSessionFactory() {
    Configuration configuration = new Configuration();
    configuration.configure("hibernate-config.xml");
    configuration.addAnnotatedClass(Employee.class);
//    Properties properties = configuration.getProperties();
//    ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(properties).build();
    return configuration.buildSessionFactory();
  }

  public static SessionFactory getSessionFactory() {
    return SESSION_FACTORY;
  }
}
