package biblioteca.modelo;

public class ListaCircularLibros {
    private static class Nodo {
        private final Libro libro;
        private Nodo siguiente;

        private Nodo(Libro libro) {
            this.libro = libro;
        }
    }

    private Nodo ultimo;
    private Nodo actual;

    public void agregar(Libro libro) {
        Nodo nuevo = new Nodo(libro);
        if (ultimo == null) {
            nuevo.siguiente = nuevo;
            ultimo = nuevo;
            actual = nuevo;
        } else {
            nuevo.siguiente = ultimo.siguiente;
            ultimo.siguiente = nuevo;
            ultimo = nuevo;
        }
    }

    public Libro avanzar() {
        if (actual == null) {
            return null;
        }
        Libro libro = actual.libro;
        actual = actual.siguiente;
        return libro;
    }

    public Libro buscarPorAutorRecursivo(String autor) {
        if (ultimo == null) {
            return null;
        }
        return buscarDesde(ultimo.siguiente, ultimo.siguiente, autor);
    }

    private Libro buscarDesde(Nodo nodo, Nodo inicio, String autor) {
        if (nodo.libro.getAutor().equalsIgnoreCase(autor)) {
            return nodo.libro;
        }
        if (nodo.siguiente == inicio) {
            return null;
        }
        return buscarDesde(nodo.siguiente, inicio, autor);
    }

    public boolean estaVacia() {
        return ultimo == null;
    }
}
