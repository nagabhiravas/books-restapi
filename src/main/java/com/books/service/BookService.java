package com.books.service;

import java.time.LocalDate;
import java.util.List;

import com.books.model.Author;
import com.books.model.AuthorBooks;
import com.books.model.BookAuthor;

public interface BookService {
	public List<BookAuthor> getBooks();
	public BookAuthor getBook(String isbn);
	public BookAuthor createBook(BookAuthor book);
	public AuthorBooks getAuthor(String firstName, String lastName, LocalDate dateOfBirth);
	public Author createAuthor(String firstName, String lastName, LocalDate dateOfBirth);
}
