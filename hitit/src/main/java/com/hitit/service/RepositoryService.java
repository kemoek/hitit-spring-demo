package com.hitit.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hitit.dto.UserDto;
import com.hitit.entity.RepositoryTable;
import com.hitit.entity.UserDetail;
import com.hitit.mapper.UserMapper;
import com.hitit.repository.RepositoryRepository;
import com.hitit.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class RepositoryService {

    private final RestTemplate restTemplate;

    private final RepositoryRepository repositoryRepository;


    @Autowired
    public RepositoryService(RepositoryRepository repositoryRepository, UserRepository userRepository, RestTemplate restTemplate, UserMapper userMapper ) {
        this.repositoryRepository = repositoryRepository;
        this.restTemplate = restTemplate;
    }

    public String getRepositoriesInfo() {
        final String GITHUB_GET_REPOS_API = "https://api.github.com/orgs/apache/repos";
        return restTemplate.getForObject(GITHUB_GET_REPOS_API, String.class);
    }

    @Transactional
    public List<RepositoryTable> createRepositories() {

        // most downloaded repo'ları bulurken API Rate Limit'e takıldığım için ilk 5 repo'yu statik vermek durumunda kaldım
        List<String> repoNames = new ArrayList<>();
        repoNames.add("echarts");
        repoNames.add("superset");
        repoNames.add("dubbo");
        repoNames.add("spark");
        repoNames.add("airflow");
        for(int i=0; i<repoNames.size(); i++) {
            RepositoryTable tempRepo = new RepositoryTable();
            tempRepo.setRepositoryName(repoNames.get(i));
            repositoryRepository.saveAndFlush(tempRepo);
        }
        System.out.println("Repolar kaydedildi.");
        List<RepositoryTable> allRepos = repositoryRepository.findAll();
        return allRepos;
    }


}
