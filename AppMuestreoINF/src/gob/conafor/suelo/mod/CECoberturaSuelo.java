package gob.conafor.suelo.mod;

public class CECoberturaSuelo {
    //Módulo A, Sección 4.11.2 Cobertura del suelo y evidencia de 
    //erosión del suelo
    private int coberturaSueloID;
    private int sitioID;
    private Integer transecto;
    private Float pendiente;

    public int getCoberturaSueloID() {
        return coberturaSueloID;
    }

    public void setCoberturaSueloID(int coberturaSueloID) {
        this.coberturaSueloID = coberturaSueloID;
    }

    public int getSitioID() {
        return sitioID;
    }

    public void setSitioID(int sitioID) {
        this.sitioID = sitioID;
    }

    public Integer getTransecto() {
        return transecto;
    }

    public void setTransecto(Integer transecto) {
        this.transecto = transecto;
    }
    
    public Float getPendiente() {
        return pendiente;
    }

    public void setPendiente(Float pendiente) {
        this.pendiente = pendiente;
    }

}
