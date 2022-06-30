package com.mouritech.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.mouritech.entity.Category;
import com.mouritech.entity.Comment;
import com.mouritech.entity.User;

public class PostDto {
	private int postId;
	private String title;
	private String content;
	private String imageName;
	private Date addedDate;

	private CategoryDto category;

	private UserDto user;
	
	private Set<CommentDto> comment = new HashSet<>();


	public PostDto() {
		
	}

	public PostDto(int postId,String title, String content, String imageName, Date addedDate, CategoryDto category, UserDto user,Set<CommentDto> comment) {
		super();
		this.postId = postId;
		this.title = title;
		this.content = content;
		this.imageName = imageName;
		this.addedDate = addedDate;
		this.category = category;
		this.user = user;
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

	public CategoryDto getCategory() {
		return category;
	}

	public void setCategory(CategoryDto category) {
		this.category = category;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}
	
	public Set<CommentDto> getComment() {
		return comment;
	}
	
	public void setComment(Set<CommentDto> comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "PostDto [title=" + title + ", content=" + content + ", imageName=" + imageName + ", addedDate="
				+ addedDate + ", category=" + category + ", user=" + user + ",comment=" +comment+"]";
	}
	
	
	
	

}
