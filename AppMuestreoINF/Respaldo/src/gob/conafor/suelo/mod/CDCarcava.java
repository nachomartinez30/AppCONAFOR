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

public class CDCarcava {

    private String query;
    private CECarcavas ceCarcavas = new CECarcavas();

    public List<Integer> getNumeroCarcavas(int sitioID) {
        List<Integer> listCanalillo = new ArrayList<>();
        query = "SELECT SitioID, Numero, Ancho, Profundidad FROM SUELO_Carcava WHERE SitioID= " + sitioID;
        Connection conn = LocalConnection.getConnection();

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                listCanalillo.add(rs.getInt("Numero"));
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener los numeros de carcava ",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en carcava", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        listCanalillo.add(0, null);
        return listCanalillo;
    }

    public CECarcavas getCarcavas(int carcavaID) {
        query = "SELECT CarcavaID, Numero, Ancho, Profundidad FROM SUELO_Carcava WHERE CarcavaID=" + carcavaID;
        Connection conn = LocalConnection.getConnection();

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                this.ceCarcavas.setNumero(rs.getInt("Numero"));
                this.ceCarcavas.setAncho(rs.getFloat("Ancho"));
                this.ceCarcavas.setProfundidad(rs.getFloat("Profundidad"));
            }
            return ceCarcavas;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al obtener los datos de carcavas ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al obtener los datos de carcavas ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public CECarcavas getCarcavasPorNumero(CECarcavas ceCarcava) {
        query = "SELECT CarcavaID, SitioID, Numero, Ancho, Profundidad FROM SUELO_Carcava WHERE SitioID=" + ceCarcava.getSitioID() + 
                " AND Numero= " + ceCarcava.getNumero();
        Connection conn = LocalConnection.getConnection();

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                this.ceCarcavas.setNumero(rs.getInt("CarcavaID"));
                this.ceCarcavas.setAncho(rs.getFloat("Ancho"));
                this.ceCarcavas.setProfundidad(rs.getFloat("Profundidad"));
            }
            return this.ceCarcavas;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al obtener los datos de carcavas ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al obtener los datos de carcavas ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void insertCarcavas(CECarcavas ceCarcavas) {
        query = "INSERT INTO SUELO_Carcava(SitioID, Ancho, Profundidad)VALUES(" + ceCarcavas.getSitioID() +  ", " + ceCarcavas.getAncho() + ", " + ceCarcavas.getProfundidad() + ")";
        Connection conn = LocalConnection.getConnection();

        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo guardar la informacion de carcavas ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos al insertar datos de carcavas ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void updateCaracava(CECarcavas ceCarcavas) {
        query = "UPDATE SUELO_Carcava SET Ancho= " + ceCarcavas.getAncho() + ", Profundidad= " + ceCarcavas.getProfundidad()
                + " WHERE CarcavaID= " + ceCarcavas.getCarcavasID();
        Connection conn = LocalConnection.getConnection();

        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo modificar la información de carcavas ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la modificación de carcavas ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void deleteCarcava(int carcavaID) {
        query = "DELETE FROM SUELO_Carcava WHERE CarcavaID= " + carcavaID;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo eliminar la información de carcava ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos  al eliminar la informacion de carcava ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void deleteCarcavaSitio(int sitioID){
        query = "DELETE FROM SUELO_Carcava WHERE SitioID= " + sitioID;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo eliminar la información de carcava por sitio", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos  al eliminar la informacion de carcava por sitio",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public DefaultTableModel getTablaCarcava(int sitioID) {
        query = "SELECT CarcavaID, SitioID, Numero, Ancho, Profundidad FROM SUELO_Carcava WHERE SitioID= " + sitioID;

        String[] encabezados = {"CarcavaID", "SitioID", "Numero", "Ancho", "Profundidad"};

        DefaultTableModel erosionModel = new DefaultTableModel(null, encabezados);
        Object[] datosErosion = new Object[5];
        Connection conn = LocalConnection.getConnection();

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                datosErosion[0] = rs.getInt("CarcavaID");
                datosErosion[1] = rs.getInt("SitioID");
                datosErosion[2] = rs.getInt("Numero");
                datosErosion[3] = rs.getFloat("Ancho");
                datosErosion[4] = rs.getFloat("Profundidad");

                erosionModel.addRow(datosErosion);
            }
            st.close();
            rs.close();
            return erosionModel;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al obtener los datos de la vista de carcava ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en para la vista de carcava ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private List<Integer> getListCarcavaID(int sitioID) {
        List<Integer> listErosionID = new ArrayList<>();
        query = "SELECT CarcavaID, SitioID FROM SUELO_Carcava WHERE SitioID= " + sitioID + " ORDER BY CarcavaID ASC";
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                listErosionID.add(rs.getInt("CarcavaID"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener los datos de carcava id ",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en lista de carcava id"
                        + e.getClass().getName() + " : " + e.getMessage(), "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        return listErosionID;
    }

    public void renumerarRegistros(int sitioID) {
        List<Integer> listCarcavaID = getListCarcavaID(sitioID);
        Connection conn = LocalConnection.getConnection();
        if (listCarcavaID != null) {
            int size = listCarcavaID.size();
            int consecutivo = 1;
            try {
                for (int i = 0; i < size; i++) {
                    Statement st = conn.createStatement();
                    query = "UPDATE SUELO_Carcava SET Numero= " + consecutivo + "  WHERE CarcavaID= " + listCarcavaID.get(i);
                    st.executeUpdate(query);
                    conn.commit();
                    consecutivo++;
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,
                        "Error! al enumerar el numero de carcavas " + e.getClass().getName() + " : " + e.getMessage(),
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al enumerar carcavas", "Conexion BD", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    
    public boolean validarTablaCarcavas(int sitioID) {
        query = "SELECT * FROM SUELO_Carcava WHERE SitioID= " + sitioID;
        boolean vacio = true;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                vacio = false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al validar la presencia de datos en la tabla de carcavas", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la tabla de carcavas", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        return vacio;
    }

}
