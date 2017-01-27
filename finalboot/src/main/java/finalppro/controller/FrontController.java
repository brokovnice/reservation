package finalppro.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;import finalppro.dao.UserRepository;
import finalppro.service.CourtService;
import finalppro.service.UserService;

@Controller
public class FrontController {

	@Autowired
	private UserService userService;
	@Autowired
	private CourtService courtService;
	
	@GetMapping("/")
	public String home(HttpServletRequest request){
		request.setAttribute("courts", courtService.findAll());
		request.setAttribute("users", userService.findAll());
		return "index";
	}
}
