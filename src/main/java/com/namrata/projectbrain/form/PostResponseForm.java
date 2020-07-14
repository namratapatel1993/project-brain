package com.namrata.projectbrain.form;


import java.util.Set;

import com.namrata.projectbrain.model.Post;

import lombok.Data;

@Data
public class PostResponseForm {
    private Set<Post> data;
}