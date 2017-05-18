package com.books.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import static com.books.data.AuthorsTestData.*;
import static com.books.data.BooksTestData.*;
import com.books.model.Author;
import com.books.model.BookAuthor;
import com.books.service.BookService;

public class BooksControllerTest {
	
	@InjectMocks
	private BooksController bc;
	
	@Mock
	private BookService bookService;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testList() {
		List<BookAuthor> b = new ArrayList<>();
		when(bookService.getBooks()).thenReturn(b);
		List<BookAuthor> books = bc.list();
		verify(bookService).getBooks();
		assertEquals(b.size(), books.size());
	}
	
	@Test
	public void testBookGet() {
		Author author = new Author();
		author.setFirstName(FIRST_NAMES[0]);
		author.setLastName(LAST_NAMES[0]);
		BookAuthor ba = new BookAuthor(ISBNS[0], TITLES[0]);
		ba.setAuthor(author);
				
		when(bookService.getBook(ISBNS[0])).thenReturn(ba);
		BookAuthor b = bc.get(ISBNS[0]);
		verify(bookService).getBook(ISBNS[0]);
		
		assertEquals(ISBNS[0], b.getIsbn());
		assertEquals(TITLES[0], b.getTitle());
		assertEquals(FIRST_NAMES[0], b.getAuthor().getFirstName());
		assertEquals(LAST_NAMES[0], b.getAuthor().getLastName());
	}
	
	@Test
	public void testBookCreate() {
		Author author = new Author();
		author.setFirstName(FIRST_NAMES[1]);
		author.setLastName(LAST_NAMES[1]);
		BookAuthor ba = new BookAuthor(ISBNS[1], TITLES[1]);
		ba.setAuthor(author);
				
		when(bookService.createBook(ba)).thenReturn(ba);
		BookAuthor b = bc.create(ba);
		verify(bookService).createBook(ba);
		
		assertEquals(ISBNS[1], b.getIsbn());
		assertEquals(TITLES[1], b.getTitle());
		assertEquals(FIRST_NAMES[1], b.getAuthor().getFirstName());
		assertEquals(LAST_NAMES[1], b.getAuthor().getLastName());
	}
}
