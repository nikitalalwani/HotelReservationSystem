package com.me.myapp.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import com.me.myapp.pojo.Customer;
import com.me.myapp.pojo.Hotel;

public class HotelDao extends SessionDao {
	
	public HotelDao() {

	}

	public Boolean saveHotel(Hotel hotel) throws Exception {
		try {
			begin();
			System.out.println("inside DAO");
			getSession().save(hotel);
			commit();
			return true;

		} catch (HibernateException e) {
			rollback();
			throw new Exception("Exception while creating user: " + e.getMessage());
		}
	}

	public Hotel getHotel(String hotelName) throws Exception {
		try {
			begin();
			Criteria cr = getSession().createCriteria(Hotel.class);
			cr.add(Restrictions.eq("hotelName", hotelName));
			Hotel hotel = (Hotel) cr.uniqueResult();
			commit();
			return hotel;
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not get user " + hotelName, e);
		}
	}

	public List<Hotel> getHotels() throws Exception {
		try {
			begin();
			Criteria cr = getSession().createCriteria(Hotel.class);
			cr.add(Restrictions.isNotNull("hotelName"));
			List<Hotel> hotelList = cr.list();
			commit();
			return hotelList;
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not get hotel ", e);
		}
	}
}
