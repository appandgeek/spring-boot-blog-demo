package com.appNgeek.blog.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false, exclude = {"post", "user"})
@ToString(callSuper = true, exclude = {"post", "user"})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Comment.class)
@Entity
@Table(name = "comments")
public class Comment extends BaseEntity {

	private static final long serialVersionUID = 8626497571327274564L;

	@Column(name = "body", nullable = false, columnDefinition = "TEXT")
	private String body;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private Post post;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private User user;
}
