package com.alura.demo.repository;

import com.alura.demo.Model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepositoty extends JpaRepository<Author, Long> {
}
