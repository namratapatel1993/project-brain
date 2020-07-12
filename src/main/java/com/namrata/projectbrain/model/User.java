package com.namrata.projectbrain.model;


import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = false, nullable = true)
    private String firstname;

    @Column(unique = false, nullable = true)
    private String lastname;

    @JsonIgnore
    @Column(unique = false, nullable = false)
    private String password;
    
}