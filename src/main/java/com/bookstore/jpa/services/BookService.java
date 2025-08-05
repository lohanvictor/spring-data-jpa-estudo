package com.bookstore.jpa.services;

import com.bookstore.jpa.domain.author.Author;
import com.bookstore.jpa.domain.book.Book;
import com.bookstore.jpa.domain.book.BookRequestDTO;
import com.bookstore.jpa.domain.publisher.Publisher;
import com.bookstore.jpa.domain.review.Review;
import com.bookstore.jpa.repositories.AuthorRepository;
import com.bookstore.jpa.repositories.BookRepository;
import com.bookstore.jpa.repositories.PublisherRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository, PublisherRepository publisherRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.publisherRepository = publisherRepository;
    }

    @Transactional
    public Book save(BookRequestDTO bookRequestDTO) {
        Book book = new Book();
        Publisher publisher = this.publisherRepository.findById(bookRequestDTO.publisherId()).orElseThrow();
        Set<Author> authors = new HashSet<>(this.authorRepository.findAllById(bookRequestDTO.authorsId()));
        book.setTitle(bookRequestDTO.title());
        book.setPublisher(publisher);
        book.setAuthors(authors);

        Review review = new Review();
        review.setComment(bookRequestDTO.reviewComment());
        review.setBook(book);
        book.setReview(review);

        return this.bookRepository.save(book);
    }

    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }

    @Transactional
    public void delete(UUID id){
        this.bookRepository.deleteById(id);
    }
}
