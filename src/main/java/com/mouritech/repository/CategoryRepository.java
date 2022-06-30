package com.mouritech.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mouritech.entity.Category;
@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{

	Category save(Category cat);

	Optional<Category> findById(Integer categoryId);

}
