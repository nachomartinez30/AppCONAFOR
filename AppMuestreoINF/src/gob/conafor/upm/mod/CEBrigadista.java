package gob.conafor.upm.mod;

import java.util.Objects;

public class CEBrigadista {
    
    private Integer brigadistaID;
    private String Nombre;
    private String ApellidoPaterno;
    private String ApellidoMaterno;
    private Integer puestoID;
    private Integer empresaID;
    
    public Integer getBrigadistaID() {
        return brigadistaID;
    }

    public void setBrigadistaID(Integer brigadistaID) {
        this.brigadistaID = brigadistaID;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellidoPaterno() {
        return ApellidoPaterno;
    }

    public void setApellidoPaterno(String ApellidoPaterno) {
        this.ApellidoPaterno = ApellidoPaterno;
    }

    public String getApellidoMaterno() {
        return ApellidoMaterno;
    }

    public void setApellidoMaterno(String ApellidoMaterno) {
        this.ApellidoMaterno = ApellidoMaterno;
    }

    public Integer getPuestoID() {
        return puestoID;
    }

    public void setPuestoID(Integer puestoID) {
        this.puestoID = puestoID;
    }

    public Integer getEmpresaID() {
        return empresaID;
    }

    public void setEmpresaID(Integer empresaID) {
        this.empresaID = empresaID;
    }

    @Override
    public String toString() {
        return this.Nombre + " " + this.ApellidoPaterno + " " + this.ApellidoMaterno;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.brigadistaID);
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
        final CEBrigadista other = (CEBrigadista) obj;
        if (!Objects.equals(this.brigadistaID, other.brigadistaID)) {
            return false;
        }
        return true;
    }
    
}
