package gob.conafor.suelo.mod;

import java.util.Objects;

public class CatEProfundidadMuestras {

    private Integer profundidadMuestraID;
    private String descripcion;

    public Integer getProfundidadMuestraID() {
        return profundidadMuestraID;
    }

    public void setProfundidadMuestraID(Integer profundidadMuestraID) {
        this.profundidadMuestraID = profundidadMuestraID;
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
        hash = 83 * hash + Objects.hashCode(this.profundidadMuestraID);
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
        final CatEProfundidadMuestras other = (CatEProfundidadMuestras) obj;
        if (!Objects.equals(this.profundidadMuestraID, other.profundidadMuestraID)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return profundidadMuestraID + "-" + descripcion;
    }

}
