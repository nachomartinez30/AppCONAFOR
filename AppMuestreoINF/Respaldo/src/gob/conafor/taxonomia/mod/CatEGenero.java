package gob.conafor.taxonomia.mod;

public class CatEGenero {

    // CAT_Genero
    private int generoID;
    private String nombre;
    private int familiaID;

    public int getGeneroID() {
        return generoID;
    }

    public void setGeneroID(int generoID) {
        this.generoID = generoID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getFamiliaID() {
        return familiaID;
    }

    public void setFamiliaID(int familiaID) {
        this.familiaID = familiaID;
    }
    
    @Override
    public String toString(){
        return this.nombre;
    }
    @Override
    public boolean equals(Object o) {
        if (o instanceof CatEGenero) {
            CatEGenero g = (CatEGenero) o;
            return (this.generoID == g.getGeneroID());
        } else {
            return false;
        }
    }

   @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.generoID;
        return hash;
    }
}
