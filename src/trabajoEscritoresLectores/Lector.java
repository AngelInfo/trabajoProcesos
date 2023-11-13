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

        try {
            controlador.empezarLeer();
            System.out.println("Lector: " + id + " esta escribiendo, hay " + controlador.numLectores + " escribiendo");
            Thread.sleep(ThreadLocalRandom.current().nextInt(3000));
            controlador.terminarLeer();
            System.out.println("Lector: " + id + " ha dejado de escribir, ahora hay " + controlador.numLectores + " escribiendo");
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
    }

}
