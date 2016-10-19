package gob.conafor.suelo.mod;

public class CEErosionLaminar {
    // Módulo A, Sección 4.11.2.2 Evaluación de las condiciones de degradación
    // del suelo
    private int erosionLaminarID;
    private int sitioID;
    private int numero;
    private Float ancho;
    private Float largo;

    public int getErosionLaminarID() {
        return erosionLaminarID;
    }

    public void setErosionLaminarID(int erosionLaminarID) {
        this.erosionLaminarID = erosionLaminarID;
    }

    public int getSitioID() {
        return sitioID;
    }

    public void setSitioID(int sitioID) {
        this.sitioID = sitioID;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Float getAncho() {
        return ancho;
    }

    public void setAncho(Float ancho) {
        this.ancho = ancho;
    }

    public Float getLargo() {
        return largo;
    }

    public void setLargo(Float largo) {
        this.largo = largo;
    }
    
}
