package com.mouritech.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mouritech.payloads.CategoryDto;

@Service
public interface CategoryService {

	//create
	CategoryDto createCategory(CategoryDto categoryDto);
	
	//update
	CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId);
	
	//delete
	public void deleteCategory(int categoryId);
	
	//get by id
	CategoryDto getCategoryById(int categoryId);
	
	//get all
	List<CategoryDto> getCategories();

}
