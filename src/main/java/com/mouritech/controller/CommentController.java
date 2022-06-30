package com.mouritech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mouritech.payloads.ApiResponse;
import com.mouritech.payloads.CommentDto;
import com.mouritech.service.CommentService;

@RestController
@RequestMapping("/api")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	//create
	@PostMapping("/post/{postId}/comments")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto, @PathVariable int postId){
		CommentDto createCom=this.commentService.createComment(commentDto, postId);
		return new ResponseEntity<CommentDto>(createCom,HttpStatus.CREATED);
	}
	
	//delete
	@DeleteMapping("/comment/{commentId}")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable int commentId){
		 this.commentService.deleteComment(commentId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Comment is Successfully Deleted!!",true),HttpStatus.OK);
		
	}
   
}
