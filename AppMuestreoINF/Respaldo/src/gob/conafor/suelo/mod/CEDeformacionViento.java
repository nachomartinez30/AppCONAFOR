package gob.conafor.suelo.mod;

public class CEDeformacionViento {
    //Módulo A, Sección 4.11.6.2 Deformación del terreno por acción del viento
    private int deformacionVientoID;
    private int sitioID;
    private int medicionMonticulos;
    private Float alturaMonticulos;
    private Float diametroMonticulos;
    private Float longitudMonticulos;
    private Float distanciaMonticulos;
    private Integer azimutMonticulos;

    public int getDeformacionVientoID() {
        return deformacionVientoID;
    }

    public void setDeformacionVientoID(int deformacionVientoID) {
        this.deformacionVientoID = deformacionVientoID;
    }

    public int getSitioID() {
        return sitioID;
    }

    public void setSitioID(int sitioID) {
        this.sitioID = sitioID;
    }

    public int getMedicionMonticulos() {
        return medicionMonticulos;
    }

    public void setMedicionMonticulos(int medicionMonticulos) {
        this.medicionMonticulos = medicionMonticulos;
    }

    public Float getAlturaMonticulos() {
        return alturaMonticulos;
    }

    public void setAlturaMonticulos(Float alturaMonticulos) {
        this.alturaMonticulos = alturaMonticulos;
    }

    public Float getDiametroMonticulos() {
        return diametroMonticulos;
    }

    public void setDiametroMonticulos(Float diametroMonticulos) {
        this.diametroMonticulos = diametroMonticulos;
    }

    public Float getLongitudMonticulos() {
        return longitudMonticulos;
    }

    public void setLongitudMonticulos(Float longitudMonticulos) {
        this.longitudMonticulos = longitudMonticulos;
    }

    public Float getDistanciaMonticulos() {
        return distanciaMonticulos;
    }

    public void setDistanciaMonticulos(Float distanciaMonticulos) {
        this.distanciaMonticulos = distanciaMonticulos;
    }

    public Integer getAzimutMonticulos() {
        return azimutMonticulos;
    }

    public void setAzimutMonticulos(Integer azimutMonticulos) {
        this.azimutMonticulos = azimutMonticulos;
    }
    
}
