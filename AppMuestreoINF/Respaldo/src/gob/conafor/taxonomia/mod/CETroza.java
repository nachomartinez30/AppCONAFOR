package gob.conafor.taxonomia.mod;

public class CETroza {

    // Tipo de troza de la submuestra
    private int trozaID;
    private int submuestraID;
    private Integer numeroTroza;
    private int tipoTrozaID;

    public int getTrozaID() {
        return trozaID;
    }

    public void setTrozaID(int trozaID) {
        this.trozaID = trozaID;
    }
    
    public int getSubmuestraID() {
        return submuestraID;
    }

    public void setSubmuestraID(int submuestraID) {
        this.submuestraID = submuestraID;
    }

    public Integer getNumeroTroza() {
        return numeroTroza;
    }

    public void setNumeroTroza(Integer numeroTroza) {
        this.numeroTroza = numeroTroza;
    }

    public int getTipoTrozaID() {
        return tipoTrozaID;
    }

    public void setTipoTrozaID(int tipoTrozaID) {
        this.tipoTrozaID = tipoTrozaID;
    }
    
}
