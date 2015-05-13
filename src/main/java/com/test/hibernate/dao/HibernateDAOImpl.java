package com.test.hibernate.dao;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.test.hibernate.models.Customer;



@Repository
public class HibernateDAOImpl {
	@Autowired
	public SessionFactory sessionFactory = null;

	@Transactional
	public Customer getUSer(int id) {
		Criteria criteria = getSessionFactory().getCurrentSession()
				.createCriteria(Customer.class).add(Restrictions.eq("id", id));
		return (Customer) criteria.uniqueResult();

	}

	@Transactional
	public void createUser(Customer u) {
		getSessionFactory().getCurrentSession().save(u);
	}

	@Transactional
	public void updateUser(Customer u) {
		getSessionFactory().getCurrentSession().update(u);
	}

	@Transactional
	public void delete(Customer u) {
		getSessionFactory().getCurrentSession().delete(u);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@Transactional
	public List<Customer> getAllCustomers() {
		Criteria criteria = getSessionFactory().getCurrentSession()
				.createCriteria(Customer.class);
		return criteria.list();
	}

}
