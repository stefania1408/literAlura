package com.aluracursos.literAlura.respository;

import com.aluracursos.literAlura.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository <Author,Long> {

    Optional<Author> findAuthorByName(String name);

    @Query("SELECT a FROM Author a WHERE a.birthYear <= :year AND a.deathYear > :year")
    List<Author> findAuthorsAliveInYear(@Param("year") int year);


    List<Author> findByBirthYearLessThanEqualAndDeathYearGreaterThan(int yearBirth, int yearDeath);



}
