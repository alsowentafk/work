package com.repositorys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.models.User;

@Repository
public class UserRepositoryJDBC {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public User findOne(Long id) {
		String sql = "SELECT id, content FROM user2 WHERE id = ?";
		RowMapper<User> rowMapper = new BeanPropertyRowMapper<User>(User.class);
		User user = jdbcTemplate.queryForObject(sql, rowMapper, id);
		return user;
	}

	public void save(User user) {
		String sql = "INSERT INTO user2 (id, content) values (?, ?)";
		jdbcTemplate.update(sql, user.getId(), user.getContent());
	}

	public void update(User user) {
		String sql = "UPDATE user2 SET content=? WHERE id=?";
		jdbcTemplate.update(sql, user.getContent(), user.getId());
	}

	public void delete(Long id) {
		String sql = "DELETE FROM user2 WHERE id=?";
		jdbcTemplate.update(sql, id);
	}

}
