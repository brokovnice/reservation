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
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import finalppro.dao.UserRepository;
import finalppro.model.Court;
import finalppro.model.Event;
import finalppro.model.Reservation;
import finalppro.model.User;
import finalppro.model.UserEditForm;
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
	
	private boolean checkLogin(HttpServletRequest request){
		UserSession us = (UserSession) request.getSession().getAttribute("userSession"); 
		if (us != null && UserType.values()[userService.findUser(us.getUserId()).getRole().getUserType().ordinal()].toString().equals("admin")){
			return true;
		} else {
			return false;
		}
	}
	
	@GetMapping("/admin")
	public String home(HttpServletRequest request){
		UserSession us = (UserSession) request.getSession().getAttribute("userSession"); 
		if (checkLogin(request)){
			/*System.out.println(UserType.values()[userService.findUser(us.getUserId()).getRole().getUserType().ordinal()].toString());
			if (UserType.values()[userService.findUser(us.getUserId()).getRole().getUserType().ordinal()].toString().equals("admin")){
				return "admin/index";
			} else {*/
				//request.setAttribute("message", "Nemáte oprávnění pro prohlížení administrace");
				return "/admin/index";
			//}
		
		} else {
			request.setAttribute("message", "Nemáte oprávnění pro prohlížení administrace");
			return "/admin/login";
		}
	}
	
	@GetMapping("/admin/users")
	public String users(HttpServletRequest request){
		if (checkLogin(request)){
			request.setAttribute("users", userService.findAll());
			return "/admin/users/index";
		} else {
			request.setAttribute("message", "Nemáte oprávnění pro prohlížení administrace");
			return "/admin/login";
		}
	}
	
	@GetMapping(value="/admin/users/edit/{id}")	
	public String userEdit(HttpServletRequest request, ModelMap map , @PathVariable ("id") int id){
		if (checkLogin(request)){
			//user.addAttribute("name", userService.findUser(id).getName());
			map.addAttribute("user", userService.findUser(id));
			//user = userService.findUser(id);
			//u = userService.findUser(id);
			
			//System.out.println(user.getName()+"asdasdasdasdasdasdsadasdasdsdasdasdasdasds");
			//request.setAttribute("users", userService.findAll());
			
			//request.setAttribute("user", o);
			return "/admin/users/edit";
		} else {
			request.setAttribute("message", "Nemáte oprávnění pro prohlížení administrace");
			return "/admin/login";
		}
	}
	
	@PostMapping("/admin/users/edit/{id}")
	public String userEditSubmitted(HttpServletRequest request, @ModelAttribute User user){
		if (checkLogin(request)){
			User oldUser = userService.findUser(user.getId());
			//oldUser.
			//System.out.println(user.getName()+" "+user.getSurname()+" "+user.getEmail());
			//request.setAttribute("message", "okokok ok oko");
			return "redrect:/admin/users";
		} else {
			request.setAttribute("message", "Nemáte oprávnění pro prohlížení administrace");
			return "/admin/login";
		}
	}
	
}
