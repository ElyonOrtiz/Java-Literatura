package com.alura.demo.service.livros;

import com.alura.demo.Model.Author;
import com.alura.demo.Model.DadosLivros;
import com.alura.demo.Model.Livro;
import com.alura.demo.repository.LivrosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LivroService {

    @Autowired
    private LivrosRepository livroRepository;
    @Autowired
    private LivrosRepository authorReposity;

    public Livro salvarLivro(Livro livro) {
        return livroRepository.save(livro);
    }
}
