package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Blogs;

@Repository
public interface BlogsRepository extends JpaRepository<Blogs, Long> {
	@Query(value = "SELECT A FROM Blogs A LEFT JOIN FETCH A.readers")
	public List<Blogs> getReaderBlogs();
}
