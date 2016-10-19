package gob.conafor.taxonomia.mod;

public class CatEGradoPutrefaccionArbolado {
    int gradoPutrefaccionID;
    String clave;
    String tipo;
    String descripcion;

    public int getGradoPutrefaccionID() {
        return gradoPutrefaccionID;
    }

    public void setGradoPutrefaccionID(int gradoPutrefaccionID) {
        this.gradoPutrefaccionID = gradoPutrefaccionID;
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
    
    @Override
    public String toString(){
        return this.clave;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o instanceof CatEGradoPutrefaccionArbolado) {
            CatEGradoPutrefaccionArbolado g = (CatEGradoPutrefaccionArbolado) o;
            return (this.gradoPutrefaccionID == g.gradoPutrefaccionID);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 31 * hash + this.gradoPutrefaccionID;
        return hash;
    }
    
}
