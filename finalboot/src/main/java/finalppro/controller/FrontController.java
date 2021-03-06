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
import finalppro.model.TicketType;
import finalppro.model.User;
import finalppro.model.UserSession;
import finalppro.model.UserType;
import finalppro.service.CourtService;
import finalppro.service.ReservationService;
import finalppro.service.UserService;

import com.google.gson.Gson;

@Controller
public class FrontController {

	@Autowired
	private UserService userService;
	@Autowired
	private CourtService courtService;
	@Autowired
	private ReservationService reservationService;
	
	@GetMapping("/")
	public String home(HttpServletRequest request){
		//System.out.println(courtService.findAllActive());
		request.setAttribute("courts", courtService.findAllActive());
		request.setAttribute("users", userService.findAll());
		return "front/index";
	}
		
	@GetMapping("/create-reservation")
	protected void doCreateReservation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain;charset=utf-8");
		String thrownError = "Nemáte dostatečná oprávnění pro požadovanou akci";		
		response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		
		//login
		if (request.getSession().getAttribute("userSession") != null) {	
			
			//prekryv
			if (checkReservationDate(new Date(request.getParameter("start")), new Date(request.getParameter("end")), Integer.parseInt(request.getParameter("courtId"))) && 
					new Date(request.getParameter("start")).after(new Date())){
				
				UserSession us = (UserSession) request.getSession().getAttribute("userSession");
				User u = userService.findUser(us.getUserId());
				
				//podle typu permice
				if (TicketType.values()[u.getTicket().getTicketType().ordinal()].toString().equals("free") ||
						(TicketType.values()[u.getTicket().getTicketType().ordinal()].toString().equals("paid") && 
						 reservationService.countFutureReservationForUser(u.getId()) < 1)){
								
				List<User> usersList = new ArrayList<>();
				usersList.add(u);
				
				Reservation r = new Reservation();
				Court c = courtService.findCourt(Integer.parseInt(request.getParameter("courtId")));
				r.setCourt(c);
				r.setDate_start(new Date(request.getParameter("start")));
				r.setDate_end(new Date(request.getParameter("end")));
				r.setUsers(usersList);			
				
				reservationService.save(r);			
				
				thrownError = "Rezervace úspěšně založena!";		
				response.setStatus(HttpServletResponse.SC_OK);
				} else {
					thrownError = "Váš typ permanentky neumožňuje mít více aktivních rezervací";		
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				}
			} else {
				thrownError = "Termín rezervace je neplatný!";		
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			}
			
			
		}
		
