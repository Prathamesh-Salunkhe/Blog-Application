package com.mouritech.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mouritech.entity.User;
import com.mouritech.exception.ResourceNotFoundException;
import com.mouritech.repository.UserRepository;
@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//loading user from DB by username
		User user = this.userRepository.findByEmail(username).orElseThrow(()-> new ResourceNotFoundException("User", "Email : "+username, 0));
		return user;
	}

}
