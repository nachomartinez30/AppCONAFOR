package gob.conafor.sitio.mod;

import gob.conafor.conn.dat.LocalConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class CDCoberturaSuelo {

    private String query;

    public CECoberturaSuelo getDatosCoberturaSitio(int sitioID) {
        query = "SELECT SitioID, Gramineas, Helechos, Musgos, Liquenes, Hierbas, Roca, SueloDesnudo, "
                + "Hojarasca, Grava, Otros FROM SITIOS_CoberturaSuelo WHERE SitioID= " + sitioID;
        Connection conn = LocalConnection.getConnection();
        CECoberturaSuelo ceCobertura = new CECoberturaSuelo();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                ceCobertura.setSitioID(rs.getInt("SitioID"));
                ceCobertura.setGramineas(rs.getInt("Gramineas"));
                ceCobertura.setHelechos(rs.getInt("Helechos"));
                ceCobertura.setMusgos(rs.getInt("Musgos"));
                ceCobertura.setLiquenes(rs.getInt("Liquenes"));
                ceCobertura.setHierbas(rs.getInt("Hierbas"));
                ceCobertura.setRoca(rs.getInt("Roca"));
                ceCobertura.setSueloDesnudo(rs.getInt("SueloDesnudo"));
                ceCobertura.setHojarasca(rs.getInt("Hojarasca"));
                ceCobertura.setGrava(rs.getInt("Grava"));
                ceCobertura.setOtros(rs.getInt("Otros"));
            }
            st.close();
            st.close();
            return ceCobertura;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al obtener la cobertura del suelo en sitio ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en cobertura de suelo", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void insertCoberturaSuelo(CECoberturaSuelo coberturaSuelo) {
        query = "INSERT or replace INTO SITIOS_CoberturaSuelo (SitioID, Gramineas, Helechos, Musgos, Liquenes, Hierbas, Roca, SueloDesnudo, "
                + "Hojarasca, Grava, Otros) VALUES(" + coberturaSuelo.getSitioID() + ", "
                + coberturaSuelo.getGramineas() + ", " + coberturaSuelo.getHelechos() + ", "
                + coberturaSuelo.getMusgos() + ", " + coberturaSuelo.getLiquenes() + ", " + coberturaSuelo.getHierbas()
                + ", " + coberturaSuelo.getRoca() + ", " + coberturaSuelo.getSueloDesnudo() + ", "
                + coberturaSuelo.getHojarasca() + ", " + coberturaSuelo.getGrava() + ", " + coberturaSuelo.getOtros()
                + ")";
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            JOptionPane.showMessageDialog(null, "Combertura de suelo insertada correctamente");
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo guardar la información de cobertura del suelo en sitio", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en cobertura del suelo en sitio", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void updateCoberturaSuelo(CECoberturaSuelo ceCobertura) {
        query = "UPDATE SITIOS_CoberturaSuelo SET Gramineas= " + ceCobertura.getGramineas() + ", Helechos= " + ceCobertura.getHelechos() + ", Musgos= " + ceCobertura.getMusgos()
                + ", Liquenes= " + ceCobertura.getLiquenes() + ", Hierbas= " + ceCobertura.getHierbas() + ", Roca= " + ceCobertura.getRoca() + ", SueloDesnudo= " + ceCobertura.getSueloDesnudo()
                + ", Hojarasca= " + ceCobertura.getHojarasca() + ", Grava=" + ceCobertura.getGrava() + ", Otros= " + ceCobertura.getOtros() + " WHERE SitioID= " + ceCobertura.getSitioID();
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            //System.out.println(query);
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo modificar la información de cobertura del suelo en sitio", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al eliminar cobertura del suelo en sitio", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void deleteCoberturaSuelo(int sitioID) {
        query = "DELETE FROM SITIOS_CoberturaSuelo WHERE SitioID= " + sitioID;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo eliminar la información de cobertura del suelo en sitio", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en  al eliminar cobertura del suelo en sitio", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
