package com.klef.jfsd.exam;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ClientDemo {

    public static void main(String[] args) {
        // Initialize Hibernate
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        cfg.addAnnotatedClass(Department.class);

        SessionFactory sessionFactory = cfg.buildSessionFactory();
        Session session = sessionFactory.openSession();

        Transaction transaction = null;
        try {
            // Start a transaction
            transaction = session.beginTransaction();

            // Create a new Department object
            Department department = new Department();
            department.setName("AIDS");
            department.setLocation("Building B");
            department.setHodName("Divyansh");

            // Save the object
            session.save(department);

            // Commit the transaction
            transaction.commit();
            System.out.println("Department added successfully!");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}
