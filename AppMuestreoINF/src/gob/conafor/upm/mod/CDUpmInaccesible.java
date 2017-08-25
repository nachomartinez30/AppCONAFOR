package gob.conafor.upm.mod;

import gob.conafor.conn.dat.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class CDUpmInaccesible {

    private String query;

    public List<CatETipoInaccesibilidad> getTipoInaccesibilidad() {
        List<CatETipoInaccesibilidad> listInaccesibilidad = new ArrayList<>();
        query = "SELECT TipoInaccesibilidadID, Clave, Tipo, Descripcion FROM CAT_TipoInaccesibilidad";
        Connection conn = LocalConnectionCat.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                CatETipoInaccesibilidad ti = new CatETipoInaccesibilidad();
                ti.setTipoInaccesibilidadID(rs.getInt("TipoInaccesibilidadID"));
                ti.setClave(rs.getString("Clave"));
                ti.setTipo(rs.getString("Tipo"));
                ti.setDescripcion(rs.getString("Descripcion"));
                listInaccesibilidad.add(ti);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener los datos de tipo de inaccesibilidad ",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en datos en tipos de inaccesibilidad"
                        + e.getClass().getName() + " : " + e.getMessage(), "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        listInaccesibilidad.add(0, null);
        return listInaccesibilidad;
    }

    public CEUPM getUPMInaccesible(int upmID) {
        query = "SELECT UPMID, TipoUPMID, GradosLatitud, MinutosLatitud, SegundosLatitud, GradosLongitud, MinutosLongitud, "
                + "SegundosLongitud, Datum, ErrorPresicion, Azimut, Distancia, TipoInaccesibilidadID, OtroTipoInaccesibilidad, "
                + "ExplicacionInaccesibilidad FROM UPM_UPM WHERE UPMID = " + upmID;
        Connection conn = LocalConnection.getConnection();
        CEUPM ceInaccesible = new CEUPM();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                ceInaccesible.setTipoUpmID(rs.getInt("TipoUPMID"));
                ceInaccesible.setGradosLatitud(rs.getInt("GradosLatitud"));
                ceInaccesible.setMinutosLatitud(rs.getInt("MinutosLatitud"));
                ceInaccesible.setSegundosLatitud(rs.getFloat("SegundosLatitud"));
                ceInaccesible.setGradosLongitud(rs.getInt("GradosLongitud"));
                ceInaccesible.setMinutosLongitud(rs.getInt("MinutosLongitud"));
                ceInaccesible.setSegundosLongitud(rs.getFloat("SegundosLongitud"));
                ceInaccesible.setDatum(rs.getString("Datum"));
                ceInaccesible.setErrorPresicion(rs.getInt("ErrorPresicion"));
                ceInaccesible.setAzimut(rs.getInt("Azimut"));
                ceInaccesible.setDistancia(rs.getFloat("Distancia"));
                ceInaccesible.setTipoInaccesibilidadID(rs.getInt("TipoInaccesibilidadID"));
                ceInaccesible.setOtroTipoInaccesibilidad(rs.getString("OtroTipoInaccesibilidad"));
                ceInaccesible.setExplicacionInaccesibilidad(rs.getString("ExplicacionInaccesibilidad"));
            }
            st.close();
            rs.close();
            return ceInaccesible;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo obtener información de inaccesibilidad del UPM ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al obtener datos de inaccesibilidad del upm", "Conexion BD", JOptionPane.ERROR_MESSAGE);
                return null;
            }
        }
    }

    public void updateInaccesibilidadUPM(CEUPM upm) {
        query = "UPDATE UPM_UPM SET GradosLatitud= " + upm.getGradosLatitud() + ", MinutosLatitud= " + upm.getMinutosLatitud()
                + ", SegundosLatitud= " + upm.getSegundosLatitud() + ", GradosLongitud= " + upm.getGradosLongitud() + ", MinutosLongitud= "
                + upm.getMinutosLongitud() + ", SegundosLongitud= " + upm.getSegundosLongitud() + ", Datum= " + "'" + upm.getDatum() + "'" + ", ErrorPresicion= "
                + upm.getErrorPresicion() + ", Azimut= " + upm.getAzimut() + ", Distancia= " + upm.getDistancia() + ", TipoInaccesibilidadID= "
                + upm.getTipoInaccesibilidadID() + ", ExplicacionInaccesibilidad= "
                + "'" + upm.getExplicacionInaccesibilidad() + "'" + " WHERE UPMID= " + upm.getUpmID();
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
            JOptionPane.showMessageDialog(null, "Se anexo la información de inaccesibilidad del UPM", "UPM Inaccesible", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo agregar la informacion de inaccesibilidad de la UPM"
                    , "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos de inaccesibilidad de la UPM"
                       , "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void updateInaccesibilidadUPMOtro(CEUPM upm) {
        query = "UPDATE UPM_UPM SET GradosLatitud= " + upm.getGradosLatitud() + ", MinutosLatitud= " + upm.getMinutosLatitud()
                + ", SegundosLatitud= " + upm.getSegundosLatitud() + ", GradosLongitud= " + upm.getGradosLongitud() + ", MinutosLongitud= "
                + upm.getMinutosLongitud() + ", SegundosLongitud= " + upm.getSegundosLongitud() + ", Datum= " + "'" + upm.getDatum() + "'" + ", ErrorPresicion= "
                + upm.getErrorPresicion() + ", Azimut= " + upm.getAzimut() + ", Distancia= " + upm.getDistancia() + ", TipoInaccesibilidadID= "
                + upm.getTipoInaccesibilidadID() + ", OtroTipoInaccesibilidad= " + "'" + upm.getOtroTipoInaccesibilidad() + "'" + ", ExplicacionInaccesibilidad= "
                + "'" + upm.getExplicacionInaccesibilidad() + "'" + " WHERE UPMID= " + upm.getUpmID();
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
            JOptionPane.showMessageDialog(null, "Se anexo la información de inaccesibilidad del UPM", "UPM Inaccesible", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo agregar la informacion de inaccesibilidad de la UPM"
                    , "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos de inaccesibilidad de la UPM"
                        , "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void deleteInaccesibilidadUPM(CEUPM upm) {
        query = "UPDATE UPM_UPM SET GradosLatitud= NULL, MinutosLatitud= NULL, SegundosLatitud= NULL, GradosLongitud= NULL, MinutosLongitud= NULL"
                + ", SegundosLongitud= NULL, Datum= NULL, Azimut= NULL, Distancia= NULL, TipoInaccesibilidadID= NULL, OtroTipoInaccesibilidad= NULL"
                + ", ExplicacionInaccesibilidad= NULL WHERE UPMID= " + upm.getUpmID();
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo eliminar la informacion de inaccesibilidad de la UPM"
                    , "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al eliminar la inaccesibilidad de la UPM"
                       , "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}
