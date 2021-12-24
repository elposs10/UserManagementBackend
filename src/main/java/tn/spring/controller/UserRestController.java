package tn.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.spring.entity.Role;
import tn.spring.entity.User;
import tn.spring.repository.UserRepository;
import tn.spring.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserRestController {

	@Autowired
	UserRepository ur;

	@Autowired
	UserService us;

	// http://localhost:9091/SpringMVC/servlet/add-user
	// @PreAuthorize("hasAuthority('ADMINISTRATOR')")
	@PostMapping("/add-user")
	@ResponseBody
	public ResponseEntity<?> addUser(@RequestBody User user) {
		return us.addUser(user);

	}

	// http://localhost:9091/SpringMVC/servlet/delete-user/{user-id}
	@PreAuthorize("hasAuthority('ADMINISTRATOR')")
	@DeleteMapping("/delete-user/{idUser}")
	@ResponseBody
	public void deleteUser(@PathVariable("idUser") int userId) {
		us.deleteUser(userId);
	}

	// http://localhost:9091/SpringMVC/servlet/update-user
	@PutMapping("/update-user")
	@ResponseBody
	public User updateUser(@RequestBody User user) throws Exception {
		return us.updateUser(user);
	}

	// http://localhost:9091/SpringMVC/servlet/retrieve-all-user
	// @PreAuthorize("hasAuthority('ADMINISTRATOR')")
	@GetMapping("/retrieve-all-user")
	@ResponseBody
	public List<User> getUser() {
		List<User> users = us.retrieveAllUsers();
		return users;
	}

	// http://localhost:9091/SpringMVC/servlet/retrieve-user-by-email/{user-email}
	@GetMapping("/retrieve-user-by-email/{user-email}")
	@ResponseBody
	public User retrieveUserByEmail(@PathVariable("user-email") String emailUser) {
		return us.findBymail(emailUser);
	}

	// http://localhost:9091/SpringMVC/servlet/retrieve-user-by-role/{user-role}
	@PreAuthorize("hasAuthority('ADMINISTRATOR')")
	@GetMapping("/retrieve-user-by-role/{user-role}")
	@ResponseBody
	public List<User> retrieveUserByRole(@PathVariable("user-role") Role role) {
		return us.findByRole(role);
	}

}
