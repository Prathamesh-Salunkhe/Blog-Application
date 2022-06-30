package com.mouritech.service;

import org.springframework.stereotype.Service;

import com.mouritech.payloads.ApiResponse;
import com.mouritech.payloads.CommentDto;

@Service
public interface CommentService {

	CommentDto createComment(CommentDto commentDto,int postId);
	void deleteComment(int commentId);
}
