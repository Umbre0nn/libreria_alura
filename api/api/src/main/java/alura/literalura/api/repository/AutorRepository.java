package alura.literalura.api.repository;

import alura.literalura.api.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    List<Autor> findByFechaNacimientoBeforeAndFechaMuerteAfter(int fechaNacimiento, int fechaMuerte);
}
