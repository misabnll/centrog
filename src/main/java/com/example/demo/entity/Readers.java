package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name="ReadersSeq", sequenceName = "READERS_SEQ", initialValue = 1, allocationSize = 10)
public class Readers {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ReadersSeq")
	private Long id;

	@Column(nullable = false, length = 15)
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
