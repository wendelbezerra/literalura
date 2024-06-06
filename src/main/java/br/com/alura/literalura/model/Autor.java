package br.com.alura.literalura.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nome;
    private Integer dataNascimento;
    private Integer dataFalecimento;

    @OneToMany(mappedBy = "autorLivro")
    private List<Livro> livros = new ArrayList<>();

    public Autor(){}
    public Autor(DadosAutor inserirAutor) {
        this.nome = inserirAutor.nome();
        this.dataNascimento = inserirAutor.dataNascimento();
        this.dataFalecimento = inserirAutor.dataFalecimento();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Integer dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Integer getDataFalecimento() {
        return dataFalecimento;
    }

    public void setDataFalecimento(Integer dataFalecimento) {
        this.dataFalecimento = dataFalecimento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "\n********************* Autor *********************" +
                "\nNome do autor: " + nome +
                ", \nNascimento: " + dataNascimento +
                ", \nFalecimento: " + dataFalecimento +
                "\n************************************************\n";
    }
}
