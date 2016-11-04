package gob.conafor.sitio.mod;

import gob.conafor.conn.dat.LocalConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class CDTransponder {

    private String query;
    private CETransponder ceTransponder = new CETransponder();
    
    public CETransponder getTransponder(int sitioID) {
        query = "SELECT SitioID, TipoColocacionID, Especifique, observaciones FROM SITIOS_Transponder "
                + " WHERE SitioID= " + sitioID;
        Connection conn = LocalConnection.getConnection();

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                this.ceTransponder.setTipoColocacionID(rs.getInt("TipoColocacionID"));
                this.ceTransponder.setEspecifique(rs.getString("Especifique"));
                this.ceTransponder.setObservaciones(rs.getString("observaciones"));
            }
            rs.close();
            st.close();            
            return this.ceTransponder;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al obtener los datos de transponder ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos al obtener datos de transponder ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void insertDatosTransponder(CETransponder ceTransponder) {
        query = "INSERT INTO SITIOS_Transponder(SitioID, TipoColocacionID, Especifique, Observaciones)VALUES(" + ceTransponder.getSitioID()
                + ", " + ceTransponder.getTipoColocacionID() + ", '" + ceTransponder.getEspecifique() + "', '" + ceTransponder.getObservaciones() + "')";
        Connection conn = LocalConnection.getConnection();

        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo guardar la informacion de transponder ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos al insertar datos de transponder ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void updateDatosTransponder(CETransponder ceTransponder) {
        query = "UPDATE SITIOS_Transponder SET TipoColocacionID= " + ceTransponder.getTipoColocacionID() + ", Especifique= '" + ceTransponder.getEspecifique()
                + "', Observaciones= '" + ceTransponder.getObservaciones() + "' WHERE SitioID=" + ceTransponder.getSitioID();
        Connection conn = LocalConnection.getConnection();
       // System.out.println(query);
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
           // e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo modificar la información de transponder ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la modificación de transponder", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void deleteDatosTransponder(int sitioID) {
        query = "DELETE FROM SITIOS_Transponder WHERE SitioID =" + sitioID;
        Connection conn = LocalConnection.getConnection();
        
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo eliminar la información del transponder ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos  al eliminar trasnpoder",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
}
