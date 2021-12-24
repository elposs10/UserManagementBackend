package tn.spring.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.spring.entity.Direction;
import tn.spring.entity.Role;
import tn.spring.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

	User findByEmail(String userEmail);

	List<User> findByDirection(Direction direction);
	
	List<User> findByRole(Role role);
}
