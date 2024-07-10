package alura.literalura.api.service;

import alura.literalura.api.model.Autor;
import alura.literalura.api.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    public List<Autor> obtenerTodosLosAutores() {
        return autorRepository.findAll();
    }

    public List<Autor> obtenerAutoresVivosEnAnio(int anio) {
        return autorRepository.findByFechaNacimientoBeforeAndFechaMuerteAfter(anio, anio);
    }
}
