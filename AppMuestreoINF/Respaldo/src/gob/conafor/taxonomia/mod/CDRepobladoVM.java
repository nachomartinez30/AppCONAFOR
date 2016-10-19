package gob.conafor.taxonomia.mod;

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

public class CDRepobladoVM {

    private String query;
    private CERepobladoVM ceRepoblado = new CERepobladoVM();
    private int repobladoVMID;

    public CERepobladoVM getRegistroRepobladoVM(int repobladoVMID) {
        query = "SELECT rep.RepobladoVMID, rep.FormaVidaID, rep.FamiliaID, rep.GeneroID, rep.EspecieID, rep.InfraespecieID, rep.NombreComun, "
                + "rep.Frecuencia50, rep.PorcentajeCobertura50, rep.Frecuencia51200, rep.PorcentajeCobertura51200, rep.Frecuencia200, "
                + "rep.PorcentajeCobertura200, rep.VigorID, ad1.AgenteDanioID AS AgenteDanio1, ad1.SeveridadID AS Severidad1, ad2.AgenteDanioID AS AgenteDanio2, ad2.SeveridadID AS Severidad2, "
                + "ClaveColecta FROM TAXONOMIA_RepobladoVM rep "
                + "LEFT JOIN REPOBLADO_AgenteDanio ad1 ON rep.RepobladoVMID = ad1.RepobladoVMID AND ad1.NumeroDanio = 1 "
                + "LEFT JOIN REPOBLADO_AgenteDanio ad2 ON rep.RepobladoVMID = ad2.RepobladoVMID AND ad2.NumeroDanio = 2 "
                + "WHERE rep.RepobladoVMID= " + repobladoVMID;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                this.ceRepoblado.setFormaVidaID(rs.getInt("FormaVidaID"));
                this.ceRepoblado.setFamiliaID(rs.getInt("FamiliaID"));
                this.ceRepoblado.setGeneroID(rs.getInt("GeneroID"));
                this.ceRepoblado.setEspecieID(rs.getInt("EspecieID"));
                this.ceRepoblado.setInfraespecieID(rs.getInt("InfraespecieID"));
                this.ceRepoblado.setNombreComun(rs.getString("NombreComun"));
                this.ceRepoblado.setFrecuencia50(rs.getInt("Frecuencia50"));
                this.ceRepoblado.setPorcentajeCobertura50(rs.getInt("PorcentajeCobertura50"));
                this.ceRepoblado.setFrecuencia51200(rs.getInt("Frecuencia51200"));
                this.ceRepoblado.setPorcentajeCobertura51200(rs.getInt("PorcentajeCobertura51200"));
                this.ceRepoblado.setFrecuencia200(rs.getInt("Frecuencia200"));
                this.ceRepoblado.setPorcentajeCobertura200(rs.getInt("PorcentajeCobertura200"));
                this.ceRepoblado.setAgenteDanio1ID(rs.getInt("AgenteDanio1"));
                this.ceRepoblado.setSeveridad1ID(rs.getInt("Severidad1"));
                this.ceRepoblado.setAgenteDanio2ID(rs.getInt("AgenteDanio2"));
                this.ceRepoblado.setSeveridad2ID(rs.getInt("Severidad2"));
                this.ceRepoblado.setVigorID(rs.getInt("VigorID"));
                this.ceRepoblado.setClaveColecta(rs.getString("ClaveColecta"));
            }
            st.close();
            rs.close();
            return ceRepoblado;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al obtener los datos de repoblado vegetacion menor ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al obtener el repoblado vegetación menor ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void insertRepobladoVM(CERepobladoVM ceRepoblado) {
        query = "INSERT INTO TAXONOMIA_RepobladoVM(SitioID, Consecutivo, FormaVidaID, FamiliaID, GeneroID, EspecieID, InfraespecieID, "
                + "NombreComun, Frecuencia50, PorcentajeCobertura50, Frecuencia51200, PorcentajeCobertura51200, "
                + "Frecuencia200, PorcentajeCobertura200, VigorID)VALUES(" + ceRepoblado.getSitioID() + "," + ceRepoblado.getConsecutivo()
                + ", " + ceRepoblado.getFormaVidaID() + ", " + ceRepoblado.getFamiliaID() + ", " + ceRepoblado.getGeneroID() + ", " + ceRepoblado.getEspecieID()
                + ", " + ceRepoblado.getInfraespecieID() + ", '" + ceRepoblado.getNombreComun() + "', "
                + ceRepoblado.getFrecuencia50() + ", " + ceRepoblado.getPorcentajeCobertura50() + ", " + ceRepoblado.getFrecuencia51200()
                + ", " + ceRepoblado.getPorcentajeCobertura51200() + ", " + ceRepoblado.getFrecuencia200() + ", " + ceRepoblado.getPorcentajeCobertura200()
                + ", " + ceRepoblado.getVigorID() + ")";
        String query2 = "SELECT last_insert_rowid()";
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            ResultSet rs = st.executeQuery(query2);
            this.repobladoVMID = rs.getInt(1);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo guardar la informacion en repoblado vegetacion menor ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos al insertar datos de arbolado ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void updateRepobladoVM(CERepobladoVM ceRepoblado) {
        query = "UPDATE TAXONOMIA_RepobladoVM SET FormaVidaID= " + ceRepoblado.getFormaVidaID() + ", FamiliaID= " + ceRepoblado.getFamiliaID()
                + ", GeneroID= " + ceRepoblado.getGeneroID() + ", EspecieID= " + ceRepoblado.getEspecieID() + ", InfraespecieID= "
                + ceRepoblado.getInfraespecieID() + ", NombreComun= '" + ceRepoblado.getNombreComun() + "'"
                + ", Frecuencia50= " + ceRepoblado.getFrecuencia50() + ", PorcentajeCobertura50= " + ceRepoblado.getPorcentajeCobertura50()
                + ", Frecuencia51200= " + ceRepoblado.getFrecuencia51200() + ", PorcentajeCobertura51200= " + ceRepoblado.getPorcentajeCobertura51200()
                + ", Frecuencia200= " + ceRepoblado.getPorcentajeCobertura200() + ", VigorID= " + ceRepoblado.getVigorID() + " WHERE RepobladoVMID= "
                + ceRepoblado.getRepobladoVMID();
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo modificar la información de repoblado vegetación menor ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la modificación de repoblado vegetación menor ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void deleteRepobladoVM(int repobladoVMID) {
        query = "DELETE FROM TAXONOMIA_RepobladoVM WHERE RepobladoVMID =" + repobladoVMID;
        Connection conn = LocalConnection.getConnection();
        String query2 = "SELECT last_insert_rowid()";
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            ResultSet rs = st.executeQuery(query2);
            this.repobladoVMID = rs.getInt(1);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo eliminar la información de repoblado vegetación menor ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos  al eliminar el repoblado de vegetació menor ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public DefaultTableModel getTablaRepobladoVM(int sitioID) {
        query = "SELECT RepobladoVMID, SitioID, Consecutivo, FormaVida, Familia, Genero, Especie, Infraespecie, NombreComun, "
                + "Frecuencia50, PorcentajeCobertura50, Frecuencia51200, PorcentajeCobertura51200, Frecuencia200, PorcentajeCobertura200, "
                + "Agente1, Severidad1, Agente2, Severidad2, Vigor, ClaveColecta FROM VW_RepobladoVM WHERE SitioID= " + sitioID;
        String[] encabezados = {"RepobladoVMID", "SitioID", "Consecutivo", "Forma de vida", "Familia", "Genero", "Especie", "Infraespecie", "Nombre comun",
            "<=50 cm", "%Cobetura", "51-200cm", "%Cobertura", ">=200 cm", "%Cobertura", "Agente 1", "Severidad 1", "Agente 2", "Severidad 2", "Vigor", "Clave de colecta"};
        DefaultTableModel repobladoModel = new DefaultTableModel(null, encabezados);
        Object[] datosRepoblado = new Object[21];
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                datosRepoblado[0] = rs.getInt("RepobladoVMID");
                datosRepoblado[1] = rs.getInt("SitioID");
                datosRepoblado[2] = rs.getInt("Consecutivo");
                datosRepoblado[3] = rs.getString("FormaVida");
                datosRepoblado[4] = rs.getString("Familia");
                datosRepoblado[5] = rs.getString("Genero");
                datosRepoblado[6] = rs.getString("Especie");
                datosRepoblado[7] = rs.getString("Infraespecie");
                datosRepoblado[8] = rs.getString("NombreComun");
                datosRepoblado[9] = rs.getString("Frecuencia50");
                datosRepoblado[10] = rs.getString("PorcentajeCobertura50");
                datosRepoblado[11] = rs.getString("Frecuencia51200");
                datosRepoblado[12] = rs.getString("PorcentajeCobertura51200");
                datosRepoblado[13] = rs.getString("Frecuencia200");
                datosRepoblado[14] = rs.getString("PorcentajeCobertura200");
                datosRepoblado[15] = rs.getString("Agente1");
                datosRepoblado[16] = rs.getString("Severidad1");
                datosRepoblado[17] = rs.getString("Agente2");
                datosRepoblado[18] = rs.getString("Severidad2");
                datosRepoblado[19] = rs.getString("Vigor");
                datosRepoblado[20] = rs.getString("ClaveColecta");
                repobladoModel.addRow(datosRepoblado);
            }
            st.close();
            rs.close();
            return repobladoModel;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al obtener los datos de la vista de repoblado vegetación menor ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en para la vista de repoblado vegetación menor ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void enumerarConsecutivo(int sitioID) {
        List<Integer> listRepobladoID = getRepobladoVMID(sitioID);
        Connection conn = LocalConnection.getConnection();
        if (listRepobladoID != null) {
            int size = listRepobladoID.size();
            int consecutivo = 1;
            try {
                for (int i = 0; i < size; i++) {
                    Statement st = conn.createStatement();
                    query = "UPDATE TAXONOMIA_RepobladoVM SET Consecutivo= " + consecutivo + "  WHERE RepobladoVMID= " + listRepobladoID.get(i);
                    st.executeUpdate(query);
                    conn.commit();
                    consecutivo++;
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,
                        "Error! al enumerar el consecutivo de repoblado vegetación menor ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al enumerar el continuo de repoblado vegetación menor ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private List<Integer> getRepobladoVMID(int sitioID) {
        List<Integer> listRepobladoID = new ArrayList<>();
        query = "SELECT RepobladoVMID, SitioID FROM TAXONOMIA_RepobladoVM WHERE SitioID= " + sitioID + " ORDER BY RepobladoVMID ASC";
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                listRepobladoID.add(rs.getInt("RepobladoVMID"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener los datos del repoblado vegetacion menor id ",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en lista de repoblado vegetación menor id ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        return listRepobladoID;
    }

    public void deleteRepobladoSitio(int sitioID) {
        query = "DELETE FROM TAXONOMIA_RepobladoVM WHERE SitioID= " + sitioID;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo eliminar la información de repoblado vegetación menor en sitio", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos  al eliminar el registro de repoblado vegetación menor ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public boolean validarHayRegistros(int sitioID) {
        query = "SELECT * FROM TAXONOMIA_RepobladoVM WHERE SitioID= " + sitioID;
        boolean vacio = false;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                vacio = true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al obtener los datos de la tabla de sotobosque ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en datos de validar tabla de sotobosque ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        return vacio;
    }

    public int getLastRepobladoVMID() {
        return this.repobladoVMID;
    }

    public void updateRepobladoFuera(CESitio ceSitio) {
        query = "UPDATE SITIOS_Sitio SET RepobladoFuera= " + ceSitio.getRepobladoFuera() + ", PorcentajeRepoblado= " + ceSitio.getPorcentajeRepoblado()
                + " WHERE SitioID= " + ceSitio.getSitioID();
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo modificar la información de repoblado fuera ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la modificación de repoblado fuera ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
