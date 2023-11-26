package trabajoEscritoresLectores;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

public class Controlador {
    public static String libro = "";
    private int numLectores;
    private int numEscritores;
    public boolean hayEscritor;

    // Método llamado por los lectores para empezar a leer
    public synchronized void empezarLeer(int id) throws InterruptedException {
        // Mientras haya un escritor escribiendo o hay escritores esperando para escribir,
        // el lector espera.
        while (hayEscritor || numEscritores > 0) {
            wait();
        }
        // Incrementa el número de lectores activos
        numLectores++;
        System.out.println("Lector: " + id + " esta leyendo, hay " + numLectores + " leyendo");
    }

    // Método llamado por los lectores para indicar que han terminado de leer
    public synchronized void terminarLeer(int id) {
        // Decrementa el número de lectores activos
        numLectores--;
        System.out.println("Lector: " + id + " ha dejado de leer, ahora hay " + numLectores + " leyendo");
        // Si no hay más lectores, notifica a todos los hilos
        if (numLectores == 0) {
            notifyAll();
        }
    }

    // Método llamado por los escritores para empezar a escribir
    public synchronized void empezarEscribir(int id) throws InterruptedException {
        // Mientras haya un escritor escribiendo o hay lectores leyendo,
        // el escritor espera.
        while (hayEscritor || numLectores > 0) {
            wait();
        }
        // Marca que hay un escritor escribiendo
        hayEscritor = true;
        // Incrementa el número de escritores activos
        numEscritores++;
        // Actualiza el contenido del "libro".
        libro = libro.concat(String.valueOf(id));
        System.out.println("Escritor: " + id + " esta escribiendo, hay " + numEscritores + " escribiendo" + " libro:" + libro);
    }

    // Método llamado por los escritores para indicar que han terminado de escribir
    public synchronized void terminarEscribir(int id) {
        // Decrementa el número de escritores activos
        numEscritores--;
        System.out.println("Escritor: " + id + " ha dejado de escribir, ahora hay " + numEscritores + " escribiendo");
        // Marca que no hay un escritor escribiendo y notifica a todos los hilos
        hayEscritor = false;
        notifyAll();
    }
}
