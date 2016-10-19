package gob.conafor.taxonomia.mod;

public class CEDanio {

    private Integer danioID;
    private int arboladoID;
    private Integer agenteDanioID;
    private Integer numeroDanio;
    private String clave;
    private Integer porcentajeDanio;

     public Integer getDanioID() { 
        return danioID;
    }

    public void setDanioID(Integer danioID) {
        this.danioID = danioID;
    }
    
    public Integer getAgenteDanioID() {
        return agenteDanioID;
    }

    public int getArboladoID() {
        return arboladoID;
    }

    public void setArboladoID(int arboladoID) {
        this.arboladoID = arboladoID;
    }

    public void setAgenteDanioID(Integer agenteDanioID) {
        this.agenteDanioID = agenteDanioID;
    }

    public int getNumeroDanio() {
        return numeroDanio;
    }

    public void setNumeroDanio(Integer numeroDanio) {
        this.numeroDanio = numeroDanio;
    }
    
    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Integer getPorcentajeDanio() {
        return porcentajeDanio;
    }

    public void setPorcentajeDanio(Integer porcentajeDanio) {
        this.porcentajeDanio = porcentajeDanio;
    }
   
}
