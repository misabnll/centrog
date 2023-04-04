package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	@Query(value = "SELECT A FROM User A LEFT JOIN FETCH A.authority WHERE A.username = :username AND A.enabled = 1")
	public Optional<User> findByUsername(String username);
}
