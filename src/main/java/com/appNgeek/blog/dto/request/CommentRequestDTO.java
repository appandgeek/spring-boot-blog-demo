package com.appNgeek.blog.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequestDTO {

	private String body;

	private Long postId;

	private Long userId;

}
