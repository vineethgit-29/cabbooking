package com.gocabs.cabbooking.entity;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class User {
	
	private String userName;
	private String password;
	@ManyToMany(fetch =FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Role> roles;
}
