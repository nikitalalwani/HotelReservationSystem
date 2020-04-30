package com.me.myapp.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import com.me.myapp.pojo.Customer;
import com.me.myapp.pojo.Hotel;
import com.me.myapp.pojo.Reservation;

public class ReservationDao extends SessionDao {

	public Boolean makeReservation(Reservation reservation) throws Exception {
		try {
			begin();
			getSession().save(reservation);
			commit();
			return true;

		} catch (HibernateException e) {
			rollback();
			throw new Exception("Exception while creating reservation: " + e.getMessage());
		}
	}
	
	public List<Reservation> getReservations() throws Exception {
		try {
			begin();
			Criteria cr = getSession().createCriteria(Reservation.class);
			cr.add(Restrictions.isNotNull("reservationNumber"));
			List<Reservation> resList = cr.list();
			commit();
			return resList;
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not get user ", e);
		}
	}
	
	public Reservation getReservationsByNumber(int number) throws Exception {
		try {
			begin();
			Criteria cr = getSession().createCriteria(Reservation.class);
			cr.add(Restrictions.eq("reservationNumber", number));
			Reservation res = (Reservation) cr.uniqueResult();
			commit();
			return res;
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not get user ", e);
		}
	}

	public boolean cancelReservation(int reservationNumber) throws Exception {
		try {
			begin();
			Criteria cr = getSession().createCriteria(Reservation.class);
			cr.add(Restrictions.eq("reservationNumber", reservationNumber));
			Reservation res =  (Reservation) cr.uniqueResult();
			
			Criteria c = getSession().createCriteria(Hotel.class);
			int hotelId = res.getHotel().getHotelID();
			c.add(Restrictions.eq("hotelID", hotelId));
			Hotel hotel = (Hotel) c.uniqueResult();
			hotel.setNumberOfRooms(hotel.getNumberOfRooms() + res.getNumberOfRoomsToBook());
			getSession().update(hotel);

			getSession().delete(res);
			commit();
			return true;

		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not get user ", e);
		}
	}

}
