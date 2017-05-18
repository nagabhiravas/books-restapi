package com.books.model;

public class BookAuthor extends Book {
	private Author author;

	public BookAuthor() {}
	
	public BookAuthor(String isbn, String title) {
		setIsbn(isbn);
		setTitle(title);
	}
	
	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}
	@Override
	public String toString() {
		return String.format("%s-%s", super.toString(), getAuthor());
	}
}
