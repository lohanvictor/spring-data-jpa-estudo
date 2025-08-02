package com.bookstore.jpa.repositories;

import com.bookstore.jpa.domain.author.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AuthorRepository extends JpaRepository<Author, UUID> {
}
