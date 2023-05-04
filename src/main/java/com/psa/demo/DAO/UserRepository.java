package com.psa.demo.DAO;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.psa.demo.model.User;

public interface UserRepository extends MongoRepository<User,Integer>{

	List<User> findByAddress(String address);

}
