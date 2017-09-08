package gob.conafor.taxonomia.mod;

public class CEArbolado {
    //Módulo A, Sección 4.6 Arbolado
    private int arboladoID;
    private int sitioID;
    private int sitio;
    private Integer consecutivo;
    private Integer numeroIndividuo;
    private Integer numeroRama;
    private Integer azimut;
    private Float distancia;
    private Integer familiaID;
    private Integer generoID;
    private Integer especieID;
    private Integer infraespecieID;
    private String nombreComun;
    private int esColecta;
    private boolean esSubmuestra;
    private int formaVidaID;
    private Integer formaFusteID;
    private Integer condicionID;
    private Integer muertoPieID;
    private Integer gradoPutrefaccionID;
    private Integer tipoToconID;
    private Float diametroNormal;
    private Float diametroBasal; // Módulo G, Sección  10.3.3
    private Float alturaTotal;
    private Integer anguloInclinacion;
    private Float alturaFusteLimpio;
    private Float alturaComercial;
    private Float diametroCopaNS;
    private Float diametroCopaEO;
    //Módulo D, Sección 6.5 Arbolado, Indicadores de copa.
    private Integer proporcionCopaVivaID;
    private Integer exposisicionCopaID;
    private Integer posicionCopaID;
    private Integer densidadCopaID;
    private Integer muerteRegresivaID;
    private Integer transparenciaFollajeID;
    // Módulo G Sección 10.3.3 y A
    private Integer vigorID;
    private Integer nivelVigorID;
    private String claveColecta;

    public int getArboladoID() {
        return arboladoID;
    }

