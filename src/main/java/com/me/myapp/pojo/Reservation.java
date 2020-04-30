package com.me.myapp.pojo;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Reservation")
public class Reservation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private int reservationNumber;

	@Column(nullable = false)
	private String checkinDate;

	@Column(nullable = false)
	private String checkoutDate;

	@Column(nullable = false)
	private String bookingStatus;

	@Column(nullable = false)
	private int numberOfRoomsToBook;

	public String getBookingStatus() {
		return bookingStatus;
	}

	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}

	@Column(nullable = false)
	private float totalCost;

	public int getNumberOfRoomsToBook() {
		return numberOfRoomsToBook;
	}

	public void setNumberOfRoomsToBook(int numberOfRoomsToBook) {
		this.numberOfRoomsToBook = numberOfRoomsToBook;
	}

	public float getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(float totalCost) {
		this.totalCost = totalCost;
	}

	@OneToOne(fetch = FetchType.LAZY, targetEntity = Hotel.class)
	@JoinColumn(name = "hotelID")
	private Hotel hotel;

	@OneToOne(fetch = FetchType.LAZY, targetEntity = Customer.class)
	@JoinColumn(name = "customerID")
	private Customer customer;

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public int getReservationNumber() {
		return reservationNumber;
	}

	public void setReservationNumber(int reservationNumber) {
		this.reservationNumber = reservationNumber;
	}

	public String getCheckinDate() {
		return checkinDate;
	}

	public void setCheckinDate(String checkinDate) {
		this.checkinDate = checkinDate;
	}

	public String getCheckoutDate() {
		return checkoutDate;
	}

	public void setCheckoutDate(String checkoutDate) {
		this.checkoutDate = checkoutDate;
	}

	@Override
	public String toString() {
		return hotel.getHotelName() + hotel.getState();
	}

}
