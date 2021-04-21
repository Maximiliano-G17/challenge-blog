package com.alkemy.blog.challenge.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
		Optional<Post> postFound = postService.findById(id);
		if(!postFound.isPresent()) {
			throw new NotFoundException("The student with file " + id + " does not exist");
		}
		model.addAttribute("post", postFound.get());
		return "views/description";
	}
	
	@GetMapping("/register")
	public String create(Model model){
		model.addAttribute("post", new Post());
		model.addAttribute("titulo", "REGISTER");
		model.addAttribute("subtitulo", "CREATE NEW POST:");
		return "views/register";
	}
	
	@PostMapping("/postRegister")
	public String postCreate(@Valid Post post,BindingResult result, Model model, 
								@RequestParam("file") MultipartFile photos) throws IOException {
		if (result.hasErrors()){
			model.addAttribute("post", post);
			model.addAttribute("titulo", "REGISTER");
			model.addAttribute("subtitulo", "CREATE NEW POST:");
			return "views/register";
		}	
		if(!photos.isEmpty()) {			
			String absolutePath = "C://Users//chami//Desktop//alkemy_challenge_java";
			byte[] bytes = photos.getBytes();			
			Path completePath = Paths.get(absolutePath + "//" + photos.getOriginalFilename());
			Files.write(completePath, bytes);
			post.setPhoto(photos.getOriginalFilename());
			postService.save(post);
		}
		return "redirect:/api/posts/";
	}
	
	@GetMapping("/update/{id}")
	public String update(Model model, @PathVariable Long id) throws NotFoundException{
		Optional<Post> postFound = postService.findById(id);
		if(!postFound.isPresent()) {
			throw new NotFoundException("The student with file " + id + " does not exist");
		}
		model.addAttribute("post", postFound.get());
		model.addAttribute("titulo", "UPDATE");
		model.addAttribute("subtitulo", "UPDATE");
		return "views/register";
	}
	
	@PostMapping("/delete/{id}")
	public String delete(@PathVariable Long id) throws NotFoundException{
		Optional<Post> postFound = postService.findById(id);
		if(!postFound.isPresent()) {
			throw new NotFoundException("The student with file " + id + " does not exist");
		}
		postService.deleteById(id);
		return "redirect:/api/posts/";
	}
}