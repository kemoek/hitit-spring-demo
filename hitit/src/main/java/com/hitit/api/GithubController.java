package com.hitit.api;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.hitit.service.RepositoryService;
import com.hitit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class GithubController {

    private final RepositoryService repositoryService;
    private final UserService userService;

    @Autowired
    public GithubController(RepositoryService repositoryService, UserService userService) {
        this.repositoryService = repositoryService;
        this.userService = userService;
    }

    @GetMapping("/get-all-repos")
    public String getAllRepos() {
        return repositoryService.getRepositoriesInfo();
    }

    @GetMapping("/create-repos") //PostMapping olmalı
    public ResponseEntity<String> createRepos(){
        repositoryService.createRepositories();
        return new ResponseEntity<>("Repos successfully created.", HttpStatus.CREATED);
    }

    @GetMapping("/create-contributions")
    public ResponseEntity<String> createContributions() throws JsonProcessingException, InterruptedException {
        userService.createContributions();
        return new ResponseEntity<>("Contributors successfully created.", HttpStatus.CREATED);

    }

}
