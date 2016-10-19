package gob.conafor.suelo.mod;

public class CECanalillo {
    // Módulo A, Sección 4.11.2.2 Evaluación de las condiciones de degradación
    // del suelo.
    private int canalilloID;
    private int sitioID;
    private int numero;
    private Float ancho;
    private Float profundidad;

    public int getCanalilloID() {
        return canalilloID;
    }

    public void setCanalilloID(int canalilloID) {
        this.canalilloID = canalilloID;
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

    public Float getAncho() {
        return ancho;
    }

    public void setAncho(Float ancho) {
        this.ancho = ancho;
    }

    public Float getProfundidad() {
        return profundidad;
    }

    public void setProfundidad(Float profundidad) {
        this.profundidad = profundidad;
    }

}
