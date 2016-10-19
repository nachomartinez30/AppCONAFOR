package gob.conafor.upm.mod;

public class CEContacto {
    
    private int upmID;
    private Integer tipoContacto;
    private String nombre;
    private String direccion;
    private Integer tipoTelefono;
    private String numeroTelefono;
    private Integer tieneRadio;
    private String canal;
    private String frecuencia;
    private Integer tieneCorreoElectronico;
    private String correoElectronico;
    private String observaciones;

    public int getUpmID() {
        return upmID;
    }

    public void setUpmID(int upmID) {
        this.upmID = upmID;
    }

    public Integer getTipoContacto() {
        return tipoContacto;
    }

    public void setTipoContacto(Integer tipoContacto) {
        this.tipoContacto = tipoContacto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Integer getTipoTelefono() {
        return tipoTelefono;
    }

    public void setTipoTelefono(Integer tipoTelefono) {
        this.tipoTelefono = tipoTelefono;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    public Integer getTieneRadio() {
        return tieneRadio;
    }

    public void setTieneRadio(Integer tieneRadio) {
        this.tieneRadio = tieneRadio;
    }

    public String getCanal() {
        return canal;
    }

    public void setCanal(String canal) {
        this.canal = canal;
    }

    public String getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(String frecuencia) {
        this.frecuencia = frecuencia;
    }

    public Integer getTieneCorreoElectronico() {
        return tieneCorreoElectronico;
    }

    public void setTieneCorreoElectronico(Integer tieneCorreoElectronico) {
        this.tieneCorreoElectronico = tieneCorreoElectronico;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

}
