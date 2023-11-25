package trabajoEscritoresLectores;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

public class Controlador {
    public static String libro = "";
    private int numLectores;
    private int numEscritores;

    public boolean hayEscritor;



    /**
     * Semaphore writerSemaphore = new Semaphore(1, true);
     * Semaphore readerSemaphore = new Semaphore(1, true);
     public void escritor(int id) throws InterruptedException {
        writerSemaphore.acquire();
        if (numLectores == 0 || numEscritores == 0) {
            readerSemaphore.acquire();
        }
        numEscritores++;
        libro = libro.concat(String.valueOf(id));
        System.out.println("Escritor: " + id + " esta escribiendo, hay " + numEscritores + " escribiendo" + " libro:" + libro);
        Thread.sleep(ThreadLocalRandom.current().nextInt(3000));
        numEscritores--;
        System.out.println("Escritor: " + id + " ha dejado de escribir, ahora hay " + numEscritores + " escribiendo");
        writerSemaphore.release();
    }

    public void lector(int id) throws InterruptedException {
        if (numEscritores > 0) {
            readerSemaphore.acquire();
        }
        numLectores++;
        System.out.println("Lector: " + id + " esta leyendo, hay " + numLectores + " leyendo");
        Thread.sleep(ThreadLocalRandom.current().nextInt(3000));
        numLectores--;
        System.out.println("Lector: " + id + " ha dejado de leer, ahora hay " + numLectores + " leyendo");
        readerSemaphore.release();
    }
     **/

    public synchronized void empezarLeer(int id) throws InterruptedException {
        while (hayEscritor || numEscritores>0) {
            wait();
        }
        numLectores++;
        System.out.println("Lector: " + id + " esta leyendo, hay " + numLectores + " leyendo");
    }

    public synchronized void terminarLeer(int id) {
        numLectores--;
        System.out.println("Lector: " + id + " ha dejado de leer, ahora hay " + numLectores + " leyendo");
        if (numLectores == 0) {
            notifyAll();
        }
    }

    public synchronized void empezarEscribir(int id) throws InterruptedException {
        while (hayEscritor || numLectores > 0) {
            wait();
        }
        hayEscritor=true;
        numEscritores++;
        libro = libro.concat(String.valueOf(id));
        System.out.println("Escritor: " + id + " esta escribiendo, hay " + numEscritores + " escribiendo" + " libro:" + libro);
    }
    public synchronized void terminarEscribir(int id){
        numEscritores--;
        System.out.println("Escritor: " + id + " ha dejado de escribir, ahora hay " + numEscritores + " escribiendo");
        hayEscritor=false;
        notifyAll();
    }

}
