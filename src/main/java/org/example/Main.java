package org.example;

import static java.lang.StringTemplate.STR;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import org.example.entity.onetomany.Customer;
import org.example.entity.onetomany.PhoneNumber;
import org.example.entity.onetoone.Employee;
import org.example.entity.onetoone.Licence;
import org.example.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class Main {
  private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

  public static void main(String[] args) {
//    System.out.println("Saving some data:");
//    System.out.println("Employee saved to db is: " + createEmployee());
//    System.out.println("Updated employee: " + updateEmployee());

//    String name = "Mickey";
//    Employee employee = getFirstEmployee();
//
//    System.out.println(STR."Here is first employee: \{employee}");
//    System.out.println("This is just some dummy sentence");
//    System.out.println(STR."Here is the licence of our employee: \{employee.getLicence()}");

//    System.out.println(STR."Customer saved to db is: \{createCustomer()}");
    String name = "Mickey Mouse";
    Customer customer = getCustomerByName(name);

    System.out.println("bla bla");
    System.out.println(STR."Customer with name \{name} is: \{customer}");
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
//      session.persist(licence); // need this line if we don't have cascading on
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
          .setParameter("name", "Mickey").getResultList().getFirst();
      Licence licence = new Licence("test licence");
      session.persist(licence);
      employee.setLicence(licence);
      transaction.commit();
    } catch (HibernateException | NoSuchElementException e) {
      if (transaction != null) {
        transaction.rollback();
      }
      System.out.println("Error when updating Employee: " + e.getMessage());
    }
    return employee;
  }

  public static Employee getEmployeeByName(String name) {
    Session session = sessionFactory.openSession();
    Employee employee = null;

    try {
      employee = session.createQuery("select e from Employee e where e.name = :name", Employee.class)
          .setParameter("name", name).getResultList().getFirst();
    } catch (HibernateException | NoSuchElementException e) {
      System.out.println("Error when updating Employee: " + e.getMessage());
    }
    return employee;
  }

  public static Employee getFirstEmployee() {
    Session session = sessionFactory.openSession();
    Employee employee = null;

    try {
      employee = session.createQuery("from Employee", Employee.class)
          .getResultList().getFirst();
    } catch (HibernateException | NoSuchElementException e) {
      System.out.println("Error when updating Employee: " + e.getMessage());
    }
    return employee;
  }

  public static Customer createCustomer() {
    Session session = sessionFactory.openSession();
    Transaction transaction = null;
    Customer customer = null;

    try {
      transaction = session.beginTransaction();
      customer = new Customer("Minnie Mouse");
      List<PhoneNumber> numberList = new ArrayList<>();
      numberList.add(new PhoneNumber("12312555555", null));
      numberList.add(new PhoneNumber("909095555555", null));
      customer.setNumbers(numberList);
      session.persist(customer); // telling HB to track the object, persistent state
      transaction.commit();
    } catch (HibernateException e) {
      if (transaction != null) {
        transaction.rollback();
      }
      System.out.println("Error when persisting Customer: " + e.getMessage());
    }
    return customer;
  }

  public static Customer getCustomerByName(String name) {
    Session session = sessionFactory.openSession();
    Customer customer = null;

    try {
      customer = session.createQuery("select c from Customer c where c.name = :name", Customer.class)
          .setParameter("name", name).getResultList().getFirst();
    } catch (HibernateException | NoSuchElementException e) {
      System.out.println("Error when reading Customer " + e.getMessage());
    }
    return customer;
  }

  public static Customer getFirstCustomer() {
    Session session = sessionFactory.openSession();
    Customer customer = null;

    try {
      customer = session.createQuery("from Customer", Customer.class)
          .getResultList().getFirst();
    } catch (HibernateException | NoSuchElementException e) {
      System.out.println("Error when reading Customer: " + e.getMessage());
    }
    return customer;
  }
}