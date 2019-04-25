package com.appNgeek.blog.domain;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
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
@EqualsAndHashCode(callSuper = false, exclude = {"users", "permissions"})
@ToString(callSuper = true, exclude = {"users", "permissions"})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Role.class)
@Entity
@Table(name = "roles")
public class Role extends BaseEntity {

	private static final long serialVersionUID = 8767825715712301390L;

	@Column(name = "name", length = 80, unique = true)
	private String name;

	@ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
	private Set<User> users = new HashSet<>();

	@ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Set<Permission> permissions = new HashSet<>();

	public void addPermsision(Permission permission) {
		getPermissions().add(permission);
		permission.getRoles().add(this);
	}

	public void addPermsisions(Collection<Permission> permissions) {
		getPermissions().addAll(permissions);
		for (Permission permission : permissions) {
			permission.getRoles().add(this);
		}
	}

	public void removePermsision(Permission permission) {
		getPermissions().remove(permission);
		permission.getRoles().remove(this);
	}

	public void removePermsisions(Collection<Permission> permissions) {
		getPermissions().removeAll(permissions);
		for (Permission permission : permissions) {
			permission.getRoles().remove(this);
		}
	}
}