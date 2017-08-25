package gob.conafor.upm.mod;

import gob.conafor.conn.dat.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class CDEpifitas {

    private String query;
    private int epifitaID;

    public List<CatETipoEpifita> getTipoEpifitas() {
        query = "SELECT TipoEpifitaID, Nombre FROM CAT_TipoEpifita";
        List<CatETipoEpifita> listEpifita = new ArrayList<>();
        Connection conn = LocalConnectionCat.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                CatETipoEpifita epifita = new CatETipoEpifita();
                epifita.setTipoEpifitaID(rs.getInt("TipoEpifitaID"));
                epifita.setNombre(rs.getString("Nombre"));

                listEpifita.add(epifita);
            }

            //listEpifita.add(0, null);
            return listEpifita;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo obtener la información de los tipos de epifitas ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos de tipos de epifitas", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public List<CatETipoEpifita> getTipoEpiftasNoCapturadas(int UPMID) {
        List<CatETipoEpifita> listEpifita = getTipoEpifitas();
        query = "SELECT UPMID, ClaseTipoID FROM UPM_Epifita WHERE UPMID= " + UPMID;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                CatETipoEpifita caTipoEpifita = new CatETipoEpifita();
                caTipoEpifita.setTipoEpifitaID(rs.getInt("ClaseTipoID"));
                listEpifita.remove(caTipoEpifita);
            }
            listEpifita.add(0, null);
            return listEpifita;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener los tipos de epifitas",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en los tipos de epifitas", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        return null;
    }

    public List<CatEPresenciaEpifita> getPresenciaEpifita() {
        query = "SELECT PresenciaEpifitaID, Descripcion FROM CAT_PresenciaEpifita";
        List<CatEPresenciaEpifita> listPresencia = new ArrayList<>();
        Connection conn = LocalConnectionCat.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                CatEPresenciaEpifita presencia = new CatEPresenciaEpifita();
                presencia.setPresenciaEpifitaID(rs.getInt("PresenciaEpifitaID"));
                presencia.setDescripcion(rs.getString("Descripcion"));
                listPresencia.add(presencia);
            }
            listPresencia.add(0, null);
            return listPresencia;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo obtener la información de los tipos de presencia de epifita ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en tipos de presencia de epifita", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public DefaultTableModel getTablaEpifitas(int UPMID) {
        query = "SELECT EpifitaID, UPMID, Tipo, PresenciaTroncos, PresenciaRamas FROM VW_Epifitas WHERE UPMID=" + UPMID;
        String[] encabezados = {"EpifitaID", "UPMID", "Tipo", "Presencia troncos", "Presencia ramas"};
        DefaultTableModel epifitaModel = new DefaultTableModel(null, encabezados);
        Object[] datosEpifitas = new Object[5];
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                datosEpifitas[0] = rs.getInt("EpifitaID");
                datosEpifitas[1] = rs.getInt("UPMID");
                datosEpifitas[2] = rs.getString("Tipo");
                datosEpifitas[3] = rs.getString("PresenciaTroncos");
                datosEpifitas[4] = rs.getString("PresenciaRamas");

                epifitaModel.addRow(datosEpifitas);
            }
            st.close();
            rs.close();
            return epifitaModel;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al obtener los datos de la vista de epifitas ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en para la vista de epifitas", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void insertEpifita(CEEpifita epifita) {
        query = "INSERT INTO UPM_Epifita(UPMID, ClaseTipoID, PresenciaTroncosID, PresenciaRamasID)VALUES(" + epifita.getUpmID() + ", "
                + epifita.getClaseTipoID() + ", " + epifita.getPresenciaTroncosID() + ", " + epifita.getPresenciaRamasID() + ")";
        //String query2 = "SELECT last_insert_rowid()";
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            //ResultSet rs = st.executeQuery(query2);
            //this.epifitaID = rs.getInt(1);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo guardar la informacion de epifita", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos al insertar datos de epifitas ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void updateEpifita(CEEpifita epifita) {
        query = "UPDATE UPM_Epifita SET ClaseTipoID= " + epifita.getClaseTipoID() + ", PresenciaTroncosID= " + epifita.getPresenciaTroncosID()
                + ", PresenciaRamasID= " + epifita.getPresenciaRamasID() + " WHERE EpifitaID= " + epifita.getEpifitaID();
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo modificar la información de epifitas ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la modificación de epifitas", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void deleteEpifita(int epifitaID) {
        query = "DELETE FROM UPM_Epifita WHERE EpifitaID= " + epifitaID;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo eliminar el registro de epifita ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos  al eliminar el registro de epífita",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void deleteEpifitasSitio(int UpmID) {
        query = "DELETE FROM UPM_Epifita WHERE UPMID= " + UpmID;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo eliminar las epífitas por sitio "
                    + e.getClass().getName() + " : " + e.getMessage(), "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos  al eliminar las epífitas por sitio",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public CEEpifita getRegistroEpifitas(int epifitaID) {
        CEEpifita ceEpifita = new CEEpifita();
        query = "SELECT EpifitaID, ClaseTipoID, PresenciaTroncosID, PresenciaRamasID FROM UPM_Epifita "
                + "WHERE EpifitaID= " + epifitaID;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                ceEpifita.setClaseTipoID(rs.getInt("ClaseTipoID"));
                ceEpifita.setPresenciaTroncosID(rs.getInt("PresenciaTroncosID"));
                ceEpifita.setPresenciaRamasID(rs.getInt("PresenciaRamasID"));
            }
            return ceEpifita;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al obtener el registro de epifitas", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al obtener el registro de epifitas", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public boolean validarTablaEpifitas(int upmID) {
        query = "SELECT * FROM UPM_Epifita WHERE UPMID= " + upmID;
        boolean vacio = true;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                vacio = false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al validar la presencia de datos en la tabla de epifitas ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en datos de validar tabla de epifitas ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        return vacio;
    }
}
