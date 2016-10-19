package gob.conafor.upm.mod;

public class CatETipoEpifita {
    
    private int tipoEpifitaID;
    private String Nombre;

    public int getTipoEpifitaID() {
        return tipoEpifitaID;
    }

    public void setTipoEpifitaID(int tipoEpifitaID) {
        this.tipoEpifitaID = tipoEpifitaID;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    @Override
    public String toString() {
        return this.tipoEpifitaID + "-" + this.Nombre;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.tipoEpifitaID;
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
        final CatETipoEpifita other = (CatETipoEpifita) obj;
        if (this.tipoEpifitaID != other.tipoEpifitaID) {
            return false;
        }
        return true;
    }
}
