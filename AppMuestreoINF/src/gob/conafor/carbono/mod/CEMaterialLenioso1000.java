package gob.conafor.carbono.mod;

public class CEMaterialLenioso1000 {
    //Módulo A, Sección 4.10.1 Material leñoso caído
    private int materialLenioso1000ID;
    private int sitioID;
    private Integer transecto;
    private Float diametro;
    private Integer grado;

    public int getMaterialLenioso1000ID() {
        return materialLenioso1000ID;
    }

    public void setMaterialLenioso1000ID(int materialLenioso1000ID) {
        this.materialLenioso1000ID = materialLenioso1000ID;
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

    public Float getDiametro() {
        return diametro;
    }

    public void setDiametro(Float diametro) {
        this.diametro = diametro;
    }

    public Integer getGrado() {
        return grado;
    }

    public void setGrado(Integer grado) {
        this.grado = grado;
    }
    
}
