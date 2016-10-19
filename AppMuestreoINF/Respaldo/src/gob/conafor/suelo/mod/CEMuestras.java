package gob.conafor.suelo.mod;

public class CEMuestras {

    //Seccion 8.3.1 Muestras del perfil
    private Integer muestrasID;
    private Integer sitioID;
    private Integer profundidadID;
    private Float muestra = null;
    private Float pesoMuestra = null;
    private Float lectura1 = null;
    private Float lectura2 = null;
    private Float lectura3 = null;
    private Float lectura4 = null;
    private Float promedio = null;
    private String claveColecta;

    public Integer getMuestrasID() {
        return muestrasID;
    }

    public void setMuestrasID(Integer muestrasID) {
        this.muestrasID = muestrasID;
    }

    public Integer getSitioID() {
        return sitioID;
    }

    public void setSitioID(Integer sitioID) {
        this.sitioID = sitioID;
    }

    public Integer getProfundidadID() {
        return profundidadID;
    }

    public void setProfundidadID(Integer profundidadID) {
        this.profundidadID = profundidadID;
    }

    public Float getPesoMuestra() {
        return pesoMuestra;
    }

    public void setPesoMuestra(Float pesoMuestra) {
        this.pesoMuestra = pesoMuestra;
    }

    public Float getLectura1() {
        return lectura1;
    }

    public void setLectura1(Float lectura1) {
        this.lectura1 = lectura1;
    }

    public Float getLectura2() {
        return lectura2;
    }

    public void setLectura2(Float lectura2) {
        this.lectura2 = lectura2;
    }

    public Float getLectura3() {
        return lectura3;
    }

    public void setLectura3(Float lectura3) {
        this.lectura3 = lectura3;
    }

    public Float getLectura4() {
        return lectura4;
    }

    public void setLectura4(Float lectura4) {
        this.lectura4 = lectura4;
    }

    public Float getPromedio() {
        return promedio;
    }

    public void setPromedio(Float promedio) {
        this.promedio = promedio;
    }

    public String getClaveColecta() {
        return claveColecta;
    }

    public void setClaveColecta(String claveColecta) {
        this.claveColecta = claveColecta;
    }

}
