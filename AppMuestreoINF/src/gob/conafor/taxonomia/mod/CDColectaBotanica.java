package gob.conafor.taxonomia.mod;

import gob.conafor.conn.dat.LocalConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class CDColectaBotanica {

    private String query;

    public List<CatESeccionTaxonomica> getSecciones() {
        List<CatESeccionTaxonomica> listSeccion = new ArrayList<>();
        query = "SELECT SeccionTaxonomicaID, Seccion FROM CAT_SeccionesTaxonomicas";
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                CatESeccionTaxonomica seccion = new CatESeccionTaxonomica();
                seccion.setSeccionTaxonomicaID(rs.getInt("SeccionTaxonomicaID"));
                seccion.setSeccion(rs.getString("Seccion"));
                listSeccion.add(seccion);
            }
            listSeccion.add(0, null);
            st.close();
            rs.close();
            return listSeccion;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en lista de secciones taxonómicas", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al cargar la lista de secciones taxonómicas", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
  
    public List<String> getClaveCreada(int UPMID) {
        List<String> listClaveColecta = new ArrayList<>();
        this.query = "SELECT UPMID, ClaveColecta FROM TAXONOMIA_ColectaBotanica WHERE UPMID= " + UPMID;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                listClaveColecta.add(rs.getString("ClaveColecta"));
            }
            listClaveColecta.add(0, null);
            st.close();
            rs.close();
            return listClaveColecta;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener las clave de colecta creadas ",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al obtener las claves de colecta creadas "
                , "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public String getPreclave(int upmID) {
        String fechaInicio = null;
        String upm = null;
        String preclave = null;
        this.query = "SELECT UPMID, FechaInicio FROM UPM_UPM WHERE UPMID= " + upmID;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                fechaInicio = rs.getString("FechaInicio");
            }
            st.close();
            rs.close();
            String anio = fechaInicio.substring(2, 4);
            String mes = fechaInicio.substring(5, 7);
            String dia = fechaInicio.substring(8, 10);
            fechaInicio = anio + mes + dia;
            if (upmID < 10) {
                upm = "00000" + upmID;
            } else if (upmID >= 10 && upmID < 100) {
                upm = "0000" + upmID;
            } else if (upmID >= 100 && upmID < 1000) {
                upm = "000" + upmID;
            } else if (upmID >= 1000 && upmID < 10000) {
                upm = "00" + upmID;
            } else if (upmID >= 10000 && upmID < 100000) {
                upm = "0" + upmID;
            } else if (upmID >= 100000) {
                upm = String.valueOf(upmID);
            }
            preclave = upm + "_" + fechaInicio;
            return preclave;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener la fecha de inicio para la pre-clave de colecta ",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al obtener la fecha de inicio para la pre-clave de colecta "
                , "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public List<Integer> getUPMID() {
        List<Integer> listUPMID = new ArrayList();
        this.query = "SELECT DISTINCT UPMID FROM TAXONOMIA_ColectaBotanica;";
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                listUPMID.add(rs.getInt("UPMID"));
            }
            listUPMID.add(0, null);
            st.close();
            rs.close();
            return listUPMID;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener los puntos UPM de la colecta botanica ",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al obtener los puntos UPM de la colecta botanica"
                , "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public List<String> getClaveColecta(int upmID) {
        List<String> listClave = new ArrayList<>();
        this.query = "SELECT ClaveColecta FROM TAXONOMIA_ColectaBotanica WHERE UPMID= " + upmID;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                listClave.add(rs.getString("ClaveColecta"));
            }
            listClave.add(0, null);
            st.close();
            rs.close();
            return listClave;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener las claves de la colecta botanica ",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al obtener las claves de la colecta botanica"
                , "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void insertColectaBotanica(CEColectaBotanica ceColecta) {
        this.query = "INSERT INTO TAXONOMIA_ColectaBotanica(UPMID, FamiliaID, GeneroID, EspecieID, InfraespecieID, NombreComun, Sitio, SeccionID, Consecutivo, "
                + "ClaveColecta, ContraFuertes, Exudado, IndicarExudado, Color, IndicarColor, CambioColor, AceitesVolatiles, "
                + "ColorFlor, IndicarColorFlor, HojasTejidoVivo, FotoFlor, FotoFruto, FotoHojasArriba, FotoHojasAbajo, FotoArbolCompleto, "
                + "FotoCorteza, VirutaIncluida, CortezaIncluida, MaderaIncluida, Observaciones)VALUES(" + ceColecta.getUPMID() + ", "
                + ceColecta.getFamiliaID() + ", " + ceColecta.getGeneroID() + ", " + ceColecta.getEspecieID() + ", '" + ceColecta.getInfraespecie()
                + "', '" + ceColecta.getNombreComun() + "', " + ceColecta.getSitio() + ", " + ceColecta.getSeccionID() + ", " + ceColecta.getConsecutivo() + ", '" + ceColecta.getClaveColecta() + "', " + ceColecta.getContraFuertes()
                + ceColecta.getExudado() + ", '" + ceColecta.getIndicarExudado() + "', " + ceColecta.getColor() + ", '" + ceColecta.getIndicarColor() + "', "
                + ceColecta.getCambioColor() + ", " + ceColecta.getAceitesVolatiles() + ", " + ceColecta.getColorFlor() + ", '" + ceColecta.getIndicarColorFlor()
                + "', " + ceColecta.getHojasTejidoVivo() + ", " + ceColecta.getFotoFlor() + ", " + ceColecta.getFotoFruto() + ", " + ceColecta.getFotoHojasArriba()
                + ", " + ceColecta.getFotoHojasAbajo() + ", " + ceColecta.getFotoArbolCompleto() + ", " + ceColecta.getFotoCorteza() + ", " + ceColecta.getVirutaIncluida()
                + ", " + ceColecta.getCortezaIncluida() + ", " + ceColecta.getMaderaIncluida() + ", '" + ceColecta.getObservaciones() + "')";
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo guardar la informacion de colecta botánica"
             , "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos al insertar datos de colecta botánica ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void modificarClaveColecta(String Clave,int UPMID,String ClaveNueva){
      String query="UPDATE TAXONOMIA_ColectaBotanica SET ClaveColecta = '" + ClaveNueva + "' WHERE ClaveColecta='"+Clave+"' AND UPMID="+UPMID;
      Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            //System.out.println(query);
            st.executeUpdate(query);
            conn.commit();
            st.close();
            JOptionPane.showMessageDialog(null, "Clave Actualizada"
                , "Conexion BD", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo modificar la Clave de Colecta"
            , "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la actualizacion de Clave"
                , "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
  }
    
    public void actualizarColectaBotanica(CEColectaBotanica ceColecta) {
        this.query = "UPDATE TAXONOMIA_ColectaBotanica SET ContraFuertes= " + ceColecta.getContraFuertes() + ", Sitio= " + ceColecta.getSitio() + ", SeccionID= " + ceColecta.getSeccionID() + ", Consecutivo= " + ceColecta.getConsecutivo()
                + ", Exudado= " + ceColecta.getExudado() + ", IndicarExudado= '" + ceColecta.getIndicarExudado() + "', Color= " + ceColecta.getColor() + ", IndicarColor= '"
                + ceColecta.getIndicarColor() + "', CambioColor= " + ceColecta.getCambioColor() + ", AceitesVolatiles= " + ceColecta.getAceitesVolatiles() + ", ColorFlor= "
                + ceColecta.getColorFlor() + ", IndicarColorFlor= '" + ceColecta.getIndicarColorFlor() + "', HojasTejidoVivo= " + ceColecta.getHojasTejidoVivo() + ", FotoFlor= "
                + ceColecta.getFotoFlor() + ", FotoFruto= " + ceColecta.getFotoFruto() + ", FotoHojasArriba= " + ceColecta.getFotoHojasArriba() + ", FotoHojasAbajo= "
                + ceColecta.getFotoHojasAbajo() + ", FotoArbolCompleto= " + ceColecta.getFotoArbolCompleto() + ", FotoCorteza= " + ceColecta.getFotoCorteza() + ", VirutaIncluida= "
                + ceColecta.getVirutaIncluida() + ", CortezaIncluida= " + ceColecta.getCortezaIncluida() + ", MaderaIncluida= " + ceColecta.getMaderaIncluida() + ", Observaciones= '"
                + ceColecta.getObservaciones() + "' WHERE ClaveColecta = '" + ceColecta.getClaveColecta() + "'";
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo modificar la información de colecta botánica"
            , "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la información de colecta botánica"
                , "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //nombreTabla = el nombre de la tabla a colocar la clave de colecta
    //compoID = nombre del ID principal de la tabla
    //clave = clave de colecta botanica
    //id = ID del registro a actualizar
    public void asignarClaveColecta(String nombreTabla, String nombreCampo, int noIndividuo, int sitioID, String claveColecta) {
        query = "UPDATE " + nombreTabla + " SET ClaveColecta= '" + claveColecta + "' WHERE " + nombreCampo + "= " + noIndividuo + " AND SitioID= "
                + sitioID;
        Connection conn = LocalConnection.getConnection();
        //System.out.println(query);
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo asignar la clave de colecta botánica "
            , "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al asignar la clave de colecta botánica"
                , "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void insertarClave(CEColectaBotanica ceColecta, int UPMID, String claveColecta) {
        query = "INSERT INTO TAXONOMIA_ColectaBotanica(UPMID, FamiliaID, GeneroID, EspecieID, InfraespecieID, NombreComun, ClaveColecta)VALUES"
                + "(" + UPMID + ", " + ceColecta.getFamiliaID() + ", " + ceColecta.getGeneroID() + ", " + ceColecta.getEspecieID()
                + ", '" + ceColecta.getInfraespecie() + "', '" + ceColecta.getNombreComun() + "', '" + claveColecta + "')";
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo guardar la clave de colecta botánica"
            , "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos al insertar la clave de colecta botánica",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public CEColectaBotanica getColectaBotanica(String claveColecta) {
        this.query = "SELECT FamiliaID, GeneroID, EspecieID, InfraespecieID, NombreComun, Sitio, SeccionID, Consecutivo, ClaveColecta, ContraFuertes, "
                + "Exudado, IndicarExudado, Color, IndicarColor, CambioColor, AceitesVolatiles, ColorFlor, IndicarColorFlor, "
                + "HojasTejidoVivo, FotoFlor, FotoFruto, FotoHojasArriba, FotoHojasAbajo, FotoArbolCompleto, FotoCorteza, "
                + "VirutaIncluida, CortezaIncluida, MaderaIncluida, Observaciones FROM TAXONOMIA_ColectaBotanica WHERE "
                + "ClaveColecta= '" + claveColecta + "'";
        Connection conn = LocalConnection.getConnection();
        CEColectaBotanica ceColecta = new CEColectaBotanica();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                ceColecta.setFamiliaID(rs.getInt("FamiliaID"));
                ceColecta.setGeneroID(rs.getInt("GeneroID"));
                ceColecta.setEspecieID(rs.getInt("EspecieID"));
                ceColecta.setInfraespecie(rs.getInt("InfraespecieID"));
                ceColecta.setNombreComun(rs.getString("NombreComun"));
                ceColecta.setSitio(rs.getInt("Sitio"));
                ceColecta.setSeccionID(rs.getInt("SeccionID"));
                ceColecta.setConsecutivo(rs.getInt("Consecutivo"));
                ceColecta.setContraFuertes(rs.getInt("ContraFuertes"));
                ceColecta.setExudado(rs.getInt("Exudado"));
                ceColecta.setIndicarExudado(rs.getString("IndicarExudado"));
                ceColecta.setColor(rs.getInt("Color"));
                ceColecta.setIndicarColor(rs.getString("IndicarColor"));
                ceColecta.setCambioColor(rs.getInt("CambioColor"));
                ceColecta.setAceitesVolatiles(rs.getInt("AceitesVolatiles"));
                ceColecta.setColorFlor(rs.getInt("ColorFlor"));
                ceColecta.setIndicarColorFlor(rs.getString("IndicarColorFlor"));
                ceColecta.setHojasTejidoVivo(rs.getInt("HojasTejidoVivo"));
                ceColecta.setFotoFlor(rs.getInt("FotoFlor"));
                ceColecta.setFotoFruto(rs.getInt("FotoFruto"));
                ceColecta.setFotoHojasArriba(rs.getInt("FotoHojasArriba"));
                ceColecta.setFotoHojasAbajo(rs.getInt("FotoHojasAbajo"));
                ceColecta.setFotoArbolCompleto(rs.getInt("FotoArbolCompleto"));
                ceColecta.setFotoCorteza(rs.getInt("FotoCorteza"));
                ceColecta.setVirutaIncluida(rs.getInt("VirutaIncluida"));
                ceColecta.setCortezaIncluida(rs.getInt("CortezaIncluida"));
                ceColecta.setMaderaIncluida(rs.getInt("MaderaIncluida"));
                ceColecta.setObservaciones(rs.getString("Observaciones"));
            }
            st.close();
            rs.close();
            return ceColecta;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener la información general del upm ",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en datos al revisar si hay datos de colecta botánica "
                , "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public boolean existeClave(String claveColecta) {
        this.query = "SELECT ClaveColecta FROM TAXONOMIA_ColectaBotanica WHERE ClaveColecta= '" + claveColecta + "'";
        boolean vacio = false;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                vacio = true;
            }
            st.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al revisar si ya existe la clave  de colecta botánica "
            , "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al revisar si ya existe la clave de colecta botánica "
                , "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        return vacio;
    }
    
    public boolean hayDatosColecta(String claveColecta) {
        this.query = "SELECT ClaveColecta FROM TAXONOMIA_ColectaBotanica WHERE ClaveColecta= '" + claveColecta + "' AND ContraFuertes= 1 OR ContraFuertes= 1";
        boolean vacio = false;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                vacio = true;
            }
            st.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al revisar si hay datos de colecta botánica "
            , "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en datos al revisar si hay datos de colecta botánica "
                , "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        return vacio;
    }
    
    public boolean hayColecta(){
        this.query = "SELECT ClaveColecta FROM TAXONOMIA_ColectaBotanica";
        boolean vacio = false;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                vacio = true;
            }
            st.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al revisar si hay datos de colecta botánica "
             , "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en datos al revisar si hay datos de colecta botánica "
               , "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        return vacio;
    }
    
    public boolean validarCapturaEspecie(String tabla, int sitioID) {
        this.query = "SELECT EspecieID FROM " + tabla + " WHERE SitioID= " + sitioID + " AND ifnull(EspecieID, '')= '' AND ifnull(ClaveColecta, '')= '' AND CondicionID = 1";
        boolean vacio = false;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                vacio = true;
            }
            st.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al revisar si se capturo clave de colecta para especie en identificación "
            , "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en datos al revisar si se capturo colecta botánica para especie en identificación"
                , "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        return vacio;
    }
 
    public boolean validarCapturaGenero(String tabla, int sitioID) {
        this.query = "SELECT GeneroID FROM " + tabla + " WHERE SitioID= " + sitioID + " AND ifnull(GeneroID, '')= '' AND ifnull(ClaveColecta, '')= ''";
        boolean vacio = false;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                vacio = true;
            }
            st.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al revisar si se capturo colecta botánica para género en identificación "
            , "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en datos al revisar si se capturo colecta botánica para género en identificación "
                , "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        return vacio;
    }
    
    public boolean validarTipoComponente(int sitioID) {
        this.query = "SELECT GeneroID FROM CARBONO_LongitudComponente WHERE SitioID= " + sitioID + " AND GeneroID > 0 AND GeneroID < 4";
        boolean vacio = false;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                vacio = true;
            }
            st.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! al revisar si se capturo colecta botánica para género en identificación ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en datos al revisar si se capturo colecta botánica para género en identificación ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
        return vacio;
    }
   
}
