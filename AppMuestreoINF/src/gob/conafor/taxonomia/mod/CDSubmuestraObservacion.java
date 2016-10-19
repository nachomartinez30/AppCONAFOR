package gob.conafor.taxonomia.mod;

import gob.conafor.conn.dat.LocalConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class CDSubmuestraObservacion {
    private String query;
    
    public CESubmuestraObservaciones getRegistroSubmuestra(int sitioID) {
        query = "SELECT SitioID, Observaciones FROM SUBMUESTRA_Observaciones WHERE SitioID= " + sitioID;
        Connection conn = LocalConnection.getConnection();
        CESubmuestraObservaciones ceSubmuestra = new CESubmuestraObservaciones();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
               ceSubmuestra.setObservaciones(rs.getString("Observaciones"));
            }
            return ceSubmuestra;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al obtener un registro de observaciones submuestra "
                   , "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane
                        .showMessageDialog(null,
                                "Error! al cerrar la base de datos en datos de registro de observaciones submuestra",
                                "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void insertSubmuestra(CESubmuestraObservaciones ceSubmuestra) {
        query = "INSERT INTO SUBMUESTRA_Observaciones(SitioID, Observaciones)VALUES(" + ceSubmuestra.getSitioID() + ", " + ceSubmuestra.getObservaciones() + ")";
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo crear una observaciones de submuestra ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos al crear observaciones de submuestra ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
     
    public void updateTroza(CESubmuestraObservaciones ceSubmuestra) {
        query = "UPDATE SUBMUESTRA_Observaciones SET Observaciones= " + ceSubmuestra.getObservaciones() + " WHERE SitioID= " + ceSubmuestra.getSitioID() ;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo modificar la información de observaciones de submuestra ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la modificación de observaciones de submuestra ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}
