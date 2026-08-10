package com.oracle.emstest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oracle.emstest.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByEmail(String email);
}
