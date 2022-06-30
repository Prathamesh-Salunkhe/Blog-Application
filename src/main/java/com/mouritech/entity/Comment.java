package com.mouritech.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String content;
	
	@ManyToOne
	private Post post;
	
	@ManyToOne
	private User user;


	public Comment() {
		
	}

	public Comment(int id, String content, Post post,User user) {
		super();
		this.id = id;
		this.content = content;
		this.post = post;
		this.user=user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "Comment [id=" + id + ", content=" + content + ", post=" + post + "]";
	}
	
	
	
	
	
	
	

}
