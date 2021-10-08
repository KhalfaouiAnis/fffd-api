package com.tekup.restapi.app.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tekup.restapi.app.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
	Optional<User> findByEmail(String email);

	Optional<User> findById(int id);

	Optional<User> findByFirstName(String firstname);

	@Query(value = "SELECT u FROM User u where u.id in (select r.user.id from Role r where r.roleName = :roleName)")
	Optional<List<User>> findByRoleName(@Param("roleName") String roleName);
}
