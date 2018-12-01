package com.jazeera.jersey.security.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jazeera.jersey.security.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>{
	
	public User findByUsername(String userName);

	@Modifying(clearAutomatically=true)
	@Query("update User u set u.lastAccessedDate = :lastAccessedDate where u.id = :id")
	public void updateLastAccessedDate(@Param("lastAccessedDate") Date lastAccessedDate, @Param("id") Integer userId);

	
	@Modifying(clearAutomatically=true)
	@Query("update User u set u.password = :password where u.id = :id")
	public void updatePassword(@Param("password") String password, @Param("id") Integer userId);
	
	public List<User> findByPersonId(Integer personId);
}
