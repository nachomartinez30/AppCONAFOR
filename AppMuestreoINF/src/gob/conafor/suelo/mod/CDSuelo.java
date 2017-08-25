package gob.conafor.suelo.mod;

import gob.conafor.conn.dat.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class CDSuelo {

    private String query;
    
    public List<CatEUsoSuelo> getUsoSuelo(){
        query = "SELECT UsoSueloID, Descripcion FROM CAT_UsoSuelo"; 
        List<CatEUsoSuelo> listUsoSuelo = new ArrayList<>();
        Connection conn = LocalConnectionCat.getConnection();
        
        try{
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                CatEUsoSuelo usoSuelo = new CatEUsoSuelo();
                usoSuelo.setUsoSueloID(rs.getInt("UsoSueloID"));
                usoSuelo.setUso(rs.getString("Descripcion"));
                listUsoSuelo.add(usoSuelo);
            }
            rs.close();
            st.close();
            listUsoSuelo.add(0, null);
            return listUsoSuelo;
        }catch(SQLException e){
             JOptionPane.showMessageDialog(null,
                    "Error! al obtener los datos de tipo de uso de suelo ",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {

                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en longitud de componentes en carbono ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public CESuelo getDatosSuelo(int sitioID) {
        query = "SELECT SitioID, UsoSueloID, OtroUsoSuelo, Espesor, PendienteDominante, Observaciones FROM SUELO_Suelo "
                + "WHERE SitioID= " + sitioID;
        Connection conn = LocalConnection.getConnection();
        CESuelo ceSuelo = new CESuelo();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                ceSuelo.setSitioID(rs.getInt("SitioID"));
                ceSuelo.setUsoSueloID(rs.getInt("UsoSueloID"));
                ceSuelo.setOtroUsoSuelo(rs.getString("OtroUsoSuelo"));
                ceSuelo.setEspesor(rs.getFloat("Espesor"));
                ceSuelo.setPendienteDominante(rs.getInt("PendienteDominante"));
                ceSuelo.setObservaciones(rs.getString("Observaciones"));
            }
            rs.close();
            st.close();
            return ceSuelo;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al obtener los datos de suelo ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al obtener los datos de suelo ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void insertDatosSuelo(CESuelo ceSuelo) {
        query = "INSERT INTO SUELO_Suelo(SitioID, UsoSueloID, OtroUsoSuelo, Espesor, PendienteDominante, Observaciones)VALUES("
                + ceSuelo.getSitioID() + ", " + ceSuelo.getUsoSueloID() + ", '" + ceSuelo.getOtroUsoSuelo() + "', " + ceSuelo.getEspesor() + ", "
                + ceSuelo.getPendienteDominante() + ", '" + ceSuelo.getObservaciones() + "')";
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo guardar la informacion de suelo ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos al insertar datos de suelo ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void updateDatosSuelo(CESuelo ceSuelo) {
        query = "UPDATE SUELO_Suelo SET UsoSueloID= " + ceSuelo.getUsoSueloID() + ", OtroUsoSuelo= '" + ceSuelo.getOtroUsoSuelo()
                + "', Espesor= " + ceSuelo.getEspesor() + ", PendienteDominante= " + ceSuelo.getPendienteDominante()
                + ", Observaciones= '" + ceSuelo.getObservaciones() + "' WHERE SitioID= " + ceSuelo.getSitioID();
        //System.out.println(query);
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo modificar la información de suelo ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la modificación de suelo ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void deleteDatosSuelo(int sitioID) {
        query = "DELETE FROM SUELO_Suelo WHERE SitioID= " + sitioID;
        Connection conn = LocalConnection.getConnection();
        
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo eliminar la información de suelo ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos  al eliminar suelo",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void agregarErosionHidricaSuelo(CESuelo ceSuelo){
         query = "UPDATE SUELO_Suelo SET NumeroCanalillos= " + ceSuelo.getNumeroCanalillos() + ", ProfundidadPromedioCanalillos= " + ceSuelo.getProfundidadPromedioCanalillos()
                + ", AnchoPromedioCanalillos= " + ceSuelo.getAnchoPromedioCanalillos() + ", LongitudCanalillos= " + ceSuelo.getLongitudCanalillos() + ", VolumenCanalillos= " + ceSuelo.getVolumenCanalillos()
                + ", NumeroCarcavas= " + ceSuelo.getNumeroCarcavas() + ", ProfundidadPromedioCarcavas= " + ceSuelo.getProfundidadPromedioCarcavas() 
                + ", AnchoPromedioCarcavas= " + ceSuelo.getAnchoPromedioCarcavas() + ", LongitudCarcavas= " + ceSuelo.getLongitudCarcavas() + ", VolumenCarcavas= " + ceSuelo.getVolumenCarcavas() + " WHERE SitioID= " + ceSuelo.getSitioID();
         
         Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo modificar la información de erosion hidrica de suelo ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
                //System.out.println("Cerrando la conexion agregarErosionHidricaSuelo");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la modificación de erosion hidrica del suelo ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void agregarDeformacionTerrenoSuelo(CESuelo ceSuelo){
         query = "UPDATE SUELO_Suelo SET NumeroMonticulos= " + ceSuelo.getNumeroMonticulos() + ", AlturaPromedioMonticulos= " + ceSuelo.getAlturaPromedioMonticulos()
                + ", AnchoPromedioMonticulos= " + ceSuelo.getAnchoPromedioMonticulos()+ ", LongitudPromedioMonticulos= " + ceSuelo.getLongitudPromedioMonticulos() + ", VolumenMonticulos= " + ceSuelo.getVolumenMonticulos()
                 + " WHERE SitioID= " + ceSuelo.getSitioID();
        Connection conn = LocalConnection.getConnection();
        //System.out.println(query);
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo modificar la información de deformacion del terreno ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la modificación de deformacion del terreno ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
