package org.example;

import org.example.entity.Employee;
import org.example.entity.Licence;
import org.example.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class Main {
  private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

  public static void main(String[] args) {
    System.out.println("Saving some data:");
    System.out.println("Employee saved to db is: " + createEmployee());
//    System.out.println("Updated employee: " + updateEmployee());
  }

  public static Employee createEmployee() {
    Session session = sessionFactory.openSession();
    Transaction transaction = null;
    Employee employee = null;
    Licence licence = null;

    try {
      transaction = session.beginTransaction();
      employee = new Employee("Mickey", "Mouse");
      licence = new Licence("mickey's driving licence");
      employee.setLicence(licence);
      session.persist(employee); // telling HB to track the object, persistent state
      transaction.commit();
    } catch (HibernateException e) {
      if (transaction != null) {
        transaction.rollback();
      }
      System.out.println("Error when persisting Employee: " + e.getMessage());
    }
    return employee;
  }

  public static Employee updateEmployee() {
    Session session = sessionFactory.openSession();
    Transaction transaction = null;
    Employee employee = null;

    try {
      transaction = session.beginTransaction();
      employee = session.createQuery("select e from Employee e where e.name = :name", Employee.class)
          .setParameter("name", "Mikey").getResultList().getFirst();
      employee.setName("Test");
      transaction.commit();
    } catch (HibernateException e) {
      if (transaction != null) {
        transaction.rollback();
      }
      System.out.println("Error when updating Employee: " + e.getMessage());
    }
    return employee;
  }
}