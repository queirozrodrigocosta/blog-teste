package com.teste.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teste.blog.model.Comments;

@Repository
public interface CommentRepository extends JpaRepository<Comments, Long> {

	List<Comments> findAllByIdPost(Long idPost);
}
