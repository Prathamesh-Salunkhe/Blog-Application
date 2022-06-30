package com.mouritech.payloads;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class CategoryDto {

	private int categoryId;
	@NotEmpty
	@Size(min=4,message="minimum size for description is 10 characters.")
	private String categoryTitle;
	@NotEmpty
	@Size(min=10,message="minimum size for description is 10 characters.")
	private String categoryDesc;
	
	public CategoryDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public CategoryDto(int categoryId, String categoryTitle, String categoryDesc) {
		super();
		this.categoryId=categoryId;
		this.categoryTitle = categoryTitle;
		this.categoryDesc = categoryDesc;
	}
	
	public int getCategoryId() {
		return categoryId;
	}
	
	public void setCategoryId(int categoryId) {
		this.categoryId=categoryId;
	}
	
	public String getCategoryTitle() {
		return categoryTitle;
	}
	public void setCategoryTitle(String categoryTitle) {
		this.categoryTitle = categoryTitle;
	}
	public String getCategoryDesc() {
		return categoryDesc;
	}
	public void setCategoryDesc(String categoryDesc) {
		this.categoryDesc = categoryDesc;
	}
	@Override
	public String toString() {
		return "CategoryDto [categoryId=" + categoryId + ", categoryTitle=" + categoryTitle + ", categoryDesc="
				+ categoryDesc + "]";
	}
	
	
}
