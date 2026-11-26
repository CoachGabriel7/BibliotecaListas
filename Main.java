package biblioteca;

import biblioteca.controlador.ControladorBiblioteca;
import biblioteca.vista.VistaConsola;

public class Main {
    public static void main(String[] args) {
        new ControladorBiblioteca(new VistaConsola()).iniciar();
    }
}
