package com.alura.demo.service.livros;

import com.alura.demo.Model.Author;
import com.alura.demo.Model.DadosAuthor;
import com.alura.demo.Model.DadosLivros;
import com.alura.demo.Model.Livro;
import com.alura.demo.repository.AuthorRepository;
import com.alura.demo.repository.LivrosRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LivroService {

    private final LivrosRepository livrosRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public LivroService(LivrosRepository livrosRepository, AuthorRepository authorRepository) {
        this.livrosRepository = livrosRepository;
        this.authorRepository = authorRepository;
    }

    public List<Author> processarAutores(List<DadosAuthor> dadosAutores) {
        return dadosAutores.stream()
                .map(dadosAutor -> {
                    // Verificar se o autor já existe no banco
                    return authorRepository.findByName(dadosAutor.name())
                            .orElseGet(() -> {
                                // Criar e salvar o autor se não existir
                                Author novoAutor = new Author(dadosAutor);
                                return authorRepository.save(novoAutor);
                            });
                })
                .collect(Collectors.toList());
    }

    @Transactional
    public Livro salvarLivro(DadosLivros dadosLivro) {
        // Processar autores para garantir que estão no contexto do JPA
        List<Author> autoresGerenciados = processarAutores(dadosLivro.autores());

        Livro livro = new Livro(dadosLivro);
        livro.setAutores(autoresGerenciados);

        return livrosRepository.findByTitulo(livro.getTitulo()).orElseGet(() -> livrosRepository.save(livro));
    }

    public List<Livro> findAllLivros() {
        return livrosRepository.findAll();
    }

    public Livro findByAutor(String autor) {
        return livrosRepository.findByAutoresName(String.valueOf(autor));
    }
    public List<Author> findAllAutor() {
        return authorRepository.findAll();
    }

}
