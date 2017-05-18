package com.books.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.books.model.Author;
import com.books.model.AuthorBooks;
import com.books.service.BookService;

@RestController
public class AuthorsController {
	
	@Autowired
	private BookService bookService;
	
	@RequestMapping(value="authors", method=RequestMethod.POST)
	public Author createAuthor(@RequestBody Author author) {
		return bookService.createAuthor(author.getFirstName(), author.getLastName(), author.getDateOfBirth());
	}
	
	@RequestMapping(value="authors/{firstName}/{lastName}", method=RequestMethod.GET)
	public AuthorBooks getAuthor(@PathVariable String firstName, @PathVariable String lastName, 
		@RequestParam(required=false) @DateTimeFormat(pattern = "MM/dd/yyyy") LocalDate dateOfBirth) {
		return bookService.getAuthor(firstName, lastName, dateOfBirth);
	}
	
}