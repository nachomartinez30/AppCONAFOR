package gob.conafor.upm.mod;

import gob.conafor.conn.dat.LocalConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class CDBrigada {

    private String query;

    //EmpresaID   DIAAPROY = 1, INYDES= 2, AMAREF = 3
    
    
    public List<CEBrigadista> getJefeBrigada() {
        List<CEBrigadista> listBrigadistas = new ArrayList();
        /*PRODUCCION*/ this.query = "SELECT BrigadistaID, Nombre, ApellidoPaterno, ApellidoMaterno FROM BRIGADA_Brigadistas WHERE PuestoID= 1 AND Activo= 1  ORDER BY ApellidoPaterno ASC";
        ///*DIAAPROY*/this.query = "SELECT BrigadistaID, Nombre, ApellidoPaterno, ApellidoMaterno FROM BRIGADA_Brigadistas WHERE PuestoID= 1 AND Activo= 1 AND EmpresaID=1 ORDER BY ApellidoPaterno ASC";
        ///*INYDES*/this.query = "SELECT BrigadistaID, Nombre, ApellidoPaterno, ApellidoMaterno FROM BRIGADA_Brigadistas WHERE PuestoID= 1 AND Activo= 1 AND EmpresaID=2 ORDER BY ApellidoPaterno ASC";
        ///*AMAREF*/this.query = "SELECT BrigadistaID, Nombre, ApellidoPaterno, ApellidoMaterno FROM BRIGADA_Brigadistas WHERE PuestoID= 1 AND Activo= 1 AND EmpresaID=3 ORDER BY ApellidoPaterno ASC";
        
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                CEBrigadista brigadista = new CEBrigadista();
                brigadista.setBrigadistaID(rs.getInt("BrigadistaID"));
                brigadista.setNombre(rs.getString("Nombre"));
                brigadista.setApellidoPaterno(rs.getString("ApellidoPaterno"));
                brigadista.setApellidoMaterno(rs.getString("ApellidoMaterno"));
                listBrigadistas.add(brigadista);
            }
            st.close();
            rs.close();
            listBrigadistas.add(0, null);
            return listBrigadistas;
        } catch (SQLException e) {
        e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                "Error! al obtener el listado de brigadistas ",
                "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
            e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al obtener el listadod e brigadistas ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public List<CEBrigadista> getBrigadistas() {
        List<CEBrigadista> listBrigadistas = new ArrayList();
        /*PRODUCCION*/ this.query = "SELECT BrigadistaID, Nombre, ApellidoPaterno, ApellidoMaterno,EmpresaID FROM BRIGADA_Brigadistas WHERE PuestoID= 0 AND Activo= 1 ORDER BY ApellidoPaterno ASC";
         ///*DIAAPROY*/this.query = "SELECT BrigadistaID, Nombre, ApellidoPaterno, ApellidoMaterno, EmpresaID  FROM BRIGADA_Brigadistas WHERE PuestoID= 0 AND Activo= 1 AND EmpresaID= 1 ORDER BY ApellidoPaterno ASC";
        ///*INYDES*/this.query = "SELECT BrigadistaID, Nombre, ApellidoPaterno, ApellidoMaterno , EmpresaID FROM BRIGADA_Brigadistas WHERE PuestoID= 0 AND Activo= 1 AND EmpresaID= 2 ORDER BY ApellidoPaterno ASC";
        ///*AMAREF*/this.query = "SELECT BrigadistaID, Nombre, ApellidoPaterno, ApellidoMaterno, EmpresaID FROM BRIGADA_Brigadistas WHERE PuestoID= 0 AND Activo= 1 AND EmpresaID= 3 ORDER BY ApellidoPaterno ASC";
        
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                CEBrigadista brigadista = new CEBrigadista();
                brigadista.setBrigadistaID(rs.getInt("BrigadistaID"));
                brigadista.setNombre(rs.getString("Nombre"));
                brigadista.setApellidoPaterno(rs.getString("ApellidoPaterno"));
                brigadista.setApellidoMaterno(rs.getString("ApellidoMaterno"));
                brigadista.setEmpresaID(rs.getInt("EmpresaID"));
                listBrigadistas.add(brigadista);
            }
            st.close();
            rs.close();
            listBrigadistas.add(0, null);
            return listBrigadistas;
        } catch (SQLException e) {
        e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                "Error! al obtener el listado de brigadistas ",
                "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
            e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al obtener el listadod e brigadistas ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public List<CEBrigadista> getBrigada(int upmID) {
        List<CEBrigadista> listBrigada = new ArrayList();
        this.query = "SELECT UPMID, BrigadistaID, Nombre, APellidoPaterno, ApellidoMaterno, PuestoID FROM VW_Brigada WHERE UPMID= " + upmID;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                CEBrigadista brigadista = new CEBrigadista();
                brigadista.setBrigadistaID(rs.getInt("BrigadistaID"));
                brigadista.setNombre(rs.getString("Nombre"));
                brigadista.setApellidoPaterno(rs.getString("ApellidoPaterno"));
                brigadista.setApellidoMaterno(rs.getString("ApellidoMaterno"));
                brigadista.setPuestoID(rs.getInt("PuestoID"));
                
                listBrigada.add(brigadista);
            }
            st.close();
            rs.close();
            return listBrigada;
        } catch (SQLException e) {
        e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                "Error! al obtener los brigadistas del levantamiento ",
                "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
            e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al obtener los brigadistas del levantamiento ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void insertBrigada(CEBrigada ceBrigada) {
        this.query = "INSERT INTO UPM_Brigada(UPMID, BrigadistaID, PuestoID, EmpresaID)VALUES(" + ceBrigada.getUpmID() + "," + ceBrigada.getBrigadistaID()
        + ", " + ceBrigada.getPuestoID() + ","+ceBrigada.getEmpresaID()+")";
        Connection conn = LocalConnection.getConnection();
        //System.out.println(query);
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (Exception e) {
        e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo guardar la información del brigadista ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
            e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al guardar la información del brigadista ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void updateBrigada(CEBrigada ceBrigada) {
        this.query = "UPDATE UPM_Brigada SET BrigadistaID= " + ceBrigada.getBrigadistaID() + ", PuestoID= " + ceBrigada.getPuestoID()
        + " WHERE UPMID= " + ceBrigada.getUpmID() + " AND PuestoID= " + ceBrigada.getPuestoID();
        //System.out.println(query);
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
        e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo actualizar la información del brigadista ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
            e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al actualizar la información del brigadista ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void deleteBrigada(Integer upmID) {
        this.query = "DELETE FROM UPM_Brigada WHERE UPMID= " + upmID;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
        e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo eliminar la información de la brigada ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
            e.printStackTrace();
                JOptionPane.showMessageDialog(
                    null, "Error! al cerrar la base de datos  al eliminar al eliminar la información de la brigada ",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}
