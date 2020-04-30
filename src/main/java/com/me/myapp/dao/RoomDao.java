package com.me.myapp.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import com.me.myapp.pojo.Room;

public class RoomDao extends SessionDao {


	public RoomDao() {
	}

	public Boolean addRoom(Room room) throws Exception {
		try {
			begin();
			getSession().save(room);
			commit();
			return true;

		} catch (HibernateException e) {
			rollback();
			throw new Exception("Exception while creating user: " + e.getMessage());
		}
	}

	public List<Room> getRooms() throws Exception {
		try {
			begin();
			Criteria criteria = getSession().createCriteria(Room.class);
			List<Room> list = criteria.list();
			commit();
			return list;
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not list the categories", e);
		}
	}

	public Room getRoomByNumber(long id) throws Exception {
		try {
			begin();
			Criteria criteria = getSession().createCriteria(Room.class);
			criteria.add(Restrictions.eq("roomNumber", id));
			Room room = (Room) criteria.uniqueResult();
			commit();
			return room;
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not list the categories", e);
		}
	}
}
