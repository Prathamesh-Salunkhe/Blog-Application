package com.mouritech.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mouritech.entity.Comment;
import com.mouritech.entity.Post;
import com.mouritech.exception.ResourceNotFoundException;
import com.mouritech.payloads.ApiResponse;
import com.mouritech.payloads.CommentDto;
import com.mouritech.repository.CommentRepository;
import com.mouritech.repository.PostRepository;

@Service
public class CommentserviceImpl implements CommentService {
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CommentDto createComment(CommentDto commentDto, int postId) {
        Post post = this.postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "PostId", postId));
        Comment comment = this.modelMapper.map(commentDto, Comment.class);
        comment.setPost(post);
        Comment savedComment = this.commentRepository.save(comment);
		return this.modelMapper.map(savedComment, CommentDto.class);
	}

	@Override
	public void deleteComment(int commentId) {
        Comment com = this.commentRepository.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment", "Comment Id", commentId)	);	
	    this.commentRepository.delete(com);
		
	}

}
