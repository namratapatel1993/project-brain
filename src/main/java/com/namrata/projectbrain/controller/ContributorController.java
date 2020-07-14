package com.namrata.projectbrain.controller;

import java.util.Collection;
import java.util.HashSet;

import com.namrata.projectbrain.form.ContributorFollowForm;
import com.namrata.projectbrain.form.ContributorForm;
import com.namrata.projectbrain.form.ContributorResponseForm;
import com.namrata.projectbrain.form.PostResponseForm;
import com.namrata.projectbrain.model.Contributor;
import com.namrata.projectbrain.model.Post;
import com.namrata.projectbrain.repository.ContributorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ContributorController {
    private final ContributorRepository contributorRepository;

    @Autowired
    public ContributorController(ContributorRepository contributorRepository) {
        this.contributorRepository = contributorRepository;
    }

    @GetMapping("/contributors")
    public Collection<Contributor> findAll() {
        return contributorRepository.findAll();
    }

    @PostMapping("/contributor/register")
    public Contributor save(@RequestBody ContributorForm userForm) {
        Contributor contributor = new Contributor();
        contributor.setEmail(userForm.getEmail());
        contributor.setPassword(userForm.getPassword());
        contributor.setUsername(userForm.getUsername());
        contributor.setFirstname(userForm.getFirstname());
        contributor.setLastname(userForm.getLastname());
        contributor.setCity(userForm.getCity());
        return contributorRepository.save(contributor);
    }

    @PostMapping("/contributor/login")
    public Contributor saveLogContributor(@RequestBody ContributorForm contributorForm) {
        Contributor contributor = contributorRepository.findContributorByEmailAndPassword(contributorForm.getEmail(), contributorForm.getPassword()).orElse(new Contributor());
        return contributor;
    }

    @PostMapping("/contributor/update")
    public Contributor updateContributor(@RequestBody ContributorForm contributorForm) {
        Contributor contributor = contributorRepository.findContributorByUsername(contributorForm.getUsername()).orElse(new Contributor());
        contributor.setFirstname(contributorForm.getFirstname());
        contributor.setLastname(contributorForm.getLastname());
        contributor.setCity(contributorForm.getCity());
        Contributor returnContributor = contributorRepository.save(contributor);
        return returnContributor;
    }
    
    @GetMapping(value = "/contributor/{username}/posts")
    public PostResponseForm getPostUsername(@PathVariable String username) {
        PostResponseForm responseForm = new PostResponseForm();
        try {
            responseForm.setData(contributorRepository.findContributorByUsername(username).orElse(new Contributor()).getPosts());
        } catch (Exception e) {
            e.printStackTrace();
            responseForm.setData(new HashSet<Post>());
        }
        return responseForm;
    }

    @GetMapping("/contributor/{username}/todos")
    public PostResponseForm getTodosUsername(@PathVariable String username) {
        PostResponseForm responseForm = new PostResponseForm();
        try {
            responseForm.setData(contributorRepository.findContributorByUsername(username).orElse(new Contributor()).getTodos());
        } catch (Exception e) {
            e.printStackTrace();
            responseForm.setData(new HashSet<Post>());
        }
        return responseForm;
    }

    @PostMapping("/contributor/follow")
    public Contributor follow(@RequestBody ContributorFollowForm contributorForm) {
        Contributor contributorToFollow = contributorRepository.findContributorByUsername(contributorForm.getUsername()).orElse(new Contributor());
        Contributor contributorToFollowed = contributorRepository.findContributorByUsername(contributorForm.getUsernameToBeFollowed()).orElse(new Contributor());
        
        contributorToFollow.getFollowers().add(contributorToFollowed);

        Contributor responseBrain = contributorRepository.save(contributorToFollow);

        return responseBrain;
    }

    @GetMapping("/contributor/{username}/followers")
    public ContributorResponseForm getFollowersUsername(@PathVariable String username) {
        ContributorResponseForm responseForm = new ContributorResponseForm();

        try {
            responseForm.setData(contributorRepository.findContributorByUsername(username).orElse(new Contributor()).getFollowers());

        } catch (Exception e) {
            e.printStackTrace();
            
            responseForm.setData(new HashSet<Contributor>());
        }
        return responseForm;
    }


}