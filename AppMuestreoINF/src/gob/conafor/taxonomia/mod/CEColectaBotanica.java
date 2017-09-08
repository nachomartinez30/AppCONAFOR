package gob.conafor.taxonomia.mod;

public class CEColectaBotanica {

    //Modulo A, Sección 4.4.8 Clave de colecta botánica
    private Integer UPMID;

    public Integer getUPMID() {
        return UPMID;
    }

    public void setUPMID(Integer UPMID) {
        this.UPMID = UPMID;
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

    public Integer getInfraespecie() {
        return infraespecie;
    }

    public void setInfraespecie(Integer infraespecie) {
        this.infraespecie = infraespecie;
    }

    public String getNombreComun() {
        return nombreComun;
    }

    public void setNombreComun(String nombreComun) {
        this.nombreComun = nombreComun;
    }

    public Integer getSitio() {
        return sitio;
    }

    public void setSitio(Integer sitio) {
        this.sitio = sitio;
    }

    public Integer getSeccionID() {
        return seccionID;
    }

    public void setSeccionID(Integer seccionID) {
        this.seccionID = seccionID;
    }

    public Integer getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(Integer consecutivo) {
        this.consecutivo = consecutivo;
    }

    public Boolean getContraFuertes() {
        return contraFuertes;
    }

    public void setContraFuertes(Boolean contraFuertes) {
        this.contraFuertes = contraFuertes;
    }

    public Boolean getExudado() {
        return exudado;
    }

    public void setExudado(Boolean exudado) {
        this.exudado = exudado;
    }

    public Boolean getColor() {
        return color;
    }

    public void setColor(Boolean color) {
        this.color = color;
    }

    public Boolean getCambioColor() {
        return cambioColor;
    }

    public void setCambioColor(Boolean cambioColor) {
        this.cambioColor = cambioColor;
    }

    public Boolean getAceitesVolatiles() {
        return aceitesVolatiles;
    }

    public void setAceitesVolatiles(Boolean aceitesVolatiles) {
        this.aceitesVolatiles = aceitesVolatiles;
    }

    public Boolean getColorFlor() {
        return colorFlor;
    }

    public void setColorFlor(Boolean colorFlor) {
        this.colorFlor = colorFlor;
    }

    public Boolean getHojasTejidoVivo() {
        return hojasTejidoVivo;
    }

    public void setHojasTejidoVivo(Boolean hojasTejidoVivo) {
        this.hojasTejidoVivo = hojasTejidoVivo;
    }

    public Boolean getFotoFlor() {
        return fotoFlor;
    }

    public void setFotoFlor(Boolean fotoFlor) {
        this.fotoFlor = fotoFlor;
    }

    public Boolean getFotoFruto() {
        return fotoFruto;
    }

    public void setFotoFruto(Boolean fotoFruto) {
        this.fotoFruto = fotoFruto;
    }

    public Boolean getFotoHojasArriba() {
        return fotoHojasArriba;
    }

    public void setFotoHojasArriba(Boolean fotoHojasArriba) {
        this.fotoHojasArriba = fotoHojasArriba;
    }

    public Boolean getFotoHojasAbajo() {
        return fotoHojasAbajo;
    }

    public void setFotoHojasAbajo(Boolean fotoHojasAbajo) {
        this.fotoHojasAbajo = fotoHojasAbajo;
    }

    public Boolean getFotoArbolCompleto() {
        return fotoArbolCompleto;
    }

    public void setFotoArbolCompleto(Boolean fotoArbolCompleto) {
        this.fotoArbolCompleto = fotoArbolCompleto;
    }

    public Boolean getFotoCorteza() {
        return fotoCorteza;
    }

    public void setFotoCorteza(Boolean fotoCorteza) {
        this.fotoCorteza = fotoCorteza;
    }

    public Boolean getVirutaIncluida() {
        return virutaIncluida;
    }

    public void setVirutaIncluida(Boolean virutaIncluida) {
        this.virutaIncluida = virutaIncluida;
    }

    public Boolean getCortezaIncluida() {
        return cortezaIncluida;
    }

    public void setCortezaIncluida(Boolean cortezaIncluida) {
        this.cortezaIncluida = cortezaIncluida;
    }

    public Boolean getMaderaIncluida() {
        return maderaIncluida;
    }

    public void setMaderaIncluida(Boolean maderaIncluida) {
        this.maderaIncluida = maderaIncluida;
    }

    public String getClaveColecta() {
        return claveColecta;
    }

    public void setClaveColecta(String claveColecta) {
        this.claveColecta = claveColecta;
    }

    public String getIndicarExudado() {
        return indicarExudado;
    }

    public void setIndicarExudado(String indicarExudado) {
        this.indicarExudado = indicarExudado;
    }

    public String getIndicarColor() {
        return indicarColor;
    }

    public void setIndicarColor(String indicarColor) {
        this.indicarColor = indicarColor;
    }

    public String getIndicarColorFlor() {
        return indicarColorFlor;
    }

    public void setIndicarColorFlor(String indicarColorFlor) {
        this.indicarColorFlor = indicarColorFlor;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    private Integer familiaID;
    private Integer generoID;
    private Integer especieID;
    private Integer infraespecie;
    private String nombreComun;
    private Integer sitio;
    private Integer seccionID;
    private Integer consecutivo;
    private Boolean contraFuertes;
    private Boolean exudado;
    private Boolean color;
    private Boolean cambioColor;
    private Boolean aceitesVolatiles;
    private Boolean colorFlor;
    private Boolean hojasTejidoVivo;
    private Boolean fotoFlor;
    private Boolean fotoFruto;
    private Boolean fotoHojasArriba;
    private Boolean fotoHojasAbajo;
    private Boolean fotoArbolCompleto;
    private Boolean fotoCorteza;
    private Boolean virutaIncluida;
    private Boolean cortezaIncluida;
    private Boolean maderaIncluida;
    private String claveColecta;
    private String indicarExudado;
    private String indicarColor;
    private String indicarColorFlor;
    private String observaciones;

}
