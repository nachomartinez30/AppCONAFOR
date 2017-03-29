package gob.conafor.sitio.mod;

import gob.conafor.conn.dat.LocalConnection;
import gob.conafor.upm.mod.CatETipoInaccesibilidad;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class CDSitio {

    private String query;

    public List<Integer> getUPMS() {
        List<Integer> listUPM = new ArrayList();
        query = "SELECT DISTINCT UPMID FROM SITIOS_Sitio";
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                listUPM.add(rs.getInt("UPMID"));
            }
            listUPM.add(0, null);
            rs.close();
            st.close();
            return listUPM;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener los conglomerados por sitio ",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al obtener los conglomerados por sitio ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public List<Integer> getUPMSitios() {
        List<Integer> listUPM = new ArrayList();
        query = "SELECT DISTINCT sit.UPMID FROM SITIOS_Sitio sit WHERE (SELECT COUNT(sit1.UPMID) FROM SITIOS_Sitio sit1 WHERE sit.UPMID = sit1.UPMID) = 4 ORDER BY sit.UPMID ASC";
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                listUPM.add(rs.getInt("UPMID"));
            }
            listUPM.add(0, null);
            rs.close();
            st.close();
            return listUPM;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener los conglomerados con sitios a revisión",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al obtener los conglomerados con sitios a revisión ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public List<Integer> getUPMCreado() {
        List<Integer> listUPM = new ArrayList();
        query = "SELECT UPMID FROM UPM_UPM WHERE Accesible= 1 ORDER BY UPMID ASC";
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                listUPM.add(rs.getInt("UPMID"));
            }
            listUPM.add(0, null);
            rs.close();
            st.close();
            return listUPM;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener los conglomerados creados",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al obtener los conglomerados creados ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
   
    public boolean son4Sitios(Integer upmID) {
        this.query = "SELECT COUNT(*) AS Sitios FROM SITIOS_Sitio WHERE UPMID= " + upmID;
        Connection conn = LocalConnection.getConnection();
        Integer noSitios = 0;
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(this.query);
            noSitios = rs.getInt("Sitios");
            rs.close();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener la cantidad de sitios levantados",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return false;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al obtener la cantidad de sitios levantados", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        return noSitios == 4;
    }

    public List<Integer> getSitiosDisponibles(int upmID) {
        List<Integer> listSitios = new ArrayList<>();
        query = "SELECT UPMID, Sitio, SitioAccesible FROM SITIOS_Sitio sit WHERE  UPMID= " + upmID + " AND SitioAccesible= 1 AND Sitio" 
                + " NOT IN (SELECT DISTINCT Sitio FROM SYS_SecuenciaCaptura WHERE UPMID = sit.UPMID)";
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Integer index = rs.getInt(2);
                listSitios.add(index);
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener los sitios accesibles",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al obtener los sitios disponibles", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        listSitios.add(0, null);
        return listSitios;
    }
    
    public List<Integer> getSitiosAccesibles(int upmID) {
        List<Integer> listSitios = new ArrayList<>();
        query = "SELECT UPMID, Sitio, SitioAccesible FROM SITIOS_Sitio sit WHERE  UPMID= " + upmID + " AND SitioAccesible= 1";
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Integer index = rs.getInt(2);
                listSitios.add(index);
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener los sitios accesibles",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al obtener los sitios accesibles", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        listSitios.add(0, null);
        return listSitios;
    }
    
    public List<Integer> getSitiosEnCaptura(int upmID) {
        List<Integer> listSitios = new ArrayList<>();
        /*query = "SELECT UPMID, Sitio, SitioAccesible FROM SITIOS_Sitio sit WHERE  UPMID= " + upmID + " AND SitioAccesible= 1 AND Sitio "
                + "IN (SELECT Sitio FROM SYS_SecuenciaCaptura WHERE Sitio = sit.sitio AND Estatus= 0)";*/
        query = "SELECT DISTINCT UPMID, Sitio FROM SYS_SecuenciaCaptura WHERE UPMID= " + upmID;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Integer index = rs.getInt(2);
                listSitios.add(index);
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener los sitios accesibles",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al obtener los sitios accesibles", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        listSitios.add(0, null);
        return listSitios;
    }

    
    public List<Integer> getSitios(int upmID) {
        List<Integer> listSitios = new ArrayList<>();
        query = "SELECT UPMID, Sitio FROM SITIOS_Sitio WHERE UPMID= " + upmID;
        Connection conn = LocalConnection.getConnection();
        for (int i = 1; i < 5; i++) {
            listSitios.add(i);
        }
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Integer index = rs.getInt(2);
                listSitios.remove(index);
            }
            listSitios.add(0, null);
            rs.close();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener los numeros de sitio ",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al obtener los sitios "
                        + e.getClass().getName() + " : " + e.getMessage(), "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        listSitios.add(0, null);
        return listSitios;
    }
    
    public List<Integer> getSitiosRevision(){
        List<Integer> listSitios = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            listSitios.add(i);
        }
        listSitios.add(0, null);
        return listSitios;
    }

    public CESitio getDatosSitio(int upmID) {
        this.query = "SELECT SitioID, UPMID, Sitio, SenialGPS, GradosLatitud, MinutosLatitud, SegundosLatitud, "
                + "GradosLongitud, MinutosLongitud, SegundosLongitud, ErrorPresicion, Datum, SitioAccesible, "
                + "Azimut, Distancia, TipoInaccesibilidad, ExplicacionInaccesibilidad "
                + "FROM SITIOS_Sitio WHERE UPMID= " + upmID;
        Connection conn = LocalConnection.getConnection();
        CESitio ceSitio = new CESitio();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                ceSitio.setSitioID(rs.getInt("SitioID"));
                ceSitio.setUpmID(rs.getInt("UPMID"));
                ceSitio.setSenialGPS(rs.getInt("SenialGPS"));
                ceSitio.setGradosLatitud(rs.getInt("GradosLatitud"));
                ceSitio.setMinutosLatitud(rs.getInt("MinutosLatitud"));
                ceSitio.setSegundosLatitud(rs.getFloat("SegundosLatitud"));
                ceSitio.setGradosLongitud(rs.getInt("GradosLongitud"));
                ceSitio.setMinutosLongitud(rs.getInt("MinutosLongitud"));
                ceSitio.setSegundosLongitud(rs.getFloat("SegundosLongitud"));
                ceSitio.setErrorPrecision(rs.getInt("ErrorPresicion"));
                ceSitio.setDatum(rs.getString("Datum"));
                ceSitio.setSitioAccesible(rs.getInt("SitioAccesible"));
                ceSitio.setAzimut(rs.getInt("Azimut"));
                ceSitio.setDistancia(rs.getFloat("Distancia"));
                ceSitio.setTipoInaccesibilidadID(rs.getInt("TipoInaccesibilidad"));
                ceSitio.setExplicacionInaccesibilidad("ExplicacionInaccesibilidad");
            }
            st.close();
            rs.close();
            return ceSitio;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener los datos del sitio ",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en datos del sitio", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public CESitio getSitioRevision(int sitioID) {
        this.query = "SELECT UPMID, SitioID, SenialGPS, GradosLatitud, MinutosLatitud, SegundosLatitud, "
                + "GradosLongitud, MinutosLongitud, SegundosLongitud, ErrorPresicion, EvidenciaMuestreo , Datum, SitioAccesible, "
                + "Azimut, Distancia, TipoInaccesibilidad, ExplicacionInaccesibilidad "
                + "FROM SITIOS_Sitio WHERE SitioID= " + sitioID;
        Connection conn = LocalConnection.getConnection();
        CESitio ceSitio = new CESitio();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(this.query);
            while (rs.next()) {
                ceSitio.setUpmID(rs.getInt("UPMID"));
                ceSitio.setSenialGPS(rs.getInt("SenialGPS"));
                ceSitio.setGradosLatitud(rs.getInt("GradosLatitud"));
                ceSitio.setMinutosLatitud(rs.getInt("MinutosLatitud"));
                ceSitio.setSegundosLatitud(rs.getFloat("SegundosLatitud"));
                ceSitio.setGradosLongitud(rs.getInt("GradosLongitud"));
                ceSitio.setMinutosLongitud(rs.getInt("MinutosLongitud"));
                ceSitio.setSegundosLongitud(rs.getFloat("SegundosLongitud"));
                ceSitio.setErrorPrecision(rs.getInt("ErrorPresicion"));
                //System.err.println("Linea 325 CDSitio= "+ rs.getInt("EvidenciaMuestreo"));
                ceSitio.setEvidenciaMuestreo(rs.getInt("EvidenciaMuestreo"));
                ceSitio.setDatum(rs.getString("Datum"));
                ceSitio.setSitioAccesible(rs.getInt("SitioAccesible"));
                ceSitio.setAzimut(rs.getInt("Azimut"));
                ceSitio.setDistancia(rs.getFloat("Distancia"));
                ceSitio.setTipoInaccesibilidadID(rs.getInt("TipoInaccesibilidad"));
                ceSitio.setExplicacionInaccesibilidad(rs.getString("ExplicacionInaccesibilidad"));
            }
            st.close();
            rs.close();
            return ceSitio;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener los datos del sitio en revisión ",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en datos del sitio en revisión", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public CESitio getDatosSitioAccesible(int upmID , int sitio) {
        query = "SELECT SitioID, UPMID, Sitio, SenialGPS, GradosLatitud, MinutosLatitud, SegundosLatitud, "
                + "GradosLongitud, MinutosLongitud, SegundosLongitud, ErrorPresicion, Datum, SitioAccesible "
                + "FROM SITIOS_Sitio WHERE UPMID= " + upmID;
        Connection conn = LocalConnection.getConnection();
        CESitio ceSitio = new CESitio();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                ceSitio.setSitioID(rs.getInt("SitioID"));
                ceSitio.setUpmID(rs.getInt("UPMID"));
                ceSitio.setSenialGPS(rs.getInt("SenialGPS"));
                ceSitio.setGradosLatitud(rs.getInt("GradosLatitud"));
                ceSitio.setMinutosLatitud(rs.getInt("MinutosLatitud"));
                ceSitio.setSegundosLatitud(rs.getFloat("SegundosLatitud"));
                ceSitio.setGradosLongitud(rs.getInt("GradosLongitud"));
                ceSitio.setMinutosLongitud(rs.getInt("MinutosLongitud"));
                ceSitio.setSegundosLongitud(rs.getFloat("SegundosLongitud"));
                ceSitio.setErrorPrecision(rs.getInt("ErrorPresicion"));
                ceSitio.setDatum(rs.getString("Datum"));
                ceSitio.setSitioAccesible(rs.getInt("SitioAccesible"));
            }
            st.close();
            rs.close();
            return ceSitio;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener los datos del sitio ",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en datos del sitio", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public CESitio getDatosSitioAccesibleSenial(int upmID) {
        query = "SELECT SitioID, UPMID, Sitio, SenialGPS, GradosLatitud, MinutosLatitud, SegundosLatitud, "
                + "GradosLongitud, MinutosLongitud, SegundosLongitud, ErrorPresicion, Datum, Azimut, Distancia, SitioAccesible "
                + "FROM SITIOS_Sitio WHERE UPMID= " + upmID;
        Connection conn = LocalConnection.getConnection();
        CESitio ceSitio = new CESitio();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                ceSitio.setSitioID(rs.getInt("SitioID"));
                ceSitio.setUpmID(rs.getInt("UPMID"));
                ceSitio.setSenialGPS(rs.getInt("SenialGPS"));
                ceSitio.setGradosLatitud(rs.getInt("GradosLatitud"));
                ceSitio.setMinutosLatitud(rs.getInt("MinutosLatitud"));
                ceSitio.setSegundosLatitud(rs.getFloat("SegundosLatitud"));
                ceSitio.setGradosLongitud(rs.getInt("GradosLongitud"));
                ceSitio.setMinutosLongitud(rs.getInt("MinutosLongitud"));
                ceSitio.setSegundosLongitud(rs.getFloat("SegundosLongitud"));
                ceSitio.setErrorPrecision(rs.getInt("ErrorPresicion"));
                ceSitio.setDatum(rs.getString("Datum"));
                ceSitio.setAzimut(rs.getInt("Azimut"));
                ceSitio.setDistancia(rs.getFloat("Distancia"));
                ceSitio.setSitioAccesible(rs.getInt("SitioAccesible"));
            }
            st.close();
            rs.close();
            return ceSitio;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener los datos del sitio sin senial",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en datos del sitio sin senial", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public CESitio getDatosSitioInaccesible(int upmID) {
        query = "SELECT SitioID, UPMID, Sitio, SitioAccesible, TipoInaccesibilidad, ExplicacionInaccesibilidad"
                + "FROM SITIOS_Sitio WHERE UPMID= " + upmID;
        Connection conn = LocalConnection.getConnection();
        CESitio ceSitio = new CESitio();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                ceSitio.setSitioID(rs.getInt("SitioID"));
                ceSitio.setUpmID(rs.getInt("UPMID"));
                ceSitio.setSitioAccesible(rs.getInt("SitioAccesible"));
                ceSitio.setTipoInaccesibilidadID(rs.getInt("TipoInaccesibilidad"));
                ceSitio.setExplicacionInaccesibilidad(rs.getString("ExplicacionInaccesibilidad"));
            }
            st.close();
            rs.close();
            return ceSitio;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener los datos del sitio inaccesible",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en datos del sitio inaccesible", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    
    public List<CatETipoInaccesibilidad> getTipoInaccesibilidad() {
        List<CatETipoInaccesibilidad> listInaccesibilidad = new ArrayList<>();
        query = "SELECT TipoInaccesibilidadID, Clave, Tipo, Descripcion FROM CAT_TipoInaccesibilidad";
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                CatETipoInaccesibilidad ti = new CatETipoInaccesibilidad();
                ti.setTipoInaccesibilidadID(rs.getInt("TipoInaccesibilidadID"));
                ti.setClave(rs.getString("Clave"));
                ti.setTipo(rs.getString("Tipo"));
                ti.setDescripcion(rs.getString("Descripcion"));
                listInaccesibilidad.add(ti);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener los datos de tipo de inaccesibilidad ",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {

                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en datos en tipos de inaccesibilidad"
                        + e.getClass().getName() + " : " + e.getMessage(), "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        listInaccesibilidad.add(0, null);
        return listInaccesibilidad;
    }

    public void insertSitioAccesible(CESitio sitio) {
        query = "INSERT INTO SITIOS_Sitio (UPMID, Sitio, SenialGPS, GradosLatitud, MinutosLatitud, SegundosLatitud, "
                + "GradosLongitud, MinutosLongitud, SegundosLongitud, ErrorPresicion, EvidenciaMuestreo, Datum, SitioAccesible)VALUES(" + sitio.getUpmID() + ", "
                + sitio.getSitio() + ", " + sitio.getSenialGPS() + ", " + sitio.getGradosLatitud() + ", " + sitio.getMinutosLatitud() + ", "
                + sitio.getSegundosLatitud() + ", " + sitio.getGradosLongitud() + ", " + sitio.getMinutosLongitud() + ", " + sitio.getSegundosLongitud() + ", "
                + sitio.getErrorPrecision() + ", " + sitio.getEvidenciaMuestreo() + ", '" + sitio.getDatum() + "' ," + sitio.getSitioAccesible() + ")";
        System.out.println(query);
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo guardar la información del sitio ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en guardar sitio accesible", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void insertSitioInaccesible(CESitio sitio) {
        query = "INSERT INTO SITIOS_Sitio (UPMID, Sitio, SitioAccesible, TipoInaccesibilidad, ExplicacionInaccesibilidad) VALUES(" + sitio.getUpmID() + ", "
                + sitio.getSitio() + ", " + sitio.getSitioAccesible() + ", " + sitio.getTipoInaccesibilidadID() + ", '" + sitio.getExplicacionInaccesibilidad() + "')";
        System.out.println(query);
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo guardar la información del sitio inaccesible", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en guardar sitio inaaccesible", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void insertSitioAccesibleSenial(CESitio sitio) {
        query = "INSERT INTO SITIOS_Sitio (UPMID, Sitio, SenialGPS, GradosLatitud, MinutosLatitud, SegundosLatitud, "
                + "GradosLongitud, MinutosLongitud, SegundosLongitud, ErrorPresicion, EvidenciaMuestreo, Datum, Azimut, Distancia, SitioAccesible)VALUES(" + sitio.getUpmID() + ", "
                + sitio.getSitio() + ", " + sitio.getSenialGPS() + ", " + sitio.getGradosLatitud() + ", " + sitio.getMinutosLatitud() + ", "
                + +sitio.getSegundosLatitud() + ", " + sitio.getGradosLongitud() + ", " + sitio.getMinutosLongitud() + ", " + sitio.getSegundosLongitud() + ", "
                + sitio.getErrorPrecision() + ", " + sitio.getEvidenciaMuestreo() + ", '" + sitio.getDatum() + "', " + sitio.getAzimut() + ", " + sitio.getDistancia() + ", " + sitio.getSitioAccesible() + ")";
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo guardar la información del sitio con coordenadas auxiliares", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en guardar sitio accesible con coordenadas auxiliares", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void updateSitioAccesible(CESitio sitio) {
        query = "UPDATE SITIOS_Sitio SET SenialGPS= " + sitio.getSenialGPS() + ", GradosLatitud= "
                + sitio.getGradosLatitud() + ", MinutosLatitus= " + sitio.getMinutosLatitud() + ", SegundosLatitud= "
                + sitio.getSegundosLatitud() + ", GradosLongitud= " + sitio.getGradosLongitud() + ", MinutosLongitud= "
                + sitio.getMinutosLongitud() + ", SegundosLongitud= " + sitio.getSegundosLongitud() + ", ErrorPresicion= "
                + sitio.getErrorPrecision() + ", Datum= " + sitio.getDatum() + "SitioAccesiblde= " + sitio.getSitioAccesible()
                + " WHERE SitioID= " + sitio.getSitioID();
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo modificar la información del sitio accesible ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos  al modificar el sitio accesible",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void updateSitio(CESitio ceSitio){
        this.query = "UPDATE SITIOS_Sitio SET SenialGPS= " + ceSitio.getSenialGPS() + ", GradosLatitud= "
                + ceSitio.getGradosLatitud() + ", MinutosLatitud= " + ceSitio.getMinutosLatitud() + ", SegundosLatitud= "
                + ceSitio.getSegundosLatitud() + ", GradosLongitud= " + ceSitio.getGradosLongitud() + ", MinutosLongitud= "
                + ceSitio.getMinutosLongitud() + ", SegundosLongitud= " + ceSitio.getSegundosLongitud() + ", ErrorPresicion= "
                + ceSitio.getErrorPrecision() + ", Datum= '" + ceSitio.getDatum() + "', Azimut= " + ceSitio.getAzimut() + ", Distancia= "
                + ceSitio.getDistancia() + ", SitioAccesible= " + ceSitio.getSitioAccesible() + ", EvidenciaMuestreo= " + ceSitio.getEvidenciaMuestreo() 
                + ", TipoInaccesibilidad= " + ceSitio.getTipoInaccesibilidadID() + ", ExplicacionInaccesibilidad= '" + ceSitio.getExplicacionInaccesibilidad() 
                + "' WHERE UPMID= " + ceSitio.getUpmID() + " AND Sitio= " +ceSitio.getSitio();
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(this.query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo modificar la información del sitio", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos  al modificar el sitio",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void updateSitioAccesibleSenial(CESitio sitio) {
        query = "UPDATE SITIOS_Sitio SET SenialGPS= " + sitio.getSenialGPS() + ", GradosLatitud= "
                + sitio.getGradosLatitud() + ", MinutosLatitus= " + sitio.getMinutosLatitud() + ", SegundosLatitud= "
                + sitio.getSegundosLatitud() + ", GradosLongitud= " + sitio.getGradosLongitud() + ", MinutosLongitud= "
                + sitio.getMinutosLongitud() + ", SegundosLongitud= " + sitio.getSegundosLongitud() + ", ErrorPresicion= "
                + sitio.getErrorPrecision() + ", Datum= " + sitio.getDatum() + ", Azimut= " + sitio.getAzimut() + ", Distancia= "
                + sitio.getDistancia() + ", SitioAccesible= " + sitio.getSitioAccesible() + " WHERE SitioID= " + sitio.getSitioID();
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo modificar la información del sitio accesible sin senial", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos  al modificar el sitio accesible sin senial",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void updateSitioInaccesible(CESitio sitio) {
        query = "UPDATE SITIOS_Sitio SET SitioAccesible= " + sitio.getSitioAccesible() + ", TipoInaccesibilidad= "
                + sitio.getTipoInaccesibilidadID() + ", ExplicacionInaccesibilidad= '" + sitio.getExplicacionInaccesibilidad()
                + "' WHERE SitioID= " + sitio.getSitioID();
        Connection conn = LocalConnection.getConnection();
        //System.out.println(query);
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo modificar la información del sitio inaccesible", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos  al modificar el sitio inaccesible",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public CESitio getSotoBosqueFuera(int sitioID) {
        query = "SELECT SotobosqueFuera, PorcentajeSotobosqueFuera FROM SITIOS_Sitio WHERE SitioID= " + sitioID;
        Connection conn = LocalConnection.getConnection();
        CESitio ceSitio = new CESitio();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                ceSitio.setSotobosqueFuera(rs.getInt("SotobosqueFuera"));
                ceSitio.setPorcentajeSotobosque(rs.getInt("PorcentajeSotobosqueFuera"));
            }
            st.close();
            rs.close();
            return ceSitio;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo obtener la información de sotobosque fuera", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos  al obtener la información de sotobosque fuera",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void updateSotobosqueFuera(CESitio ceSitio) {
        query = "UPDATE SITIOS_Sitio SET SotobosqueFuera= " + ceSitio.getSotobosqueFuera() + ", PorcentajeSotobosqueFuera= " + ceSitio.getPorcentajeSotobosque()
                + " WHERE SitioID= " + ceSitio.getSitioID();
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo modificar la información del sotobosque fuera fuera del sitio ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos  al modificar el sotobosque fuera",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void updateRepobladoFuera(CESitio sitio) {
        query = "UPDATE SITIOS_Sitio SET RepobladoFuera= " + sitio.getRepobladoFuera() + ", PorcentajeRepoblado= " + sitio.getPorcentajeRepoblado()
                + " WHERE SitioID= " + sitio.getSitioID();
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo modificar la información del repoblado fuera del sitio ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos  al modificar el repoblado fuera",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public CESitio getRepobladoFuera(int sitioID) {
        query = "SELECT RepobladoFuera, PorcentajeRepoblado FROM SITIOS_Sitio WHERE SitioID= " + sitioID;
        Connection conn = LocalConnection.getConnection();
        CESitio ceSitio = new CESitio();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                ceSitio.setRepobladoFuera(rs.getInt("RepobladoFuera"));
                ceSitio.setPorcentajeRepoblado(rs.getInt("PorcentajeRepoblado"));
            }
            st.close();
            rs.close();
            return ceSitio;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo obtener la información de repoblado fuera", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos  al obtener la información de repoblado fuera",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void deleteSitio(int upm, int sitio){
        query = "DELETE FROM SITIOS_Sitio WHERE UPMID= " + upm + " AND Sitio= " + sitio;
         Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo eliminar la información del sitio ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos  al eliminar la información del sitio",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Datos de la clave de vegetacion.
    //Parte de la información del sitio.
    public CESitio getClaveVegetacion(int sitioID) {
        query = "SELECT SitioID, ClaveSerieV, Condicion, FaseSucecional, ArbolFuera, Ecotono, "
                + "CondicionPresenteCampo, CondicionEcotono FROM SITIOS_Sitio WHERE SitioID= " + sitioID;
        Connection conn = LocalConnection.getConnection();
        CESitio ceSitio = new CESitio();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                ceSitio.setClaveSerieVID(rs.getInt("ClaveSerieV"));
                ceSitio.setCondicion(rs.getInt("Condicion"));
                ceSitio.setFaseSucecionalID(rs.getInt("FaseSucecional"));
                ceSitio.setArbolFuera(rs.getInt("ArbolFuera"));
                ceSitio.setEcotono(rs.getInt("Ecotono"));
                ceSitio.setCondicionPresenteCampo(rs.getString("CondicionPresenteCampo"));
                ceSitio.setCondicionEcotono(rs.getString("CondicionEcotono"));
            }
            st.close();
            rs.close();
            return ceSitio;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo obtener la información de la clave de vegetación del sitio ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos  al obtener la información de la clave de vegetación del sitio",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public List<CatEClaveSerieV> getClaveVegetacion() {
        List<CatEClaveSerieV> listClaveV = new ArrayList<>();
        query = "SELECT ClaveSerieVID, Ecosistema, Formacion, TipoVegetacion, Clave, EsForestal FROM CAT_ClaveSerieV ";
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                CatEClaveSerieV cs = new CatEClaveSerieV();
                cs.setClaveSerievID(rs.getInt("ClaveSerieVID"));
                cs.setEcosistema(rs.getString("Ecosistema"));
                cs.setFormacion(rs.getString("Formacion"));
                cs.setTipoVegetacion(rs.getString("TipoVegetacion"));
                cs.setClave(rs.getString("Clave"));
                cs.setEsForestal(rs.getInt("EsForestal"));
                listClaveV.add(cs);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener los datos de clave de vegetacion serie V ",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en datos en tipos de inaccesibilidad", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        listClaveV.add(0, null);
        return listClaveV;
    }

    public List<CatEFaseSucecional> getClaveSucecional() {
        List<CatEFaseSucecional> listFase = new ArrayList<>();
        query = "SELECT FaseSucecionalID, Descripcion, Clave FROM CAT_FaseSucecional";
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                CatEFaseSucecional fs = new CatEFaseSucecional();
                fs.setFaseSucecionalID(rs.getInt("FaseSucecionalID"));
                fs.setDescripcion(rs.getString("Descripcion"));
                fs.setClave(rs.getString("Clave"));
                listFase.add(fs);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener los datos de fase sucecional ",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en datos de fase sucecional", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        listFase.add(0, null);
        return listFase;
    }

    public void updateClaveVegetacion(CESitio sitio) {
        query = "UPDATE SITIOS_Sitio SET ClaveSerieV= " + sitio.getClaveSerieVID() + ", Condicion= " + sitio.getCondicion()+ ", FaseSucecional= " + sitio.getFaseSucecionalID()
                + ", ArbolFuera= " + sitio.getArbolFuera() + ", Ecotono= " + sitio.getEcotono() + ", CondicionPresenteCampo= '" + sitio.getCondicionPresenteCampo()
                + "', CondicionEcotono= '" + sitio.getCondicionEcotono() + "' WHERE SitioID= " + sitio.getSitioID() + " AND Sitio= " + sitio.getSitio();
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo modificar la información de la clave de vegetacion ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la modificación de la clave de vegetacion", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void setObservacionesSitio(CESitio ceSitio) {
        query = "UPDATE SITIOS_Sitio SET Observaciones= '" + ceSitio.getObservaciones() + "' WHERE SitioID= " + ceSitio.getSitioID();
        boolean vacio = true;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo agregar la información de observaciones del sitio ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al agregar la información de observacioens del sitio ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public CESitio getObservaciones(int sitioID){
        query = "SELECT Observaciones FROM SITIOS_Sitio WHERE SitioID= " + sitioID;
        Connection conn = LocalConnection.getConnection();
        CESitio ceSitio = new CESitio();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                ceSitio.setObservaciones(rs.getString("Observaciones"));
            }
            st.close();
            rs.close();
            return ceSitio;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo obtener las observaciones del sitio ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos  al obtener las observaciones del sitio ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public boolean validarSitio1(int upmID) {
        query = "SELECT * FROM SITIOS_Sitio WHERE UPMID =" + upmID;
        Connection conn = LocalConnection.getConnection();
        boolean vacio = false;
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                vacio = true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo validar la existencia de sitios ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en datos de validar la existencia de sitios ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        return vacio;
    }

    public Integer getSitioID(Integer upmID, Integer sitio) {
        query = "SELECT UPMID, Sitio, SitioID FROM SITIOS_Sitio WHERE UPMID= " + upmID + " AND Sitio= " + sitio;
        Connection conn = LocalConnection.getConnection();
        Integer sitioID = 0;
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                sitioID = rs.getInt("SitioID");
            }
            return sitioID;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo obtener el sitioID ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al obtener el sitioID ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
