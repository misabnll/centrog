package com.example.demo.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name="BlogsSeq", sequenceName = "BLOGS_SEQ", initialValue = 1, allocationSize = 10)
public class Blogs {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BlogsSeq")
	private Long id;

	@Column(nullable = false, length = 50)
	private String title;

	@Column(nullable = false, length = 4000)
	private String description;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinTable(name = "blogs_readers", joinColumns = @JoinColumn(name = "blog_id"), inverseJoinColumns = @JoinColumn(name = "reader_id"))
	private Set<Readers> readers;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Readers> getReaders() {
		return readers;
	}

	public void setReaders(Set<Readers> readers) {
		this.readers = readers;
	}
}
