package com.Repository;

import org.springframework.data.repository.CrudRepository;
import com.model.User;


public interface UserRepository extends CrudRepository<User, Long> {

}