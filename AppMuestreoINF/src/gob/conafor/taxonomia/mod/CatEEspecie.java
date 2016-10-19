package gob.conafor.taxonomia.mod;

public class CatEEspecie {

    // CAT_Especie
    private int especieID;
    private String nombre;
    private int generoID;

    public int getEspecieID() {
        return especieID;
    }

    public void setEspecieID(int especieID) {
        this.especieID = especieID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getGeneroID() {
        return generoID;
    }

    public void setGeneroID(int generoID) {
        this.generoID = generoID;
    }
    
    @Override
    public String toString(){
        return this.nombre;
    }
    @Override
    public boolean equals(Object o) {
        if (o instanceof CatEEspecie) {
            CatEEspecie g = (CatEEspecie) o;
            return (this.especieID == g.getEspecieID());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.especieID;
        return hash;
    }

}
