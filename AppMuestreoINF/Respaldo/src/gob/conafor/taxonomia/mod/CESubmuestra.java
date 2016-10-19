package gob.conafor.taxonomia.mod;

public class CESubmuestra {
    // Submuestra del arbolado
    private int submuestraID;
    private int consecutivo;
    private int noIndividuo;
    private int noRama;
    private int sitioID;
    private int arboladoID;
    private String familia;
    private String genero;
    private String especie;
    private Float diametroBasal;
    private Integer edad;
    private Integer numeroAnillos25;
    private Float longitudAnillos10;
    private Float grozorCorteza;

    public int getSubmuestraID() {
        return submuestraID;
    }

    public void setSubmuestraID(int submuestraID) {
        this.submuestraID = submuestraID;
    }

    public int getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(int consecutivo) {
        this.consecutivo = consecutivo;
    }

    public int getNoIndividuo() {
        return noIndividuo;
    }

    public void setNoIndividuo(int noIndividuo) {
        this.noIndividuo = noIndividuo;
    }

    public int getNoRama() {
        return noRama;
    }

    public void setNoRama(int noRama) {
        this.noRama = noRama;
    }

    public int getSitioID() {
        return sitioID;
    }

    public void setSitioID(int sitioID) {
        this.sitioID = sitioID;
    }

    public int getArboladoID() {
        return arboladoID;
    }

    public void setArboladoID(int arboladoID) {
        this.arboladoID = arboladoID;
    }

    public String getFamilia() {
        return familia;
    }

    public void setFamilia(String familia) {
        this.familia = familia;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public Float getDiametroBasal() {
        return diametroBasal;
    }

    public void setDiametroBasal(Float diametroBasal) {
        this.diametroBasal = diametroBasal;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Integer getNumeroAnillos25() {
        return numeroAnillos25;
    }

    public void setNumeroAnillos25(Integer numeroAnillos25) {
        this.numeroAnillos25 = numeroAnillos25;
    }

    public Float getLongitudAnillos10() {
        return longitudAnillos10;
    }

    public void setLongitudAnillos10(Float longitudAnillos10) {
        this.longitudAnillos10 = longitudAnillos10;
    }

    public Float getGrozorCorteza() {
        return grozorCorteza;
    }

    public void setGrozorCorteza(Float grozorCorteza) {
        this.grozorCorteza = grozorCorteza;
    }
}
