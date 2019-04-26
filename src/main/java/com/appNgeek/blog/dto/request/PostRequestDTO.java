package com.appNgeek.blog.dto.request;

import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRequestDTO {

	private String title;

	private String body;

	private Long userId;

	private Set<Long> tagIds;

}
