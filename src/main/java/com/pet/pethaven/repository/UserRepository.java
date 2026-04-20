package com.pet.pethaven.repository;

import com.pet.pethaven.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User,Integer> {
    User findByEmail(String email);
}
