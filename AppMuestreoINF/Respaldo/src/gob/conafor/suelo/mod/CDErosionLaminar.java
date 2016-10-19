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

public class CDErosionLaminar {

    private String query;
    private CEErosionLaminar ceErosion = new CEErosionLaminar();

    public List<Integer> getNumeroErosion(int sitioID) {
        List<Integer> listErosion = new ArrayList<>();
        query = "SELECT SitioID, Numero, Ancho, Largo FROM SUELO_ErosionLaminar WHERE SitioID= " + sitioID;
        Connection conn = LocalConnection.getConnection();

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                listErosion.add(rs.getInt("Numero"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener los numeros de erosion laminar ",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en los numeros de erosion laminar", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        listErosion.add(0, null);
        return listErosion;
    }

    public CEErosionLaminar getErosion(int erosionID) {
        query = "SELECT ErosionLaminarID, SitioID, Numero, Ancho, Largo FROM SUELO_ErosionLaminar WHERE ErosionLaminarID=" + erosionID;
        Connection conn = LocalConnection.getConnection();

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                this.ceErosion.setNumero(rs.getInt("Numero"));
                this.ceErosion.setAncho(rs.getFloat("Ancho"));
                this.ceErosion.setLargo(rs.getFloat("Largo"));
            }
            return ceErosion;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al obtener los datos de erosion laminar", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al obtener los datos de erosion laminar", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public CEErosionLaminar getErosionPorNumero(CEErosionLaminar ceErosion) {
        query = "SELECT ErosionLaminarID, SitioID, Numero, Ancho, Largo FROM SUELO_ErosionLaminar WHERE SitioID= " + ceErosion.getSitioID() +
                " AND Numero= " + ceErosion.getNumero();
        Connection conn = LocalConnection.getConnection();

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                this.ceErosion.setNumero(rs.getInt("ErosionLaminarID"));
                this.ceErosion.setAncho(rs.getFloat("Ancho"));
                this.ceErosion.setLargo(rs.getFloat("Largo"));
            }
            return this.ceErosion;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al obtener los datos de erosion laminar", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al obtener los datos de erosion laminar", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void insertErosion(CEErosionLaminar ceErosion) {
        query = "INSERT INTO SUELO_ErosionLaminar(SitioID, Ancho, Largo)VALUES(" + ceErosion.getSitioID() + ", " + ceErosion.getAncho() + ", " + ceErosion.getLargo() + ")";
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo guardar la informacion de erosion laminar ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos al insertar datos de erosion laminar ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void updateErosion(CEErosionLaminar ceErosion) {
        query = "UPDATE SUELO_ErosionLaminar SET Ancho= " + ceErosion.getAncho() + ", Largo= " + ceErosion.getLargo()
                + " WHERE ErosionLaminarID= " + ceErosion.getErosionLaminarID();
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo modificar la información de erosion laminar ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la modificación de erosion laminar", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void deleteErosion(int erosionID) {
        query = "DELETE FROM SUELO_ErosionLaminar WHERE ErosionLaminarID= " + erosionID;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo eliminar la información de erosion laminar ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos  al eliminar la informacion de erosion laminar ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void deleteErosionLaminarSitio(int sitioID){
        query = "DELETE FROM SUELO_ErosionLaminar WHERE sitioID= " + sitioID;
         Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo eliminar la información de erosion laminar por sitio", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos  al eliminar la informacion de erosion laminar por sitio",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    public DefaultTableModel getTablaErosion(int sitioID) {
        query = "SELECT ErosionLaminarID, SitioID, Numero, Ancho, Largo FROM SUELO_ErosionLaminar WHERE SitioID= " + sitioID;
        String[] encabezados = {"ErosionLaminarID", "SitioID", "Numero", "Ancho", "Largo"};
        DefaultTableModel canalilloModel = new DefaultTableModel(null, encabezados);
        Object[] datosCanalillo = new Object[5];
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                datosCanalillo[0] = rs.getInt("ErosionLaminarID");
                datosCanalillo[1] = rs.getInt("SitioID");
                datosCanalillo[2] = rs.getInt("Numero");
                datosCanalillo[3] = rs.getFloat("Ancho");
                datosCanalillo[4] = rs.getFloat("Largo");
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
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en para la vista de canalillo ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private List<Integer> getListErosionID(int sitioID) {
        List<Integer> listErosionID = new ArrayList<>();
        query = "SELECT ErosionLaminarID, SitioID FROM SUELO_ErosionLaminar WHERE SitioID= " + sitioID + " ORDER BY ErosionLaminarID ASC";
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                listErosionID.add(rs.getInt("ErosionLaminarID"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener los datos de erosion laminar id ",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en lista de erosion laminar id", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        return listErosionID;
    }

    public void renumerarRegistros(int sitioID) {
        List<Integer> listErosionID = getListErosionID(sitioID);
        Connection conn = LocalConnection.getConnection();
        if (listErosionID != null) {
            int size = listErosionID.size();
            int consecutivo = 1;
            try {
                for (int i = 0; i < size; i++) {
                    Statement st = conn.createStatement();
                    query = "UPDATE SUELO_ErosionLaminar SET Numero= " + consecutivo + "  WHERE ErosionLaminarID= " + listErosionID.get(i);
                    st.executeUpdate(query);
                    conn.commit();
                    consecutivo++;
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,
                        "Error! al enumerar el numero de erosion laminar ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al enumerar erosion laminar ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    
    public boolean validarTablaErosionLaminar(int sitioID) {
        query = "SELECT * FROM SUELO_ErosionLaminar WHERE SitioID= " + sitioID;
        boolean vacio = true;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                vacio = false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al validar la presencia de datos en la tabla de erosión laminar", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la tabla de erosión laminar", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        return vacio;
    }
}
