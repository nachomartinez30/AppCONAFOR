package gob.conafor.taxonomia.mod;

public class CatEFamiliaEspecie {

    //CAT_FamiliaEspecie
    
    private int familiaID;
    private String nombre;
    private int claseID;

    public int getFamiliaID() {
        return familiaID;
    }

    public void setFamiliaID(int familiaID) {
        this.familiaID = familiaID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getClaseID() {
        return claseID;
    }

    public void setClaseID(int claseID) {
        this.claseID = claseID;
    }

    @Override
    public String toString() {
        return nombre;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o instanceof CatEFamiliaEspecie) {
            CatEFamiliaEspecie g = (CatEFamiliaEspecie) o;
            return (this.familiaID == g.getFamiliaID());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + this.familiaID;
        return hash;
    }

}
