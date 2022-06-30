package com.mouritech.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.mouritech.payloads.UserDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity

public class User implements UserDetails {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int id;
	
	private String name;
	
	private String password;
	
	private String email;
	
	private String about;
	
	@OneToMany(mappedBy="user",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private List<Post> posts = new ArrayList<>();
	
//	@OneToMany(mappedBy ="user",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
//	private Set<Comment> comments = new HashSet<>();
	
	@ManyToMany(cascade=CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinTable(name="user_role",
	joinColumns=@JoinColumn(name="user",referencedColumnName="id"),
	inverseJoinColumns = @JoinColumn(name="role",referencedColumnName = "id")) 
	private Set<Role> roles = new HashSet<>();
	
	public User(int id, String name, String password, String email, String about,Set<Role> roles) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.email = email;
		this.about = about;
		this.roles= roles;
	}
	public User() {
		
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	public List<Post> getPosts() {
		return posts;
	}
	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
//	public Set<Comment> getComments() {
//		return comments;
//	}
//	public void setComments(Set<Comment> comments) {
//		this.comments = comments;
//	}
	
	public Set<Role> getRolesSet() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	
	
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", email=" + email + ", about=" + about
				+ "]";
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> auth = this.roles.stream().map((role)-> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
		return auth;
	}
	@Override
	public String getUsername() {
		return this.email;
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return true;
	}
//	public List<UserDto> getRoles() {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
