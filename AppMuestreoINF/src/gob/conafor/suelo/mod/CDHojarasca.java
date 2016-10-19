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

public class CDHojarasca {
    
    private String query;
    private CEHojarasca ceHojarasca = new CEHojarasca();
    
    public List<Integer> getPuntosHojarasca(int sitioID) {
        List<Integer> listPuntosHojarasca = new ArrayList<>();
        for (int i = 1; i < 9; i++) {
            listPuntosHojarasca.add(i);
        }
        query = "SELECT SitioID, Punto FROM SUELO_Hojarasca WHERE SitioID= " + sitioID;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Integer index = rs.getInt(2);
                listPuntosHojarasca.remove(index);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener los puntos de hojarasca ",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en los puntos de hojarasca ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        listPuntosHojarasca.add(0, null);
        return listPuntosHojarasca;
    }
    
    public List<Integer> getListHojarascaID(int sitioID) {
        List<Integer> listHojarascaID = new ArrayList<>();
        query = "SELECT HojarascaID, SitioID FROM SUELO_Hojarasca WHERE SitioID= " + sitioID + " ORDER BY HojarascaID ASC";
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                listHojarascaID.add(rs.getInt("HojarascaID"));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener los datos de hojarasca id ",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en lista de hojarasca id", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        return listHojarascaID;
    }

