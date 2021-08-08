package com.tekup.restapi.restapi.repositories;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import com.tekup.restapi.restapi.models.User;

public interface UserRepository extends CrudRepository<User,Integer> {
	Optional<User> findByEmail(String email);    
	Optional<User> findByFirstName(String firstname);    
}
