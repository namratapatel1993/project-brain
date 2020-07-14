package com.namrata.projectbrain.form;

import javax.persistence.Column;

import lombok.Data;

@Data
public class ContributorFollowForm {
    
    @Column(name="username")
    private String username;

    @Column(name="username")
    private String usernameToBeFollowed;
}