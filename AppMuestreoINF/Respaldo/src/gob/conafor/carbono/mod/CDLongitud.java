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

public class CDLongitud {

    private String query;

    public CELongitudComponente getRegistroSegementoComponente(int longitudID) {
        query = "SELECT LongitudComponenteID, Transecto, ComponenteID, FamiliaID, GeneroID, EspecieID, InfraespecieID, NombreComun, "
                + "Segmento1, Segmento2, Segmento3, Segmento4, Segmento5, Segmento6, Segmento7, "
                + "Segmento8, Segmento9, Segmento10, Total, ClaveColecta FROM CARBONO_LongitudComponente WHERE LongitudComponenteID= " + longitudID;
        CELongitudComponente ceSegemento = new CELongitudComponente();
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                ceSegemento.setTransecto(rs.getInt("Transecto"));
                ceSegemento.setComponenteID(rs.getInt("ComponenteID"));
                ceSegemento.setFamiliaID(rs.getInt("FamiliaID"));
                ceSegemento.setGeneroID(rs.getInt("GeneroID"));
                ceSegemento.setEspecieID(rs.getInt("EspecieID"));
                ceSegemento.setInfraespecieID(rs.getInt("InfraespecieID"));
                ceSegemento.setNombreComun(rs.getString("NombreComun"));
                ceSegemento.setSegmento1(rs.getInt("Segmento1"));
                ceSegemento.setSegmento2(rs.getInt("Segmento2"));
                ceSegemento.setSegmento3(rs.getInt("Segmento3"));
                ceSegemento.setSegmento4(rs.getInt("Segmento4"));
                ceSegemento.setSegmento5(rs.getInt("Segmento5"));
                ceSegemento.setSegmento6(rs.getInt("Segmento6"));
                ceSegemento.setSegmento7(rs.getInt("Segmento7"));
                ceSegemento.setSegmento8(rs.getInt("Segmento8"));
                ceSegemento.setSegmento9(rs.getInt("Segmento9"));
                ceSegemento.setSegmento10(rs.getInt("Segmento10"));
                ceSegemento.setTotal(rs.getInt("Total"));
                ceSegemento.setColectaBotanica(rs.getString("ClaveColecta"));
            }
            return ceSegemento;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al obtener un registro de longitud por componente ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane
                        .showMessageDialog(null,
                                "Error! al cerrar la base de datos en datos de registro de longitud por componente",
                                "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public CECoberturaDosel getRegistroCoberturaDosel(int coberturaID) {
        query = "SELECT CoberturaDoselID, SitioID, Transecto, Punto1, Punto2, Punto3, Punto4, Punto5, Punto6, Punto7, Punto8, "
                + "Punto9, Punto10 FROM CARBONO_CoberturaDosel WHERE CoberturaDoselID= " + coberturaID;
        CECoberturaDosel ceCobertura = new CECoberturaDosel();
        Connection conn = LocalConnection.getConnection();

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                ceCobertura.setTransecto(rs.getInt("Transecto"));
                ceCobertura.setPunto1(rs.getInt("Punto1"));
                ceCobertura.setPunto2(rs.getInt("Punto2"));
                ceCobertura.setPunto3(rs.getInt("Punto3"));
                ceCobertura.setPunto4(rs.getInt("Punto4"));
                ceCobertura.setPunto5(rs.getInt("Punto5"));
                ceCobertura.setPunto6(rs.getInt("Punto6"));
                ceCobertura.setPunto7(rs.getInt("Punto7"));
                ceCobertura.setPunto8(rs.getInt("Punto8"));
                ceCobertura.setPunto9(rs.getInt("Punto9"));
                ceCobertura.setPunto10(rs.getInt("Punto10"));
            }
            return ceCobertura;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al obtener un registro de cobertura dosel ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane
                        .showMessageDialog(null,
                                "Error! al cerrar la base de datos en datos de registro de longitud por componente",
                                "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public List<CatECarbonoComponente> getComponentesSegemento() {
        List<CatECarbonoComponente> listComponentes = new ArrayList<>();
        query = "SELECT ComponenteID, Componente FROM CAT_TipoComponente";
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
                    "Error! al obtener los datos de tipo de componentes de longitud de componentes en carbono ",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {

                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en longitud de componentes en carbono "
                        + e.getClass().getName() + " : " + e.getMessage(), "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public List<Integer> getTransectoComponentes(){
         List<Integer> listTransecto = new ArrayList<>();
        for (int i = 1; i < 3; i++) {
            listTransecto.add(i);
        }
        listTransecto.add(0, null);
        return listTransecto;
    }
    
    public List<Integer> getTransectoCoberturaDosel(int sitioID) {
        List<Integer> listTransecto = new ArrayList<>();
        for (int i = 1; i < 3; i++) {
            listTransecto.add(i);
        }
        listTransecto.add(0, null);
        query = "SELECT SitioID, Transecto FROM CARBONO_CoberturaDosel WHERE SitioID= " + sitioID;
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
                    "Error! al obtener los numeros de transecto de cobertura dosel",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en los numeros de transecto en cobertura dosel", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        return listTransecto;
    }

    public DefaultTableModel getTablaComponentes(int sitioID) {
        query = "SELECT LongitudComponenteID, SitioID, Consecutivo, Transecto, Componente, Familia, Genero, Especie, Infraespecie, "
                + "NombreComun, Segmento1, Segmento2, Segmento3, Segmento4, Segmento5, "
                + "Segmento6, Segmento7, Segmento8, Segmento9, Segmento10, Total, ClaveColecta FROM VW_LongitudComponente "
                + "WHERE SitioID= " + sitioID;
        String[] encabezados = {"LongitudComponentesID", "SitioID", "Consecutivo", "Transecto", "Componente", "Familia", "Genero", "Especie", "Infraespecie", "Nombre comun",
            "Segmento 1", "Segmento 2", "Segmento 3", "Segmento 4", "Segmento 5", "Segmento 6", "Segmento 7",
            "Segmento 8 ", "Segmento 9", "Segmento 10", "Total", "Clave de colecta"};
        DefaultTableModel componentesModel = new DefaultTableModel(null, encabezados);
        Object[] datosComponentes = new Object[22];
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                datosComponentes[0] = rs.getInt("LongitudComponenteID");
                datosComponentes[1] = rs.getInt("SitioID");
                datosComponentes[2] = rs.getInt("Consecutivo");
                datosComponentes[3] = rs.getInt("Transecto");
                datosComponentes[4] = rs.getString("Componente");
                datosComponentes[5] = rs.getString("Familia");
                datosComponentes[6] = rs.getString("Genero");
                datosComponentes[7] = rs.getString("Especie");
                datosComponentes[8] = rs.getString("Infraespecie");
                datosComponentes[9] = rs.getString("NombreComun");
                datosComponentes[10] = rs.getString("Segmento1");
                datosComponentes[11] = rs.getString("Segmento2");
                datosComponentes[12] = rs.getString("Segmento3");
                datosComponentes[13] = rs.getString("Segmento4");
                datosComponentes[14] = rs.getString("Segmento5");
                datosComponentes[15] = rs.getString("Segmento6");
                datosComponentes[16] = rs.getString("Segmento7");
                datosComponentes[17] = rs.getString("Segmento8");
                datosComponentes[18] = rs.getString("Segmento9");
                datosComponentes[19] = rs.getString("Segmento10");
                datosComponentes[20] = rs.getString("Total");
                datosComponentes[21] = rs.getString("ClaveColecta");
                componentesModel.addRow(datosComponentes);
            }
            st.close();
            rs.close();
            return componentesModel;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al obtener los datos de la vista de longitud de componentes de carbono", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en para la vista de longitud de componentes de carbono", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public DefaultTableModel getTablaCoberturaDosel(int sitioID) {
        query = "SELECT CoberturaDoselID, SitioID, Transecto, Punto1, Punto2, Punto3, Punto4, Punto5, Punto6, Punto7, Punto8, Punto9, Punto10 "
                + "FROM CARBONO_CoberturaDosel WHERE SitioID= " + sitioID + " ORDER BY Transecto ASC";

        String[] encabezados = {"CoberturaDoselID", "SitioID", "Transecto", "Punto 1", "Punto 2", "Punto 3", "Punto 4", "Punto 5", "Punto 6",
            "Punto 7", "Punto 8", "Punto 9", "Punto 10"};

        DefaultTableModel doselModel = new DefaultTableModel(null, encabezados);
        Object[] datosDosel = new Object[13];
        Connection conn = LocalConnection.getConnection();

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                datosDosel[0] = rs.getInt("CoberturaDoselID");
                datosDosel[1] = rs.getInt("SitioID");
                datosDosel[2] = rs.getInt("Transecto");
                datosDosel[3] = rs.getInt("Punto1");
                datosDosel[4] = rs.getInt("Punto2");
                datosDosel[5] = rs.getInt("Punto3");
                datosDosel[6] = rs.getInt("Punto4");
                datosDosel[7] = rs.getInt("Punto5");
                datosDosel[8] = rs.getInt("Punto6");
                datosDosel[9] = rs.getInt("Punto7");
                datosDosel[10] = rs.getInt("Punto8");
                datosDosel[11] = rs.getInt("Punto9");
                datosDosel[12] = rs.getInt("Punto10");
                doselModel.addRow(datosDosel);
            }
            st.close();
            rs.close();
            return doselModel;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al obtener los datos de la vista de cobertura dosel carbono", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos para la vista de cobertura dosel de carbono", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void insertDatosSegementoComponente(CELongitudComponente ceSegmento) {
        query = "INSERT INTO CARBONO_LongitudComponente(SitioID, Transecto, ComponenteID, FamiliaID, GeneroID, EspecieID, InfraespecieID, "
                + "NombreComun, Segmento1, Segmento2, Segmento3, Segmento4, Segmento5, Segmento6, Segmento7, Segmento8, Segmento9, "
                + "Segmento10, Total)VALUES(" + ceSegmento.getSitioID() + " ," + ceSegmento.getTransecto() + ", " + ceSegmento.getComponenteID() + ", "
                + ceSegmento.getFamiliaID() + ", " + ceSegmento.getGeneroID() + ", " + ceSegmento.getEspecieID() + ", " + ceSegmento.getInfraespecieID()
                + ", '" + ceSegmento.getNombreComun() + "', " + ceSegmento.getSegmento1() + ", " + ceSegmento.getSegmento2()
                + ", " + ceSegmento.getSegmento3() + ", " + ceSegmento.getSegmento4() + ", " + ceSegmento.getSegmento5() + ", " + ceSegmento.getSegmento6()
                + ", " + ceSegmento.getSegmento7() + ", " + ceSegmento.getSegmento8() + ", " + ceSegmento.getSegmento9() + ", " + ceSegmento.getSegmento10()
                + ", " + ceSegmento.getTotal() + ")";
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo guardar la informacion en longitud de componentes ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos al insertar datos de longitud de componentes ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void insertDatosCoberturaDosel(CECoberturaDosel cobertura) {
        query = "INSERT INTO CARBONO_CoberturaDosel (SitioID, Transecto, Punto1, Punto2, Punto3, Punto4, Punto5, Punto6, "
                + "Punto7, Punto8, Punto9, Punto10)VALUES(" + cobertura.getSitioID() + ", " + cobertura.getTransecto() + ","
                + cobertura.getPunto1() + ", " + cobertura.getPunto2() + ", " + cobertura.getPunto3() + ", " + cobertura.getPunto4()
                + ", " + cobertura.getPunto5() + ", " + cobertura.getPunto6() + ", " + cobertura.getPunto7() + ", " + cobertura.getPunto8()
                + ", " + cobertura.getPunto9() + ", " + cobertura.getPunto10() + ")";
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo guardar la informacion en cobertura dosel ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos al insertar datos de cobertura dosel  ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void updateDatosLongitudComponente(CELongitudComponente ceSegmento) {
        query = "UPDATE CARBONO_LongitudComponente SET Transecto= " + ceSegmento.getTransecto() + ", ComponenteID= " + ceSegmento.getComponenteID()
                + ", FamiliaID= " + ceSegmento.getFamiliaID() + ", GeneroID= " + ceSegmento.getGeneroID()
                + ", EspecieID= " + ceSegmento.getEspecieID() + ", InfraespecieID= " + ceSegmento.getInfraespecieID() + ", NombreComun= '" + ceSegmento.getNombreComun()
                + "', Segmento1= " + ceSegmento.getSegmento1() + ", Segmento2= " + ceSegmento.getSegmento2() + ", Segmento3= "
                + ceSegmento.getSegmento3() + ", Segmento4= " + ceSegmento.getSegmento4() + ", Segmento5= " + ceSegmento.getSegmento5() + ", Segmento6= " + ceSegmento.getSegmento6()
                + ", Segmento7= " + ceSegmento.getSegmento7() + ", Segmento8= " + ceSegmento.getSegmento8() + ", Segmento9= " + ceSegmento.getSegmento9() + ", Segmento10= " + ceSegmento.getSegmento10()
                + ", Total= " + ceSegmento.getTotal() + " WHERE LongitudComponenteID= " + ceSegmento.getLongitudComponenteID();
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo modificar la información de longitud de componente ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la modificación de longitud de componente", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void updateDatosCoberturaDosel(CECoberturaDosel cobertura) {
        query = "UPDATE CARBONO_CoberturaDosel SET Punto1= " + cobertura.getPunto1() + ", Punto2= "
                + cobertura.getPunto2() + ", Punto3= " + cobertura.getPunto3() + ", Punto4= " + cobertura.getPunto4() + ", Punto5= " + cobertura.getPunto5()
                + ", Punto6= " + cobertura.getPunto6() + ", Punto7= " + cobertura.getPunto7() + ", Punto8= " + cobertura.getPunto8() + ", Punto9= "
                + cobertura.getPunto9() + ", Punto10= " + cobertura.getPunto10() + " WHERE CoberturaDoselID= " + cobertura.getCoberturaDoselID();
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo modificar la información de cobertura dosel ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la modificación de cobertura dosel ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void deleteDatosSegementoComponente(CELongitudComponente ceSegemento) {
        query = "DELETE FROM CARBONO_LongitudComponente WHERE LongitudComponenteID= " + ceSegemento.getComponenteID();
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo eliminar la información de longitud de componente ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos  al eliminar la información de longitud de componente",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void deleteLongitudComponentesSitio(int sitioID){
        query = "DELETE FROM CARBONO_LongitudComponente WHERE SitioID= " + sitioID;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo eliminar la información de longitud de componente por sitio", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos  al eliminar la información de longitud de componente por sitio",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void deleteDatosCoberturaDosel(CECoberturaDosel ceCobertura) {
        query = "DELETE FROM CARBONO_CoberturaDosel WHERE CoberturaDoselID= " + ceCobertura.getCoberturaDoselID();
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo eliminar la información de cobertura dosel ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos  al eliminar la información de cobertura dosel",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void deleteCoberturaDoselSitio(int sitioID){
        query = "DELETE FROM CARBONO_CoberturaDosel WHERE SitioID= " + sitioID;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo eliminar la información de cobertura dosel por sitio", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos  al eliminar la información de cobertura dosel por sitio",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public boolean validarComponente(int sitioID, int transecto, int componente) {
        query = "SELECT SitioID, Transecto, ComponenteID FROM CARBONO_LongitudComponente WHERE SitioID= " + sitioID + " AND Transecto= " + transecto + " AND ComponenteID= " + componente;
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
    
    public boolean validarTablaComponente(int sitioID) {
        query = "SELECT * FROM CARBONO_LongitudComponente WHERE SitioID= " + sitioID;
        boolean vacio = true;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                vacio = false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al validar la presencia de datos en la tabla de longitud interceptada por componente", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en datos de validar longitud interceptada por componente", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        return vacio;
    }
    
    public boolean validarCoberturaDosel(int sitioID) {
        query = "SELECT * FROM CARBONO_CoberturaDosel WHERE SitioID= " + sitioID;
        boolean vacio = true;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                vacio = false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al validar la presencia de datos en la tabla de cobertura dosel", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en datos de cobertura dosel", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        return vacio;
    }

    public void enumerarConsecutivo(int sitioID) {
        List<Integer> listAboladoID = getLongitudComponenteID(sitioID);
        Connection conn = LocalConnection.getConnection();
        if (listAboladoID != null) {
            int size = listAboladoID.size();
            int consecutivo = 1;
            try {
                for (int i = 0; i < size; i++) {
                    Statement st = conn.createStatement();
                    query = "UPDATE CARBONO_LongitudComponente SET Consecutivo= " + consecutivo + "  WHERE LongitudComponenteID= " + listAboladoID.get(i);
                    st.executeUpdate(query);
                    conn.commit();
                    consecutivo++;
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,
                        "Error! al enumerar el consecutivo de longitud por componente ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al enumerar el consecutivo de longitud por componente ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private List<Integer> getLongitudComponenteID(int sitioID) {
        List<Integer> listArboladoID = new ArrayList<>();
        query = "SELECT LongitudComponenteID, SitioID FROM CARBONO_LongitudComponente WHERE SitioID= " + sitioID + " ORDER BY LongitudComponenteID ASC";
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                listArboladoID.add(rs.getInt("LongitudComponenteID"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener los datos de longitud componente id ",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en lista de longitud de componentes id", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        return listArboladoID;
    }
}
