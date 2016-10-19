package gob.conafor.suelo.mod;

public class CEErosionHidricaCarcavas {
    // Módulo A, Sección 4.11.6.1 Erosion hídrica por deformación del terreno
    private int erosionCarcavasID;
    private int sitioID;
    private int medicion;
    private Float profundidad;
    private Float ancho;
    private Float distancia;
    private Integer azimut;

    public int getErosionCarcavasID() {
        return erosionCarcavasID;
    }

    public void setErosionCarcavasID(int erosionCarcavasID) {
        this.erosionCarcavasID = erosionCarcavasID;
    }

    public int getSitioID() {
        return sitioID;
    }

    public void setSitioID(int sitioID) {
        this.sitioID = sitioID;
    }

    public int getMedicion() {
        return medicion;
    }

    public void setMedicion(int medicion) {
        this.medicion = medicion;
    }

    public Float getProfundidad() {
        return profundidad;
    }

    public void setProfundidad(Float profundidad) {
        this.profundidad = profundidad;
    }

    public Float getAncho() {
        return ancho;
    }

    public void setAncho(Float ancho) {
        this.ancho = ancho;
    }

    public Float getDistancia() {
        return distancia;
    }

    public void setDistancia(Float distancia) {
        this.distancia = distancia;
    }

    public Integer getAzimut() {
        return azimut;
    }

    public void setAzimut(Integer azimut) {
        this.azimut = azimut;
    }

}
