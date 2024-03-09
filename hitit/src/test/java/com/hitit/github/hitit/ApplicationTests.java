package com.hitit.github.hitit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hitit.api.GithubController;
import com.hitit.repository.RepositoryRepository;
import com.hitit.service.RepositoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class ApplicationTests {

	@Autowired
	private GithubController githubController;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private RepositoryService repositoryService;


	@Autowired
	private RepositoryRepository repositoryRepository;

	private final String GITHUB_API_URL = "https://api.github.com";


	@Test
	void contextLoads() {
	}

	@Test
	void reposApi() {
		assertThat(githubController).isNotNull();
	}

	@Test
	void getReposShouldReturnRepos() {
		assertThat(this.repositoryService.getRepositoriesInfo()).isNotNull();
		assertThat(this.repositoryService.createRepositories().get(0).getRepositoryName()).isNotNull();
	}

	@Test
	void repoSizeShouldReturnMaximum5() {
		assertThat(repositoryRepository.findAll().size()).isLessThanOrEqualTo(5);
	}

	@Test
	void userSizeOfRepositoryShouldReturnNotNull() throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		String repositoriesInfo = this.repositoryService.getRepositoriesInfo();
		JsonNode jsonNode = objectMapper.readTree(repositoriesInfo);
		if (jsonNode.size() > 0){
			String jsonResponse = restTemplate.getForObject(
					GITHUB_API_URL + "/repos/apache/" + jsonNode.get(0).findValue("name").asText() + "/contributors" , String.class);
			jsonNode = objectMapper.readTree(jsonResponse);
			assertThat(jsonNode.size()).isNotNull();
		}

	}


}
