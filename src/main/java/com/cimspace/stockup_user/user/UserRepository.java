package com.cimspace.stockup_user.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserRepository extends JpaRepository<User, String> {
    List<User> username(String username);
    List<User> email(String email);
}
