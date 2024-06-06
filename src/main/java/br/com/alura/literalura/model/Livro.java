package br.com.alura.literalura.model;

import jakarta.persistence.*;

@Entity
@Table(name = "livros")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String tituloLivro;
    private String idioma;
    private Integer totalDownload;

//    @Transient
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Autor autorLivro;

    public Livro(){}
    public Livro(DadosLivro novoLivro, Autor autor) {
        this.tituloLivro = novoLivro.tituloLivro();
        this.idioma = novoLivro.idioma().toString();
        this.totalDownload = novoLivro.totalDownloads();
        this.autorLivro = autor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTituloLivro() {
        return tituloLivro;
    }

    public void setTituloLivro(String tituloLivro) {
        this.tituloLivro = tituloLivro;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Integer getTotalDownload() {
        return totalDownload;
    }

    public void setTotalDownload(Integer totalDownload) {
        this.totalDownload = totalDownload;
    }

    public Autor getAutorLivro() {
        return autorLivro;
    }

    public void setAutorLivro(Autor autorLivro) {
        this.autorLivro = autorLivro;
    }

    @Override
    public String toString() {
        return "\n*********************** Livro ***********************" +
                "\nNome do livro: " + tituloLivro +
                ", \nIdioma: " + idioma.toString() +
                ", \nAutor: " + autorLivro.getNome() +
                ", \nQuantidade de downloads: " + totalDownload +
                "\n**********************************************\n";
    }
}
