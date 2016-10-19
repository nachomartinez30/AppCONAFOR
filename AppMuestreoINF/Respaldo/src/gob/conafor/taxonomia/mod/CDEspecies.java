package gob.conafor.taxonomia.mod;

import gob.conafor.conn.dat.LocalConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class CDEspecies {

    private String query;
    private final CatEFamiliaEspecie familiaEspecie = new CatEFamiliaEspecie();
    private final List<CatEFamiliaEspecie> listFamiliaEspecie = new ArrayList<>();
    private final CatEGenero genero = new CatEGenero();
    private final List<CatEGenero> listGeneros = new ArrayList<>();
    private final List<CatEEspecie> listEspecies = new ArrayList<>();
    private final List<CatEInfraespecie> listInfraespecies = new ArrayList<>();
    
    public List<CatEFamiliaEspecie> getFamiliaEspecies() {
        query = "SELECT FamiliaID, Nombre FROM CAT_FamiliaEspecie ORDER BY Nombre";
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                CatEFamiliaEspecie familia = new CatEFamiliaEspecie();
                familia.setFamiliaID(rs.getInt("FamiliaID"));
                familia.setNombre(rs.getString("Nombre"));
                listFamiliaEspecie.add(familia);
            }
            st.close();
            rs.close();
            listFamiliaEspecie.add(0, null);
            return listFamiliaEspecie;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener el catálogo de familia ",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos al generar el listado de familias",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public CatEFamiliaEspecie getFamilia(int generoID) {
        query = "SELECT fa.FamiliaID, fa.Nombre  FROM CAT_FamiliaEspecie fa"
                + " LEFT JOIN CAT_Genero ge ON fa.FamiliaID = ge.FamiliaID"
                + " WHERE ge.GeneroID= " + generoID;
        Connection conn = LocalConnection.getConnection();
        CatEFamiliaEspecie ceFamilia = new CatEFamiliaEspecie();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                ceFamilia.setFamiliaID(rs.getInt("FamiliaID"));
                ceFamilia.setNombre(rs.getString("Nombre"));
            }
            st.close();
            rs.close();
            return ceFamilia;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener el catálogo de familia ",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos al generar el listado de familias",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public List<CatEGenero> getGeneros(int familiaID) {
        query = "SELECT GeneroID, Nombre, FamiliaID FROM CAT_Genero WHERE FamiliaID= " + familiaID;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                CatEGenero genero = new CatEGenero();
                genero.setGeneroID(rs.getInt("GeneroID"));
                genero.setNombre(rs.getString("Nombre"));
                genero.setFamiliaID(rs.getInt("FamiliaID"));
                listGeneros.add(genero);
            }
            st.close();
            rs.close();
            listGeneros.add(0, null);
            return listGeneros;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener el catálogo de generos ",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos al generar el listado de generos",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public List<CatEGenero> getGenero() {
        query = "SELECT GeneroID, Nombre, FamiliaID FROM CAT_Genero ORDER BY Nombre";
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                CatEGenero genero = new CatEGenero();
                genero.setGeneroID(rs.getInt("GeneroID"));
                genero.setNombre(rs.getString("Nombre"));
                genero.setFamiliaID(rs.getInt("FamiliaID"));
                listGeneros.add(genero);
            }
            st.close();
            rs.close();
            listGeneros.add(0, null);
            return listGeneros;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener el catálogo de generos ",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos al generar el listado de generos",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public List<CatEGenero> getGeneroFiltrado(char filtro){
         query = "SELECT GeneroID, Nombre, FamiliaID FROM CAT_Genero WHERE NOMBRE LIKE '" + filtro + "%' ORDER BY Nombre";
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                CatEGenero genero = new CatEGenero();
                genero.setGeneroID(rs.getInt("GeneroID"));
                genero.setNombre(rs.getString("Nombre"));
                genero.setFamiliaID(rs.getInt("FamiliaID"));
                listGeneros.add(genero);
            }
            st.close();
            rs.close();
           // listGeneros.add(0, null);
            return listGeneros;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener el catálogo de generos filtrados",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos al generar el listado de generos filtrados",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public List<CatEGenero> getGenerosSF(){
        query = "SELECT GeneroID, Nombre, FamiliaID FROM CAT_Genero";
        Connection conn = LocalConnection.getConnection();
        List listGenero = new ArrayList<>();
        try{
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                CatEGenero genero = new CatEGenero();
                genero.setGeneroID(rs.getInt("GeneroID"));
                genero.setNombre(rs.getString("Nombre"));
                genero.setFamiliaID(rs.getInt("FamiliaID"));
                listGenero.add(genero);
            }
            st.close();
            rs.close();
            listGenero.add(0, null);
            return listGenero;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener el catálogo de generosSF ",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos al generar el listado de generosSF ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public List<CatEEspecie> getEspecies(int generoID) {
        query = "SELECT EspecieID, Nombre, GeneroID FROM CAT_Especie WHERE GeneroID= " + generoID + " ORDER BY Nombre";
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                CatEEspecie especie = new CatEEspecie();
                especie.setEspecieID(rs.getInt("EspecieID"));
                especie.setNombre(rs.getString("Nombre"));
                especie.setGeneroID(rs.getInt("GeneroID"));
                listEspecies.add(especie);
            }
            st.close();
            rs.close();
            listEspecies.add(0, null);
            return listEspecies;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener el catálogo de especies ",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos al generar el listado de especies"
                        + e.getClass().getName() + " : " + e.getMessage(),
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public List<CatEInfraespecie> getInfraespecie(int especieID) {
        query = "SELECT InfraespecieID, Nombre, EspecieID FROM CAT_Infraespecie WHERE EspecieID= " + especieID;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                CatEInfraespecie infraespecie = new CatEInfraespecie();
                infraespecie.setInfraespecieID(rs.getInt("InfraespecieID"));
                infraespecie.setNombre(rs.getString("Nombre"));
                infraespecie.setEspecieID(rs.getInt("EspecieID"));
                listInfraespecies.add(infraespecie);
            }
            st.close();
            rs.close();
            listInfraespecies.add(0, null);
            return listInfraespecies;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener el catálogo de Infraespecies ",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos al generar el listado de Infraespecies",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}
