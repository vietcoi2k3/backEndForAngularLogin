package com.example.SecurityAndToken.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.SecurityAndToken.model.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer>{

	@Query(value = "select * from user_model where username =:username " , nativeQuery = true)
	public UserModel findByUsernameModel(String username);
	
	@Query(value = "select count(id) from user_model where username =:username and password=:password ",nativeQuery = true)
	public int CheckLogin(String username,String password);
	
	@Query(value = "select count(id) from user_model where username =:username  ",nativeQuery = true)
	public int CheckUsername(String username);
}
