package gob.conafor.taxonomia.mod;

import java.util.Objects;

public class CatECondicionArbolado {
    Integer condicionID;
    String condicion;

    public Integer getCondicionID() {
        return condicionID;
    }

    public void setCondicionID(Integer condicionID) {
        this.condicionID = condicionID;
    }

    public String getCondicion() {
        return condicion;
    }

    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof CatECondicionArbolado) {
            CatECondicionArbolado g = (CatECondicionArbolado) o;
            return (this.condicionID == g.getCondicionID());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.condicionID);
        return hash;
    }
     
    @Override
    public String toString(){
        return this.condicionID + "-" + this.condicion;
    }
}
