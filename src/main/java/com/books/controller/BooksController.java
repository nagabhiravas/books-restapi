package com.books.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.books.model.BookAuthor;
import com.books.service.BookService;

@RestController
public class BooksController {
	
	@Autowired
	private BookService bookService;
	
	@RequestMapping(value="books", method=RequestMethod.GET)
	public List<BookAuthor> list() {
		return bookService.getBooks();
	}
	
	@RequestMapping(value="books", method=RequestMethod.POST)
	public BookAuthor create(@RequestBody BookAuthor book) {
		return bookService.createBook(book);
	}
	
	@RequestMapping(value="books/{isbn}", method=RequestMethod.GET)
	public BookAuthor get(@PathVariable String isbn) {
		return bookService.getBook(isbn);
	}
	
}