package com.books.model;

import java.util.List;

public class AuthorBooks extends Author {
	private List<Book> books;
	public AuthorBooks() {}
	public AuthorBooks(Author author) {
		setFirstName(author.getFirstName());
		setLastName(author.getLastName());
		setDateOfBirth(author.getDateOfBirth());
	}
	public List<Book> getBooks() {
		return this.books;
	}
	public void setBooks(List<Book> books) {
		this.books = books;
	}
	@Override
	public String toString() {
		return String.format("%s-%s", super.toString(), books);
	}
}
