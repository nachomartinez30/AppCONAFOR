package gob.conafor.taxonomia.mod;

public class CatETipoTroza {

    private int trozaID;
    private String clave;
    private String descripcion;

    public int getTrozaID() {
        return trozaID;
    }

    public void setTrozaID(int trozaID) {
        this.trozaID = trozaID;
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
    public String toString() {
        return this.clave;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof CatETipoTroza) {
            CatETipoTroza g = (CatETipoTroza) o;
            return (this.getTrozaID() == g.getTrozaID());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + this.trozaID;
        return hash;
    }
    
}
