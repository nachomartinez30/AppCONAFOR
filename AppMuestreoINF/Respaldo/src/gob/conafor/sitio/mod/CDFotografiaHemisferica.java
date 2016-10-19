package gob.conafor.sitio.mod;

import gob.conafor.conn.dat.LocalConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class CDFotografiaHemisferica {

    private String query;
    private CEFotografiaHemisferica ceFoto = new CEFotografiaHemisferica();

    public CEFotografiaHemisferica getDatosFotografia(int sitioID) {
        query = "SELECT SitioID, CoberturaArborea, TomaFotografia, Hora, DeclinacionMagnetica "
                + "FROM SITIOS_FotografiaHemisferica WHERE SitioID= " + sitioID;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                ceFoto.setSitioID(rs.getInt("SitioID"));
                ceFoto.setCoberturaArborea(rs.getInt("CoberturaArborea"));
                ceFoto.setTomaFotografia(rs.getInt("TomaFotografia"));
                ceFoto.setHora(rs.getString("Hora"));
                ceFoto.setDeclinacionMagnetica(rs.getInt("DeclinacionMagnetica"));
            }
            st.close();
            rs.close();
            return ceFoto;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al obtener los datos de fotografía hemisferica ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al obtener la deformacion fotografía hemisférica ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void insertFotografiaHemisferica(CEFotografiaHemisferica ceFoto) {
        query = "INSERT INTO SITIOS_FotografiaHemisferica(SitioID, CoberturaArborea, TomaFotografia, Hora, DeclinacionMagnetica)VALUES("
                + ceFoto.getSitioID() + ", " + ceFoto.getCoberturaArborea() + ", " + ceFoto.getTomaFotografia() + ", '" + ceFoto.getHora()
                + "', " + ceFoto.getDeclinacionMagnetica() + ")";
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo guardar la informacion de fotografía hemisférica ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos al insertar los datos de fotografía hemisférica ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void updateFotografiaHemisferica(CEFotografiaHemisferica ceFoto) {
        query = "UPDATE SITIOS_FotografiaHemisferica SET CoberturaArborea= " + ceFoto.getCoberturaArborea() + ", TomaFotografia= " + ceFoto.getTomaFotografia()
                + ", Hora= '" + ceFoto.getHora() + "', DeclinacionMagnetica= " + ceFoto.getDeclinacionMagnetica() + " WHERE SitioID= " + ceFoto.getSitioID();
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo modificar la informacion de fotografía hemisférica ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos al modificar los datos de fotografía hemisférica ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void deleteFotografiaHemisferica(int sitioID) {
        query = "DELETE FROM SITIOS_FotografiaHemisferica WHERE SitioID= " + sitioID;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo eliminar la informacion de fotografía hemisférica ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos al eliminar los datos de fotografía hemisférica ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
