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

public class CDPedestal {

    private String query;
    private CEPedestal cePedestal = new CEPedestal();
    // Condiciones de degradacion del suelo en pedestal

    public List<Integer> getNumeroPedestal(int sitioID) {
        List<Integer> listPedestal = new ArrayList<>();
        query = "SELECT SitioID, Numero FROM SUELO_Pedestal WHERE SitioID= " + sitioID;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                listPedestal.add(rs.getInt("Numero"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener los numeros de pedestal " + e.getClass().getName() + " : " + e.getMessage(),
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en los numeros de pedestal"
                        + e.getClass().getName() + " : " + e.getMessage(), "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        listPedestal.add(0, null);
        return listPedestal;
    }
    
    public CEPedestal getPedestal(int pedestalID) {
        query = "SELECT PedestalID, Numero, Altura FROM SUELO_Pedestal WHERE PedestalID=" + pedestalID;
        Connection conn = LocalConnection.getConnection();

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                this.cePedestal.setNumero(rs.getInt("Numero"));
                this.cePedestal.setAltura(rs.getFloat("Altura"));
            }
            return cePedestal;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al obtener la altura de pedestal", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al obtener la altura de pedestal ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
        public CEPedestal getPedestalPorNumero(CEPedestal cePedestal) {
        query = "SELECT PedestalID, SitioID, Numero, Altura FROM SUELO_Pedestal WHERE SitioID=" + cePedestal.getSitioID() + 
                " AND Numero= " + cePedestal.getNumero();
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                this.cePedestal.setNumero(rs.getInt("PedestalID"));
                this.cePedestal.setAltura(rs.getFloat("Altura"));
            }
            return this.cePedestal;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al obtener la altura de pedestal", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al obtener la altura de pedestal ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void insertPedestal(CEPedestal cePedestal) {
        query = "INSERT INTO SUELO_Pedestal(SitioID, Altura)VALUES(" + cePedestal.getSitioID() + ", " + cePedestal.getAltura() + ")";
        Connection conn = LocalConnection.getConnection();

        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo guardar la informacion en pedestal ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos al insertar datos de pedestal "
                        + e.getClass().getName() + " : " + e.getMessage(),
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void updatePedestal(CEPedestal cePedestal) {
        query = "UPDATE SUELO_Pedestal SET Altura= " + cePedestal.getAltura()
                + " WHERE PedestalID= " + cePedestal.getPedestalID();
        Connection conn = LocalConnection.getConnection();

        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo modificar la información de pedestal ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la modificación de pedestal", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void deletePedestal(int pedestalID) {
        query = "DELETE FROM SUELO_Pedestal WHERE PedestalID= " + pedestalID;
        Connection conn = LocalConnection.getConnection();
        try {

            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo eliminar la información de pedestal ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos  al eliminar pedestal",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void deletePedestalSitio(int sitioID){
        query = "DELETE FROM SUELO_Pedestal WHERE SitioID= " + sitioID;
        Connection conn = LocalConnection.getConnection();
        try {

            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo eliminar la información de pedestal por sitio", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos  al eliminar pedestal por sitio",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public DefaultTableModel getTablaPedestal(int sitioID) {
        query = "SELECT PedestalID, SitioID, Numero, Altura FROM SUELO_Pedestal WHERE SitioID= " + sitioID;

        String[] encabezados = {"PedestalID", "SitioID", "Numero", "Altura"};

        DefaultTableModel pedestalModel = new DefaultTableModel(null, encabezados);
        Object[] datosPedestal = new Object[4];
        Connection conn = LocalConnection.getConnection();

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                datosPedestal[0] = rs.getInt("PedestalID");
                datosPedestal[1] = rs.getInt("SitioID");
                datosPedestal[2] = rs.getInt("Numero");
                datosPedestal[3] = rs.getFloat("Altura");

                pedestalModel.addRow(datosPedestal);
            }
            st.close();
            rs.close();
            return pedestalModel;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al obtener los datos de la vista de pedestal ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en para la vista de pedestal", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private List<Integer> getListPedestalID(int sitioID) {
        List<Integer> listPedestalID = new ArrayList<>();
        query = "SELECT PedestalID, SitioID FROM SUELO_Pedestal WHERE SitioID= " + sitioID + " ORDER BY PedestalID ASC";
        Connection conn = LocalConnection.getConnection();

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                listPedestalID.add(rs.getInt("PedestalID"));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener los datos de pedestal id ",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en lista de pedestal id", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        return listPedestalID;
    }

    public void renumerarRegistros(int sitioID) {
        List<Integer> listPedestalID = getListPedestalID(sitioID);
        Connection conn = LocalConnection.getConnection();
        if (listPedestalID != null) {
            int size = listPedestalID.size();
            int consecutivo = 1;

            try {
                for (int i = 0; i < size; i++) {
                    Statement st = conn.createStatement();
                    query = "UPDATE SUELO_Pedestal SET Numero= " + consecutivo + "  WHERE PedestalID= " + listPedestalID.get(i);
                    st.executeUpdate(query);
                    conn.commit();
                    consecutivo++;
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,
                        "Error! al enumerar el numero de pedestal " ,
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al enumerar pedestal ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    
    public boolean validarTablaPedestal(int sitioID) {
        query = "SELECT * FROM SUELO_Pedestal WHERE SitioID= " + sitioID;
        boolean vacio = true;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                vacio = false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al validar la presencia de datos en la tabla de pedestal", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la tabla de pedestal", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        return vacio;
    }
}
