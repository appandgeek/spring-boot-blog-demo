package com.appNgeek.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appNgeek.blog.domain.Role;
import com.appNgeek.blog.repo.RoleRepository;

@Service
public class RoleService extends AbstractService<Role, Long> {

	protected RoleRepository roleRepository;

	@Autowired
	public RoleService(RoleRepository roleRepository) {
		super(roleRepository);
		this.roleRepository = roleRepository;
	}

	@Override
	public String getEntityType() {
		return "Role";
	}

	public Role findByName(String name) {
		return roleRepository.findByName(name);
	}



}
