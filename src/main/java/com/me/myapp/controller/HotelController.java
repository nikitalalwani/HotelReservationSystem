package com.me.myapp.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.omg.CORBA.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;
import java.io.File;
import java.io.FileNotFoundException;
import com.me.myapp.dao.*;
import com.me.myapp.pojo.*;

@Controller
public class HotelController {
	
	private static final Logger logger = LoggerFactory.getLogger(HotelController.class);

	@Autowired
	HotelDao hotelDao;

	@Autowired
	RoomDao roomDao;

	@Autowired
	ReservationDao reservationDao;

	@Autowired
	CustomerDao cusDao;

	int totalStayDays = 0;
		
	@RequestMapping("/addHotel")
	public ModelAndView newHotel() {
		return new ModelAndView("addHotel", "hotel", new Hotel());
	}

	@RequestMapping(value = "/addHotel", method = RequestMethod.POST)
	public ModelAndView addHotel(Model model, @ModelAttribute("hotel") Hotel hotel, BindingResult result) {

		try {
			hotelDao.saveHotel(hotel);
			logger.debug("Hotel is saved in db");

		} catch (Exception e) {

			return new ModelAndView("error", "errorMessage", "This Hotel already exists.");
		}
		return new ModelAndView("success", "message", "Hotel added successfully");
	}

	@RequestMapping("/searchHotels")
	public ModelAndView search(Model model) {

		ModelAndView mv = new ModelAndView();
		try {
			mv.addObject("hotels", hotelDao.getHotels());
			mv.setViewName("searchHotel");

		} catch (Exception e) {
			return new ModelAndView("error", "errorMessage", e + "Please create Hotel first");
		}
		return mv;
	}

	@RequestMapping("/reserveHotel")
	public ModelAndView newReservation(Model model, @RequestParam("hotelName") String hotelName) {
		ModelAndView mv = new ModelAndView();
		try {
			Hotel hotel = hotelDao.getHotel(hotelName);
			Reservation r = new Reservation();
			r.setHotel(hotel);
			mv.addObject("reservation", r);
			mv.setViewName("bookHotel");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			return new ModelAndView("error", "error_message", e + "No Hotels Found");
		}
		return mv;
	}

	@RequestMapping(value = "/reserveHotel", method = RequestMethod.POST)
	public ModelAndView hotelReservation(HttpServletRequest request,
			@ModelAttribute("reservation") Reservation reservation, BindingResult result, Model model) {

		ModelAndView mv = new ModelAndView();
		try {

			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date checkinDate = format.parse(reservation.getCheckinDate());
			Date checkoutDate = format.parse(reservation.getCheckoutDate());

			Date date = new Date();

			if (checkinDate.before(date)) {
				return new ModelAndView("error", "error_message", "Checkin date cannot be less than today's date");
			} else if (checkoutDate.before(checkinDate)) {
				return new ModelAndView("error", "error_message", "Checkout date cannot be less than checkin date");
			}

			long duration = checkoutDate.getTime() - checkinDate.getTime();
			long diffInDays = TimeUnit.MILLISECONDS.toDays(duration);
			
			if (diffInDays <= 0) {
				return new ModelAndView("error", "error_message", "Checkin and checkout dates cannot be same");
			}
			System.out.println(request.getParameter("hotel"));
			System.out.println(request.getAttribute("hotel"));
			
			Hotel hotel = hotelDao.getHotel(request.getParameter("hotel"));
			
			if (reservation.getNumberOfRoomsToBook() <= 0) {
				return new ModelAndView("error", "error_message", "Reservation should have atleast 1 room");
			}
			
			hotel.setNumberOfRooms(hotel.getNumberOfRooms() - reservation.getNumberOfRoomsToBook());
			if (hotel.getNumberOfRooms() <= 0) {
				return new ModelAndView("error", "error_message", "Hotel doesn't have enough rooms");
			}

			Customer customer = cusDao.getCustomerByUsername((String) request.getSession().getAttribute("username"));
			float cost = diffInDays * hotel.getCostPerNight() * reservation.getNumberOfRoomsToBook();

			reservation.setCheckinDate(checkinDate.toString());
			reservation.setCheckoutDate(checkoutDate.toString());
			reservation.setCustomer(customer);
			reservation.setHotel(hotel);
			reservation.setTotalCost(cost);
			reservation.setBookingStatus("confirmed");
			reservationDao.makeReservation(reservation);
			logger.debug("Reservation success");

			return new ModelAndView("success", "message", "Hotel reserved successfully");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			return new ModelAndView("error", "error_message", e);
		}
	}

