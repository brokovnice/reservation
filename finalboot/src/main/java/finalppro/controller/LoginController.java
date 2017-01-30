package finalppro.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import finalppro.model.Login;
import finalppro.model.User;
import finalppro.model.UserSession;
import finalppro.service.UserService;

@Controller

public class LoginController {

	@Autowired
	UserService userService;
	
	@GetMapping("/login")
	public String loginForm(HttpServletRequest request, Model model){
		UserSession us = (UserSession)request.getSession().getAttribute("userSession");
		
		if (us != null) {
			System.out.println("asdasdasasdsd"+us.getUserId());
		} else {
			System.out.println("null buddy");
		}
		model.addAttribute("login", new Login());		
		return "front/login";
	}
	
	@PostMapping("/login")
	public String loginForm(HttpServletRequest request, @ModelAttribute Login login){
		request.getSession().invalidate();
		
		int userId = userService.authenticate(login.getUsername(), login.getPassword());
		if (userId != -1){
			User u = userService.findUser(userId);
			UserSession us = new UserSession(userId, new Date(),u.getName(), u.getSurname());
			request.getSession().setAttribute("userSession", us);
			return "redirect:/";
		} else {			
			request.setAttribute("message", "Neplatné přihlašovací údaje!");
			return "front/login";
		}
	}
	
	@GetMapping("/login-admin")
	public String loginFormAdmin(HttpServletRequest request, Model model){
		UserSession us = (UserSession)request.getSession().getAttribute("userSession");
		
		model.addAttribute("login", new Login());		
		return "admin/login";
	}
	
	@PostMapping("/login-admin")
	public String loginFormAdmin(HttpServletRequest request, @ModelAttribute Login login){
		request.getSession().invalidate();
		
		int userId = userService.authenticate(login.getUsername(), login.getPassword());
		if (userId != -1){
			User u = userService.findUser(userId);
			UserSession us = new UserSession(userId, new Date(),u.getName(), u.getSurname());
			request.getSession().setAttribute("userSession", us);
			return "admin/index";
		} else {			
			request.setAttribute("message", "Neplatné přihlašovací údaje!");
			return "admin/login";
		}
	}
	
	@GetMapping("/logout")
	public String doLogout(HttpServletRequest request){
		request.getSession().invalidate();
		return "redirect:/"; 
	}
}
