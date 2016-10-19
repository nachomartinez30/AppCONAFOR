package gob.conafor.upm.mod;

public class CatETipoUPM {

    //CAT_TipoUPM
    private int tipoUPMID;
    private String tipoUPM;
    private String descripicion;

    public int getTipoUPMID() {
        return tipoUPMID;
    }

    public void setTipoUPMID(int tipoUPMID) {
        this.tipoUPMID = tipoUPMID;
    }

    public String getTipoUPM() {
        return tipoUPM;
    }

    public void setTipoUPM(String tipoUPM) {
        this.tipoUPM = tipoUPM;
    }

    public String getDescripicion() {
        return descripicion;
    }

    public void setDescripicion(String descripicion) {
        this.descripicion = descripicion;
    }

    public String toString() {
        return this.tipoUPMID + "-" + this.tipoUPM;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.tipoUPMID;
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
        final CatETipoUPM other = (CatETipoUPM) obj;
        if (this.tipoUPMID != other.tipoUPMID) {
            return false;
        }
        return true;
    }

}
