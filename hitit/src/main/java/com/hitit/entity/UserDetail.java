package com.hitit.entity;

import javax.persistence.*;

@Entity
public class UserDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String location;
    private String company;
    private int contributions;

    @ManyToOne
    @JoinColumn(name = "repository_id")
    private RepositoryTable repository;

    public UserDetail(String username, String location, String company, int contributions, RepositoryTable repository) {
        this.username = username;
        this.location = location;
        this.company = company;
        this.contributions = contributions;
        this.repository = repository;
    }

    public UserDetail() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getContributions() {
        return contributions;
    }

    public void setContributions(int contributions) {
        this.contributions = contributions;
    }

    public RepositoryTable getRepository() {
        return repository;
    }

    public void setRepository(RepositoryTable repository) {
        this.repository = repository;
    }
}
