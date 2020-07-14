package com.namrata.projectbrain.form;

import lombok.Data;

@Data
public class ContributorForm {
    private String email;
    private String username;
    private String firstname;
    private String lastname;
    private String password;
    private String city;
}