package com.mouritech.controller;

import java.util.List;

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

import com.mouritech.payloads.ApiResponse;
import com.mouritech.payloads.CategoryDto;
import com.mouritech.service.CategoryService;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	//create
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Validated @RequestBody CategoryDto categoryDto){
		CategoryDto createCategory = this.categoryService.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(createCategory,HttpStatus.CREATED);
	}
	
	//update
	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto,@PathVariable int categoryId ){
		CategoryDto updatedCategory = this.categoryService.updateCategory(categoryDto,categoryId);
		return new ResponseEntity<CategoryDto>(updatedCategory,HttpStatus.CREATED);
	}

	//delete
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable int categoryId ){
		this.categoryService.deleteCategory(categoryId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("category is deleted successfully!!!",true),HttpStatus.OK);
	}
	
	//get
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto>getCategory(@PathVariable Integer categoryId){
		CategoryDto categoryDto = this.categoryService.getCategoryById(categoryId);
		return new ResponseEntity<CategoryDto>(categoryDto,HttpStatus.OK);
	}
	
	//getAll
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>>getCategories(){
		List<CategoryDto> categoryDto = this.categoryService.getCategories();
		return new ResponseEntity<List<CategoryDto>>(categoryDto,HttpStatus.OK);
	}
	
}
