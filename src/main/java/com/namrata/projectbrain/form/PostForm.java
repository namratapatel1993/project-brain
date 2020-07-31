package com.namrata.projectbrain.form;

import java.util.Set;

import com.namrata.projectbrain.model.Post;

import lombok.Data;

@Data
public class PostForm {
    private String id;
    private String citeId;
    private String title;
    private String context;
    private String content;
    private String username;
    private Set<Post> data;
}   