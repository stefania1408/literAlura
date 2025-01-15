Esta es una aplicación que brinda un catalogo de libros, permite la interacción por consola con el usuario, proporciona los datos por medio de la la API [Gutendex](https://gutendex.com/) y permite almacenarlos en una base de datos 
Local

1. Buscar un nuevo Libro:
Busca los libros en la API de Gutendex. No permite agregar el mismo libro dos veces

2. Mostrar los libros buscados:
Muestra un listado con todos los libros que han sido buscados y almacenados en la base de datos.  

3. Buscar por idioma:
Muestra estadísticas de libros según el idioma (`en`, `es`, `fr`).  

4. Buscar autores vivos en el año
Muestra un listado con los autores vivos en un año determinado con base a los datos de los libros guardados.  

5. Mostrar todos los autores
 Muestra un listado con todos los autores de los libros almacenados.  

6. Mostrar top 5 libros más descargados
Muestra los cinco libros con más descargas dentro de la base de datos.  

7. Salir
 Cierra la aplicación.  


Tecnologías utilizadas

Java Spring Boot
Spring Data JPA
PostgreSQL
Jackson.  
Driver de PostgreSQL

Configuración  

1. Clona este repositorio 

2. Configura PostgreSQL:

      Crea una base de datos llamada libros.
      Configura las credenciales de acceso en el archivo application.properties del proyecto:
  
      ```bash
      spring.datasource.url=jdbc:postgresql://localhost:5432/<NOMBRE_DE_BASE DE DATOS>
      spring.datasource.username=<TU_USUARIO>
      spring.datasource.password=<TU_PASSWORD>

3. Ejecuta la aplicación



