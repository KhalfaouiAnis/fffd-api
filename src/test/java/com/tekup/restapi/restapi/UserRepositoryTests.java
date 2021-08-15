package com.tekup.restapi.restapi;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.tekup.restapi.models.Role;
import com.tekup.restapi.models.User;
import com.tekup.restapi.repositories.UserRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UserRepositoryTests {

	@Autowired
	private UserRepository repo;

	@Autowired
	private TestEntityManager entityManager;

	@Test
	public void testCreatetNewUserWithOneRole() {
		Role roleAdmin = entityManager.find(Role.class, 1);
		User anis = new User("Anis", "Khalfaoui", "anis.khalfaoui@tek-up.de", "123456");
		anis.addRole(roleAdmin);

		User savedUser = repo.save(anis);

		assertThat(savedUser.getId()).isGreaterThan(0);
	}

	@Test
	public void testCreatetNewUserWithTwoRoles() {
		User john = new User("John", "Doe", "john.doe@tek-up.de", "123456");
		Role roleRestaurantMngr = new Role("RESTAURANT_MANAGER");
		Role roleShipper = new Role("DELIVERY_MAN");
		john.addRole(roleRestaurantMngr);
		john.addRole(roleShipper);

		User savedUser = repo.save(john);

		assertThat(savedUser.getId()).isGreaterThan(0);

	}

	@Test
	public void testListAllUsers() {
		Iterable<User> listUsers = repo.findAll();
		listUsers.forEach(user -> System.out.println(user));
	}

	@Test
	public void testGetUserById() {
		User user = repo.findById(1).get();
		assertThat(user.getEmail()).isNotNull();
	}

	@Test
	public void testUpdateUserDetails() {
		User user = repo.findById(1).get();
		user.setPhoneNumber("23365342");
		User savedUser = repo.save(user);
	}

	@Test
	public void testUpdateUserRoles() {
		User user = repo.findById(1).get();
		Role roleRestaurantMngr = new Role("RESTAURANT_MANAGER");
		Role roleShipper = new Role("DELIVERY_MAN");
		user.getRoles().remove(roleRestaurantMngr);
		user.addRole(roleShipper);
		User savedUser = repo.save(user);
	}

	@Test
	public void testDeleteUse() {
		Integer userId = 2;
		repo.deleteById(userId);
	}

}
