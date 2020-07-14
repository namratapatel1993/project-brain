package com.namrata.projectbrain.controller;

import com.namrata.projectbrain.form.PostForm;
import com.namrata.projectbrain.form.TodoForm;
import com.namrata.projectbrain.model.Contributor;
import com.namrata.projectbrain.model.Post;
import com.namrata.projectbrain.repository.ContributorRepository;
import com.namrata.projectbrain.repository.PostRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostContributorController {
    private final PostRepository postRepository;
    private final ContributorRepository contributorRepository;

    @Autowired
    public PostContributorController(PostRepository postRepository,ContributorRepository contributorRepository){
        this.postRepository = postRepository;
        this.contributorRepository = contributorRepository;
    }

    @PostMapping("/add/newpost")
    public Post assignIdeaToBrain(@RequestBody PostForm postForm) {
        try {
            Contributor contributor = contributorRepository.findContributorByUsername(postForm.getUsername()).orElse(new Contributor());

            Post post = new Post();
            post.setTitle(postForm.getTitle());
            post.setContext(postForm.getContext());
            post.setContent(postForm.getContent());
            post.setCreator(contributor);

            Post returnPost = postRepository.save(post);

            contributor.getPosts().add(returnPost);

            contributorRepository.save(contributor);

            return returnPost;
        } catch (Exception e) {
            e.printStackTrace();
            return new Post();
        }
    }

    @GetMapping("/post")
    public Post findOne(@RequestParam Long id) {
        return postRepository.findIdeaById(id).orElse(new Post());
    }

    @DeleteMapping("/post/remove")
    public String removeOne(@RequestParam Long id) {
        Post post = postRepository.findById(id).orElse(new Post());
        post.getCreator().getPosts().remove(post);
        post.setCreator(null);

        postRepository.save(post);

        postRepository.deleteById(id);

        return "Success";
    }

    @PostMapping("/add/todo")
    public Contributor addIdeaToDo(@RequestBody TodoForm todoForm) {
        try {
            Post idea = postRepository.findIdeaById(todoForm.getIdeaId()).orElse(new Post());
            Contributor contributor = contributorRepository.findContributorByUsername(todoForm.getUsername()).orElse(new Contributor());

            contributor.getTodos().add(idea);

            Contributor responseContributor = contributorRepository.save(contributor);

            return responseContributor;
        } catch (Exception e) {
            e.printStackTrace();
            return new Contributor();
        }
    }
}