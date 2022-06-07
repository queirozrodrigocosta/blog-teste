package com.teste.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teste.blog.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
