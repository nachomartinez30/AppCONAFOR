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

public class CDVegetacionMayorIndividual {
    
    private String query;
    private int vegetacionMayorID;
    
    
    public CEVegetacionMayorIndividual getRegistroVM(int vegetacionMayorID) {
        query = "SELECT vm.VegetacionMayorID, vm.Consecutivo, vm.NoIndividuo, vm.FormaVidaID, vm.CondicionID, vm.FamiliaID, vm.GeneroID, vm.EspecieID, vm.InfraespecieID, "
                + "vm.NombreComun, vm.FormaGeometricaID, vm.DensidadFollajeID, vm.DiametroBase, vm.AlturaTotal, vm.DiametroCoberturaMayor, "
                + "vm.DiametroCoberturaMenor, ad1.AgenteDanioID AS Danio1, ad1.SeveridadID AS Severidad1, ad2.AgenteDanioID AS Danio2, ad2.SeveridadID AS Severidad2, vm.VigorID, vm.ClaveColecta FROM TAXONOMIA_VegetacionMayorIndividual vm "
                + "LEFT JOIN VEGETACIONMAYORI_DanioSeveridad ad1 ON vm.VegetacionMayorID = ad1.VegetacionMayorID AND ad1.NumeroDanio = 1 "
                + "LEFT JOIN VEGETACIONMAYORI_DanioSeveridad ad2 ON vm.VegetacionMayorID = ad2.VegetacionMayorID AND ad2.NumeroDanio = 2 "
                + "WHERE vm.VegetacionMayorID= " + vegetacionMayorID;
        Connection conn = LocalConnection.getConnection();
        CEVegetacionMayorIndividual vegetacionMayor = new CEVegetacionMayorIndividual();

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                vegetacionMayor.setVegetacionMayorID(rs.getInt("VegetacionMayorID"));
                vegetacionMayor.setConsecutivo(rs.getInt("Consecutivo"));
                vegetacionMayor.setNumeroIndividuo(rs.getInt("NoIndividuo"));
                vegetacionMayor.setFormaVidaID(rs.getInt("FormaVidaID"));
                vegetacionMayor.setCondicionID(rs.getInt("CondicionID"));
                vegetacionMayor.setFamiliaID(rs.getInt("FamiliaID"));
                vegetacionMayor.setGeneroID(rs.getInt("GeneroID"));
                vegetacionMayor.setEspecieID(rs.getInt("EspecieID"));
                vegetacionMayor.setInfraespecieID(rs.getInt("InfraespecieID"));
                vegetacionMayor.setNombreComun(rs.getString("NombreComun"));
                vegetacionMayor.setFormaGeometricaID(rs.getInt("FormaGeometricaID"));
                vegetacionMayor.setDencidadFollajeID(rs.getInt("DensidadFollajeID"));
                vegetacionMayor.setDiametroBase(rs.getFloat("DiametroBase"));
                vegetacionMayor.setAlturaTotal(rs.getFloat("AlturaTotal"));
                vegetacionMayor.setDiametroCoberturaMayor(rs.getFloat("DiametroCoberturaMayor"));
                vegetacionMayor.setDiametroCoberturaMenor(rs.getFloat("DiametroCoberturaMenor"));
                vegetacionMayor.setAgenteDanio1ID(rs.getInt("Danio1"));
                vegetacionMayor.setSeveridad1ID(rs.getInt("Severidad1"));
                vegetacionMayor.setAgenteDanio2ID(rs.getInt("Danio2"));
                vegetacionMayor.setSeveridad2ID(rs.getInt("Severidad2"));
                vegetacionMayor.setVigorID(rs.getInt("VigorID"));
                vegetacionMayor.setClaveColecta(rs.getString("ClaveColecta"));
            }
            st.close();
            rs.close();
            return vegetacionMayor;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al obtener los datos de vegetacion mayor individual ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al obtener la vegetación mayor individual ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
     public int getLastIndexInsertedVegetacionMayorIndividual(){
       int valor=0;
       
        String query = "select MAX(VegetacionMayorID) as last_ID from TAXONOMIA_VegetacionMayorIndividual";
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
    
    public void insertVegetacionMayor(CEVegetacionMayorIndividual vegetacionMayor){
        query = "INSERT INTO TAXONOMIA_VegetacionMayorIndividual(SitioID,Consecutivo, NoIndividuo, FormaVidaID, CondicionID, FamiliaID, GeneroID, "
                + "EspecieID, InfraespecieID, NombreComun, FormaGeometricaID, DensidadFollajeID, DiametroBase, AlturaTotal, "
                + "DiametroCoberturaMayor, DiametroCoberturaMenor, VigorID)VALUES(" + vegetacionMayor.getSitioID() + ", " +vegetacionMayor.getConsecutivo() + ", " + vegetacionMayor.getNumeroIndividuo() 
                + ", " + vegetacionMayor.getFormaVidaID() + ", " + vegetacionMayor.getCondicionID() + ", " + vegetacionMayor.getFamiliaID() + ", " + vegetacionMayor.getGeneroID() 
                + ", " + vegetacionMayor.getEspecieID() + ", " + vegetacionMayor.getInfraespecieID() + ", '" + vegetacionMayor.getNombreComun() 
                + "', " + vegetacionMayor.getFormaGeometricaID() + ", " + vegetacionMayor.getDencidadFollajeID() + ", " + vegetacionMayor.getDiametroBase() 
                + ", " + vegetacionMayor.getAlturaTotal() + ", " + vegetacionMayor.getDiametroCoberturaMayor() + ", " + vegetacionMayor.getDiametroCoberturaMenor() + ", " + vegetacionMayor.getVigorID() + ")";

        Connection conn = LocalConnection.getConnection();
        
        try{
            Statement st = conn.createStatement();
            st.executeUpdate(query);
           
            vegetacionMayor.setVegetacionMayorID(getLastIndexInsertedVegetacionMayorIndividual());
            conn.commit();
            st.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error! no se pudo guardar la informacion en vegetacion mayor individuales ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos al insertar datos de vegetacion mayor individuales "
                        + e.getClass().getName() + " : " + e.getMessage(),
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void updateVegetacionMayor(CEVegetacionMayorIndividual vegetacionMayor) {
        query = "UPDATE TAXONOMIA_VegetacionMayorIndividual SET FormaVidaID= " + vegetacionMayor.getFormaVidaID() + ", CondicionID= " + vegetacionMayor.getCondicionID()
                + ", FamiliaID= " + vegetacionMayor.getFamiliaID() + ", GeneroID= " + vegetacionMayor.getGeneroID() + ", EspecieID= " + vegetacionMayor.getEspecieID()
                + ", InfraespecieID= " + vegetacionMayor.getInfraespecieID() + ", NombreComun= '" + vegetacionMayor.getNombreComun()
                + "', FormaGeometricaID= " + vegetacionMayor.getFormaGeometricaID() + ", DensidadFollajeID= " + vegetacionMayor.getDencidadFollajeID() + ", DiametroBase= " + vegetacionMayor.getDiametroBase()
                + ", AlturaTotal= " + vegetacionMayor.getAlturaTotal() + ", DiametroCoberturaMayor= " + vegetacionMayor.getDiametroCoberturaMayor() + ", DiametroCoberturaMenor= " + vegetacionMayor.getDiametroCoberturaMenor()
                + ", VigorID= " + vegetacionMayor.getVigorID() + " WHERE VegetacionMayorID= " + vegetacionMayor.getVegetacionMayorID();
        Connection conn = LocalConnection.getConnection();

        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo modificar la información de vegetación mayor individual ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la modificación de vegetación mayor individual "
                        , "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void deleteVegetacionMayor(int vegetacionMayorID) {
        query = "DELETE FROM TAXONOMIA_VegetacionMayorIndividual WHERE VegetacionMayorID= " + vegetacionMayorID;
        System.out.println(query);
        Connection conn = LocalConnection.getConnection();
        String query2 = "SELECT last_insert_rowid()";

        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            ResultSet rs = st.executeQuery(query2);
            this.vegetacionMayorID = rs.getInt(1);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo eliminar la información de vegetación mayor individual ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos  al eliminar vegetación mayor individual ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void deleteAllVegetacionMayor(int sitioID) {
        query = "DELETE FROM TAXONOMIA_VegetacionMayorIndividual WHERE SitioID= " + sitioID;
        System.out.println("CDVEGMayor"+query);
        Connection conn = LocalConnection.getConnection();
        String query2 = "SELECT last_insert_rowid()";

        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            ResultSet rs = st.executeQuery(query2);
            this.vegetacionMayorID = rs.getInt(1);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo eliminar la información de vegetación mayor individual ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos  al eliminar vegetación mayor individual ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
     public int getLastVMID() {
        return this.vegetacionMayorID;
    }
     
    public void enumerarConsecutivo(int sitioID) {
        List<Integer> listVegetacionID = getVegetacionMayorID(sitioID);
        Connection conn = LocalConnection.getConnection();
        if (listVegetacionID != null) {
            int size = listVegetacionID.size();
            int consecutivo = 1;
            try {
                for (int i = 0; i < size; i++) {
                    Statement st = conn.createStatement();
                    query = "UPDATE TAXONOMIA_VegetacionMayorIndividual SET Consecutivo= " + consecutivo + "  WHERE VegetacionMayorID= " + listVegetacionID.get(i);
                    st.executeUpdate(query);
                    conn.commit();
                    consecutivo++;
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,
                        "Error! al enumerar el consecutivo de vegetación mayor ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al enumerar el continuo de vegetación mayor ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private List<Integer> getVegetacionMayorID(int sitioID) {
        List<Integer> listRepobladoID = new ArrayList<>();
        query = "SELECT VegetacionMayorID, SitioID FROM TAXONOMIA_VegetacionMayorIndividual WHERE SitioID= " + sitioID + " ORDER BY VegetacionMayorID ASC";
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                listRepobladoID.add(rs.getInt("VegetacionMayorID"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener los datos de vegetacion mayor id ",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos vegetación mayor id ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        return listRepobladoID;
    }
    
    public DefaultTableModel getTablaVegetacionMayor(int sitioID) {
        query = "SELECT VegetacionMayorID, SitioID, Consecutivo, NoIndividuo, FormaVida, Condicion, Familia, Genero, Especie, Infraespecie, "
                + "NombreComun, FormaGeometrica, DensidadFollaje, DiametroBase, AlturaTotal, DiametroCoberturaMayor, DiametroCoberturaMenor, "
                + "Agente1, Severidad1, Agente2, Severidad2, Vigor, ClaveColecta FROM VW_VegetacionMayorIndividual WHERE SitioID= " + sitioID+" ORDER BY Consecutivo";
        String[] encabezados = {"VegetacionMayorID", "SitioID", "Consecutivo", "Individuo", "Forma de vida", "Condicion", "Familia", "Genero", "Especie", "Infraespecie",
            "Nombre comun", "Forma geometrica", "Densidad follaje", "Diametro de base", "Altura total", "Diametro de cobertura mayor",
            "Diametro de cobertura menor", "Agente danio 1", "Severdiad 1", "Agente danio 2", "Severidad 2", "Vigor", "Clave de colecta"};
        DefaultTableModel vegetacionMayorModel = new DefaultTableModel(null, encabezados);
        Object[] datosVegetacionMayor = new Object[23];
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                datosVegetacionMayor[0] = rs.getInt("VegetacionMayorID");
                datosVegetacionMayor[1] = rs.getInt("SitioID");
                datosVegetacionMayor[2] = rs.getString("Consecutivo");
                datosVegetacionMayor[3] = rs.getString("NoIndividuo");
                datosVegetacionMayor[4] = rs.getString("FormaVida");
                datosVegetacionMayor[5] = rs.getString("Condicion");
                datosVegetacionMayor[6] = rs.getString("Familia");
                datosVegetacionMayor[7] = rs.getString("Genero");
                datosVegetacionMayor[8] = rs.getString("Especie");
                datosVegetacionMayor[9] = rs.getString("Infraespecie");
                datosVegetacionMayor[10] = rs.getString("NombreComun");
                datosVegetacionMayor[11] = rs.getString("FormaGeometrica");
                datosVegetacionMayor[12] = rs.getString("DensidadFollaje");
                datosVegetacionMayor[13] = rs.getString("DiametroBase");
                datosVegetacionMayor[14] = rs.getString("AlturaTotal");
                datosVegetacionMayor[15] = rs.getString("DiametroCoberturaMayor");
                datosVegetacionMayor[16] = rs.getString("DiametroCoberturaMenor");
                datosVegetacionMayor[17] = rs.getString("Agente1");
                datosVegetacionMayor[18] = rs.getString("Severidad1");
                datosVegetacionMayor[19] = rs.getString("Agente2");
                datosVegetacionMayor[20] = rs.getString("Severidad2");
                datosVegetacionMayor[21] = rs.getString("Vigor");
                datosVegetacionMayor[22] = rs.getString("ClaveColecta");
                vegetacionMayorModel.addRow(datosVegetacionMayor);
            }
            st.close();
            rs.close();
            return vegetacionMayorModel;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al obtener los datos de la vista de vegetación mayor individual "
                   , "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en para la vista de vegetación mayor individual ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public List<Integer> getConsecutivo(int sitioID) {
        List<Integer> listConsecutivo = new ArrayList<>();
        query = "SELECT SitioID, Consecutivo FROM TAXONOMIA_VegetacionMayorIndividual WHERE SitioID= " + sitioID;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                listConsecutivo.add(rs.getInt("Consecutivo"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener la lista de consecutivos ",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en lista de consecutivos", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        listConsecutivo.add(0, null);
        return listConsecutivo;
    }
    
    public void enumerarIndividuo(int sitioID) {
        List<Integer> listVegetacionMayorID = getVegetacionMayorIndividualID(sitioID);
        Connection conn = LocalConnection.getConnection();
        if (listVegetacionMayorID != null) {
            int size = listVegetacionMayorID.size();
            int individuo = 1;
            try {
                for (int i = 0; i < size; i++) {
                    Statement st = conn.createStatement();
                    query = "UPDATE TAXONOMIA_VegetacionMayorIndividual SET NoIndividuo= " + individuo + "  WHERE VegetacionMayorID= " + listVegetacionMayorID.get(i);
                    st.executeUpdate(query);
                    conn.commit();
                    individuo++;
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,
                        "Error! al enumerar al individuo",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al enumerar al individuo", "Conexion BD", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private List<Integer> getVegetacionMayorIndividualID(int sitioID) {
        List<Integer> listArboladoID = new ArrayList<>();
        query = "SELECT VegetacionMayorID, SitioID FROM TAXONOMIA_VegetacionMayorIndividual WHERE SitioID= " + sitioID + " ORDER BY VegetacionMayorID ASC";
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                listArboladoID.add(rs.getInt("VegetacionMayorID"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener los datos de vegetacion mayor id ",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en lista de vegetacion mayor id", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        return listArboladoID;
    }
}
