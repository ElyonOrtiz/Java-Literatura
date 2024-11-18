package com.alura.demo.principal;

import com.alura.demo.Model.*;
import com.alura.demo.repository.AuthorRepository;
import com.alura.demo.repository.LivrosRepository;
import com.alura.demo.service.ConsumoApi;
import com.alura.demo.service.ConverteDados;
import com.alura.demo.service.livros.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class Principal {
    private Scanner leitor = new Scanner(System.in);
    private ConsumoApi consumoApi = new ConsumoApi();
    private ConverteDados converter = new ConverteDados();
    private final LivroService livroService;

    @Autowired
    public Principal(LivrosRepository livrosRepository, LivroService livroService){
        this.livroService = livroService;
    }
    public void exibeMenu(){
        var opcao = -1;
        while (opcao != 0){
            var menu = """
                    1- Cadastrar Novo Livro
                    2- Listar Livros Registrados
                    3- Listar Auteores Registrados
                    4- Buscar Livros por autor
                    0- Sair
                    """;
            System.out.println(menu);
            opcao = leitor.nextInt();

            switch (opcao){
                case 1:
                    cadastrarLivro();
                    break;
                case 2:
                    listarLivros();
                    break;
                case 3:
                    listarAutores();
                    break;
                case 4:
                    buscarLivrosPorAutor();
                    break;
                case 0:
                    System.out.println("Sessão encerrada");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }


    private void cadastrarLivro() {
        ApiResponse dados = getDadosDoLivro();

        List<DadosLivros> livros = dados.results();

        if (livros.isEmpty()) {
            System.out.println("Nenhum livro encontrado.");
            return;
        }

        while (!livros.isEmpty()) {
            System.out.println("Escolha um livro para salvar ou uma das opções:");
            System.out.println("1 - Salvar todos");
            System.out.println("0 - Cancelar");
            System.out.println("Livros encontrados:");
            for (int i = 0; i < livros.size(); i++) {
                var livro = livros.get(i);
                System.out.printf("%d - %s - %s - Autor(es): %s\n",
                        i + 2, livro.titulo(), livro.idiomas(), livro.autores());
            }


            int opcao = leitor.nextInt();

            if (opcao == 1) {
                livros.forEach(dadosLivro -> {
                    salvarLivro(dadosLivro);
                });
                break;
            } else if (opcao == 0) {
                System.out.println("Operação cancelada.");
                break;
            } else if (opcao > 0 && opcao <= livros.size()) {
                var livroSelecionado = livros.get(opcao - 1);
                salvarLivro(livroSelecionado);
                livros.remove(opcao - 1);
            } else {
                System.out.println("Opção inválida, tente novamente.");
            }
        }
    }



    private void salvarLivro(DadosLivros livro) {
        livroService.salvarLivro(livro);
    }

    private void salvarLivros(List<DadosLivros> livros) {
        for (var dados : livros) {
            livroService.salvarLivro(dados);
        }
        System.out.println("Todos os livros foram salvos com sucesso!");
    }


    private ApiResponse getDadosDoLivro() {
        System.out.println("Digite o título do livro para busca");
        var titulo = leitor.next();
        var json = consumoApi.obterDados("https://gutendex.com/books/?search=" + titulo);
        System.out.println(json);
        ApiResponse response = converter.obterDados(json, ApiResponse.class);
        System.out.println(response);
        return response;
    }
    private void buscarLivrosPorAutor() {
        System.out.println("Por qual autor deseja buscar livros?");
        String autor = leitor.next();
        try {
            livroService.findByAutor(autor);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private void listarAutores() {

        livroService.findAllAutor();
    }

    private void listarLivros() {
        livroService.findAllLivros();
    }

}
