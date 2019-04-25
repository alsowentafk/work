package com.repositorys;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.models.User;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class UserRepository {
	private static final String PATH_TO_FILE = "./uploads/database.json";
	private Gson gson = new Gson();

	public void save(User user) {
		try {
			String json = gson.toJson(user);
			Files.write(Paths.get(PATH_TO_FILE), (json + '\n').getBytes(), StandardOpenOption.APPEND);
			log.info("Serializable succssesful"+json+" to "+PATH_TO_FILE);
		} catch (IOException e) {
			log.error(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}
	}

	public User getById(Long id) {
		try {
			return gson.fromJson(deserialize().stream().filter(u -> gson.fromJson(u, User.class).getId().equals(id))
					.findFirst().orElse(null), User.class);
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public void update(User user) {
		try {
			List<String> out = new ArrayList<>(Files.readAllLines(Paths.get(PATH_TO_FILE), StandardCharsets.UTF_8));
			out.removeIf(u -> gson.fromJson(u, User.class).getId().equals(user.getId()));
			out.add(gson.toJson(user));
			Files.write(Paths.get(PATH_TO_FILE), out, StandardCharsets.UTF_8);
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public void delete(Long id) {
		try {
			List<String> out = new ArrayList<>(Files.readAllLines(Paths.get(PATH_TO_FILE), StandardCharsets.UTF_8));
			out.removeIf(u -> gson.fromJson(u, User.class).getId().equals(id));
			Files.write(Paths.get(PATH_TO_FILE), out, StandardCharsets.UTF_8);
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	private List<String> deserialize() throws FileNotFoundException, IOException {
		List<String> jsonList = Files.readAllLines(Paths.get(PATH_TO_FILE));
		log.info("Deserialize succssesful"+" jsonList"+" from "+PATH_TO_FILE);
		return jsonList;
	}
}
