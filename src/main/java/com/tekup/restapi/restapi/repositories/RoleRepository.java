package com.tekup.restapi.restapi.repositories;

import java.util.List;

import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;
import org.springframework.stereotype.Repository;

import com.tekup.restapi.restapi.models.Role;

@Repository
public interface RoleRepository extends JpaAttributeConverter<Role, Integer>{

	Role save(Role role);

	void saveAll(List<Role> roles);

}
