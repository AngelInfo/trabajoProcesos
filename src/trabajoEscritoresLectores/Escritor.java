package trabajoEscritoresLectores;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

import static trabajoEscritoresLectores.Controlador.*;

public class Escritor implements Runnable {
    private int id;
    public static AtomicInteger contador = new AtomicInteger(0);

    private final Controlador controlador;

    public Escritor(Controlador controlador) {
        this.controlador = controlador;
        this.id = contador.incrementAndGet();
    }

    @Override
    public void run() {
        while (true) {
            try {
                controlador.empezarEscribir(id);
                Thread.sleep(200);
                controlador.terminarEscribir(id);
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
