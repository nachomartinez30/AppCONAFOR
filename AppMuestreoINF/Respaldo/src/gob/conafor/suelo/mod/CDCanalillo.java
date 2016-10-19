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

public class CDCanalillo {

    private String query;
    private CECanalillo ceCanalillo = new CECanalillo();

    public List<Integer> getNumeroCanalillo(int sitioID) {
        List<Integer> listCanalillo = new ArrayList<>();
        query = "SELECT SitioID, Numero, Ancho, Profundidad FROM SUELO_Canalillo WHERE SitioID= " + sitioID;
        Connection conn = LocalConnection.getConnection();

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                listCanalillo.add(rs.getInt("Numero"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener los numeros de canalillo ",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en canalillo"
                       , "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        listCanalillo.add(0, null);
        return listCanalillo;
    }

    public CECanalillo getCanalillo(int canalilloID) {
        query = "SELECT CanalilloID, Numero, Ancho, Profundidad FROM SUELO_Canalillo WHERE CanalilloID=" + canalilloID;
        Connection conn = LocalConnection.getConnection();

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                this.ceCanalillo.setNumero(rs.getInt("Numero"));
                this.ceCanalillo.setAncho(rs.getFloat("Ancho"));
                this.ceCanalillo.setProfundidad(rs.getFloat("Profundidad"));
            }
            return ceCanalillo;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al obtener los datos de canalillo ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al obtener los datos de canalillo ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public CECanalillo getCanalilloPorNumero(CECanalillo ceCanalillo) {
        
        query = "SELECT CanalilloID, SitioID, Numero, Ancho, Profundidad FROM SUELO_Canalillo WHERE SitioID= " + ceCanalillo.getSitioID() + 
                " AND Numero= " + ceCanalillo.getNumero();
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                this.ceCanalillo.setCanalilloID(rs.getInt("CanalilloID"));
                this.ceCanalillo.setAncho(rs.getFloat("Ancho"));
                this.ceCanalillo.setProfundidad(rs.getFloat("Profundidad"));
            }
            return this.ceCanalillo;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al obtener los datos de canalillo ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al obtener los datos de canalillo ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void insertCanalillo(CECanalillo ceCanalillo) {
        query = "INSERT INTO SUELO_Canalillo(SitioID, Ancho, Profundidad)VALUES(" + ceCanalillo.getSitioID() +  ", " + ceCanalillo.getAncho() + ", " + ceCanalillo.getProfundidad() + ")";
        Connection conn = LocalConnection.getConnection();

        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo guardar la informacion de canalillo ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos al insertar datos de canalillo",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void updateCanalillo(CECanalillo ceCanalillo) {
        query = "UPDATE SUELO_Canalillo SET Ancho= " + ceCanalillo.getAncho() + ", Profundidad= " + ceCanalillo.getProfundidad()
                + " WHERE CanalilloID= " + ceCanalillo.getCanalilloID();
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo modificar la información de canalillo ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la modificación de canalillo ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void deleteCanalillo(int canalilloID) {
        query = "DELETE FROM SUELO_Canalillo WHERE CanalilloID= " + canalilloID;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo eliminar la información de canalillo ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos  al eliminar la informacion de canalillo ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void deleteCanalilloSitio(int sitioID){
        query = "DELETE FROM SUELO_Canalillo WHERE SitioID=" + sitioID;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo eliminar la información de canalillo por sitio", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos  al eliminar la informacion de canalillo por sitio",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public DefaultTableModel getTablaCanalillo(int sitioID) {
        query = "SELECT CanalilloID, SitioID, Numero, Ancho, Profundidad FROM SUELO_Canalillo WHERE SitioID= " + sitioID;

        String[] encabezados = {"CanalilloID", "SitioID", "Numero", "Ancho", "Profundidad"};

        DefaultTableModel canalilloModel = new DefaultTableModel(null, encabezados);
        Object[] datosCanalillo = new Object[5];
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                datosCanalillo[0] = rs.getInt("CanalilloID");
                datosCanalillo[1] = rs.getInt("SitioID");
                datosCanalillo[2] = rs.getInt("Numero");
                datosCanalillo[3] = rs.getFloat("Ancho");
                datosCanalillo[4] = rs.getFloat("Profundidad");

                canalilloModel.addRow(datosCanalillo);
            }
            st.close();
            rs.close();
            return canalilloModel;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al obtener los datos de la vista de canalillo ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en para la vista de canalillo", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private List<Integer> getListCanalilloID(int sitioID) {
        List<Integer> listCanalilloID = new ArrayList<>();
        query = "SELECT CanalilloID, SitioID FROM SUELO_Canalillo WHERE SitioID= " + sitioID + " ORDER BY CanalilloID ASC";
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                listCanalilloID.add(rs.getInt("CanalilloID"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener los datos de canalillo id ",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en lista de canalillo id", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        return listCanalilloID;
    }

    public void renumerarRegistros(int sitioID) {
        List<Integer> listCanalilloID = getListCanalilloID(sitioID);
        Connection conn = LocalConnection.getConnection();
        if (listCanalilloID != null) {
            int size = listCanalilloID.size();
            int consecutivo = 1;
            try {
                for (int i = 0; i < size; i++) {
                    Statement st = conn.createStatement();
                    query = "UPDATE SUELO_Canalillo SET Numero= " + consecutivo + "  WHERE CanalilloID= " + listCanalilloID.get(i);
                    st.executeUpdate(query);
                    conn.commit();
                    consecutivo++;
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,
                        "Error! al enumerar canalillo ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al enumerar canalillo ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    
    public boolean validarTablaCanalillos(int sitioID) {
        query = "SELECT * FROM SUELO_Canalillo WHERE SitioID= " + sitioID;
        boolean vacio = true;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                vacio = false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al validar la presencia de datos en la tabla de canalillos", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la tabla de canalillos", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        return vacio;
    }
}
