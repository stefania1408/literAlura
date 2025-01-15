package com.aluracursos.literAlura.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.aluracursos.literAlura.dto.BookDto;
import jakarta.persistence.*;


@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;
    @Column(unique = true)
    private String title;

    @ManyToOne
    private Author author;
    private String language;
    @JsonAlias({"download_count"})
    private Integer downloads;



    public Book(){}
    public Book(BookDto bookDto){
        this.title=bookDto.title();
        this.author=new Author(bookDto.authorList().get(0).name(),
                bookDto.authorList().get(0).birthYear(),
                bookDto.authorList().get(0).deathYear());
        this.language=bookDto.languajes().get(0);
        this.downloads=bookDto.downloads();
    }
    public Book(String title,Author authors,  String languages, Integer downloads) {
        this.author = authors;
        this.title = title;
        this.language = languages;
        this.downloads = downloads;
    }

    public Integer getDownloads() {
        return downloads;
    }

    public void setDownloads(Integer downloads) {
        this.downloads = downloads;
    }



    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return  "---- Book ----\n"+
                "title:"+title + '\n' +
                "author:"+author.getName()+'\n'+
                "languages:"+language +'\n'+
                "downloads:"+downloads +'\n';

    }
}
