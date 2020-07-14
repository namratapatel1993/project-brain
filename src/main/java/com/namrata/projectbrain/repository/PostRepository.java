package com.namrata.projectbrain.repository;

import java.util.Optional;

import com.namrata.projectbrain.model.Post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findIdeaById(Long id); 
}