    public void reenumerarHojarasca(int sitioID) {
        List<Integer> listHojarascaPuntos = getListHojarascaID(sitioID);
        Connection conn = LocalConnection.getConnection();
        if (listHojarascaPuntos != null) {
            int size = listHojarascaPuntos.size();
            int consecutivo = 1;
            try {
                for (int i = 0; i < size; i++) {
                    Statement st = conn.createStatement();
                    query = "UPDATE SUELO_Hojarasca SET Punto= " + consecutivo + "  WHERE HojarascaID= " + listHojarascaPuntos.get(i);
                    st.executeUpdate(query);
                    conn.commit();
                    consecutivo++;
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,
                        "Error! al reenumerar los puntos de hojarasca ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al reenumerar los puntos de hojarasca ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    
    public List<CatETipoHojarasca> getTipohojarasca(int tipoHojarascaID) {
        List<CatETipoHojarasca> lisTipoHojarasca = new ArrayList<>();
        query = "SELECT TipoHojarascaID, Clave, Descripcion FROM CAT_TipoHojarasca";
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                CatETipoHojarasca tipoHojarasca = new CatETipoHojarasca();

                tipoHojarasca.setTipoHojarascaID(rs.getInt("TipoHojarascaID"));
                tipoHojarasca.setClave(rs.getString("Clave"));
                tipoHojarasca.setDescripcion(rs.getString("Descripcion"));

                lisTipoHojarasca.add(tipoHojarasca);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener los datos de tipo de hojarasca ",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {

                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en datos en tipos de hojarasca ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        lisTipoHojarasca.add(0, null);
        return lisTipoHojarasca;
    }
    
    public CEHojarasca getDatosHojarasca(int hojarascaID) {
        query = "SELECT HojarascaID, Punto, TipoHojarascaID, EspesorHO, EspesorF, PesoTotalHO, PesoTotalF, "
                + "PesoMuestraHO, PesoMuestraF, Observaciones, ClaveHO, ClaveF FROM SUELO_Hojarasca WHERE HojarascaID= " + hojarascaID;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                this.ceHojarasca.setPunto(rs.getInt("Punto"));
                this.ceHojarasca.setTipoID(rs.getInt("TipoHojarascaID"));
                this.ceHojarasca.setEspesorHO(rs.getFloat("EspesorHO"));
                this.ceHojarasca.setEspesorF(rs.getFloat("EspesorF"));
                this.ceHojarasca.setPesoTotalHO(rs.getFloat("PesoTotalHO"));
                this.ceHojarasca.setPesoTotalF(rs.getFloat("PesoTotalF"));
                this.ceHojarasca.setPesoMuestraHO(rs.getFloat("PesoMuestraHO"));
                this.ceHojarasca.setPesoMuestraF(rs.getFloat("PesoMuestraF"));
                this.ceHojarasca.setObservaciones(rs.getString("Observaciones"));
                this.ceHojarasca.setClaveHO(rs.getString("ClaveHO"));
                this.ceHojarasca.setClaveF(rs.getString("ClaveF"));
            }
            return this.ceHojarasca;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al obtener los datos de hojarasca ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al obtener la información de hojarasca ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    
    
    public DefaultTableModel getTablaHojarasca(int sitioID) {
        query = "SELECT HojarascaID, SitioID, Punto, TipoHojarasca, EspesorHO, EspesorF, PesoTotalHO, "
                + "PesoTotalF, PesoMuestraHO, PesoMuestraF, Observaciones, ClaveHO, ClaveF FROM VW_Hojarasca";
        String[] encabezados = {"HojarascaID", "SitioID", "Punto", "Tipo hojarasca", "Espesor HO", "Espesor F",
            "Peso total HO", "Peso total F", "Peso muestra HO", "Peso muestra F", "Observaciones", "Clave HO", "Clave F"};
        DefaultTableModel hojarascaModel = new DefaultTableModel(null, encabezados);
        Object[] datosHojarasca = new Object[13];
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                datosHojarasca[0] = rs.getInt("HojarascaID");
                datosHojarasca[1] = rs.getInt("SitioID");
                datosHojarasca[2] = rs.getInt("Punto");
                datosHojarasca[3] = rs.getString("TipoHojarasca");
                datosHojarasca[4] = rs.getFloat("EspesorHO");
                datosHojarasca[5] = rs.getFloat("EspesorF");
                datosHojarasca[6] = rs.getFloat("PesoTotalHO");
                datosHojarasca[7] = rs.getFloat("PesoTotalF");
                datosHojarasca[8] = rs.getFloat("PesoMuestraHO");
                datosHojarasca[9] = rs.getFloat("PesoMuestraF");
                datosHojarasca[10] = rs.getString("Observaciones");
                datosHojarasca[11] = rs.getString("ClaveHO");
                datosHojarasca[12] = rs.getString("ClaveF");
                
                hojarascaModel.addRow(datosHojarasca);
            }
            st.close();
            rs.close();
            return hojarascaModel;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al obtener los datos de la vista de hojarasca ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en para la vista de hojarasca ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void insertHojarasca(CEHojarasca ceHojarasca) {
        query = "INSERT INTO SUELO_Hojarasca(SitioID, Punto, TipoHojarascaID, EspesorHO, EspesorF, PesoTotalHO, PesoTotalF, "
                + "PesoMuestraHO, PesoMuestraF, Observaciones, ClaveHO, ClaveF)VALUES(" + ceHojarasca.getSitioID() + ", " + ceHojarasca.getPunto() + ", " + ceHojarasca.getTipoID() + ", " + ceHojarasca.getEspesorHO() + ", " + ceHojarasca.getEspesorF()
                + ", " + ceHojarasca.getPesoTotalHO() + ", " + ceHojarasca.getPesoTotalF() + ", " + ceHojarasca.getPesoMuestraHO() + ", " + ceHojarasca.getPesoMuestraF() + ", '" + ceHojarasca.getObservaciones() 
                + "', '" + ceHojarasca.getClaveHO() + "', '" + ceHojarasca.getClaveF() + "')";
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo guardar la informacion en hojarasca ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos al insertar datos de hojarasca ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public boolean validarCDPuntoHojarasca(CEHojarasca ceHojarasca) {
        query = "SELECT SitioID, Punto FROM SUELO_Hojarasca WHERE SitioID= " + ceHojarasca.getSitioID() + " AND Punto= " + ceHojarasca.getPunto();
        boolean vacio = false;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                vacio = true;
            }
           // vacio = false;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al validar la duplicidad de puntos en la tabla de hojarsaca", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al validar la duplicidad de puntos en la tabla de hojarasca", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        return vacio;
    }
    
    public void updateHojarasca(CEHojarasca ceHojarascas) {
        query = "UPDATE SUELO_Hojarasca SET TipoHojarascaID= " + ceHojarascas.getTipoID() + ", EspesorHO= " + ceHojarascas.getEspesorHO()
                + ", EspesorF= " + ceHojarascas.getEspesorF() + ", PesoTotalHO= " + ceHojarascas.getPesoTotalHO() + ", PesoTotalF= " + ceHojarascas.getPesoTotalF()
                + ", PesoMuestraHO= " + ceHojarascas.getPesoMuestraHO() + ", PesoMuestraF= " + ceHojarascas.getPesoMuestraF() + ", Observaciones= '" + ceHojarascas.getObservaciones()
                + "' WHERE HojarascaID= " + ceHojarascas.getHojarascaID();
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo modificar la información de hojarasca ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la modificación de hojaraca ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void deleteHojarasca(int hojarascaID) {
        query = "DELETE FROM SUELO_Hojarasca WHERE HojarascaID= " + hojarascaID;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo eliminar la información de hojarasca ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos  al eliminar hojarasca",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void deleteHojarascaSitio(int sitioID){
         query = "DELETE FROM SUELO_Hojarasca WHERE SitioID= " + sitioID;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo eliminar la información de hojarasca por sitio", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos  al eliminar hojarasca por sitio",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public boolean validarTablaHojarasca(int sitioID) {
        query = "SELECT * FROM SUELO_Hojarasca WHERE SitioID= " + sitioID;
        boolean vacio = true;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                vacio = false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al validar la presencia de datos en la tabla de hojarsaca", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la tabla de hojarasca", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        return vacio;
    }

}
