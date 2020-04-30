package com.me.myapp.dao;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.me.myapp.pojo.Customer;

import java.util.List;

public class CustomerDao extends SessionDao {

	public CustomerDao() {

	}

	public Boolean saveCustomer(Customer customer) throws Exception {
		try {
			begin();
			System.out.println("inside DAO");
			getSession().saveOrUpdate(customer);
			commit();
			return true;

		} catch (HibernateException e) {
			rollback();
			throw new Exception("Exception while creating user: " + e.getMessage());
		}
	}

	public Customer getCustomer(String username, String password) throws Exception {
		try {
			begin();
			Criteria cr = getSession().createCriteria(Customer.class);
			cr.add(Restrictions.eq("username", username));
			cr.add(Restrictions.eq("password", password));
			Customer cust = (Customer) cr.uniqueResult();
			commit();
			return cust;
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not get user " + username, e);
		}
	}

	public List<Customer> getCustomers() throws Exception {
		try {
			begin();
			Criteria cr = getSession().createCriteria(Customer.class);
			cr.add(Restrictions.isNotNull("customerID"));
			List<Customer> custList = cr.list();
			commit();
			return custList;
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not get user ", e);
		}
	}
	
	public Customer getCustomerByUsername(String username) throws Exception {
		try {
			begin();
			Criteria criteria = getSession().createCriteria(Customer.class);
			criteria.add(Restrictions.eq("username", username));
			Customer customer = (Customer) criteria.uniqueResult();
			commit();
			return customer;
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not list the categories", e);
		}
	}
	
	public boolean validateCustomer (String username, String password) throws Exception {
		boolean result = false;
		try {
			begin();
			Criteria cr = getSession().createCriteria(Customer.class);
			cr.add(Restrictions.eq("username", username));
			cr.add(Restrictions.eq("password", password));
			Customer cust = (Customer) cr.uniqueResult();
			
			if (cust != null) {
				result = true;
			}
			commit();
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not get user " + username, e);
		}
		
		return result;
	}
}
