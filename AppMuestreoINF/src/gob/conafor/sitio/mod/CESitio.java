package gob.conafor.sitio.mod;

public class CESitio {

    //Módulo A, Sección 4.1 ubicacion del sitio de muestreo
    private int upmID;
    private int sitioID;
    private int sitio;
    private int senialGPS;
    private Integer gradosLatitud;
    private Integer minutosLatitud;
    private Float segundosLatitud;
    private Integer gradosLongitud;
    private Integer minutosLongitud;
    private Float segundosLongitud;
    private int errorPrecision;
    private String datum;
    private Integer evidenciaMuestreo;
    private Integer azimut;
    private Float distancia;
    //Módulo A, Sección 4.2 Características de accesibilidad de los sitios.
    private int sitioAccesible;
    private Integer tipoInaccesibilidadID;
    private String explicacionInaccesibilidad;
    //Las siguientes variables se captura una sola vez por sitio, independientemente de los módulos a capturar.
    /*
     Módulo A, Sección 4.6 Arbolado
     Módulo A-D, Sección 6.5 Arbolado
     Módulo G, Sección 10.3.3 Arbolado
     Módulo H, sección 11.2 Vegetacion mayor
     */
    private int hipsometroBrujula;
    private int cintaClinometroBrujula;
    private Integer cuadrante1;
    private Integer cuadrante2;
    private Integer cuadrante3;
    private Integer cuadrante4;
    private Float distancia1;
    private Float distancia2;
    private Float distancia3;
    private Float distancia4;
    private int repobladoFuera;
    private Integer porcentajeRepoblado;
    private int sotobosqueFuera;
    private Integer porcentajeSotobosque;
    //Módulo A, Sección 4.8 Clave de vegetación.
    private int coberturaForestal;
    private int condicion;
    private Integer faseSucecionalID;
    private Integer claveSerieVID;
    private int arbolFuera;
    private int ecotono;
    private String condicionPresenteCampo;
    private String condicionEcotono;
    private String observaciones;
    private Integer secuencia;
    private Integer sitio3Accesible;
    private String modulo;

    public int getUpmID() {
        return upmID;
    }

    public void setUpmID(int upmID) {
        this.upmID = upmID;
    }

    public int getSitioID() {
        return sitioID;
    }

    public void setSitioID(int sitioID) {
        this.sitioID = sitioID;
    }

    public int getSitio() {
        return sitio;
    }

    public void setSitio(int sitio) {
        this.sitio = sitio;
    }

    public int getSenialGPS() {
        return senialGPS;
    }

