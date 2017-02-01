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

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
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
import finalppro.model.Address;
import finalppro.model.Court;
import finalppro.model.CourtEditForm;
import finalppro.model.Event;
import finalppro.model.Reservation;
import finalppro.model.Role;
import finalppro.model.User;
import finalppro.model.UserEditForm;
import finalppro.model.UserSession;
import finalppro.model.UserType;
import finalppro.service.AddressService;
import finalppro.service.CourtService;
import finalppro.service.ReservationService;
import finalppro.service.RoleService;
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
	@Autowired
	private RoleService roleService;
	@Autowired
	private AddressService addressService;
	
	private boolean checkLogin(HttpServletRequest request){
		UserSession us = (UserSession) request.getSession().getAttribute("userSession"); 
		if (us != null && UserType.values()[userService.findUser(us.getUserId()).getRole().getUserType().ordinal()].toString().equals("admin")){
			return true;
		} else {
			return false;
		}
	}
	
	@GetMapping("/admin")
	public String home(HttpServletRequest request, ModelMap map){
		UserSession us = (UserSession) request.getSession().getAttribute("userSession"); 
		if (checkLogin(request)){
			/*System.out.println(UserType.values()[userService.findUser(us.getUserId()).getRole().getUserType().ordinal()].toString());
			if (UserType.values()[userService.findUser(us.getUserId()).getRole().getUserType().ordinal()].toString().equals("admin")){
				return "admin/index";
			} else {*/
				//request.setAttribute("message", "Nemáte oprávnění pro prohlížení administrace");
			
			map.addAttribute("activePlayers", userService.activePlayers());
			map.addAttribute("activeCourts", courtService.findAllActive().size());
			map.addAttribute("totalReservations", reservationService.findAll().size());
			map.addAttribute("forbiddenPlayers", userService.forbiddenPlayers());
			
			return "/admin/index";
			//}
		
		} else {
			request.setAttribute("message", "Nemáte oprávnění pro prohlížení administrace");
			map.addAttribute("action", "/admin/login");
			return "/login";
		}
	}
	
	//Hraci
	@GetMapping("/admin/users")
	public String users(HttpServletRequest request, ModelMap map){
		if (checkLogin(request)){
			map.addAttribute("users", userService.findAll());
			map.addAttribute("userService", userService);
			return "/admin/users/index";
		} else {
			request.setAttribute("message", "Nemáte oprávnění pro prohlížení administrace");
			map.addAttribute("action", "/admin/login");
			return "/login";
		}
	}
	
	@GetMapping(value="/admin/users/create")	
	public String userCreate(HttpServletRequest request, ModelMap map){
		if (checkLogin(request)){			
			map.addAttribute("title", "Vytvoření nového uživatele");
			map.addAttribute("action", "/admin/users/create");
			return "/admin/users/edit";
		} else {
			request.setAttribute("message", "Nemáte oprávnění pro prohlížení administrace");
			map.addAttribute("action", "/admin/login");
			return "/login";
		}
	}
	
	@PostMapping(value="/admin/users/create")	
	public String userCreateSubmitted(HttpServletRequest request, @ModelAttribute UserEditForm userEditForm, ModelMap map){
		if (checkLogin(request)){
			//Configuration configuration = new Configuration().configure();
			//SessionFactory sessionFactory = configuration.buildSessionFactory();
			
			//Session session = sessionFactory.openSession();
		//	session.beginTransaction();
			
			
			User user = new User();
			Address address = new Address();
			Role role = roleService.findRole(userEditForm.getUserType());
			
			user.setName(userEditForm.getName());
			user.setSurname(userEditForm.getSurname());
			user.setEmail(userEditForm.getEmail());
			user.setUsername(userEditForm.getUsername());
			user.setPassword(userEditForm.getPassword());

			address.setStreet(userEditForm.getStreet());
			address.setCity(userEditForm.getCity());
			address.setPostal_code(userEditForm.getPostal_code());
			addressService.save(address);
			//Long addressid = (Long)session.save(address);
			user.setAddress(address);
			
			//int addressId = (int) session.save(address);
			//Address address2 = session.get(Address.class, addressId);
			//System.out.println(address2.getStreet() + " id: "+Integer.toString(address2.getId()));
			roleService.save(role);
			//user.setAddress(address);
			//Long roleId = (Long)session.save(role);
			user.setRole(role);
			
			userService.save(user);
			//Long userid = (Long)session.save(user);
			//System.out.println(Long.toString(addressid) + " : role: "+Long.toString(roleId) + " user: "+Long.toString(userid));
			//session.getTransaction().commit();
			//session.close();
			
			//userService.save(user);
			//map.addAttribute("message", "Uživatel úspěšně vytvořen!");
			return "redirect:/admin/users";
		} else {
			request.setAttribute("message", "Nemáte oprávnění pro prohlížení administrace");
			map.addAttribute("action", "/admin/login");
			return "/login";
		}
	}
	
	@GetMapping(value="/admin/users/edit/{id}")	
	public String userEdit(HttpServletRequest request, ModelMap map , @PathVariable ("id") int id){
		if (checkLogin(request)){
			//user.addAttribute("name", userService.findUser(id).getName());
			map.addAttribute("user", userService.findUser(id));
			map.addAttribute("title", "Editace uživatele");
			map.addAttribute("action", "/admin/users/edit");
			//System.out.println(userService.findUser(id).getAddress().getPostal_code());
			//user = userService.findUser(id);
			//u = userService.findUser(id);
			
			//System.out.println(user.getName()+"asdasdasdasdasdasdsadasdasdsdasdasdasdasds");
			//request.setAttribute("users", userService.findAll());
			
			//request.setAttribute("user", o);
			return "/admin/users/edit";
		} else {
			request.setAttribute("message", "Nemáte oprávnění pro prohlížení administrace");
			map.addAttribute("action", "/admin/login");
			return "/login";
		}
	}
	
	@PostMapping("/admin/users/edit/{id}")
	public String userEditSubmitted(HttpServletRequest request, @PathVariable ("id") int id, @ModelAttribute UserEditForm userEditForm, ModelMap map){
		if (checkLogin(request)){
			User oldUser = userService.findUser(id);
			Address address = oldUser.getAddress();	
			System.out.println(userEditForm.getUserType());
			Role r = roleService.findRole(userEditForm.getUserType());
			System.out.println(r.getUserType().toString());
			roleService.save(r);
			oldUser.setName(userEditForm.getName());
			oldUser.setSurname(userEditForm.getSurname());
			oldUser.setUsername(userEditForm.getUsername());
			oldUser.setPassword(userEditForm.getPassword());
			oldUser.setRole(r);
			oldUser.setEmail(userEditForm.getEmail());
			
			address.setStreet(userEditForm.getStreet());
			address.setCity(userEditForm.getCity());
			address.setPostal_code(userEditForm.getPostal_code());
			addressService.save(address);
			
			userService.save(oldUser);
			
			//map.addAttribute("message", "Uživatel úspěšně editován!");
			return "redirect:/admin/users";
		} else {
			request.setAttribute("message", "Nemáte oprávnění pro prohlížení administrace");
			map.addAttribute("action", "/admin/login");
			return "/login";
		}
	}
	
	@GetMapping(value="/admin/users/delete/{id}")	
	public String userDelete(HttpServletRequest request,  @PathVariable ("id") int id, ModelMap map){
		if (checkLogin(request)){
			userService.delete(id);
			//map.addAttribute("message", "Uživatel úspěšně smazán!");
			return "redirect:/admin/users";
		} else {
			request.setAttribute("message", "Nemáte oprávnění pro prohlížení administrace");
			map.addAttribute("action", "/admin/login");
			return "/login";
		}
	}
	
	
	
	//kurty
	@GetMapping("/admin/courts")
	public String courts(HttpServletRequest request, ModelMap map){
		if (checkLogin(request)){
			map.addAttribute("courts", courtService.findAll());
			return "/admin/courts/index";
		} else {
			request.setAttribute("message", "Nemáte oprávnění pro prohlížení administrace");
			map.addAttribute("action", "/admin/login");
			return "/login";
		}
	}
	
	@GetMapping(value="/admin/courts/create")	
	public String courtCreate(HttpServletRequest request, ModelMap map){
		if (checkLogin(request)){			
			map.addAttribute("title", "Vytvoření nového kurtu");
			map.addAttribute("action", "/admin/courts/create");
			return "/admin/courts/edit";
		} else {
			request.setAttribute("message", "Nemáte oprávnění pro prohlížení administrace");
			map.addAttribute("action", "/admin/login");
			return "/login";
		}
	}
	
	@PostMapping(value="/admin/courts/create")	
	public String courtCreateSubmitted(HttpServletRequest request, @ModelAttribute CourtEditForm courtEditForm, ModelMap map){
		if (checkLogin(request)){			
			Court court = new Court();
			Address address = new Address();
			
			address.setStreet(courtEditForm.getStreet());
			address.setCity(courtEditForm.getCity());
			address.setPostal_code(courtEditForm.getPostal_code());
			addressService.save(address);
			
			court.setAddress(address);
			
			court.setName(courtEditForm.getName());
			court.setActive(courtEditForm.isActive());
			
			courtService.save(court);
			
			//map.addAttribute("message", "Kurt úspěšně vytvořen!");
			return "redirect:/admin/courts";
		} else {
			request.setAttribute("message", "Nemáte oprávnění pro prohlížení administrace");
			map.addAttribute("action", "/admin/login");
			return "/login";
		}
	}	
	
	@GetMapping(value="/admin/courts/edit/{id}")	
	public String courtsEdit(HttpServletRequest request, ModelMap map , @PathVariable ("id") int id){
		if (checkLogin(request)){
			map.addAttribute("court", courtService.findCourt(id));
			map.addAttribute("title", "Editace kurtu");
			map.addAttribute("action", "/admin/courts/edit");
			
			return "/admin/courts/edit";
		} else {
			request.setAttribute("message", "Nemáte oprávnění pro prohlížení administrace");
			map.addAttribute("action", "/admin/login");
			return "/login";
		}
	}
	
	@PostMapping("/admin/courts/edit/{id}")
	public String courtEditSubmitted(HttpServletRequest request, @PathVariable ("id") int id, @ModelAttribute CourtEditForm courtEditForm, ModelMap map){
		if (checkLogin(request)){
			System.out.println("saeae");
			Court court = courtService.findCourt(id);
			Address address = court.getAddress();
			
			address.setStreet(courtEditForm.getStreet());
			address.setCity(courtEditForm.getCity());
			address.setPostal_code(courtEditForm.getPostal_code());
			addressService.save(address);

			court.setActive(courtEditForm.isActive());
			court.setName(courtEditForm.getName());
			courtService.save(court);
			
			//map.addAttribute("message", "Kurt úspěšně editován!");
			return "redirect:/admin/courts";
		} else {
			request.setAttribute("message", "Nemáte oprávnění pro prohlížení administrace");
			map.addAttribute("action", "/admin/login");
			return "/login";
		}
	}
	
	@GetMapping(value="/admin/courts/delete/{id}")	
	public String courtDelete(HttpServletRequest request,  @PathVariable ("id") int id, ModelMap map){
		if (checkLogin(request)){
			courtService.delete(id);
			//map.addAttribute("message", "Kurt úspěšně smazán!");
			return "redirect:/admin/courts";
		} else {
			request.setAttribute("message", "Nemáte oprávnění pro prohlížení administrace");
			map.addAttribute("action", "/admin/login");
			return "/login";
		}
	}
	
}
