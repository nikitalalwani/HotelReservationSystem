package com.me.myapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.me.myapp.dao.*;
import com.me.myapp.pojo.*;

@Controller

public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	CustomerDao cusDao;

	@RequestMapping("/register")
	protected ModelAndView registerCutomer(HttpServletRequest request) throws Exception {

		return new ModelAndView("dashboard", "customer", new Customer());
	}
	

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	protected ModelAndView customerRegistered(HttpServletRequest request, Model model, @ModelAttribute("customer") Customer customer, BindingResult result)
			throws Exception {

		try {
			
			Customer c = cusDao.getCustomerByUsername(customer.getUsername());
			
			if (c ==  null) {
				cusDao.saveCustomer(customer);
				model.addAttribute("message", "User registered successfully!");
				return new ModelAndView("loginSuccess");

			} else {
				model.addAttribute("error_message", "User is already registered. Please log in");
				return new ModelAndView("loginError");
			}

		} catch (Exception e) {
			model.addAttribute("error_message", e);
			return new ModelAndView("loginError");
		}
	}

	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	protected ModelAndView loginCustomer(HttpServletRequest request, Model model, @RequestParam("username") String username,
			@RequestParam("password") String password) throws Exception {
		try {
			request.getSession().invalidate();
			if (username.equals("admin@gmail.com") && password.equals("123")) {

				request.getSession().setAttribute("username", username);

				return new ModelAndView("adminDashboard");
			}

			Customer cust = cusDao.getCustomerByUsername(username);
			if (cust != null && cust.getPassword().contentEquals(password)) {
				request.getSession().setAttribute("username", cust.getUsername());
				model.addAttribute("customer", cust);
				return new ModelAndView("dashboard");
			} else {
				model.addAttribute("error_message", "User does not exist!");
				return new ModelAndView("loginError");
			}

		} catch (Exception e) {
			model.addAttribute("error_message", "User does not exist!");
			return new ModelAndView("loginError");
		}
	}


}
