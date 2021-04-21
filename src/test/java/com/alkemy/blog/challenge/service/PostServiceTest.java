package com.alkemy.blog.challenge.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.alkemy.blog.challenge.domain.Post;

import javassist.NotFoundException;

@SpringBootTest
public class PostServiceTest {
	
	@Autowired
	private PostService postService;
	
	@Test
	public void saveAPost_withCorrectAttribute_returnAPost() {		
		Post post = new Post();
		post.setCategory("Gaming");
		post.setContent("content");
		post.setCreationDate(new Date());
		post.setPhoto("../.../");
		post.setTitle("Title");
		Post postSave = postService.save(post);
		
		assertThat(postSave).isNotNull();
		assertEquals("Gaming", postSave.getCategory());
	}
	
	@Test
	public void findAll_returnListPost() {
		List<Post> listPost = postService.findAll();
		
		assertThat(listPost).isNotEmpty();
		assertEquals(4, listPost.size());
	}
	
	@Test
	public void findById_withExistingId_returnAPost() throws NotFoundException {
		Long id = 1L;
		Optional<Post> postFound= postService.findById(id);
		
		assertThat(postFound).isNotEmpty();
		assertEquals(id, postFound.get().getId());
	}
	
	@Test
	public void findById_withNonexistentId_returnEmpty(){
		Long id = -99L;
		Exception exception = assertThrows(NotFoundException.class, () -> {
			postService.findById(id);
	    });
		
		 String expectedMessage = "Post with " + id + " not found";
		 String actualMessage = exception.getMessage();
		    
		 assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	public void updatePost_withExistingId_returnAUpdatedPost() throws NotFoundException {	
		Long id = 1L;
		String updatedTitle = "Title update";
		Optional<Post> postFound = postService.findById(id);
		
		postFound.get().setTitle("Title update");	
		Post updatedPost = postService.save(postFound.get());
		
		assertThat(postFound).isNotEmpty();
		assertEquals(updatedTitle, updatedPost.getTitle());
	}
	
	@Test
	public void updatePost_withNonexistentId_returnNotFoundException(){	
		Long id = -99L;
		Exception exception = assertThrows(NotFoundException.class, () -> {
			postService.findById(id);
	    });
		String expectedMessage = "Post with " + id + " not found";
		String actualMessage = exception.getMessage();
		    
		assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	public void deletePost_withExistingId_returnEmpty(){	
		Long id = 5L;
		Post post = new Post();
		post.setCategory("Just Chatting");
		post.setContent("content");
		post.setCreationDate(new Date());
		post.setPhoto("../.../");
		post.setTitle("Title");
		postService.save(post);
		
		List<Post> listBeforeDeletePost = postService.findAll();
		int sizeBeforeDeletePost = listBeforeDeletePost.size();
		postService.deleteById(id);	
		List<Post> listAfterDeletePost = postService.findAll();
		int sizeAfterDeletePost = listAfterDeletePost.size();
		
		
		assertThat(listBeforeDeletePost).isNotEmpty();
		assertEquals(sizeBeforeDeletePost-1, sizeAfterDeletePost);
	}
}