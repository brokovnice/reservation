package finalppro.dao;

import org.springframework.data.repository.CrudRepository;

import mybootppro.model.Task;	

public interface TaskRepository extends CrudRepository<Task, Integer>{

}
