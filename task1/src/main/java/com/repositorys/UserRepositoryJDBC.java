package com.repositorys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.models.User;

@Transactional
@Repository
public class UserRepositoryJDBC {

	@Autowired
	@Qualifier("jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	public User findOne(Long id) {
		String sql = "SELECT id, content FROM user WHERE id = ?";
		RowMapper<User> rowMapper = new BeanPropertyRowMapper<User>(User.class);
		User user = jdbcTemplate.queryForObject(sql, rowMapper, id);
		return user;
	}

	public void save(User user) {
		String sql = "INSERT INTO user (id, content) values (?, ?)";
		jdbcTemplate.update(sql, user.getId(), user.getContent());
	}

	public void update(User user) {
		String sql = "UPDATE user SET content=? WHERE id=?";
		jdbcTemplate.update(sql, user.getContent(), user.getId());
	}

	public void delete(Long id) {
		String sql = "DELETE FROM user WHERE id=?";
		jdbcTemplate.update(sql, id);
	}

}