	@RequestMapping("/viewBookings")
	public ModelAndView viewBookings(HttpServletRequest request, Model model) {
		ModelAndView mv = new ModelAndView();
		try {

			String username = (String) request.getSession().getAttribute("username");
			List<Reservation> resList = reservationDao.getReservations();
			List<Reservation> resfinalList = new ArrayList<Reservation>();

			for (Reservation res : resList) {
				System.out.println(res.getCustomer().getUsername());
				System.out.println(username);
				if (res.getCustomer().getUsername().equalsIgnoreCase(username)) {
					resfinalList.add(res);
				}
			}
			System.out.println(resfinalList);
			if (resfinalList.size() == 0) {
				return new ModelAndView("error", "error_message", "No Bookings found for this customer");
			}
			mv.addObject("reservations", resfinalList);
			mv.setViewName("viewBookingDetails");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			return new ModelAndView("error", "error_message", e);
		}
		return mv;
	}
	
	@RequestMapping("/viewAllBookings")
	public ModelAndView viewAllBookings(HttpServletRequest request, Model model) {
		ModelAndView mv = new ModelAndView();
		try {

			String username = (String) request.getSession().getAttribute("username");
			List<Reservation> resList = reservationDao.getReservations();

			if (resList.size() == 0) {
				return new ModelAndView("error", "error_message", "No Bookings found for this customer");
			}
			mv.addObject("reservations", resList);
			mv.setViewName("viewAllBookings");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			return new ModelAndView("error", "error_message", e);
		}
		return mv;
	}

	@RequestMapping("/cancelReservation")
	public ModelAndView cancelReservation(HttpServletRequest request, Model model) {
		ModelAndView mv = new ModelAndView();
		try {

			int resNumber = Integer.parseInt(request.getParameter("reservationNumber"));
			System.out.println(resNumber);
			boolean deleted = reservationDao.cancelReservation(resNumber);

			if (deleted == true) {

				mv.addObject("message", "Your resevation cancelled successfully");
				mv.setViewName("success");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			return new ModelAndView("error", "error_message", e);
		}
		return mv;
	}

	@RequestMapping("/getReceipt")
	public ModelAndView reservationReceipt(HttpServletRequest request, Model model) {

		String test = "/Users/nikitalalwani/Desktop/receipt.pdf";
		int reserveNum = Integer.parseInt(request.getParameter("reservationNumber"));
		
		FileOutputStream fs;
		try {
			Reservation res = reservationDao.getReservationsByNumber(reserveNum);
			fs = new FileOutputStream(new File(test));
			PdfWriter writer = new PdfWriter(fs);
			PdfDocument pdfdoc = new PdfDocument(writer);
			Document doc = new Document(pdfdoc);
			generateReceipt(doc, res);
			doc.close();
			writer.close();
			return new ModelAndView("success", "message", "Your receipt is generated");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			return new ModelAndView("error", "error_message", e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return new ModelAndView("error", "error_message", e);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			return new ModelAndView("error", "error_message", e);
		}

	}

	private void generateReceipt(Document doc, Reservation reservation) {

		doc.add(new Paragraph("First Name: " + reservation.getCustomer().getFirstName() + " " + "Last Name: " + reservation.getCustomer().getLastName()));
		doc.add(new Paragraph("------------------------Your Reservation Details------------------------"));
		doc.add(new Paragraph("Reservation Number: " + reservation.getReservationNumber()));
		doc.add(new Paragraph("Checkin Date: " + reservation.getCheckinDate()));
		doc.add(new Paragraph("Checkout Date: " + reservation.getCheckoutDate()));
		doc.add(new Paragraph("Booking status: " + reservation.getBookingStatus()));
		doc.add(new Paragraph("Number of rooms booked: " + reservation.getNumberOfRoomsToBook()));
		doc.add(new Paragraph("Hotel Name: " + reservation.getHotel().getHotelName()));
		doc.add(new Paragraph("Cost per night: " + reservation.getHotel().getCostPerNight()));
		doc.add(new Paragraph("Your total Bill is: " + reservation.getTotalCost()));

	}

}