    public void setArboladoID(int arboladoID) {
        this.arboladoID = arboladoID;
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

    public int getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(int consecutivo) {
        this.consecutivo = consecutivo;
    }

    public int getNumeroIndividuo() {
        return numeroIndividuo;
    }

    public void setNumeroIndividuo(int numeroIndividuo) {
        this.numeroIndividuo = numeroIndividuo;
    }

    public int getNumeroRama() {
        return numeroRama;
    }

    public void setNumeroRama(int numeroRama) {
        this.numeroRama = numeroRama;
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

    public Integer getFamiliaID() {
        return familiaID;
    }

    public void setFamiliaID(Integer familiaID) {
        this.familiaID = familiaID;
    }

    public Integer getGeneroID() {
        return generoID;
    }

    public void setGeneroID(Integer generoID) {
        this.generoID = generoID;
    }

    public Integer getEspecieID() {
        return especieID;
    }

    public void setEspecieID(Integer especieID) {
        this.especieID = especieID;
    }

    public Integer getInfraespecieID() {
        return infraespecieID;
    }

    public void setInfraespecieID(Integer infraespecieID) {
        this.infraespecieID = infraespecieID;
    }

    public String getNombreComun() {
        return nombreComun;
    }

    public void setNombreComun(String nombreComun) {
        this.nombreComun = nombreComun;
    }

    public int getEsColecta() {
        return esColecta;
    }

    public void setEsColecta(int esColecta) {
        this.esColecta = esColecta;
    }

    public Boolean getEsSubmuestra() {
        return esSubmuestra;
    }

    public void setEsSubmuestra(Boolean esSubmuestra) {
        this.esSubmuestra = esSubmuestra;
    }

    public Integer getFormaVidaID() {
        return formaVidaID;
    }

    public void setFormaVidaID(Integer formaVidaID) {
        this.formaVidaID = formaVidaID;
    }

    public Integer getFormaFusteID() {
        return formaFusteID;
    }

    public void setFormaFusteID(Integer formaFusteID) {
        this.formaFusteID = formaFusteID;
    }

    public Integer getCondicionID() {
        return condicionID;
    }

    public void setCondicionID(Integer condicionID) {
        this.condicionID = condicionID;
    }

    public Integer getMuertoPieID() {
        return muertoPieID;
    }

    public void setMuertoPieID(Integer muertoPieID) {
        this.muertoPieID = muertoPieID;
    }

    public Integer getGradoPutrefaccionID() {
        return gradoPutrefaccionID;
    }

    public void setGradoPutrefaccionID(Integer gradoPutrefaccionID) {
        this.gradoPutrefaccionID = gradoPutrefaccionID;
    }

    public Integer getTipoToconID() {
        return tipoToconID;
    }

    public void setTipoToconID(Integer tipoToconID) {
        this.tipoToconID = tipoToconID;
    }
    
    public Float getDiametroNormal() {
        return diametroNormal;
    }

    public void setDiametroNormal(Float diametroNormal) {
            this.diametroNormal = diametroNormal;
    }

    public Float getDiametroBasal() {
        return diametroBasal;
    }

    public void setDiametroBasal(Float diametroBasal) {
        this.diametroBasal = diametroBasal;
    }

    public Float getAlturaTotal() {
        return alturaTotal;
    }

    public void setAlturaTotal(Float alturaTotal) {
        this.alturaTotal = alturaTotal;
    }

    public Integer getAnguloInclinacion() {
        return anguloInclinacion;
    }

    public void setAnguloInclinacion(Integer anguloInclinacion) {
        this.anguloInclinacion = anguloInclinacion;
    }
    
    public Float getAlturaFusteLimpio() {
        return alturaFusteLimpio;
    }

    public void setAlturaFusteLimpio(Float alturaFusteLimpio) {
        this.alturaFusteLimpio = alturaFusteLimpio;
    }

    public Float getAlturaComercial() {
        return alturaComercial;
    }

    public void setAlturaComercial(Float alturaComercial) {
        this.alturaComercial = alturaComercial;
    }

    public Float getDiametroCopaNS() {
        return diametroCopaNS;
    }

    public void setDiametroCopaNS(Float diametroCopaNS) {
        this.diametroCopaNS = diametroCopaNS;
    }

    public Float getDiametroCopaEO() {
        return diametroCopaEO;
    }

    public void setDiametroCopaEO(Float diametroCopaEO) {
        this.diametroCopaEO = diametroCopaEO;
    }

    public Integer getProporcionCopaVivaID() {
        return proporcionCopaVivaID;
    }

    public void setProporcionCopaVivaID(Integer proporcionCopaVivaID) {
        this.proporcionCopaVivaID = proporcionCopaVivaID;
    }

    public Integer getExposisicionCopaID() {
        return exposisicionCopaID;
    }

    public void setExposisicionCopaID(Integer exposisicionCopaID) {
        this.exposisicionCopaID = exposisicionCopaID;
    }

    public Integer getPosicionCopaID() {
        return posicionCopaID;
    }

    public void setPosicionCopaID(Integer posicionCopaID) {
        this.posicionCopaID = posicionCopaID;
    }

    public Integer getDensidadCopaID() {
        return densidadCopaID;
    }

    public void setDensidadCopaID(Integer densidadCopaID) {
        this.densidadCopaID = densidadCopaID;
    }

    public Integer getMuerteRegresivaID() {
        return muerteRegresivaID;
    }

    public void setMuerteRegresivaID(Integer muerteRegresivaID) {
        this.muerteRegresivaID = muerteRegresivaID;
    }

    public Integer getTransparenciaFollajeID() {
        return transparenciaFollajeID;
    }

    public void setTransparenciaFollajeID(Integer transparenciaFollajeID) {
        this.transparenciaFollajeID = transparenciaFollajeID;
    }

    public Integer getVigorID() {
        return vigorID;
    }

    public void setVigorID(Integer vigorID) {
        this.vigorID = vigorID;
    }

    public String getClaveColecta() {
        return claveColecta;
    }

    public void setClaveColecta(String claveColecta) {
        this.claveColecta = claveColecta;
    }

    public Integer getNivelVigorID() {
        return nivelVigorID;
    }

    public void setNivelVigorID(Integer nivelVigorID) {
        this.nivelVigorID = nivelVigorID;
    }
    
}