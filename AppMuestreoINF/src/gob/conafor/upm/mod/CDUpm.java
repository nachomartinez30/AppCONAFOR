package gob.conafor.upm.mod;

import gob.conafor.conn.dat.LocalConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class CDUpm {

    private final List<CatEMalla> listMalla = new ArrayList();
    private final CatEMalla malla = new CatEMalla();
    private String query;
    
    public String[] getUPM() {
        List<String> listUpm = new ArrayList();
        String[] arrayUpm;
        query = "SELECT UPMID FROM UPM_MallaPuntos";
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                listUpm.add(rs.getString("UPMID"));
            }
            listUpm.add(0, null);
            arrayUpm = (String[]) listUpm.toArray(new String[listUpm.size()]);
            return arrayUpm;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                "Error! al obtener la malla de muestreo ",
                "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en malla de muestreo", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    //UPM_MallaMuestreo DIAAPROY = 1, INYDES = 2, AMAREF = 3 
    public List<Integer> getUPMID() {
        List<Integer> listUPMID = new ArrayList();
        //this.query = "SELECT UPMID, SecuenciaID, ProveedorID FROM UPM_MallaPuntos WHERE upmid =226708 or upmid =4 and UPMID NOT IN (SELECT UPMID FROM UPM_UPM) AND SecuenciaID <> 0";
        /*PRODUCCION*/ this.query = "SELECT UPMID, SecuenciaID, ProveedorID FROM UPM_MallaPuntos WHERE UPMID NOT IN (SELECT UPMID FROM UPM_UPM) AND SecuenciaID <> 0 order by UPMID";
        ///*DIAAPROY*/this.query = "SELECT UPMID, SecuenciaID, ProveedorID FROM UPM_MallaPuntos WHERE UPMID NOT IN (SELECT UPMID FROM UPM_UPM) AND SecuenciaID <> 0 AND ProveedorID=1 ORDER BY UPMID";
        ///*INYDES*/this.query = "SELECT UPMID, SecuenciaID, ProveedorID FROM UPM_MallaPuntos WHERE UPMID NOT IN (SELECT UPMID FROM UPM_UPM) AND SecuenciaID <> 0 AND ProveedorID=2 ORDER BY UPMID";
        ///*AMAREF*/this.query = "SELECT UPMID, SecuenciaID, ProveedorID FROM UPM_MallaPuntos WHERE UPMID NOT IN (SELECT UPMID FROM UPM_UPM) AND SecuenciaID <> 0 AND ProveedorID=3 ORDER BY UPMID";
        
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                listUPMID.add(rs.getInt("UPMID"));
            }
            listUPMID.add(0, null);
            st.close();
            rs.close();
            return listUPMID;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                "Error! al obtener los puntos UPM de levatamiento ",
                "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {

                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al obtener los puntos UPM de muestreo"
                    , "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public List<Integer> getUPMCapturados() {
        query = "SELECT UPMID FROM UPM_UPM";
        Connection conn = LocalConnection.getConnection();
        List<Integer> listUpm = new ArrayList<>();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                listUpm.add(rs.getInt("UPMID"));
            }
            st.close();
            rs.close();
            listUpm.add(0, null);
            return listUpm;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                "Error! al obtener los upm´s capturados",
                "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al obtener los upm's capturados"
                    , "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public List<CatEMalla> getMalla(int upmID) {
        query = "SELECT UPMID, Estado, PROYECTO, A, B, C, D, E, F, G, H, Municipio FROM UPM_MallaPuntos WHERE UPMID= " + upmID;
        Connection conn = LocalConnection.getConnection();
        List<Integer> listUPM = new ArrayList<>();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                malla.setUpmId(rs.getInt("UPMID"));
                malla.setEstado(rs.getString("Estado"));
                malla.setProyecto(rs.getString("PROYECTO"));
                malla.setA(rs.getByte("A"));
                malla.setB(rs.getByte("B"));
                malla.setC(rs.getByte("C"));
                malla.setD(rs.getByte("D"));
                malla.setE(rs.getByte("E"));
                malla.setF(rs.getByte("F"));
                malla.setG(rs.getByte("G"));
                malla.setH(rs.getByte("H"));
                malla.setMunicipio(rs.getString("Municipio"));
                listMalla.add(malla);
            }
            st.close();
            rs.close();
            return listMalla;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                "Error! al obtener la malla de muestreo ",
                "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en tenencias"
                 , "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public CEUPM getInformacionGeneral(int upmID) {
        query = "SELECT UPMID, TipoUpmID, FechaInicio, FechaFin, Predio, Paraje, TipoTenenciaID, InformacionContacto FROM "
        + "UPM_UPM WHERE UPMID= " + upmID;
        Connection conn = LocalConnection.getConnection();
        CEUPM ceUpm = new CEUPM();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                ceUpm.setUpmID(rs.getInt("UPMID"));
                ceUpm.setTipoUpmID(rs.getInt("TipoUpmID"));
                ceUpm.setFechaInicio(rs.getString("FechaInicio"));
                ceUpm.setFechaFin(rs.getString("FechaFin"));
                ceUpm.setPredio(rs.getString("Predio"));
                ceUpm.setParaje(rs.getString("Paraje"));
                ceUpm.setTenenciaID(rs.getInt("TipoTEnenciaID"));
                ceUpm.setInformacionContacto(rs.getInt("InformacionContacto"));
            }
            st.close();
            rs.close();
            return ceUpm;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                "Error! al obtener la información general del upm ",
                "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en información general del upm "
                    , "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public Integer getSecuenciaID(int upmID) {
        Integer secuenciaID = null;
        query = "SELECT UPMID, SecuenciaID FROM UPM_MallaPuntos WHERE UPMID= " + upmID;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                secuenciaID = rs.getInt(2);
            }
            return secuenciaID;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                "Error! al obtener la secuencia ",
                "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al obtener la secuencia "
                    , "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public List<CatETipoUPM> getTiposUPM() {
        query = "SELECT TipoUPMID, TipoUpm, Descripcion FROM CAT_TipoUPM";
        Connection conn = LocalConnection.getConnection();
        List<CatETipoUPM> tiposUpm = new ArrayList();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                CatETipoUPM tipoUpm = new CatETipoUPM();
                tipoUpm.setTipoUPMID(rs.getInt("TipoUPMID"));
                tipoUpm.setTipoUPM(rs.getString("TipoUpm"));
                tipoUpm.setDescripicion(rs.getString("Descripcion"));
                tiposUpm.add(tipoUpm);
            }
            st.close();
            rs.close();
            tiposUpm.add(0, null);
            return tiposUpm;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                "Error! al obtener el tipo de upm " + e.getClass().getName() + " : " + e.getMessage(),
                "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en tenencias"
                    , "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public List<CatETipoTenencia> getTiposTenencia() {
        List<CatETipoTenencia> listTenencias = new ArrayList();
        query = "SELECT TipoTenenciaID, Descripcion FROM CAT_TipoTenencia";
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                CatETipoTenencia tenencia = new CatETipoTenencia();
                tenencia.setTipoTenenciaID(rs.getInt("TipoTenenciaID"));
                tenencia.setDescripcion(rs.getString("Descripcion"));
                listTenencias.add(tenencia);
            }
            st.close();
            rs.close();
            listTenencias.add(0, null);
            return listTenencias;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                "Error! al obtener los tipos de tenencia",
                "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en tenencias"
                    , "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void insertDatosGeneralesUPM(CEUPM upm) {
        query = "INSERT INTO UPM_UPM (UPMID, FechaInicio, FechaFin, TipoUPMID, Predio, Paraje, "
        + "TipoTenenciaID, Accesible, informacionContacto) " + "VALUES(" + upm.getUpmID()
        + ", " + "'" + upm.getFechaInicio() + "'" + " , " + "'" + upm.getFechaFin() + "'" + ", " + upm.getTipoUpmID() + ", "
        + "'" + upm.getPredio() + "'" + ", " + "'" + upm.getParaje() + "'" + ", " + upm.getTenenciaID()
        + ", " + upm.getAccesible() + ", " + upm.getInformacionContacto() + ")";
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo guardar la información del upm "
                , "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en tenencias"
                    , "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void updateInformacionGeneral(CEUPM ceUpm) {
        query = "UPDATE UPM_UPM SET TipoUPMID= " + ceUpm.getTipoUpmID() + ", FechaInicio= '" + ceUpm.getFechaInicio() + "', FechaFin= '" + ceUpm.getFechaFin()
        + "', Predio= '" + ceUpm.getPredio() + "', Paraje= '" + ceUpm.getParaje() + "', TipoTenenciaID= " + ceUpm.getTenenciaID()
        + ", Accesible= " + ceUpm.getAccesible() + " WHERE UPMID= " + ceUpm.getUpmID();
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo actualizar la información general del upm"
                , "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al actualizar la información general del upm"
                    , "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void eliminarUPMInaccesible(CEUPM ceUpm){ 
       query = "UPDATE UPM_UPM SET GradosLatitud= NULL, MinutosLatitud= NULL, SegundosLatitud= NULL, GradosLongitud= NULL, " 
       + "MinutosLongitud= NULL, SegundosLongitud= NULL, Datum= NULL, ErrorPresicion= NULL, Azimut= NULL, Distancia= NULL, "
       + "TipoInaccesibilidadID= NULL, OtroTipoInaccesibilidad= NULL, ExplicacionInaccesibilidad= NULL "
       + "WHERE UPMID= " + ceUpm.getUpmID();
       Connection conn = LocalConnection.getConnection();
       try {
        Statement st = conn.createStatement();
        System.out.println(ceUpm.getUpmID());
        st.executeUpdate(query);
        conn.commit();
        st.close();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error! no se pudo eliminar la información de inaccesibilidad del upm"
            , "Conexion BD", JOptionPane.ERROR_MESSAGE);
    } finally {
        try {
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al eliminar la información de inaccesibilidad del upm"
                , "Conexion BD", JOptionPane.ERROR_MESSAGE);
        }
    }
}

