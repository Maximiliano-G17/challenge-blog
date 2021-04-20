package com.alkemy.blog.challenge.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alkemy.blog.challenge.domain.Post;

public interface PostRepository extends JpaRepository<Post, Long>{
	
	List<Post> findAllByOrderByCreationDateDesc();

}
