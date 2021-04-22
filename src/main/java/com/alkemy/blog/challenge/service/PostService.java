package com.alkemy.blog.challenge.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.alkemy.blog.challenge.domain.Post;

import javassist.NotFoundException;

public interface PostService {

	Post save(Post post);
	
	List<Post> findAll();

	Optional<Post> findById(Long id) throws NotFoundException;

	void deleteById(Long id);
	
	Page<Post> findPaginated(Map<String, Object> params);
}