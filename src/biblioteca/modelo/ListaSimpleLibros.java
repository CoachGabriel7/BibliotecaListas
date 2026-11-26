package biblioteca.modelo;

public class ListaSimpleLibros {
    private static final int CAPACIDAD_MAXIMA = 20;

    private static class Nodo {
        private final Libro libro;
        private Nodo siguiente;

        private Nodo(Libro libro) {
            this.libro = libro;
        }
    }

    private Nodo primero;
    private Nodo ultimo;
    private int cantidad;

    public boolean agregar(Libro libro) {
        if (cantidad == CAPACIDAD_MAXIMA) {
            return false;
        }

        Nodo nuevo = new Nodo(libro);
        if (primero == null) {
            primero = nuevo;
        } else {
            ultimo.siguiente = nuevo;
        }
        ultimo = nuevo;
        cantidad++;
        return true;
    }

    public Libro buscarPorCodigo(String codigo) {
        Nodo actual = primero;
        while (actual != null) {
            if (actual.libro.getCodigo().equalsIgnoreCase(codigo)) {
                return actual.libro;
            }
            actual = actual.siguiente;
        }
        return null;
    }

    public Libro retirarUltimo() {
        if (primero == null) {
            return null;
        }

        Libro libro = ultimo.libro;
        if (primero == ultimo) {
            primero = null;
            ultimo = null;
        } else {
            Nodo actual = primero;
            while (actual.siguiente != ultimo) {
                actual = actual.siguiente;
            }
            actual.siguiente = null;
            ultimo = actual;
        }
        cantidad--;
        return libro;
    }

    public String listar() {
        if (primero == null) {
            return "No hay libros en esta sección.";
        }

        StringBuilder resultado = new StringBuilder();
        Nodo actual = primero;
        while (actual != null) {
            resultado.append(actual.libro).append(System.lineSeparator());
            actual = actual.siguiente;
        }
        return resultado.toString();
    }
}
