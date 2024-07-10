package alura.literalura.api;

import alura.literalura.api.model.Autor;
import alura.literalura.api.model.Libro;
import alura.literalura.api.service.GutendexService;
import alura.literalura.api.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class ApiApplication implements CommandLineRunner {

	@Autowired
	private GutendexService gutendexService;

	@Autowired
	private LibroService libroService;

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		int opcion;
		do {
			System.out.println("1. Buscar libro por título");
			System.out.println("2. Listar todos los libros");
			System.out.println("3. Listar todos los autores");
			System.out.println("4. Listar autores vivos en un año");
			System.out.println("5. Listar autores fallecidos en un año");
			System.out.println("6. Eliminar un libro");
			System.out.println("7. Salir");
			opcion = scanner.nextInt();
			scanner.nextLine();

			switch (opcion) {
				case 1:
					System.out.println("Ingrese el título del libro:");
					String titulo = scanner.nextLine();
					buscarLibroPorTitulo(titulo);
					break;
				case 2:
					listarTodosLosLibros();
					break;
				case 3:
					listarTodosLosAutores();
					break;
				case 4:
					System.out.println("Ingrese el año:");
					int anioVivos = scanner.nextInt();
					scanner.nextLine();
					listarAutoresVivosEnAnio(anioVivos);
					break;
				case 5:
					System.out.println("Ingrese el año:");
					int anioFallecidos = scanner.nextInt();
					scanner.nextLine();
					listarAutoresFallecidosEnAnio(anioFallecidos);
					break;
				case 6:
					System.out.println("Ingrese el ID del libro a eliminar:");
					Long idLibro = scanner.nextLong();
					scanner.nextLine();
					eliminarLibro(idLibro);
					break;
				case 7:
					System.out.println("Saliendo...");
					break;
				default:
					System.out.println("Opción no válida.");
					break;
			}
		} while (opcion != 7);
	}

	private void buscarLibroPorTitulo(String titulo) {
		List<Libro> libros = gutendexService.buscarLibros(titulo);
		if (libros.isEmpty()) {
			System.out.println("No se encontraron libros con el título " + titulo);
		} else {
			System.out.println("Libros encontrados:");
			for (Libro libro : libros) {
				System.out.println(libro);
			}
		}
	}

	private void listarTodosLosLibros() {
		List<Libro> libros = libroService.obtenerTodosLosLibros();
		if (libros.isEmpty()) {
			System.out.println("No hay libros disponibles.");
		} else {
			System.out.println("Listado de todos los libros:");
			for (Libro libro : libros) {
				System.out.println(libro);
			}
		}
	}

	private void listarTodosLosAutores() {

		List<Autor> autores = gutendexService.obtenerTodosLosAutores();
		if (autores.isEmpty()) {
			System.out.println("No hay autores disponibles.");
		} else {
			System.out.println("Listado de todos los autores:");
			for (Autor autor : autores) {
				System.out.println(autor);
			}
		}
	}

	private void listarAutoresVivosEnAnio(int anio) {
		List<Autor> autoresVivos = gutendexService.obtenerAutoresVivosEnAnio(anio);
		if (autoresVivos.isEmpty()) {
			System.out.println("No se encontraron autores vivos en el año " + anio);
		} else {
			System.out.println("Autores vivos en el año " + anio + ":");
			for (Autor autor : autoresVivos) {
				System.out.println(autor.getNombre() + " (Nacido: " + autor.getFechaNacimiento() + ", Fallecido: " + autor.getFechaMuerte() + ")");
			}
		}
	}

	private void listarAutoresFallecidosEnAnio(int anio) {
		List<Autor> autoresFallecidos = gutendexService.obtenerAutoresFallecidosEnAnio(anio);
		if (autoresFallecidos.isEmpty()) {
			System.out.println("No se encontraron autores fallecidos en el año " + anio);
		} else {
			System.out.println("Autores fallecidos en el año " + anio + ":");
			for (Autor autor : autoresFallecidos) {
				System.out.println(autor.getNombre() + " (Nacido: " + autor.getFechaNacimiento() + ", Fallecido: " + autor.getFechaMuerte() + ")");
			}
		}
	}

	private void eliminarLibro(Long id) {
		libroService.eliminarLibro(id);
		System.out.println("Libro con ID " + id + " eliminado.");
	}
}
