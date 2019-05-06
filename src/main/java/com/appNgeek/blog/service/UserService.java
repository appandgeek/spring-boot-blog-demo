package com.appNgeek.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appNgeek.blog.domain.User;
import com.appNgeek.blog.repo.UserRepository;

@Service
public class UserService extends AbstractService<User, Long> {

	protected UserRepository userRepository;

	@Autowired
	public UserService(UserRepository userRepository) {
		super(userRepository);
		this.userRepository = userRepository;
	}

	@Override
	public String getEntityType() {
		return "User";
	}

	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

}
