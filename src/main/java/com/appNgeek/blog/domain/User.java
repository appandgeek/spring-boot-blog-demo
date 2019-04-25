package com.appNgeek.blog.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
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
@EqualsAndHashCode(callSuper = false, exclude = {"roles"})
@ToString(callSuper = true, exclude = {"roles"})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = User.class)
@Entity
@Table(name = "users")
public class User extends BaseEntity {

	private static final long serialVersionUID = -526708773566020062L;

	@Column(name = "name", length = 40)
	private String name;

	@Column(name = "email", nullable = false, unique = true, length = 60)
	private String email;

	@Column(name = "enabled")
	private boolean enabled;

	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Set<Role> roles;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, orphanRemoval = true)
	private Set<Post> posts;

	public User(Long ownerId) {
		this.id = ownerId;
	}

	public void addRole(Role role) {
		roles.add(role);
		role.getUsers().add(this);
	}

	public void removeRole(Role role) {
		roles.remove(role);
		role.getUsers().remove(this);
	}

}
