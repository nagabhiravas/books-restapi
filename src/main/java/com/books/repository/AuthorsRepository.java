package com.books.repository;

import java.time.LocalDate;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.stereotype.Repository;
import com.books.model.Author;

@Repository
public class AuthorsRepository {
	private ConcurrentMap<String, Author> authors = new ConcurrentHashMap<>();
	
	public Author get(String firstName, String lastName) {
		return authors.get(firstName+"-"+lastName);
	}
	
	public Author create(String firstName, String lastName, LocalDate dateOfBirth) {
		Author author = new Author();
		author.setFirstName(firstName);
		author.setLastName(lastName);
		author.setDateOfBirth(dateOfBirth);
		authors.put(author.getFirstName()+"-"+author.getLastName(), author);
		return author;
	}
}