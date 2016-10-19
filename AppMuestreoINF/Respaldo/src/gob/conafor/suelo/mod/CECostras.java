package gob.conafor.suelo.mod;

public class CECostras {
    // Módulo A, Sección 4.11.2.2 Evaluación de las condiciones e degradación
    // del suelo.
    private int costrasID;
    private int sitioID;
    private int numero;
    private Float diametro;

    public int getCostrasID() {
        return costrasID;
    }

    public void setCostrasID(int costrasID) {
        this.costrasID = costrasID;
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
