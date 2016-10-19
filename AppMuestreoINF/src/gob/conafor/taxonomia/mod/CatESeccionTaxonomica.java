package gob.conafor.taxonomia.mod;

import java.util.Objects;

public class CatESeccionTaxonomica {

    private Integer seccionTaxonomicaID;
    private String seccion;

    public Integer getSeccionTaxonomicaID() {
        return seccionTaxonomicaID;
    }

    public void setSeccionTaxonomicaID(Integer seccionTaxonomicaID) {
        this.seccionTaxonomicaID = seccionTaxonomicaID;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.seccionTaxonomicaID);
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
        final CatESeccionTaxonomica other = (CatESeccionTaxonomica) obj;
        if (!Objects.equals(this.seccionTaxonomicaID, other.seccionTaxonomicaID)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return seccionTaxonomicaID + "-" + seccion;
    }
    
}
