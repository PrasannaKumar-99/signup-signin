package com.example.online.store.repositroy;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.online.store.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}

