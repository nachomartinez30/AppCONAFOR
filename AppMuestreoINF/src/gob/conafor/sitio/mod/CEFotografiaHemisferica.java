package gob.conafor.sitio.mod;

public class CEFotografiaHemisferica {

    //Módulo F, Sección 9 Toma de fotografías hemisféricas.
    private int sitioID;
    private Boolean coberturaArborea;
    private Boolean tomaFotografia;
    private String hora;
    private Integer declinacionMagnetica;
    private int UPMID;

    public int getUPMID() {
        return UPMID;
    }

    public void setUPMID(int UPMID) {
        this.UPMID = UPMID;
    }

    public int getSitioID() {
        return sitioID;
    }

    public void setSitioID(int sitioID) {
        this.sitioID = sitioID;
    }

    public Boolean getCoberturaArborea() {
        return coberturaArborea;
    }

    public void setCoberturaArborea(Boolean coberturaArborea) {
        this.coberturaArborea = coberturaArborea;
    }

    public Boolean getTomaFotografia() {
        return tomaFotografia;
    }

    public void setTomaFotografia(Boolean tomaFotografia) {
        this.tomaFotografia = tomaFotografia;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public Integer getDeclinacionMagnetica() {
        return declinacionMagnetica;
    }

    public void setDeclinacionMagnetica(Integer declinacionMagnetica) {
        this.declinacionMagnetica = declinacionMagnetica;
    }

}
