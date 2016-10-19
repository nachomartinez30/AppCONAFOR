package gob.conafor.taxonomia.mod;

public class CatESeveridadZA {

    private int severidadID;
    private String descripcion;

    public int getSeveridadID() {
        return severidadID;
    }

    public void setSeveridadID(int severidadID) {
        this.severidadID = severidadID;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.severidadID;
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
        final CatESeveridadZA other = (CatESeveridadZA) obj;
        if (this.severidadID != other.severidadID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return severidadID + "";
    }

}
