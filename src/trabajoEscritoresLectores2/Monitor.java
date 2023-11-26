package trabajoEscritoresLectores2;

import java.util.ArrayList;
import java.util.List;

public class Monitor {
    private int nLectores = 0;
    private int nEscritores = 0;
    public static String libro="";
    private List<Thread> EscritoresCola = new ArrayList<>();
    public synchronized void empezarLeer(int id) {
        while (!puedeLeer())
            esperando();
        nLectores++;
        System.out.println("Lector: " + id + " esta leyendo, hay "+ nLectores +" leyendo");
    }
    private boolean puedeLeer() {
        if (nEscritores > 0) return false;
        if (EscritoresCola.size() > 0) return false;
        return true;
    }
    public synchronized void terminarLeer(int id) {
        nLectores--;
        notifyAll();
        System.out.println("Lector: " + id + " ha terminado de leer, ahora hay "+ nLectores +" leyendo");

    }
    public synchronized void EmpezarEsribir(int id) {
        Thread me = Thread.currentThread();
        EscritoresCola.add(me);
        while (!puedeEsribir(me))
            esperando();
        EscritoresCola.remove(me);
        nEscritores++;
        libro = libro.concat(String.valueOf(id));
        System.out.println("Escritor " + id + ": esta escribiendo, hay " + nEscritores + " escribiendo" + " libro:" + libro);
    }
    private boolean puedeEsribir(Thread me) {
        if (nLectores > 0) return false;
        if (nEscritores > 0) return false;
        if (EscritoresCola.get(0) != me) return false;
        return true;
    }
    public synchronized void terminarEscribir(int id) {
        nEscritores--;
        notifyAll();
        libro = libro.concat(String.valueOf(id));
        System.out.println("Escritor " + id + ": ha dejado de escribir, ahora hay " + nEscritores + " escribiendo");
    }

    private synchronized void esperando() {
        try {
            wait();
        } catch (InterruptedException ignored) {
        }
    }
}