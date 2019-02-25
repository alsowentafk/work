package com.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import com.models.User;

@Service
public class UserServiceJDBC {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public User getUser(Long id) {
		String sql = "SELECT id, content FROM user WHERE id = ?";
		RowMapper<User> rowMapper = new BeanPropertyRowMapper<User>(User.class);
		User user = jdbcTemplate.queryForObject(sql, rowMapper, id);
		return user;
	}

	public void saveUser(User user) {
		String sql = "INSERT INTO user (id, content) values (?, ?)";
		jdbcTemplate.update(sql, user.getId(), user.getContent());
	}

	public void deleteUser(Long id) {
		String sql = "DELETE FROM user WHERE id=?";
		jdbcTemplate.update(sql, id);
	}

	public void update(User user) {
		String sql = "UPDATE user SET content=? WHERE id=?";
		jdbcTemplate.update(sql, user.getContent(), user.getId());
	}

}
