package gob.conafor.upm.mod;

public class CatETipoExposicion {
    
    private int exposicionID;
    private String clave;
    private String descripcion;

    public int getExposicionID() {
        return exposicionID;
    }

    public void setExposicionID(int exposicionID) {
        this.exposicionID = exposicionID;
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
    public String toString(){
        return this.exposicionID + "-" + this.descripcion;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o instanceof CatETipoExposicion) {
            CatETipoExposicion g = (CatETipoExposicion) o;
            return (this.exposicionID == g.exposicionID);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + this.exposicionID;
        return hash;
    }
}
