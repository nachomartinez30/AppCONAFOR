package gob.conafor.suelo.mod;

public class CatETipoHojarasca {
    private int tipoHojarascaID;
    private String clave;
    private String descripcion;

    public int getTipoHojarascaID() {
        return tipoHojarascaID;
    }

    public void setTipoHojarascaID(int tipoHojarascaID) {
        this.tipoHojarascaID = tipoHojarascaID;
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
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CatETipoHojarasca other = (CatETipoHojarasca) obj;
        if (this.tipoHojarascaID != other.tipoHojarascaID) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return this.clave;
    }
}
