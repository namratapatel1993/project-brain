package com.namrata.projectbrain.model;

import java.util.Set;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class Contributor {

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

    @Column(unique = false, nullable = true)
    private String city;

    @JsonIgnore
    @Column(unique = false, nullable = false)
    private String password;

    @ManyToMany
    private Set<Contributor> followers;

    @OneToMany
    private Set<Post> todos;

    @OneToMany
    @JsonIgnore
    private Set<Post> posts;
    
}