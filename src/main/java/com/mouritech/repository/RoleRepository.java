package com.mouritech.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mouritech.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}
