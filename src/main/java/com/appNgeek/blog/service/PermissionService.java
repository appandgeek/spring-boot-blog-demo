package com.appNgeek.blog.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appNgeek.blog.domain.Permission;
import com.appNgeek.blog.enums.PermissionType;
import com.appNgeek.blog.repo.PermissionRepository;

@Service
public class PermissionService extends AbstractService<Permission, Long> {

	protected PermissionRepository permissionRepository;

	@Autowired
	public PermissionService(PermissionRepository permissionRepository) {
		super(permissionRepository);
		this.permissionRepository = permissionRepository;
	}

	@Override
	public String getEntityType() {
		return "Permission";
	}

	public Permission findByName(String name) {
		return permissionRepository.findByName(name);
	}
	
	public Collection<Permission> findByNameIn(Collection<PermissionType> permissionTypes) {
		List<String> permissionNameList  = new ArrayList<>();
		for (PermissionType permissionType : permissionTypes) {
			permissionNameList.add(permissionType.name());
		}
		return permissionRepository.findByNameIn(permissionNameList);
	}



}
