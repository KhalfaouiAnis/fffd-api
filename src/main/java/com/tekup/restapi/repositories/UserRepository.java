package com.tekup.restapi.repositories;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tekup.restapi.models.User;

@Repository
public interface UserRepository extends CrudRepository<User,Integer> {
	Optional<User> findByEmail(String email);
	Optional<User> findByFirstName(String firstname);
}
