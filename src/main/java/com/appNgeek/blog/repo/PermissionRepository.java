package com.appNgeek.blog.repo;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.appNgeek.blog.domain.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {

	Permission findByName(String name);

	Collection<Permission> findByNameIn(Collection<String> names);

}