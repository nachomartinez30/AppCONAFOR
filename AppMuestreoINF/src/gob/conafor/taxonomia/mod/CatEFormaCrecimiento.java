package gob.conafor.taxonomia.mod;

public class CatEFormaCrecimiento {
    
    private int formaCrecimientoID;
    private String descripcion;

    public int getFormaCrecimientoID() {
        return formaCrecimientoID;
    }

    public void setFormaCrecimientoID(int formaCrecimientoID) {
        this.formaCrecimientoID = formaCrecimientoID;
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
        hash = 37 * hash + this.formaCrecimientoID;
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
        final CatEFormaCrecimiento other = (CatEFormaCrecimiento) obj;
        if (this.formaCrecimientoID != other.formaCrecimientoID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return formaCrecimientoID + "-" + descripcion;
    }
    
    
}
