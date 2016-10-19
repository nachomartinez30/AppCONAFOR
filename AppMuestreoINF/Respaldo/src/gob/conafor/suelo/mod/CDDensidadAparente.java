package gob.conafor.suelo.mod;

import gob.conafor.conn.dat.LocalConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class CDDensidadAparente {

    private String query;
    private CEDensidadAparente ceDensidad = new CEDensidadAparente();

    public CEDensidadAparente getDensidadAparente(int sitioID) {
        query = "SELECT DensidadAparenteID, SitioID, PofundidadReal, DiametroCilindro, VolumenMaterial, PesoTotalSuelo, PesoTotalMuestra, Observaciones FROM SUELO_DencidadAparente"
                + " WHERE SitioID= " + sitioID;
        Connection conn = LocalConnection.getConnection();

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                this.ceDensidad.setDensidadAparenteID(rs.getInt("DensidadAparenteID"));
                this.ceDensidad.setSitioID(rs.getInt("SitioID"));
                this.ceDensidad.setProfundidadReal(rs.getFloat("ProfundidadReal"));
                this.ceDensidad.setDiametroCilindro(rs.getFloat("DiametroCilindro"));
                this.ceDensidad.setVolumen(rs.getFloat("VolumenMaterial"));
                this.ceDensidad.setPesoTotalSuelo(rs.getFloat("PesoTotalSuelo"));
                this.ceDensidad.setPesoTotalMuestra(rs.getFloat("PesoTotalMuestra"));
                this.ceDensidad.setObservaciones(rs.getString("Observaciones"));
            }
            return this.ceDensidad;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al obtener los datos de densidad aparente ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al obtener la información de densidad aparente ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void insertDensidadAparente(CEDensidadAparente ceDensidad) {
        query = "INSERT INTO SUELO_DensidadAparente(SitioID, ProfundidadReal, DiametroCilindro, VolumenMaterial, "
                + "PesoTotalSuelo, PesoTotalMuestra, Observaciones)VALUES(" + ceDensidad.getSitioID() + ", " + ceDensidad.getProfundidadReal() + ", " + ceDensidad.getDiametroCilindro()
                + ", " + ceDensidad.getVolumen() + ", " + ceDensidad.getPesoTotalSuelo() + ", " + ceDensidad.getPesoTotalMuestra() + ", '" + ceDensidad.getObservaciones() + "')";
        Connection conn = LocalConnection.getConnection();

        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo guardar la informacion de densidad aparente ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos al insertar la información de dencidad aparente ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void updateDensidadAparente(CEDensidadAparente ceDensidad) {
        query = "UPDATE SUELO_DensidadAparente SET ProfundidadReal= " + ceDensidad.getProfundidadReal() + ", DiametroCilindro= " + ceDensidad.getDiametroCilindro()
                + ", VolumenMaterial= " + ceDensidad.getVolumen() + ", PesoTotalSuelo= " + ceDensidad.getPesoTotalSuelo() + ", PesoTotalMuestra= " + ceDensidad.getPesoTotalMuestra()
                + ", Observaciones= " + ceDensidad.getObservaciones() + " WHERE SitioID= " + ceDensidad.getSitioID();

        Connection conn = LocalConnection.getConnection();

        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo modificar la información de densidad aparente ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la modificación de densidad aparente ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void deleteDensidadAparente(int sitioID) {
        query = "DELETE FROM SUELO_DensidadAparente WHERE SitioID= " + sitioID;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo eliminar la información de densidad aparente ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos  al eliminar desndiad aparente",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
