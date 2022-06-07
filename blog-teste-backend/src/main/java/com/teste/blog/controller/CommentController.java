package com.teste.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teste.blog.dto.CommentDto;
import com.teste.blog.security.CommentService;
import com.teste.blog.security.PostService;

@RestController
@RequestMapping("/api/comments/")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping
    public ResponseEntity createPost(@RequestBody CommentDto CommentDto) {
    	commentService.createComments(CommentDto);
        return new ResponseEntity(HttpStatus.OK);
    }
    
    @GetMapping("/filter/{id}")
    public ResponseEntity<List<CommentDto>> getComments(@PathVariable @RequestBody Long id) {
        return new ResponseEntity<>(commentService.getComments(id), HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity deletePost(@PathVariable @RequestBody Long id) {
    	commentService.deleteComments(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
