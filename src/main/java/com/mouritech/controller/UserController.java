package com.mouritech.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mouritech.entity.User;
import com.mouritech.payloads.ApiResponse;
import com.mouritech.payloads.UserDto;
import com.mouritech.service.UserService;

@RestController
@RequestMapping("/api/v1")
public class UserController {

	@Autowired
	public UserService userService;
	
	@PostMapping("/user")
	public ResponseEntity<UserDto> createUser(@Validated @RequestBody UserDto userDto){
		UserDto createUserDto = this.userService.createUser(userDto);
		return new ResponseEntity<UserDto>(createUserDto,HttpStatus.CREATED);
	}
	
	@PutMapping("/user/{userId}")
	public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto,@PathVariable Integer userId){
		
		UserDto updatedUser = this.userService.updateUser(userDto,userId);
		return ResponseEntity.ok(updatedUser);
		
	}
	
	@GetMapping("/user")
	public ResponseEntity<List<UserDto>> getAllUsers(){
		 
		return ResponseEntity.ok(this.userService.getAllUsers());
	}
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<UserDto> getUserById(@PathVariable Integer userId){
		
		return ResponseEntity.ok(this.userService.getUserById(userId));
	}
	
	@DeleteMapping("user/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId){
		this.userService.deleteUser(userId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted successfully!!!",true),HttpStatus.OK);
	}

	
	
	
}
