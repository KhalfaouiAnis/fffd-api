package com.tekup.restapi.restapi;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.tekup.restapi.app.models.Role;
import com.tekup.restapi.app.repositories.RoleRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class RoleRepositoryTests {

	@Autowired
	private RoleRepository repo;
	
	@Test
	public void testCreateFirstRole() {
		Role adminRole = new Role("ADMIN");
		Role saveRole = repo.save(adminRole);
		
		assertThat(saveRole.getId()).isGreaterThan(0);
	}
	
	@Test
	public void testCreateRestRoles() {
		List<Role> roles = new ArrayList<Role>();
		roles.add(new Role("RESTAURANT_MANAGER"));
		roles.add(new Role("CLIENT"));
		roles.add(new Role("DELIVERY_MAN"));		
		repo.saveAll(roles);
	}
	
}
