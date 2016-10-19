package gob.conafor.suelo.mod;

public class CEPedestal {
    // Módulo A, Sección 4.11.2.2 Evaluación de las condiciones en centímetros
    private int pedestalID;
    private int sitioID;
    private int numero;
    private Float altura;

    public int getPedestalID() {
        return pedestalID;
    }

    public void setPedestalID(int pedestalID) {
        this.pedestalID = pedestalID;
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

    public Float getAltura() {
        return altura;
    }

    public void setAltura(Float altura) {
        this.altura = altura;
    }

}