public void actualizarDatosGeneralesUPM(CEUPM upm) {
    query = "UPDATE UPM_UPM SET FechaInicio= " + upm.getFechaInicio() + ", FechaFin= " + upm.getFechaFin() + ", "
    + "TipoUPMID= " + upm.getTipoUpmID() + ", Altitud= " + upm.getAltitud() + ", PendienteRepresentativa= " + upm.getPendienteRepresentativa() + ", "
    + "FisiografiaID= " + upm.getFisiografiaID()+ ", ExposicionID= " + upm.getExposicionID() + ", Predio=" + upm.getPredio() + ", "
    + "Paraje= " + upm.getParaje() + ", TipoTenenciaID=" + upm.getTenenciaID() + ", Accesible=" + upm.getAccesible() + ", GradosLatitud= " + upm.getGradosLatitud() + ", "
    + "MinutosLatitud=" + upm.getMinutosLatitud() + ", SegundosLatitud= " + upm.getSegundosLatitud() + ", GradosLongitud= " + upm.getGradosLongitud() + ", "
    + "MinutosLongitud= " + upm.getMinutosLongitud() + " ," + upm.getSegundosLongitud() + ",Datum=" + upm.getDatum() + "ErrorPresicion= " + upm.getErrorPresicion() + ", "
    + "Azimut= " + upm.getAzimut() + ", Distancia= " + upm.getDistancia() + ", TipoInaccesibilidadID= " + upm.getTipoInaccesibilidadID() + ", OtroTipoInaccesibilidad" + upm.getOtroTipoInaccesibilidad() + ""
    + "WHERE UPMID=" + upm.getUpmID();
    Connection conn = LocalConnection.getConnection();
    try {
        Statement st = conn.createStatement();
        st.executeUpdate(query);
        conn.commit();
        st.close();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error! no se pudo actualizar la información del upm "
            , "Conexion BD", JOptionPane.ERROR_MESSAGE);
    } finally {
        try {
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en tenencias"
                , "Conexion BD", JOptionPane.ERROR_MESSAGE);
        }
    }
}




