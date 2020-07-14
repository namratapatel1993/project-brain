package com.namrata.projectbrain.repository;

import java.util.Optional;

import com.namrata.projectbrain.model.Contributor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContributorRepository extends JpaRepository<Contributor, Long> {

    Optional<Contributor> findContributorByUsername(String username);
    Optional<Contributor> findContributorByEmailAndPassword(String email, String password);
}