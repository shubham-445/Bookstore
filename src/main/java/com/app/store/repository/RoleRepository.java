package com.app.store.repository;

import org.springframework.data.repository.CrudRepository;

import com.app.store.domain.security.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {
	
	Role findByName(String name);

}
