package gob.conafor.carbono.mod;

public class CECubiertaVegetal {
    //Módulo A, Sección 4.10.2.2 Altura de arbustos, hierbas y pastos.
    private int cubiertaVegetalID;
    private int sitioID;
    private Integer transecto;
    private Integer componente;
    private Float altura5;
    private Float altura10;

    public int getCubiertaVegetalID() {
        return cubiertaVegetalID;
    }

    public void setCubiertaVegetalID(int cubiertaVegetalID) {
        this.cubiertaVegetalID = cubiertaVegetalID;
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

    public Integer getComponente() {
        return componente;
    }

    public void setComponente(Integer componente) {
        this.componente = componente;
    }

    public Float getAltura5() {
        return altura5;
    }

    public void setAltura5(Float altura5) {
        this.altura5 = altura5;
    }

    public Float getAltura10() {
        return altura10;
    }

    public void setAltura10(Float altura10) {
        this.altura10 = altura10;
    }
    
}
