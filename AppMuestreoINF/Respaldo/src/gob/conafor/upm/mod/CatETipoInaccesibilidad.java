package gob.conafor.upm.mod;

public class CatETipoInaccesibilidad {

    private int tipoInaccesibilidadID;
    private String clave;
    private String tipo;
    private String descripcion;

    public int getTipoInaccesibilidadID() {
        return tipoInaccesibilidadID;
    }

    public void setTipoInaccesibilidadID(int tipoInaccesibilidadID) {
        this.tipoInaccesibilidadID = tipoInaccesibilidadID;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String toString() {
        return this.tipoInaccesibilidadID + "-" + this.clave;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.tipoInaccesibilidadID;
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
        final CatETipoInaccesibilidad other = (CatETipoInaccesibilidad) obj;
        if (this.tipoInaccesibilidadID != other.tipoInaccesibilidadID) {
            return false;
        }
        return true;
    }

}
