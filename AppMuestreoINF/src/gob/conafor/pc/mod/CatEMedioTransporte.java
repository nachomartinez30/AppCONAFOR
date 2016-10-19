package gob.conafor.pc.mod;

public class CatEMedioTransporte {

    //CAT_MedioTransporte
    private int medioTransporteID;
    private String Medio;
    private String Descripcion;

    public int getMedioTransporteID() {
        return medioTransporteID;
    }

    public void setMedioTransporteID(int medioTransporteID) {
        this.medioTransporteID = medioTransporteID;
    }

    public String getMedio() {
        return Medio;
    }

    public void setMedio(String medio) {
        Medio = medio;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String toString() {
        return this.medioTransporteID + "-" + this.Medio;
    }

}
