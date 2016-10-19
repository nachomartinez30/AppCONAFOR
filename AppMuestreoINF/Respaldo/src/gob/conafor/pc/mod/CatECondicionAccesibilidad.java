package gob.conafor.pc.mod;

public class CatECondicionAccesibilidad {

    //CAT_CondicionAccesibilidad
    private int condicionAccesibilidadID;
    private String condicion;
    private String descripcion;

    public int getCondicionAccesibilidadID() {
        return condicionAccesibilidadID;
    }

    public void setCondicionAccesibilidadID(int condicionAccesibilidadID) {
        this.condicionAccesibilidadID = condicionAccesibilidadID;
    }

    public String getCondicion() {
        return condicion;
    }

    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String toString() {
        return this.condicionAccesibilidadID + "-" + this.condicion;
    }

}
