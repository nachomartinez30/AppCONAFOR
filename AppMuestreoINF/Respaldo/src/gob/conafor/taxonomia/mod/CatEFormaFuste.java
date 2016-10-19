package gob.conafor.taxonomia.mod;

import java.util.Objects;

public class CatEFormaFuste {
    private Integer formaFusteID;
    private String formaFuste;

    public Integer getFormaFusteID() {
        return formaFusteID;
    }

    public void setFormaFusteID(Integer formaFusteID) {
        this.formaFusteID = formaFusteID;
    }

    public String getFormaFuste() {
        return formaFuste;
    }

    public void setFormaFuste(String formaFuste) {
        this.formaFuste = formaFuste;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o instanceof CatEFormaFuste) {
            CatEFormaFuste g = (CatEFormaFuste) o;
            return (this.formaFusteID == g.getFormaFusteID());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.formaFusteID);
        return hash;
    }
    
    public String toString(){
        return this.formaFusteID + "-" + this.formaFuste;
    }
}
