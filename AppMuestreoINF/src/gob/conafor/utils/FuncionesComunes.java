package gob.conafor.utils;

import gob.conafor.conn.dat.LocalConnection;
import gob.conafor.ini.vie.Main;
import gob.conafor.sitio.mod.CESitio;
import gob.conafor.sys.mod.CDSecuencia;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class FuncionesComunes {

    private String query;
    private CDSecuencia navegacion = new CDSecuencia();

    public void reiniciarComboModel(JComboBox nombre) {
        DefaultComboBoxModel model = (DefaultComboBoxModel) nombre.getModel();
        model.removeAllElements();
    }
    
    public void reiniciarTabla(JTable nombre){
        DefaultTableModel model = (DefaultTableModel) nombre.getModel();
        model.setRowCount(0);
    }

    public boolean validarSeccionCapturada(String nombreTabla, CESitio ceSitio) {
        this.query = "SELECT * FROM " + nombreTabla + " WHERE SitioID= " + ceSitio.getSitioID();
        boolean vacio = true;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                vacio = false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al revisar si hay datos en una seccion "
                    + e.getClass().getName() + " : " + e.getMessage(), "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en datos al revisar si hay datos en una seccion "
                        + e.getClass().getName() + " : " + e.getMessage(), "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        return vacio;
    }

    public boolean validarAccesibilidadSitio3(int upmID, int sitio) {
        this.query = "SELECT UPMID, Sitio, SitioAccesible FROM SITIOS_Sitio WHERE UPMID= " + upmID + " AND Sitio= 3" + " AND SitioAccesible= 1";
        boolean vacio = false;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                vacio = true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al revisar el sitio 3 de levantamiento de suelo y carbono "
                    + e.getClass().getName() + " : " + e.getMessage(), "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al revisar el sitio 3 de levantamiento de suelo y carbono"
                        + e.getClass().getName() + " : " + e.getMessage(), "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        return vacio;
    }
    
    public boolean validarAccesibilidadSitio4(int upmID, int sitio) {
        this.query = "SELECT UPMID, Sitio, SitioAccesible FROM SITIOS_Sitio WHERE UPMID= " + upmID + " AND Sitio= 4" + " AND SitioAccesible= 1";
        boolean vacio = false;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                vacio = true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al revisar el sitio 4 de levantamiento de suelo y carbono "
                    + e.getClass().getName() + " : " + e.getMessage(), "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al revisar el sitio 4 de levantamiento de suelo y carbono"
                        + e.getClass().getName() + " : " + e.getMessage(), "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        return vacio;
    }
    
    public boolean validarAccesibilidadSitio2(int upmID, int sitio) {
        this.query = "SELECT UPMID, Sitio, SitioAccesible FROM SITIOS_Sitio WHERE UPMID= " + upmID + " AND Sitio= 2" + " AND SitioAccesible= 1";
        boolean vacio = false;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                vacio = true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al revisar el sitio 2 de levantamiento de suelo y carbono "
                    + e.getClass().getName() + " : " + e.getMessage(), "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al revisar el sitio 2 de levantamiento de suelo y carbono"
                        + e.getClass().getName() + " : " + e.getMessage(), "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        return vacio;
    }
    
    public Integer sitioCapturaSueloCarbono(int upm, int sitio) {
        Integer sitioLevantamiento = null;
        if (validarAccesibilidadSitio3(upm, sitio)) {
            sitioLevantamiento = 3;
        } else if (validarAccesibilidadSitio4(upm, sitio)) {
            sitioLevantamiento = 4;
        } else if (validarAccesibilidadSitio2(upm, sitio)) {
            sitioLevantamiento = 2;
        } else {
            sitioLevantamiento = 1;
        }
        return sitioLevantamiento;
    }
    
    public void manipularBotonesMenuPrincipal(boolean enCaptura) {
        if (enCaptura == true) {
            Main.main.menGuardarArchivo.setEnabled(false);
            Main.main.btnCrearUPM.setEnabled(false);
            Main.main.btnCrearSitio.setEnabled(false);
            Main.main.btnCapturarModulos.setEnabled(false);
            Main.main.btnContinuarModulos.setEnabled(false);
            Main.main.btnColectaBotanica.setEnabled(false);
            Main.main.btnRevisarUPM.setEnabled(false);
            Main.main.btnRevisionSitios.setEnabled(false);
            Main.main.btnRevisarModulos.setEnabled(false);
            Main.main.btnVerReportes.setEnabled(false);
            Main.main.btnVerReportes.setEnabled(false);
            Main.main.scrpanelModulos.setVisible(false);
        } else {
            Main.main.btnCrearUPM.setEnabled(true);
            Main.main.menGuardarArchivo.setEnabled(true);
            Main.main.btnCrearSitio.setEnabled(navegacion.habilitarCapturaSitio());
            Main.main.btnCapturarModulos.setEnabled(navegacion.habilitarCapturaModulos());
            Main.main.btnContinuarModulos.setEnabled(navegacion.habilitarContinuarModulos());
            Main.main.btnColectaBotanica.setEnabled(navegacion.habilitarColectaBotanica());
            Main.main.btnRevisarUPM.setEnabled(navegacion.habilitarRevisarUPM());
            Main.main.btnRevisionSitios.setEnabled(navegacion.habilitarRevisarSitios());
            Main.main.btnRevisarModulos.setEnabled(navegacion.habilitarContinuarModulos());
            Main.main.btnVerReportes.setEnabled(true);
            Main.main.scrpanelModulos.setVisible(true);
        }
    }
    
    public boolean habilitarCheckBox(String tabla, Integer sitioID){
        this.query = "SELECT SitioID FROM " + tabla + " WHERE SitioID= " + sitioID;
        boolean vacio = false;
        //System.out.println("FuncionesComunes Linea 172="+this.query);
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                vacio = true;
            }
            st.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al revisar el sitio 2 de levantamiento de suelo y carbono "
                    + e.getClass().getName() + " : " + e.getMessage(), "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al revisar el sitio 2 de levantamiento de suelo y carbono"
                        + e.getClass().getName() + " : " + e.getMessage(), "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        return vacio;
    }
    
  /*  public static void main(String []args ){
        FuncionesComunes f = new FuncionesComunes();
        
        System.out.println(f.sitioCapturaSueloCarbono(384, 3));
    }*/
    public int buscarSecuencia(int UPMID){
        this.query = "SELECT SecuenciaID FROM UPM_MallaPuntos WHERE UPMID=" + UPMID;
        int secuenciaID=0;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                secuenciaID=rs.getInt("SecuenciaID");
            }
            st.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al revisar el sitio 2 de levantamiento de suelo y carbono "
                    + e.getClass().getName() + " : " + e.getMessage(), "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al revisar el sitio 2 de levantamiento de suelo y carbono"
                        + e.getClass().getName() + " : " + e.getMessage(), "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        return secuenciaID;
    }
}
