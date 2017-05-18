package com.books.repository;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.books.model.BookAuthor;

@Repository
public class BooksRepository {
	
	private ConcurrentMap<String, BookAuthor> books = new ConcurrentHashMap<>();
	
	public List<BookAuthor> list() {
		return books.values().stream().collect(Collectors.toList());
	}
	
	public BookAuthor get(String isbn) {
		return books.get(isbn);
	}
	
	public BookAuthor create(BookAuthor book) {
		books.put(book.getIsbn(), book);
		return book;
	}
	
}