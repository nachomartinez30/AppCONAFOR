package gob.conafor.taxonomia.mod;

import gob.conafor.conn.dat.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class CDCondicionTaxonomica {

    private String query;

    public List<CatEVigorArbolado> getVigorArbolado() {
        List<CatEVigorArbolado> listVigor = new ArrayList<>();
        query = "SELECT VigorID, Descripcion FROM CAT_TipoVigorArbolado";
        Connection conn = LocalConnectionCat.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                CatEVigorArbolado vigor = new CatEVigorArbolado();
                vigor.setVigorID(rs.getInt("VigorID"));
                vigor.setDescripcion(rs.getString("Descripcion"));
                listVigor.add(vigor);
            }
            st.close();
            rs.close();
            listVigor.add(0, null);
            return listVigor;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener el catálogo de tipo vigor arbolado",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos al generar el listado de tipos de vigor arbolado",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public List<CatETipoVigor> getVigorSotobosqueRepoblado() {
        List<CatETipoVigor> listVigor = new ArrayList<>();
        query = "SELECT VigorID, Descripcion FROM CAT_TipoVigorSotobosqueRepoblado";
        Connection conn = LocalConnectionCat.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                CatETipoVigor vigor = new CatETipoVigor();
                vigor.setVigorID(rs.getInt("VigorID"));
                vigor.setDescripcion(rs.getString("Descripcion"));
                listVigor.add(vigor);
            }
            st.close();
            rs.close();
            listVigor.add(0, null);
            return listVigor;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener el catálogo de tipo vigor de sotobosque repoblado ",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos al generar el listado de tipos de vigor de sotobosque y repoblado",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public List<CatENivelVigor> getNivelVigor() {
        List<CatENivelVigor> listNivelVigor = new ArrayList<>();
        query = "SELECT NivelVigorID, Clave, Descripcion FROM CAT_NivelVigor";
        Connection conn = LocalConnectionCat.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                CatENivelVigor nivelVigor = new CatENivelVigor();
                nivelVigor.setNivelVigorID(rs.getInt("NivelVigorID"));
                nivelVigor.setClave(rs.getString("Clave"));
                nivelVigor.setDescripcion(rs.getString("Descripcion"));
                listNivelVigor.add(nivelVigor);
            }
            st.close();
            rs.close();
            listNivelVigor.add(0, null);
            return listNivelVigor;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener el catálogo de nivel de vigor ",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos al generar el listado de nivel de vigor ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public List<CatEAgenteDanio> getAgenteDanio() {
        List<CatEAgenteDanio> listAgente = new ArrayList<>();
        query = "SELECT AgenteDanioID, Clave, Agente, Descripcion FROM CAT_AgenteDanio";
        Connection conn = LocalConnectionCat.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                CatEAgenteDanio agente = new CatEAgenteDanio();
                agente.setAgenteDanioID(rs.getInt("AgenteDanioID"));
                agente.setClave(rs.getString("Clave"));
                agente.setAgente(rs.getString("Agente"));
                agente.setDescripcion(rs.getString("Descripcion"));
                listAgente.add(agente);
            }
            st.close();
            rs.close();
            return listAgente;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener el catálogo de daños en sotobosque ",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos al generar el listado de tipos de daños de sotobosque",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public List<CatEFormaVida> getFormaVida() {
        List<CatEFormaVida> listFormaVida = new ArrayList<>();
        query = "SELECT FormaVidaID, Descripcion FROM CAT_TipoFormaVidaArbolado";
        System.out.println(query);
        Connection conn = LocalConnectionCat.getConnection();

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                CatEFormaVida fv = new CatEFormaVida();
                fv.setFormaVidaID(rs.getInt("FormaVidaID"));
                fv.setFormaVida(rs.getString("Descripcion"));
                listFormaVida.add(fv);
            }
            st.close();
            rs.close();
            listFormaVida.add(0, null);
            return listFormaVida;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener el catálogo de forma de vida ",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {

            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos al generar el listado de forma de vida de arbolado",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //Forma de vida de manglares
    public List<CatEFormaVidaRepobladoVM> getFormaVidaRepobladoVM() {
        List<CatEFormaVidaRepobladoVM> listFormaVida = new ArrayList<>();
        query = "SELECT FormaVidaRepobladoVMID, Descripcion FROM CAT_TipoFormaVidaRepobladoVM";
        Connection conn = LocalConnectionCat.getConnection();

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                CatEFormaVidaRepobladoVM fv = new CatEFormaVidaRepobladoVM();
                fv.setFormaVidaRepobladoVMID(rs.getInt("FormaVidaRepobladoVMID"));
                fv.setFormaVida(rs.getString("Descripcion"));
                listFormaVida.add(fv);
            }
            st.close();
            rs.close();
            listFormaVida.add(0, null);
            return listFormaVida;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener el catálogo de forma de vida de repoblado vegetacion menor",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos al generar el listado de forma de vida de  repoblado vegetacion menor ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public List<CatEFormaVidaZA> getFormaVidaZA() {
        List<CatEFormaVidaZA> listFormaVida = new ArrayList<>();
        query = "SELECT FormaVidaZAID, Morfotipo FROM CAT_TipoFormaVidaZA";
        Connection conn = LocalConnectionCat.getConnection();

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                CatEFormaVidaZA formaVida = new CatEFormaVidaZA();
                formaVida.setFormaVidaZAID(rs.getInt("FormaVidaZAID"));
                formaVida.setMorfotipo(rs.getString("Morfotipo"));
                listFormaVida.add(formaVida);
            }
            st.close();
            rs.close();
            listFormaVida.add(0, null);
            return listFormaVida;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener el catálogo de forma de vida de  zonas áridas ",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos al generar el listado de forma de vida de  zonas áridas ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public List<CatEFormaVidaZA> getFormaVidaVM() {
        List<CatEFormaVidaZA> listFormaVida = new ArrayList<>();
        query = "SELECT FormaVidaZAID, Morfotipo FROM CAT_TipoFormaVidaZA WHERE FormaVidaZAID < 15";
        Connection conn = LocalConnectionCat.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                CatEFormaVidaZA formaVida = new CatEFormaVidaZA();
                formaVida.setFormaVidaZAID(rs.getInt("FormaVidaZAID"));
                formaVida.setMorfotipo(rs.getString("Morfotipo"));
                listFormaVida.add(formaVida);
            }
            st.close();
            rs.close();
            listFormaVida.add(0, null);
            return listFormaVida;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener el catálogo de forma de vida de  zonas áridas ",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos al generar el listado de forma de vida de  zonas áridas ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public List<CatEFormaFuste> getFormaFuste() {
        List<CatEFormaFuste> listFormaFuste = new ArrayList<>();
        query = "SELECT FormaFusteID, Descripcion AS FormaFuste FROM CAT_TipoFormaFuste";
        Connection conn = LocalConnectionCat.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                CatEFormaFuste ff = new CatEFormaFuste();
                ff.setFormaFusteID(rs.getInt("FormaFusteID"));
                ff.setFormaFuste(rs.getString("FormaFuste"));
                listFormaFuste.add(ff);
            }
            st.close();
            rs.close();
            listFormaFuste.add(0, null);
            return listFormaFuste;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener el catálogo de forma fuste ",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {

            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos al generar el listado de forma fuste ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public List<CatECondicionArbolado> getCondicionArboladoArbol() {
        List<CatECondicionArbolado> listCondicionArbolado = new ArrayList<>();
        query = "SELECT CondicionID, Descripcion AS Condicion FROM CAT_CondicionArbolado";
        Connection conn = LocalConnectionCat.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                CatECondicionArbolado ca = new CatECondicionArbolado();
                ca.setCondicionID(rs.getInt("CondicionID"));
                ca.setCondicion(rs.getString("Condicion"));
                listCondicionArbolado.add(ca);
            }
            st.close();
            rs.close();
            listCondicionArbolado.add(0, null);
            return listCondicionArbolado;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener el catálogo de condicion arbolado ",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {

            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos al generar el listado de condicion de arbolado ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public List<CatECondicionArbolado> getCondicionArboladoLianas() {
        List<CatECondicionArbolado> listCondicionArbolado = new ArrayList<>();
        query = "SELECT CondicionID, Descripcion AS Condicion FROM CAT_CondicionArbolado WHERE CondicionID IN(1, 2)";
        Connection conn = LocalConnectionCat.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                CatECondicionArbolado ca = new CatECondicionArbolado();
                ca.setCondicionID(rs.getInt("CondicionID"));
                ca.setCondicion(rs.getString("Condicion"));
                listCondicionArbolado.add(ca);
            }
            st.close();
            rs.close();
            listCondicionArbolado.add(0, null);
            return listCondicionArbolado;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener el catálogo de condicion arbolado ",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos al generar el listado de condicion de arbolado ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public List<CatECondicionMuertoPie> getCondicionMuertoPie() {
        List<CatECondicionMuertoPie> listMuertoPie = new ArrayList<>();
        query = "SELECT MuertoPieID, Clave, Descripcion  FROM CAT_CondicionMuertoPie";
        Connection conn = LocalConnectionCat.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                CatECondicionMuertoPie ca = new CatECondicionMuertoPie();
                ca.setMuertoPieID(rs.getInt("MuertoPieID"));
                ca.setClave(rs.getString("Clave"));
                ca.setDescripcion(rs.getString("Descripcion"));
                listMuertoPie.add(ca);
            }
            st.close();
            rs.close();
            listMuertoPie.add(0, null);
            return listMuertoPie;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener el catalogo de condicion de muerto en pie ",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos al generar el listado de condicion de muerto en pie ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public List<CatECondicionVM> getCondicionVM() {
        List<CatECondicionVM> listCondicionVM = new ArrayList<>();
        query = "SELECT CondicionVMID, Descripcion FROM CAT_CondicionVM";
        Connection conn = LocalConnectionCat.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                CatECondicionVM condicion = new CatECondicionVM();
                condicion.setCondicionVMID(rs.getInt("CondicionVMID"));
                condicion.setDescripcion(rs.getString("Descripcion"));
                listCondicionVM.add(condicion);
            }
            st.close();
            rs.close();
            listCondicionVM.add(0, null);
            return listCondicionVM;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener el catalogo de grado de condicion vegetación menor ",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos al generar el listado condición de vegetación menor ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public List<CatEGradoPutrefaccionArbolado> getGradoPutrefaccion() {
        List<CatEGradoPutrefaccionArbolado> listGradoPutrefaccion = new ArrayList<>();
        query = "SELECT GradoPutrefaccionID, Clave, Tipo, Descripcion  FROM CAT_GradoPutrefaccionArbolado";
        Connection conn = LocalConnectionCat.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                CatEGradoPutrefaccionArbolado ca = new CatEGradoPutrefaccionArbolado();
                ca.setGradoPutrefaccionID(rs.getInt("GradoPutrefaccionID"));
                ca.setClave(rs.getString("Clave"));
                ca.setTipo("Tipo");
                ca.setDescripcion("Descripcion");
                listGradoPutrefaccion.add(ca);
            }
            st.close();
            rs.close();
            listGradoPutrefaccion.add(0, null);
            return listGradoPutrefaccion;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener el catalogo de grado de putrefaccion de arbolado ",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos al generar el listado grado de putrefaccion de arbolado ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public List<CatETipoTocon> getTipoTocon() {
        List<CatETipoTocon> listTipoTocon = new ArrayList<>();
        query = "SELECT TipoToconID, Clave, Descripcion FROM CAT_TipoTocon";
        Connection conn = LocalConnectionCat.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                CatETipoTocon tc = new CatETipoTocon();
                tc.setTipoToconID(rs.getInt("TipoToconID"));
                tc.setClave(rs.getString("Clave"));
                tc.setDescripcion(rs.getString("Descripcion"));
                listTipoTocon.add(tc);
            }
            st.close();
            rs.close();
            listTipoTocon.add(0, null);
            return listTipoTocon;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener el catálogo de tipos de tocon ",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {

            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos al generar el listado de tipos de tocon ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //Condicion de copa del arbolado
    public List<CatEPorcentajeArbolado> getPorcentajeArboladoCopa() {
        List<CatEPorcentajeArbolado> listPorcentaje = new ArrayList<>();
        query = "SELECT PorcentajeArboladoID, Clave, Descripcion FROM CAT_PorcentajeArbolado WHERE PorcentajeArboladoID < 22";
        Connection conn = LocalConnectionCat.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                CatEPorcentajeArbolado porcentaje = new CatEPorcentajeArbolado();
                porcentaje.setPorcentajeArboladoID(rs.getInt("PorcentajeArboladoID"));
                porcentaje.setClave(rs.getString("Clave"));
                porcentaje.setDescripcion(rs.getString("Descripcion"));
                listPorcentaje.add(porcentaje);
            }
            st.close();
            rs.close();
            listPorcentaje.add(0, null);
            return listPorcentaje;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener el catálogo de porcentajes de arbolado copa ",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos al generar el listado de porcentajes de arbolado copa ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public List<CatESeveridadZA> getSeveridadZA() {
        List<CatESeveridadZA> listSeveridad = new ArrayList<>();
        query = "SELECT SeveridadID, Descripcion FROM CAT_SeveridadZA";
        Connection conn = LocalConnectionCat.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                CatESeveridadZA severidad = new CatESeveridadZA();
                severidad.setSeveridadID(rs.getInt("SeveridadID"));
                severidad.setDescripcion(rs.getString("Descripcion"));
                listSeveridad.add(severidad);
            }
            st.close();
            rs.close();
            return listSeveridad;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener el catálogo de severidad de zonas áridas ",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos al generar el listado de severidad de zonas áridas ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public List<CatEPorcentajeArbolado> getPorcentajeArboladoCopa6() {
        List<CatEPorcentajeArbolado> listPorcentaje = new ArrayList<>();
        query = "SELECT PorcentajeArboladoID, Clave, Descripcion FROM CAT_PorcentajeArbolado WHERE PorcentajeArboladoID > 21";
        Connection conn = LocalConnectionCat.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                CatEPorcentajeArbolado porcentaje = new CatEPorcentajeArbolado();
                porcentaje.setPorcentajeArboladoID(rs.getInt("PorcentajeArboladoID"));
                porcentaje.setClave(rs.getString("Clave"));
                porcentaje.setDescripcion(rs.getString("Descripcion"));
                listPorcentaje.add(porcentaje);
            }
            st.close();
            rs.close();
            listPorcentaje.add(0, null);
            return listPorcentaje;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error! al obtener el catálogo de porcentajes de arbolado copa ",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos al generar el listado de porcentajes de arbolado copa ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public List<CatEExposicionLuzCopa> getExposicionLuzCopa() {
        List<CatEExposicionLuzCopa> listluzExposicion = new ArrayList<>();
        query = "SELECT ExposicionLuzID, Codigo, Descripcion FROM CAT_ExposicionLuzCopa";
        Connection conn = LocalConnectionCat.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                CatEExposicionLuzCopa exposicion = new CatEExposicionLuzCopa();
                exposicion.setExposicionLuzID(rs.getInt("ExposicionLuzID"));
                exposicion.setCodigo(rs.getString("Codigo"));
                exposicion.setDescripcion(rs.getString("Descripcion"));

                listluzExposicion.add(exposicion);
            }
            st.close();
            rs.close();
            listluzExposicion.add(0, null);
            return listluzExposicion;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                    null, "Error! al cerrar la base de datos al generar el listado de tipos de exposición luz ",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos al generar el listado de tipos de exposicion luz ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public List<CatEPosicionCopa> getPosicionCopa() {
        List<CatEPosicionCopa> listPosicionCopa = new ArrayList<>();
        query = "SELECT PosicionCopaID, PosicionCopa, Descripcion FROM CAT_PosicionCopa";
        Connection conn = LocalConnectionCat.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                CatEPosicionCopa posicion = new CatEPosicionCopa();
                posicion.setPosicionCopaID(rs.getInt("PosicionCopaID"));
                posicion.setPosicionCopa(rs.getString("PosicionCopa"));
                posicion.setDescripcion(rs.getString("Descripcion"));

                listPosicionCopa.add(posicion);
            }
            st.close();
            rs.close();
            listPosicionCopa.add(0, null);
            return listPosicionCopa;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                    null, "Error! al cerrar la base de datos al generar el listado de tipos de posición de copa ",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos al generar el listado de tipos de posicion de copa ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public List<CatEFormaGeometrica> getFormaGeometrica() {
        List<CatEFormaGeometrica> listFormaGeometrica = new ArrayList<>();
        query = "SELECT FormaGeometricaID, Descripcion FROM CAT_TipoFormaGeometrica";
        Connection conn = LocalConnectionCat.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                CatEFormaGeometrica forma = new CatEFormaGeometrica();
                forma.setFormaGeometricaID(rs.getInt("FormaGeometricaID"));
                forma.setDescripcion(rs.getString("Descripcion"));
                listFormaGeometrica.add(forma);
            }
            st.close();
            rs.close();
            listFormaGeometrica.add(0, null);
            return listFormaGeometrica;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                    null, "Error! al cerrar la base de datos al generar el listado de tipos de formas geométricas ",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos al generar el listado de tipos de formas geométricas ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public List getDensidadFollaje() {
        List<CatEDensidadFollaje> listDensidad = new ArrayList<>();
        query = "SELECT DensidadFollajeID, Descripcion FROM CAT_DensidadFollaje";
        Connection conn = LocalConnectionCat.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                CatEDensidadFollaje densidad = new CatEDensidadFollaje();
                densidad.setDensidadFollajeID(rs.getInt("DensidadFollajeID"));
                densidad.setDescripcion(rs.getString("Descripcion"));
                listDensidad.add(densidad);
            }
            st.close();
            rs.close();
            listDensidad.add(0, null);
            return listDensidad;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                    null, "Error! al cerrar la base de datos al generar el listado de tipos de densidad de follaje ",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos al generar el listado de tipos de densidad de follaje ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public List<CatEFormaCrecimiento> getFormaCrecimiento() {
        List<CatEFormaCrecimiento> listFormaCrecimiento = new ArrayList<>();
        query = "SELECT FormaCrecimientoID, Descripcion FROM CAT_TipoFormaCrecimiento";
        Connection conn = LocalConnectionCat.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                CatEFormaCrecimiento formaCrecimiento = new CatEFormaCrecimiento();
                formaCrecimiento.setFormaCrecimientoID(rs.getInt("FormaCrecimientoID"));
                formaCrecimiento.setDescripcion(rs.getString("Descripcion"));
                listFormaCrecimiento.add(formaCrecimiento);
            }
            st.close();
            rs.close();
            listFormaCrecimiento.add(0, null);
            return listFormaCrecimiento;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                    null, "Error! al cerrar la base de datos al generar el listado de tipos de formas de crecimiento ",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos al generar el listado de tipos de densidad de follaje ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public List<CatEDensidadColonia> getDensidadColonia() {
        List<CatEDensidadColonia> listDensidadColonia = new ArrayList<>();
        query = "SELECT DensidadColoniaID, Descripcion FROM CAT_DensidadColonia";
        Connection conn = LocalConnectionCat.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                CatEDensidadColonia densidadColonia = new CatEDensidadColonia();
                densidadColonia.setDensidadColoniaID(rs.getInt("DensidadColoniaID"));
                densidadColonia.setDescripcion(rs.getString("Descripcion"));
                listDensidadColonia.add(densidadColonia);
            }
            st.close();
            rs.close();
            listDensidadColonia.add(0, null);
            return listDensidadColonia;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                    null, "Error! al cerrar la base de datos al generar el listado de densidad de colonia ",
                    "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos al generar el listado de tipos de densidad de follaje ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
}
