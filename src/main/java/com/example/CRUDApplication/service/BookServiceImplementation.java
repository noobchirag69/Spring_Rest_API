package com.example.CRUDApplication.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CRUDApplication.model.Book;
import com.example.CRUDApplication.repo.BookRepo;

@Service
public class BookServiceImplementation implements BookService {

	@Autowired
	private BookRepo bookRepo;

	@Override
	public List<Book> getAllBooks() {

		List<Book> bookList = new ArrayList<>();
		bookRepo.findAll().forEach(bookList::add);

		return bookList;

	}

	@Override
	public Optional<Book> getOneBook(Long id) {

		Optional<Book> book = bookRepo.findById(id);
		return book;

	}

	@Override
	public Book addBook(Book book) {

		Book newBook = bookRepo.save(book);
		return newBook;

	}

	@Override
	public Book updateBook(Long id, Book book) {

		Optional<Book> oldBook = bookRepo.findById(id);

		if (oldBook.isPresent()) {

			Book newBook = oldBook.get();

			newBook.setTitleString(book.getTitleString());
			newBook.setAuthorString(book.getAuthorString());

			Book bookObj = bookRepo.save(newBook);
			return bookObj;

		}

		return null;

	}

	@Override
	public void deleteBook(Long id) {

		bookRepo.deleteById(id);

	}

}
