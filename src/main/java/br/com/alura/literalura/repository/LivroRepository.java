package br.com.alura.literalura.repository;

import br.com.alura.literalura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    Optional<Livro> findByIdiomaContainingIgnoreCase(String idiomaLivro);

    @Query("SELECT DISTINCT l.idioma FROM Livro l ORDER BY l.idioma")
    List<String> todosidiomas();
}
