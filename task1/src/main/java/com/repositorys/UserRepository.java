package com.repositorys;

import org.springframework.data.repository.CrudRepository;
import com.models.User;;

public interface UserRepository extends CrudRepository<User, Long> {

}