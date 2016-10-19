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

public class CDCostras {

    private String query;
    private CECostras ceCostras = new CECostras();
    // Condiciones de degradacion del suelo en pedestal

    public List<Integer> getNumeroCostras(int sitioID) {
        List<Integer> listPedestal = new ArrayList<>();
        query = "SELECT SitioID, Numero FROM SUELO_Costras WHERE SitioID= " + sitioID;
        Connection conn = LocalConnection.getConnection();

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                listPedestal.add(rs.getInt("Numero"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener los numeros de costras ",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en los numeros de costras ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        listPedestal.add(0, null);
        return listPedestal;
    }

    public CECostras getCostras(int costrasID) {
        query = "SELECT CostrasID, Numero, Diametro FROM SUELO_Costras WHERE CostrasID=" + costrasID;
        Connection conn = LocalConnection.getConnection();

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                this.ceCostras.setNumero(rs.getInt("Numero"));
                this.ceCostras.setDiametro(rs.getFloat("Diametro"));
            }
            return ceCostras;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al obtener el diametro de costras ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al obtener el diametro de costras ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public CECostras getCostrasPorNumero(CECostras ceCostras) {
        query = "SELECT CostrasID, SitioID, Numero, Diametro FROM SUELO_Costras WHERE SitioID=" + ceCostras.getSitioID() + 
                " AND Numero= " + ceCostras.getNumero();
        Connection conn = LocalConnection.getConnection();

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                this.ceCostras.setNumero(rs.getInt("CostrasID"));
                this.ceCostras.setDiametro(rs.getFloat("Diametro"));
            }
            return this.ceCostras;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al obtener el diametro de costras ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al obtener el diametro de costras ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void insertCostras(CECostras ceCostras) {
        query = "INSERT INTO SUELO_Costras(SitioID, Diametro)VALUES(" + ceCostras.getSitioID() + ", " + ceCostras.getDiametro() + ")";
        Connection conn = LocalConnection.getConnection();

        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo guardar la informacion de costras ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos al insertar datos de costras ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void updateCostras(CECostras ceCostras) {
        query = "UPDATE SUELO_Costras SET Diametro= " + ceCostras.getDiametro()
                + " WHERE CostrasID= " + ceCostras.getCostrasID();
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

    public void deleteCostras(int costrasID) {
        query = "DELETE FROM SUELO_Costras WHERE CostrasID= " + costrasID;
        Connection conn = LocalConnection.getConnection();
        try {

            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo eliminar la información de costras ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos  al eliminar costras ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void deleteCostrasSitio(int sitioID){
        query = "DELETE FROM SUELO_Costras WHERE SitioID= " + sitioID;
        Connection conn = LocalConnection.getConnection();
        try {

            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo eliminar la información de costras por sitio", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos  al eliminar costras por sitio",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public DefaultTableModel getTablaCostras(int sitioID) {
        query = "SELECT CostrasID, SitioID, Numero, Diametro FROM SUELO_Costras WHERE SitioID= " + sitioID;

        String[] encabezados = {"CostrasID", "SitioID", "Numero", "Diametro"};

        DefaultTableModel costrasModel = new DefaultTableModel(null, encabezados);
        Object[] datosCostras = new Object[4];
        Connection conn = LocalConnection.getConnection();

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                datosCostras[0] = rs.getInt("CostrasID");
                datosCostras[1] = rs.getInt("SitioID");
                datosCostras[2] = rs.getInt("Numero");
                datosCostras[3] = rs.getFloat("Diametro");

                costrasModel.addRow(datosCostras);
            }
            st.close();
            rs.close();
            return costrasModel;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al obtener los datos de la vista de costras ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en para la vista de costras ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private List<Integer> getListCostrasID(int sitioID) {
        List<Integer> listPedestalID = new ArrayList<>();
        query = "SELECT CostrasID, SitioID FROM SUELO_Costras WHERE SitioID= " + sitioID + " ORDER BY CostrasID ASC";
        Connection conn = LocalConnection.getConnection();

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                listPedestalID.add(rs.getInt("CostrasID"));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener los datos de costra id ",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en lista de costra id", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        return listPedestalID;
    }

    public void renumerarRegistros(int sitioID) {
        List<Integer> listCostrasID = getListCostrasID(sitioID);
        Connection conn = LocalConnection.getConnection();

        if (listCostrasID != null) {
            int size = listCostrasID.size();
            int consecutivo = 1;

            try {
                for (int i = 0; i < size; i++) {
                    Statement st = conn.createStatement();
                    query = "UPDATE SUELO_Costras SET Numero= " + consecutivo + "  WHERE CostrasID= " + listCostrasID.get(i);
                    st.executeUpdate(query);
                    conn.commit();
                    consecutivo++;
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,
                        "Error! al enumerar el numero de costras ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al enumerar costras ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    
    public boolean validarTablaCostras(int sitioID) {
        query = "SELECT * FROM SUELO_Costras WHERE SitioID= " + sitioID;
        boolean vacio = true;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                vacio = false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al validar la presencia de datos en la tabla de costras", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la tabla de costras", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        return vacio;
    }
}