public List<Object> getUpmData(int upmID) {
    query = "SELECT UPMID, FechaInicio, FechaFin, TipoUPMID, Altitud, PendienteRepresentativa, FisiografiaID, ExposicionID, "
    + "Predio, Paraje, TipoTenenciaID, Accesible FROM UPM_UPM WHERE UPMID= " + upmID;
    Connection conn = LocalConnection.getConnection();
    List<Object> upmData = new ArrayList<Object>();
    try {
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            upmData.add(rs.getInt("UPMID"));
            upmData.add(rs.getDate("FechaInicio"));
            upmData.add(rs.getDate("FechaFin"));
            upmData.add(rs.getInt("TipoUPMID"));
            upmData.add(rs.getFloat("Altitud"));
            upmData.add(rs.getInt("PendienteRepresentativa"));
            upmData.add(rs.getInt("FisiografiaID"));
            upmData.add(rs.getInt("EposicionID"));
            upmData.add(rs.getString("Predio"));
            upmData.add(rs.getString("Paraje"));
            upmData.add(rs.getInt("TipoTenenciaID"));
            upmData.add(rs.getBoolean("Accesible"));
        }
        st.close();
        rs.close();
        return upmData;
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error! no se pudo obtener la información del upm "
            , "Conexion BD", JOptionPane.ERROR_MESSAGE);
        return null;
    } finally {
        try {
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en tenencias"
                , "Conexion BD", JOptionPane.ERROR_MESSAGE);
        }
    }
}

