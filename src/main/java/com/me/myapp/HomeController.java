package com.me.myapp;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.me.myapp.pojo.Customer;
import com.me.myapp.pojo.Hotel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		logger.debug("We are in home screen!");
		return "home";
	}

	@RequestMapping("/signup")
	public String signup(Customer customer) {
		return "signup";
	}

	@RequestMapping("/success")
	public String success(HttpServletRequest request) {

		request.getSession().invalidate();
		return "success";
	}

	@RequestMapping("/error")
	public String error(HttpServletRequest request) {

		return "error";
	}

	@RequestMapping("/dashboard")
	public String dashboard(HttpServletRequest request) {

		String username = (String) request.getSession().getAttribute("username");
		if (username.equalsIgnoreCase("admin@gmail.com")) {

			return "adminDashboard";
		}
		return "dashboard";
	}

	@RequestMapping("/hotel")
	public String hotel(Hotel hotel) {

		return "hotel";
	}

	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {

		request.getSession().invalidate();
		return "home";
	}
}
