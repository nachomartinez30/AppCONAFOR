package gob.conafor.sys.mod;

import gob.conafor.conn.dat.LocalConnection;
import gob.conafor.sitio.mod.CESitio;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class CDSeguimientoUPM {

    private String query;

    //Este método permite llenar la lista de selección de UPM'S ya capturados.
    public String[] getUpmCapturados() {

        query = "SELECT DISTINCT UPMID FROM SYS_SeccionesCapturadas";
        Connection conn = LocalConnection.getConnection();
        List<String> listUpm = new ArrayList<>();
        String[] arrayUpm;
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                listUpm.add(rs.getString("UPMID"));
            }
            listUpm.add(0, "0");
            arrayUpm = (String[]) listUpm.toArray(new String[listUpm.size()]);
            rs.close();
            st.close();
            return arrayUpm;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener la upm a continuar " ,
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en UPM'S a continuar", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public int getTipoUPM(int upmID) {
        query = "SELECT TipoUPMID FROM UPM_UPM WHERE UPMID= " + upmID;
        Connection conn = LocalConnection.getConnection();
        int tipoUpmID = 0;
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                tipoUpmID = rs.getInt("TipoUPMID");
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo obtener el tipo de UPM al continuar la captura", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al obtener el tipo de UPM al continuar la captura ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        return tipoUpmID;
    }
    
    //Este método permite ir guardando el historial de capturas realizadas
    public void insertSeccionCaptura(CESeccionesCapturadas seccion) {

        query = "INSERT INTO SYS_SeccionesCapturadas(UPMID, SeccionID, Estatus)VALUES("
                + seccion.getUpmId() + ", " + seccion.getSeccionId() + ", " + seccion.getEstaus() + ")";
        Connection conn = LocalConnection.getConnection();

        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo guardar la información de la seccion capturada ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al guardar la sección capturada"
                        + e.getClass().getName() + " : " + e.getMessage(), "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void insertSeccionCapturaSitio(CESeccionesCapturadas seccion){ 
        this.query = "INSERT INTO SYS_SeccionesCapturadas(UPMID, Sitio, SeccionID, Estatus)VALUES(" + seccion.getUpmId()
                + ", " + seccion.getSitio() + ", " + seccion.getSeccionId() + ", "  + seccion.getEstaus()+ ")";
         Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
           // JOptionPane.showMessageDialog(null, "UPM: " + seccion.getUpmId()+ " Sitio: " + seccion.getSitio()+ "  " + " Seccion: " + seccion.getSeccionId() + " Status: " + seccion.getEstaus());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo guardar la información de la seccion capturada en sitio", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al guardar la sección capturada en sitio", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void updateSeccionCaptura(CESeccionesCapturadas seccion) {
        query = "UPDATE SYS_SeccionesCapturadas SET Estatus= " + seccion.getEstaus() + " WHERE UPMID= " + seccion.getUpmId() + " "
                + "AND SeccionID= " + seccion.getSeccionId();
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo actualizar la información de la seccion capturada ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al actualizar la sección capturada", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void updateSeccionCapturaSitio(CESeccionesCapturadas seccion) {
        query = "UPDATE SYS_SeccionesCapturadas SET Estatus= " + seccion.getEstaus() + " WHERE UPMID= " + seccion.getUpmId()
                + " AND SeccionID= " + seccion.getSeccionId();
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo actualizar la información de la seccion capturada ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al actualizar la sección capturada", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    //Este método permite posicionarse en el formulario siguiente a capturar
    private int buscarSiguienteCaptura(int upm){
        query = "SELECT UPMID, SeccionID FROM SYS_SeccionesCapturadas WHERE UPMID= " + upm + " AND Estatus= -1";
        Connection conn = LocalConnection.getConnection();
        int seccion = 0;
        
        try{
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            
            while(rs.next()){
                seccion = rs.getInt("SeccionID");
            }
            return seccion;
        }catch(SQLException e){
             JOptionPane.showMessageDialog(null,
                    "Error! al obtener la seccion capturada ",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
           return -1;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {

                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al obtener la sección capturada", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    //Este procedimiento regresa el nombre del formulario en el que se continuará la captura pendiente utilizando
    //el procedimienti buscar siguiente captura, el cuál regresa la sección a continuar
    public String getFormularioSiguiente(int upmID){
 
        query = "SELECT SeccionID, Formulario FROM SYS_Seccion WHERE SeccionID= " + buscarSiguienteCaptura(upmID);
        Connection conn = LocalConnection.getConnection();
        String formulario = null;
        
        try{
            
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            
            while(rs.next()){
                
                formulario = rs.getString("Formulario");
            }
            return formulario;
        }catch(SQLException e){
              JOptionPane.showMessageDialog(null, "Error! no se puede conseguir el formulario de captura ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        }finally {

            try {

                conn.close();

            } catch (SQLException e) {

                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al cuntinuar captura", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    //Este método obtiene la información de las secciones capturadas de manera actualizada.
 /*   public List<CESeccion>getSeccionesCapturadas(int upmID){ 
        
        query = "SELECT cap.UPMID, sec.SubSeccion AS Seccion, sec.Modulo, " +
                "CASE cap.Estatus WHEN 1 THEN 'Capturado' WHEN  0 THEN 'Sin Capturar' ELSE 'Pendiente' END Estatus " +
                "FROM SYS_Seccion sec LEFT JOIN SYS_SeccionesCapturadas cap " +
                "ON sec.SeccionID = cap.SeccionID WHERE cap.UPMID= " + upmID;
                
        Connection conn = LocalConnection.getConnection();
        List<CESeccion> listaSecciones = new ArrayList<>();
        
        try{
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            
            while(rs.next()){
                CESeccion seccion = new CESeccion();
               
                seccion.setSeccion(rs.getString("Seccion"));
                seccion.setModulo(rs.getString("Modulo"));
                seccion.setEstatus(rs.getString("Estatus"));
                
                listaSecciones.add(seccion);
            }
            
            return listaSecciones;
            
        }catch(SQLException e){
             JOptionPane.showMessageDialog(null, "Error! al obtener los datos de secciones capturadas: "
                    + e.getClass().getName() + " : " + e.getMessage(), "Conexion BD", JOptionPane.ERROR_MESSAGE);

            return null;
        } finally {

            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en las secciones capturadas"
                        + e.getClass().getName() + " : " + e.getMessage(), "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }

        }

    }*/
    
    public DefaultTableModel getTablaSeccionesCapturadas(int upmID) {
        
        query = "SELECT cap.UPMID, sec.SubSeccion AS Seccion, cap.Sitio, sec.Modulo, " +
                "CASE cap.Estatus WHEN 1 THEN 'Capturado' WHEN  0 THEN 'Sin Capturar' ELSE 'Pendiente' END Estatus " +
                "FROM SYS_Seccion sec LEFT JOIN SYS_SeccionesCapturadas cap " +
                "ON sec.SeccionID = cap.SeccionID WHERE cap.UPMID= " + upmID;
        String[] encabezados = {"Seccion", "Sitio", "Modulo", "Estatus"};
                
        DefaultTableModel seguimientoModel = new DefaultTableModel(null, encabezados);
        Object[] seguimiento = new Object[4]; 
        Connection conn = LocalConnection.getConnection();
        try{
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(query);
             while(rs.next()){
                 seguimiento[0] = rs.getString("Seccion");
                 seguimiento[1] = rs.getString("Sitio");
                 seguimiento[2] = rs.getString("Modulo");
                 seguimiento[3] = rs.getString("Estatus");
                 seguimientoModel.addRow(seguimiento);
             }
             
            st.close();
            rs.close();
            
            return seguimientoModel;
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error! al obtener los datos de la vista de secciones capturadas ", "Conexion BD", JOptionPane.ERROR_MESSAGE);

            return null;
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en para la vista de secciones capturadas", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    /* public boolean validarSeccionesCapturadas() {
        query = "SELECT * FROM SYS_SeccionesCapturadas";
        boolean vacio = false;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                vacio = true;
            }

        } catch (SQLException e) {
            
            JOptionPane.showMessageDialog(null, "Error! al validar las secciones capturadas "
                    + e.getClass().getName() + " : " + e.getMessage(), "Conexion BD", JOptionPane.ERROR_MESSAGE);

        }finally {

            try {

                conn.close();

            } catch (SQLException e) {

                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en validación de secciones capturadas"
                        + e.getClass().getName() + " : " + e.getMessage(), "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        return vacio;
    }*/
    
     public int getSitioID(int upm, int sitio){
         query = "SELECT SitioID FROM SITIOS_Sitio WHERE UPMID= " + upm + " AND Sitio= " + sitio;
         Connection conn = LocalConnection.getConnection();
         int sitioID = 0;
         
         try{
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(query);
             
             while(rs.next()){
                 sitioID = rs.getInt("SitioID");
             }
             
             return sitioID;
             
         }catch(SQLException e){
             JOptionPane.showMessageDialog(null, "Error! al buscar el id del sitio ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
         }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en validación de secciones capturadas", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
         return sitioID;
     }
     
    public CESitio getDatosSitio(int upm) {

        query = "SELECT UPMID, Sitio FROM SYS_SeccionesCapturadas WHERE UPMID= " + upm + " AND Estatus= -1";
        Connection conn = LocalConnection.getConnection();
        int upmID = 0;
        int sitio = 0;
        CESitio sit = new CESitio();

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                upmID = rs.getInt("UPMID");
                sitio = rs.getInt("Sitio");
            }
            sit.setUpmID(upmID);
            sit.setSitio(sitio);
            sit.setSitioID(getSitioID(upmID, sitio));
           
            return sit;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener la seccion capturada en sitio ",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {

            try {
                conn.close();
            } catch (SQLException e) {

                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al obtener la sección capturada en sitio", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}

