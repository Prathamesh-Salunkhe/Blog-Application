package com.mouritech.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mouritech.exception.*;
import com.mouritech.config.AppConstants;
import com.mouritech.entity.Role;
import com.mouritech.entity.User;
import com.mouritech.payloads.RoleDto;
import com.mouritech.payloads.UserDto;
import com.mouritech.repository.RoleRepository;
import com.mouritech.repository.UserRepository;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
//	@Autowired
//	private UserDto userDto;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepository roleRepository;
	
//	@Autowired
//	private RoleDto roleDto;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		User users = this.dtoToUser(userDto);
		User savedUser= this.userRepository.save(users);
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","Id",userId));
		
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		
		User updatedUser = this.userRepository.save(user);
		UserDto userDto1 = this.userToDto(updatedUser);
		return userDto1;
	}

	
	@Override
	public UserDto getUserById(Integer userId) {
		User user = this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
		return this.userToDto(user);
	}

	
	@Override
	public List<UserDto> getAllUsers() {
	   List<User> users =  this.userRepository.findAll();
	  List<UserDto>userDtos = users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
		return userDtos;
	}

	
	@Override
	public void deleteUser(Integer userId) {
		User user = this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","User Id",userId));
		this.userRepository.delete(user);
	}
	
	public User dtoToUser(UserDto userDto) {
		User user =this.modelMapper.map(userDto,User.class);

		return user;
		
	}
	
	public UserDto userToDto(User user) {
		UserDto userDto =this.modelMapper.map(user, UserDto.class);
		
		return userDto;
		
	}

	@Override
	public UserDto registerNewUser(UserDto userDto) {
		User user = this.modelMapper.map(userDto, User.class);
		//encoded the password
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		
		//roles
		Role role = this.roleRepository.findById(AppConstants.NORMAL).get();
		
		user.getRolesSet().add(role);
		User newUser = this.userRepository.save(user);
		return this.modelMapper.map(newUser, UserDto.class);
	}

}
