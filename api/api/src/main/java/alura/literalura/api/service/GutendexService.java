package alura.literalura.api.service;

import alura.literalura.api.model.Autor;
import alura.literalura.api.model.GutendexResponse;
import alura.literalura.api.model.GutendexResponse.GutendexBook;
import alura.literalura.api.model.GutendexResponse.Person;
import alura.literalura.api.model.Libro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class GutendexService {

    @Autowired
    private RestTemplate restTemplate;

    private static final String GUTENDEX_API_URL = "https://gutendex.com/books";

    public List<Libro> buscarLibros(String titulo) {
        String url = GUTENDEX_API_URL + "?search=" + titulo;
        GutendexResponse response = restTemplate.getForObject(url, GutendexResponse.class);

        List<Libro> libros = new ArrayList<>();
        if (response != null && response.getResults() != null) {
            for (GutendexBook book : response.getResults()) {
                Libro libro = new Libro();
                libro.setTitulo(book.getTitle());
                libro.setAutor(book.getAuthors().stream().map(Person::getName).findFirst().orElse("Desconocido"));
                libro.setIsbn(String.valueOf(book.getId()));
                libros.add(libro);
            }
        }
        return libros;
    }

    public List<Libro> buscarLibrosPorAutor(String autor) {
        String url = GUTENDEX_API_URL + "?search=" + autor;
        GutendexResponse response = restTemplate.getForObject(url, GutendexResponse.class);

        List<Libro> libros = new ArrayList<>();
        if (response != null && response.getResults() != null) {
            for (GutendexBook book : response.getResults()) {
                Libro libro = new Libro();
                libro.setTitulo(book.getTitle());
                libro.setAutor(book.getAuthors().stream().map(Person::getName).findFirst().orElse("Desconocido"));
                libro.setIsbn(String.valueOf(book.getId()));
                libros.add(libro);
            }
        }
        return libros;
    }

    public List<Autor> obtenerAutoresVivosEnAnio(int anio) {
        String url = GUTENDEX_API_URL + "?author_year_start=" + anio;
        GutendexResponse response = restTemplate.getForObject(url, GutendexResponse.class);

        List<Autor> autoresVivos = new ArrayList<>();
        if (response != null && response.getResults() != null) {
            for (GutendexBook book : response.getResults()) {
                for (Person author : book.getAuthors()) {
                    if (author.getBirthYear() != null && author.getBirthYear() <= anio &&
                            (author.getDeathYear() == null || author.getDeathYear() > anio)) {
                        Autor autor = new Autor();
                        autor.setNombre(author.getName());
                        autor.setFechaNacimiento(Integer.parseInt(author.getBirthYear().toString()));
                        autor.setFechaMuerte(Integer.parseInt(author.getDeathYear() != null ? author.getDeathYear().toString() : "N/A"));
                        autoresVivos.add(autor);
                    }
                }
            }
        }
        return autoresVivos;
    }

    public List<Autor> obtenerAutoresFallecidosEnAnio(int anio) {
        String url = GUTENDEX_API_URL + "?author_year_end=" + anio;
        GutendexResponse response = restTemplate.getForObject(url, GutendexResponse.class);

        List<Autor> autoresFallecidos = new ArrayList<>();
        if (response != null && response.getResults() != null) {
            for (GutendexBook book : response.getResults()) {
                for (Person author : book.getAuthors()) {
                    if (author.getDeathYear() != null && author.getDeathYear() <= anio) {
                        Autor autor = new Autor();
                        autor.setNombre(author.getName());
                        autor.setFechaNacimiento(Integer.parseInt(author.getBirthYear() != null ? author.getBirthYear().toString() : "N/A"));
                        autor.setFechaMuerte(Integer.parseInt(author.getDeathYear().toString()));
                        autoresFallecidos.add(autor);
                    }
                }
            }
        }
        return autoresFallecidos;
    }

    public List<Autor> obtenerTodosLosAutores() {

        String url = GUTENDEX_API_URL;
        GutendexResponse response = restTemplate.getForObject(url, GutendexResponse.class);

        List<Autor> autores = new ArrayList<>();
        if (response != null && response.getResults() != null) {
            for (GutendexBook book : response.getResults()) {
                for (Person author : book.getAuthors()) {
                    Autor autor = new Autor();
                    autor.setNombre(author.getName());
                    autor.setFechaNacimiento(Integer.parseInt(author.getBirthYear() != null ? author.getBirthYear().toString() : "N/A"));
                    autor.setFechaMuerte(Integer.parseInt(author.getDeathYear() != null ? author.getDeathYear().toString() : "N/A"));
                    autores.add(autor);
                }
            }
        }
        return autores;
    }
}
