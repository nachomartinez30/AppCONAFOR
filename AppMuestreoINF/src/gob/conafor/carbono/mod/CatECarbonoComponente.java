package gob.conafor.carbono.mod;

public class CatECarbonoComponente {
    
    private int componenteID;
    private String componente;

    public int getComponenteID() {
        return componenteID;
    }

    public void setComponenteID(int componenteID) {
        this.componenteID = componenteID;
    }

    public String getComponente() {
        return componente;
    }

    public void setComponente(String Componente) {
        this.componente = Componente;
    }

    @Override
    public String toString() {
        return this.getComponenteID() + "-" + this.componente;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + this.componenteID;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CatECarbonoComponente other = (CatECarbonoComponente) obj;
        if (this.componenteID != other.componenteID) {
            return false;
        }
        return true;
    }

}
