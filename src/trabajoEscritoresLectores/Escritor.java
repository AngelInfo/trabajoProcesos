package trabajoEscritoresLectores;

import trabajoEscritoresLectores2.RW_Monitor_4;

import java.util.concurrent.atomic.AtomicInteger;

public class Escritor implements Runnable {
    private int id;
    //AtomicInteger que va sumando
    public static AtomicInteger contador = new AtomicInteger(0);

    //controlador, objeto que usamos para acceder a los metodos.
    private final Controlador controlador;

    //Constructor al que se le pasa un objeto Controlador y cada vez que se llama va incrementando él id de Escritor.
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
