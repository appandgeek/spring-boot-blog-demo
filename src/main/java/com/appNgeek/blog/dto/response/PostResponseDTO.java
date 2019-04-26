package com.appNgeek.blog.dto.response;

import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostResponseDTO {

	private Long id;

	private String title;

	private String body;

	private UserResponseDTO user;

	private Set<TagResponseDTO> tags;

}
