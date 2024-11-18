package com.alura.demo.repository;

import com.alura.demo.Model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LivrosRepository extends JpaRepository<Livro, Long> {

    Optional<Livro> findByTitulo(String titulo);

    Livro findByAutoresName(String autor);
}
