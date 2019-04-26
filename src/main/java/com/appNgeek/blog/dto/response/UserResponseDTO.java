package com.appNgeek.blog.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDTO {

	private Long id;
	
	private String name;

	private String email;

	private String profileImageURL;

}
