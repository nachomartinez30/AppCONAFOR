package gob.conafor.taxonomia.mod;

import gob.conafor.conn.dat.LocalConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class CDDanioVM {

    private String query;

    public List<CEDanioSeveridad> getDanio(int vegetacionMenorID) {
        List<CEDanioSeveridad> listDanio = new ArrayList<>();
        query = "SELECT VegetacionMenorID, NumeroDanio, AgenteDanioID, SeveridadID  FROM VEGETACIONMENOR_DanioSeveridad WHERE VegetacionMenorID= " + vegetacionMenorID + " ORDER BY NumeroDanio ASC";
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
                    "Error! al obtener los datos de daño de vegetación menor ",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en datos de daño de vegetación menor"
                      , "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        return listDanio;
    }

    public void insertDanio(CEDanioSeveridad ceDanio) {
        query = "INSERT INTO VEGETACIONMENOR_DanioSeveridad(VegetacionMenorID, NumeroDanio, AgenteDanioID, SeveridadID)VALUES(" + ceDanio.getSeccionID()
                + " ," + ceDanio.getNumeroDanio() + " ," + ceDanio.getAgenteDanioID() + " ," + ceDanio.getSeveridadID() + ")";
       
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo guardar la informacion de daños de vegetación menor"
                   , "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos al insertar datos de daños de vegetación menor ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void updateDanio(CEDanioSeveridad ceDanio) {
        query = "UPDATE VEGETACIONMENOR_DanioSeveridad SET AgenteDanioID= " + ceDanio.getAgenteDanioID()
                + ", SeveridadID= " + ceDanio.getSeveridadID() + " WHERE VegetacionMenorID= " + ceDanio.getSeccionID()
                + " AND NumeroDanio= " + ceDanio.getNumeroDanio();
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo modificar la información de vegetación menor ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la modificacion de vegetación menor ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void deleteDanio(int seccionID) {
        query = "DELETE FROM VEGETACIONMENOR_DanioSeveridad WHERE VegetacionMenorID= " + seccionID;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo eliminar la información de vegetación menor ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos  al eliminar el daño de vegetación menor ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
