package com.mouritech.payloads;

public class CommentDto {

	
	private int id;
	
	private String content;

	public CommentDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CommentDto(int id, String content) {
		super();
		this.id = id;
		this.content = content;
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
	
	
	

}
