package finalppro.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import finalppro.dao.RoleRepository;
import finalppro.dao.RoleRepository;
import finalppro.model.Role;
import finalppro.model.UserType;

@Service
@Transactional
public class RoleService {

	private final RoleRepository roleRepository;
	
	public RoleService(RoleRepository roleRepository){
		this.roleRepository = roleRepository;
	}
	
	public List<Role> findAll(){
		List<Role> roles = new ArrayList<>();
		for(Role role : roleRepository.findAll()){
			roles.add(role);
		}
		return roles;
	}
	
	public Role findRole(int id){
		return roleRepository.findOne(id);
	}
	
	public Role findRole(String roleName){
		Role r = null;
		for (Role role : roleRepository.findAll()) {
			if (UserType.values()[role.getUserType().ordinal()].toString().equals(roleName)){
				r = role;
			}
		}
		
		return r;
	}
	
	public void save(Role role){
		roleRepository.save(role);
	}
	
	public void delete(int id){
		roleRepository.delete(id);
	}
}
