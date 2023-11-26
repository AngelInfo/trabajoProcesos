package trabajoEscritoresLectores2;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public class Escritor2 implements Runnable {
    private int id;
    //AtomicInteger que va sumando
    public static AtomicInteger contador = new AtomicInteger(0);

    //controlador, objeto que usamos para acceder a los metodos.
    private final RW_Monitor_4 controlador;

    //Constructor al que se le pasa un objeto Controlador y cada vez que se llama va incrementando él id de Escritor.
    public Escritor2(RW_Monitor_4 controlador) {
        this.controlador = controlador;
        this.id = contador.incrementAndGet();
    }

    @Override
    public void run() {

        try {
            controlador.openWriting(id);
            Thread.sleep(ThreadLocalRandom.current().nextInt(200));

            controlador.closeWriting(id);
            Thread.sleep(ThreadLocalRandom.current().nextInt(200));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }
}
