/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.conafor.suelo.mod;

/**
 *
 * @author ignacio.martinez
 */
public class CEMedicionCarcava {

    private int MedicionCarcavasID;
    private int SitioID;
    private int NumeroCarcavas;
    private float ProfundidadPromedio;
    private float AnchoPromedio;
    private float Longitud;
    private float Volumen;

    public int getMedicionCarcavasID() {
        return MedicionCarcavasID;
    }

    public void setMedicionCarcavasID(int MedicionCarcavasID) {
        this.MedicionCarcavasID = MedicionCarcavasID;
    }

    public int getSitioID() {
        return SitioID;
    }

    public void setSitioID(int SitioID) {
        this.SitioID = SitioID;
    }

    public int getNumeroCarcavas() {
        return NumeroCarcavas;
    }

    public void setNumeroCarcavas(int NumeroCarcavas) {
        this.NumeroCarcavas = NumeroCarcavas;
    }

    public float getProfundidadPromedio() {
        return ProfundidadPromedio;
    }

    public void setProfundidadPromedio(float ProfundidadPromedio) {
        this.ProfundidadPromedio = ProfundidadPromedio;
    }

    public float getAnchoPromedio() {
        return AnchoPromedio;
    }

    public void setAnchoPromedio(float AnchoPromedio) {
        this.AnchoPromedio = AnchoPromedio;
    }

    public float getLongitud() {
        return Longitud;
    }

    public void setLongitud(float Longitud) {
        this.Longitud = Longitud;
    }

    public float getVolumen() {
        return Volumen;
    }

    public void setVolumen(float Volumen) {
        this.Volumen = Volumen;
    }
    
    
}
