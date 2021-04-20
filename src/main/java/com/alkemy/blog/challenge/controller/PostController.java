package com.alkemy.blog.challenge.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alkemy.blog.challenge.domain.Post;
import com.alkemy.blog.challenge.service.PostService;

import javassist.NotFoundException;

@Controller
@RequestMapping("api/posts")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@GetMapping("/")
	public String home(Model model) {
		List<Post> allPosts = postService.findAll();
		model.addAttribute("allPosts", allPosts);
		return "views/home";
	}
	
	@GetMapping("/getAll")
	public String getAll(Model model) {
		List<Post> allPosts = postService.findAll();
		model.addAttribute("allPosts", allPosts);
		return "views/getAll";
	}
	
	@GetMapping("/description/{id}")
	public String description(Model model,@PathVariable Long id) throws NotFoundException {
		Optional<Post> postFound = postService.findById(id);
		if(!postFound.isPresent()) {
			throw new NotFoundException("The student with file " + id + " does not exist");
		}
		model.addAttribute("post", postFound.get());
		return "views/description";
	}
	
	@GetMapping("/post")
	public String findById(Model model, Long id) throws NotFoundException {
		System.out.println(id);
		Optional<Post> postFound = postService.findById(id);
		if(!postFound.isPresent()) {
			throw new NotFoundException("The student with file " + id + " does not exist");
		}
		model.addAttribute("post", postFound.get());
		return "views/description";
	}

}