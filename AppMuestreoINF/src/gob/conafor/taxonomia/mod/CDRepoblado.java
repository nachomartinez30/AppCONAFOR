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

public class CDRepoblado {

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
            JOptionPane.showMessageDialog(null, "Error! al obtener el Id del sitio en repoblado", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return sitioID;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al obtener el id del sitio en repoblado ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public boolean validarTablaRepoblado(int sitioID) {
        query = "SELECT * FROM TAXONOMIA_Repoblado WHERE SitioID= " + sitioID;
        boolean vacio = true;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                vacio = false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al obtener los datos de la tabla de repoblado ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en datos de validar tabla de repoblado ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        return vacio;
    }

    public DefaultTableModel getTablaRepoblado(int sitioID) {
        query = "SELECT RepobladoID, SitioID, Consecutivo, Familia, Genero, Especie, Infraespecie, NombreComun, Frecuencia025, "
                + "Edad025, Frecuencia151, Edad151, Frecuencia275, Edad275, Vigor, Danio, "
                + "PorcentajeDanio, ClaveColecta FROM VW_Repoblado WHERE SitioID= " + sitioID;
        String[] encabezados = {"RepobladoID", "SitioID", "Consecutivo", "Familia", "Genero", "Especie", "Infraespecie", "Nombre comun",
            "Frecuencia 0.25-1.50", "Edad 0.25-1.50", "Frecuencia 1.51-2.75", "Edad 1.51-2.75", "Frecuencia 2.75", "Edad 2.75",
            "Vigor", "Daño", "Porcentaje daño", "Clave de colecta"};
        DefaultTableModel repobladoModel = new DefaultTableModel(null, encabezados);
        Object[] datosRepoblado = new Object[18];
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                datosRepoblado[0] = rs.getInt("RepobladoID");
                datosRepoblado[1] = rs.getInt("SitioID");
                datosRepoblado[2] = rs.getInt("Consecutivo");
                datosRepoblado[3] = rs.getString("Familia");
                datosRepoblado[4] = rs.getString("Genero");
                datosRepoblado[5] = rs.getString("Especie");
                datosRepoblado[6] = rs.getString("Infraespecie");
                datosRepoblado[7] = rs.getString("NombreComun");
                datosRepoblado[8] = rs.getString("Frecuencia025");
                datosRepoblado[9] = rs.getString("Edad025");
                datosRepoblado[10] = rs.getString("Frecuencia151");
                datosRepoblado[11] = rs.getString("Edad151");
                datosRepoblado[12] = rs.getString("Frecuencia275");
                datosRepoblado[13] = rs.getString("Edad275");
                datosRepoblado[14] = rs.getString("Vigor");
                datosRepoblado[15] = rs.getString("Danio");
                datosRepoblado[16] = rs.getString("PorcentajeDanio");
                datosRepoblado[17] = rs.getString("ClaveColecta");
                repobladoModel.addRow(datosRepoblado);
            }
            st.close();
            rs.close();
            return repobladoModel;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al obtener los datos de la vista de repoblado ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en para la vista de repoblado", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public List<CERepoblado> getDatosRepoblado(int sitioID) {
        query = "SELECT RepobladoID, SitioID, FamiliaID, GeneroID, EspecieID, InfraespecieID, NombreComun, Frecuencia025150, "
                + "Edad025150, Frecuencia151275, Edad151275, Frecuencia275, Edad275, VigorID, DanioID, "
                + "PorcentajeDanio, ClaveColecta FROM TAXONOMIA_Repoblado  WHERE SitioID= " + sitioID;
        Connection conn = LocalConnection.getConnection();
        List<CERepoblado> listRepoblado = new ArrayList<>();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                CERepoblado repoblado = new CERepoblado();
                repoblado.setSitioID(rs.getInt("SitioID"));
                repoblado.setFamiliaID(rs.getInt("FamiliaID"));
                repoblado.setGeneroID(rs.getInt("GeneroID"));
                repoblado.setEspecieID(rs.getInt("EspecieID"));
                repoblado.setInfraespecieID(rs.getInt("InfraespecieID"));
                repoblado.setNombreComun(rs.getString("NombreComun"));
                repoblado.setFrecuecia025150(rs.getInt("Frecuencia025150"));
                repoblado.setEdad025150(rs.getInt("Edad025150"));
                repoblado.setFrecuencia151275(rs.getInt("Frecuencia151275"));
                repoblado.setEdad151275(rs.getInt("Edad151275"));
                repoblado.setFrecuencia275(rs.getInt("Frecuencia275"));
                repoblado.setEdad275(rs.getInt("Edad275"));
                repoblado.setVigorID(rs.getInt("VigorID"));
                repoblado.setDanioID(rs.getInt("DanioID"));
                repoblado.setPorcentajeDanio(rs.getInt("PorcentajeDanio"));
                repoblado.setClaveColecta(rs.getString("ClaveColecta"));
                listRepoblado.add(repoblado);
            }
            st.close();
            rs.close();
            return listRepoblado;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al obtener los datos de repoblado ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane
                        .showMessageDialog(null,
                                "Error! al cerrar la base de datos en datos de repoblado",
                                "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void insertDatosRepoblado(CERepoblado repoblado) {

        query = "INSERT INTO TAXONOMIA_Repoblado(SitioID, FamiliaID, GeneroID, EspecieID, InfraespecieID, NombreComun, "
                + "Frecuencia025150, Edad025150, Frecuencia151275, Edad151275, Frecuencia275, Edad275, vigorID, DanioID, "
                + "PorcentajeDanio)VALUES(" + repoblado.getSitioID() + ", " + repoblado.getFamiliaID() + ", " + repoblado.getGeneroID() + ""
                + ", " + repoblado.getEspecieID() + ", " + repoblado.getInfraespecieID() + ", '" + repoblado.getNombreComun() + "'"
                + ", " + repoblado.getFrecuecia025150() + ", " + repoblado.getEdad025150() + ""
                + ", " + repoblado.getFrecuencia151275() + ", " + repoblado.getEdad151275() + ", " + repoblado.getFrecuencia275() + ""
                + ", " + repoblado.getEdad275() + ", " + repoblado.getVigorID() + ", " + repoblado.getDanioID() + ", " + repoblado.getPorcentajeDanio() + ")";
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo guardar la informacion en repoblado ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos al insertar datos de repoblado ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void guardarRepoblado() {
        query = "INSERT INTO TAXONOMIA_Repoblado(SitioID, FamiliaID, GeneroID, EspecieID, InfraespecieID, NombreComun, "
                + "Frecuencia025150, Edad025150, Frecuencia151275, Edad151275, Frecuencia275, Edad275, vigorID, DanioID, "
                + "PorcentajeDanio)VALUES(12, 6, 26, 333, 'Infraespecie','Nombre comun', 0, 5, 10, 10, 20, 30, 40, 2, 4, 50)";
        Connection conn = LocalConnection.getConnection();
        for (int i = 0; i < 901; i++) {
            try {
                Statement st = conn.createStatement();
                st.executeUpdate(query);
                conn.commit();
                st.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error! no se pudo guardar la informacion en repoblado ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
            /*finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(
                            null, "Error! al cerrar la base de datos al insertar datos de repoblado "
                            + e.getClass().getName() + " : " + e.getMessage(),
                            "Conexion BD", JOptionPane.ERROR_MESSAGE);
                }
            }*/
        }
    }

    public void updateDatosRepoblado(CERepoblado repoblado) {
        query = "UPDATE TAXONOMIA_Repoblado SET FamiliaID= " + repoblado.getFamiliaID() + ", GeneroID= "
                + repoblado.getGeneroID() + ", EspecieID= " + repoblado.getEspecieID() + ", InfraespecieID= "
                + repoblado.getInfraespecieID() + ", NombreComun= '" + repoblado.getNombreComun() + "'"
                + ", Frecuencia025150= " + repoblado.getFrecuecia025150()
                + ", Edad025150= " + repoblado.getEdad025150() + ", Frecuencia151275= "
                + repoblado.getFrecuencia151275() + ", Edad151275= " + repoblado.getEdad151275()
                + ", Frecuencia275= " + repoblado.getFrecuencia275() + ", Edad275= "
                + repoblado.getEdad275() + ", VigorID= " + repoblado.getVigorID() + ", DanioID= "
                + repoblado.getDanioID() + ", PorcentajeDanio= " + repoblado.getPorcentajeDanio() + " WHERE RepobladoID= "
                + repoblado.getRepobladoID();
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo modificar la información de repoblado ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la modificación de repoblado", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void deleteDatosRepoblado(CERepoblado rep) {
        query = "DELETE FROM TAXONOMIA_Repoblado WHERE RepobladoID= " + rep.getRepobladoID();
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo eliminar la información del repoblado ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos  al eliminar el repoblado",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void deleteRepobladoSitio(int sitioID) {
        query = "DELETE FROM TAXONOMIA_Repoblado WHERE SitioID= " + sitioID;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo eliminar la información del repoblado en sitio", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos  al eliminar el repoblado en sitio",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public CERepoblado getRegistroRepoblado(int repobladoID) {
        this.query = "SELECT RepobladoID, FamiliaID, GeneroID, EspecieID, InfraespecieID, "
                + "NombreComun, Frecuencia025150, Edad025150, Frecuencia151275, Edad151275, "
                + "Frecuencia275, Edad275, VigorID, DanioID, PorcentajeDanio, ClaveColecta FROM TAXONOMIA_Repoblado WHERE RepobladoID= " + repobladoID;
        Connection conn = LocalConnection.getConnection();
        CERepoblado rep = new CERepoblado();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                rep.setFamiliaID(rs.getInt("FamiliaID"));
                rep.setGeneroID(rs.getInt("GeneroID"));
                rep.setEspecieID(rs.getInt("EspecieID"));
                rep.setInfraespecieID(rs.getInt("InfraespecieID"));
                rep.setNombreComun(rs.getString("NombreComun"));
                rep.setFrecuecia025150(rs.getInt("Frecuencia025150"));
                rep.setEdad025150(rs.getInt("Edad025150"));
                rep.setFrecuencia151275(rs.getInt("Frecuencia151275"));
                rep.setEdad151275(rs.getInt("Edad151275"));
                rep.setFrecuencia275(rs.getInt("Frecuencia275"));
                rep.setEdad275(rs.getInt("Edad275"));
                rep.setVigorID(rs.getInt("VigorID"));
                rep.setDanioID(rs.getInt("DanioID"));
                rep.setPorcentajeDanio(rs.getInt("PorcentajeDanio"));
                rep.setClaveColecta(rs.getString("ClaveColecta"));
            }
            return rep;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al obtener un registro de repoblado ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane
                        .showMessageDialog(null,
                                "Error! al cerrar la base de datos en datos de registro de repoblado",
                                "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void enumerarConsecutivo(int sitioID) {
        List<Integer> listAboladoID = getRepobladoID(sitioID);
        Connection conn = LocalConnection.getConnection();
        if (listAboladoID != null) {
            int size = listAboladoID.size();
            int consecutivo = 1;
            try {
                for (int i = 0; i < size; i++) {
                    Statement st = conn.createStatement();
                    query = "UPDATE TAXONOMIA_Repoblado SET Consecutivo= " + consecutivo + "  WHERE RepobladoID= " + listAboladoID.get(i);
                    st.executeUpdate(query);
                    conn.commit();
                    consecutivo++;
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,
                        "Error! al enumerar el consecutivo de repoblado " + e.getClass().getName() + " : " + e.getMessage(),
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al enumerar el consecutivo del repoblado", "Conexion BD", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private List<Integer> getRepobladoID(int sitioID) {
        List<Integer> listArboladoID = new ArrayList<>();
        query = "SELECT RepobladoID, SitioID FROM TAXONOMIA_Repoblado WHERE SitioID= " + sitioID + " ORDER BY RepobladoID ASC";
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                listArboladoID.add(rs.getInt("RepobladoID"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener los datos de repoblado id ",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en lista de repoblado id", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        return listArboladoID;
    }
}
