package com.alura.demo.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "autores")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_autor")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("birth_year")
    private Integer birthYear;

    @JsonProperty("death_year")
    private Integer deathYear;

    @ManyToMany(mappedBy = "autores", fetch = FetchType.EAGER)
    private List<Livro> livros = new ArrayList<>();

    // Default constructor
    public Author(DadosAuthor dados) {
        this.name = dados.name();
        this.birthYear = dados.birthYear();
        this.deathYear = dados.deathYear();
    }
    public Author() {}
    public void adicionarLivro(Livro livro) {
        if (!this.livros.contains(livro)) {
            this.livros.add(livro);
            livro.getAutores().add(this); // Adiciona referência inversa
        } // adiciona a referência inversa
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    public Integer getDeathYear() {
        return deathYear;
    }

    public void setDeathYear(Integer deathYear) {
        this.deathYear = deathYear;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }

}