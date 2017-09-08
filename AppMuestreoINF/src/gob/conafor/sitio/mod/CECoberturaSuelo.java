package gob.conafor.sitio.mod;

public class CECoberturaSuelo {

    //Módulo A, Sección 4.3 Registro de vegetación menor y cobertura del suelo.
    //Módulo G, Sección 10.2.1 Registro de vegetación menor y cobertura de suelo (sitio de 1m).
    
    private int sitioID;
    private int gramineas;
    private int helechos;
    private int musgos;
    private int liquenes;
    private int hierbas;
    private int roca;
    private int sueloDesnudo;
    private int hojarasca;
    private int grava;
    private int otros;
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

    public int getGramineas() {
        return gramineas;
    }

    public void setGramineas(int gramineas) {
        this.gramineas = gramineas;
    }

    public int getHelechos() {
        return helechos;
    }

    public void setHelechos(int helechos) {
        this.helechos = helechos;
    }

    public int getMusgos() {
        return musgos;
    }

    public void setMusgos(int musgos) {
        this.musgos = musgos;
    }

    public int getLiquenes() {
        return liquenes;
    }

    public void setLiquenes(int liquenes) {
        this.liquenes = liquenes;
    }

    public int getHierbas() {
        return hierbas;
    }

    public void setHierbas(int hierbas) {
        this.hierbas = hierbas;
    }

    public int getRoca() {
        return roca;
    }

    public void setRoca(int roca) {
        this.roca = roca;
    }

    public int getSueloDesnudo() {
        return sueloDesnudo;
    }

    public void setSueloDesnudo(int sueloDesnudo) {
        this.sueloDesnudo = sueloDesnudo;
    }

    public int getHojarasca() {
        return hojarasca;
    }

    public void setHojarasca(int hojarasca) {
        this.hojarasca = hojarasca;
    }

    public int getGrava() {
        return grava;
    }

    public void setGrava(int grava) {
        this.grava = grava;
    }

    public int getOtros() {
        return otros;
    }

    public void setOtros(int otros) {
        this.otros = otros;
    }

}
