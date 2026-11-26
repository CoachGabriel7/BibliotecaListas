package biblioteca.modelo;

public class Prestamo {
    private final Libro libro;
    private final String usuario;

    public Prestamo(Libro libro, String usuario) {
        this.libro = libro;
        this.usuario = usuario;
    }

    public Libro getLibro() {
        return libro;
    }

    public String getUsuario() {
        return usuario;
    }

    @Override
    public String toString() {
        return libro + " | Usuario: " + usuario;
    }
}
