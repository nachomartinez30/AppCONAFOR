package gob.conafor.suelo.mod;

public class CatELecturaTierra {

    private int lecturaTierraID;
    private String clave;
    private String descripcion;

    public int getLecturaTierraID() {
        return lecturaTierraID;
    }

    public void setLecturaTierraID(int lecturaTierraID) {
        this.lecturaTierraID = lecturaTierraID;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return clave;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + this.lecturaTierraID;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CatELecturaTierra other = (CatELecturaTierra) obj;
        if (this.lecturaTierraID != other.lecturaTierraID) {
            return false;
        }
        return true;
    }

}
