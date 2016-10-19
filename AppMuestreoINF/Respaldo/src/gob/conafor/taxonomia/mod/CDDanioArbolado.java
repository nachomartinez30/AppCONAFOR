package gob.conafor.taxonomia.mod;

import gob.conafor.conn.dat.LocalConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class CDDanioArbolado {

    private String query;
    
    public List<CEDanioSeveridad> getDanio(int arboladoID) {
        List<CEDanioSeveridad> listDanio = new ArrayList<>();
        query = "SELECT ArboladoID, NumeroDanio, AgenteDanioID, SeveridadID FROM ARBOLADO_DanioSeveridad WHERE ArboladoID= " + arboladoID + " ORDER BY NumeroDanio ASC";
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
                    "Error! al obtener los datos de daño del arbolado ",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en datos de daño del arbolado"
                      , "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        return listDanio;
    }
    
    public void insertDanioArbolado(CEDanioSeveridad danio){
        query = "INSERT INTO Arbolado_DanioSeveridad(ArboladoID, NumeroDanio, AgenteDanioID, SeveridadID)VALUES(" + danio.getSeccionID() + " ," + danio.getNumeroDanio() + " ," + danio.getAgenteDanioID() 
                + " ," + danio.getSeveridadID() + ")"; 
        
        Connection conn = LocalConnection.getConnection();
        
        try{
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error! no se pudo guardar la informacion de daños de arbolado "
                    , "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos al insertar datos de daños de arbolado ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void updateDanioArbolado(CEDanioSeveridad danio){
        
        query = "UPDATE Arbolado_DanioSeveridad SET AgenteDanioID= " + danio.getAgenteDanioID() 
                + ", SeveridadID= " + danio.getSeveridadID() + " WHERE ArboladoID= " + danio.getSeccionID()
                + " AND NumeroDanio= " + danio.getNumeroDanio();
        
        Connection conn = LocalConnection.getConnection();

        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo modificar la información de danio de arbolado"
                    , "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la modificacion de danio de arbolado"
                      , "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void deleteDanioArbolado(CEDanioSeveridad danio) {
        query = "DELETE FROM Arbolado_DanioSeveridad WHERE ArboladoID= " + danio.getSeccionID();

        Connection conn = LocalConnection.getConnection();

        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo eliminar la información de daño del arbolado "
                    , "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos  al eliminar el daño de arbolado"
                        , "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
   
}
