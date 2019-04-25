package com.appNgeek.blog.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@EqualsAndHashCode(callSuper = true, exclude = {"comments", "tags"})
@ToString(callSuper = true, exclude = {"comments", "tags"})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Post.class)
@Entity
@Table(name = "posts")
public class Post extends BaseEntity {

	private static final long serialVersionUID = -3031553322114547692L;

	@Column(name = "title", nullable = false, length = 200)
	private String title;

	@Column(name = "body", columnDefinition = "TEXT")
	private String body;

	@ManyToOne(optional = false)
	private User user;

	@OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
	private Set<Comment> comments;

	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Tag> tags;

}
