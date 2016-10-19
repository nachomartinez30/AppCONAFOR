package gob.conafor.suelo.mod;

import gob.conafor.conn.dat.LocalConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class CDEvidenciaErosion {

    private String query;
     /////////////////////////////////////////////////////////////////////
    //Metodos de Cobertura del suelo
   ///////////////////////////////////////////////////////////////////////
    public List<Integer> getTransecto(){
        List<Integer> listTransecto = new ArrayList<>();
        
        for(int i = 1; i < 5; i++){
            listTransecto.add(i);
        }
        listTransecto.add(0, null);
        return listTransecto;
    }
    
    public boolean validarTransectoCobertura(CECoberturaSuelo ceCobertura) {
        query = "SELECT SitioID, Transecto FROM SUELO_CoberturaSuelo WHERE SitioID= " + ceCobertura.getSitioID() + " AND Transecto= "
                + ceCobertura.getTransecto();
        boolean vacio = false;
        Connection conn = LocalConnection.getConnection();

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                vacio = true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al validar la captura de transecto de cobertura de suelo ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        }  finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en datos de validar la captura de transecto de cobertura de suelo ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        return vacio;
    }
    
    public Float getCoberturaSuelo(CECoberturaSuelo ceCobertura) {
        query = "SELECT SitioID, Transecto, Pendiente FROM SUELO_CoberturaSuelo WHERE SitioID= "
                + ceCobertura.getSitioID() + " AND Transecto= " + ceCobertura.getTransecto();
        Connection conn = LocalConnection.getConnection();
        Float pendiente = null;

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                pendiente = rs.getFloat("Pendiente");
            }
            return pendiente;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener los datos de cobertura de suelo ",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en datos del sitio", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public Integer getIdCoberturaActual(CECoberturaSuelo ceCobertura) {
        query = "SELECT CoberturaSueloID, SitioID, Transecto FROM SUELO_CoberturaSuelo WHERE SitioID= " + ceCobertura.getSitioID()
                + " AND Transecto= " + ceCobertura.getTransecto();
        Connection conn = LocalConnection.getConnection();
        Integer coberturaID = 0;
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                coberturaID = rs.getInt("CoberturaSueloID");
            }
            return coberturaID;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener el ID de cobertura de suelo ",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al obtener el ID de cobertura de suelo ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void insertCoberturaSuelo(CECoberturaSuelo ceCobertura) {
        query = "INSERT INTO SUELO_CoberturaSuelo(SitioID, Transecto, Pendiente)VALUES(" + ceCobertura.getSitioID() + ", " + ceCobertura.getTransecto()
                + ", " + ceCobertura.getPendiente() + ")";
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
            JOptionPane.showMessageDialog(null, "Se creo un nuevo transecto de cobertura");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo guardar la informacion de cobertura de suelo ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos al insertar datos de cobertura del suelo ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void updateCoberturaSuelo(CECoberturaSuelo cobertura) {
        query = "UPDATE SUELO_CoberturaSuelo SET Pendiente= " + cobertura.getPendiente()
                + " WHERE SitioID= " + cobertura.getSitioID() + " AND Transecto= " + cobertura.getTransecto();
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
            JOptionPane.showMessageDialog(null, "Se actualizo el transecto de cobertura de suelo");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo modificar la información de cobertura de suelo ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al modificar la cobertura de suelo ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void deleteCoberturaSuelo(CECoberturaSuelo cobertura) {
        query = "DELETE FROM SUELO_CoberturaSuelo WHERE sitioID= " + cobertura.getSitioID() + " AND Transecto= " + cobertura.getTransecto();
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
            JOptionPane.showMessageDialog(null, "Se elimino el transecto de cobertura de suelo");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo eliminar la cobertura del suelo ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos  al eliminar la cobertura del suelo",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Metodos de Evidencia de erosion del suelo
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    public List<Integer> getPuntos(int coberturaSueloID) {
        List<Integer> listPunto = new ArrayList<>();
        for (int i = 1; i < 16; i++) {
            listPunto.add(i);
        }
        listPunto.add(0, null);
        query = "SELECT CoberturaSueloID, Punto FROM SUELO_EvidenciaErosion WHERE CoberturaSueloID= " + coberturaSueloID;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Integer index = rs.getInt(2);
                listPunto.remove(index);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener los puntos de evidencia de erosion " + e.getClass().getName() + " : " + e.getMessage(),
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en los numeros de troza de submuestra"
                        + e.getClass().getName() + " : " + e.getMessage(), "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        return listPunto;
    }
    
    public List<CatELecturaTierra> getTipoLecturaTierra() {
        List<CatELecturaTierra> listLectura = new ArrayList<>();
        query = "SELECT LecturaTierraID, Clave, Descripcion FROM CAT_TipoLecturaTierra";
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                CatELecturaTierra ceLectura = new CatELecturaTierra();
                ceLectura.setLecturaTierraID(rs.getInt("LecturaTierraID"));
                ceLectura.setClave(rs.getString("Clave"));
                ceLectura.setDescripcion(rs.getString("Descripcion"));
                listLectura.add(ceLectura);
            }
            listLectura.add(0, null);
            return listLectura;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener los datos de tipo de lectura de tierra ",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en el listado de lectura de tierra ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
   public DefaultTableModel getTableEvidenciaErosion(int coberturaID){
        query = "SELECT EvidenciaErosionID, CoberturaSueloID, Punto, Dosel, Suelo FROM VW_EvidenciaErosion WHERE CoberturaSueloID= " + coberturaID;
        String [] encabezados = {"EvidenciaErosionID", "CoberturaSueloID", "Punto", "Dosel", "Suelo"};
        DefaultTableModel coberturaModel = new DefaultTableModel(null, encabezados);
        Object [] datosCobertura = new Object[5];
        Connection conn = LocalConnection.getConnection();
        try{
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                datosCobertura[0] = rs.getInt("EvidenciaErosionID");
                datosCobertura[1] = rs.getInt("CoberturaSueloID");
                datosCobertura[2] = rs.getInt("Punto");
                datosCobertura[3] = rs.getInt("Dosel");
                datosCobertura[4] = rs.getString("Suelo");
                
                coberturaModel.addRow(datosCobertura);
            }
            st.close();
            rs.close();
            
            return coberturaModel;
        }catch(SQLException e){
             JOptionPane.showMessageDialog(null, "Error! al obtener los datos de la vista de evidencia de erosion ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en para la vista de evidencia de erosion ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public CEEvidenciaErosion getEvidenciaErosionPunto(int evidenciaErosionID) {
        query = "SELECT EvidenciaErosionID, Punto, Dosel, LecturaTierraID FROM SUELO_EvidenciaErosion WHERE EvidenciaErosionID= " + evidenciaErosionID;
        Connection conn = LocalConnection.getConnection();
        CEEvidenciaErosion evidencia = new CEEvidenciaErosion();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                //evidencia.setCoberturaSueloID(rs.getInt("CoberturaSueloID"));
                evidencia.setPunto(rs.getInt("Punto"));
                evidencia.setDosel(rs.getInt("Dosel"));
                evidencia.setLecturaTierraID(rs.getInt("LecturaTierraID"));
            }
            return evidencia;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener la evidencia de erosion por punto ",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al recuperar la evidencia de erosion por punto ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void insertEvidenciaErosion(CEEvidenciaErosion evidencia) {
        query = "INSERT INTO SUELO_EvidenciaErosion(CoberturaSueloID, Punto, Dosel, LecturaTierraID)VALUES(" + evidencia.getCoberturaSueloID() + ", " + evidencia.getPunto()
                + ", " + evidencia.getDosel() + ", " + evidencia.getLecturaTierraID() + ")";
        Connection conn = LocalConnection.getConnection();

        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo guardar la informacion de evidencia de erosion ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos al insertar datos de evidencia de erosion ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void updateEvidenciaErosion(CEEvidenciaErosion evidencia) {
        query = "UPDATE SUELO_EvidenciaErosion SET Dosel= " + evidencia.getDosel() + ", LecturaTierraID= " + evidencia.getLecturaTierraID()
                + " WHERE EvidenciaErosionID=" + evidencia.getEvidenciaErosionID();
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo modificar la evidencia de erosion ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la modificación de la evidencia de erosion", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void deleteEvidenciaErosion(int evidenciaID) {
        query = "DELETE FROM SUELO_EvidenciaErosion WHERE EvidenciaErosionID=" + evidenciaID;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo eliminar la evidencia de erosion ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos  al eliminar la evidencia de erosion ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void deleteCoberturaSueloSitio(int sitioID){
        query = "DELETE FROM SUELO_CoberturaSuelo WHERE SitioID= " + sitioID;
         Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo eliminar la cobertura de suelo por sitio ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos  al eliminar la cobertura de suelo por sitio ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public boolean validarTablaCubiertaSuelo(int sitioID) {
        query = "SELECT * FROM SUELO_CoberturaSuelo WHERE SitioID= " + sitioID;
        boolean vacio = true;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                vacio = false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al validar la presencia de datos en la tabla cubierta de suelo", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la tabla de cubierta de suelo", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        return vacio;
    }
    
    public boolean validarTablaEvidenciaErosion(int sitioID){
        query = "SELECT co.SitioID, er.CoberturaSueloID FROM SUELO_EvidenciaErosion er LEFT JOIN " +
                 "SUELO_CoberturaSuelo co ON er.CoberturaSueloID = co.CoberturaSueloID WHERE co.SitioID= " + sitioID;
        boolean vacio = true;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                vacio = false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al validar la presencia de datos en la tabla de evidencia de erosion", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la tabla de evidencia de suelo", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        return vacio;
    }
}
