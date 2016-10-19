package gob.conafor.upm.mod;

public class CatETipoTenencia {
    
    //CAT_TipoTenencia
    private int tipoTenenciaID;
    private String descripcion;

    public int getTipoTenenciaID() {
        return tipoTenenciaID;
    }

    public void setTipoTenenciaID(int tipoTenenciaID) {
        this.tipoTenenciaID = tipoTenenciaID;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String toString() {
        return this.tipoTenenciaID + "-" + this.descripcion;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + this.tipoTenenciaID;
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
        final CatETipoTenencia other = (CatETipoTenencia) obj;
        if (this.tipoTenenciaID != other.tipoTenenciaID) {
            return false;
        }
        return true;
    }

}
