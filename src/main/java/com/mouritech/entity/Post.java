package com.mouritech.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Post implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int postId;
	private String title;
	private String content;
	private String imageName;
	private Date addedDate;
	
	@ManyToOne
	//@JoinColumn(name="category_id")
	private Category category;
	
	@ManyToOne
	private User user;
	
	@OneToMany(mappedBy ="post",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private Set<Comment> comment = new HashSet<>();


	public Post() {
		
	}

	public Post(int postId, String title, String content, String imageName, Date addedDate,Set<Comment> comment) {
		super();
		this.postId = postId;
		this.title = title;
		this.content = content;
		this.imageName = imageName;
		this.addedDate = addedDate;
		this.comment=comment;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public Date getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Comment> getComment() {
		return comment;
	}
	
	public void setComment(Set<Comment> comment) {
		this.comment = comment;
	}
	
	@Override
	public String toString() {
		return "Post [postId=" + postId + ", title=" + title + ", content=" + content + ", imageName=" + imageName
				+ ", addedDate=" + addedDate + ", category=" + category + ", user=" + user + ",comment="+comment+"]";
	}
	
	
	

}
