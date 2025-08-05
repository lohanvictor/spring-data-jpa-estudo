package com.bookstore.jpa.controllers;

import com.bookstore.jpa.domain.book.Book;
import com.bookstore.jpa.domain.book.BookRequestDTO;
import com.bookstore.jpa.services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bookstore/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<Book> postBook(@RequestBody BookRequestDTO bookRequestDTO) {
        Book book = this.bookService.save(bookRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(book);
    }
}
