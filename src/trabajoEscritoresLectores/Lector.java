package trabajoEscritoresLectores;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

import static trabajoEscritoresLectores.Controlador.*;

public class Lector implements Runnable {
    //AtomicInteger que va sumando
    public static AtomicInteger contadorL = new AtomicInteger(0);
    private int id;
    //controlador, objeto que usamos para acceder a los metodos.
    private Controlador controlador;

    //Constructor al que se le pasa un objeto Controlador y cada vez que se llama va incrementando el id de Lector.
    public Lector(Controlador controlador) {
        this.id = contadorL.incrementAndGet();
        this.controlador = controlador;
    }

    //metodo que ejecuta el hilo
    public void run() {
        while (true) {
            try {
                //metodo que hace que empiece a leer el libro.
                controlador.empezarLeer(id);
                //El hilo se espera una cantidad entre 0 y 200 milisegundos
                Thread.sleep(ThreadLocalRandom.current().nextInt(200));
                //metodo que hace que termine de leer el libro.
                controlador.terminarLeer(id);
                //El hilo se espera al terminar una cantidad entre 0 y 200 milisegundos
                Thread.sleep(ThreadLocalRandom.current().nextInt(200));
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }
        }
    }

}
