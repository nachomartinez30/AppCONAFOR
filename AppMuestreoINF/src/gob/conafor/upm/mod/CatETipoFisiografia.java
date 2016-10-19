package gob.conafor.upm.mod;

public class CatETipoFisiografia {
    
    private int fisiografiaID;
    private String tipoFisiografia;
    private String descripcion;

    public int getFisiografiaID() {
        return fisiografiaID;
    }

    public void setFisiografiaID(int fisiografiaID) {
        this.fisiografiaID = fisiografiaID;
    }

    public String getTipoFisiografia() {
        return tipoFisiografia;
    }

    public void setTipoFisiografia(String tipoFisiografia) {
        this.tipoFisiografia = tipoFisiografia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    @Override
    public String toString(){
        return this.fisiografiaID + "-" +this.tipoFisiografia;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o instanceof CatETipoFisiografia) {
            CatETipoFisiografia g = (CatETipoFisiografia) o;
            return (this.fisiografiaID == g.getFisiografiaID());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 61 * hash + this.fisiografiaID;
        return hash;
    }
  
}
