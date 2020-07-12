package com.namrata.projectbrain.repository;

import java.util.Optional;

import com.namrata.projectbrain.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByUsername(String username);
    Optional<User> findUserByEmailAndPassword(String email, String password);
}