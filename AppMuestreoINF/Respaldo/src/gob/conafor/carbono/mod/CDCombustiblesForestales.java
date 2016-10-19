package gob.conafor.carbono.mod;

import gob.conafor.conn.dat.LocalConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class CDCombustiblesForestales {

    private String query;

    public List<Integer> getTransectoMaterial100(int sitioID) {
        List<Integer> listTransecto = new ArrayList<Integer>();
        for (int i = 1; i < 3; i++) {
            listTransecto.add(i);
        }
        listTransecto.add(0, null);
        query = "SELECT SitioID, Transecto FROM CARBONO_MaterialLenioso100 WHERE SitioID= " + sitioID;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Integer index = rs.getInt(2);
                listTransecto.remove(index);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener los numeros de transecto de material 100" + e.getClass().getName() + " : " + e.getMessage(),
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en los numeros de transecto en material 100"
                        + e.getClass().getName() + " : " + e.getMessage(), "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        return listTransecto;
    }
    
    public List<Integer> getTransectoMaterial1000() {
        List<Integer> listTransecto = new ArrayList<Integer>();
        for (int i = 1; i < 3; i++) {
            listTransecto.add(i);
        }
        listTransecto.add(0, null);
        return listTransecto;
    }
    
    public List<Integer> getGradoPutrefaccionl1000() {
        List<Integer> listTransecto = new ArrayList<Integer>();
        for (int i = 1; i < 6; i++) {
            listTransecto.add(i);
        }
        listTransecto.add(0, null);
        return listTransecto;
    }
    
     public List<Integer> getTransectoCubiertaVegetal() {
        List<Integer> listTransecto = new ArrayList<Integer>();
        for (int i = 1; i < 3; i++) {
            listTransecto.add(i);
        }
        listTransecto.add(0, null);
        return listTransecto;
    }
     
    public List<CatECarbonoComponente> getComponentesCV() {
        List<CatECarbonoComponente> listComponentes = new ArrayList<>();
        query = "SELECT ComponenteID, Componente FROM CAT_CarbonoComponente";
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                CatECarbonoComponente comp = new CatECarbonoComponente();

                comp.setComponenteID(rs.getInt("ComponenteID"));
                comp.setComponente(rs.getString("Componente"));
                listComponentes.add(comp);
            }
            listComponentes.add(0, null);
            return listComponentes;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener los datos de tipo de componentes de cubierta vegetal en carbono ",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {

                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en longitud de componentes en carbono  ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
            
 
    public CEMaterialLenioso100 getRegistroMaterialLenioso100(int material100ID) {
        query = "SELECT MaterialLenioso100ID, Transecto, Pendiente, UnaHora, DiezHoras, CienHoras FROM CARBONO_MaterialLenioso100 WHERE "
                + "MaterialLenioso100ID= " + material100ID;
        Connection conn = LocalConnection.getConnection();
        CEMaterialLenioso100 ceMaterial100 = new CEMaterialLenioso100();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                ceMaterial100.setTransecto(rs.getInt("Transecto"));
                ceMaterial100.setPendiente(rs.getInt("Pendiente"));
                ceMaterial100.setUnaHora(rs.getInt("UnaHora"));
                ceMaterial100.setDiezHoras(rs.getInt("DiezHoras"));
                ceMaterial100.setCienHoras(rs.getInt("CienHoras"));
            }
            return ceMaterial100;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al obtener un registro de material de 100 horas ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane
                        .showMessageDialog(null,
                                "Error! al cerrar la base de datos en datos de registro de material de 100 horas",
                                "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public CEMaterialLenioso1000 getRegistroMaterialLenioso1000(int materialLenioso1000) {
        query = "SELECT MaterialLenioso1000ID, Transecto, Diametro, Grado FROM CARBONO_MaterialLenioso1000 WHERE MaterialLenioso1000ID =" + materialLenioso1000;
        Connection conn = LocalConnection.getConnection();
        CEMaterialLenioso1000 ceMaterial1000 = new CEMaterialLenioso1000();

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                ceMaterial1000.setTransecto(rs.getInt("Transecto"));
                ceMaterial1000.setDiametro(rs.getFloat("Diametro"));
                ceMaterial1000.setGrado(rs.getInt("Grado"));
            }
            return ceMaterial1000;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al obtener un registro de material de 1000 horas ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane
                        .showMessageDialog(null,
                                "Error! al cerrar la base de datos en datos de registro de material de 1000 horas",
                                "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public CECubiertaVegetal getCubiertaVegetal(int cubiertaVegetalID) {
        query = "SELECT CubiertaVegetalID, Transecto, ComponenteID, Altura5, Altura10 FROM CARBONO_CubiertaVegetal WHERE "
                + "CubiertaVegetalID= " + cubiertaVegetalID;
        Connection conn = LocalConnection.getConnection();
        CECubiertaVegetal ceCubierta = new CECubiertaVegetal();

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                ceCubierta.setTransecto(rs.getInt("Transecto"));
                ceCubierta.setComponente(rs.getInt("ComponenteID"));
                ceCubierta.setAltura5(rs.getFloat("Altura5"));
                ceCubierta.setAltura10(rs.getFloat("Altura10"));
            }
            return ceCubierta;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al obtener un registro de cubierta vegetal de carbono ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane
                        .showMessageDialog(null,
                                "Error! al cerrar la base de datos en datos de registro de cubierta vegetal de carbono",
                                "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void insertMaterialLenioso100(CEMaterialLenioso100 material100) {
        query = "INSERT INTO CARBONO_MaterialLenioso100(SitioID, Transecto, Pendiente, UnaHora, DiezHoras, CienHoras)VALUES(" + material100.getSitioID()
                + "," + material100.getTransecto() + ", " + material100.getPendiente() + ", " + material100.getUnaHora() + ", " + material100.getDiezHoras()
                + ", " + material100.getCienHoras() + ")";

        Connection conn = LocalConnection.getConnection();

        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo guardar la informacion en material leñoso 100 ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos al insertar datos de material leñoso 100 ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void insertMaterialLenioso1000(CEMaterialLenioso1000 material1000) {
        query = "INSERT INTO CARBONO_MaterialLenioso1000(SitioID, Transecto, Diametro, Grado)VALUES(" + material1000.getSitioID()
                + ", " + material1000.getTransecto() + ", " + material1000.getDiametro() + ", " + material1000.getGrado() + ")";

        Connection conn = LocalConnection.getConnection();

        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo guardar la informacion de material leñoso 1000 ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos al insertar datos de material leñosos 1000 ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void insertCubiertaVegetal(CECubiertaVegetal cubierta) {
        query = "INSERT INTO CARBONO_CubiertaVegetal(SitioID, Transecto, ComponenteID, Altura5, Altura10)VALUES(" + cubierta.getSitioID()
                + ", " + cubierta.getTransecto() + ", " + cubierta.getComponente() + ", " + cubierta.getAltura5() + ", " + cubierta.getAltura10() + ")";
        Connection conn = LocalConnection.getConnection();

        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo guardar la informacion de cubierta vegetal de carbono ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos al insertar datos de cubierta vegetal de carbono ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void updateMaterialLenioso100(CEMaterialLenioso100 material100) {
        query = "UPDATE CARBONO_MaterialLenioso100 SET Transecto= " + material100.getTransecto() + ", Pendiente= " + material100.getPendiente() + ", UnaHora= "
                + material100.getUnaHora() + ", DiezHoras= " + material100.getCienHoras() + ", CienHoras= " + material100.getCienHoras() + " WHERE "
                + "MaterialLenioso100ID= " + material100.getMaterialLenioso100ID();

        Connection conn = LocalConnection.getConnection();

        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo modificar la información de material leñoso 100 ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la modificación de material leñoso 100", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void updateMaterialLenioso1000(CEMaterialLenioso1000 material1000) {
        query = "UPDATE CARBONO_MaterialLenioso1000 SET Transecto= " + material1000.getTransecto() + ", Diametro= " + material1000.getDiametro() + ", Grado= "
                + material1000.getGrado() + " WHERE MaterialLenioso1000ID=  " + material1000.getMaterialLenioso1000ID();

        Connection conn = LocalConnection.getConnection();

        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo modificar la información de material leñoso 1000 ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la modificación de material leñoso 1000 ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void updateCubiertaVegetal(CECubiertaVegetal cubierta) {
        query = "UPDATE CARBONO_CubiertaVegetal SET Transecto= " + cubierta.getTransecto() + ", ComponenteID= " + cubierta.getComponente() + ", Altura5= "
                + cubierta.getAltura5() + ", Altura10= " + cubierta.getAltura10() + " WHERE CubiertaVegetalID=  " + cubierta.getCubiertaVegetalID();

        Connection conn = LocalConnection.getConnection();

        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo modificar la información de cubierta vegetal de carbono ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la modificación de cubierta vegetañ de carbono", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void deleteMaterialLenioso100(CEMaterialLenioso100 material100) {
        query = "DELETE FROM CARBONO_MaterialLenioso100 WHERE MaterialLenioso100ID= " + material100.getMaterialLenioso100ID();
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo eliminar la información de material leñoso 100 ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos  al eliminar el material leñoso 100",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void deleteMaterialLenioso100Sitio(int sitioID){
        query = "DELETE FROM CARBONO_MaterialLenioso100 WHERE SitioID= " + sitioID;
         Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo eliminar la información de material leñoso 100 por sitio", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos  al eliminar el material leñoso 100 por sitio",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void deleteMaterialLenioso1000(CEMaterialLenioso1000 material1000) {
        query = "DELETE FROM CARBONO_MaterialLenioso1000 WHERE MaterialLenioso1000ID= " + material1000.getMaterialLenioso1000ID();
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo eliminar la información de material leñoso 1000 ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos  al eliminar el material leñoso 1000 ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void deleteMaterialLenioso1000Sitio(int sitioID){
        query = "DELETE FROM CARBONO_MaterialLenioso1000 WHERE SitioID= " + sitioID;
         Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo eliminar la información de material leñoso 1000 por sitio", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos  al eliminar el material leñoso 1000 por sitio",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void deleteCubiertaVegetal(CECubiertaVegetal ceCubierta) {
        query = "DELETE FROM CARBONO_CubiertaVegetal WHERE CubiertaVegetalID= " + ceCubierta.getCubiertaVegetalID();
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo eliminar la información de cubierta vegetal de carbono ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos  al eliminar la cubierta vegetal de carbono",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void deleteCubiertaVegetalSitio(int sitioID) {
        query = "DELETE FROM CARBONO_CubiertaVegetal WHERE SitioID= " + sitioID;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo eliminar la información de cubierta vegetal de carbono por sitio", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos  al eliminar la cubierta vegetal de carbono por sitio",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public DefaultTableModel getTablaMaterialLenioso100(int sitioID) {
        query = "SELECT MaterialLenioso100ID, SitioID, Transecto, Pendiente, UnaHora, DiezHoras, "
                + "CienHoras FROM CARBONO_MaterialLenioso100 WHERE SitioID= " + sitioID;

        String[] encabezados = {"MaterialLenioso100ID", "SitioID", "Transecto", "Pendiente", "Una hora", "Diez horas", "Cien horas"};
        DefaultTableModel materialLenioso100Model = new DefaultTableModel(null, encabezados);
        Object[] datosMaterialLenioso100 = new Object[7];
        Connection conn = LocalConnection.getConnection();

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                datosMaterialLenioso100[0] = rs.getInt("MaterialLenioso100ID");
                datosMaterialLenioso100[1] = rs.getInt("SitioID");
                datosMaterialLenioso100[2] = rs.getInt("Transecto");
                datosMaterialLenioso100[3] = rs.getInt("Pendiente");
                datosMaterialLenioso100[4] = rs.getInt("UnaHora");
                datosMaterialLenioso100[5] = rs.getInt("DiezHoras");
                datosMaterialLenioso100[6] = rs.getInt("CienHoras");

                materialLenioso100Model.addRow(datosMaterialLenioso100);
            }
            st.close();
            rs.close();
            return materialLenioso100Model;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al obtener los datos de la vista de material leñoso 100 ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en para la vista de material leñoso 100", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public DefaultTableModel getTablaMaterialLenioso1000(int sitioID) {
        query = "SELECT MaterialLenioso1000ID, SitioID, Transecto, Diametro, Grado FROM CARBONO_MaterialLenioso1000 "
                + "WHERE SitioID= " + sitioID;

        String[] encabezados = {"MaterialLenioso1000ID", "SitioID", "Transecto", "Diametro", "Grado"};

        DefaultTableModel materialLenioso1000Model = new DefaultTableModel(null, encabezados);
        Object[] datosdMaterialLenioso1000 = new Object[5];
        Connection conn = LocalConnection.getConnection();

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                datosdMaterialLenioso1000[0] = rs.getInt("MaterialLenioso1000ID");
                datosdMaterialLenioso1000[1] = rs.getInt("SitioID");
                datosdMaterialLenioso1000[2] = rs.getInt("Transecto");
                datosdMaterialLenioso1000[3] = rs.getFloat("Diametro");
                datosdMaterialLenioso1000[4] = rs.getInt("Grado");

                materialLenioso1000Model.addRow(datosdMaterialLenioso1000);
            }
            st.close();
            rs.close();
            return materialLenioso1000Model;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al obtener los datos de la vista de material lenioso 1000 ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en para la vista de material lenioso 1000", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public DefaultTableModel getTablaCubiertaVegetal(int sitioID) {
        query = "SELECT CubiertaVegetalID, SitioID, Transecto, Componente, Altura5, Altura10 FROM VW_CarbonoCubiertaVegetal WHERE SitioID= " + sitioID;
        String[] encabezados = {"CubiertaVegetalID", "SitioID", "Transecto", "Componente", "Altura5", "Altura10"};
        DefaultTableModel cubiertaVegetalModel = new DefaultTableModel(null, encabezados);
        Object[] datosCubiertaVegetal = new Object[6];
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                datosCubiertaVegetal[0] = rs.getInt("CubiertaVegetalID");
                datosCubiertaVegetal[1] = rs.getInt("SitioID");
                datosCubiertaVegetal[2] = rs.getInt("Transecto");
                datosCubiertaVegetal[3] = rs.getString("Componente");
                datosCubiertaVegetal[4] = rs.getFloat("Altura5");
                datosCubiertaVegetal[5] = rs.getFloat("Altura10");
                cubiertaVegetalModel.addRow(datosCubiertaVegetal);
            }
            st.close();
            rs.close();
            return cubiertaVegetalModel;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al obtener los datos de la vista de carbono cubierta vegetal ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en para la vista de carbono cubierta vegetal", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public boolean validarCubiertaVegetal(int sitioID, int transecto, int componente) {
        query = "SELECT SitioID, Transecto, ComponenteID FROM CARBONO_CubiertaVegetal WHERE SitioID= " + sitioID + " AND Transecto= " + transecto + " AND ComponenteID= " + componente;
        boolean vacio = false;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                vacio = true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al obtener los datos de la tabla de cubierta vegetal de carbono ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en datos de validar tabla de sotobosque ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        return vacio;
    }
    
    public boolean hayMaterialLenioso100(int sitioID) {
        query = "SELECT * FROM CARBONO_MaterialLenioso100 WHERE SitioID= " + sitioID;
        boolean vacio = true;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                vacio = false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al validar la presencia de datos en la tabla de material lenioso 100", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en datos de validar material lenioso 100 ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        return vacio;
    }
     
    public boolean hayMaterialLenioso1000(int sitioID) {
        query = "SELECT * FROM CARBONO_MaterialLenioso1000 WHERE SitioID= " + sitioID;
        boolean vacio = true;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                vacio = false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al validar la presencia de datos en la tabla de material lenioso 1000", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en datos de validar material lenioso 1000", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        return vacio;
    }
    
    public boolean hayCubiertaVegetal(int sitioID) {
        query = "SELECT * FROM CARBONO_CubiertaVegetal WHERE SitioID= " + sitioID;
        boolean vacio = true;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                vacio = false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al validar la presencia de datos en la tabla de material lenioso 1000", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en datos de validar material lenioso 1000", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        return vacio;
    }
    
}
