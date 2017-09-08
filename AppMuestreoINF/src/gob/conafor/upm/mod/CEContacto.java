package gob.conafor.upm.mod;

public class CEContacto {
    
    private int upmID;
    private Boolean tipoContacto;
    private String nombre;
    private String direccion;
    private Boolean tipoTelefono;
    private String numeroTelefono;
    private Boolean tieneRadio;
    private String canal;
    private String frecuencia;
    private Boolean tieneCorreoElectronico;
    private String correoElectronico;
    private String observaciones;

    public int getUpmID() {
        return upmID;
    }

    public void setUpmID(int upmID) {
        this.upmID = upmID;
    }

    public Boolean getTipoContacto() {
        return tipoContacto;
    }

    public void setTipoContacto(Boolean tipoContacto) {
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

    public Boolean getTipoTelefono() {
        return tipoTelefono;
    }

    public void setTipoTelefono(Boolean tipoTelefono) {
        this.tipoTelefono = tipoTelefono;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    public Boolean getTieneRadio() {
        return tieneRadio;
    }

    public void setTieneRadio(Boolean tieneRadio) {
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

    public Boolean getTieneCorreoElectronico() {
        return tieneCorreoElectronico;
    }

    public void setTieneCorreoElectronico(Boolean tieneCorreoElectronico) {
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
