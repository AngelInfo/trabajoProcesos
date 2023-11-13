package trabajoEscritoresLectores;

public class Controlador {
    public static String libro="";
    public int numLectores;
    public int numEscritores;

    public synchronized void empezarLeer() throws InterruptedException {
        if (numEscritores<=0) {
            numLectores++;
        }else {
            wait();
        }
    }
    public synchronized void terminarLeer(){
        numLectores--;
        if (numLectores==0)
            notifyAll();
    }
    public synchronized void empezarEscribir() throws InterruptedException {
        if (numEscritores<=0 && numLectores<=0) {
            numEscritores++;
        }else
            wait();
    }
    public synchronized void terminarEscribir(){
        numEscritores--;
        notifyAll();
    }

}
