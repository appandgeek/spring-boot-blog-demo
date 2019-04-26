package com.appNgeek.blog.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum PermissionType {

	CREATE_POST("Create Post"),VIEW_POST("View Post"), EDIT_POST("Edit Post"), DELETE_POST("Delete Post");

	private String displayName;

	PermissionType(String displayName) {
		this.displayName = displayName;
	}

	public String displayName() {
		return displayName;
	}

	@Override
	public String toString() {
		return displayName;
	}

	@JsonCreator
	public static PermissionType fromString(String input) {
		return PermissionType.valueOf(input.toUpperCase());
	}
}
