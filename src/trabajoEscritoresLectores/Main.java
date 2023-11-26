package trabajoEscritoresLectores;

import java.util.ArrayList;
import java.util.List;

import static trabajoEscritoresLectores.Controlador.libro;

public class Main {
    public static void main(String[] args) {
        Controlador c = new Controlador();
        //lista de lectores
        List<Lector> lectores = new ArrayList<>();
        //lista de escritores
        List<Escritor> escritores = new ArrayList<>();
        //bucle lectores
        for (int i = 0; i < 5; i++) {
            lectores.add(new Lector(c));
            new Thread(lectores.get(i)).start();
        }
        //bucle escritores
        for (int i = 0; i < 2; i++) {
            escritores.add(new Escritor(c));
            new Thread(escritores.get(i)).start();
        }
    }
}
