package gob.conafor.taxonomia.mod;

public class CatECondicionMuertoPie {
    int muertoPieID;
    String clave;
    String descripcion;

    public int getMuertoPieID() {
        return muertoPieID;
    }

    public void setMuertoPieID(int muertoPieID) {
        this.muertoPieID = muertoPieID;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    @Override
    public String toString(){
        return this.clave;
    }
    @Override
    public boolean equals(Object o) {
        if (o instanceof CatECondicionMuertoPie) {
            CatECondicionMuertoPie g = (CatECondicionMuertoPie) o;
            return (this.muertoPieID == g.muertoPieID);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.muertoPieID;
        return hash;
    }
}
