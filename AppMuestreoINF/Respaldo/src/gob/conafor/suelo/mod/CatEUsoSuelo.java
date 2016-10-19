package gob.conafor.suelo.mod;

public class CatEUsoSuelo {

    private int usoSueloID;
    private String uso;

    public int getUsoSueloID() {
        return usoSueloID;
    }

    public void setUsoSueloID(int usoSueloID) {
        this.usoSueloID = usoSueloID;
    }

    public String getUso() {
        return uso;
    }

    public void setUso(String uso) {
        this.uso = uso;
    }

    @Override
    public String toString() {
        return usoSueloID + "";
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + this.usoSueloID;
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
        final CatEUsoSuelo other = (CatEUsoSuelo) obj;
        if (this.usoSueloID != other.usoSueloID) {
            return false;
        }
        return true;
    }

}
