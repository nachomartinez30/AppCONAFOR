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

public class CDVegetacionMenor {

    private String query;
    public CEVegetacionMenor vegetacionMenor = new CEVegetacionMenor();
    private int vegetacionMenorID;

    public CEVegetacionMenor getRegistroVegetacionMenor(int vegetacionMenorID) {
        query = "SELECT vm.VegetacionMenorID, vm.Consecutivo, vm.FamiliaID, vm.GeneroID, vm.EspecieID, vm.InfraespecieID, vm.NombreComun, vm.FormaVidaID, "
                + "vm.CondicionID, vm.Numero0110, vm.Numero1125, vm.Numero2650, vm.Numero5175, vm.Numero76100, vm.Numero101125, vm.Numero126150, vm.Numero150, vm.PorcentajeCobertura, "
                + "vm.vigorID, ad1.AgenteDanioID AS AgenteDanio1, ad1.SeveridadID AS Severidad1, ad2.AgenteDanioID AS AgenteDanio2, ad2.SeveridadID AS Severidad2, vm.ClaveColecta "
                + "FROM TAXONOMIA_VegetacionMenor vm "
                + "LEFT JOIN VEGETACIONMENOR_DanioSeveridad ad1 ON vm.VegetacionMenorID = ad1.VegetacionMenorID AND ad1.NumeroDanio = 1 "
                + "LEFT JOIN VEGETACIONMENOR_DanioSeveridad ad2 ON vm.VegetacionMenorID = ad2.VegetacionMenorID AND ad2.NumeroDanio = 2 "
                + "WHERE vm.VegetacionMenorID= " + vegetacionMenorID;
        Connection conn = LocalConnection.getConnection();
        
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                vegetacionMenor.setVegetacionMenorID(rs.getInt("VegetacionMenorID"));
                vegetacionMenor.setConsecutivo(rs.getInt("Consecutivo"));
                vegetacionMenor.setFamiliaID(rs.getInt("FamiliaID"));
                vegetacionMenor.setGeneroID(rs.getInt("GeneroID"));
                vegetacionMenor.setEspecieID(rs.getInt("EspecieID"));
                vegetacionMenor.setInfraespecieID(rs.getInt("InfraespecieID"));
                vegetacionMenor.setNombreComun(rs.getString("NombreComun"));
                vegetacionMenor.setFormaVidaID(rs.getInt("FormaVidaID"));
                vegetacionMenor.setCondicionID(rs.getInt("CondicionID"));
                vegetacionMenor.setNumero0110(rs.getInt("Numero0110"));
                vegetacionMenor.setNumero1125(rs.getInt("Numero1125"));
                vegetacionMenor.setNumero2650(rs.getInt("Numero2650"));
                vegetacionMenor.setNumero5175(rs.getInt("Numero5175"));
                vegetacionMenor.setNumero76100(rs.getInt("Numero76100"));
                vegetacionMenor.setNumero101125(rs.getInt("Numero101125"));
                vegetacionMenor.setNumero126150(rs.getInt("Numero126150"));
                vegetacionMenor.setNumero150(rs.getInt("Numero150"));
                vegetacionMenor.setPorcentajeCobertura(rs.getInt("PorcentajeCobertura"));
                vegetacionMenor.setAgenteDanio1ID(rs.getInt("AgenteDanio1"));
                vegetacionMenor.setSeveridad1ID(rs.getInt("Severidad1"));
                vegetacionMenor.setAgenteDanio2ID(rs.getInt("AgenteDanio2"));
                vegetacionMenor.setSeveridad2ID(rs.getInt("Severidad2"));
                vegetacionMenor.setVigorID(rs.getInt("VigorID"));
                vegetacionMenor.setClaveColecta(rs.getString("ClaveColecta"));
            }
            st.close();
            rs.close();
            return vegetacionMenor;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al obtener los datos de vegetacion menor ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;

        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al obtener la vegetación menor ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void insertVegetacionMenor(CEVegetacionMenor ceVegetacionMenor) {
        query = "INSERT INTO TAXONOMIA_VegetacionMenor(SitioID, FamiliaID, GeneroID, EspecieID, InfraespecieID, NombreComun, "
                + "FormaVidaID, CondicionID, Numero0110, Numero1125, Numero2650, Numero5175, Numero76100, "
                + "Numero101125, Numero126150, Numero150, PorcentajeCobertura, VigorID)VALUES(" + ceVegetacionMenor.getSitioID()
                + ", " + ceVegetacionMenor.getFamiliaID() + ", " + ceVegetacionMenor.getGeneroID() + ", " + ceVegetacionMenor.getEspecieID()
                + ", " + ceVegetacionMenor.getInfraespecieID() + ", '" + ceVegetacionMenor.getNombreComun() + "'"
                + ", " + ceVegetacionMenor.getFormaVidaID() + ", " + ceVegetacionMenor.getCondicionID() + ", " + ceVegetacionMenor.getNumero0110()
                + ", " + ceVegetacionMenor.getNumero1125() + ", " + ceVegetacionMenor.getNumero2650() + ", " + ceVegetacionMenor.getNumero5175()
                + ", " + ceVegetacionMenor.getNumero76100() + ", " + ceVegetacionMenor.getNumero101125() + ", " + ceVegetacionMenor.getNumero126150()
                + ", " + ceVegetacionMenor.getNumero150() + ", " + ceVegetacionMenor.getPorcentajeCobertura() + ", " + ceVegetacionMenor.getVigorID() + ")";
        
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
           vegetacionMenor.setVegetacionMenorID(getLastIndexInsertedVegetacionMenor());
            conn.commit();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo guardar la informacion en vegetacion menor ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos al insertar datos de vegetación menor",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void updateVegetacionMenor(CEVegetacionMenor ceVegetacionMenor) {
        query = "UPDATE TAXONOMIA_VegetacionMenor SET FamiliaID= " + ceVegetacionMenor.getFamiliaID() + ", GeneroID= " + ceVegetacionMenor.getGeneroID()
                + ", EspecieID= " + ceVegetacionMenor.getEspecieID() + ", InfraespecieID= " + ceVegetacionMenor.getInfraespecieID() + ", NombreComun= '" + ceVegetacionMenor.getNombreComun() + "', FormaVidaID= " + ceVegetacionMenor.getFormaVidaID() + ", CondicionID= " + ceVegetacionMenor.getCondicionID()
                + ", Numero0110= " + ceVegetacionMenor.getNumero0110() + ", Numero1125= " + ceVegetacionMenor.getNumero1125() + ", Numero2650= " + ceVegetacionMenor.getNumero2650()
                + ", Numero5175= " + ceVegetacionMenor.getNumero5175() + ", Numero76100= " + ceVegetacionMenor.getNumero76100() + ", Numero101125= " + ceVegetacionMenor.getNumero101125()
                + ", Numero126150= " + ceVegetacionMenor.getNumero126150() + ", Numero150= " + ceVegetacionMenor.getNumero150() + ", PorcentajeCobertura= " + ceVegetacionMenor.getPorcentajeCobertura()
                + ", VigorID= " + ceVegetacionMenor.getVigorID() + " WHERE VegetacionMenorID= " + ceVegetacionMenor.getVegetacionMenorID();
        Connection conn = LocalConnection.getConnection();

        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo modificar la información de vegetación menor ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la modificación de vegetación menor ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void deleteVegetacionMenor(int vegetacionMenorID) {
        query = "DELETE FROM TAXONOMIA_VegetacionMenor WHERE VegetacionMenorID= " + vegetacionMenorID;
        Connection conn = LocalConnection.getConnection();


        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
           
            vegetacionMenor.setVegetacionMenorID(getLastIndexInsertedVegetacionMenor());
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo eliminar la información de vegetación menor ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos  al eliminar vegetació menor ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

     public int getLastIndexInsertedVegetacionMenor(){
       int valor=0;
       
        String query = "select MAX(vegetacionMenorID) as last_ID from TAXONOMIA_VegetacionMenor";
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeQuery(query);
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                 valor = rs.getInt("last_ID");
                 
            }
            conn.commit();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo modificar la información de arbolado ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la modificación de arbolado", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        return valor;
   }
    
    public DefaultTableModel getTableVegetacionMenor(int sitioID) {
        query = "SELECT VegetacionMenorID, SitioID, Consecutivo, Familia, Genero, Especie, Infraespecie, NombreComun, "
                + "FormaVida, Condicion, Numero0110, Numero1125, Numero2650, Numero5175, Numero76100, Numero101125, Numero126150, "
                + "Numero150, PorcentajeCobertura, Agente1, Severidad1, Agente2, Severidad2, Vigor, ClaveColecta FROM VW_VegetacionMenor WHERE SitioID= " + sitioID;
        String[] encabezados = {"VegetacionMenorID", "SitioID", "Consecutivo", "Familia", "Genero", "Especie", "Infraespecie", "Nombre comun",
            "Forma de vida", "Condicion", "01-10", "11-25", "26-50", "51-75", "76-100", "101-125", "126-150", ">150", "Cobertura %", "Agente daño 1",
            "Severidad 1", "Agente daño 2", "Severidad 2", "Vigor", "Clave de colecta"};
         //System.out.println(query);
        DefaultTableModel vegetacionMenorModel = new DefaultTableModel(null, encabezados);
        Object[] datosVegetacionMenor = new Object[25];
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                datosVegetacionMenor[0] = rs.getInt("VegetacionMenorID");
                datosVegetacionMenor[1] = rs.getInt("SitioID");
                datosVegetacionMenor[2] = rs.getString("Consecutivo");
                datosVegetacionMenor[3] = rs.getString("Familia");
                datosVegetacionMenor[4] = rs.getString("Genero");
                datosVegetacionMenor[5] = rs.getString("Especie");
                datosVegetacionMenor[6] = rs.getString("Infraespecie");
                datosVegetacionMenor[7] = rs.getString("NombreComun");
                datosVegetacionMenor[8] = rs.getString("FormaVida");
                datosVegetacionMenor[9] = rs.getString("Condicion");
                datosVegetacionMenor[10] = rs.getString("Numero0110");
                datosVegetacionMenor[11] = rs.getString("Numero1125");
                datosVegetacionMenor[12] = rs.getString("Numero2650");
                datosVegetacionMenor[13] = rs.getString("Numero5175");
                datosVegetacionMenor[14] = rs.getString("Numero76100");
                datosVegetacionMenor[15] = rs.getString("Numero101125");
                datosVegetacionMenor[16] = rs.getString("Numero126150");
                datosVegetacionMenor[17] = rs.getString("Numero150");
                datosVegetacionMenor[18] = rs.getString("PorcentajeCobertura");
                datosVegetacionMenor[19] = rs.getString("Agente1");
                datosVegetacionMenor[20] = rs.getString("Severidad1");
                datosVegetacionMenor[21] = rs.getString("Agente2");
                datosVegetacionMenor[22] = rs.getString("Severidad2");
                datosVegetacionMenor[23] = rs.getString("Vigor");
                datosVegetacionMenor[24] = rs.getString("ClaveColecta");
                vegetacionMenorModel.addRow(datosVegetacionMenor);
            }
            st.close();
            rs.close();
            return vegetacionMenorModel;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al obtener los datos de la vista de vegetación menor ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en para la vista de vegetación menor ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public int getLastVMID() {
        return this.vegetacionMenorID;
    }

    public boolean validarHayRegistros(int sitioID) {
        query = "SELECT * FROM TAXONOMIA_VegetacionMenor WHERE SitioID= " + sitioID;
        boolean vacio = false;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                vacio = true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al obtener los datos de la tabla de vegetación menor ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en datos de validar tabla de vegetación menor ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        return vacio;
    }

    public void enumerarConsecutivo(int sitioID) {
        List<Integer> listVegetacionID = getVegetacionMenorID(sitioID);
        Connection conn = LocalConnection.getConnection();
        if (listVegetacionID != null) {
            int size = listVegetacionID.size();
            int consecutivo = 1;
            try {
                for (int i = 0; i < size; i++) {
                    Statement st = conn.createStatement();
                    query = "UPDATE TAXONOMIA_VegetacionMenor SET Consecutivo= " + consecutivo + "  WHERE VegetacionMenorID= " + listVegetacionID.get(i);
                    st.executeUpdate(query);
                    conn.commit();
                    consecutivo++;
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,
                        "Error! al enumerar el consecutivo de vegetación menor ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al enumerar el continuo de vegetación menor ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private List<Integer> getVegetacionMenorID(int sitioID) {
        List<Integer> listRepobladoID = new ArrayList<>();
        query = "SELECT VegetacionMenorID, SitioID FROM TAXONOMIA_VegetacionMenor WHERE SitioID= " + sitioID + " ORDER BY VegetacionMenorID ASC";
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                listRepobladoID.add(rs.getInt("VegetacionMenorID"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener los datos de vegetacion menor id ",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos vegetación menor id ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        return listRepobladoID;
    }
}
