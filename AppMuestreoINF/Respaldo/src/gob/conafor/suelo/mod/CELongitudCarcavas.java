package gob.conafor.suelo.mod;

public class CELongitudCarcavas {
    // Módulo A, Sección 4.11.6.1 Erosión hídrica con deformación del terreno
    private int longitudCarcavasID;
    private int sitioID;
    private int campoLongitud;
    private Float longitud;

    public int getLongitudCarcavasID() {
        return longitudCarcavasID;
    }

    public void setLongitudCarcavasID(int longitudCarcavasID) {
        this.longitudCarcavasID = longitudCarcavasID;
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
