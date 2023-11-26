package trabajoEscritoresLectores2;

import java.util.ArrayList;
import java.util.List;

public class Monitor {
    private int nLectores = 0;
    private int nEscritores = 0;
    public static String libro = "";
    private List<Thread> EscritoresCola = new ArrayList<>();

    // Método llamado por los lectores para empezar a leer
    public synchronized void empezarLeer(int id) {
        // Espera hasta que sea posible leer
        while (!puedeLeer())
            esperando();
        nLectores++;
        System.out.println("Lector: " + id + " esta leyendo, hay " + nLectores + " leyendo");
    }

    // Método privado que verifica si es posible leer
    private boolean puedeLeer() {
        if (nEscritores > 0) return false;
        if (EscritoresCola.size() > 0) return false;
        return true;
    }

    // Método llamado por los lectores para indicar que han terminado de leer
    public synchronized void terminarLeer(int id) {
        nLectores--;
        notifyAll();
        System.out.println("Lector: " + id + " ha terminado de leer, ahora hay " + nLectores + " leyendo");
    }

    // Método llamado por los escritores para empezar a escribir
    public synchronized void EmpezarEsribir(int id) {
        Thread me = Thread.currentThread();
        EscritoresCola.add(me);

        // Espera hasta que sea posible escribir
        while (!puedeEsribir(me))
            esperando();

        EscritoresCola.remove(me);
        nEscritores++;
        libro = libro.concat(String.valueOf(id));
        System.out.println("Escritor " + id + ": esta escribiendo, hay " + nEscritores + " escribiendo" + " libro:" + libro);
    }

    // Método privado que verifica si es posible escribir
    private boolean puedeEsribir(Thread me) {
        if (nLectores > 0) return false;
        if (nEscritores > 0) return false;
        if (EscritoresCola.size() > 0 && EscritoresCola.get(0) != me) return false;
        return true;
    }

    // Método llamado por los escritores para indicar que han terminado de escribir
    public synchronized void terminarEscribir(int id) {
        nEscritores--;
        notifyAll();
        libro = libro.concat(String.valueOf(id));
        System.out.println("Escritor " + id + ": ha dejado de escribir, ahora hay " + nEscritores + " escribiendo");
    }

    // Método privado para manejar la espera
    private synchronized void esperando() {
        try {
            wait();
        } catch (InterruptedException ignored) {
            // Ignora la interrupción
        }
    }
}
