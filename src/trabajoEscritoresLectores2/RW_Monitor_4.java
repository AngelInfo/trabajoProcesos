package trabajoEscritoresLectores2;

import java.util.ArrayList;
import java.util.List;

public class RW_Monitor_4 {
    private int nReaders = 0;
    private int nWriters = 0;
    public static String libro="";
    private List<Thread> waitingWriters = new ArrayList<>();
    public synchronized void openReading(int id) {
        while (!canRead())
            waiting();
        nReaders++;
        System.out.println("Lector: " + id + " esta leyendo, hay "+nReaders+" leyendo");
    }
    private boolean canRead() {
        if (nWriters > 0) return false;
        if (waitingWriters.size() > 0) return false;
        return true;
    }
    public synchronized void closeReading(int id) {
        nReaders--;
        notifyAll();
        System.out.println("Lector: " + id + " ha terminado de leer, ahora hay "+nReaders+" leyendo");

    }
    public synchronized void openWriting(int id) {
        Thread me = Thread.currentThread();
        waitingWriters.add(me);
        while (!canWrite(me))
            waiting();
        waitingWriters.remove(me);
        nWriters++;
        libro = libro.concat(String.valueOf(id));
        System.out.println("Escritor " + id + ": esta escribiendo, hay " + nWriters + " escribiendo" + " libro:" + libro);
    }
    private boolean canWrite(Thread me) {
        if (nReaders > 0) return false;
        if (nWriters > 0) return false;
        if (waitingWriters.get(0) != me) return false;
        return true;
    }
    public synchronized void closeWriting(int id) {
        nWriters--;
        notifyAll();
        libro = libro.concat(String.valueOf(id));
        System.out.println("Escritor " + id + ": ha dejado de escribir, ahora hay " + nWriters + " escribiendo");
    }

    private synchronized void waiting() {
        try {
            wait();
        } catch (InterruptedException ignored) {
        }
    }
}