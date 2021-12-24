package tn.spring.security;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import tn.spring.entity.User;
import tn.spring.service.UserService;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	UserService iuserservice;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = new User();
		try {
			user = iuserservice.retrieveUserByEmail(username);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return UserDetailsImpl.build(user);
	}

}
