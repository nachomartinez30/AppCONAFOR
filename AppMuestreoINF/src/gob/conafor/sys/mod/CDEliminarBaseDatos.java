package gob.conafor.sys.mod;

import gob.conafor.conn.dat.LocalConnection;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class CDEliminarBaseDatos {
    
     public void eliminarUPM() {
        String query = "DELETE FROM UPM_UPM";
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo eliminar la informacion de los UPM'S", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos al eliminar los datos de los UPM'S ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
     
     public void eliminarSitios(){
        String query = "DELETE FROM SITIOS_Sitio";
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo eliminar la informacion de los Sitios", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos al eliminar los datos de los sitios ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
     }
}
