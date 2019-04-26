package com.appNgeek.blog.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.appNgeek.blog.domain.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByName(String name);

}