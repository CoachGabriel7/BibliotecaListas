package biblioteca.modelo;

public class ListaDoblePrestamos {
    private static class Nodo {
        private final Prestamo prestamo;
        private Nodo anterior;
        private Nodo siguiente;

        private Nodo(Prestamo prestamo) {
            this.prestamo = prestamo;
        }
    }

    private Nodo primero;

    public void agregarAlInicio(Prestamo prestamo) {
        Nodo nuevo = new Nodo(prestamo);
        nuevo.siguiente = primero;
        if (primero != null) {
            primero.anterior = nuevo;
        }
        primero = nuevo;
    }

    public Prestamo buscarPorTitulo(String titulo) {
        Nodo actual = primero;
        while (actual != null) {
            if (actual.prestamo.getLibro().getTitulo().equalsIgnoreCase(titulo)) {
                return actual.prestamo;
            }
            actual = actual.siguiente;
        }
        return null;
    }

    public Prestamo devolverPorTitulo(String titulo) {
        Nodo actual = primero;
        while (actual != null) {
            if (actual.prestamo.getLibro().getTitulo().equalsIgnoreCase(titulo)) {
                if (actual.anterior == null) {
                    primero = actual.siguiente;
                } else {
                    actual.anterior.siguiente = actual.siguiente;
                }
                if (actual.siguiente != null) {
                    actual.siguiente.anterior = actual.anterior;
                }
                return actual.prestamo;
            }
            actual = actual.siguiente;
        }
        return null;
    }

    public String listar() {
        if (primero == null) {
            return "No hay préstamos activos.";
        }

        StringBuilder resultado = new StringBuilder();
        Nodo actual = primero;
        while (actual != null) {
            resultado.append(actual.prestamo).append(System.lineSeparator());
            actual = actual.siguiente;
        }
        return resultado.toString();
    }
}
