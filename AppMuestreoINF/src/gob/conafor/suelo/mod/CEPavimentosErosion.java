package gob.conafor.suelo.mod;

public class CEPavimentosErosion {
    // Módulo A, Sección 4.11.2.2 Evaluación de las condiciones de degradación
    // del suelo.
    private int pavimentosErosionID;
    private int sitioID;
    private int numero;
    private Float diametro;

    public int getPavimentosErosionID() {
        return pavimentosErosionID;
    }

    public void setPavimentosErosionID(int pavimentosErosionID) {
        this.pavimentosErosionID = pavimentosErosionID;
    }

    public int getSitioID() {
        return sitioID;
    }

    public void setSitioID(int sitioID) {
        this.sitioID = sitioID;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Float getDiametro() {
        return diametro;
    }

    public void setDiametro(Float diametro) {
        this.diametro = diametro;
    }

}
