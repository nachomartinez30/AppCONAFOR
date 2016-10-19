package gob.conafor.taxonomia.mod;

import java.util.Objects;

public class CatEFormaVida {

    private Integer formaVidaID;
    private String formaVida;

    public Integer getFormaVidaID() {
        return formaVidaID;
    }

    public void setFormaVidaID(Integer formaVidaID) {
        this.formaVidaID = formaVidaID;
    }

    public String getFormaVida() {
        return formaVida;
    }

    public void setFormaVida(String formaVida) {
        this.formaVida = formaVida;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o instanceof CatEFormaVida) {
            CatEFormaVida g = (CatEFormaVida) o;
            return (this.formaVidaID == g.getFormaVidaID());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.formaVidaID);
        return hash;
    }

    @Override
    public String toString(){
        return this.formaVidaID + "-" + this.formaVida;
    }
}
