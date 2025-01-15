package com.aluracursos.literAlura.respository;

import com.aluracursos.literAlura.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {

   // @Query("SELECT b FROM Book b WHERE  b.language =:lan")
    Optional<List<Book>> findByLanguage(String lan);
}
