package com.books.model;

public class Book {
	
	private String isbn;
	private String title;
	
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Override
	public String toString() {
		return String.format("%s-%s", getIsbn(), getTitle());
	}
	
}
