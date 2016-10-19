package gob.conafor.taxonomia.mod;

public class CatEInfraespecie {
    
    private int infraespecieID;
    private String nombre;
    private int especieID;

    public int getInfraespecieID() {
        return infraespecieID;
    }

    public void setInfraespecieID(int infraespecieID) {
        this.infraespecieID = infraespecieID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEspecieID() {
        return especieID;
    }

    public void setEspecieID(int especieID) {
        this.especieID = especieID;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.infraespecieID;
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
        final CatEInfraespecie other = (CatEInfraespecie) obj;
        if (this.infraespecieID != other.infraespecieID) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return nombre;
    }
}
