package gob.conafor.taxonomia.mod;

import gob.conafor.conn.dat.LocalConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class CDSubmuestra {

    private String query;
    
    public CESubmuestra getRegistroSubmuestra(int submuestraID) {
        query = "SELECT SubmuestraID, Consecutivo, NoIndividuo, NoRama, Familia, Genero, "
                + "Especie, DiametroBasal, Edad, NumeroAnillos25, LongitudAnillos10, GrozorCorteza "
                + "FROM VW_Submuestra WHERE SubmuestraID= " + submuestraID;
        Connection conn = LocalConnection.getConnection();
        CESubmuestra ceSubmuestra = new CESubmuestra();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                ceSubmuestra.setSubmuestraID(rs.getInt("SubmuestraID"));
                ceSubmuestra.setConsecutivo(rs.getInt("Consecutivo"));
                ceSubmuestra.setNoIndividuo(rs.getInt("NoIndividuo"));
                ceSubmuestra.setNoRama(rs.getInt("NoRama"));
                ceSubmuestra.setFamilia(rs.getString("Familia"));
                ceSubmuestra.setGenero(rs.getString("Genero"));
                ceSubmuestra.setEspecie(rs.getString("Especie"));
                ceSubmuestra.setDiametroBasal(rs.getFloat("DiametroBasal"));
                ceSubmuestra.setEdad(rs.getInt("Edad"));
                ceSubmuestra.setNumeroAnillos25(rs.getInt("NumeroAnillos25"));
                ceSubmuestra.setLongitudAnillos10(rs.getFloat("LongitudAnillos10"));
                ceSubmuestra.setGrozorCorteza(rs.getFloat("GrozorCorteza"));
            }
            return ceSubmuestra;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al obtener un registro de submuestra "
                   , "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane
                        .showMessageDialog(null,
                                "Error! al cerrar la base de datos en datos de registro de submuestra",
                                "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void insertSubmuestra(int sitioID, int arboladoID){
        query = "INSERT INTO Arbolado_Submuestra (SitioID, ArboladoID)VALUES("+ sitioID + ", " + arboladoID + ")";
        Connection conn = LocalConnection.getConnection();
        try{
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e){
             JOptionPane.showMessageDialog(null, "Error! no se pudo crear una submuestra nueva ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos al crear una nueva submuestra ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void insertTipoTroza (CETroza troza){
        query = "INSERT INTO ARBOLADO_Troza (SubmuestraID, NoTroza, TipoTrozaID)VALUES("
                + troza.getSubmuestraID() + ", " + troza.getNumeroTroza() + ", " + troza.getTipoTrozaID() + ")";
        Connection conn = LocalConnection.getConnection();
        try{
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error! no se pudo crear una troza nueva ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos al crear una nueva troza ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void deleteRegistroSubmuestra(int arboladoID) {
        query = "DELETE FROM Arbolado_Submuestra WHERE ArboladoID= " + arboladoID;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo eliminar la submuestra ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos  al eliminar la submuestra",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
  
    public void deleteRegistroTroza(int trozaID) {
        query = "DELETE FROM ARBOLADO_Troza WHERE TrozaID= " + trozaID;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo eliminar el registro de troza ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos  al eliminar el registro de troza",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void deleteSubmuestra() {
        query = "DELETE FROM Arbolado_Submuestra";
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo eliminar la submuestra ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos  al eliminar la submuestra",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public DefaultTableModel getTablaSubmuestra(int sitioID) {
        query = "SELECT SubmuestraID, SitioID, ArboladoID, Consecutivo, NoIndividuo, NoRama, Familia, Genero, Especie, "
                + "DiametroBasal, Edad, NumeroAnillos25, LongitudAnillos10, GrozorCorteza FROM VW_Submuestra WHERE SitioID= "
                + sitioID;

        String[] encabezados = {"SubmuestraID", "SitioID", "ArboladoID", "Consecutivo", "Individuo", "Rama/tallo",
            "Familia", "Genero", "Especie", "Diametro basal", " Edad", "Numero de anillos 2.5",
            "Longitud Anillos 10", "Grozor corteza"};

        DefaultTableModel submuestraModel = new DefaultTableModel(null, encabezados);
        Object[] datosSubmuestra = new Object[14];
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                datosSubmuestra[0] = rs.getInt("SubmuestraID");
                datosSubmuestra[1] = rs.getInt("SitioID");
                datosSubmuestra[2] = rs.getInt("ArboladoID");
                datosSubmuestra[3] = rs.getInt("Consecutivo");
                datosSubmuestra[4] = rs.getInt("NoIndividuo");
                datosSubmuestra[5] = rs.getInt("NoRama");
                datosSubmuestra[6] = rs.getString("Familia");
                datosSubmuestra[7] = rs.getString("Genero");
                datosSubmuestra[8] = rs.getString("Especie");
                datosSubmuestra[9] = rs.getString("DiametroBasal");
                datosSubmuestra[10] = rs.getString("Edad");
                datosSubmuestra[11] = rs.getString("NumeroAnillos25");
                datosSubmuestra[12] = rs.getString("LongitudAnillos10");
                datosSubmuestra[13] = rs.getString("GrozorCorteza");
                
                submuestraModel.addRow(datosSubmuestra);
            }
            st.close();
            rs.close();
            return submuestraModel;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al obtener los datos de la vista de submuetra ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en para la vista de submuestra", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public DefaultTableModel getTablaTroza(int submuestraID) {
        query = "SELECT TrozaID, SubmuestraID, NoTroza, TipoTroza FROM VW_SubmuestraTroza WHERE SubmuestraID= " + submuestraID;
        String[] encabezados = {"TrozaID", "SubmuestraID", "Numero", "Tipo"};
        DefaultTableModel trozaModel = new DefaultTableModel(null, encabezados);
        Object[] datosTroza = new Object[4];
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                datosTroza[0] = rs.getInt("TrozaID");
                datosTroza[1] = rs.getInt("SubmuestraID");
                datosTroza[2] = rs.getInt("NoTroza");
                datosTroza[3] = rs.getString("TipoTroza");

                trozaModel.addRow(datosTroza);
            }
            st.close();
            rs.close();
            return trozaModel;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al obtener los datos de la vista de troza de submuestra ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en para la vista de troza de submuestra", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
            
    
    public List<CatETipoTroza> getTipotroza() {
        List<CatETipoTroza> listTipoTroza = new ArrayList<>();
        query = "SELECT TipoTrozaID, Clave, Descripcion FROM CAT_TipoTroza";
        Connection conn = LocalConnection.getConnection();

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                CatETipoTroza tipoTroza = new CatETipoTroza();

                tipoTroza.setTrozaID(rs.getInt("TipoTrozaID"));
                tipoTroza.setClave(rs.getString("Clave"));
                tipoTroza.setDescripcion(rs.getString("Descripcion"));

                listTipoTroza.add(tipoTroza);
            }
            listTipoTroza.add(0, null);
            return listTipoTroza;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener los datos de tipo de troza de submuestra" ,
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en datos en tipos de troza de submuestra", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public List<Integer> getNumeroTroza(int submuestraID) {
        List<Integer> listNumeroTroza = new ArrayList<Integer>();
        listNumeroTroza.add(0, null);
        for (int i = 1; i < 9; i++) {
            listNumeroTroza.add(i);
        }
        query = "SELECT SubmuestraID, NoTroza FROM ARBOLADO_Troza WHERE SubmuestraID= " + submuestraID;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                Integer index = rs.getInt(2);
                listNumeroTroza.remove(index);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener los numeros de troza de submuestra",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en los numeros de troza de submuestra", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        return listNumeroTroza;
    }
    
    public void updateSubmuestra(CESubmuestra submuestra){
        query = "UPDATE Arbolado_Submuestra SET DiametroBasal= " + submuestra.getDiametroBasal() + ", Edad= " + submuestra.getEdad()
                + ", NumeroAnillos25= " + submuestra.getNumeroAnillos25() + ", LongitudAnillos10= " + submuestra.getLongitudAnillos10()
                + ", GrozorCorteza= " + submuestra.getGrozorCorteza() + " WHERE SubmuestraID = " + submuestra.getSubmuestraID();
        Connection conn = LocalConnection.getConnection();
        try{
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error! no se pudo modificar la información de submuestra ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la modificación de submuestra", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void updateTroza(CETroza ceTroza) {
        query = "UPDATE ARBOLADO_Troza SET TipoTrozaID= " +  ceTroza.getTipoTrozaID() + " WHERE TrozaID= " + ceTroza.getTrozaID();
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo modificar la información de troza ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la modificación de troza", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public CETroza getRegisroTroza(int trozaID) {
        query = "SELECT TrozaID, NoTroza, TipoTrozaID FROM ARBOLADO_Troza WHERE TrozaID= " + trozaID;
        CETroza ceTroza = new CETroza();
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                ceTroza.setNumeroTroza(rs.getInt("NoTroza"));
                ceTroza.setTipoTrozaID(rs.getInt("TipoTrozaID"));
            }
            return ceTroza;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo obtener el registro de troza ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al obtener el registro de troza", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public boolean validarPresenciaSubmuestra(int sitioID) {
        query = "SELECT SitioID FROM ARBOLADO_Submuestra WHERE SitioID= " + sitioID;
        boolean vacio = false;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                vacio = true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al validar la existencia de submuestras", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en datos de validar la existencia de submuestras", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        return vacio;
    }
    
    public boolean faltaDiametroBasal(int sitioID){
        query = "SELECT SitioID, DiametroBasal FROM ARBOLADO_Submuestra WHERE SitioID= " + sitioID + " AND DiametroBasal IS NULL OR DiametroBasal= ''";
        boolean vacio = false;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                vacio = true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al validar la captura de diametro basal", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en datos de validar la captura de diametro basal", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        return vacio;
    }
    
    public boolean faltaEdad(int sitioID) {
        query = "SELECT SitioID, Edad FROM ARBOLADO_Submuestra WHERE SitioID= " + sitioID + " AND Edad IS NULL OR Edad= ''";
        boolean vacio = false;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                vacio = true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al validar la captura de edad", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en datos de validar la captura de edad", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        return vacio;
    }
    
    public boolean faltaNumeroAnillos25(int sitioID) {
        query = "SELECT SitioID, NumeroAnillos25 FROM ARBOLADO_Submuestra WHERE SitioID= " + sitioID + " AND NumeroAnillos25 IS NULL OR NumeroAnillos25= ''";
        boolean vacio = false;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                vacio = true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al validar la captura de anillos 25", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en datos de validar la captura de anillos 25", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        return vacio;
    }
    
    public boolean faltaLongitudAnillos10(int sitioID) {
        query = "SELECT SitioID, LongitudAnillos10 FROM ARBOLADO_Submuestra WHERE SitioID= " + sitioID + " AND LongitudAnillos10 IS NULL OR LongitudAnillos10= ''";
        boolean vacio = false;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                vacio = true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al validar la captura de longitud de anillos 10", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en datos de validar la captura de anillos 10", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        return vacio;
    }
    
    public boolean faltaGrozorCorteza(int sitioID){
        query = "SELECT SitioID, GrozorCorteza FROM ARBOLADO_Submuestra WHERE SitioID= " + sitioID + " AND GrozorCorteza IS NULL OR GrozorCorteza= ''";
        boolean vacio = false;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                vacio = true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al validar la captura de grozor corteza", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al validar la captura de grozor corteza", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        return vacio;
    }
}