		PrintWriter out = response.getWriter();
		out.write(thrownError);
	}
	
	@GetMapping("/update-reservation")
	protected void doUpdateReservation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain;charset=utf-8");
		String thrownError = "Nemáte dostatečná oprávnění pro požadovanou akci";		
		response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		
		
		if (request.getSession().getAttribute("userSession") != null) {		
			UserSession us = (UserSession) request.getSession().getAttribute("userSession");
			
			Reservation r = reservationService.findReservation(Integer.parseInt(request.getParameter("id")));
			Date endDate = new Date(request.getParameter("end"));
			Date startDate = new Date(request.getParameter("start"));
			
			r.setDate_start(startDate);
			r.setDate_end(endDate);
			r.setNote(request.getParameter("note"));
			
			//String my = UserType.values()[1].toString();
			//System.out.println(my);
			//System.out.println(Boolean.toString(UserType.values()[userService.findUser(us.getUserId()).getRole().getUserType().ordinal()] == "admin"));
			//System.out.println(UserType.values()[userService.findUser(us.getUserId()).getRole().getUserType().ordinal()].toString().equals("admin"));
			if (UserType.values()[userService.findUser(us.getUserId()).getRole().getUserType().ordinal()].toString().equals("admin")){
				//System.out.println("admin");
				thrownError = "Rezervace úspěšně modifikována";
				response.setStatus(HttpServletResponse.SC_OK);
				reservationService.save(r);
			} else {
			
				for (User user : r.getUsers()) {		
					if (user.getId() == us.getUserId()) {
						System.out.println("normal");
						thrownError = "Rezervace úspěšně modifikována";
						response.setStatus(HttpServletResponse.SC_OK);
						reservationService.save(r);
					}
				}
			}
		}
		
		
		
		//SimpleDateFormat sdfEvents = new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss");
		//SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		
		
		//try {
			//Reservation r = reservationService.findReservation(Integer.parseInt(request.getParameter("id")));			
			//System.out.println(request.getParameter("start"));
			//Date endDate = new Date(request.getParameter("end"));
			//Date startDate = new Date(request.getParameter("start"));
			//System.out.println(endDate.getDay());
			//System.out.println(s.format(endDate));
			//startDate = sdfEvents.parse(request.getParameter("end"));
			//r.setDate_start(startDate);
			//r.setDate_end(endDate);
			//r.setNote(request.getParameter("note"));
			
			//reservationService.save(r);
		/*} catch (ParseException e){
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}			*/	
	
		//String thrownError = "Rezervace úspěšně editována";
		//response.setStatus(HttpServletResponse.SC_OK);
		PrintWriter out = response.getWriter();
		out.write(thrownError);
	}
	
	@GetMapping("/delete-reservation")
	protected void doDeleteReservation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Reservation r = reservationService.findReservation(Integer.parseInt(request.getParameter("id")));
				
		response.setContentType("text/plain;charset=utf-8");
		String thrownError = "Nemáte dostatečná oprávnění pro požadovanou akci";		
		response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		
		if (request.getSession().getAttribute("userSession") != null) {		
			UserSession us = (UserSession) request.getSession().getAttribute("userSession");
				
			if (UserType.values()[userService.findUser(us.getUserId()).getRole().getUserType().ordinal()].toString().equals("admin")){
				reservationService.delete(Integer.parseInt(request.getParameter("id")));			
				thrownError = "Good";
				response.setStatus(HttpServletResponse.SC_OK);
			} else {
				for (User user : r.getUsers()) {
					if (user.getId() == us.getUserId()) {
						reservationService.delete(Integer.parseInt(request.getParameter("id")));			
						thrownError = "Good";
						System.out.println("Goood");
						response.setStatus(HttpServletResponse.SC_OK);
					}
				}
			}
		} 
		
		PrintWriter out = response.getWriter();
		//out.write(new Gson().toJson(thrownError));
		out.write(thrownError);
		//out.flush();
	}
	
	@GetMapping("/fillcalendar")
	protected void doLoad(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 /*List<Event> reservations = new ArrayList<>();
		 
		 Event e = new Event();
		 e.setId(1);
		 e.setStart("2017-01-28");
		 e.setEnd("2017-01-29");
		 e.setTitle("Task in Progress");
		 
		 reservations.add(e);
		 */
		 
		 String startDateStr = request.getParameter("start");
		 String endDateStr = request.getParameter("end");
		 //System.out.println("Id kurtu: "+request.getParameter("courtId"));
		 
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 SimpleDateFormat sdfEvents = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		 
		 Date startDate;
		 Date endDate;
		try {
			endDate = sdf.parse(endDateStr);
			startDate = sdf.parse(startDateStr);
			System.out.println(request.getParameter("courtId"));
			List<Reservation> reservations = reservationService.findAll(startDate, endDate, Integer.parseInt(request.getParameter("courtId")));
			
			List<Event> events = new ArrayList<>();
			
			for (Reservation reservation : reservations) {
				List<User> users = (List<User>) reservation.getUsers();
				String owners = "";
				for (User user : users) {
					owners += (user.getName() + " " + user.getSurname());
				}
				Event e = new Event(reservation.getId(),owners , sdfEvents.format(reservation.getDate_start()), sdfEvents.format(reservation.getDate_end()), "");
				events.add(e);
			}
			
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.write(new Gson().toJson(events));
			 
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	
	private boolean checkReservationDate(Date start, Date end, int courtId){
	
		List<Reservation> reservations = reservationService.findAll(start, end, courtId);
		if (reservations.size() > 0){
			return false;
		} else {
			return true;
		}
	}
}
