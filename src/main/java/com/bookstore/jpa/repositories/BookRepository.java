package com.bookstore.jpa.repositories;

import com.bookstore.jpa.domain.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {

    boolean existsByTitle(String title);

    boolean existsById(UUID id);
}
