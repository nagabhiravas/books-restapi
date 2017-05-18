package com.books.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static com.books.data.AuthorsTestData.*;
import static com.books.data.BooksTestData.*;
import com.books.model.Author;
import com.books.model.AuthorBooks;
import com.books.model.Book;
import com.books.service.BookService;

public class AuthorsControllerTest {
	@InjectMocks
	private AuthorsController ac;
	
	@Mock
	private BookService bookService;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetAuthor() {
		Author author = new Author();
		author.setFirstName(FIRST_NAMES[0]);
		author.setLastName(LAST_NAMES[0]);
		AuthorBooks ab = new AuthorBooks(author);
		Book book = new Book();
		book.setIsbn(ISBNS[0]);
		book.setTitle(TITLES[0]);
		ab.setBooks(Arrays.asList(book));
		
		when(bookService.getAuthor(FIRST_NAMES[0], LAST_NAMES[0], null)).thenReturn(ab);
		AuthorBooks abTest = ac.getAuthor(FIRST_NAMES[0], LAST_NAMES[0], null);
		verify(bookService).getAuthor(FIRST_NAMES[0], LAST_NAMES[0], null);
		
		assertThat(abTest.getFirstName(), is(FIRST_NAMES[0]));
		assertThat(abTest.getLastName(), is(LAST_NAMES[0]));
		assertThat(abTest.getBooks().size(), is(1));
	}
	
	@Test
	public void testCreateAuthor() {
		Author author = new Author();
		author.setFirstName(FIRST_NAMES[1]);
		author.setLastName(LAST_NAMES[1]);
		
		when(bookService.createAuthor(FIRST_NAMES[1], LAST_NAMES[1], null)).thenReturn(author);
		when(ac.createAuthor(author)).thenReturn(author);
		Author a = ac.createAuthor(author);
		verify(bookService).createAuthor(FIRST_NAMES[1], LAST_NAMES[1], null);
		
		assertThat(a.getFirstName(), is(FIRST_NAMES[1]));
		assertThat(a.getLastName(), is(LAST_NAMES[1]));
	}
}
