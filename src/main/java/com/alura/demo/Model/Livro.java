// Livro.java
package com.alura.demo.Model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "livros")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})

    @JoinTable(

            name = "livro_autor",

            joinColumns = @JoinColumn(name = "livro_id"),

            inverseJoinColumns = @JoinColumn(name = "autor_id")

    )
    private List<Author> autores = new ArrayList<Author>();

    @ElementCollection
    private List<String> idiomas = new ArrayList<>();

    // Constructors
    public Livro() {}

    public Livro(DadosLivros dados) {
        this.titulo = dados.titulo();
        this.idiomas = dados.idiomas();
        this.autores = dados.autores().stream()
                .map(Author::new)
                .collect(Collectors.toList()); // Inicializa apenas com os novos autores
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Author> getAutores() {
        return autores;
    }

    public void setAutores(List<Author> autores) {
        this.autores = autores;
    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<String> idiomas) {
        this.idiomas = idiomas;
    }
}
