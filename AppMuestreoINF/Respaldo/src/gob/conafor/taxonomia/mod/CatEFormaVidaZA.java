package gob.conafor.taxonomia.mod;

public class CatEFormaVidaZA {
    
    int formaVidaZAID;
    String morfotipo;

    public int getFormaVidaZAID() {
        return formaVidaZAID;
    }

    public void setFormaVidaZAID(int formaVidaZAID) {
        this.formaVidaZAID = formaVidaZAID;
    }

    public String getMorfotipo() {
        return morfotipo;
    }

    public void setMorfotipo(String morfotipo) {
        this.morfotipo = morfotipo;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.formaVidaZAID;
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
        final CatEFormaVidaZA other = (CatEFormaVidaZA) obj;
        if (this.formaVidaZAID != other.formaVidaZAID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return formaVidaZAID + "";
    }

    

}
