package gob.conafor.taxonomia.mod;

public class CatENivelVigor {

    private int nivelVigorID;
    private String clave;
    private String descripcion;

    public int getNivelVigorID() {
        return nivelVigorID;
    }

    public void setNivelVigorID(int nivelVigorID) {
        this.nivelVigorID = nivelVigorID;
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
        return clave;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.nivelVigorID;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CatENivelVigor other = (CatENivelVigor) obj;
        if (this.nivelVigorID != other.nivelVigorID) {
            return false;
        }
        return true;
    }

   
}
