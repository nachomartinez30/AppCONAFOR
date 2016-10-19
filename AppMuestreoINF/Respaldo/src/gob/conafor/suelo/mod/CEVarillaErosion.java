package gob.conafor.suelo.mod;

public class CEVarillaErosion {
    // Módulo A, Sección 4.11.5 Varillas de erosión
    private int varillaID;
    private int sitioID;
    private Integer noVarilla;
    private Integer azimut;
    private Float distancia;
    private Float profundidad;

    public int getVarillaID() {
        return varillaID;
    }

    public void setVarillaID(int varillaID) {
        this.varillaID = varillaID;
    }

    public int getSitioID() {
        return sitioID;
    }

    public void setSitioID(int sitioID) {
        this.sitioID = sitioID;
    }

    public Integer getNoVarilla() {
        return noVarilla;
    }

    public void setNoVarilla(Integer noVarilla) {
        this.noVarilla = noVarilla;
    }

    public Integer getAzimut() {
        return azimut;
    }

    public void setAzimut(Integer azimut) {
        this.azimut = azimut;
    }

    public Float getDistancia() {
        return distancia;
    }

    public void setDistancia(Float distancia) {
        this.distancia = distancia;
    }

    public Float getProfundidad() {
        return profundidad;
    }

    public void setProfundidad(Float profundidad) {
        this.profundidad = profundidad;
    }

}
