package gob.conafor.taxonomia.mod;

import gob.conafor.conn.dat.LocalConnection;
import gob.conafor.sitio.mod.CESitio;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class CDArbolado {

    private String query;
    private int arboaldoID;

    public List<CEArbolado> getArbolado(CESitio sitio) {

        List<CEArbolado> listArbolado = new ArrayList<>();

        query = "SELECT SitioID, Consecutivo, NoIndividuo, NoRama, Azimut, Distancia, FamiliaID, GeneroID, EspecieID, "
                + "InfraespecieID, NombreComun, EsColecta, EsSubmuestra, FormaVidaID, FormaFusteID, CondicionID, MuertoPieID, GradoPutrefaccionID, TipoToconID, "
                + "DiametroNormal, AlturaTotal, AnguloInclinacion, AlturaFusteLimpio, AlturaComercial, DiametroCopaNS, "
                + "DiametroCopaEO  FROM TAXONOMIA_Arbolado WHERE SitioID= " + sitio.getSitioID();
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                CEArbolado arbolado = new CEArbolado();
                arbolado.setSitioID(rs.getInt("SitioID"));
                arbolado.setConsecutivo(rs.getInt("Consecutivo"));
                arbolado.setNumeroIndividuo(rs.getInt("NoIndividuo"));
                arbolado.setNumeroRama(rs.getInt("NoRama"));
                arbolado.setAzimut(rs.getInt("Azimut"));
                arbolado.setDistancia(rs.getFloat("Distancia"));
                arbolado.setFamiliaID(rs.getInt("FamiliaID"));
                arbolado.setGeneroID(rs.getInt("GeneroID"));
                arbolado.setEspecieID(rs.getInt("EspecieID"));
                arbolado.setInfraespecieID(rs.getInt("InfraespecieID"));
                arbolado.setNombreComun(rs.getString("NombreComun"));
                arbolado.setEsColecta(rs.getInt("EsColecta"));
                arbolado.setEsSubmuestra(rs.getInt("EsSubmuestra"));
                arbolado.setFormaVidaID(rs.getInt("FormaVidaID"));
                arbolado.setFormaFusteID(rs.getInt("FormaFusteID"));
                arbolado.setCondicionID(rs.getInt("CondicionID"));
                arbolado.setMuertoPieID(rs.getInt("MuertoPieID"));
                arbolado.setGradoPutrefaccionID(rs.getInt("GradoPutrefaccionID"));
                arbolado.setTipoToconID(rs.getInt("TipoToconID"));
                arbolado.setDiametroNormal(rs.getFloat("DiametroNormal"));
                arbolado.setAlturaTotal(rs.getFloat("AlturaTotal"));
                arbolado.setAnguloInclinacion(rs.getInt("AnguloInclinacion"));
                arbolado.setAlturaFusteLimpio(rs.getFloat("AlturaFusteLimpio"));
                arbolado.setAlturaComercial(rs.getFloat("AlturaComercial"));
                arbolado.setDiametroCopaNS(rs.getFloat("DiametroCopaNS"));
                arbolado.setDiametroCopaEO(rs.getFloat("DiametroCopaEO"));

                listArbolado.add(arbolado);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener los datos del arbolado ",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en datos del arbolado", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        return listArbolado;
    }

    public boolean validarTablaArbolado(int sitioID) {
        query = "SELECT * FROM TAXONOMIA_Arbolado";
        boolean vacio = true;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                vacio = false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al obtener los datos de la tabla de repoblado ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en datos de validar tabla de repoblado ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        return vacio;
    }

    public DefaultTableModel getTablaArbolado(int sitioID) {
        query = "SELECT ArboladoID, SitioID, Consecutivo, Individuo, Rama, Azimut, Distancia, Familia, Genero, "
                + "Especie, Infraespecie, NombreComun, EsSubmuestra, FormaVida, Condicion, FormaFuste, TipoMuertoPie, GradoPutrefaccion, TipoTocon, "
                + "DiametroNormal, AlturaTotal, AnguloInclinacion, AlturaFusteLimpio, AlturaComercial, DiametroCopaNS, DiametroCopaEO, "
                + "Agente1, Severidad1, Agente2, Severidad2, Vigor, NivelVigor, ClaveColecta "
                + "FROM VW_Arbolado_A WHERE SitioID= " + sitioID;
        String[] encabezados = {"ArboladoID", "SitioID", "Consecutivo", "Individuo", "Rama", "Azimut", "Distancia", "Familia", "Genero",
            "Especie", "Infraespecie", "Nombre común", "Submuestra", "Forma vida", "Condición", "Forma Fuste", " Tipo muerto en píe", "Grado putrefacción", "Tipo tocón",
            "Diámetro normal", "Altura total", "Ángulo inclinación", "Altura fuste limpio", "Altura comercial", "Diámetro copa NS", "Diámetro copa EO",
            "Agente 1", "Severidad 1", "Agente 2", "Severidad 2", "Vigor", "Nivel Vigor" , "Clave de colecta"};
        DefaultTableModel arboladoModel = new DefaultTableModel(null, encabezados);
        Object[] datosArbolado = new Object[33];
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                datosArbolado[0] = rs.getInt("ArboladoID");
                datosArbolado[1] = rs.getInt("SitioID");
                datosArbolado[2] = rs.getString("Consecutivo");
                datosArbolado[3] = rs.getString("Individuo");
                datosArbolado[4] = rs.getString("Rama");
                datosArbolado[5] = rs.getString("Azimut");
                datosArbolado[6] = rs.getString("Distancia");
                datosArbolado[7] = rs.getString("Familia");
                datosArbolado[8] = rs.getString("Genero");
                datosArbolado[9] = rs.getString("Especie");
                datosArbolado[10] = rs.getString("Infraespecie");
                datosArbolado[11] = rs.getString("NombreComun");
                datosArbolado[12] = rs.getString("EsSubmuestra");
                datosArbolado[13] = rs.getString("FormaVida");
                datosArbolado[14] = rs.getString("Condicion");
                datosArbolado[15] = rs.getString("FormaFuste");
                datosArbolado[16] = rs.getString("TipoMuertoPie");
                datosArbolado[17] = rs.getString("GradoPutrefaccion");
                datosArbolado[18] = rs.getString("TipoTocon");
                datosArbolado[19] = rs.getString("DiametroNormal");
                datosArbolado[20] = rs.getString("AlturaTotal");
                datosArbolado[21] = rs.getString("AnguloInclinacion");
                datosArbolado[22] = rs.getString("AlturaFusteLimpio");
                datosArbolado[23] = rs.getString("AlturaComercial");
                datosArbolado[24] = rs.getString("DiametroCopaNS");
                datosArbolado[25] = rs.getString("DiametroCopaEO");
                datosArbolado[26] = rs.getString("Agente1");
                datosArbolado[27] = rs.getString("Severidad1");
                datosArbolado[28] = rs.getString("Agente2");
                datosArbolado[29] = rs.getString("Severidad2");
                datosArbolado[30] = rs.getString("Vigor");
                datosArbolado[31] = rs.getString("NivelVigor");
                datosArbolado[32] = rs.getString("ClaveColecta");
                arboladoModel.addRow(datosArbolado);
            }
            st.close();
            rs.close();
            return arboladoModel;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al obtener los datos de la vista de arbolado del modulo A ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en para la vista de arbolado del modulo A ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public DefaultTableModel getTablaArboladoD(int sitioID) {
        query = "SELECT ArboladoID, SitioID, Consecutivo, Individuo, Rama, Azimut, Distancia, Familia, Genero, "
                + "Especie, Infraespecie, NombreComun, EsSubmuestra, FormaVida, Condicion, FormaFuste, TipoMuertoPie, GradoPutrefaccion, TipoTocon, "
                + "DiametroNormal, AlturaTotal, AnguloInclinacion, AlturaFusteLimpio, AlturaComercial, DiametroCopaNS, DiametroCopaEO, ProporcionCopaViva, ExposicionLuzCopa, "
                + "PosicionCopa, DencidadCopa, MuerteRegresiva, TransparenciaFollaje, Agente1, Severidad1, Agente2, Severidad2, ClaveColecta "
                + "FROM VW_Arbolado_D WHERE SitioID= " + sitioID;
        String[] encabezados = {"ArboladoID", "SitioID", "Consecutivo", "Individuo", "Rama", "Azimut", "Distancia", "Familia", "Genero",
            "Especie", "Infraespecie", "Nombre comun", "Submuestra", "Forma vida", "Condicion", "Forma Fuste", " Tipo muerto en pie", "Grado putrefaccion", "Tipo tocon",
            "Diametro normal", "Altura total", "Angulo inclinacion", "Altura fuste limpio", "Altura comercial", "Diametro copa NS", "Diametro copa EO", "Proporcion Copa",
            "Exposicion luz", "Posicion copa", "Densidad copa", "Muerte regresiva", "Transparencia follage", "Agente daño 1", "Severidad 1", "Agente daño 2", "Severidad 2", "Clave de colecta"};
        DefaultTableModel arboladoModel = new DefaultTableModel(null, encabezados);
        Object[] datosArbolado = new Object[37];
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                datosArbolado[0] = rs.getInt("ArboladoID");
                datosArbolado[1] = rs.getInt("SitioID");
                datosArbolado[2] = rs.getString("Consecutivo");
                datosArbolado[3] = rs.getString("Individuo");
                datosArbolado[4] = rs.getString("Rama");
                datosArbolado[5] = rs.getString("Azimut");
                datosArbolado[6] = rs.getString("Distancia");
                datosArbolado[7] = rs.getString("Familia");
                datosArbolado[8] = rs.getString("Genero");
                datosArbolado[9] = rs.getString("Especie");
                datosArbolado[10] = rs.getString("Infraespecie");
                datosArbolado[11] = rs.getString("NombreComun");
                datosArbolado[12] = rs.getString("EsSubmuestra");
                datosArbolado[13] = rs.getString("FormaVida");
                datosArbolado[14] = rs.getString("Condicion");
                datosArbolado[15] = rs.getString("FormaFuste");
                datosArbolado[16] = rs.getString("TipoMuertoPie");
                datosArbolado[17] = rs.getString("GradoPutrefaccion");
                datosArbolado[18] = rs.getString("TipoTocon");
                datosArbolado[19] = rs.getString("DiametroNormal");
                datosArbolado[20] = rs.getString("AlturaTotal");
                datosArbolado[21] = rs.getString("AnguloInclinacion");
                datosArbolado[22] = rs.getString("AlturaFusteLimpio");
                datosArbolado[23] = rs.getString("AlturaComercial");
                datosArbolado[24] = rs.getString("DiametroCopaNS");
                datosArbolado[25] = rs.getString("DiametroCopaEO");
                datosArbolado[26] = rs.getString("ProporcionCopaViva");
                datosArbolado[27] = rs.getString("ExposicionLuzCopa");
                datosArbolado[28] = rs.getString("PosicionCopa");
                datosArbolado[29] = rs.getString("DencidadCopa");
                datosArbolado[30] = rs.getString("MuerteRegresiva");
                datosArbolado[31] = rs.getString("TransparenciaFollaje");
                datosArbolado[32] = rs.getString("Agente1");
                datosArbolado[33] = rs.getString("Severidad1");
                datosArbolado[34] = rs.getString("Agente2");
                datosArbolado[35] = rs.getString("Severidad2");
                datosArbolado[36] = rs.getString("ClaveColecta");

                arboladoModel.addRow(datosArbolado);
            }
            st.close();
            rs.close();
            return arboladoModel;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al obtener los datos de la vista de arbolado del modulo D ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en para la vista de arbolado del modulo D ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public DefaultTableModel getTablaArboladoG(int sitioID) {
        query = "SELECT ArboladoID, SitioID, Consecutivo, Individuo, Rama, Azimut, Distancia, Familia, Genero, "
                + "Especie, Infraespecie, NombreComun, EsSubmuestra, FormaVida, Condicion, FormaFuste, TipoMuertoPie, GradoPutrefaccion, TipoTocon, "
                + "DiametroNormal,DiametroBasal, AlturaTotal, AnguloInclinacion, AlturaFusteLimpio, AlturaComercial, DiametroCopaNS, DiametroCopaEO, "
                + "Agente1, Severidad1, Agente2, Severidad2, Vigor, NivelVigor, ClaveColecta "
                + "FROM VW_Arbolado_G WHERE SitioID= " + sitioID;
        String[] encabezados = {"ArboladoID", "SitioID", "Consecutivo", "Individuo", "Rama", "Azimut", "Distancia", "Familia", "Genero",
            "Especie", "Infraespecie", "Nombre comun", "Submuestra", "Forma vida", "Condicion", "Forma Fuste", "Tipo muerto en pie", "Grado putrefaccion", "Tipo tocon",
            "Diametro normal", "Diametro basal", "Altura total", "Angulo inclinacion", "Altura fuste limpio", "Altura comercial", "Diametro copa NS", "Diametro copa EO",
            "Agente 1", "Severidad 1", "Agente 2", "Severidad 2", "Vigor", "Nivel Vigor", "Clave de colecta"};
        DefaultTableModel arboladoModel = new DefaultTableModel(null, encabezados);
        Object[] datosArbolado = new Object[34];
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                datosArbolado[0] = rs.getInt("ArboladoID");
                datosArbolado[1] = rs.getInt("SitioID");
                datosArbolado[2] = rs.getString("Consecutivo");
                datosArbolado[3] = rs.getString("Individuo");
                datosArbolado[4] = rs.getString("Rama");
                datosArbolado[5] = rs.getString("Azimut");
                datosArbolado[6] = rs.getString("Distancia");
                datosArbolado[7] = rs.getString("Familia");
                datosArbolado[8] = rs.getString("Genero");
                datosArbolado[9] = rs.getString("Especie");
                datosArbolado[10] = rs.getString("Infraespecie");
                datosArbolado[11] = rs.getString("NombreComun");
                datosArbolado[12] = rs.getString("EsSubmuestra");
                datosArbolado[13] = rs.getString("FormaVida");
                datosArbolado[14] = rs.getString("Condicion");
                datosArbolado[15] = rs.getString("FormaFuste");
                datosArbolado[16] = rs.getString("TipoMuertoPie");
                datosArbolado[17] = rs.getString("GradoPutrefaccion");
                datosArbolado[18] = rs.getString("TipoTocon");
                datosArbolado[19] = rs.getString("DiametroNormal");
                datosArbolado[20] = rs.getString("DiametroBasal");
                datosArbolado[21] = rs.getString("AlturaTotal");
                datosArbolado[22] = rs.getString("AnguloInclinacion");
                datosArbolado[23] = rs.getString("AlturaFusteLimpio");
                datosArbolado[24] = rs.getString("AlturaComercial");
                datosArbolado[25] = rs.getString("DiametroCopaNS");
                datosArbolado[26] = rs.getString("DiametroCopaEO");
                datosArbolado[27] = rs.getString("Agente1");
                datosArbolado[28] = rs.getString("Severidad1");
                datosArbolado[29] = rs.getString("Agente2");
                datosArbolado[30] = rs.getString("Severidad2");
                datosArbolado[31] = rs.getString("Vigor");
                datosArbolado[32] = rs.getString("NivelVigor");
                datosArbolado[33] = rs.getString("ClaveColecta");
                arboladoModel.addRow(datosArbolado);
            }
            st.close();
            rs.close();
            return arboladoModel;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al obtener los datos de la vista de arbolado del modulo G ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en para la vista de arbolado del modulo G ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void insertArboladoA(CEArbolado arbolado) {
        query = "INSERT INTO TAXONOMIA_Arbolado(SitioID, Consecutivo, NoIndividuo, NoRama, Azimut, Distancia, FamiliaID, "
                + "GeneroID, EspecieID, InfraespecieID, NombreComun, EsSubmuestra, FormaVidaID, FormaFusteID, CondicionID, MuertoPieID, GradoPutrefaccionID, TipoToconID, "
                + "DiametroNormal, DiametroBasal, AlturaTotal, AnguloInclinacion, AlturaFusteLimpio, AlturaComercial, "
                + "DiametroCopaNS, DiametroCopaEO, ProporcionCopaVivaID, ExposicionCopaID, PosicionCopaID, DensidadCopaID, MuerteRegresivaID, TransparenciaFollajeID, VigorID, NivelVigorID)VALUES(" + arbolado.getSitioID() + ", " + arbolado.getConsecutivo() + ", " + arbolado.getNumeroIndividuo()
                + " ," + arbolado.getNumeroRama() + " ," + arbolado.getAzimut() + " ," + arbolado.getDistancia() + " ,"
                + arbolado.getFamiliaID() + ", " + arbolado.getGeneroID() + ", " + arbolado.getEspecieID() + ", " + arbolado.getInfraespecieID() + ", '"
                + arbolado.getNombreComun() + "', " + arbolado.getEsSubmuestra() + ", " + arbolado.getFormaVidaID() + ", " + arbolado.getFormaFusteID()
                + ", " + arbolado.getCondicionID() + ", " + arbolado.getMuertoPieID() + ", " + arbolado.getGradoPutrefaccionID() + ", " + arbolado.getTipoToconID() + ", " + arbolado.getDiametroNormal() + ", " + arbolado.getDiametroBasal() + ", " + arbolado.getAlturaTotal() + ", " + arbolado.getAnguloInclinacion()
                + ", " + arbolado.getAlturaFusteLimpio() + ", " + arbolado.getAlturaComercial() + ", " + arbolado.getDiametroCopaNS() + ", " + arbolado.getDiametroCopaEO() + ", " + arbolado.getProporcionCopaVivaID() + ", " + arbolado.getExposisicionCopaID()
                + ", " + arbolado.getPosicionCopaID() + ", " + arbolado.getDensidadCopaID() + ", " + arbolado.getMuerteRegresivaID() + ", " + arbolado.getTransparenciaFollajeID() + ", " + arbolado.getVigorID() + ", " + arbolado.getNivelVigorID() + ")";
        String query2 = "SELECT last_insert_rowid()";
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            ResultSet rs = st.executeQuery(query2);
            this.arboaldoID = rs.getInt(1);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo guardar la informacion en arbolado ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos al insertar datos de arbolado ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void updateArboladoA(CEArbolado arbolado) {
        query = "UPDATE TAXONOMIA_Arbolado SET Consecutivo= " + arbolado.getConsecutivo() + ", NoIndividuo= " + arbolado.getNumeroIndividuo() + ", NoRama= " + arbolado.getNumeroRama()
                + ", Azimut= " + arbolado.getAzimut() + ", Distancia= " + arbolado.getDistancia() + ", FamiliaID= " + arbolado.getFamiliaID()
                + ", GeneroID= " + arbolado.getGeneroID() + ", EspecieID= " + arbolado.getEspecieID() + ", InfraespecieID= " + arbolado.getInfraespecieID()
                + ", NombreComun= '" + arbolado.getNombreComun() + "', EsSubmuestra= " + arbolado.getEsSubmuestra()
                + ", FormaVidaID= " + arbolado.getFormaVidaID() + ", FormaFusteID= " + arbolado.getFormaFusteID() + ", CondicionID= " + arbolado.getCondicionID() + ", MuertoPieID= " + arbolado.getMuertoPieID() + ", GradoPutrefaccionID= "
                + arbolado.getGradoPutrefaccionID() + ", TipoToconID= " + arbolado.getTipoToconID() + ", DiametroNormal= " + arbolado.getDiametroNormal() + ", DiametroBasal= " + arbolado.getDiametroBasal() + ", AlturaTotal= " + arbolado.getAlturaTotal()
                + ", AnguloInclinacion= " + arbolado.getAnguloInclinacion() + ", AlturaFusteLimpio= " + arbolado.getAlturaFusteLimpio() + ", AlturaComercial= "
                + arbolado.getAlturaComercial() + ", DiametroCopaNS= " + arbolado.getDiametroCopaNS() + ", DiametroCopaEO= " + arbolado.getDiametroCopaEO() + ", ProporcionCopaVivaID= " + arbolado.getProporcionCopaVivaID() + ", ExposicionCopaID= " + arbolado.getExposisicionCopaID()
                + ", PosicionCopaID= " + arbolado.getPosicionCopaID() + ", DensidadCopaID= " + arbolado.getDensidadCopaID() + ", MuerteRegresivaID=" + arbolado.getMuerteRegresivaID() + ", TransparenciaFollajeID= " + arbolado.getTransparenciaFollajeID() + ", VigorID= " + arbolado.getVigorID() 
                +", NivelVigorID= " + arbolado.getNivelVigorID() + " WHERE ArboladoID = " + arbolado.getArboladoID();
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo modificar la información de arbolado ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la modificación de arbolado", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void updateArboladoSubmuestra(boolean haySubmuetra, int arboladoID) {
        String query1 = "UPDATE TAXONOMIA_Arbolado SET EsSubmuestra = 1 WHERE arboladoID= " + arboladoID;
        String query2 = "UPDATE TAXONOMIA_Arbolado SET EsSubmuestra = 0 WHERE arboladoID= " + arboladoID;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            if (haySubmuetra == true) {
                st.executeUpdate(query1);
            } else {
                st.executeUpdate(query2);
            }
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo actualizar si hay submuestra ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la modificación de si hay submuestra", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void deleteDatosArbolado(CEArbolado arbolado) {
        query = "DELETE FROM TAXONOMIA_Arbolado WHERE ArboladoID= " + arbolado.getArboladoID();
        Connection conn = LocalConnection.getConnection();
        String query2 = "SELECT last_insert_rowid()";
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            ResultSet rs = st.executeQuery(query2);
            this.arboaldoID = rs.getInt(1);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo eliminar la información del arbolado ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos  al eliminar el arbolado",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void deleteArboladoSitio(int sitioID) {
        query = "DELETE FROM TAXONOMIA_Arbolado WHERE SitioID= " + sitioID;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo eliminar la información del arbolado en sitio ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos  al eliminar el arbolado en sitio",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private int getNumeroRegistros(int sitioID) {
        query = "SELECT COUNT (*) AS Registros FROM TAXONOMIA_Arbolado WHERE SitioID = " + sitioID;
        int registros = 0;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                registros = rs.getInt("Registros");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al obtener el conteo de registros del arbolado ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return 0;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al obtener el conteo de registros del arbolado "
                        + e.getClass().getName() + " : " + e.getMessage(), "Conexion BD", JOptionPane.ERROR_MESSAGE);
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(
                            null, "Error! al cerrar la base de datos  al eliminar el arbolado"
                            + e.getClass().getName() + " : " + e.getMessage(),
                            "Conexion BD", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        return registros;
    }

    public void enumerarConsecutivo(int sitioID) {
        List<Integer> listAboladoID = getArboladoID(sitioID);
        Connection conn = LocalConnection.getConnection();
        if (listAboladoID != null) {
            int size = listAboladoID.size();
            int consecutivo = 1;
            try {
                for (int i = 0; i < size; i++) {
                    Statement st = conn.createStatement();
                    query = "UPDATE TAXONOMIA_Arbolado SET Consecutivo= " + consecutivo + "  WHERE ArboladoID= " + listAboladoID.get(i);
                    st.executeUpdate(query);
                    conn.commit();
                    consecutivo++;
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,
                        "Error! al enumerar el consecutivo del arbolado ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al enumerar el consecutivo del arbolado", "Conexion BD", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    
     public void enumerarRama(int sitioID) {
        List<Integer> listAboladoID = getArboladoID(sitioID);
        Connection conn = LocalConnection.getConnection();
        if (listAboladoID != null) {
            int size = listAboladoID.size();
            int consecutivo = 1;
            try {
                for (int i = 0; i < size; i++) {
                    Statement st = conn.createStatement();
                    query = "UPDATE TAXONOMIA_Arbolado SET NoRama= " + consecutivo + "  WHERE ArboladoID= " + listAboladoID.get(i);
                    st.executeUpdate(query);
                    conn.commit();
                    consecutivo++;
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,
                        "Error! al enumerar rama o tallo ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al enumerar rama o tallo", "Conexion BD", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private List<Integer> getArboladoID(int sitioID) {
        List<Integer> listArboladoID = new ArrayList<>();
        query = "SELECT ArboladoID, SitioID FROM TAXONOMIA_Arbolado WHERE SitioID= " + sitioID + " ORDER BY ArboladoID ASC";
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                listArboladoID.add(rs.getInt("ArboladoID"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener los datos de arbolado id ",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en lista de Arbolado id", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        return listArboladoID;
    }
    
    public int getLastArboladoID() {
        return this.arboaldoID;
    }

    public List<Integer> getConsecutivo(int sitioID) {
        List<Integer> listConsecutivo = new ArrayList<>();
        query = "SELECT SitioID, Consecutivo FROM TAXONOMIA_Arbolado WHERE SitioID= " + sitioID;
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

    public int getRamaTallo(int sitioID, int individuo, int rama) {
        query = "SELECT COUNT(*) AS Registros FROM TAXONOMIA_Arbolado WHERE SitioID = " + sitioID + " AND NoIndividuo = " + individuo + " AND NoRama = " + rama + " ";
        Connection conn = LocalConnection.getConnection();
        int registros = 0;
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                registros = rs.getInt("Registros");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! no se pudo obtener el numero de ramas o tallos ",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos el obtener el numero de ramas o tallos", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        return registros;
    }

    public CEArbolado getRegistroArboladoA(int arboladoID) {
        query = "SELECT ArboladoID, NoIndividuo, NoRama, Azimut, Distancia, FamiliaID, GeneroID, EspecieID, InfraespecieID, "
                + "NombreComun, EsSubmuestra, FormaVidaID, CondicionID, FormaFusteID, MuertoPieID, GradoPutrefaccionID, "
                + "TipoToconID, DiametroNormal, AlturaTotal, AnguloInclinacion, AlturaFusteLimpio, AlturaComercial, "
                + "DiametroCopaNS, DiametroCopaEO, VigorID, NivelVigorID, ClaveColecta FROM TAXONOMIA_Arbolado WHERE ArboladoID= " + arboladoID;
        Connection conn = LocalConnection.getConnection();
        CEArbolado arb = new CEArbolado();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                arb.setArboladoID(rs.getInt("ArboladoID"));
                arb.setNumeroIndividuo(rs.getInt("NoIndividuo"));
                arb.setNumeroRama(rs.getInt("NoRama"));
                arb.setAzimut(rs.getInt("Azimut"));
                arb.setDistancia(rs.getFloat("Distancia"));
                arb.setFamiliaID(rs.getInt("FamiliaID"));
                arb.setGeneroID(rs.getInt("GeneroID"));
                arb.setEspecieID(rs.getInt("EspecieID"));
                arb.setInfraespecieID(rs.getInt("InfraespecieID"));
                arb.setNombreComun(rs.getString("NombreComun"));
                arb.setEsSubmuestra(rs.getInt("EsSubmuestra"));
                arb.setFormaVidaID(rs.getInt("FormaVidaID"));
                arb.setCondicionID(rs.getInt("CondicionID"));
                arb.setFormaFusteID(rs.getInt("FormaFusteID"));
                arb.setMuertoPieID(rs.getInt("MuertoPieID"));
                arb.setGradoPutrefaccionID(rs.getInt("GradoPutrefaccionID"));
                arb.setTipoToconID(rs.getInt("TipoToconID"));
                arb.setDiametroNormal(rs.getFloat("DiametroNormal"));
                arb.setAlturaTotal(rs.getFloat("AlturaTotal"));
                if(rs.getObject("AnguloInclinacion") != null){
                     arb.setAnguloInclinacion(rs.getInt("AnguloInclinacion"));
                }
                
                if(rs.getObject("AlturaFusteLimpio") != null){
                     arb.setAlturaFusteLimpio(rs.getFloat("AlturaFusteLimpio"));
                }
                if(rs.getObject("AlturaComercial") != null){
                    arb.setAlturaComercial(rs.getFloat("AlturaComercial"));
                }
                arb.setDiametroCopaNS(rs.getFloat("DiametroCopaNS"));
                arb.setDiametroCopaEO(rs.getFloat("DiametroCopaEO"));
                arb.setVigorID(rs.getInt("VigorID"));
                arb.setNivelVigorID(rs.getInt("NivelVigorID"));
                arb.setClaveColecta(rs.getString("ClaveColecta"));
            }
            return arb;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al obtener un registro de arbolado ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane
                        .showMessageDialog(null,
                                "Error! al cerrar la base de datos en datos de registro de arbolado",
                                "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public CEArbolado getRegistroArboladoD(int arboladoID) {
        query = "SELECT ArboladoID, NoIndividuo, NoRama, Azimut, Distancia, FamiliaID, GeneroID, EspecieID, InfraespecieID, "
                + "NombreComun, EsSubmuestra, FormaVidaID, CondicionID, FormaFusteID, MuertoPieID, GradoPutrefaccionID, "
                + "TipoToconID, DiametroNormal, DiametroBasal, AlturaTotal, AnguloInclinacion, AlturaFusteLimpio, AlturaComercial, "
                + "DiametroCopaNS, DiametroCopaEO, ProporcionCopaVivaID, ExposicionCopaID, PosicionCopaID, DensidadCopaID, MuerteRegresivaID, "
                + "TransparenciaFollajeID, VigorID, NivelVigorID, ClaveColecta FROM TAXONOMIA_Arbolado WHERE ArboladoID= " + arboladoID;
        Connection conn = LocalConnection.getConnection();
        CEArbolado arb = new CEArbolado();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                arb.setArboladoID(rs.getInt("ArboladoID"));
                arb.setNumeroIndividuo(rs.getInt("NoIndividuo"));
                arb.setNumeroRama(rs.getInt("NoRama"));
                arb.setAzimut(rs.getInt("Azimut"));
                arb.setDistancia(rs.getFloat("Distancia"));
                arb.setFamiliaID(rs.getInt("FamiliaID"));
                arb.setGeneroID(rs.getInt("GeneroID"));
                arb.setEspecieID(rs.getInt("EspecieID"));
                arb.setInfraespecieID(rs.getInt("InfraespecieID"));
                arb.setNombreComun(rs.getString("NombreComun"));
                arb.setEsSubmuestra(rs.getInt("EsSubmuestra"));
                arb.setFormaVidaID(rs.getInt("FormaVidaID"));
                arb.setCondicionID(rs.getInt("CondicionID"));
                arb.setFormaFusteID(rs.getInt("FormaFusteID"));
                arb.setMuertoPieID(rs.getInt("MuertoPieID"));
                arb.setGradoPutrefaccionID(rs.getInt("GradoPutrefaccionID"));
                arb.setTipoToconID(rs.getInt("TipoToconID"));
                arb.setDiametroNormal(rs.getFloat("DiametroNormal"));
                arb.setDiametroBasal(rs.getFloat("DiametroBasal"));
                arb.setAlturaTotal(rs.getFloat("AlturaTotal"));
                if(rs.getObject("AnguloInclinacion") != null){
                     arb.setAnguloInclinacion(rs.getInt("AnguloInclinacion"));
                }
                if(rs.getObject("AlturaFusteLimpio") != null){
                     arb.setAlturaFusteLimpio(rs.getFloat("AlturaFusteLimpio"));
                }
                if(rs.getObject("AlturaComercial") != null){
                    arb.setAlturaComercial(rs.getFloat("AlturaComercial"));
                }
                arb.setDiametroCopaNS(rs.getFloat("DiametroCopaNS"));
                arb.setDiametroCopaEO(rs.getFloat("DiametroCopaEO"));
                arb.setProporcionCopaVivaID(rs.getInt("ProporcionCopaVivaID"));
                arb.setExposisicionCopaID(rs.getInt("ExposicionCopaID"));
                arb.setPosicionCopaID(rs.getInt("PosicionCopaID"));
                arb.setDensidadCopaID(rs.getInt("DensidadCopaID"));
                arb.setMuerteRegresivaID(rs.getInt("MuerteRegresivaID"));
                arb.setTransparenciaFollajeID(rs.getInt("TransparenciaFollajeID"));
                arb.setVigorID(rs.getInt("VigorID"));
                arb.setNivelVigorID(rs.getInt("NivelVigorID"));
                arb.setClaveColecta(rs.getString("ClaveColecta"));
            }
            return arb;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al obtener un registro de arbolado ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane
                        .showMessageDialog(null,
                                "Error! al cerrar la base de datos en datos de registro de arbolado",
                                "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public CEArbolado getRegistroArboladoG(int arboladoID) {
        query = "SELECT ArboladoID, NoIndividuo, NoRama, Azimut, Distancia, FamiliaID, GeneroID, EspecieID, InfraespecieID, "
                + "NombreComun, EsSubmuestra, FormaVidaID, CondicionID, FormaFusteID, MuertoPieID, GradoPutrefaccionID, "
                + "TipoToconID, DiametroNormal, DiametroBasal, AlturaTotal, AnguloInclinacion, AlturaFusteLimpio, AlturaComercial, "
                + "DiametroCopaNS, DiametroCopaEO, VigorID, NivelVigorID, ClaveColecta FROM TAXONOMIA_Arbolado WHERE ArboladoID= " + arboladoID;
        Connection conn = LocalConnection.getConnection();
        CEArbolado arb = new CEArbolado();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                arb.setArboladoID(rs.getInt("ArboladoID"));
                arb.setNumeroIndividuo(rs.getInt("NoIndividuo"));
                arb.setNumeroRama(rs.getInt("NoRama"));
                arb.setAzimut(rs.getInt("Azimut"));
                arb.setDistancia(rs.getFloat("Distancia"));
                arb.setFamiliaID(rs.getInt("FamiliaID"));
                arb.setGeneroID(rs.getInt("GeneroID"));
                arb.setEspecieID(rs.getInt("EspecieID"));
                arb.setInfraespecieID(rs.getInt("InfraespecieID"));
                arb.setNombreComun(rs.getString("NombreComun"));
                arb.setEsSubmuestra(rs.getInt("EsSubmuestra"));
                arb.setFormaVidaID(rs.getInt("FormaVidaID"));
                arb.setCondicionID(rs.getInt("CondicionID"));
                arb.setFormaFusteID(rs.getInt("FormaFusteID"));
                arb.setMuertoPieID(rs.getInt("MuertoPieID"));
                arb.setGradoPutrefaccionID(rs.getInt("GradoPutrefaccionID"));
                arb.setTipoToconID(rs.getInt("TipoToconID"));
                if (rs.getObject("DiametroNormal") != null) {
                    arb.setDiametroNormal(rs.getFloat("DiametroNormal"));
                }
                if (rs.getObject("DiametroBasal") != null) {
                    arb.setDiametroBasal(rs.getFloat("DiametroBasal"));
                }
                if (rs.getObject("AlturaTotal") != null) {
                    arb.setAlturaTotal(rs.getFloat("AlturaTotal"));
                }
                if(rs.getObject("AnguloInclinacion") != null){
                     arb.setAnguloInclinacion(rs.getInt("AnguloInclinacion"));
                }
                if(rs.getObject("AlturaFusteLimpio") != null){
                     arb.setAlturaFusteLimpio(rs.getFloat("AlturaFusteLimpio"));
                }
                if(rs.getObject("AlturaComercial") != null){
                    arb.setAlturaComercial(rs.getFloat("AlturaComercial"));
                }
                arb.setDiametroCopaNS(rs.getFloat("DiametroCopaNS"));
                arb.setDiametroCopaEO(rs.getFloat("DiametroCopaEO"));
                arb.setVigorID(rs.getInt("VigorID"));
                arb.setNivelVigorID(rs.getInt("NivelVigorID"));
                arb.setClaveColecta(rs.getString("ClaveColecta"));
            }
            return arb;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al obtener un registro de arbolado ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane
                        .showMessageDialog(null,
                                "Error! al cerrar la base de datos en datos de registro de arbolado",
                                "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public Integer getConsecutivoArbolado(int consecutivo) {
        query = "SELECT ArboladoID, Consecutivo FROM TAXONOMIA_Arbolado WHERE Consecutivo= " + consecutivo;
        Connection conn = LocalConnection.getConnection();
        CEArbolado arb = new CEArbolado();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                arb.setArboladoID(rs.getInt("ArboladoID"));
                arb.setNumeroIndividuo(rs.getInt("Consecutivo"));
            }
            return arb.getArboladoID();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al obtener un arbol por consecutivo ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane
                        .showMessageDialog(null,
                                "Error! al cerrar la base de datos en datos al obtener un arbol por consecutivo",
                                "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public boolean validarCrearSubmuestra(int sitioID) {
        query = "SELECT SitioID, EsSubmuestra FROM TAXONOMIA_Arbolado WHERE SitioID= " + sitioID + " AND EsSubmuestra= 1";
        boolean vacio = true;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                vacio = false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al validar la creación de submuestras", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en datos de validar la creación de submuestras", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        return vacio;
    }
    
    public boolean validarNumeroRamatallo(int sitioID, int numeroRama){

        query = "SELECT SitioID, NoRama FROM TAXONOMIA_Arbolado WHERE SitioID = " + sitioID + " AND NoRama= " + numeroRama;
         boolean vacio = false;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                vacio = true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al validar la creación de submuestras", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en datos de validar la creación de submuestras", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }

        return vacio;
    }

}
