package gob.conafor.sitio.mod;

public class CEParametrosFisicoQuimicos {

    //Módulo G, Sección 10.1 Parámetros Físico-Químicos, Agua superficial e intersticial
    private int sitioID;
    private Boolean tipoAgua;
    private Float salinidad;
    private Float temperaturaAgua;
    private Float conductividadElectrica;
    private Float ph;
    private Float potencialRedox;
    private Float profundidad;
    private String observaciones;

    public int getSitioID() {
        return sitioID;
    }

    public void setSitioID(int sitioID) {
        this.sitioID = sitioID;
    }

    public Boolean getTipoAgua() {
        return tipoAgua;
    }

    public void setTipoAgua(Boolean tipoAgua) {
        this.tipoAgua = tipoAgua;
    }

    public Float getSalinidad() {
        return salinidad;
    }

    public void setSalinidad(Float salinidad) {
        this.salinidad = salinidad;
    }

    public Float getTemperaturaAgua() {
        return temperaturaAgua;
    }

    public void setTemperaturaAgua(Float temperaturaAgua) {
        this.temperaturaAgua = temperaturaAgua;
    }

    public Float getConductividadElectrica() {
        return conductividadElectrica;
    }

    public void setConductividadElectrica(Float conductividadElectrica) {
        this.conductividadElectrica = conductividadElectrica;
    }

    public Float getPh() {
        return ph;
    }

    public void setPh(Float ph) {
        this.ph = ph;
    }

    public Float getPotencialRedox() {
        return potencialRedox;
    }

    public void setPotencialRedox(Float potencialRedox) {
        this.potencialRedox = potencialRedox;
    }

    public Float getProfundidad() {
        return profundidad;
    }

    public void setProfundidad(Float profundidad) {
        this.profundidad = profundidad;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
   
}
