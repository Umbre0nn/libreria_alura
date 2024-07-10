package alura.literalura.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import alura.literalura.api.model.Libro;
import alura.literalura.api.model.Autor;
import alura.literalura.api.service.LibroService;
import alura.literalura.api.service.GutendexService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/libros")
public class LibroController {

    @Autowired
    private LibroService libroService;

    @Autowired
    private GutendexService gutendexService;

    @GetMapping
    public List<Libro> obtenerTodosLosLibros() {
        return libroService.obtenerTodosLosLibros();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Libro> obtenerLibroPorId(@PathVariable Long id) {
        Optional<Libro> libro = libroService.obtenerLibroPorId(id);
        return libro.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Libro guardarLibro(@RequestBody Libro libro) {
        return libroService.guardarLibro(libro);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Libro> actualizarLibro(@PathVariable Long id, @RequestBody Libro libroDetalles) {
        Optional<Libro> libro = libroService.obtenerLibroPorId(id);
        if (libro.isPresent()) {
            Libro libroActualizado = libroService.actualizarLibro(id, libroDetalles);
            return ResponseEntity.ok(libroActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarLibro(@PathVariable Long id) {
        Optional<Libro> libro = libroService.obtenerLibroPorId(id);
        if (libro.isPresent()) {
            libroService.eliminarLibro(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Libro>> buscarLibros(@RequestParam String titulo) {
        List<Libro> resultados = gutendexService.buscarLibros(titulo);
        return ResponseEntity.ok(resultados);
    }

    @GetMapping("/buscarPorAutor")
    public ResponseEntity<List<Libro>> buscarLibrosPorAutor(@RequestParam String autor) {
        List<Libro> resultados = gutendexService.buscarLibrosPorAutor(autor);
        return ResponseEntity.ok(resultados);
    }

    @GetMapping("/autoresVivos")
    public ResponseEntity<List<Autor>> buscarAutoresVivos(@RequestParam int anio) {
        List<Autor> resultados = gutendexService.obtenerAutoresVivosEnAnio(anio);
        return ResponseEntity.ok(resultados);
    }

    @GetMapping("/autoresFallecidos")
    public ResponseEntity<List<Autor>> buscarAutoresFallecidos(@RequestParam int anio) {
        List<Autor> resultados = gutendexService.obtenerAutoresFallecidosEnAnio(anio);
        return ResponseEntity.ok(resultados);
    }
}
