package finalppro.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import finalppro.dao.UserRepository;
import finalppro.model.User;

@Service
@Transactional
public class UserService {

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
	
	public User findUser(int id){
		return userRepository.findOne(id);
	}
	
	public void save(User user){
		userRepository.save(user);
	}
	
	public void delete(int id){
		userRepository.delete(id);
	}
	
	public int authenticate(String username, String password){
		int userId = -1;		
		for (User user : userRepository.findAll()) {
			System.out.println(user.getUsername()+username+" "+user.getPassword()+password);
			
			if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
				System.out.println("baaang");
				userId = user.getId();
			}
		}
		
		System.out.println(Integer.toString(userId));
		return userId;
	}
}
