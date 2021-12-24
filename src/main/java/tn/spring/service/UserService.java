package tn.spring.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import tn.spring.entity.Direction;
import tn.spring.entity.Role;
import tn.spring.entity.User;

public interface UserService {

	ResponseEntity<?> addUser(User user);

	User updateUser(User user) throws Exception;

	boolean deleteUser(int idUser);

	User retrieveUserById(int idUser);
	
	User retrieveUserByEmail(String email);

	List<User> retrieveAllUsers();

	User findBymail(String emailUser);

	List<User> findByRole(Role role);

}
