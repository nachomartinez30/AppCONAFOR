package gob.conafor.pc.mod;

public class CEPuntoControl {

    private int upmID;
    private String descripcion;
    private String paraje;
    private Integer gradosLatitud;
    private Integer minutosLatitud;
    private Float segundosLatitud;
    private Integer gradosLongitud;
    private Integer minutosLongitud;
    private Float segundosLongitud;
    private String datum;
    private Integer errorPrecision;
    private Integer azimut;
    private Float distancia;

    public int getUpmID() {
        return upmID;
    }

    public void setUpmID(int upmID) {
        this.upmID = upmID;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getParaje() {
        return paraje;
    }

    public void setParaje(String paraje) {
        this.paraje = paraje;
    }

    public Integer getGradosLatitud() {
        return gradosLatitud;
    }

    public void setGradosLatitud(Integer gradosLatitud) {
        this.gradosLatitud = gradosLatitud;
    }

    public Integer getMinutosLatitud() {
        return minutosLatitud;
    }

    public void setMinutosLatitud(Integer minutosLatitud) {
        this.minutosLatitud = minutosLatitud;
    }

    public Float getSegundosLatitud() {
        return segundosLatitud;
    }

    public void setSegundosLatitud(Float segundosLatitud) {
        this.segundosLatitud = segundosLatitud;
    }

    public Integer getGradosLongitud() {
        return gradosLongitud;
    }

    public void setGradosLongitud(Integer gradosLongitud) {
        this.gradosLongitud = gradosLongitud;
    }

    public Integer getMinutosLongitud() {
        return minutosLongitud;
    }

    public void setMinutosLongitud(Integer minutosLongitud) {
        this.minutosLongitud = minutosLongitud;
    }

    public Float getSegundosLongitud() {
        return segundosLongitud;
    }

    public void setSegundosLongitud(Float segundosLongitud) {
        this.segundosLongitud = segundosLongitud;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public Integer getErrorPrecision() {
        return errorPrecision;
    }

    public void setErrorPrecision(Integer errorPrecision) {
        this.errorPrecision = errorPrecision;
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

}
