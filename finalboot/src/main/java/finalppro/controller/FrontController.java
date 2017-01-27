package finalppro.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;import finalppro.dao.UserRepository;
import finalppro.model.Event;
import finalppro.model.Reservation;
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
		System.out.println(courtService.findAllActive());
		request.setAttribute("courts", courtService.findAllActive());
		request.setAttribute("users", userService.findAll());
		return "index";
	}
	
	@GetMapping("fillcalendar")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		 
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 
		 Date startDate;
		 Date endDate;
		try {
			endDate = sdf.parse(endDateStr);
			startDate = sdf.parse(startDateStr);
			
			List<Reservation> reservations = reservationService.findAll(startDate, endDate);
			
			List<Event> events = new ArrayList<>();
			
			for (Reservation reservation : reservations) {
				Event e = new Event(reservation.getNote(), sdf.format(reservation.getDate_start()), sdf.format(reservation.getDate_end()), "");
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
}
