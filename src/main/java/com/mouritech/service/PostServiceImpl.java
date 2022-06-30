package com.mouritech.service;

import java.awt.print.Pageable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mouritech.entity.Category;
import com.mouritech.entity.Post;
import com.mouritech.entity.User;
import com.mouritech.exception.ResourceNotFoundException;
import com.mouritech.payloads.PostDto;
import com.mouritech.payloads.PostResponse;
import com.mouritech.repository.CategoryRepository;
import com.mouritech.repository.PostRepository;
import com.mouritech.repository.UserRepository;

@Service
public class PostServiceImpl implements PostService{

	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public PostDto createPost(PostDto postDto,int userId,int categoryId) {
		User user = this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","User Id",userId));
		Category category= this.categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Category Id",categoryId));
		
		
		Post post = this.modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		Post post1=this.postRepository.save(post);
		
		return this.modelMapper.map(post1, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, int postId) {
        Post post = this.postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "Post Id", postId))	;	

        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());
        Post updatePost = this.postRepository.save(post);
        
		return this.modelMapper.map(updatePost, PostDto.class);
	}

	@Override
	public void deletePost(int postId) {
         Post post = this.postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "Post Id", postId))	;	
          postRepository.delete(post);
	}

	@Override
	public PostResponse getAllPost(int pageNumber,int pageSize,String sortBy, String sortDir) {
		
		Sort sort = null;
		if(sortDir.equalsIgnoreCase("asc"))
		{
			sort=Sort.by(sortBy).ascending();
		}else
		{
			sort=Sort.by(sortBy).descending();
		}
		PageRequest p= PageRequest.of(pageNumber, pageSize,sort);
        Page<Post> pagePosts=this.postRepository.findAll(p);
        List<Post> allPosts= pagePosts.getContent();
        List<PostDto> postDtos = allPosts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		PostResponse postResponse = new PostResponse();
		
        postResponse.setContent(postDtos);
        
        postResponse.setPageNumber(pagePosts.getNumber());
        postResponse.setPageSize(pagePosts.getSize());
        postResponse.setTotalElements(pagePosts.getTotalElements());
        postResponse.setTotalPages(pagePosts.getTotalPages());
        postResponse.setLastPage(pagePosts.isLast());
        
        return postResponse;
	}

	@Override
	public PostDto getPostById(Integer postId) {
		Post post = this.postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "Post Id", postId));
		return this.modelMapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> getPostByCategory(int categoryId) {
		Category cat = this.categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","Category Id", categoryId));
		List<Post>posts = this.postRepository.findByCategory(cat);
		List<PostDto>postDtos = posts.stream().map((post) -> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<PostDto> getPostByUser(int userId) {
		User us = this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "User Id", userId));
		List<Post>posts=this.postRepository.findByUser(us);
		List<PostDto>postDtos = posts.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
       List<Post> posts = this.postRepository.findByTitleContaining(keyword);
       List<PostDto> postDtos = posts.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
       return postDtos;
	}

}
