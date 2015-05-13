package com.test.hibernate.examples;

import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.test.hibernate.dao.HibernateDAOImpl;
import com.test.hibernate.models.Customer;


public class TestSpringHibernateIntegration {
	final static private Logger logger = LoggerFactory.getLogger(TestSpringHibernateIntegration.class);
	public TestSpringHibernateIntegration() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		logger.debug("initializing the spring context...");
		ApplicationContext appContext = new ClassPathXmlApplicationContext("/spring/application-config.xml");
		logger.debug("Spring Context initialized");
		HibernateDAOImpl hibimpl =(HibernateDAOImpl) appContext.getBean("hibDAOImpl");
		
		System.out.println("======================================================");
		System.out.println("About to insert the customer details to database... ");
		System.out.println("------------------------------------------------------");
		for(int i=0;i<50;i++){
			Customer u = new Customer();
			u.setName("Customer "+i);
			u.setPhoneNumber("123-"+i);
			u.setType("type -" + i% (new Random(10).nextInt()));
			hibimpl.createUser(u);
		}
		System.out.println("------------------------------------------------------");		
		
//		System.out.println("======================================================");
//		System.out.println("Getting the First record from the table... ");
//		System.out.println("------------------------------------------------------");
//		Customer cust = (Customer)hibimpl.getUSer(1);
//		System.out.println("Name :"+cust.getName());
//		System.out.println("Type :"+cust.getType());
//		System.out.println("phone"+cust.getPhoneNumber());
//		System.out.println("------------------------------------------------------");
		
		System.out.println("======================================================");
		System.out.println("List all the entries from table... ");
		System.out.println("------------------------------------------------------");
		
		List<Customer> customers = hibimpl.getAllCustomers();
		for(Customer customer: customers){
			System.out.println("| "+customer.getId() +"\t| "+customer.getName() +"\t| "+customer.getType()+"\t| "+customer.getPhoneNumber()+" |");
			System.out.println("--------------------------------------------------");
		}
	}

}
