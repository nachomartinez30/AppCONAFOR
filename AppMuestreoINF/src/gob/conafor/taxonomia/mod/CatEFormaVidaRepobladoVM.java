package gob.conafor.taxonomia.mod;

public class CatEFormaVidaRepobladoVM {

    private int formaVidaRepobladoVMID;
    private String formaVida;

    public int getFormaVidaRepobladoVMID() {
        return formaVidaRepobladoVMID;
    }

    public void setFormaVidaRepobladoVMID(int formaVidaRepobladoVMID) {
        this.formaVidaRepobladoVMID = formaVidaRepobladoVMID;
    }

    public String getFormaVida() {
        return formaVida;
    }

    public void setFormaVida(String formaVida) {
        this.formaVida = formaVida;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.formaVidaRepobladoVMID;
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
        final CatEFormaVidaRepobladoVM other = (CatEFormaVidaRepobladoVM) obj;
        if (this.formaVidaRepobladoVMID != other.formaVidaRepobladoVMID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return formaVidaRepobladoVMID + "-" + formaVida;
    }

}
