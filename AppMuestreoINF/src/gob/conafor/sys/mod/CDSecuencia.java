package gob.conafor.sys.mod;

import gob.conafor.conn.dat.LocalConnection;
import gob.conafor.sitio.mod.CESitio;
import gob.conafor.utils.FuncionesComunes;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class CDSecuencia {

    private String query;
    
    public List<Integer> getModulos(int upmID) {
        this.query = "SELECT A, C, D, E, F, G, H FROM UPM_MallaPuntos WHERE UPMID= " + upmID;
        List modulos = new ArrayList<>();
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                modulos.add(rs.getInt("A"));
                modulos.add(rs.getInt("C"));
                modulos.add(rs.getInt("D"));
                modulos.add(rs.getInt("E"));
                modulos.add(rs.getInt("F"));
                modulos.add(rs.getInt("G"));
                modulos.add(rs.getInt("H"));
            }
            return modulos;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener los modulos del UPM ",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    public List<Integer> getUPMPorModulo() {
        query = "SELECT DISTINCT UPMID FROM SYS_SecuenciaCaptura";
        List listUPM = new ArrayList<>();
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                listUPM.add(rs.getInt("UPMID"));
            }
            listUPM.add(0, null);
            rs.close();
            st.close();
            return listUPM;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener el upm por módulo ",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al obtener el upm por modulo ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public List<Integer> getUPMRevision(){
        query = "SELECT DISTINCT UPMID FROM SYS_UPM_Revision";
        List listUPM = new ArrayList<>();
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                listUPM.add(rs.getInt("UPMID"));
            }
            listUPM.add(0, null);
            rs.close();
            st.close();
            return listUPM;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener el upm para revision ",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al obtener el upm para revisión ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public List<Integer> getSitiosRevision(int upmID){
        query = "SELECT UPMID, Sitio FROM SYS_UPM_Revision WHERE UPMID= " + upmID;
         List listUPM = new ArrayList<>();
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                listUPM.add(rs.getInt("Sitio"));
            }
            listUPM.add(0, null);
            rs.close();
            st.close();
            return listUPM;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener los sitios para revision ",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al obtener los sitios para revisión ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public boolean habilitarCapturaSitio() {
        query = "SELECT * FROM UPM_UPM WHERE Accesible= 1";
        boolean hayRegistros = false;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                hayRegistros = true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! No se pudo validar la existencia de UPM'S capturados", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en datos de validar la presencia de UPM'S capturados ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        return hayRegistros;
    }
    
    public boolean habilitarCapturaModulos() {
        query = "SELECT * FROM SITIOS_Sitio WHERE SitioAccesible= 1";
        boolean hayRegistros = false;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                hayRegistros = true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! No se pudo validar la existencia de sitios capturados ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en datos de validar la presencia de sitios capturados ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        return hayRegistros;
    }
    
    public boolean habilitarContinuarModulos() {
        query = "SELECT DISTINCT UPMID FROM SYS_SecuenciaCaptura";
        boolean hayRegistros = false;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                hayRegistros = true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! No se pudo validar la existencia de formatos capturados ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en datos de validar la presencia de formatos capturados ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        return hayRegistros;
    }
    
    public boolean habilitarRevisarUPM() {
        query = "SELECT UPMID FROM UPM_UPM";
        boolean hayRegistros = false;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                hayRegistros = true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! No se pudo validar la existencia de UPM'S capturados", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en datos de validar la presencia de UPM'S capturados", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        return hayRegistros;
    }
    
    public boolean habilitarRevisarSitios() {
        query = "SELECT SitioID FROM SITIOS_Sitio";
        boolean hayRegistros = false;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                hayRegistros = true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! No se pudo validar la existencia de Sitios capturados", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en datos de validar la presencia de Sitios capturados", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        return hayRegistros;
    }

    public boolean habilitarColectaBotanica() {
        query = "SELECT UPMID FROM TAXONOMIA_ColectaBotanica";
        boolean hayRegistros = false;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                hayRegistros = true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! No se pudo validar la existencia de Colectas botánicas ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en datos de validar la presencia de colectas botánicas", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        return hayRegistros;
    }
    
    public void insertFormatoCapturado(CESitio ceSitio, int formularioID) {
        Integer secuenciaID = ceSitio.getSecuencia();
        Integer upmId = ceSitio.getUpmID();
        Integer sitio = ceSitio.getSitio();
        query = "INSERT INTO SYS_SecuenciaCaptura(UPMID, Sitio, FormatoID, Visitado)VALUES(" + upmId + ", " + sitio + ", " + formularioID + ", " + 1 + ")";
        Connection conn = LocalConnection.getConnection();
        if (secuenciaID != null) {
            try {
                Statement st = conn.createStatement();
                st.executeUpdate(query);
                conn.commit();
                st.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! no se pudo guardar la información del formato visitado ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(
                            null, "Error! al cerrar la base de datos al insertar datos del formato visitado ",
                            "Conexion BD", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    
    public void insertSecuenciaTerminada(CESitio ceSitio) {
        query = "INSERT INTO SYS_UPM_Revision(UPMID, SitioID, Sitio, SecuenciaID)VALUES(" + ceSitio.getUpmID() + ", "
                + ceSitio.getSitioID() + ", " + ceSitio.getSitio() + ", " + ceSitio.getSecuencia() + ")";
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo guardar la información del sitio para revisión ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos al insertar datos del sitio para revisión ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void updateFormatoCapturado(CESitio ceSitio, int capturado) {
        query = "UPDATE SYS_SecuenciaCaptura SET Capturado = " + capturado + " WHERE UPMID= " + ceSitio.getUpmID() + " AND Sitio= " + ceSitio.getSitio();
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo actualizar si fue capturado el formato ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos al actualizar si fue capturado el formato ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public DefaultTableModel getTablaSecuenciaCaptura(int upmID, int sitio) {
        this.query = "SELECT SecuenciaID, FormatoID, UPM, Sitio, Formato, Estatus FROM VW_Secuencia WHERE UPM = " + upmID + " AND Sitio= " + sitio;
        String[] encabezados = {"SecuenciaID", "FormatoID", "UPM", "Sitio", "Formato", "Estatus"};
        Object[] secuencia = new Object[6];
        DefaultTableModel secuenciaModel = new DefaultTableModel(null, encabezados);
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                secuencia[0] = rs.getInt("SecuenciaID");
                secuencia[1] = rs.getInt("FormatoID");
                secuencia[2] = rs.getInt("UPM");
                secuencia[3] = rs.getInt("Sitio");
                secuencia[4] = rs.getString("Formato");
                secuencia[5] = rs.getString("Estatus");
                secuenciaModel.addRow(secuencia);
            }
            st.close();
            rs.close();
            return secuenciaModel;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al obtener los datos de la vista de secuencia de captura ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos para la vista de secuancia capturada", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
        public DefaultTableModel getTablaSecuenciaCapturaRevision(int upmID, int sitio) {
        this.query = "SELECT SecuenciaID, FormatoID, UPM, Sitio, Formato, Estatus FROM VW_Secuencia WHERE "
                + "UPM = " + upmID + " AND Sitio= " + sitio + " AND Estatus <> 'PENDIENTE'";
        String[] encabezados = {"SecuenciaID", "FormatoID", "UPM", "Sitio", "Formato", "Estatus"};
        Object[] secuencia = new Object[6];
        DefaultTableModel secuenciaModel = new DefaultTableModel(null, encabezados);
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                secuencia[0] = rs.getInt("SecuenciaID");
                secuencia[1] = rs.getInt("FormatoID");
                secuencia[2] = rs.getInt("UPM");
                secuencia[3] = rs.getInt("Sitio");
                secuencia[4] = rs.getString("Formato");
                secuencia[5] = rs.getString("Estatus");
                secuenciaModel.addRow(secuencia);
            }
            st.close();
            rs.close();
            return secuenciaModel;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al obtener los datos de la vista de secuencia de captura ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos para la vista de secuancia capturada", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public int getFormatoPendiente(int upmID, int sitio) {
        query = "SELECT FormatoID FROM SYS_SecuenciaCaptura WHERE UPMID= " + upmID + " AND Sitio= " + sitio + " AND Estatus= 0 LIMIT 1" ;
        Connection conn = LocalConnection.getConnection();
        int formatoID = 0;
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                formatoID = rs.getInt("FormatoID");
            }
            st.close();
            rs.close();
            return formatoID;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al obtener el id del formato pendiente", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return 0;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al obtener el id del formato pendiente", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void insertSecuencia1(int upmID, int sitio) {
        FuncionesComunes funciones = new FuncionesComunes();
        //Solo módulo A
        List<Integer> listFormatosID = new ArrayList<>();
        Integer noSitio = funciones.sitioCapturaSueloCarbono(upmID, 3);
        if (sitio == noSitio) {
            listFormatosID.add(0, 1);
            listFormatosID.add(1, 2);
            listFormatosID.add(2, 3);
            listFormatosID.add(3, 4);
            listFormatosID.add(4, 5);
            listFormatosID.add(5, 6);
            listFormatosID.add(6, 7);
            listFormatosID.add(7, 8);
            listFormatosID.add(8, 9);
            listFormatosID.add(9, 10);
            listFormatosID.add(10, 11);
            listFormatosID.add(11, 12);
            listFormatosID.add(12, 13);
            if(sitio != 1){
                listFormatosID.remove(5);
            }
        } else {
            listFormatosID.add(0, 1);
            listFormatosID.add(1, 2);
            listFormatosID.add(2, 3);
            listFormatosID.add(3, 4);
            listFormatosID.add(4, 5);
            listFormatosID.add(5, 6);
            listFormatosID.add(6, 13);
            if(sitio != 1){
                listFormatosID.remove(5);
            }
        }
        int size = listFormatosID.size();
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            for (int i = 0; i < size; i++) {
                query = "INSERT INTO SYS_SecuenciaCaptura(SecuenciaID, UPMID, Sitio, FormatoID)VALUES(" + 1 + ", " + upmID + ", " + sitio + ", " + listFormatosID.get(i) + ")";
                st.executeUpdate(query);
                conn.commit();
            }
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo guardar la información de la secuencia 1", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos al insertar datos de la secuencia 1",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void insertSecuencia2(int upmID, int sitio) {
        FuncionesComunes funciones = new FuncionesComunes();
        //Solo módulo A y C
        List<Integer> listFormatosID = new ArrayList<>();
        Integer noSitio = funciones.sitioCapturaSueloCarbono(upmID, 3);
        if (noSitio == sitio) {
            listFormatosID.add(0, 1);//Módulo A
            listFormatosID.add(1, 2);
            listFormatosID.add(2, 3);
            listFormatosID.add(3, 4);
            listFormatosID.add(4, 5);
            listFormatosID.add(5, 6);
            listFormatosID.add(6, 9);
            listFormatosID.add(7, 10);
            listFormatosID.add(8, 11);
            listFormatosID.add(9, 12);
            listFormatosID.add(10, 13);
            listFormatosID.add(11, 7);//Módulo C
            listFormatosID.add(12, 8);
            if(sitio != 1){
                listFormatosID.remove(5);
            }
        } else {
            listFormatosID.add(0, 1);//
            listFormatosID.add(1, 2);
            listFormatosID.add(2, 3);
            listFormatosID.add(3, 4);
            listFormatosID.add(4, 5);
            listFormatosID.add(5, 6);
            listFormatosID.add(6, 13);
            listFormatosID.add(7, 7);//Módulo C
            listFormatosID.add(8, 8);
            if(sitio != 1){
                listFormatosID.remove(5);
            }
        }
        int size = listFormatosID.size();
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            for (int i = 0; i < size; i++) {
                query = "INSERT INTO SYS_SecuenciaCaptura(SecuenciaID, UPMID, Sitio, FormatoID)VALUES(" + 2 + ", " + upmID + ", " + sitio + ", " + listFormatosID.get(i) + ")";
                st.executeUpdate(query);
                conn.commit();
            }
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo guardar la información de la secuencia 2", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos al insertar datos de la secuencia 2 ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void insertSecuencia3(int upmID, int sitio) {
        FuncionesComunes funciones = new FuncionesComunes();
        //Solo módulo ACEG
        List<Integer> listFormatosID = new ArrayList<>();
        Integer noSitio = funciones.sitioCapturaSueloCarbono(upmID, 3);
        if (noSitio == sitio) {
            listFormatosID.add(0, 5);//Modulo A
            listFormatosID.add(1, 6);
            listFormatosID.add(2, 9);
            listFormatosID.add(3, 10);
            listFormatosID.add(4, 11);
            listFormatosID.add(5, 12);
            listFormatosID.add(6, 13);
            listFormatosID.add(7, 7);//Módulo C
            listFormatosID.add(8, 8);
            listFormatosID.add(9, 15);//Modulo E
            listFormatosID.add(10, 16);
            listFormatosID.add(11, 18);//Módulo G
            listFormatosID.add(12, 19);
            listFormatosID.add(13, 20);
            listFormatosID.add(14, 4);//Submuestra
            if(sitio != 1){
                listFormatosID.remove(1);
            }
        } else {
            listFormatosID.add(0, 5);//Modulo A
            listFormatosID.add(1, 6);
            listFormatosID.add(2, 13);
            listFormatosID.add(3, 7);//Módulo C
            listFormatosID.add(4, 8);
            listFormatosID.add(5, 18);//Módulo G
            listFormatosID.add(6, 19);
            listFormatosID.add(7, 20);
            listFormatosID.add(8, 4);//Submuestra
            if(sitio != 1){
                listFormatosID.remove(1);
            }
        }
        int size = listFormatosID.size();
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            for (int i = 0; i < size; i++) {
                query = "INSERT INTO SYS_SecuenciaCaptura(SecuenciaID, UPMID, Sitio, FormatoID)VALUES(" + 3 + ", " + upmID + ", " + sitio + ", " + listFormatosID.get(i) + ")";
                st.executeUpdate(query);
                conn.commit();
            }
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo guardar la secuencia 3", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos al insertar datos de la secuencia 3",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void insertSecuencia4(int upmID, int sitio) {
        FuncionesComunes funciones = new FuncionesComunes();
        //Módulo A y E
        List<Integer> listFormatosID = new ArrayList<>();
        Integer noSitio = funciones.sitioCapturaSueloCarbono(upmID, 3);
        if (noSitio == sitio) {
            listFormatosID.add(0, 1);//Módulo A
            listFormatosID.add(1, 2);
            listFormatosID.add(2, 3);
            listFormatosID.add(3, 4);
            listFormatosID.add(4, 5);
            listFormatosID.add(5, 6);
            listFormatosID.add(6, 7);
            listFormatosID.add(7, 8);
            listFormatosID.add(8, 9);
            listFormatosID.add(9, 10);
            listFormatosID.add(10, 11);
            listFormatosID.add(11, 12);
            listFormatosID.add(12, 13);
            listFormatosID.add(13, 15);//Modulo E
            listFormatosID.add(14, 16);
            if(sitio != 1){
                listFormatosID.remove(5);
            }
        } else {
            listFormatosID.add(0, 1);//Módulo A
            listFormatosID.add(1, 2);
            listFormatosID.add(2, 3);
            listFormatosID.add(3, 4);
            listFormatosID.add(4, 5);
            listFormatosID.add(5, 6);
            listFormatosID.add(6, 13);
            if(sitio != 1){
                listFormatosID.remove(5);
            }
        }
        int size = listFormatosID.size();
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            for (int i = 0; i < size; i++) {
                query = "INSERT INTO SYS_SecuenciaCaptura(SecuenciaID, UPMID, Sitio, FormatoID)VALUES(" + 4 + ", " + upmID + ", " + sitio + ", " + listFormatosID.get(i) + ")";
                st.executeUpdate(query);
                conn.commit();
            }
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo guardar la información de la secuencia 4 ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos al insertar datos de la secuencia 4",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
     public void insertSecuencia5(int upmID, int sitio) {
         FuncionesComunes funciones = new FuncionesComunes();
         //Módulo A, C, D y F
        List<Integer> listFormatosID = new ArrayList<>();
        Integer noSitio = funciones.sitioCapturaSueloCarbono(upmID, 3);
         if (noSitio == sitio) {
             listFormatosID.add(0, 1);//Módulo A
             listFormatosID.add(1, 2);
             listFormatosID.add(2, 5);
             listFormatosID.add(3, 6);
             listFormatosID.add(4, 9);
             listFormatosID.add(5, 10);
             listFormatosID.add(6, 11);
             listFormatosID.add(7, 12);
             listFormatosID.add(8, 13);
             listFormatosID.add(9, 7);//Módulo C
             listFormatosID.add(10, 8);
             listFormatosID.add(11, 14);//Modulo D
             listFormatosID.add(12, 4);
             listFormatosID.add(13, 17);//Modulo F
             if(sitio != 1){
                listFormatosID.remove(3);
            }
         } else {
             listFormatosID.add(0, 1);//Módulo A
             listFormatosID.add(1, 2);
             listFormatosID.add(2, 5);
             listFormatosID.add(3, 6);
             listFormatosID.add(4, 13);
             listFormatosID.add(5, 7);//Módulo C
             listFormatosID.add(6, 8);
             listFormatosID.add(7, 14);//Modulo D
             listFormatosID.add(8, 4);
             listFormatosID.add(9, 17);//Modulo F
             if(sitio != 1){
                listFormatosID.remove(3);
            }
         }
        int size = listFormatosID.size();
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            for (int i = 0; i < size; i++) {
                query = "INSERT INTO SYS_SecuenciaCaptura(SecuenciaID, UPMID, Sitio, FormatoID)VALUES(" + 5 + ", " + upmID + ", " + sitio + ", " + listFormatosID.get(i) + ")";
                st.executeUpdate(query);
                conn.commit();
            }
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo guardar la información de la secuencia 5 ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos al insertar datos de la secuencia 5 ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void insertSecuencia6(int upmID, int sitio) {
         FuncionesComunes funciones = new FuncionesComunes();
       //Módulo A, C y D
        List<Integer> listFormatosID = new ArrayList<>();
        Integer noSitio = funciones.sitioCapturaSueloCarbono(upmID, 3);
        if (noSitio == sitio) {
            listFormatosID.add(0, 1);//Módulo A
            listFormatosID.add(1, 2);
            listFormatosID.add(2, 5);
            listFormatosID.add(3, 6);
            listFormatosID.add(4, 9);
            listFormatosID.add(5, 10);
            listFormatosID.add(6, 11);
            listFormatosID.add(7, 12);
            listFormatosID.add(8, 13);
            listFormatosID.add(9, 7);//Módulo C
            listFormatosID.add(10, 8);
            listFormatosID.add(11, 14);//Modulo D
            listFormatosID.add(12, 4);
            if(sitio != 1){
                listFormatosID.remove(3);
            }
        } else {
            listFormatosID.add(0, 1);//Módulo A
            listFormatosID.add(1, 2);
            listFormatosID.add(2, 5);
            listFormatosID.add(3, 6);
            listFormatosID.add(4, 13);
            listFormatosID.add(5, 7);//Módulo C
            listFormatosID.add(6, 8);
            listFormatosID.add(7, 14);//Modulo D
            listFormatosID.add(8, 4);
            if(sitio != 1){
                listFormatosID.remove(3);
            }
        }
        int size = listFormatosID.size();
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            for (int i = 0; i < size; i++) {
                query = "INSERT INTO SYS_SecuenciaCaptura(SecuenciaID, UPMID, Sitio, FormatoID)VALUES(" + 6 + ", " + upmID + ", " + sitio + ", " + listFormatosID.get(i) + ")";
                st.executeUpdate(query);
                conn.commit();
            }
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo guardar la información de la secuencia 6 ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos al insertar datos de la secuencia 6 ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void insertSecuencia7(int upmID, int sitio) {
        FuncionesComunes funciones = new FuncionesComunes();
        //Módulo A, C, D y E
        List<Integer> listFormatosID = new ArrayList<>();
        Integer noSitio = funciones.sitioCapturaSueloCarbono(upmID, 3);
        if (noSitio == sitio) {
            listFormatosID.add(0, 1);//Módulo A
            listFormatosID.add(1, 2);
            listFormatosID.add(2, 5);
            listFormatosID.add(3, 6);
            listFormatosID.add(4, 9);
            listFormatosID.add(5, 10);
            listFormatosID.add(6, 11);
            listFormatosID.add(7, 12);
            listFormatosID.add(8, 13);
            listFormatosID.add(9, 7);//Módulo C
            listFormatosID.add(10, 8);
            listFormatosID.add(11, 14);//Modulo D
            listFormatosID.add(12, 4);
            listFormatosID.add(13, 15);//Modulo E
            listFormatosID.add(14, 16);
            if(sitio != 1){
                listFormatosID.remove(3);
            }
        } else {
            listFormatosID.add(0, 1);//Módulo A
            listFormatosID.add(1, 2);
            listFormatosID.add(2, 5);
            listFormatosID.add(3, 6);
            listFormatosID.add(4, 13);
            listFormatosID.add(5, 7);//Módulo C
            listFormatosID.add(6, 8);
            listFormatosID.add(7, 14);//Modulo D
            listFormatosID.add(8, 4);
            if(sitio != 1){
                listFormatosID.remove(3);
            }
        }
        int size = listFormatosID.size();
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            for (int i = 0; i < size; i++) {
                query = "INSERT INTO SYS_SecuenciaCaptura(SecuenciaID, UPMID, Sitio, FormatoID)VALUES(" + 7 + ", " + upmID + ", " + sitio + ", " + listFormatosID.get(i) + ")";
                st.executeUpdate(query);
                conn.commit();
            }
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo guardar la información de la secuencia 7"
                    , "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos al insertar datos de la secuencia 7",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
//Continuar a partir de esta secuencia.
    public void insertSecuencia8(int upmID, int sitio) {
        FuncionesComunes funciones = new FuncionesComunes();
        //Módulo A, C, D, E y F
        List<Integer> listFormatosID = new ArrayList<>();
        Integer noSitio = funciones.sitioCapturaSueloCarbono(upmID, 3);
        if (noSitio == sitio) {
            listFormatosID.add(0, 1);//Módulo A
            listFormatosID.add(1, 2);
            listFormatosID.add(2, 5);
            listFormatosID.add(3, 6);
            listFormatosID.add(4, 9);
            listFormatosID.add(5, 10);
            listFormatosID.add(6, 11);
            listFormatosID.add(7, 12);
            listFormatosID.add(8, 13);
            listFormatosID.add(9, 7);//Módulo C
            listFormatosID.add(10, 8);
            listFormatosID.add(11, 14);//Modulo D
            listFormatosID.add(12, 4);
            listFormatosID.add(13, 15);//Modulo E
            listFormatosID.add(14, 16);
            listFormatosID.add(15, 17);//Modulo F
            if(sitio != 1){
                listFormatosID.remove(3);
            }
        } else {
            listFormatosID.add(0, 1);//Módulo A
            listFormatosID.add(1, 2);
            listFormatosID.add(2, 5);
            listFormatosID.add(3, 6);
            listFormatosID.add(4, 13);
            listFormatosID.add(5, 7);//Módulo C
            listFormatosID.add(6, 8);
            listFormatosID.add(7, 14);//Modulo D
            listFormatosID.add(8, 4);
            listFormatosID.add(9, 17);//Modulo F
            if(sitio != 1){
                listFormatosID.remove(3);
            }
        }
        int size = listFormatosID.size();
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            for (int i = 0; i < size; i++) {
                query = "INSERT INTO SYS_SecuenciaCaptura(SecuenciaID, UPMID, Sitio, FormatoID)VALUES(" + 8 + ", " + upmID + ", " + sitio + ", " + listFormatosID.get(i) + ")";
                st.executeUpdate(query);
                conn.commit();
            }
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo guardar la información de la secuencia 8", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos al insertar datos de la secuencia 8",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void insertSecuencia9(int upmID, int sitio) {
        FuncionesComunes funciones = new FuncionesComunes();
        //Solo módulo A, C y E
        List<Integer> listFormatosID = new ArrayList<>();
        Integer noSitio = funciones.sitioCapturaSueloCarbono(upmID, 3);
        if (noSitio == sitio) {
            listFormatosID.add(0, 1);//Módulo A
            listFormatosID.add(1, 2);
            listFormatosID.add(2, 3);
            listFormatosID.add(3, 4);
            listFormatosID.add(4, 5);
            listFormatosID.add(5, 6);
            listFormatosID.add(6, 9);
            listFormatosID.add(7, 10);
            listFormatosID.add(8, 11);
            listFormatosID.add(9, 12);
            listFormatosID.add(10, 13);
            listFormatosID.add(11, 7);//Módulo C
            listFormatosID.add(12, 8);
            listFormatosID.add(13, 15);//Modulo E
            listFormatosID.add(14, 16);
            if(sitio != 1){
                listFormatosID.remove(5);
            }
        } else {
            listFormatosID.add(0, 1);//Módulo A
            listFormatosID.add(1, 2);
            listFormatosID.add(2, 3);
            listFormatosID.add(3, 4);
            listFormatosID.add(4, 5);
            listFormatosID.add(5, 6);
            listFormatosID.add(6, 13);
            listFormatosID.add(7, 7);//Módulo C
            listFormatosID.add(8, 8);
            if(sitio != 1){
                listFormatosID.remove(5);
            }
        }
        int size = listFormatosID.size();
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            for (int i = 0; i < size; i++) {
                query = "INSERT INTO SYS_SecuenciaCaptura(SecuenciaID, UPMID, Sitio, FormatoID)VALUES(" + 9 + ", " + upmID + ", " + sitio + ", " + listFormatosID.get(i) + ")";
                st.executeUpdate(query);
                conn.commit();
            }
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo guardar la información de la secuencia 9", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos al insertar datos de la secuencia 9",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void insertSecuencia10(int upmID, int sitio) {
        FuncionesComunes funciones = new FuncionesComunes();
        //Solo módulo A, C y H
        List<Integer> listFormatosID = new ArrayList<>();
        Integer noSitio = funciones.sitioCapturaSueloCarbono(upmID, 3);
        if (noSitio == sitio) {
            listFormatosID.add(0, 1);//Solo vegetación menor y cobertura
            listFormatosID.add(1, 2);//Módulo A
            listFormatosID.add(2, 3);
            listFormatosID.add(3, 4);
            listFormatosID.add(4, 5);
            listFormatosID.add(5, 6);
            listFormatosID.add(6, 9);
            listFormatosID.add(7, 10);
            listFormatosID.add(8, 11);
            listFormatosID.add(9, 12);
            listFormatosID.add(10, 13);
            listFormatosID.add(11, 7);//Módulo C
            listFormatosID.add(12, 8);
            listFormatosID.add(13, 21);//Módulo H
            listFormatosID.add(14, 22);
            listFormatosID.add(15, 23);
            if(sitio != 1){
                listFormatosID.remove(5);
            }
        } else {
            listFormatosID.add(0, 1);//Solo vegetación menor y cobertura
            listFormatosID.add(1, 2);//Módulo A
            listFormatosID.add(2, 3);
            listFormatosID.add(3, 4);
            listFormatosID.add(4, 5);
            listFormatosID.add(5, 6);
            listFormatosID.add(6, 13);
            listFormatosID.add(7, 7);//Módulo C
            listFormatosID.add(8, 8);
            listFormatosID.add(9, 21);//Módulo H
            listFormatosID.add(10, 22);
            listFormatosID.add(11,23);
            if(sitio != 1){
                listFormatosID.remove(5);
            }
        }
        int size = listFormatosID.size();
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            for (int i = 0; i < size; i++) {
                query = "INSERT INTO SYS_SecuenciaCaptura(SecuenciaID, UPMID, Sitio, FormatoID)VALUES(" + 10 + ", " + upmID + ", " + sitio + ", " + listFormatosID.get(i) + ")";
                st.executeUpdate(query);
                conn.commit();
            }
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo guardar la información de la secuencia 10", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos al insertar datos de la secuencia 10",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void insertSecuencia11(int upmID, int sitio) {
        FuncionesComunes funciones = new FuncionesComunes();
        List<Integer> listFormatosID = new ArrayList<>();
        Integer noSitio = funciones.sitioCapturaSueloCarbono(upmID, 3);
        if (noSitio == sitio) {
            listFormatosID.add(0, 1);//Solo vegetación menor y cobertura
            listFormatosID.add(1, 2);//Módulo A
            listFormatosID.add(2, 3);
            listFormatosID.add(3, 4);
            listFormatosID.add(4, 5);
            listFormatosID.add(5, 6);
            listFormatosID.add(6, 7);
            listFormatosID.add(7, 8);
            listFormatosID.add(8, 9);
            listFormatosID.add(9, 10);
            listFormatosID.add(10, 11);
            listFormatosID.add(11, 12);
            listFormatosID.add(12, 13);
            listFormatosID.add(13, 21);//Módulo H
            listFormatosID.add(14, 22);
            listFormatosID.add(15, 23);
            if(sitio != 1){
                listFormatosID.remove(5);
            }
        } else {
            listFormatosID.add(0, 1);//Solo vegetación menor y cobertura
            listFormatosID.add(1, 2);//Módulo A
            listFormatosID.add(2, 3);
            listFormatosID.add(3, 4);
            listFormatosID.add(4, 5);
            listFormatosID.add(5, 6);
            listFormatosID.add(6, 13);
            listFormatosID.add(7, 21);//Módulo H
            listFormatosID.add(8, 22);
            listFormatosID.add(9, 23);
            if(sitio != 1){
                listFormatosID.remove(5);
            }
        }
        int size = listFormatosID.size();
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            for (int i = 0; i < size; i++) {
                query = "INSERT INTO SYS_SecuenciaCaptura(SecuenciaID, UPMID, Sitio, FormatoID)VALUES(" + 11 + ", " + upmID + ", " + sitio + ", " + listFormatosID.get(i) + ")";
                st.executeUpdate(query);
                conn.commit();
            }
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo guardar la información de la secuencia 11", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos al insertar datos de la secuencia 11",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void insertSecuencia12(int upmID, int sitio) {
        FuncionesComunes funciones = new FuncionesComunes();
        //Módulo A, E y H
        List<Integer> listFormatosID = new ArrayList<>();
        Integer noSitio = funciones.sitioCapturaSueloCarbono(upmID, 3);
        System.err.println("Sitiodonde se tomará Suelos    "+noSitio);
        if (noSitio == sitio) {
            listFormatosID.add(0, 1);//Solo vegetación menor y cobertura
            listFormatosID.add(1, 2);//Módulo A
            listFormatosID.add(2, 3);
            listFormatosID.add(3, 4);
            listFormatosID.add(4, 5);
            listFormatosID.add(5, 6);
            listFormatosID.add(6, 7);
            listFormatosID.add(7, 8);
            listFormatosID.add(8, 9);
            listFormatosID.add(9, 10);
            listFormatosID.add(10, 11);
            listFormatosID.add(11, 12);
            listFormatosID.add(12, 13);
            listFormatosID.add(13, 15);//Modulo E
            listFormatosID.add(14, 16);
            listFormatosID.add(15, 21);//Módulo H
            listFormatosID.add(16, 22);
            listFormatosID.add(17, 23);
            if(sitio != 1){
                listFormatosID.remove(5);
            }
        } else {
            listFormatosID.add(0, 1);//Solo vegetación menor y cobertura
            listFormatosID.add(1, 2);//Módulo A
            listFormatosID.add(2, 3);
            listFormatosID.add(3, 4);
            listFormatosID.add(4, 5);
            listFormatosID.add(5, 6);
            listFormatosID.add(6, 13);
            listFormatosID.add(7, 21);//Módulo H
            listFormatosID.add(8, 22);
            listFormatosID.add(9, 23);
            if(sitio != 1){
                listFormatosID.remove(5);
            }
        }
        int size = listFormatosID.size();
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            for (int i = 0; i < size; i++) {
                query = "INSERT INTO SYS_SecuenciaCaptura(SecuenciaID, UPMID, Sitio, FormatoID)VALUES(" + 12 + ", " + upmID + ", " + sitio + ", " + listFormatosID.get(i) + ")";
                st.executeUpdate(query);
                conn.commit();
            }
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo guardar la información de la secuencia 12", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos al insertar datos de la secuencia 12",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void insertSecuencia13(int upmID, int sitio) {
        FuncionesComunes funciones = new FuncionesComunes();
       //Solo módulo A, C, E y H
        List<Integer> listFormatosID = new ArrayList<>();
        Integer noSitio = funciones.sitioCapturaSueloCarbono(upmID, 3);
        if (noSitio == sitio) {
            listFormatosID.add(0, 1);//Solo vegetación menor y cobertura
            listFormatosID.add(1, 2);//Módulo A
            listFormatosID.add(2, 3);
            listFormatosID.add(3, 4);
            listFormatosID.add(4, 5);
            listFormatosID.add(5, 6);
            listFormatosID.add(6, 9);
            listFormatosID.add(7, 10);
            listFormatosID.add(8, 11);
            listFormatosID.add(9, 12);
            listFormatosID.add(10, 13);
            listFormatosID.add(11, 7);//Módulo C
            listFormatosID.add(12, 8);
            listFormatosID.add(13, 15);//Modulo E
            listFormatosID.add(14, 16);
            listFormatosID.add(15, 21);//Módulo H
            listFormatosID.add(16, 22);
            listFormatosID.add(17, 23);
            if(sitio != 1){
                listFormatosID.remove(5);
            }
        } else {
            listFormatosID.add(0, 1);//Solo vegetación menor y cobertura
            listFormatosID.add(1, 2);//Módulo A
            listFormatosID.add(2, 3);
            listFormatosID.add(3, 4);
            listFormatosID.add(4, 5);
            listFormatosID.add(5, 6);
            listFormatosID.add(6, 13);
            listFormatosID.add(7, 7);//Módulo C
            listFormatosID.add(8, 8);
            listFormatosID.add(9, 21);//Módulo H
            listFormatosID.add(10, 22);
            listFormatosID.add(11, 23);
            if(sitio != 1){
                listFormatosID.remove(5);
            }
        }

        int size = listFormatosID.size();
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            for (int i = 0; i < size; i++) {
                query = "INSERT INTO SYS_SecuenciaCaptura(SecuenciaID, UPMID, Sitio, FormatoID)VALUES(" + 13 + ", " + upmID + ", " + sitio + ", " + listFormatosID.get(i) + ")";
                st.executeUpdate(query);
                conn.commit();
            }
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo guardar la información de la secuencia 13", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos al insertar datos de la secuencia 13",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void insertSecuencia14(int upmID, int sitio) {
        FuncionesComunes funciones = new FuncionesComunes();
         //Módulo A, E y G 
        List<Integer> listFormatosID = new ArrayList<>();
        Integer noSitio = funciones.sitioCapturaSueloCarbono(upmID, 3);
        if (noSitio == sitio) {
            listFormatosID.add(0, 5);//Módulo A
            listFormatosID.add(1, 6);
            listFormatosID.add(2, 7);
            listFormatosID.add(3, 8);
            listFormatosID.add(4, 9);
            listFormatosID.add(5, 10);
            listFormatosID.add(6, 11);
            listFormatosID.add(7, 12);
            listFormatosID.add(8, 13);
            listFormatosID.add(9, 15);//Modulo E
            listFormatosID.add(10, 16);
            listFormatosID.add(11, 18);//Módulo G
            listFormatosID.add(12, 19);
            listFormatosID.add(13, 20);
            listFormatosID.add(14, 4);
            if(sitio != 1){
                listFormatosID.remove(1);
            }
        } else {
            listFormatosID.add(0, 5);//Módulo A
            listFormatosID.add(1, 6);
            listFormatosID.add(2, 13);
            listFormatosID.add(3, 18);//Módulo G
            listFormatosID.add(4, 19);
            listFormatosID.add(5, 20);
            listFormatosID.add(6, 4);
            if(sitio != 1){
                listFormatosID.remove(1);
            }
        }
        int size = listFormatosID.size();
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            for (int i = 0; i < size; i++) {
                query = "INSERT INTO SYS_SecuenciaCaptura(SecuenciaID, UPMID, Sitio, FormatoID)VALUES(" + 14 + ", " + upmID + ", " + sitio + ", " + listFormatosID.get(i) + ")";
                st.executeUpdate(query);
                conn.commit();
            }
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo guardar la información de la secuencia 14 ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos al insertar datos de la secuencia 14",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void insertSecuencia15(int upmID, int sitio) {
        FuncionesComunes funciones = new FuncionesComunes();
        //Solo módulo A y G
        List<Integer> listFormatosID = new ArrayList<>();
        Integer noSitio = funciones.sitioCapturaSueloCarbono(upmID, 3);
        if (noSitio == sitio) {
            listFormatosID.add(0, 5);//Módulo A
            listFormatosID.add(1, 6);
            listFormatosID.add(2, 7);
            listFormatosID.add(3, 8);
            listFormatosID.add(4, 9);
            listFormatosID.add(5, 10);
            listFormatosID.add(6, 11);
            listFormatosID.add(7, 12);
            listFormatosID.add(8, 13);
            listFormatosID.add(9, 18);//Módulo G
            listFormatosID.add(10, 19);
            listFormatosID.add(11, 20);
            listFormatosID.add(12, 4);
            if(sitio != 1){
                listFormatosID.remove(1);
            }
        } else {
            listFormatosID.add(0, 5);//Módulo A
            listFormatosID.add(1, 6);
            listFormatosID.add(2, 13);
            listFormatosID.add(3, 18);//Módulo G
            listFormatosID.add(4, 19);
            listFormatosID.add(5, 20);
            listFormatosID.add(6, 4);
            if(sitio != 1){
                listFormatosID.remove(1);
            }
        }
        int size = listFormatosID.size();
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            for (int i = 0; i < size; i++) {
                query = "INSERT INTO SYS_SecuenciaCaptura(SecuenciaID, UPMID, Sitio, FormatoID)VALUES(" + 15 + ", " + upmID + ", " + sitio + ", " + listFormatosID.get(i) + ")";
                st.executeUpdate(query);
                conn.commit();
            }
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo guardar la información de la secuencia 1", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos al insertar datos de la secuencia 1",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void deleteSecuencia(int upmID) {
        query = "DELETE FROM SYS_SecuenciaCaptura WHERE UPMID= " + upmID;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo eliminar la informacion de la secuencia del UPM " + upmID, "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos al eliminar los datos de la secuencia del upm " + upmID,
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void updateSecuencia(CESitio ceSitio, int formatoID, int status) {
        query = "UPDATE SYS_SecuenciaCaptura SET Estatus = " + status + " WHERE "
                + "FormatoID= " + formatoID + " AND UPMID= " + ceSitio.getUpmID() + " AND Sitio= " + ceSitio.getSitio();
        //Estatus 1 = capturado, 0 = no capturado, -1 No capturado
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo modificar la informacion de la secuencia de captura", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos al modificar los datos de secuencia de captura",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public Integer getSiguienteCaptura(int upmID, int sitio){
        this.query = "SELECT UPMID, Sitio, FormatoID FROM SYS_SecuenciaCaptura WHERE Capturado = 0 AND UPMID= " + upmID + " AND Sitio= " + sitio + "ORDER BY ROWID ASC LIMIT 1";
        Integer formatoID = 0;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
               formatoID = rs.getInt("FormaID");
            }
            return formatoID;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! No se pudo obtener el formato a capturar en la secuencia de captura", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en datos al obtener el siguiente formato en la secuencia", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public Integer getSecuencia(int upmID){
        this.query = "SELECT UPMID, secuenciaID FROM UPM_MallaPuntos WHERE UPMID= " + upmID;
        Integer secuenciaID = 0;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
               secuenciaID = rs.getInt("secuenciaID");
            }
            return secuenciaID;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! No se pudo obtener el id de la secuencia de captura", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return 0;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al obtener el id de secuecia de captura", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        } 
    }
}
