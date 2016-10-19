package gob.conafor.suelo.mod;

public class CELongitudMonticulos {
    //Módulo A, Sección 4.11.6.2 Deformación del terreno por acción del viento
    private int longitudMonticulosID;
    private int sitioID;
    private int campoLongitud;
    private Float longitud;

    public int getLongitudMonticulosID() {
        return longitudMonticulosID;
    }

    public void setLongitudMonticulosID(int longitudMonticulosID) {
        this.longitudMonticulosID = longitudMonticulosID;
    }

    public int getSitioID() {
        return sitioID;
    }

    public void setSitioID(int sitioID) {
        this.sitioID = sitioID;
    }

    public int getCampoLongitud() {
        return campoLongitud;
    }

    public void setCampoLongitud(int campoLongitud) {
        this.campoLongitud = campoLongitud;
    }

    public Float getLongitud() {
        return longitud;
    }

    public void setLongitud(Float longitud) {
        this.longitud = longitud;
    }

}
