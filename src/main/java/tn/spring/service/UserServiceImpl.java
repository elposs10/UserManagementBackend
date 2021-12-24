package tn.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import tn.spring.entity.Role;
import tn.spring.entity.User;
import tn.spring.repository.UserRepository;
import tn.spring.response.ResponseMessage;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository ur;

	@Autowired
	UserService us;

	@Autowired
	PasswordEncoder encoder;

	@Override
	public ResponseEntity<?> addUser(User user) {

		user.setPassword(encoder.encode(user.getPassword()));
		user.setRole(Role.USER);

		if (user.getEmail().equals("")) {
			return ResponseEntity.badRequest().body(new ResponseMessage("Error: please add your email!"));
		}
		if (user.getFirstName().equals("")) {
			return ResponseEntity.badRequest().body(new ResponseMessage("Error: please add your fisrtname!"));
		}
		if (user.getLastName().equals("")) {
			return ResponseEntity.badRequest().body(new ResponseMessage("Error: please add your lastname!"));
		}
		if (us.findBymail(user.getEmail()) != null) {
			return ResponseEntity.badRequest().body(new ResponseMessage("Error: Email is already taken!"));
		}
		ur.save(user);
		return ResponseEntity.ok(new ResponseMessage("user added Succefully"));
	}

	@Override
	public User updateUser(User user) throws Exception {
		User userinthedatabase = us.retrieveUserById(user.getIdUser());
		if (!encoder.encode(user.getPassword()).equals(userinthedatabase.getPassword())) {
			user.setPassword(encoder.encode(user.getPassword()));
		}
		return ur.save(user);
	}

	@Override
	public boolean deleteUser(int idUser) {
		if (ur.existsById(idUser)) {
			ur.deleteById(idUser);
			return true;
		} else
			return false;
	}

	@Override
	public User retrieveUserById(int idUser) {
		return ur.findById(idUser).get();
	}
	
	@Override
	public User retrieveUserByEmail(String email) {
		return ur.findByEmail(email);
	}

	@Override
	public List<User> retrieveAllUsers() {
		return (List<User>) ur.findAll();
	}

	@Override
	public User findBymail(String userEmail) {
		return ur.findByEmail(userEmail);
	}

	@Override
	public List<User> findByRole(Role role) {
		return ur.findByRole(role);
	}

}
