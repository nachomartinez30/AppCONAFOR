package gob.conafor.taxonomia.mod;

public class CatEPorcentajeArbolado {

    //Catalago de variables de copa

    private int porcentajeArboladoID;
    private String clave;
    private String descripcion;

    public int getPorcentajeArboladoID() {
        return porcentajeArboladoID;
    }

    public void setPorcentajeArboladoID(int porcentajeArboladoID) {
        this.porcentajeArboladoID = porcentajeArboladoID;
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
        int hash = 7;
        hash = 97 * hash + this.porcentajeArboladoID;
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof CatEPorcentajeArbolado) {
            CatEPorcentajeArbolado g = (CatEPorcentajeArbolado) o;
            return (this.porcentajeArboladoID == g.getPorcentajeArboladoID());
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return clave;
    }

}
