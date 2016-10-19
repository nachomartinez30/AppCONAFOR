package gob.conafor.suelo.mod;

import gob.conafor.conn.dat.LocalConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class CDVarillasErosion {

    private String query;

    public List<CEVarillaErosion> getDatosVarillas(int sitioID) {
        query = "SELECT SitioID, NoVarilla, Azimut, Distancia, Profundidad FROM SUELO_VarillaErosion WHERE SitioID= " + sitioID + " ORDER BY NoVarilla ASC";
        Connection conn = LocalConnection.getConnection();
        List<CEVarillaErosion> listVarilla = new ArrayList<>();

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                CEVarillaErosion varilla = new CEVarillaErosion();
                varilla.setNoVarilla(rs.getInt("NoVarilla"));
                varilla.setAzimut(rs.getInt("Azimut"));
                varilla.setDistancia(rs.getFloat("Distancia"));
                varilla.setProfundidad(rs.getFloat("Profundidad"));
                listVarilla.add(varilla);
            }
            return listVarilla;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener los datos de varillas de erosion ",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en datos de varillas de erosion ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void insertDatosVarillas(CEVarillaErosion ceVarilla) {
        query = "INSERT INTO SUELO_VarillaErosion(SitioID, NoVarilla, Azimut, Distancia, Profundidad)VALUES(" + ceVarilla.getSitioID()
                + ", " + ceVarilla.getNoVarilla() + ", " + ceVarilla.getAzimut() + ", " + ceVarilla.getDistancia() + ", " + ceVarilla.getProfundidad() + ")";
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo guardar la informacion de varillas de erosion ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos al insertar datos de varillas de  ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void updateDatosVarillas(CEVarillaErosion ceVarilla) {
        query = "UPDATE SUELO_VarillaErosion SET Azimut= " + ceVarilla.getAzimut() + ", Distancia= " + ceVarilla.getDistancia()
                + ", Profundidad= " + ceVarilla.getProfundidad() + " WHERE SitioID= " + ceVarilla.getSitioID() + " AND NoVarilla= " + ceVarilla.getNoVarilla();
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo modificar la información de varillas de erosion ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la modificación de varillas de erosion ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void deleteDatosVarillas(int sitioID) {
        query = "DELETE FROM SUELO_VarillaErosion WHERE SitioID= " + sitioID;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo eliminar la información de varillas de erosion ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos  al eliminar los datos de varillas de erosion",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
