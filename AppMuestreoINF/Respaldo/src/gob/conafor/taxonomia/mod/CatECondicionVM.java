package gob.conafor.taxonomia.mod;

public class CatECondicionVM {

    private int condicionVMID;
    private String descripcion;

    public int getCondicionVMID() {
        return condicionVMID;
    }

    public void setCondicionVMID(int condicionVMID) {
        this.condicionVMID = condicionVMID;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + this.condicionVMID;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CatECondicionVM other = (CatECondicionVM) obj;
        if (this.condicionVMID != other.condicionVMID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString(){
        return this.condicionVMID + "-" + this.descripcion;
    }
}
