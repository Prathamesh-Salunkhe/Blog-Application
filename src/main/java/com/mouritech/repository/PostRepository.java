package com.mouritech.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mouritech.entity.Category;
import com.mouritech.entity.Post;
import com.mouritech.entity.User;
@Repository
public interface PostRepository extends JpaRepository<Post, Integer>{

	List<Post>findByUser(User user);
	
	//List<Post>findByUser(Category category);

	List<Post> findByCategory(Category cat);

	//List<Post> findByCategory(User us);
	
	List<Post> findByTitleContaining(String title);
}
