package gob.conafor.taxonomia.mod;

public class CatEPosicionCopa {

    //Posicion copa arbolado

    private int posicionCopaID;
    private String posicionCopa;
    private String descripcion;

    public int getPosicionCopaID() {
        return posicionCopaID;
    }

    public void setPosicionCopaID(int posicionCopaID) {
        this.posicionCopaID = posicionCopaID;
    }

    public String getPosicionCopa() {
        return posicionCopa;
    }

    public void setPosicionCopa(String posicionCopa) {
        this.posicionCopa = posicionCopa;
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
        hash = 47 * hash + this.posicionCopaID;
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof CatEPosicionCopa) {
            CatEPosicionCopa g = (CatEPosicionCopa) o;
            return (this.posicionCopaID == g.getPosicionCopaID());
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return this.posicionCopaID + "-" + posicionCopa;
    }

}
