package gob.conafor.sitio.mod;

public class CETransponder {
    //Módulo A, Sección 4.12 Colocación del TAG o transponder.
    private int transpoderID;
    private int sitioID;
    private int sitio;
    private int tipoColocacionID;
    private String especifique;
    private String observaciones;

    public int getTranspoderID() {
        return transpoderID;
    }

    public void setTranspoderID(int transpoderID) {
        this.transpoderID = transpoderID;
    }

    public int getSitioID() {
        return sitioID;
    }

    public void setSitioID(int sitioID) {
        this.sitioID = sitioID;
    }

    public int getSitio() {
        return sitio;
    }

    public void setSitio(int sitio) {
        this.sitio = sitio;
    }

    public int getTipoColocacionID() {
        return tipoColocacionID;
    }

    public void setTipoColocacionID(int tipoColocacionID) {
        this.tipoColocacionID = tipoColocacionID;
    }

    public String getEspecifique() {
        return especifique;
    }

    public void setEspecifique(String especifique) {
        this.especifique = especifique;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

}
