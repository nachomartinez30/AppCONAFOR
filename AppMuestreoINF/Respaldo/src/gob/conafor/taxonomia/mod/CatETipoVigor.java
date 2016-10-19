package gob.conafor.taxonomia.mod;

public class CatETipoVigor {

    //CAT_NivelVigorArbolado
    private int vigorID;
    private String descripcion;

    public int getVigorID() {
        return vigorID;
    }

    public void setVigorID(int vigorID) {
        this.vigorID = vigorID;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return vigorID + "-" + descripcion;
    }
     @Override
    public boolean equals(Object o) {
        if (o instanceof CatETipoVigor) {
            CatETipoVigor g = (CatETipoVigor) o;
            return (this.vigorID == g.getVigorID());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.vigorID;
        return hash;
    }
}
