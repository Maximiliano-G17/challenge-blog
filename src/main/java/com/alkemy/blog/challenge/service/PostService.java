package com.alkemy.blog.challenge.service;

import java.util.List;
import java.util.Optional;

import com.alkemy.blog.challenge.domain.Post;

import javassist.NotFoundException;

public interface PostService {

	Post save(Post post);
	
	List<Post> findAll();

	Optional<Post> findById(Long id) throws NotFoundException;

	void deleteById(Long id);
}