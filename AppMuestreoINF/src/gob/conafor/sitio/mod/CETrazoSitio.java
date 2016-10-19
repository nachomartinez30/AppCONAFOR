package gob.conafor.sitio.mod;

import gob.conafor.utils.ValidacionesComunes;

public class CETrazoSitio {

    private int upmID;
    private int sitioID;
    private int sitio;
    private int hipsometroBrujula;
    private int cintaClinometroBrujula;
    private Integer cuadrante1;
    private Integer cuadrante2;
    private Integer cuadrante3;
    private Integer cuadrante4;
    private Float distancia1;
    private Float distancia2;
    private Float distancia3;
    private Float distancia4;
    private ValidacionesComunes validacion = new ValidacionesComunes();

    public int getUpmID() {
        return upmID;
    }

    public void setUpmID(int upmID) {
        this.upmID = upmID;
    }

    public int getSitioID() {
        return sitioID;
    }

    public void setSitioID(int sitioID) {
        this.sitioID = sitioID;
    }

    public int getSitio() {
        return sitio;
    }

    public void setSitio(int sitio) {
        this.sitio = sitio;
    }

    public Integer getHipsometroBrujula() {
        return hipsometroBrujula;
    }

    public void setHipsometroBrujula(Integer hipsometroBrujula) {
        this.hipsometroBrujula = hipsometroBrujula;
    }

    public int getCintaClinometroBrujula() {
        return cintaClinometroBrujula;
    }

    public void setCintaClinometroBrujula(int cintaClinometroBrujula) {
        this.cintaClinometroBrujula = cintaClinometroBrujula;
    }

    public Integer getCuadrante1() {
        return cuadrante1;
    }

    public void setCuadrante1(Integer cuadrante1) {
        if (cuadrante1 != null) {
            this.cuadrante1 = cuadrante1;
            setDistancia1(asignarDistancia(cuadrante1));
        } else {
            this.cuadrante1 = null;
        }
    }

    public Integer getCuadrante2() {
        return cuadrante2;
    }

    public void setCuadrante2(Integer cuadrante2) {
        if (cuadrante2 != null) {
            this.cuadrante2 = cuadrante2;
            setDistancia2(asignarDistancia(cuadrante2));
        } else {
            this.cuadrante2 = null;
        }
    }

    public Integer getCuadrante3() {
        return cuadrante3;
    }

    public void setCuadrante3(Integer cuadrante3) {
        if (cuadrante3 != null) {
            this.cuadrante3 = cuadrante3;
            setDistancia3(asignarDistancia(cuadrante3));
        } else {
            this.cuadrante3 = null;
        }
    }

    public Integer getCuadrante4() {
        return cuadrante4;
    }

    public void setCuadrante4(Integer cuadrante4) {
        if (cuadrante4 != null) {
            this.cuadrante4 = cuadrante4;
            setDistancia4(asignarDistancia(cuadrante4));
        } else {
            this.cuadrante4 = null;
        }
    }

    public Float getDistancia1() {
        return distancia1;
    }

    public void setDistancia1(Float distancia1) {
        this.distancia1 = distancia1;
    }

    public Float getDistancia2() {
        return distancia2;
    }

    public void setDistancia2(Float distancia2) {
        this.distancia2 = distancia2;
    }

    public Float getDistancia3() {
        return distancia3;
    }

    public void setDistancia3(Float distancia3) {
        this.distancia3 = distancia3;
    }

    public Float getDistancia4() {
        return distancia4;
    }

    public void setDistancia4(Float distancia4) {
        this.distancia4 = distancia4;
    }

    private Float asignarDistancia(Integer cuadrante) {
        if ((cuadrante >= 0 && cuadrante <= 10) || (cuadrante <= -1 && cuadrante >= -10)) {
            return (float) 11.28;
        } else if ((cuadrante >= 11 && cuadrante <= 20) || (cuadrante <= -11 && cuadrante >= -20)) {
            return (float) 11.34;
        } else if ((cuadrante >= 21 && cuadrante <= 30) || (cuadrante <= -21 && cuadrante >= -30)) {
            return (float) 11.50;
        } else if ((cuadrante >= 31 && cuadrante <= 40) || (cuadrante <= -31 && cuadrante >= -40)) {
            return (float) 11.78;
        } else if ((cuadrante >= 41 && cuadrante <= 50) || (cuadrante <= -41 && cuadrante >= -50)) {
            return (float) 12.15;
        } else if ((cuadrante >= 51 && cuadrante <= 60) || (cuadrante <= -51 && cuadrante >= -60)) {
            return (float) 12.61;
        } else if ((cuadrante >= 61 && cuadrante <= 70) || (cuadrante <= -61 && cuadrante >= -70)) {
            return (float) 13.15;
        } else if ((cuadrante >= 71 && cuadrante <= 80) || (cuadrante >= -71 && cuadrante <= -80)) {
            return (float) 13.77;
        } else if ((cuadrante >= 81 && cuadrante <= 90) || (cuadrante <= -81 && cuadrante >= -90)) {
            return (float) 14.45;
        } else if ((cuadrante >= 91 && cuadrante <= 100) || (cuadrante <= -91 && cuadrante >= -100)) {
            return (float) 15.18;
        } else if ((cuadrante >= 101 && cuadrante <= 150) || (cuadrante <= -101 && cuadrante >= -150)) {
            return (float) 15.95;
        }
        return (float) 0.0;
    }
}
