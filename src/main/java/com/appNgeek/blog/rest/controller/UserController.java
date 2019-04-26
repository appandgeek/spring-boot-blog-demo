package com.appNgeek.blog.rest.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.appNgeek.blog.domain.User;
import com.appNgeek.blog.exception.BlogAppException;
import com.appNgeek.blog.service.UserService;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

	private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@GetMapping
	public Page<User> findAll(Pageable pageable) {
		LOG.debug("findAll User");
		return userService.findAll(pageable);
	}

	@GetMapping("/find")
	public User findByEmail(@RequestParam String email) {
		LOG.debug("findByTitle User with title : {}", email);
		return userService.findByEmail(email);
	}

	@GetMapping("/{id}")
	public User findOne(@PathVariable Long id) throws BlogAppException {
		LOG.debug("findOne User with id : {}", id);
		Optional<User> result = userService.findById(id);
		if (result.isPresent())
			return result.get();
		else
			throw new BlogAppException("User with given id not found");
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public User create(@RequestBody User user) {
		LOG.debug("create User with User : {}", user);
		return userService.save(user);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		LOG.debug("delete User with id : {}", id);
		userService.deleteById(id);
	}

	@PutMapping("/{id}")
	public User updateContract(@RequestBody User user, @PathVariable Long id) throws BlogAppException {
		User userFromDB = findOne(id);
		userFromDB.copy(user);
		return userService.save(userFromDB);
	}
}
