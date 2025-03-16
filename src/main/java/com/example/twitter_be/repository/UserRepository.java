package com.example.twitter_be.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.twitter_be.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

    public User findByUsername(String username);

    public User findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.fullName LIKE %?1% OR u.email LIKE %?1%")
    public List<User> searchUsers(String keyword);
    
}