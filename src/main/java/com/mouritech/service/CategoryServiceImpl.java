package com.mouritech.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mouritech.entity.Category;
import com.mouritech.entity.User;
import com.mouritech.exception.ResourceNotFoundException;
import com.mouritech.payloads.CategoryDto;
import com.mouritech.payloads.UserDto;
import com.mouritech.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category cat=this.modelMapper.map(categoryDto, Category.class);
		Category addCategory = categoryRepository.save(cat);
		return this.modelMapper.map(addCategory, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		Category cat = this.categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","Category Id",categoryId));
		cat.setCategoryTitle(categoryDto.getCategoryTitle());
		cat.setCategoryDesc(categoryDto.getCategoryDesc());
		Category updatedcat= this.categoryRepository.save(cat);
		return this.modelMapper.map(updatedcat, CategoryDto.class);
	}

	@Override
	public void deleteCategory(int categoryId) {
		
		Category cat = this.categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","Category Id",categoryId));
		this.categoryRepository.delete(cat);
	}

	@Override
	public CategoryDto getCategoryById(int categoryId) {
		Category cat = this.categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","Category Id",categoryId));
		
		return this.modelMapper.map(cat,CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getCategories() {
		List<Category> category = this.categoryRepository.findAll();
		List<CategoryDto> catDtos = category.stream().map((cat)->this.modelMapper.map(cat,CategoryDto.class)).collect(Collectors.toList());
		return catDtos;
	}

}
