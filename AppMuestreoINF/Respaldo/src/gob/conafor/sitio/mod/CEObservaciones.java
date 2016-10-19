package gob.conafor.sitio.mod;

public class CEObservaciones {
    
    private String observacionesID;
    private Integer sitioID;
    private Integer formatoID;
    private String observaciones;

    public String getObservacionesID() {
        return observacionesID;
    }

    public void setObservacionesID(String observacionesID) {
        this.observacionesID = observacionesID;
    }

    public Integer getSitioID() {
        return sitioID;
    }

    public void setSitioID(Integer sitioID) {
        this.sitioID = sitioID;
    }

    public Integer getFormatoID() {
        return formatoID;
    }

    public void setFormatoID(Integer formatoID) {
        this.formatoID = formatoID;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

}
