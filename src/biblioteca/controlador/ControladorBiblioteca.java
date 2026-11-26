package biblioteca.controlador;

import biblioteca.modelo.Libro;
import biblioteca.modelo.ListaCircularLibros;
import biblioteca.modelo.ListaDoblePrestamos;
import biblioteca.modelo.ListaSimpleLibros;
import biblioteca.modelo.Prestamo;
import biblioteca.vista.VistaConsola;

public class ControladorBiblioteca {
    private final VistaConsola vista;
    private final ListaSimpleLibros librosNuevos = new ListaSimpleLibros();
    private final ListaDoblePrestamos prestamos = new ListaDoblePrestamos();
    private final ListaCircularLibros referencias = new ListaCircularLibros();

    public ControladorBiblioteca(VistaConsola vista) {
        this.vista = vista;
    }

    public void iniciar() {
        int opcion;
        do {
            vista.mostrarMenuPrincipal();
            opcion = vista.leerOpcion("Elige una opción: ");
            switch (opcion) {
                case 1:
                    menuLibrosNuevos();
                    break;
                case 2:
                    menuPrestamos();
                    break;
                case 3:
                    menuReferencias();
                    break;
                case 0:
                    vista.mostrar("Hasta luego.");
                    break;
                default:
                    vista.mostrar("Opción no válida.");
            }
        } while (opcion != 0);
    }

    private void menuLibrosNuevos() {
        int opcion;
        do {
            vista.mostrarMenuNuevos();
            opcion = vista.leerOpcion("Elige una opción: ");
            switch (opcion) {
                case 1:
                    agregarLibroNuevo();
                    break;
                case 2:
                    vista.mostrar(librosNuevos.listar());
                    break;
                case 3:
                    String codigo = vista.leerTexto("Código del libro: ");
                    mostrarResultado(librosNuevos.buscarPorCodigo(codigo), "No se encontró el libro.");
                    break;
                case 4:
                    mostrarResultado(librosNuevos.retirarUltimo(), "No hay libros para retirar.");
                    break;
                case 0:
                    break;
                default:
                    vista.mostrar("Opción no válida.");
            }
        } while (opcion != 0);
    }

    private void agregarLibroNuevo() {
        Libro libro = leerLibro();
        if (librosNuevos.agregar(libro)) {
            vista.mostrar("Libro agregado.");
        } else {
            vista.mostrar("La sección ya llegó al límite de 20 libros.");
        }
    }

    private void menuPrestamos() {
        int opcion;
        do {
            vista.mostrarMenuPrestamos();
            opcion = vista.leerOpcion("Elige una opción: ");
            switch (opcion) {
                case 1:
                    registrarPrestamo();
                    break;
                case 2:
                    vista.mostrar(prestamos.listar());
                    break;
                case 3:
                    buscarPrestamo();
                    break;
                case 4:
                    devolverLibro();
                    break;
                case 0:
                    break;
                default:
                    vista.mostrar("Opción no válida.");
            }
        } while (opcion != 0);
    }

    private void registrarPrestamo() {
        Libro libro = leerLibro();
        String usuario = vista.leerTexto("Nombre del usuario: ");
        prestamos.agregarAlInicio(new Prestamo(libro, usuario));
        vista.mostrar("Préstamo registrado.");
    }

    private void buscarPrestamo() {
        String titulo = vista.leerTexto("Título del libro: ");
        Prestamo prestamo = prestamos.buscarPorTitulo(titulo);
        if (prestamo == null) {
            vista.mostrar("No se encontró un préstamo con ese título.");
        } else {
            vista.mostrar(prestamo.toString());
        }
    }

    private void devolverLibro() {
        String titulo = vista.leerTexto("Título del libro que se devuelve: ");
        Prestamo prestamo = prestamos.devolverPorTitulo(titulo);
        if (prestamo == null) {
            vista.mostrar("No se encontró un préstamo con ese título.");
        } else {
            vista.mostrar("Devolución registrada: " + prestamo);
        }
    }

    private void menuReferencias() {
        int opcion;
        do {
            vista.mostrarMenuReferencia();
            opcion = vista.leerOpcion("Elige una opción: ");
            switch (opcion) {
                case 1:
                    referencias.agregar(leerLibro());
                    vista.mostrar("Libro agregado.");
                    break;
                case 2:
                    mostrarResultado(referencias.avanzar(), "No hay libros de referencia.");
                    break;
                case 3:
                    String autor = vista.leerTexto("Nombre del autor: ");
                    mostrarResultado(referencias.buscarPorAutorRecursivo(autor), "No se encontró un libro de ese autor.");
                    break;
                case 4:
                    vista.mostrar(referencias.estaVacia()
                            ? "La sección está vacía."
                            : "La sección tiene libros.");
                    break;
                case 0:
                    break;
                default:
                    vista.mostrar("Opción no válida.");
            }
        } while (opcion != 0);
    }

    private Libro leerLibro() {
        String codigo = vista.leerTexto("Código: ");
        String titulo = vista.leerTexto("Título: ");
        String autor = vista.leerTexto("Autor: ");
        return new Libro(codigo, titulo, autor);
    }

    private void mostrarResultado(Libro libro, String mensajeSiNoExiste) {
        vista.mostrar(libro == null ? mensajeSiNoExiste : libro.toString());
    }
}
