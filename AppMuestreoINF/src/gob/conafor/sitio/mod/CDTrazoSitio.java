package gob.conafor.sitio.mod;

import gob.conafor.conn.dat.LocalConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class CDTrazoSitio {
    
    private String query;

    public void updateTrazoSitio(CETrazoSitio trazo) {

        query = "UPDATE SITIOS_Sitio SET HipsometroBrujula= " + trazo.getHipsometroBrujula() + ", CintaClinometroBrujula= " + trazo.getCintaClinometroBrujula()
                + ", Cuadrante1= " + trazo.getCuadrante1() + ", Cuadrante2= " + trazo.getCuadrante2() + ", Cuadrante3= " + trazo.getCuadrante3()
                + ", Cuadrante4= " + trazo.getCuadrante4() + ", Distancia1= " + trazo.getDistancia1() + ", Distancia2= " + trazo.getDistancia2()
                + ", Distancia3= " + trazo.getDistancia3() + ", Distancia4= " + trazo.getDistancia4() + " WHERE SitioID= " + trazo.getSitioID();
        Connection conn = LocalConnection.getConnection();
        try {
           Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
            JOptionPane.showMessageDialog(null, "Trazo guardado correctamente ", "Trazo del sitio", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo agregar la información de  cuadrante del sitio ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al agregar datos del cuadrante del sitio ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public CESitio getTrazoSitio(CESitio sitio) {
        CESitio trazo = new CESitio();

        query = "SELECT SitioID, UPMID, Sitio, HipsometroBrujula, CintaClinometroBrujula, Cuadrante1, Cuadrante2, Cuadrante3, "
                + "Cuadrante4, Distancia1, Distancia2, Distancia3, Distancia4 FROM SITIOS_Sitio WHERE SitioID= " + sitio.getSitioID();
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                trazo.setSitioID(rs.getInt("SitioID"));
                trazo.setUpmID(rs.getInt("UPMID"));
                trazo.setSitio(rs.getInt("Sitio"));
                trazo.setHipsometroBrujula(rs.getInt("HipsometroBrujula"));
                trazo.setCintaClinometroBrujula(rs.getInt("CintaClinometroBrujula"));
                trazo.setCuadrante1(rs.getInt("Cuadrante1"));
                trazo.setCuadrante2(rs.getInt("Cuadrante2"));
                trazo.setCuadrante3(rs.getInt("Cuadrante3"));
                trazo.setCuadrante4(rs.getInt("Cuadrante4"));
                trazo.setDistancia1(rs.getFloat("Distancia1"));
                trazo.setDistancia2(rs.getFloat("Distancia2"));
                trazo.setDistancia3(rs.getFloat("Distancia3"));
                trazo.setDistancia4(rs.getFloat("Distancia4"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener los datos del trazo del sitio ",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {

                conn.close();

            } catch (SQLException e) {

                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en datos del sitio"
                        , "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        return trazo;
    }
    
    public int[] validarCapturaTrazo(CESitio sitio) {
        query = "SELECT HipsometroBrujula, CintaClinometroBrujula FROM SITIOS_Sitio WHERE sitioID= " + sitio.getSitioID();
        Connection conn = LocalConnection.getConnection();
        int[] datosTrazo = {0, 0};
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                datosTrazo[0] = rs.getInt("HipsometroBrujula");
                datosTrazo[1] = rs.getInt("CintaClinometroBrujula");
            }
            st.close();
            rs.close();
            
            return datosTrazo;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al vaidar los datos de trazo del sitio ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al validar la captura del trazo del sitio ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
