package com.alkemy.blog.challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alkemy.blog.challenge.domain.Post;

public interface PostRepository extends JpaRepository<Post, Long>{

}
