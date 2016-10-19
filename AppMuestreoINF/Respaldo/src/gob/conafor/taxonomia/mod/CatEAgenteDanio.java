package gob.conafor.taxonomia.mod;

public class CatEAgenteDanio {

    // CAT_AgenteDanio
    private int agenteDanioID;
    private String clave;
    private String agente;
    private String descripcion;

    public int getAgenteDanioID() {
        return agenteDanioID;
    }

    public void setAgenteDanioID(int agenteDanioID) {
        this.agenteDanioID = agenteDanioID;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getAgente() {
        return agente;
    }

    public void setAgente(String agente) {
        this.agente = agente;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return clave;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o instanceof CatEAgenteDanio) {
            CatEAgenteDanio g = (CatEAgenteDanio) o;
            return (this.agenteDanioID == g.agenteDanioID);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + this.agenteDanioID;
        return hash;
    }
}
