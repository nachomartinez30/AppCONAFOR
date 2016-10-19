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

public class CDSotoBosque {

    private String query;
    
    public int getSitioID(int UPMID, int sitio) {
        query = "SELECT SitioID, UPMID, Sitio FROM SITIOS_Sitio WHERE UPMID= " + UPMID + " AND Sitio= " + sitio;
        Connection conn = LocalConnection.getConnection();
        int sitioID = 0;
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                sitioID = rs.getInt("SitioID");
            }
            return sitioID;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al obtener el Id del sitio en sotobosque ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return sitioID;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al obtener el id del sitio en sotobosque "
                       , "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public boolean validarTablaSotobosque(int sitioID) {
        query = "SELECT * FROM TAXONOMIA_SotoBosque WHERE SitioID= " + sitioID;
        boolean vacio = true;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                vacio = false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al obtener los datos de la tabla de sotobosque "
                   , "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en datos de validar tabla de sotobosque "
                        , "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        return vacio;
    }

    public DefaultTableModel getTablaSotoBosque(int sitioID) {
        query = "SELECT SotoBosqueID, SitioID, Consecutivo, Familia, Genero, Especie, Infraespecie, NombreComun, Frecuencia025150, "
                + "Cobertura025150, Frecuencia151275, Cobertura151275, Frecuencia275, Cobertura275, Vigor, ClaveDanio, "
                + "PorcentajeDanio, ClaveColecta FROM VW_SotoBosque WHERE SitioID= " + sitioID;
        String[] encabezados = {"SotoBosqueID", "SitioID", "Consecutivo", "Familia", "Genero", "Especie", "Infraespecie", "Nombre comun",
            "Frecuencia 0.25-1.50", "Cobertura 0.25-1.50", "Frecuencia 1.51-2.75", "Cobertura 1.51-2.75", "Frecuencia 2.75", "Cobertura 2.75",
            "Vigor", "Daño", "Porcentaje daño", "Clave de colecta"};
        DefaultTableModel sotoBosqueModel = new DefaultTableModel(null, encabezados);
        Object[] datosSotoBosque = new Object[18];
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                datosSotoBosque[0] = rs.getInt("SotoBosqueID");
                datosSotoBosque[1] = rs.getInt("SitioID");
                datosSotoBosque[2] = rs.getInt("Consecutivo");
                datosSotoBosque[3] = rs.getString("Familia");
                datosSotoBosque[4] = rs.getString("Genero");
                datosSotoBosque[5] = rs.getString("Especie");
                datosSotoBosque[6] = rs.getString("Infraespecie");
                datosSotoBosque[7] = rs.getString("NombreComun");
                datosSotoBosque[8] = rs.getString("Frecuencia025150");
                datosSotoBosque[9] = rs.getString("Cobertura025150");
                datosSotoBosque[10] = rs.getString("Frecuencia151275");
                datosSotoBosque[11] = rs.getString("Cobertura151275");
                datosSotoBosque[12] = rs.getString("Frecuencia275");
                datosSotoBosque[13] = rs.getString("Cobertura275");
                datosSotoBosque[14] = rs.getString("Vigor");
                datosSotoBosque[15] = rs.getString("ClaveDanio");
                datosSotoBosque[16] = rs.getString("PorcentajeDanio");
                datosSotoBosque[17] = rs.getString("ClaveColecta");
                sotoBosqueModel.addRow(datosSotoBosque);
            }
            st.close();
            rs.close();
            return sotoBosqueModel;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al obtener los datos de la vista de sotobosque "
                   , "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en para la vista de sotobosque"
                        , "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public List<CESotoBosque> getDatosSotoBosque(int sitioID) {
        query = "SELECT SitioID, FamiliaID, GeneroID, EspecieID, InfraespecieID, NombreComun, EsColecta, Frecuencia025150, "
                + "Cobertura025150, Frecuencia151275, Cobertura151275, Frecuencia275, Cobertura275, VigorID, DanioID, "
                + "PorcentajeDanio FROM TAXONOMIA_SotoBosque  WHERE SitioID= " + sitioID;
        Connection conn = LocalConnection.getConnection();
        List<CESotoBosque> listSotoBosque = new ArrayList<>();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                CESotoBosque sotoBosque = new CESotoBosque();
                sotoBosque.setSitioID(rs.getInt("SitioID"));
                sotoBosque.setFamiliaID(rs.getInt("FamiliaID"));
                sotoBosque.setGeneroID(rs.getInt("GeneroID"));
                sotoBosque.setEspecieID(rs.getInt("EspecieID"));
                sotoBosque.setInfraespecieID(rs.getInt("InfraespecieID"));
                sotoBosque.setNombreComun(rs.getString("NombreComun"));
                sotoBosque.setEsColecta(rs.getInt("EsColecta"));
                sotoBosque.setFrecuecia025150(rs.getInt("Frecuencia025150"));
                sotoBosque.setCobertura025150(rs.getInt("Cobertura025150"));
                sotoBosque.setFrecuencia151275(rs.getInt("Frecuencia151275"));
                sotoBosque.setCobertura151275(rs.getInt("Cobertura151275"));
                sotoBosque.setFrecuencia275(rs.getInt("Frecuencia275"));
                sotoBosque.setCobertura275(rs.getInt("Cobertura275"));
                sotoBosque.setVigorID(rs.getInt("VigorId"));
                sotoBosque.setDanioID(rs.getInt("DanioID"));
                sotoBosque.setPorcentajeDanio(rs.getInt("PorcentajeDanio"));
                listSotoBosque.add(sotoBosque);
            }
            st.close();
            rs.close();
            return listSotoBosque;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al obtener los datos de sotobosque "
                    , "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane
                        .showMessageDialog(null,
                                "Error! al cerrar la base de datos en datos de sotobosque",
                                "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void insertDatosSotoBosque(CESotoBosque sotoBosque) {
        query = "INSERT INTO TAXONOMIA_SotoBosque(SitioID, FamiliaID, GeneroID, EspecieID, InfraespecieID, NombreComun, "
                + "Frecuencia025150, Cobertura025150, Frecuencia151275, Cobertura151275, Frecuencia275, Cobertura275, vigorID, DanioID, "
                + "PorcentajeDanio)VALUES(" + sotoBosque.getSitioID() + ", " + sotoBosque.getFamiliaID() + ", " + sotoBosque.getGeneroID() + ""
                + ", " + sotoBosque.getEspecieID() + ", " + sotoBosque.getInfraespecieID() + ", '" + sotoBosque.getNombreComun() + "'"
                + ", " + sotoBosque.getFrecuecia025150() + ", " + sotoBosque.getCobertura025150() + ""
                + ", " + sotoBosque.getFrecuencia151275() + ", " + sotoBosque.getCobertura151275() + ", " + sotoBosque.getFrecuencia275() + ""
                + ", " + sotoBosque.getCobertura275() + ", " + sotoBosque.getVigorID() + ", " + sotoBosque.getDanioID() + ", " + sotoBosque.getPorcentajeDanio() + ")";
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo guardar la informacion en sotobosque "
                   , "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos al insertar datos de sotobosque",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void updateDatosSotoBosque(CESotoBosque sotoBosque) {
        query = "UPDATE TAXONOMIA_SotoBosque SET FamiliaID= " + sotoBosque.getFamiliaID() + ", GeneroID= "
                + sotoBosque.getGeneroID() + ", EspecieID= " + sotoBosque.getEspecieID() + ", InfraespecieID= "
                + sotoBosque.getInfraespecieID() + ", NombreComun= '" + sotoBosque.getNombreComun() + "', Frecuencia025150= " + sotoBosque.getFrecuecia025150()
                + ", Cobertura025150= " + sotoBosque.getCobertura025150() + ", Frecuencia151275= "
                + sotoBosque.getFrecuencia151275() + ", Cobertura151275= " + sotoBosque.getCobertura151275()
                + ", Frecuencia275= " + sotoBosque.getFrecuencia275() + ", Cobertura275= "
                + sotoBosque.getCobertura275() + ", VigorID= " + sotoBosque.getVigorID() + ", DanioID= "
                + sotoBosque.getDanioID() + ", PorcentajeDanio= " + sotoBosque.getPorcentajeDanio() + " WHERE SotoBosqueID= "
                + sotoBosque.getSotoBosqueID();
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo modificar la información de sotobosque ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la modificación de sotobosque"
                        , "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void deleteDatosSotoBosque(CESotoBosque sb) {
        query = "DELETE FROM TAXONOMIA_SotoBosque WHERE SotoBosqueID= " + sb.getSotoBosqueID();
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo eliminar la información de sotobosque "
                    , "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos  al eliminar el registro de sotobosque",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void deleteSotobosqueSitio(int sitioID) {
        query = "DELETE FROM TAXONOMIA_SotoBosque WHERE SitioID= " + sitioID;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo eliminar la información de sotobosque por sitio"
                    , "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos  al eliminar el sotobosque por sitio"
                        + e.getClass().getName() + " : " + e.getMessage(),
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public CESotoBosque getRegistroSotobosque(int sotobosqueID) {
        this.query = "SELECT SotoBosqueID, FamiliaID, GeneroID, EspecieID, InfraespecieID, "
                + "NombreComun, Frecuencia025150, Cobertura025150, Frecuencia151275, Cobertura151275, "
                + "Frecuencia275, Cobertura275, VigorID, DanioID, PorcentajeDanio, ClaveColecta FROM TAXONOMIA_SotoBosque WHERE SotoBosqueID= " + sotobosqueID;
        Connection conn = LocalConnection.getConnection();
        CESotoBosque sb = new CESotoBosque();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                sb.setFamiliaID(rs.getInt("FamiliaID"));
                sb.setGeneroID(rs.getInt("GeneroID"));
                sb.setEspecieID(rs.getInt("EspecieID"));
                sb.setInfraespecieID(rs.getInt("InfraespecieID"));
                sb.setNombreComun(rs.getString("NombreComun"));
                sb.setFrecuecia025150(rs.getInt("Frecuencia025150"));
                sb.setCobertura025150(rs.getInt("Cobertura025150"));
                sb.setFrecuencia151275(rs.getInt("Frecuencia151275"));
                sb.setCobertura151275(rs.getInt("Cobertura151275"));
                sb.setFrecuencia275(rs.getInt("Frecuencia275"));
                sb.setCobertura275(rs.getInt("Cobertura275"));
                sb.setVigorID(rs.getInt("VigorID"));
                sb.setDanioID(rs.getInt("DanioID"));
                sb.setPorcentajeDanio(rs.getInt("PorcentajeDanio"));
                sb.setClaveColecta(rs.getString("ClaveColecta"));
            }
            return sb;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al obtener un registro de sotobosque "
                    , "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane
                        .showMessageDialog(null,
                                "Error! al cerrar la base de datos en datos de registro de sotobosque",
                                "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void enumerarConsecutivo(int sitioID) {
        List<Integer> listAboladoID = getSotobosqueID(sitioID);
        Connection conn = LocalConnection.getConnection();
        if (listAboladoID != null) {
            int size = listAboladoID.size();
            int consecutivo = 1;
            try {
                for (int i = 0; i < size; i++) {
                    Statement st = conn.createStatement();
                    query = "UPDATE TAXONOMIA_SotoBosque SET Consecutivo= " + consecutivo + "  WHERE SotoBosqueID= " + listAboladoID.get(i);
                    st.executeUpdate(query);
                    conn.commit();
                    consecutivo++;
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,
                        "Error! al enumerar el consecutivo de sotobosque ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al enumerar el consecutivo del sotobosque"
                            , "Conexion BD", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private List<Integer> getSotobosqueID(int sitioID) {
        List<Integer> listArboladoID = new ArrayList<>();
        query = "SELECT SotoBosqueID, SitioID FROM TAXONOMIA_SotoBosque WHERE SitioID= " + sitioID + " ORDER BY SotoBosqueID ASC";
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                listArboladoID.add(rs.getInt("SotoBosqueID"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener los datos de sotobosque id ",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en lista de sotobosque id"
                       , "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        return listArboladoID;
    }
    
   
}
