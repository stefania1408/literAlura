package com.aluracursos.literAlura.console;
import com.aluracursos.literAlura.dto.ApiResponse;
import com.aluracursos.literAlura.dto.BookDto;
import com.aluracursos.literAlura.models.Author;
import com.aluracursos.literAlura.models.Book;
import com.aluracursos.literAlura.service.Adapter;
import com.aluracursos.literAlura.service.AuthorSerivece;
import com.aluracursos.literAlura.service.BookService;
import com.aluracursos.literAlura.service.ConsumerApi;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.SQLOutput;
import java.util.*;
import java.util.stream.Collectors;
public class DriverLiterAlura {
    private final String BASE_URL="https://gutendex.com/books/";
    private Adapter my_adapter= new Adapter();
    private ConsumerApi consumerApi= new ConsumerApi();
    private Scanner reader = new Scanner(System.in);
    private AuthorSerivece  authorService;
    private BookService bookService;

    public DriverLiterAlura(BookService bookService,AuthorSerivece authorSerivece){
        this.bookService=bookService;
        this.authorService=authorSerivece;
    }

    public  void test(){
        int option=0;
        String languages;
        while(true){
            while(true){
                try{
                    System.out.println("\t>>> Book Api <<<\n");
                    var menu="""
                    1 .- Buscar un nuevo Libro
                    2.-  Mostrar los libros buscados
                    3.-  Buscar en idiomas
                    4.-  Buscar autores vivos en el año
                    5.-  Mostrar todos los autores
                    6.-  Mostrar top 5 libros mas descargados
                    7.-  Salir
                    """;
                    System.out.println(menu);
                    System.out.print("Ingrese su elecion:");

                    option= reader.nextInt();
                    reader.nextLine();
                    if(option >0 && option <8){
                        break;
                    }else{
                        System.out.println("ingrese un numero del 1 al 7");
                    }
                }catch (InputMismatchException e){
                    System.out.println("Por favor ingrese solo numeros");
                    reader.nextLine();
                }

            }



            switch (option){
                case 1:
                    searchBook();

                    break;
                case 2:
                    showAllBooks();
                    break;
                case 3:
                    while (true) {
                        try {
                            System.out.println("""
                                en -> English
                                fr -> French
                                es -> Español
                                """);
                            System.out.println("Ingrese idioma:");
                            languages = reader.nextLine();
                            if (languages.matches("[a-zA-Z]{2}")) { // Validar que sean 2 letras
                                searchByLanguages(languages);
                                break;
                            } else {
                                throw new IllegalArgumentException("Debe ingresar un código de idioma válido (ej: 'en', 'es').");
                            }
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    break;

                case 4:
                    searchAuthorInYear();
                    break;
                case 5:
                    showAllAuthors();
                    break;
                case 6:
                    showTop5Books();
                    break;
                case 7:
                    System.out.println("saliendo...");
                    return;
                default:
                    System.out.println("Opcion no valida");
            }


        }
    }

    public void searchBook(){
        System.out.println("Ingrese titulo:");
        var title= reader.nextLine();
        if(!bookService.isExist(title)){
            String url=BASE_URL +"?search="+URLEncoder.encode(title,StandardCharsets.UTF_8);
            var json= consumerApi.getDataApi(url);

            ApiResponse response=my_adapter.getDataFromJson(json,ApiResponse.class);
            List<BookDto> bookDto = response.result();
            Optional<Book> book = Optional.ofNullable(bookDto)
                    .flatMap(list -> list.stream().findFirst())
                    .map(Book::new);
            if(book.isPresent()){
                bookService.saveBook(book.get());
                System.out.println("libro guardado.");
            }else{
                System.out.println("El libro no existe");
            }

        }else{
            System.out.println("El libro ya existe");
        }



    }
    public  void  showTop5Books(){
        List<Book> books= bookService.topFiveBooks();
        System.out.println("Los 5 de libros mas descargados son:");
        books.forEach(System.out::println);
    }
    void showAllBooks(){
        List<Book> books=bookService.getAll();
        books.forEach(System.out::println);
    }

    void searchByLanguages(String languages){
        Optional<List<Book>> booksOpt=bookService.getBooksInLanguage(languages);


        if(booksOpt.isPresent()){
            DoubleSummaryStatistics bookStatistic=booksOpt.get().stream()
                    .collect(Collectors.summarizingDouble(Book::getDownloads));
            System.out.println("__________________________________________________________________");
            System.out.println("Total de libros en el idioma \'"+languages +"\' es: " + bookStatistic.getCount());
            System.out.println("El libro con  menos descargas tiene: " + bookStatistic.getMin());
            System.out.println("El libro con mas descargas tiene: " + bookStatistic.getMax());
            System.out.println("El total de descargas en este idioma es: " + bookStatistic.getSum());
            System.out.println("El promedio de descargas es: " + bookStatistic.getAverage());
            System.out.println("__________________________________________________________________");
        }else{
            System.out.println("No se encontraron libros con ese idioma");
        }

    }
    void searchAuthorInYear(){
        int year;
        while (true) {
            try {
                System.out.println("Ingrese año:");
                year = Integer.parseInt(reader.nextLine());

                break;
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un año válido.");
            }
        }

        List<Author> authors= authorService.getAuthorsLiveIn(year);
        if(!authors.isEmpty()){
            authors.forEach(System.out::println);
        }else {
            System.out.println("No hay autores vivos en ese año.");
        }
    }
    void showAllAuthors(){
        List<Author> authors=authorService.getAllAuthors();
        authors.forEach(System.out::println);

    }

}
