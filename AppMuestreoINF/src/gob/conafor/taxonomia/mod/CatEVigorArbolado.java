package gob.conafor.taxonomia.mod;

import java.util.Objects;

public class CatEVigorArbolado {
    
    private Integer vigorID;
    private String descripcion;

    public Integer getVigorID() {
        return vigorID;
    }

    public void setVigorID(Integer vigorID) {
        this.vigorID = vigorID;
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
        hash = 83 * hash + Objects.hashCode(this.vigorID);
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
        final CatEVigorArbolado other = (CatEVigorArbolado) obj;
        if (!Objects.equals(this.vigorID, other.vigorID)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return  vigorID.toString();
    }

}
