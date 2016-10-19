package gob.conafor.taxonomia.mod;

public class CatEDensidadFollaje {

    private int densidadFollajeID;
    private String descripcion;

    public int getDensidadFollajeID() {
        return densidadFollajeID;
    }

    public void setDensidadFollajeID(int densidadFollajeID) {
        this.densidadFollajeID = densidadFollajeID;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.densidadFollajeID;
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
        final CatEDensidadFollaje other = (CatEDensidadFollaje) obj;
        if (this.densidadFollajeID != other.densidadFollajeID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return densidadFollajeID + "";
    }
    
}
