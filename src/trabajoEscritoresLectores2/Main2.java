package trabajoEscritoresLectores2;

import java.util.ArrayList;
import java.util.List;

public class Main2 {
    public static void main(String[] args) {
        Monitor c = new Monitor();
        //lista de lectores
        List<Lector2> lectores = new ArrayList<>();
        //lista de escritores
        List<Escritor2> escritores = new ArrayList<>();
        //bucle lectores
        for (int i = 0; i < 5; i++) {
            lectores.add(new Lector2(c));
            new Thread(lectores.get(i)).start();
        }
        //bucle escritores
        for (int i = 0; i < 2; i++) {
            escritores.add(new Escritor2(c));
            new Thread(escritores.get(i)).start();
        }
    }
}
