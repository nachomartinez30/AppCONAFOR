package gob.conafor.suelo.mod;

public class CEDensidadAparente {

    // Módulo E, Sección 8.3 muestreo de densidad aparente(DAP)
    private int densidadAparenteID;
    private int sitioID;
    private Float profundidadReal;
    private Float diametroCilindro;
    private Float volumen;
    private Float pesoTotalSuelo;
    private Float pesoTotalMuestra;
    private String observaciones;

    public int getDensidadAparenteID() {
        return densidadAparenteID;
    }

    public void setDensidadAparenteID(int densidadAparenteID) {
        this.densidadAparenteID = densidadAparenteID;
    }

    public int getSitioID() {
        return sitioID;
    }

    public void setSitioID(int sitioID) {
        this.sitioID = sitioID;
    }

    public Float getProfundidadReal() {
        return profundidadReal;
    }

    public void setProfundidadReal(Float profundidadReal) {
        this.profundidadReal = profundidadReal;
    }

    public Float getDiametroCilindro() {
        return diametroCilindro;
    }

    public void setDiametroCilindro(Float diametroCilindro) {
        this.diametroCilindro = diametroCilindro;
    }

    public Float getVolumen() {
        return volumen;
    }

    public void setVolumen(Float volumen) {
        this.volumen = volumen;
    }

    public Float getPesoTotalSuelo() {
        return pesoTotalSuelo;
    }

    public void setPesoTotalSuelo(Float pesoTotalSuelo) {
        this.pesoTotalSuelo = pesoTotalSuelo;
    }

    public Float getPesoTotalMuestra() {
        return pesoTotalMuestra;
    }

    public void setPesoTotalMuestra(Float pesoTotalMuestra) {
        this.pesoTotalMuestra = pesoTotalMuestra;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

}
