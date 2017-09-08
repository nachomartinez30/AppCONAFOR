package gob.conafor.sitio.mod;

public class CatEClaveSerieV {
    
    private int claveSerieVID;
    private String ecosistema;
    private String formacion;
    private String tipoVegetacion;
    private String clave;
    private boolean EsForestal;

    public int getClaveSerievID() {
        return claveSerieVID;
    }

    public void setClaveSerievID(int claveSerieVID) {
        this.claveSerieVID = claveSerieVID;
    }

    public String getEcosistema() {
        return ecosistema;
    }

    public void setEcosistema(String ecosistema) {
        this.ecosistema = ecosistema;
    }

    public String getFormacion() {
        return formacion;
    }

    public void setFormacion(String formacion) {
        this.formacion = formacion;
    }

    public String getTipoVegetacion() {
        return tipoVegetacion;
    }

    public void setTipoVegetacion(String tipoVegetacion) {
        this.tipoVegetacion = tipoVegetacion;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public boolean getEsForestal() {
        return EsForestal;
    }

    public void setEsForestal(boolean EsForestal) {
        this.EsForestal = EsForestal;
    }
    
    @Override
    public String toString(){
        return this.clave;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o instanceof CatEClaveSerieV) {
            CatEClaveSerieV g = (CatEClaveSerieV) o;
            return (this.claveSerieVID == g.claveSerieVID);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + this.claveSerieVID;
        return hash;
    }
 
}
