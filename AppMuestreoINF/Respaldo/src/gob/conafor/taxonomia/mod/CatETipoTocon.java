package gob.conafor.taxonomia.mod;

import java.util.Objects;

public class CatETipoTocon {
    
    private Integer tipoToconID;
    private String clave;
    private String descripcion;

    public Integer getTipoToconID() {
        return tipoToconID;
    }

    public void setTipoToconID(Integer tipoToconID) {
        this.tipoToconID = tipoToconID;
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
    public boolean equals(Object o) {
        if (o instanceof CatETipoTocon) {
            CatETipoTocon g = (CatETipoTocon) o;
            return (this.tipoToconID == g.tipoToconID);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 73 * hash + Objects.hashCode(this.tipoToconID);
        return hash;
    }
    
    @Override
    public String toString(){
        return this.clave;
    }
}
