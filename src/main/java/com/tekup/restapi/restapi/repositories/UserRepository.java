package com.tekup.restapi.restapi.repositories;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import com.tekup.restapi.restapi.models.User;

public interface UserRepository extends CrudRepository<User,Long> {
    Optional<User> findByEmail(String email);
    
    User findByFirstName(String firstname);

    Optional<User> findByEmailOrPhoneNumber(String email, String username);
}
