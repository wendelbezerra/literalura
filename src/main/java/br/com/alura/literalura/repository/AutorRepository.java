package br.com.alura.literalura.repository;

import br.com.alura.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    //select * from autores WHERE autores.data_falecimento >= 1800 AND autores.data_nascimento <= 1800
//    @Query(value = "select * from autores WHERE autores.data_falecimento >= 1800 AND autores.data_nascimento <= 1800", nativeQuery = true)
    @Query("select a from Autor a WHERE a.dataFalecimento >= :ano AND a.dataNascimento <= :ano")
    Optional<Autor> findByAno(int ano);
}
