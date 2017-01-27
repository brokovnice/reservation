package finalppro.dao;

import org.springframework.data.repository.CrudRepository;

import finalppro.model.User;


public interface UserRepository extends CrudRepository<User, Integer>{

}
