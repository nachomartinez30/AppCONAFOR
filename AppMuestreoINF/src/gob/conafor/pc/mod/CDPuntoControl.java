package gob.conafor.pc.mod;

import gob.conafor.conn.dat.LocalConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class CDPuntoControl {

    private String query;

    public CEPuntoControl getPuntoControl(int upmID) {
        query = "SELECT UPMID, Descripcion, Paraje, GradosLatitud, MinutosLatitud, SegundosLatitud, "
                + "GradosLongitud, MinutosLongitud, SegundosLongitud, ErrorPresicion, "
                + "Datum, Azimut, Distancia FROM PC_PuntoControl WHERE UPMID= " + upmID;
        Connection conn = LocalConnection.getConnection();
        CEPuntoControl cePuntoControl = new CEPuntoControl();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                cePuntoControl.setUpmID(rs.getInt("UPMID"));
                cePuntoControl.setDescripcion(rs.getString("Descripcion"));
                cePuntoControl.setParaje(rs.getString("Paraje"));
                cePuntoControl.setGradosLatitud(rs.getInt("GradosLatitud"));
                cePuntoControl.setMinutosLatitud(rs.getInt("MinutosLatitud"));
                cePuntoControl.setSegundosLatitud(rs.getFloat("SegundosLatitud"));
                cePuntoControl.setGradosLongitud(rs.getInt("GradosLongitud"));
                cePuntoControl.setMinutosLongitud(rs.getInt("MinutosLongitud"));
                cePuntoControl.setSegundosLongitud(rs.getFloat("SegundosLongitud"));
                cePuntoControl.setErrorPrecision(rs.getInt("ErrorPresicion"));
                cePuntoControl.setDatum(rs.getString("Datum"));
                cePuntoControl.setAzimut(rs.getInt("Azimut"));
                cePuntoControl.setDistancia(rs.getFloat("Distancia"));
            }
            st.close();
            rs.close();
            return cePuntoControl;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al obtener los datos del punto de control "
                    + e.getClass().getName() + " : " + e.getMessage(), "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane
                        .showMessageDialog(null,
                                "Error! al cerrar la base de datos en datos del punto de control"
                                + e.getClass().getName() + " : " + e.getMessage(),
                                "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public boolean existPuntoControl(int UPMID){
        boolean exist=false;
        int result=0;
        String query="Select EXISTS (SELECT UPMID From PC_PuntoControl WHERE UPMID="+ UPMID+")";
        Connection conn = LocalConnection.getConnection();
        CEPuntoControl cePuntoControl = new CEPuntoControl();
        
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            
            while(rs.next()){
                result=rs.getInt(1);
                if(result==0){
                    exist=false;
                }else{
                    exist=true;
                }
            }
        }  catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! al obtener existencia del punto de control "
                    + e.getClass().getName() + " : " + e.getMessage(), "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return false;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane
                        .showMessageDialog(null,
                                "Error! al cerrar la base de datos en datos del punto de control"
                                + e.getClass().getName() + " : " + e.getMessage(),
                                "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        //System.out.println("Exist\t"+exist);
        //System.out.println("result\t"+result);
        return exist;
    }
    
    public void insertPuntoControl(CEPuntoControl puntoControl) {
        query = "INSERT INTO PC_PuntoControl(UPMID, Descripcion, Paraje, GradosLatitud, MinutosLatitud, SegundosLatitud, "
                + "GradosLongitud, MinutosLongitud, SegundosLongitud, ErrorPresicion, Datum, Azimut, Distancia"
                + ")VALUES(" + puntoControl.getUpmID() + ", " + "'" + puntoControl.getDescripcion() + "'" + ", "
                + "'" + puntoControl.getParaje() + "'" + ", " + puntoControl.getGradosLatitud() + ", "
                + puntoControl.getMinutosLatitud() + ", " + puntoControl.getSegundosLatitud() + ", " + puntoControl.getGradosLongitud() + ", "
                + puntoControl.getMinutosLongitud() + ", " + puntoControl.getSegundosLongitud() + ", "
                + puntoControl.getErrorPrecision() + ", " + "'" + puntoControl.getDatum() + "'" + ", " + puntoControl.getAzimut()
                + ", " + puntoControl.getDistancia() + ")";
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo guardar la información del Punto de control "
                    + e.getClass().getName() + " : " + e.getMessage(), "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos en los datos del punto de control"
                        + e.getClass().getName() + " : " + e.getMessage(),
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void updatePuntoControl(CEPuntoControl puntoControl) {
        query = "UPDATE PC_PuntoControl SET Descripcion= '" + puntoControl.getDescripcion() + "', Paraje= '"
                + puntoControl.getParaje() + "', " + "GradosLatitud= " + puntoControl.getGradosLatitud()
                + ", MinutosLatitud= " + puntoControl.getMinutosLatitud() + ", " + "SegundosLatitud= "
                + puntoControl.getSegundosLatitud() + ", GradosLongitud= " + puntoControl.getGradosLongitud() + ", "
                + "MinutosLongitud= " + puntoControl.getMinutosLongitud() + ", SegundosLongitud= "
                + puntoControl.getSegundosLongitud() + ", " + "ErrorPresicion= " + puntoControl.getErrorPrecision()
                + ", Datum= '" + puntoControl.getDatum() + "', Azimut= " + puntoControl.getAzimut() + ", " + "Distancia= "
                + puntoControl.getDistancia() + " WHERE UPMID= " + puntoControl.getUpmID();
        System.out.println(query);
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo modificar la información del Punto de control "
                    + e.getClass().getName() + " : " + e.getMessage(), "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos  al modificar el punto de control"
                        + e.getClass().getName() + " : " + e.getMessage(),
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void deletePuntoControl(int upmID) {
        query = "DELETE FROM PC_PuntoControl WHERE UPMID= " + upmID;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo eliminar la información del Punto de control "
                    + e.getClass().getName() + " : " + e.getMessage(), "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos  al eliminar el punto de control"
                        + e.getClass().getName() + " : " + e.getMessage(),
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}
