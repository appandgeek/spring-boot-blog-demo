package com.appNgeek.blog.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentResponseDTO {

	private String body;

	private UserResponseDTO user;

}
