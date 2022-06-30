package com.mouritech.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mouritech.entity.User;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	User save(User user);

	Optional<User> findById(Integer userId);
	
	Optional<User>findByEmail(String email);

	void delete(User user);

}
