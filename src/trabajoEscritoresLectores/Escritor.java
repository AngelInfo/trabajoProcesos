package trabajoEscritoresLectores;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

import static trabajoEscritoresLectores.Controlador.*;

public class Escritor implements Runnable {
    private int id;
    public static AtomicInteger contador = new AtomicInteger(0);

    private final Controlador controlador;
    public Escritor(Controlador controlador) {
        this.controlador=controlador;
        this.id = contador.incrementAndGet();
    }

    @Override
    public void run() {
        try {
            controlador.empezarEscribir();
            System.out.println("Escritor: " + id + " esta escribiendo, hay " + controlador.numEscritores + " escribiendo");
            libro=libro.concat(String.valueOf(id));
            Thread.sleep(ThreadLocalRandom.current().nextInt(3000));
            controlador.terminarEscribir();
            System.out.println("Escritor: " + id + " ha dejado de escribir, ahora hay " + controlador.numEscritores + " escribiendo");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
