package com.books.service;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Function;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.books.model.Author;
import com.books.model.AuthorBooks;
import com.books.model.Book;
import com.books.model.BookAuthor;
import com.books.repository.AuthorsRepository;
import com.books.repository.BooksRepository;

@Service("bookService")
public class BookServiceImpl implements BookService {
	
	@Autowired
	private AuthorsRepository authorsRepository;
	
	@Autowired
	private BooksRepository booksRepository;
	
	public List<BookAuthor> getBooks() {
		return booksRepository.list();
	}
	
	public BookAuthor getBook(String isbn) {
		return booksRepository.get(isbn);
	}
	
	public BookAuthor createBook(BookAuthor book) {
		Author author = authorsRepository.get(book.getAuthor().getFirstName(), book.getAuthor().getLastName());
		if (author==null) authorsRepository.create(book.getAuthor().getFirstName(), book.getAuthor().getLastName(), book.getAuthor().getDateOfBirth());
		return booksRepository.create(book);
	}
	
	public AuthorBooks getAuthor(String firstName, String lastName, LocalDate dateOfBirth) {
		Author a = authorsRepository.get(firstName, lastName);
		AuthorBooks ab = null;
		if (a != null) {
			ab = new AuthorBooks(a);
			List<Book> books = booksRepository.list().stream().filter(b -> 
				Objects.equals(b.getAuthor().getFirstName(), a.getFirstName()) &&
				Objects.equals(b.getAuthor().getLastName(), a.getLastName()))
			.map(new Function<BookAuthor, Book>() {
                  @Override
                  public Book apply(BookAuthor ba) {
                	  Book b = new Book();
                	  b.setIsbn(ba.getIsbn());
                	  b.setTitle(ba.getTitle());
                     return b;
                  }
              }).collect(Collectors.toList());
			ab.setBooks(books);
		}
		return ab;
	}
	
	public Author createAuthor(String firstName, String lastName, LocalDate dateOfBirth) {
		return authorsRepository.create(firstName, lastName, dateOfBirth);
	}
	
}