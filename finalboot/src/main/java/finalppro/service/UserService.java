package finalppro.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import finalppro.dao.UserRepository;
import finalppro.model.Reservation;
import finalppro.model.User;
import finalppro.model.UserType;

@Service
@Transactional
public class UserService {
	@Autowired
	ReservationService reservationService;

	private final UserRepository userRepository;
	
	public UserService(UserRepository userRepository){
		this.userRepository = userRepository;
	}
	
	public List<User> findAll(){
		List<User> users = new ArrayList<>();
		for(User user : userRepository.findAll()){
			users.add(user);
		}
		return users;
	}
	
	public int countReservationsPerUser(int userId){
		return findUser(userId).getReservations().size();
	}
	
	public User findUser(int id){
		return userRepository.findOne(id);
	}
	
	public void save(User user){
		userRepository.save(user);
	}
	
	public void delete(int id){
		for (Reservation reservation : findUser(id).getReservations()) {
			reservationService.delete(reservation.getId());
		}		
		userRepository.delete(id);
	}
	
	public int authenticate(String username, String password){
		int userId = -1;		
		for (User user : userRepository.findAll()) {
			//System.out.println(user.getUsername()+username+" "+user.getPassword()+password);
			
			if (user.getUsername().equals(username) && user.getPassword().equals(password) && !UserType.values()[user.getRole().getUserType().ordinal()].toString().equals("forbidden")) {
				//System.out.println("baaang");
				userId = user.getId();
			}
		}
		
		System.out.println(Integer.toString(userId));
		return userId;
	}
}
