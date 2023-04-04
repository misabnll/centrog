package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Readers;

@Repository
public interface ReadersRepository extends JpaRepository<Readers, Long> {
}