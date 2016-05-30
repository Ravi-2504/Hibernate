package com.hibernate.helloworld;

import java.sql.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;


public class Main {

	public static void main(String[] args) {
		
		
		List employees = list();
		System.out.println("Total Employees: " + employees.size());
		
		Employee empl = new Employee("Ravi", "Reddy", new Date(System.currentTimeMillis()), "911");
		empl = save(empl);
		System.out.printf("%s %s \n", empl.getFirstname(), empl.getLastname());
		
	}
	
	

	private static List list() {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();

		List employees = session.createQuery("from Employee").list();
		session.close();
		return employees;
	}
	
	private static Employee save(Employee employee) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();

		session.beginTransaction();

		int id = (int) session.save(employee);
		employee.setId(id);
		
		session.getTransaction().commit();
		
		session.close();

		return employee;
	}

	
	
}
