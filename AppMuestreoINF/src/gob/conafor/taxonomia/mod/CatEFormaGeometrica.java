package gob.conafor.taxonomia.mod;

public class CatEFormaGeometrica {

    private int formaGeometricaID;
    private String descripcion;

    public int getFormaGeometricaID() {
        return formaGeometricaID;
    }

    public void setFormaGeometricaID(int formaGeometricaID) {
        this.formaGeometricaID = formaGeometricaID;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.formaGeometricaID;
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
        final CatEFormaGeometrica other = (CatEFormaGeometrica) obj;
        if (this.formaGeometricaID != other.formaGeometricaID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return formaGeometricaID + "-" + descripcion;
    }
}
