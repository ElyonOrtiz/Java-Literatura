# Literatura API - Sistema de Gerenciamento de Livros

Este projeto é uma aplicação Java desenvolvida com Spring Boot, responsável pelo gerenciamento de livros e autores. Ele permite cadastrar novos livros, listar os livros e autores registrados, e buscar livros por autor a partir de uma API externa.
## Funcionalidades

- Cadastrar Novo Livro: Permite pesquisar livros por título e cadastrar novos livros no sistema.
- Listar Livros Registrados: Exibe todos os livros que foram cadastrados no sistema.
- Listar Autores Registrados: Exibe todos os autores registrados no sistema.
- Buscar Livros por Autor: Permite buscar livros de um autor específico a partir do banco de dados.

## Como Rodar o Projeto
### Pré-requisitos

- **Java 21** (ou versão superior)
- **Maven** 
- **Spring Boot**
- 
### Passos para execução
1. Clone o repositório:
```xml
 git clone https://github.com/ElyonOrtiz/Change-conversor-de-moedas.git
```
2. Navegue até o diretório do projeto:
```xml
 cd Change-conversor-de-moedas.git
```
3. Compile o projeto com Maven:
```xml
    mvn clean install
```
4. Execute a aplicação:
```xml
    mvn spring-boot:run
```
5. Acesse o terminal onde o programa está sendo executado e interaja com o menu conforme desejado.
## Estrutura do Código

- **Pacote `com.alura.demo.principal`**: Contém a classe principal do sistema (`Principal`), que gerencia a interface de usuário e interage com os serviços para cadastrar, listar e buscar livros e autores.
- **Pacote `com.alura.demo.service`**: Contém as classes responsáveis por consumir a API externa e converter os dados recebidos em objetos que serão manipulados no sistema.
- **Pacote `com.alura.demo.repository`**: Contém os repositórios que interagem com o banco de dados para salvar e consultar livros e autores.
- **Pacote `com.alura.demo.Model`**: Contém as classes de modelo que representam os dados de livros e autores.

## Como Funciona

1. **Cadastro de Livros:**
    - O usuário pode buscar livros pela API externa do [Gutendex](https://gutendex.com), fornecendo um título de livro.
    - Após a busca, o sistema apresenta uma lista de livros encontrados e permite que o usuário escolha quais livros deseja salvar.

2. **Listagem de Livros e Autores:**
    - O sistema permite visualizar todos os livros e autores registrados no banco de dados local.

3. **Busca de Livros por Autor:**
    - O usuário pode buscar todos os livros de um autor específico registrado no banco de dados.

## Exemplo de Uso

Ao executar a aplicação, um menu será exibido no terminal com as seguintes opções:


```xml
1- Cadastrar Novo Livro 
2- Listar Livros Registrados 
3- Listar Autores Registrados 
4- Buscar Livros por autor 0- Sair
```

### Cadastro de Livro

- O usuário insere o título do livro, e a aplicação consulta a API do Gutendex.
- A lista de livros é exibida, e o usuário pode escolher salvar um livro ou todos os livros encontrados.

### Buscar Livros por Autor

- O usuário pode buscar livros de um autor específico digitando o nome do autor.

## Dependências

- **Spring Boot**: Framework usado para o desenvolvimento da aplicação.
- **JPA / Hibernate**: Para gerenciamento do banco de dados.
- **Consumo API**: Utiliza a API pública do [Gutendex](https://gutendex.com) para buscar informações de livros.
- **Scanner**: Para interação com o usuário via terminal.

## Contribuindo

Sinta-se à vontade para contribuir com melhorias ou correções para este projeto. Basta fazer um fork, realizar suas alterações e enviar um pull request.

## Licença

Este projeto está licenciado sob a Licença MIT - veja o arquivo [LICENSE](LICENSE) para mais detalhes.
