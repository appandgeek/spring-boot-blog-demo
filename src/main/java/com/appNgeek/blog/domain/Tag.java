package com.appNgeek.blog.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
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
@EqualsAndHashCode(callSuper = true, exclude = {"posts"})
@ToString(callSuper = true, exclude = {"posts"})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Tag.class)
@Entity
@Table(name = "tags")
public class Tag extends BaseEntity {

	private static final long serialVersionUID = 8096135007546929448L;

	@Column(name = "name", nullable = false, length = 100)
	private String name;

	@ManyToMany(mappedBy = "tags", fetch = FetchType.LAZY)
	private Set<Post> posts;
}
