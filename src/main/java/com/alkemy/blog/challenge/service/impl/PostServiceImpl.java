package com.alkemy.blog.challenge.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkemy.blog.challenge.domain.Post;
import com.alkemy.blog.challenge.repository.PostRepository;
import com.alkemy.blog.challenge.service.PostService;

import javassist.NotFoundException;

@Service
@Transactional
public class PostServiceImpl implements PostService{

	@Autowired
	private PostRepository postRepository;

	@Override
	public Post save(Post post) {
		return postRepository.save(post);
	}
	
	@Override
	public List<Post> findAll() {
		return postRepository.findAll();
	}

	@Override
	public Optional<Post> findById(Long id) throws NotFoundException{
		Optional<Post> postFound = postRepository.findById(id);
		if(!postFound.isPresent()) {
			throw new NotFoundException("Post with " + id + " not found");
		}
		return postFound;
	}

	@Override
	public void deleteById(Long id) {	
		postRepository.deleteById(id);
	}
}