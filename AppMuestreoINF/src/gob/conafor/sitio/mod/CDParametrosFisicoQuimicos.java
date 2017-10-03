package gob.conafor.sitio.mod;

import gob.conafor.conn.dat.LocalConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class CDParametrosFisicoQuimicos {

    private String query;
    private CEParametrosFisicoQuimicos ceParametros = new CEParametrosFisicoQuimicos();
    
    public CEParametrosFisicoQuimicos getDatosParametrosFQ(int sitioID) {
        query = "SELECT SitioID, TipoAgua, Salinidad, Temperatura, ConductividadElectrica, Ph, PotencialRedox, "
                + "Profundidad, Observaciones FROM SITIOS_ParametrosFisicoQuimicos WHERE SitioID= " + sitioID;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                this.ceParametros.setTipoAgua(rs.getInt("TipoAgua"));
                this.ceParametros.setSalinidad(rs.getFloat("Salinidad"));
                this.ceParametros.setTemperaturaAgua(rs.getFloat("Temperatura"));
                this.ceParametros.setConductividadElectrica(rs.getFloat("ConductividadElectrica"));
                this.ceParametros.setPh(rs.getFloat("Ph"));
                this.ceParametros.setPotencialRedox(rs.getFloat("PotencialRedox"));
                this.ceParametros.setProfundidad(rs.getFloat("Profundidad"));
                this.ceParametros.setObservaciones(rs.getString("Observaciones"));
            }
            st.close();
            rs.close();
            return ceParametros;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al obtener los datos de parametros físico químicos ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al obtener los datos de parámetros físico quimicos ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void inserParametrosFQ(CEParametrosFisicoQuimicos ceParametros) {
        query = "INSERT OR REPLACE INTO SITIOS_ParametrosFisicoQuimicos(SitioID, TipoAgua, Salinidad, Temperatura, ConductividadElectrica, Ph, PotencialRedox, "
                + "Profundidad, Observaciones)VALUES(" + ceParametros.getSitioID() + ", " + ceParametros.getTipoAgua() + ", " + ceParametros.getSalinidad() + ", " + ceParametros.getTemperaturaAgua()
                + ", " + ceParametros.getConductividadElectrica() + ", " + ceParametros.getPh() + ", " + ceParametros.getPotencialRedox() + ", " + ceParametros.getProfundidad()
                + ", '" + ceParametros.getObservaciones() + "')";
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            JOptionPane.showMessageDialog(null, "Se inserto correctamente los parametros fisico-quimicos");
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo guardar la información  de parámetros físico químicos", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos al insertar datos parámetros fisíco químicos ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void updateParametrosFQ(CEParametrosFisicoQuimicos ceParametros) {
        query = "UPDATE SITIOS_ParametrosFisicoQuimicos SET TipoAgua= " + ceParametros.getTipoAgua() + ", Salinidad= " + ceParametros.getSalinidad() + ", Temperatura= " + ceParametros.getTemperaturaAgua()
                + ", ConductividadElectrica= " + ceParametros.getConductividadElectrica() + ", Ph= " + ceParametros.getPh() + ", PotencialRedox= "
                + ceParametros.getPotencialRedox() + ", Profundidad= " + ceParametros.getProfundidad()
                + ", Observaciones= '" + ceParametros.getObservaciones() + "' WHERE SitioID= " + ceParametros.getSitioID();
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo modificar la información de parámetros físico químicos ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la modificación de parámetros físico químicos ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void deleteParametrosFQ(int sitioID) {
        query = "DELETE FROM SITIOS_ParametrosFisicoQuimicos WHERE SitioID = " + sitioID;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                    null, "Error! al cerrar la base de datos  al eliminar parámetros fisico químicos",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos  al eliminar parámetros fisico químicos",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
