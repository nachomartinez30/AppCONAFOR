package gob.conafor.suelo.mod;

public class CEHojarasca {

    // Modulo E, Sección 8.1 Capas de Hojarasca(HO) y Fermentación(F)
    private int hojarascaID;
    private int sitioID;
    private Integer punto;
    private int tipoID;
    private Float espesorHO;
    private Float espesorF;
    private Float pesoTotalHO;
    private Float pesoTotalF;
    private Float pesoMuestraHO;
    private Float pesoMuestraF;
    private String observaciones;
    private String claveHO;
    private String claveF;

    public int getHojarascaID() {
        return hojarascaID;
    }

    public void setHojarascaID(int hojarascaID) {
        this.hojarascaID = hojarascaID;
    }

    public int getSitioID() {
        return sitioID;
    }

    public void setSitioID(int sitioID) {
        this.sitioID = sitioID;
    }

    public Integer getPunto() {
        return punto;
    }

    public void setPunto(Integer punto) {
        this.punto = punto;
    }

    public int getTipoID() {
        return tipoID;
    }

    public void setTipoID(int tipoID) {
        this.tipoID = tipoID;
    }

    public Float getEspesorHO() {
        return espesorHO;
    }

    public void setEspesorHO(Float espesorHO) {
        this.espesorHO = espesorHO;
    }

    public Float getEspesorF() {
        return espesorF;
    }

    public void setEspesorF(Float espesorF) {
        this.espesorF = espesorF;
    }

    public Float getPesoTotalHO() {
        return pesoTotalHO;
    }

    public void setPesoTotalHO(Float pesoTotalHO) {
        this.pesoTotalHO = pesoTotalHO;
    }

    public Float getPesoTotalF() {
        return pesoTotalF;
    }

    public void setPesoTotalF(Float pesoTotalF) {
        this.pesoTotalF = pesoTotalF;
    }

    public Float getPesoMuestraHO() {
        return pesoMuestraHO;
    }

    public void setPesoMuestraHO(Float pesoMuestraHO) {
        this.pesoMuestraHO = pesoMuestraHO;
    }

    public Float getPesoMuestraF() {
        return pesoMuestraF;
    }

    public void setPesoMuestraF(Float pesoMuestraF) {
        this.pesoMuestraF = pesoMuestraF;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getClaveHO() {
        return claveHO;
    }

    public void setClaveHO(String claveHO) {
        this.claveHO = claveHO;
    }

    public String getClaveF() {
        return claveF;
    }

    public void setClaveF(String claveF) {
        this.claveF = claveF;
    }
}
