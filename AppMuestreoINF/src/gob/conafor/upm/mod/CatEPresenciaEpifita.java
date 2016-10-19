package gob.conafor.upm.mod;

public class CatEPresenciaEpifita {
    
    private int presenciaEpifitaID;
    private String descripcion;

    public int getPresenciaEpifitaID() {
        return presenciaEpifitaID;
    }

    public void setPresenciaEpifitaID(int presenciaEpifitaID) {
        this.presenciaEpifitaID = presenciaEpifitaID;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return this.presenciaEpifitaID + "-" + this.descripcion;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + this.presenciaEpifitaID;
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
        final CatEPresenciaEpifita other = (CatEPresenciaEpifita) obj;
        if (this.presenciaEpifitaID != other.presenciaEpifitaID) {
            return false;
        }
        return true;
    }
}
