package com.mouritech.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mouritech.entity.Post;
import com.mouritech.payloads.PostDto;
import com.mouritech.payloads.PostResponse;

@Service
public interface PostService {

	//create
	PostDto createPost(PostDto postDto,int userId,int categoryId);
	
	//update
	PostDto updatePost(PostDto postDto,int postId);
	
	//delete
	void deletePost(int postId);
	
	//get all post
	PostResponse getAllPost(int pageNumber,int pageSize,String sortBy,String sortDir);
	
	//get by Id
	PostDto getPostById(Integer postId);
	
	//get all post by category
	List<PostDto> getPostByCategory(int categoryId);
	
	//get all post by user
	List<PostDto> getPostByUser(int userId);
	
	//search post
	List<PostDto> searchPosts(String keyword);

	
}
