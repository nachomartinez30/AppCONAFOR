package gob.conafor.taxonomia.mod;

public class CatEDensidadColonia {

    private int densidadColoniaID;
    private String descripcion;

    public int getDensidadColoniaID() {
        return densidadColoniaID;
    }

    public void setDensidadColoniaID(int densidadColoniaID) {
        this.densidadColoniaID = densidadColoniaID;
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
        hash = 29 * hash + this.densidadColoniaID;
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
        final CatEDensidadColonia other = (CatEDensidadColonia) obj;
        if (this.densidadColoniaID != other.densidadColoniaID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return densidadColoniaID + "";
    }
    
}
