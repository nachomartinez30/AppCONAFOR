package gob.conafor.pc.mod;

public class CatETransporteAccesibilidad {

    //CAT_Accesibilidad
    private int medioTransporteID;
    private int viaAccesibilidadID;

    public CatETransporteAccesibilidad(int medioTransporteID, int viaAccesibilidadID) {
        super();
        this.medioTransporteID = medioTransporteID;
        this.viaAccesibilidadID = viaAccesibilidadID;
    }

    public int getMedioTransporteID() {
        return medioTransporteID;
    }

    public void setMedioTransporteID(int medioTransporteID) {
        this.medioTransporteID = medioTransporteID;
    }

    public int getViaAccesibilidadID() {
        return viaAccesibilidadID;
    }

    public void setViaAccesibilidadID(int viaAccesibilidadID) {
        this.viaAccesibilidadID = viaAccesibilidadID;
    }

}
