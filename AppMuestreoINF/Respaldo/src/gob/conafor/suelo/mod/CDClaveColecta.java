
package gob.conafor.suelo.mod;

import gob.conafor.conn.dat.LocalConnection;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class CDClaveColecta {

    private String query;

    public void asignarClaveSuelo(int sitioID, String claveColecta, String nombreTabla, String nombreCampo, int valor) {
        query = "UPDATE " + nombreTabla + " SET ClaveColecta= " + claveColecta + " WHERE SitioID= " + sitioID + " AND " + nombreCampo + "= " + valor;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo asignar la clave de colecta de suelo", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al asignar la clave de colecta de suelo ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
