package gob.conafor.sitio.mod;

public class CEFotografiaHemisferica {

    //Módulo F, Sección 9 Toma de fotografías hemisféricas.
    private int sitioID;
    private int coberturaArborea;
    private int tomaFotografia;
    private String hora;
    private Integer declinacionMagnetica;

    public int getSitioID() {
        return sitioID;
    }

    public void setSitioID(int sitioID) {
        this.sitioID = sitioID;
    }

    public int getCoberturaArborea() {
        return coberturaArborea;
    }

    public void setCoberturaArborea(int coberturaArborea) {
        this.coberturaArborea = coberturaArborea;
    }

    public int getTomaFotografia() {
        return tomaFotografia;
    }

    public void setTomaFotografia(int tomaFotografia) {
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
