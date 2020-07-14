package com.namrata.projectbrain.form;

import java.util.Set;

import com.namrata.projectbrain.model.Contributor;

import lombok.Data;

@Data
public class ContributorResponseForm {
    private Set<Contributor> data;
}