    public void setSenialGPS(int senialGPS) {
        this.senialGPS = senialGPS;
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

    public int getErrorPrecision() {
        return errorPrecision;
    }

    public void setErrorPrecision(int errorPrecision) {
        this.errorPrecision = errorPrecision;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public Integer getEvidenciaMuestreo() {
        return evidenciaMuestreo;
    }

    public void setEvidenciaMuestreo(Integer evidenciaMuestreo) {
        this.evidenciaMuestreo = evidenciaMuestreo;
    }

    public Integer getAzimut() {
        return azimut;
    }

    public void setAzimut(Integer azimut) {
        this.azimut = azimut;
    }

    public Float getDistancia() {
        return distancia;
    }

    public void setDistancia(Float distancia) {
        this.distancia = distancia;
    }

    public int getSitioAccesible() {
        return sitioAccesible;
    }

    public void setSitioAccesible(int sitioAccesible) {
        this.sitioAccesible = sitioAccesible;
    }

    public Integer getTipoInaccesibilidadID() {
        return tipoInaccesibilidadID;
    }

    public void setTipoInaccesibilidadID(Integer tipoInaccesibilidadID) {
        this.tipoInaccesibilidadID = tipoInaccesibilidadID;
    }

    public String getExplicacionInaccesibilidad() {
        return explicacionInaccesibilidad;
    }

    public void setExplicacionInaccesibilidad(String explicacionInaccesibilidad) {
        this.explicacionInaccesibilidad = explicacionInaccesibilidad;
    }

    public int getHipsometroBrujula() {
        return hipsometroBrujula;
    }

    public void setHipsometroBrujula(int hipsometroBrujula) {
        this.hipsometroBrujula = hipsometroBrujula;
    }

    public int getCintaClinometroBrujula() {
        return cintaClinometroBrujula;
    }

    public void setCintaClinometroBrujula(int cintaClinometroBrujula) {
        this.cintaClinometroBrujula = cintaClinometroBrujula;
    }

    public Integer getCuadrante1() {
        return cuadrante1;
    }

    public void setCuadrante1(Integer cuadrante1) {
        this.cuadrante1 = cuadrante1;
    }

    public Integer getCuadrante2() {
        return cuadrante2;
    }

    public void setCuadrante2(Integer cuadrante2) {
        this.cuadrante2 = cuadrante2;
    }

    public Integer getCuadrante3() {
        return cuadrante3;
    }

    public void setCuadrante3(Integer cuadrante3) {
        this.cuadrante3 = cuadrante3;
    }

    public Integer getCuadrante4() {
        return cuadrante4;
    }

    public void setCuadrante4(Integer cuadrante4) {
        this.cuadrante4 = cuadrante4;
    }

    public Float getDistancia1() {
        return distancia1;
    }

    public void setDistancia1(Float distancia1) {
        this.distancia1 = distancia1;
    }

    public Float getDistancia2() {
        return distancia2;
    }

    public void setDistancia2(Float distancia2) {
        this.distancia2 = distancia2;
    }

    public Float getDistancia3() {
        return distancia3;
    }

    public void setDistancia3(Float distancia3) {
        this.distancia3 = distancia3;
    }

    public Float getDistancia4() {
        return distancia4;
    }

    public void setDistancia4(Float distancia4) {
        this.distancia4 = distancia4;
    }

    public int getRepobladoFuera() {
        return repobladoFuera;
    }

    public void setRepobladoFuera(int repobladoFuera) {
        this.repobladoFuera = repobladoFuera;
    }

    public Integer getPorcentajeRepoblado() {
        return porcentajeRepoblado;
    }

    public void setPorcentajeRepoblado(Integer porcentajeRepoblado) {
        this.porcentajeRepoblado = porcentajeRepoblado;
    }

    public int getSotobosqueFuera() {
        return sotobosqueFuera;
    }

    public void setSotobosqueFuera(int sotobosqueFuera) {
        this.sotobosqueFuera = sotobosqueFuera;
    }

    public Integer getPorcentajeSotobosque() {
        return porcentajeSotobosque;
    }

    public void setPorcentajeSotobosque(Integer porcentajeSotobosque) {
        this.porcentajeSotobosque = porcentajeSotobosque;
    }

    public int getCoberturaForestal() {
        return coberturaForestal;
    }

    public void setCoberturaForestal(int coberturaForestal) {
        this.coberturaForestal = coberturaForestal;
    }

    public int getCondicion() {
        return condicion;
    }

    public void setCondicion(int condicion) {
        this.condicion = condicion;
    }

    public Integer getFaseSucecionalID() {
        return faseSucecionalID;
    }

    public void setFaseSucecionalID(Integer faseSucecionalID) {
        this.faseSucecionalID = faseSucecionalID;
    }

    public Integer getClaveSerieVID() {
        return claveSerieVID;
    }

    public void setClaveSerieVID(Integer claveSerieVID) {
        this.claveSerieVID = claveSerieVID;
    }

    public int getArbolFuera() {
        return arbolFuera;
    }

    public void setArbolFuera(int arbolFuera) {
        this.arbolFuera = arbolFuera;
    }

    public int getEcotono() {
        return ecotono;
    }

    public void setEcotono(int ecotono) {
        this.ecotono = ecotono;
    }

    public String getCondicionPresenteCampo() {
        return condicionPresenteCampo;
    }

    public void setCondicionPresenteCampo(String condicionPresenteCampo) {
        this.condicionPresenteCampo = condicionPresenteCampo;
    }

    public String getCondicionEcotono() {
        return condicionEcotono;
    }

    public void setCondicionEcotono(String condicionEcotono) {
        this.condicionEcotono = condicionEcotono;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Integer getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(Integer secuencia) {
        this.secuencia = secuencia;
    }

    public String getModulo() {
        return modulo;
    }

    public void setModulo(String modulo) {
        this.modulo = modulo;
    }

    public Integer getSitio3Accesible() {
        return sitio3Accesible;
    }

    public void setSitio3Accesible(Integer sitio3Accesible) {
        this.sitio3Accesible = sitio3Accesible;
    }
    
}
