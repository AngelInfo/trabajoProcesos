package trabajoEscritoresLectores;

import java.util.ArrayList;
import java.util.List;

import static trabajoEscritoresLectores.Controlador.libro;

public class Main {
    public static void main(String[] args) {
        Controlador c = new Controlador();
        List<Lector> lectores = new ArrayList<>();
        List<Escritor> escritores = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            lectores.add(new Lector(c));
            new Thread(lectores.get(i)).start();
        }
        for (int i = 0; i < 2; i++) {
            escritores.add(new Escritor(c));
            new Thread(escritores.get(i)).start();
        }
    }
}
