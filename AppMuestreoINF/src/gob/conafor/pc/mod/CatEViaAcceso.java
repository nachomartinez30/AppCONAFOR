package gob.conafor.pc.mod;

public class CatEViaAcceso {

    //CAT_ViaAccesibilidad
    private int viaAccesibilidadID;
    private String via;
    private String descripcion;

    public int getViaAccesibilidadID() {
        return viaAccesibilidadID;
    }

    public void setViaAccesibilidadID(int viaAccesibilidadID) {
        this.viaAccesibilidadID = viaAccesibilidadID;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return this.viaAccesibilidadID + "-" + this.via;
    }

}
