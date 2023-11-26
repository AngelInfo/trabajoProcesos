package trabajoEscritoresLectores2;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public class Lector2 implements Runnable {
    //AtomicInteger que va sumando
    public static AtomicInteger contadorL = new AtomicInteger(0);
    private int id;
    //controlador, objeto que usamos para acceder a los metodos.
    private Monitor controlador;

    //Constructor al que se le pasa un objeto Monitor y cada vez que se llama va incrementando él id de Lector.
    public Lector2(Monitor controlador) {
        this.id = contadorL.incrementAndGet();
        this.controlador = controlador;
    }

    //metodo que ejecuta el hilo
    public void run() {

            try {
                controlador.empezarLeer(id);
                Thread.sleep(ThreadLocalRandom.current().nextInt(200));
                controlador.terminarLeer(id);
                Thread.sleep(ThreadLocalRandom.current().nextInt(200));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

    }

}
