package com.mouritech.payloads;

import com.mouritech.entity.Role;
import com.sun.istack.NotNull;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Validation;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


public class UserDto {
	
	private int id;
	@NotEmpty
	@Size(min = 4,message="Must be at least 4 characters")
	private String name;
	@NotEmpty
	@Email(message="Email address is not valid!!")
	
	private String email;
	@NotEmpty
	@Size(min = 3,max=10,message="password must be min. of 3 chars or max 10 chars")
	private String password;
	@NotNull
	private String about;
	
	private Set<RoleDto> roles = new HashSet<>();
	

	public UserDto() {
		
	}

	public UserDto(int id,String name, String email, String password, String about,Set<RoleDto> roles) {
		super();
		this.id=id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.about = about;
		this.roles = roles;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	
	public Set<RoleDto> getRoles() {
		return roles;
	}
	
	public void setRoles(Set<RoleDto> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "UserDto [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", about="
				+ about + "]";
	}
	

}
