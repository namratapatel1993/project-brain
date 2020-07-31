package com.namrata.projectbrain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

import com.namrata.projectbrain.form.PostResponseForm;
import com.namrata.projectbrain.model.Post;
import com.namrata.projectbrain.repository.PostRepository;

@RestController
public class PostController {
    private final PostRepository postRepository;

    @Autowired
    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @GetMapping("/posts")
    public PostResponseForm findAll() {
        PostResponseForm responseForm = new PostResponseForm();
        try {
            responseForm.setData(new HashSet<>(postRepository.findAll()));
        } catch (Exception e) {
            e.printStackTrace();
            responseForm.setData(new HashSet<Post>());
        }
        return responseForm;
    }

    @GetMapping(value = "/post/{title}/posts")
    public PostResponseForm getIdeasByTitle(@PathVariable String title) {
        PostResponseForm responseForm = new PostResponseForm();
        try {
            Set<Post> ideasByTitle = postRepository.findPostByTitleContainingIgnoreCase(title);

            if(ideasByTitle == null) {
                responseForm.setData(new HashSet<>());
            } else {
                responseForm.setData(ideasByTitle);
            }

        } catch (Exception e) {
            e.printStackTrace();
            responseForm.setData(new HashSet<Post>());
        }
        return responseForm;
    }


}