public List<Object> getUpmInaccesible(int upmID) {
    query = "SELECT UPMID, GradosLatitud, MinutosLatitud, SegundosLatitud, GradosLongitud, "
    + "MinutosLongitud, SegundosLongitud, Datum, ErrorPresicion, Azimut, Distancia, TipoInaccesibilidadID, OtroTipoInaccesibilidad, "
    + "ExplicacionInaccesibilidad FROM UPM_UPM WHERE UPMID= " + upmID;

    Connection conn = LocalConnection.getConnection();
    List<Object> upmInaccesible = new ArrayList<>();
    try {
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            upmInaccesible.add(rs.getInt("UPMID"));
            upmInaccesible.add(rs.getInt("GradosLatitud"));
            upmInaccesible.add(rs.getInt("MinutosLatitud"));
            upmInaccesible.add(rs.getFloat("SegundosLatitud"));
            upmInaccesible.add(rs.getInt("GradosLongitud"));
            upmInaccesible.add(rs.getInt("MinutosLongitud"));
            upmInaccesible.add(rs.getFloat("SegundosLongitud"));
            upmInaccesible.add(rs.getString("Datum"));
            upmInaccesible.add(rs.getInt("ErrorPresicion"));
            upmInaccesible.add(rs.getInt("Azimut"));
            upmInaccesible.add(rs.getFloat("Distancia"));
            upmInaccesible.add(rs.getInt("TipoInaccesibilidadID"));
            upmInaccesible.add(rs.getString("OtroTipoInaccesibilidad"));
            upmInaccesible.add(rs.getString("ExplicacionInaccesibilidad"));
        }
        st.close();
        rs.close();
        return upmInaccesible;
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error! no se pudo obtener la información del upm "
            , "Conexion BD", JOptionPane.ERROR_MESSAGE);
        return null;
    } finally {
        try {
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en tenencias"
                , "Conexion BD", JOptionPane.ERROR_MESSAGE);
        }
    }
}

