package gob.conafor.sitio.mod;

import gob.conafor.conn.dat.LocalConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class CDObservaciones {

    private String query;

    public void insertObservaciones(CEObservaciones observaciones) {
        query = "INSERT or replace  INTO SITIOS_Observaciones(SitioID, FormatoID, Observaciones)VALUES(" + observaciones.getSitioID() + ", " + observaciones.getFormatoID()
                + ", '" + observaciones.getObservaciones() + "')";
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
        e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudieron guardar las observaciones "
                    + e.getClass().getName() + " : " + e.getMessage(), "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
            e.printStackTrace();
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos al guardar las observaciones",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void updateObservaciones(CEObservaciones observaciones) {
        query = "UPDATE SITIOS_Observaciones SET Observaciones= '" + observaciones.getObservaciones() + "' WHERE SitioID= " + observaciones.getSitioID()
                + " AND FormatoID= " + observaciones.getFormatoID();
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
        e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudieron modificar las observaciones ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
            e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la modificacion de observaciones ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void deleteObservaciones(CEObservaciones observaciones) {
        query = "DELETE FROM SITIOS_Observaciones WHERE SitioID= " + observaciones.getSitioID() + " AND FormatoID= " + observaciones.getFormatoID();
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
        e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudieron eliminar las observaciones ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
            e.printStackTrace();
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos  al eliminar las observaciones",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public String getObservaciones(CEObservaciones observaciones) {
        query = "SELECT Observaciones FROM SITIOS_Observaciones WHERE SitioID= " + observaciones.getSitioID() + " AND FormatoID= " + observaciones.getFormatoID();
        System.out.println("observaciones query\t"+query);
        Connection conn = LocalConnection.getConnection();
        String texto = null;
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                texto = rs.getString("Observaciones");
            }
            return texto;
        } catch (SQLException e) {
        e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! al obtener las observaciones ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
            e.printStackTrace();
                JOptionPane
                        .showMessageDialog(null,
                                "Error! al cerrar la base de datos al obtener las observaciones",
                                "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
