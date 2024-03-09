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
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final RestTemplate restTemplate;

    private final RepositoryRepository repositoryRepository;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private final String GITHUB_API_URL = "https://api.github.com";

    public void createContributions() throws JsonProcessingException, InterruptedException {

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResponse;
        List<RepositoryTable> repositoriesList = repositoryRepository.findAll();
        for(int i=0; i<repositoriesList.size(); i++) { // repo'lar için for döngüsü
            jsonResponse = restTemplate.getForObject(GITHUB_API_URL + "/repos/apache/" + repositoriesList.get(i).getRepositoryName() + "/contributors" , String.class);
            JsonNode jsonNode = objectMapper.readTree(jsonResponse);

            List<UserDto> userDtoList = new ArrayList<>();
            for(int j=0; j<jsonNode.size() && j<10; j++) { // user'lar için for döngüsü // j = max 9 olmalı
                UserDto tempUser = new UserDto();
                tempUser.setContributions(jsonNode.get(j).get("contributions").asInt());
                tempUser.setUsername(jsonNode.get(j).get("login").asText());
                tempUser.setRepository_id(repositoriesList.get(i));
                userDtoList.add(tempUser);
            } // buradaki for döngüsü bitince bir repodaki bütün katkı yapan kullanıcıları çekmiş oluyoruz

            getUsers(userDtoList);
        }

    }

    public void getUsers(List<UserDto> userList) throws JsonProcessingException, InterruptedException {
        String userDetails;
        ObjectMapper objectMapper = new ObjectMapper();
        //kullanılacak api -> /users/{username}
        int i = 0;
        while (i < 10 && i < userList.size()) {   //  ilk 10 kullanıcının bilgileri alınmalıdır
            userDetails = restTemplate.getForObject(GITHUB_API_URL + "/users/" + userList.get(i).getUsername(), String.class);
            JsonNode jsonNode = objectMapper.readTree(userDetails);
            if (jsonNode != null) {
                if (jsonNode.get("location") != null) {
                    userList.get(i).setLocation(jsonNode.get("location").asText());
                }
                if (jsonNode.get("company") != null) {
                    userList.get(i).setCompany(jsonNode.get("company").asText());
                }
            }
            i++;
            Thread.sleep(200); // 0.2s
        }
        List<UserDetail> saveUserDetailList = userMapper.convertToEntityList(userList);
        userRepository.saveAll(saveUserDetailList);

    }

}
