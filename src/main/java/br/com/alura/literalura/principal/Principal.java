package br.com.alura.literalura.principal;

import br.com.alura.literalura.model.Autor;
import br.com.alura.literalura.model.DadosLivro;
import br.com.alura.literalura.model.DadosAutor;
import br.com.alura.literalura.model.Livro;
import br.com.alura.literalura.repository.AutorRepository;
import br.com.alura.literalura.repository.LivroRepository;
import br.com.alura.literalura.service.ConverteDados;
import com.alura.scrennmatch.service.ConsumoApi;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {

    final private String ENDERECO = "https://gutendex.com/books/?search=";
    private ConsumoApi consumeApi = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private Scanner leitura = new Scanner(System.in);

    private LivroRepository livroRepository;
    private AutorRepository autorRepository;

    public Principal(LivroRepository livroRepository, AutorRepository autorRepository) {
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
    }

    public void menuPrincipal() {
        var numeroOpcao = -1;
        while (numeroOpcao != 0) {
            abreMenu();
            numeroOpcao = leitura.nextInt();
            leitura.nextLine();
            switch (numeroOpcao){
                case 0: {
                    System.out.println("Saindo...");
                    break;
                }
                case 1: {
                    buscaLivro();
                    break;
                }
                case 2: {
                    listaLivros();
                    break;
                }
                case 3: {
                    listaAutores();
                    break;
                }
                case 4: {
                    listaAutoresVivos();
                    break;
                }
                case 5: {
                    listaLivroIdioma();
                    break;
                }
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    public void abreMenu(){
        System.out.println("Escolha uma opção:");
        System.out.println("""
                    1 - Buscar e resgistrar livro pelo título.
                    2 - Listar livros resgistrados.
                    3 - Listar autores registrados.
                    4 - Listar autores vivos em um determinado período.
                    5 - Listar livros em um determinado idioma.
                    0 - Sair.
                    """);
    }

    public void buscaLivro(){
        System.out.println("Digite o nome do livro:");
        var buscarLivro = leitura.nextLine();
        var dados = consumeApi.obterDados(ENDERECO+buscarLivro.replace(" ", "+"));
        converteDados(dados);
    }

    public void listaLivros(){
        List<Livro> livros = livroRepository.findAll();
        livros.stream()
                .sorted(Comparator.comparing(Livro::getTituloLivro))
                .forEach(System.out::println);
    }

    public void listaAutores(){
        List<Autor> autores = autorRepository.findAll();
        autores.stream()
                .sorted(Comparator.comparing(Autor::getNome))
                .forEach(System.out::println);
    }

    public void listaAutoresVivos(){
        System.out.println("Escolha um ano:");
        Integer ano = leitura.nextInt();
        leitura.nextLine();
        Optional<Autor> autor = autorRepository.findByAno(ano);
        System.out.println(autor);
    }

    public void listaLivroIdioma(){
        System.out.println("Escolha um dos idiomas registrados:");
        var idiomasRegistrados = livroRepository.todosidiomas();
        idiomasRegistrados.forEach(System.out::println);
        String idioma = leitura.nextLine();
        Optional<Livro> livros = livroRepository.findByIdiomaContainingIgnoreCase(idioma);
        if(livros.isPresent()) {
            System.out.println("Idioma buscado: "+livros.get());
        } else {
            System.out.println("Idioma não encontrado.");
        }
    }

    public void converteDados(String dadosRecebido){
        try {
            DadosLivro novoLivro = conversor.obterDados(dadosRecebido, DadosLivro.class);
            DadosAutor inserirAutor = conversor.obterDados(dadosRecebido, DadosAutor.class);
            Autor autor = new Autor(inserirAutor);
            Livro livro = new Livro(novoLivro, autor);
            try {
                livroRepository.save(livro);
                autorRepository.save(autor);
                System.out.println("\nLivro salvo com sucesso");
                System.out.println(livro);
            } catch (DataIntegrityViolationException e) {
                System.out.println("\nEste livro já consta no banco de dados");
                System.out.println(livro);
            }
        } catch (NullPointerException e){
            System.out.println("Não foi possível encontrar este livro!");
        }
    }
}
