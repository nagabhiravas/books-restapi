package com.books.service;

import static com.books.data.AuthorsTestData.FIRST_NAMES;
import static com.books.data.AuthorsTestData.LAST_NAMES;
import static com.books.data.BooksTestData.ISBNS;
import static com.books.data.BooksTestData.TITLES;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.books.model.Author;
import com.books.model.AuthorBooks;
import com.books.model.Book;
import com.books.model.BookAuthor;
import com.books.repository.AuthorsRepository;
import com.books.repository.BooksRepository;
import com.books.service.BookService;

public class BookServiceTest {
	
	@InjectMocks
	private BookService bookService = new BookServiceImpl();
	
	@Mock
	private BooksRepository booksRepository;
	
	@Mock
	private AuthorsRepository authorsRepository;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetBooks() {
		//ConcurrentMap<String, BookAuthor> books = new ConcurrentHashMap<>();
		List<BookAuthor> b = new ArrayList<>();
		when(booksRepository.list()).thenReturn(b);
		List<BookAuthor> books = bookService.getBooks();
		verify(booksRepository).list();
		assertEquals(b.size(), books.size());
	}
	
	@Test
	public void testGetBook() {
		Author author = new Author();
		author.setFirstName(FIRST_NAMES[0]);
		author.setLastName(LAST_NAMES[0]);
		BookAuthor ba = new BookAuthor(ISBNS[0], TITLES[0]);
		ba.setAuthor(author);
				
		when(booksRepository.get(ISBNS[0])).thenReturn(ba);
		BookAuthor b = bookService.getBook(ISBNS[0]);
		verify(booksRepository).get(ISBNS[0]);
		
		assertEquals(ISBNS[0], b.getIsbn());
		assertEquals(TITLES[0], b.getTitle());
		assertEquals(FIRST_NAMES[0], b.getAuthor().getFirstName());
		assertEquals(LAST_NAMES[0], b.getAuthor().getLastName());
	}
	
	@Test
	public void testCreateBook() {
		Author author = new Author();
		author.setFirstName(FIRST_NAMES[1]);
		author.setLastName(LAST_NAMES[1]);
		BookAuthor ba = new BookAuthor(ISBNS[1], TITLES[1]);
		ba.setAuthor(author);
				
		when(booksRepository.create(ba)).thenReturn(ba);
		BookAuthor b = bookService.createBook(ba);
		verify(booksRepository).create(ba);
		
		assertEquals(ISBNS[1], b.getIsbn());
		assertEquals(TITLES[1], b.getTitle());
		assertEquals(FIRST_NAMES[1], b.getAuthor().getFirstName());
		assertEquals(LAST_NAMES[1], b.getAuthor().getLastName());
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
		
		when(authorsRepository.get(FIRST_NAMES[0], LAST_NAMES[0])).thenReturn(ab);
		AuthorBooks abTest = bookService.getAuthor(FIRST_NAMES[0], LAST_NAMES[0], null);
		verify(authorsRepository).get(FIRST_NAMES[0], LAST_NAMES[0]);
		
		assertThat(abTest.getFirstName(), is(FIRST_NAMES[0]));
		assertThat(abTest.getLastName(), is(LAST_NAMES[0]));
	}
	
	@Test
	public void testCreateAuthor() {
		Author author = new Author();
		author.setFirstName(FIRST_NAMES[1]);
		author.setLastName(LAST_NAMES[1]);
		
		when(authorsRepository.create(FIRST_NAMES[0], LAST_NAMES[0], null)).thenReturn(author);
		Author a = bookService.createAuthor(FIRST_NAMES[0], LAST_NAMES[0], null);
		verify(authorsRepository).create(FIRST_NAMES[0], LAST_NAMES[0], null);
		
		assertThat(a.getFirstName(), is(FIRST_NAMES[1]));
		assertThat(a.getLastName(), is(LAST_NAMES[1]));
	}
	
}
