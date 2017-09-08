package gob.conafor.upm.mod;



public class CEUPM {

    private Integer upmID;
    private String fechaInicio;
    private String fechaFin;
    private Integer tipoUpmID; //Catálogo de tipo de conglomerado
    private String predio;
    private String paraje;
    private Integer tenenciaID;
    private Boolean accesible;
    //Módulo A, Sección 4.9 Características del conglomerado
    private Float altitud;
    private Integer pendienteRepresentativa;
    private Integer fisiografiaID;
    private Integer exposicionID;
    //Módulo 0, Sección 3.4 Características de Inaccesibilidad al conglomerado
    private int gradosLatitud;
    private int minutosLatitud;
    private float segundosLatitud;
    private int gradosLongitud;
    private int minutosLongitud;
    private float segundosLongitud;
    private String datum;
    private int errorPresicion;
    private int azimut;
    private float distancia;
    private int tipoInaccesibilidadID;
    private String otroTipoInaccesibilidad;
    private String explicacionInaccesibilidad;
    private Integer informacionContacto;

    public Integer getUpmID() {
        return upmID;
    }

    public void setUpmID(Integer upmID) {
        this.upmID = upmID;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Integer getTipoUpmID() {
        return tipoUpmID;
    }

    public void setTipoUpmID(Integer tipoUpmID) {
        this.tipoUpmID = tipoUpmID;
    }

    public String getPredio() {
        return predio;
    }

    public void setPredio(String predio) {
        this.predio = predio;
    }

    public String getParaje() {
        return paraje;
    }

    public void setParaje(String paraje) {
        this.paraje = paraje;
    }

    public Integer getTenenciaID() {
        return tenenciaID;
    }

    public void setTenenciaID(Integer tenenciaID) {
        this.tenenciaID = tenenciaID;
    }

    public Boolean getAccesible() {
        return accesible;
    }

    public void setAccesible(Boolean accesible) {
        this.accesible = accesible;
    }

    public Float getAltitud() {
        return altitud;
    }

    public void setAltitud(Float altitud) {
        this.altitud = altitud;
    }

    public Integer getPendienteRepresentativa() {
        return pendienteRepresentativa;
    }

    public void setPendienteRepresentativa(Integer pendienteRepresentativa) {
        this.pendienteRepresentativa = pendienteRepresentativa;
    }

    public Integer getFisiografiaID() {
        return fisiografiaID;
    }

    public void setFisiografiaID(Integer fisiografiaID) {
        this.fisiografiaID = fisiografiaID;
    }

    public Integer getExposicionID() {
        return exposicionID;
    }

    public void setExposicionID(Integer exposicionID) {
        this.exposicionID = exposicionID;
    }

    public int getGradosLatitud() {
        return gradosLatitud;
    }

    public void setGradosLatitud(int gradosLatitud) {
        this.gradosLatitud = gradosLatitud;
    }

    public int getMinutosLatitud() {
        return minutosLatitud;
    }

    public void setMinutosLatitud(int minutosLatitud) {
        this.minutosLatitud = minutosLatitud;
    }

    public float getSegundosLatitud() {
        return segundosLatitud;
    }

    public void setSegundosLatitud(float segundosLatitud) {
        this.segundosLatitud = segundosLatitud;
    }

    public int getGradosLongitud() {
        return gradosLongitud;
    }

    public void setGradosLongitud(int gradosLongitud) {
        this.gradosLongitud = gradosLongitud;
    }

    public int getMinutosLongitud() {
        return minutosLongitud;
    }

    public void setMinutosLongitud(int minutosLongitud) {
        this.minutosLongitud = minutosLongitud;
    }

    public float getSegundosLongitud() {
        return segundosLongitud;
    }

    public void setSegundosLongitud(float segundosLongitud) {
        this.segundosLongitud = segundosLongitud;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public int getErrorPresicion() {
        return errorPresicion;
    }

    public void setErrorPresicion(int errorPresicion) {
        this.errorPresicion = errorPresicion;
    }

    public int getAzimut() {
        return azimut;
    }

    public void setAzimut(int azimut) {
        this.azimut = azimut;
    }

    public float getDistancia() {
        return distancia;
    }

    public void setDistancia(float distancia) {
        this.distancia = distancia;
    }

    public int getTipoInaccesibilidadID() {
        return tipoInaccesibilidadID;
    }

    public void setTipoInaccesibilidadID(int tipoInaccesibilidadID) {
        this.tipoInaccesibilidadID = tipoInaccesibilidadID;
    }

    public String getOtroTipoInaccesibilidad() {
        return otroTipoInaccesibilidad;
    }

    public void setOtroTipoInaccesibilidad(String otroTipoInaccesibilidad) {
        this.otroTipoInaccesibilidad = otroTipoInaccesibilidad;
    }

    public String getExplicacionInaccesibilidad() {
        return explicacionInaccesibilidad;
    }

    public void setExplicacionInaccesibilidad(String explicacionInaccesibilidad) {
        this.explicacionInaccesibilidad = explicacionInaccesibilidad;
    }

    public Integer getInformacionContacto() {
        return informacionContacto;
    }

    public void setInformacionContacto(Integer informacionContacto) {
        this.informacionContacto = informacionContacto;
    }

   

}
