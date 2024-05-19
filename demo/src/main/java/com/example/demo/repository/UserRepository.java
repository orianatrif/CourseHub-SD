package com.example.demo.repository;

import com.example.demo.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    @Query(value = "SELECT u FROM UserEntity u WHERE u.firstname = :name")
    Optional<UserEntity> findUserByFirstName(String name);

    UserDetails findByFirstname(String firstname);

    Optional<UserEntity> findByEmail(String email);




}
