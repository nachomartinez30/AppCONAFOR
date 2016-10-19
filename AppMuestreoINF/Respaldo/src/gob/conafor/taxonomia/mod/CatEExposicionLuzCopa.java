package gob.conafor.taxonomia.mod;

public class CatEExposicionLuzCopa {

    //Exposicion a la luz de copa arbolado

    private int exposicionLuzID;
    private String codigo;
    private String descripcion;

    public int getExposicionLuzID() {
        return exposicionLuzID;
    }

    public void setExposicionLuzID(int exposicionLuzID) {
        this.exposicionLuzID = exposicionLuzID;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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
        hash = 31 * hash + this.exposicionLuzID;
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof CatEExposicionLuzCopa) {
            CatEExposicionLuzCopa g = (CatEExposicionLuzCopa) o;
            return (this.exposicionLuzID == g.getExposicionLuzID());
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return  codigo;
    }
    
}
