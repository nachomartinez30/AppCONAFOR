package gob.conafor.suelo.mod;

public class CEProfundidadSuelo {

    // Módulo E, Sección 8.2 Suelo a las profundidades de 0-30 cm y 30-60 cm.
    private int profundidadSueloID;
    private int sitioID;
    private int punto;
    private Float profundidad030;
    private Float profundidad3060;
    private Float peso030;
    private Float peso3060;
    private String equipo030;
    private String equipo3060;
    private String observaciones;
    private String clave030;
    private String clave3060;

    public int getProfundidadSueloID() {
        return profundidadSueloID;
    }

    public void setProfundidadSueloID(int profundidadSueloID) {
        this.profundidadSueloID = profundidadSueloID;
    }

    public int getSitioID() {
        return sitioID;
    }

    public void setSitioID(int sitioID) {
        this.sitioID = sitioID;
    }

    public int getPunto() {
        return punto;
    }

    public void setPunto(int punto) {
        this.punto = punto;
    }

    public Float getProfundidad030() {
        return profundidad030;
    }

    public void setProfundidad030(Float profundidad030) {
        this.profundidad030 = profundidad030;
    }

    public Float getProfundidad3060() {
        return profundidad3060;
    }

    public void setProfundidad3060(Float profundidad3060) {
        this.profundidad3060 = profundidad3060;
    }

    public Float getPeso030() {
        return peso030;
    }

    public void setPeso030(Float peso030) {
        this.peso030 = peso030;
    }

    public Float getPeso3060() {
        return peso3060;
    }

    public void setPeso3060(Float peso3060) {
        this.peso3060 = peso3060;
    }

    public String getEquipo030() {
        return equipo030;
    }

    public void setEquipo030(String equipo030) {
        this.equipo030 = equipo030;
    }

    public String getEquipo3060() {
        return equipo3060;
    }

    public void setEquipo3060(String equipo3060) {
        this.equipo3060 = equipo3060;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getClave030() {
        return clave030;
    }

    public void setClave030(String clave030) {
        this.clave030 = clave030;
    }

    public String getClave3060() {
        return clave3060;
    }

    public void setClave3060(String clave3060) {
        this.clave3060 = clave3060;
    }
}