public List<CatETipoFisiografia> getTipoFisiografia() {
    query = "SELECT FisiografiaID, TipoFisiografia, Descripcion FROM CAT_TipoFisiografia";
    List<CatETipoFisiografia> listFisiografia = new ArrayList<>();
    Connection conn = LocalConnection.getConnection();
    try {
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            CatETipoFisiografia fisiografia = new CatETipoFisiografia();
            fisiografia.setFisiografiaID(rs.getInt("FisiografiaID"));
            fisiografia.setTipoFisiografia(rs.getString("TipoFisiografia"));
            fisiografia.setDescripcion(rs.getString("Descripcion"));
            listFisiografia.add(fisiografia);
        }
        listFisiografia.add(0, null);
        return listFisiografia;
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error! no se pudo obtener la información de los tipos de fisiografia "
            , "Conexion BD", JOptionPane.ERROR_MESSAGE);
        return null;
    } finally {
        try {
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en tipos de fisiografia"
                , "Conexion BD", JOptionPane.ERROR_MESSAGE);
        }
    }
}

public List<CatETipoExposicion> getTipoExposicion() {
    query = "SELECT ExposicionID, Clave, Descripcion FROM CAT_TipoExposicion";
    List<CatETipoExposicion> listExposicion = new ArrayList<>();
    Connection conn = LocalConnection.getConnection();
    try {
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            CatETipoExposicion exposicion = new CatETipoExposicion();
            exposicion.setExposicionID(rs.getInt("ExposicionID"));
            exposicion.setClave(rs.getString("Clave"));
            exposicion.setDescripcion(rs.getString("Descripcion"));
            listExposicion.add(exposicion);
        }
        st.close();
        rs.close();
        listExposicion.add(0, null);
        return listExposicion;
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error! no se pudo obtener la información de los tipos de exposición "
            , "Conexion BD", JOptionPane.ERROR_MESSAGE);
        return null;
    } finally {
        try {
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en tipos de exposicion"
                , "Conexion BD", JOptionPane.ERROR_MESSAGE);
        }
    }
}

public CEUPM getCaracteristicasUPM(int upmID) {
    query = "SELECT Altitud, PendienteRepresentativa, FisiografiaID, ExposicionID FROM UPM_UPM WHERE UPMID= " + upmID;
    Connection conn = LocalConnection.getConnection();
    CEUPM ceUpm = new CEUPM();
    try {
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            ceUpm.setAltitud(rs.getFloat("Altitud"));
            ceUpm.setPendienteRepresentativa(rs.getInt("PendienteRepresentativa"));
            ceUpm.setFisiografiaID(rs.getInt("FisiografiaID"));
            ceUpm.setExposicionID(rs.getInt("ExposicionID"));
        }
        rs.close();
        st.close();
        return ceUpm;
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error! no se pudo obtener la información de las características del UPM "
            , "Conexion BD", JOptionPane.ERROR_MESSAGE);
        return null;
    } finally {
        try {
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al obtener los datos de las características del upm"
                , "Conexion BD", JOptionPane.ERROR_MESSAGE);
        }
    }
}

public void updateCaracteristicasUPM(CEUPM ceupm) {
    query = "UPDATE  UPM_UPM SET Altitud= " + ceupm.getAltitud() + ", PendienteRepresentativa= " + ceupm.getPendienteRepresentativa()
    + ", FisiografiaID= " + ceupm.getFisiografiaID() + ", ExposicionID= " + ceupm.getExposicionID() + " WHERE UPMID= " + ceupm.getUpmID();
    Connection conn = LocalConnection.getConnection();
    try {
        Statement st = conn.createStatement();
        st.executeUpdate(query);
        conn.commit();
        st.close();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error! no se pudo modificar la información de caracteristicas de la UPM "
            , "Conexion BD", JOptionPane.ERROR_MESSAGE);
    } finally {
        try {
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la modificación de las caracteristicas de la UPM"
                , "Conexion BD", JOptionPane.ERROR_MESSAGE);
        }
    }
}

}
