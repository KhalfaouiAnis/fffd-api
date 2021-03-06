package com.tekup.restapi.app.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tekup.restapi.app.models.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer>{	
	Optional<Role> findByRoleName(String roleName);
}
