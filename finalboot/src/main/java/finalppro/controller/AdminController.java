package finalppro.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import finalppro.dao.UserRepository;
import finalppro.model.Court;
import finalppro.model.Event;
import finalppro.model.Reservation;
import finalppro.model.User;
import finalppro.model.UserSession;
import finalppro.model.UserType;
import finalppro.service.CourtService;
import finalppro.service.ReservationService;
import finalppro.service.UserService;

import com.google.gson.Gson;

@Controller
public class AdminController {

	@Autowired
	private UserService userService;
	@Autowired
	private CourtService courtService;
	@Autowired
	private ReservationService reservationService;
	
	@GetMapping("/admin")
	public String home(HttpServletRequest request){
		UserSession us = (UserSession) request.getSession().getAttribute("userSession"); 
		if (us != null && UserType.values()[userService.findUser(us.getUserId()).getRole().getUserType().ordinal()].toString().equals("admin")){
			System.out.println(UserType.values()[userService.findUser(us.getUserId()).getRole().getUserType().ordinal()].toString());
			if (UserType.values()[userService.findUser(us.getUserId()).getRole().getUserType().ordinal()].toString().equals("admin")){
				return "admin/index";
			} else {
				request.setAttribute("message", "Nemáte oprávnění pro prohlížení administrace");
				return "admin/index";
			}
		
		} else {
			request.setAttribute("message", "Nemáte oprávnění pro prohlížení administrace");
			return "admin/login";
		}
	}
	

		
	
}
