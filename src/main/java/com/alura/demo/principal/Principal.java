package com.alura.demo.principal;

import com.alura.demo.Model.ApiResponse;
import com.alura.demo.Model.DadosLivros;
import com.alura.demo.Model.Livro;
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
    private LivroService livroService = new LivroService();
    private LivrosRepository livrosRepository;

    public Principal(LivrosRepository livrosRepository){
        this.livrosRepository = livrosRepository;
    }
    public void exibeMenu(){
        var opcao = -1;
        while (opcao != 0){
            var menu = """
                    1- Cadastrar Novo Livro
                    0- Sair
                    """;
            System.out.println(menu);
            opcao = leitor.nextInt();

            switch (opcao){
                case 1:
                    cadastrarLivro();
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

        var opcaoSalvarTodos = livros.size() + 1;
        var opcaoCancelar = opcaoSalvarTodos + 1;

        while (!livros.isEmpty()) {
            System.out.println("Livros encontrados:");
            for (int i = 0; i < livros.size(); i++) {
                var livro = livros.get(i);
                System.out.printf("%d - %s - %s - Autor(es): %s\n", i + 1, livro.titulo(), livro.idiomas().toString(), livro.autores().toString());
            }

            System.out.printf("%d - Salvar todos\n", opcaoSalvarTodos);
            System.out.printf("%d - Cancelar\n", opcaoCancelar);

            System.out.println("Escolha um livro para salvar ou uma das opções acima:");
            int opcao = leitor.nextInt();

            if (opcao == opcaoSalvarTodos) {
//                salvarLivros(livros);
                break;
            } else if (opcao == opcaoCancelar) {
                System.out.println("Operação cancelada.");
                break;
            } else if (opcao > 0 && opcao <= livros.size()) {
                var livroSelecionado = livros.get(opcao - 1);
                Livro livro = new Livro(livroSelecionado);
                System.out.println();

                livrosRepository.save(livro);
                livros.remove(opcao - 1);
            } else {
                System.out.println("Opção inválida, tente novamente.");
            }
        }
    }


    private void salvarLivro(Livro livro) {
        livroService.salvarLivro(livro);
        System.out.printf( livro.getTitulo());
    }

    private void salvarLivros(List<DadosLivros> livros) {
        for (var dados : livros) {
            Livro livro = new Livro(dados);
            livroService.salvarLivro(livro);
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
}
