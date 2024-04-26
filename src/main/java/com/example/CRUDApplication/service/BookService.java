package com.example.CRUDApplication.service;

import java.util.List;
import java.util.Optional;

import com.example.CRUDApplication.model.Book;

public interface BookService {

	public List<Book> getAllBooks();

	public Optional<Book> getOneBook(Long id);

	public Book addBook(Book book);

	public Book updateBook(Long id, Book book);

	public void deleteBook(Long id);

}
