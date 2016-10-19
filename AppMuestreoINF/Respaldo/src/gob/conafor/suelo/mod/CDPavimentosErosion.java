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

public class CDPavimentosErosion {

    private String query;
    private CEPavimentosErosion cePaviemnto = new CEPavimentosErosion();
    // Condiciones de degradacion del suelo en pedestal

    public List<Integer> getNumeroPavimento(int sitioID) {
        List<Integer> listPavimento = new ArrayList<>();
        query = "SELECT SitioID, Numero FROM SUELO_PavimentoErosion WHERE SitioID= " + sitioID;
        Connection conn = LocalConnection.getConnection();

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                listPavimento.add(rs.getInt("Numero"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener los numeros de pavimento de erosion ",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en los numeros de pavimento de erosion ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        listPavimento.add(0, null);
        return listPavimento;
    }

    public CEPavimentosErosion getPavimento(int pavimentoID) {
        query = "SELECT PavimentoErosionID, Numero, Diametro FROM SUELO_PavimentoErosion WHERE PavimentoErosionID=" + pavimentoID;
        Connection conn = LocalConnection.getConnection();

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                this.cePaviemnto.setNumero(rs.getInt("Numero"));
                this.cePaviemnto.setDiametro(rs.getFloat("Diametro"));
            }
            return cePaviemnto;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al obtener pavimento de erosion ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al obtener pavimento de erosion ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
     public CEPavimentosErosion getPavimentoPorNumero(CEPavimentosErosion cePavimento) {
        query = "SELECT PavimentoErosionID, SitioID, Numero, Diametro FROM SUELO_PavimentoErosion WHERE SitioID=" + cePavimento.getSitioID() + 
                " AND Numero= " + cePavimento.getNumero();
        Connection conn = LocalConnection.getConnection();

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                this.cePaviemnto.setNumero(rs.getInt("PavimentoErosionID"));
                this.cePaviemnto.setDiametro(rs.getFloat("Diametro"));
            }
            return this.cePaviemnto;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al obtener pavimento de erosion ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al obtener pavimento de erosion ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void insertPavimento(CEPavimentosErosion cePavimento) {
        query = "INSERT INTO SUELO_PavimentoErosion(SitioID, Diametro)VALUES(" + cePavimento.getSitioID() + ", " + cePavimento.getDiametro() + ")";
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo guardar la informacion de pavimento de erosion ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos al insertar datos de pavimento de erosion",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void updatePavimento(CEPavimentosErosion cePavimento) {
        query = "UPDATE SUELO_PavimentoErosion SET Diametro= " + cePavimento.getDiametro()
                + " WHERE PavimentoErosionID= " + cePavimento.getPavimentosErosionID();
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo modificar la información de pavimento de erosion ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la modificación de pavimento de erosion ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void deletePavimento(int pavimentoID) {
        query = "DELETE FROM SUELO_PavimentoErosion WHERE PavimentoErosionID= " + pavimentoID;
        Connection conn = LocalConnection.getConnection();
        try {

            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo eliminar la información de pavimento de erosion ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos  al eliminar pavimento de erosion ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void deletePavimentoSitio(int sitioID) {
        query = "DELETE FROM SUELO_PavimentoErosion WHERE SitioID= " + sitioID;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo eliminar la información de pavimento de erosion por sitio", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos  al eliminar pavimento de erosion por sitio",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public DefaultTableModel getTablaPavimento(int sitioID) {
        query = "SELECT PavimentoErosionID, SitioID, Numero, Diametro FROM SUELO_PavimentoErosion WHERE SitioID= " + sitioID;
        String[] encabezados = {"PavimentoErosionID", "SitioID", "Numero", "Diametro"};
        DefaultTableModel costrasModel = new DefaultTableModel(null, encabezados);
        Object[] datosCostras = new Object[4];
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                datosCostras[0] = rs.getInt("PavimentoErosionID");
                datosCostras[1] = rs.getInt("SitioID");
                datosCostras[2] = rs.getInt("Numero");
                datosCostras[3] = rs.getFloat("Diametro");
                costrasModel.addRow(datosCostras);
            }
            st.close();
            rs.close();
            return costrasModel;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al obtener los datos de la vista de pavimento de erosion ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en para la vista de pavimento de erosion ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private List<Integer> getListPavimentoID(int sitioID) {
        List<Integer> listPedestalID = new ArrayList<>();
        query = "SELECT PavimentoErosionID, SitioID FROM SUELO_PavimentoErosion WHERE SitioID= " + sitioID + " ORDER BY PavimentoErosionID ASC";
        Connection conn = LocalConnection.getConnection();

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                listPedestalID.add(rs.getInt("PavimentoErosionID"));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener los datos de pavimento de erosion id ",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en lista de pavimento de erosion id", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        return listPedestalID;
    }

    public void renumerarRegistros(int sitioID) {
        List<Integer> listPavimentoID = getListPavimentoID(sitioID);
        Connection conn = LocalConnection.getConnection();

        if (listPavimentoID != null) {
            int size = listPavimentoID.size();
            int consecutivo = 1;

            try {
                for (int i = 0; i < size; i++) {
                    Statement st = conn.createStatement();
                    query = "UPDATE SUELO_PavimentoErosion SET Numero= " + consecutivo + "  WHERE PavimentoErosionID= " + listPavimentoID.get(i);
                    st.executeUpdate(query);
                    conn.commit();
                    consecutivo++;
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,
                        "Error! al enumerar el numero de pavimento de erosion " + e.getClass().getName() + " : " + e.getMessage(),
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al enumerar pavimento de erosion ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    
     public boolean validarTablaPavimentos(int sitioID) {
        query = "SELECT * FROM SUELO_PavimentoErosion WHERE SitioID= " + sitioID;
        boolean vacio = true;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                vacio = false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al validar la presencia de datos en la tabla de pavimentos"
                    + e.getClass().getName() + " : " + e.getMessage(), "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la tabla de pavimentos"
                        + e.getClass().getName() + " : " + e.getMessage(), "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        return vacio;
    }
}
