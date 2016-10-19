package gob.conafor.sys.mod;

public class CESeccion {

    private String Seccion;
    private String modulo;
    private String estatus;

    public String getSeccion() {
        return Seccion;
    }

    public void setSeccion(String Seccion) {
        this.Seccion = Seccion;
    }

    public String getModulo() {
        return modulo;
    }

    public void setModulo(String modulo) {
        this.modulo = modulo;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }
    
    @Override
    public String toString(){
        return  " " + getSeccion() + " \t" + " " + getModulo() + "\t" + " "+ getEstatus();
    }

}
