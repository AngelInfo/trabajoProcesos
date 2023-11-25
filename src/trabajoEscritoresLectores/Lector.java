package trabajoEscritoresLectores;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

import static trabajoEscritoresLectores.Controlador.*;

public class Lector implements Runnable {
    public static AtomicInteger contadorL = new AtomicInteger(0);
    private int id;
    private Controlador controlador;

    public Lector(Controlador controlador) {
        this.id = contadorL.incrementAndGet();
        this.controlador = controlador;
    }
    public void run() {
        while (true) {
            try {
                controlador.empezarLeer(id);
                Thread.sleep(200);
                controlador.terminarLeer(id);
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }
        }
    }

}
