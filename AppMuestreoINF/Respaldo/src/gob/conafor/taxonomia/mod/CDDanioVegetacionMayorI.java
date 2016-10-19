package gob.conafor.taxonomia.mod;

import gob.conafor.conn.dat.LocalConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class CDDanioVegetacionMayorI {
    
    private String query;
    
    public List<CEDanioSeveridad> getDanio(int vegetacionMayorID) {
        List<CEDanioSeveridad> listDanio = new ArrayList<>();
        query = "SELECT VegetacionMayorID, NumeroDanio, AgenteDanioID, SeveridadID  FROM VEGETACIONMAYORI_DanioSeveridad WHERE VegetacionMayorID= " + vegetacionMayorID + " ORDER BY NumeroDanio ASC";
        Connection conn = LocalConnection.getConnection();

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                CEDanioSeveridad danio = new CEDanioSeveridad();
                danio.setNumeroDanio(rs.getInt("NumeroDanio"));
                danio.setAgenteDanioID(rs.getInt("AgenteDanioID"));
                danio.setSeveridadID(rs.getInt("SeveridadID"));
                listDanio.add(danio);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener los datos de daño de vegetación mayor individual ",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en datos de daño de vegetación mayor individual "
                       , "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        return listDanio;
    }

    public void insertDanio(CEDanioSeveridad ceDanio) {
        query = "INSERT INTO VEGETACIONMAYORI_DanioSeveridad(VegetacionMayorID, NumeroDanio, AgenteDanioID, SeveridadID)VALUES(" + ceDanio.getSeccionID()
                + " ," + ceDanio.getNumeroDanio() + " ," + ceDanio.getAgenteDanioID() + " ," + ceDanio.getSeveridadID() + ")";
        Connection conn = LocalConnection.getConnection();

        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo guardar la informacion de daños de vegetación mayor individual"
                   , "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos al insertar datos de daños de vegetación mayor individual ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void updateDanio(CEDanioSeveridad ceDanio) {
        query = "UPDATE VEGETACIONMAYORI_DanioSeveridad SET AgenteDanioID= " + ceDanio.getAgenteDanioID()
                + ", SeveridadID= " + ceDanio.getSeveridadID() + " WHERE VegetacionMayorID= " + ceDanio.getSeccionID()
                + " AND NumeroDanio= " + ceDanio.getNumeroDanio();
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo modificar la información de daño de vegetación mayor individual "
                    , "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la modificacion de daño de vegetación mayor individual "
                        , "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void deleteDanio(int seccionID) {
        query = "DELETE FROM VEGETACIONMAYORI_DanioSeveridad WHERE VegetacionMayorID= " + seccionID;
        Connection conn = LocalConnection.getConnection();

        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo eliminar la información de vegetación mayor individual "
                    , "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos  al eliminar el daño de vegetación mayor individual ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
 
}
