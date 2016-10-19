package gob.conafor.suelo.mod;

public class CEEvidenciaErosion {
    //Esta sección es parte de Cobertura suelo módulo A
    private int evidenciaErosionID;
    private int coberturaSueloID;
    private Integer punto;
    private Integer dosel;
    private Integer lecturaTierraID;

    public int getEvidenciaErosionID() {
        return evidenciaErosionID;
    }

    public void setEvidenciaErosionID(int evidenciaErosionID) {
        this.evidenciaErosionID = evidenciaErosionID;
    }

    public int getCoberturaSueloID() {
        return coberturaSueloID;
    }

    public void setCoberturaSueloID(int coberturaSueloID) {
        this.coberturaSueloID = coberturaSueloID;
    }

    public Integer getPunto() {
        return punto;
    }

    public void setPunto(Integer punto) {
        this.punto = punto;
    }

    public Integer getDosel() {
        return dosel;
    }

    public void setDosel(Integer dosel) {
        this.dosel = dosel;
    }

    public Integer getLecturaTierraID() {
        return lecturaTierraID;
    }

    public void setLecturaTierraID(Integer lecturaTierraID) {
        this.lecturaTierraID = lecturaTierraID;
    }
    
}
