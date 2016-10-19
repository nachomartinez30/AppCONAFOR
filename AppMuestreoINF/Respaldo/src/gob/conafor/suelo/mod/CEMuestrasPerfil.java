package gob.conafor.suelo.mod;

public class CEMuestrasPerfil {
    
    //Sección 8.4.1 Muestras del perfil
    private Integer muestrasPerfilID;
    private Integer sitioID;
    private Integer gradosLatitud;
    private Integer minutosLatitud;
    private Float segundosLatitud;
    private Integer gradosLongitud;
    private Integer minutosLongitud;
    private Float segundosLongitud;
    private Float elevacion;
    private Float diametroInterno;
    private Float diametroExterno;
    private Float altura;
    private String observaciones;

    public Integer getMuestrasPerfilID() {
        return muestrasPerfilID;
    }

    public void setMuestrasPerfilID(Integer muestrasPerfilID) {
        this.muestrasPerfilID = muestrasPerfilID;
    }

    public Integer getSitioID() {
        return sitioID;
    }

    public void setSitioID(Integer sitioID) {
        this.sitioID = sitioID;
    }

    public Integer getGradosLatitud() {
        return gradosLatitud;
    }

    public void setGradosLatitud(Integer gradosLatitud) {
        this.gradosLatitud = gradosLatitud;
    }

    public Integer getMinutosLatitud() {
        return minutosLatitud;
    }

    public void setMinutosLatitud(Integer minutosLatitud) {
        this.minutosLatitud = minutosLatitud;
    }

    public Float getSegundosLatitud() {
        return segundosLatitud;
    }

    public void setSegundosLatitud(Float segundosLatitud) {
        this.segundosLatitud = segundosLatitud;
    }

    public Integer getGradosLongitud() {
        return gradosLongitud;
    }

    public void setGradosLongitud(Integer gradosLongitud) {
        this.gradosLongitud = gradosLongitud;
    }

    public Integer getMinutosLongitud() {
        return minutosLongitud;
    }

    public void setMinutosLongitud(Integer minutosLongitud) {
        this.minutosLongitud = minutosLongitud;
    }

    public Float getSegundosLongitud() {
        return segundosLongitud;
    }

    public void setSegundosLongitud(Float segundosLongitud) {
        this.segundosLongitud = segundosLongitud;
    }

    public Float getElevacion() {
        return elevacion;
    }

    public void setElevacion(Float elevacion) {
        this.elevacion = elevacion;
    }

    public Float getDiametroInterno() {
        return diametroInterno;
    }

    public void setDiametroInterno(Float diametroInterno) {
        this.diametroInterno = diametroInterno;
    }

    public Float getDiametroExterno() {
        return diametroExterno;
    }

    public void setDiametroExterno(Float diametroExterno) {
        this.diametroExterno = diametroExterno;
    }

    public Float getAltura() {
        return altura;
    }

    public void setAltura(Float altura) {
        this.altura = altura;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    
}
