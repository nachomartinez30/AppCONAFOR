package gob.conafor.sitio.mod;

public class CatEFaseSucecional {
    
    private int faseSucecionalID;
    private String descripcion;
    private String clave;

    public int getFaseSucecionalID() {
        return faseSucecionalID;
    }

    public void setFaseSucecionalID(int faseSucecionalID) {
        this.faseSucecionalID = faseSucecionalID;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
    
    @Override
    public String toString(){
        return this.clave;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o instanceof CatEFaseSucecional) {
            CatEFaseSucecional g = (CatEFaseSucecional) o;
            return (this.faseSucecionalID == g.getFaseSucecionalID());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.faseSucecionalID;
        return hash;
    }

}
