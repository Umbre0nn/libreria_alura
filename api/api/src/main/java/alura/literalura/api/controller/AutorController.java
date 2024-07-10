package alura.literalura.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import alura.literalura.api.model.Autor;
import alura.literalura.api.service.AutorService;

import java.util.List;

@RestController
@RequestMapping("/autores")
public class AutorController {

    @Autowired
    private AutorService autorService;

    @GetMapping
    public List<Autor> obtenerTodosLosAutores() {
        return autorService.obtenerTodosLosAutores();
    }

    @GetMapping("/vivos/{anio}")
    public List<Autor> obtenerAutoresVivosEnAnio(@PathVariable int anio) {
        return autorService.obtenerAutoresVivosEnAnio(anio);
    }
}
