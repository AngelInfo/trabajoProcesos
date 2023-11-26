package trabajoEscritoresLectores2;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public class Escritor2 implements Runnable {
    private int id;
    //AtomicInteger que va sumando
    public static AtomicInteger contador = new AtomicInteger(0);

    //controlador, objeto que usamos para acceder a los metodos.
    private final Monitor controlador;

    //Constructor al que se le pasa un objeto Monitor y cada vez que se llama va incrementando él id de Escritor.
    public Escritor2(Monitor controlador) {
        this.controlador = controlador;
        this.id = contador.incrementAndGet();
    }

    @Override
    public void run() {

        try {
            controlador.EmpezarEsribir(id);
            Thread.sleep(ThreadLocalRandom.current().nextInt(200));
            controlador.terminarEscribir(id);
            Thread.sleep(ThreadLocalRandom.current().nextInt(200));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }
}
