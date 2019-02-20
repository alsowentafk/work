package com.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String content;

	public void setId(long ID) {
		this.id = ID;
	}

	public void setContent(String Content) {
		this.content = Content;
	}

	public long getId() {

		return id;
	}

	public String getContent() {

		return content;
	}
}