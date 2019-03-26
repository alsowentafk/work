package com.repositorys;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import com.models.User;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class UserRepository {
	private static Map<Long, User> userRepositoryTemplate = new HashMap<Long, User>();
	private static String path = "./uploads/database.json";

	private void serializable(Map<Long, User> template) {
		Gson gson = new Gson();
		String json = gson.toJson(template);
		try (FileWriter file = new FileWriter(path)) {

			file.write(json);
			file.flush();
			log.info("Serializable succssesful");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Map<Long, User> deserialize() throws FileNotFoundException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		Map<Long, User> template = mapper.readValue(new File(path), new TypeReference<Map<Long, User>>() {
		});
		log.info("Deserialize succssesful");
		return template;
	}

	public UserRepository() throws IOException, FileNotFoundException {
		userRepositoryTemplate = deserialize();
	}

	public void save(User user) {
		userRepositoryTemplate.put(user.getId(), user);
		serializable(userRepositoryTemplate);
	}

	public User getbyId(Long id) {
		try {
			userRepositoryTemplate = deserialize();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		User user = userRepositoryTemplate.get(id);
		return user;
	}

	public void update(User user) {
		userRepositoryTemplate.put(user.getId(), user);
		serializable(userRepositoryTemplate);
	}

	public void delete(Long id) {
		userRepositoryTemplate.remove(id);
		serializable(userRepositoryTemplate);
	}
}
