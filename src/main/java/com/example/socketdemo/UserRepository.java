package com.example.socketdemo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query(value = "select distinct user from User user left join fetch user.roles",
		        countQuery = "select count(distinct user) from User user")
    Page<User> findAllWithEagerRelationships(Pageable pageable);

	@Query("select distinct user from User user left join fetch user.roles")
	List<User> findAllWithEagerRelationships();

	@Query("select user from User user left join fetch user.roles where user.id =:id")
	Optional<User> findOneWithEagerRelationships(@Param("id") Long id);
	
	@Query("select user from User user left join fetch user.roles where user.username =:username")
	Optional<User> findOneEagerData(@Param("username") String username);

	List<User> findAll();
		    
    User findByUsername(String username);
    
   
   
   Optional<User>  findById(Long id);

   boolean existsByUsername(String username);

  

   @Query(value = "SELECT id FROM User u WHERE  u.username = ?1", nativeQuery = true)
   Long findIdByUserName(String username);


  

}