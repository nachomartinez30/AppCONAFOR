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
public class CEMedicionCanalillo {

    private int MedicionCanalillosID;
    private int SitioID;
    private int NumeroCanalillos;
    private float ProfundidadPromedio;
    private float Longitud;
    private float Volumen;

    public int getMedicionCanalillosID() {
        return MedicionCanalillosID;
    }

    public void setMedicionCanalillosID(int MedicionCanalillosID) {
        this.MedicionCanalillosID = MedicionCanalillosID;
    }

    public int getSitioID() {
        return SitioID;
    }

    public void setSitioID(int SitioID) {
        this.SitioID = SitioID;
    }

    public int getNumeroCanalillos() {
        return NumeroCanalillos;
    }

    public void setNumeroCanalillos(int NumeroCanalillos) {
        this.NumeroCanalillos = NumeroCanalillos;
    }

    public float getProfundidadPromedio() {
        return ProfundidadPromedio;
    }

    public void setProfundidadPromedio(float ProfundidadPromedio) {
        this.ProfundidadPromedio = ProfundidadPromedio;
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
