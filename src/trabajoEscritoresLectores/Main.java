package trabajoEscritoresLectores;

import static trabajoEscritoresLectores.Controlador.libro;

public class Main {
    public static void main(String[] args) {
        Controlador c = new Controlador();
        for (int i = 0; i < 10; i++) {
            Thread lectores = new Thread(new Lector(c));
            lectores.start();
        }
        for (int i = 0; i < 10; i++) {
            Thread escritores = new Thread(new Escritor(c));
            escritores.start();
        }
        System.out.println(libro);
    }
}
