package biblioteca.vista;

import java.util.Scanner;

public class VistaConsola {
    private final Scanner scanner = new Scanner(System.in);

    public int leerOpcion(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String entrada = scanner.nextLine().trim();
            try {
                return Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Escribe un número válido.");
            }
        }
    }

    public String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine().trim();
    }

    public void mostrar(String texto) {
        System.out.println(texto);
    }

    public void mostrarMenuPrincipal() {
        System.out.println("\nBiblioteca El Saber");
        System.out.println("1. Libros nuevos");
        System.out.println("2. Libros prestados");
        System.out.println("3. Libros de referencia");
        System.out.println("0. Salir");
    }

    public void mostrarMenuNuevos() {
        System.out.println("\nSección A - Libros nuevos");
        System.out.println("1. Agregar libro");
        System.out.println("2. Mostrar libros");
        System.out.println("3. Buscar por código");
        System.out.println("4. Retirar último libro");
        System.out.println("0. Volver");
    }

    public void mostrarMenuPrestamos() {
        System.out.println("\nSección B - Libros prestados");
        System.out.println("1. Registrar préstamo");
        System.out.println("2. Mostrar préstamos activos");
        System.out.println("3. Buscar por título");
        System.out.println("4. Registrar devolución");
        System.out.println("0. Volver");
    }

    public void mostrarMenuReferencia() {
        System.out.println("\nSección C - Libros de referencia");
        System.out.println("1. Agregar libro");
        System.out.println("2. Avanzar al siguiente libro");
        System.out.println("3. Buscar por autor");
        System.out.println("4. Revisar si está vacía");
        System.out.println("0. Volver");
    }
}
