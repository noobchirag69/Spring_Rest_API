package com.example.CRUDApplication.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.CRUDApplication.model.Book;
import com.example.CRUDApplication.service.BookServiceImplementation;

@RestController
public class BookContrroller {

	@Autowired
	BookServiceImplementation service;

	@GetMapping("/getAllBooks")
	public ResponseEntity<List<Book>> getAllBooks() {

		try {

			List<Book> bookList = service.getAllBooks();

			if (bookList.isEmpty())
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);

			return new ResponseEntity<>(bookList, HttpStatus.OK);

		} catch (Exception exception) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/getBook/{id}")
	public ResponseEntity<Book> getOneBook(@PathVariable Long id) {

		try {

			Optional<Book> bookToGet = service.getOneBook(id);

			if (bookToGet.isPresent())
				return new ResponseEntity<>(bookToGet.get(), HttpStatus.OK);

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		} catch (Exception exception) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PostMapping("/addBook")
	public ResponseEntity<Book> addBook(@RequestBody Book book) {

		try {

			Book bookToAdd = service.addBook(book);
			return new ResponseEntity<>(bookToAdd, HttpStatus.OK);

		} catch (Exception exception) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PutMapping("/updateBook/{id}")
	public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book newBookData) {

		try {

			Book updatedBook = service.updateBook(id, newBookData);

			if (updatedBook != null) {
				return new ResponseEntity<>(updatedBook, HttpStatus.OK);
			}

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		} catch (Exception exception) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@DeleteMapping("/deleteBook/{id}")
	public ResponseEntity<HttpStatus> deleteBook(@PathVariable Long id) {

		try {

			service.deleteBook(id);
			return new ResponseEntity<>(HttpStatus.OK);

		} catch (Exception exception) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
