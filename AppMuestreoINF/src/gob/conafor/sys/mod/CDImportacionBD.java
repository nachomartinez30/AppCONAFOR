package gob.conafor.sys.mod;

import gob.conafor.conn.dat.ExternalConnection;
import gob.conafor.conn.dat.LocalConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class CDImportacionBD {

    private String querySelect;
    private String queryInsert;
    private String queryDelete;
    private Connection baseDatosLocal;
    private Connection baseDatosExterna;
    private Statement sqlExterno;
    private Statement sqlLocal;
    private int upmIDExterno;
    private int upmIDLocal;

    public void validarRepetidos(String ruta) {
        this.querySelect = "SELECT UPMID FROM UPM_UPM";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            this.sqlLocal = this.baseDatosLocal.createStatement();
            ResultSet rsExterno = sqlExterno.executeQuery(this.querySelect);
            ResultSet rsLocal = sqlLocal.executeQuery(this.querySelect);
            while (rsExterno.next()) {
                this.upmIDExterno = rsExterno.getInt("UPMID");
                while (rsLocal.next()) {
                    this.upmIDLocal = rsLocal.getInt("UPMID");
                    if (this.upmIDExterno == this.upmIDLocal) {
                        Object[] opciones = {"Si", "No"};
                        int respuesta = JOptionPane.showOptionDialog(null, "El UPMID: " + this.upmIDExterno + " ya se encuentra en la base de datos local, ¿desea reeplazarlo?",
                                "Importación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[1]);
                        if (respuesta == JOptionPane.YES_OPTION) {
                            eliminarRepetido(upmIDLocal);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo revisar la importacion de la tabla UPM_UPM", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al revisar la importación de la tabla UPM_UPM", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public boolean continuarSinRepetidos(String ruta, int upmID) {
        this.querySelect = "SELECT UPMID FROM UPM_UPM";
        this.baseDatosLocal = LocalConnection.getConnection();
        boolean hayRepetidos = false;
        try {
            this.sqlLocal = this.baseDatosLocal.createStatement();
            ResultSet rsLocal = sqlLocal.executeQuery(this.querySelect);
            while (rsLocal.next()) {
                this.upmIDLocal = rsLocal.getInt("UPMID");
                if (upmID == this.upmIDLocal) {
                    hayRepetidos = false;
                } else {
                    hayRepetidos = true;
                }
            }
            return hayRepetidos;
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo revisar la importacion de la tabla UPM_UPM", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return false;
        } finally {
            try {
                baseDatosLocal.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos al revisar la importación de la tabla UPM_UPM", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void eliminarRepetido(int upmID) {
        this.queryDelete = "DELETE FROM UPM_UPM WHERE UPMID = " + upmID;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(this.queryDelete);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo eliminar la información del UPMID repetido ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos  al eliminar el UPMID repetido",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //1
    public void importarUPM_UPM(String ruta) {
        this.querySelect = "SELECT UPMID, FechaInicio, FechaFin, TipoUPMID, Altitud, PendienteRepresentativa, "
                + "FisiografiaID, ExposicionID, Predio, Paraje, TipoTenenciaID, Accesible, GradosLatitud, MinutosLatitud, "
                + "SegundosLatitud, GradosLongitud, MinutosLongitud, SegundosLongitud, Datum, ErrorPresicion, "
                + "Azimut, Distancia, TipoInaccesibilidadID, OtroTipoInaccesibilidad, ExplicacionInaccesibilidad, "
                + "InformacionContacto FROM UPM_UPM";
        Integer tipoInaccesibilidadID = null;
        Integer UPMID = null;
        String fechaInicio = null;
        String fechaFin = null;
        Integer tipoUpmID = null;
        Float altitud = null;
        Integer pendiente = null;
        Integer fisiografiaID = null;
        Integer exposicionID = null;
        String predio = null;
        String paraje = null;
        Integer tipoTenenciaID = null;
        Integer accesible = null;
        Integer gradosLatitud = null;
        Integer minutosLatitud = null;
        Float segundosLatitud = null;
        Integer gradosLongitud = null;
        Integer minutosLongitud = null;
        Float segundosLongitud = null;
        String datum = null;
        Integer errorPresicion = null;
        Integer azimut = null;
        Float distancia = null;
        String otroTipoInaccesibilidad = null;
        String explicacionInaccesibilidad = null;
        Integer informacionContacto = null;
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                if (rs.getObject("UPMID") != null) {
                    UPMID = rs.getInt("UPMID");
                }
                if (rs.getObject("FechaInicio") != null) {
                    fechaInicio = rs.getString("FechaInicio");
                }
                if (rs.getObject("FechaFin") != null) {
                    fechaFin = rs.getString("FechaFin");
                }
                if (rs.getObject("TipoUPMID") != null) {
                    tipoUpmID = rs.getInt("TipoUPMID");
                }
                if (rs.getObject("Altitud") != null) {
                    altitud = rs.getFloat("Altitud");
                }
                if (rs.getObject("PendienteRepresentativa") != null) {
                    pendiente = rs.getInt("PendienteRepresentativa");
                }
                if (rs.getObject("FisiografiaID") != null) {
                    fisiografiaID = rs.getInt("FisiografiaID");
                }
                if (rs.getObject("ExposicionID") != null) {
                    exposicionID = rs.getInt("ExposicionID");
                }
                if (rs.getObject("Predio") != null) {
                    predio = rs.getString("Predio");
                }
                if (rs.getObject("Paraje") != null) {
                    paraje = rs.getString("Paraje");
                }
                if (rs.getObject("TipoTenenciaID") != null) {
                    tipoTenenciaID = rs.getInt("TipoTenenciaID");
                }
                if (rs.getObject("Accesible") != null) {
                    accesible = rs.getInt("Accesible");
                }
                if (rs.getObject("GradosLatitud") != null) {
                    gradosLatitud = rs.getInt("GradosLatitud");
                }
                if (rs.getObject("MinutosLatitud") != null) {
                    minutosLatitud = rs.getInt("MinutosLatitud");
                }
                if (rs.getObject("SegundosLatitud") != null) {
                    segundosLatitud = rs.getFloat("SegundosLatitud");
                }
                if (rs.getObject("GradosLongitud") != null) {
                    gradosLongitud = rs.getInt("GradosLongitud");
                }
                if (rs.getObject("MinutosLongitud") != null) {
                    minutosLongitud = rs.getInt("MinutosLongitud");
                }
                if (rs.getObject("SegundosLongitud") != null) {
                    segundosLongitud = rs.getFloat("SegundosLongitud");
                }
                if (rs.getObject("Datum") != null) {
                    datum = rs.getString("Datum");
                }
                if (rs.getObject("ErrorPresicion") != null) {
                    errorPresicion = rs.getInt("ErrorPresicion");
                }
                if (rs.getObject("Azimut") != null) {
                    azimut = rs.getInt("Azimut");
                }
                if (rs.getObject("Distancia") != null) {
                    distancia = rs.getFloat("Distancia");
                }
                if (rs.getObject("OtroTipoInaccesibilidad") != null) {
                    otroTipoInaccesibilidad = rs.getString("OtroTipoInaccesibilidad");
                }
                if (rs.getObject("ExplicacionInaccesibilidad") != null) {
                    explicacionInaccesibilidad = rs.getString("ExplicacionInaccesibilidad");
                }
                if (rs.getObject("InformacionContacto") != null) {
                    informacionContacto = rs.getInt("InformacionContacto");
                }
                if (rs.getObject("TipoInaccesibilidadID") != null) {
                    tipoInaccesibilidadID = rs.getInt("TipoInaccesibilidadID");
                }

                ps.executeUpdate("INSERT INTO UPM_UPM(UPMID, FechaInicio, FechaFin, TipoUPMID, Altitud, PendienteRepresentativa, "
                        + "FisiografiaID, ExposicionID, 'Predio', 'Paraje', TipoTenenciaID, Accesible, GradosLatitud, MinutosLatitud, "
                        + "SegundosLatitud, GradosLongitud, MinutosLongitud, SegundosLongitud, Datum, ErrorPresicion, "
                        + "Azimut, Distancia, TipoInaccesibilidadID, OtroTipoInaccesibilidad, ExplicacionInaccesibilidad, "
                        + "InformacionContacto)VALUES(" + UPMID + ", '" + fechaInicio + "', '" + fechaFin + "', " + tipoUpmID + ", " + altitud + ", " + pendiente + " , " + fisiografiaID + ", " + exposicionID
                        + ", '" + predio + "', '" + paraje + "', " + tipoTenenciaID + ", " + accesible + ", " + gradosLatitud + ", " + minutosLatitud + ", " + segundosLatitud + ", " + gradosLongitud + ", " + minutosLongitud + ", " + segundosLongitud + " , '"
                        + datum + "', " + errorPresicion + ", " + azimut + ", " + distancia + ", " + tipoInaccesibilidadID + ", '" + otroTipoInaccesibilidad + "', '" + explicacionInaccesibilidad + "', " + informacionContacto + ")");
                this.baseDatosLocal.commit();
                tipoInaccesibilidadID = null;
                UPMID = null;
                fechaInicio = null;
                fechaFin = null;
                tipoUpmID = null;
                altitud = null;
                pendiente = null;
                fisiografiaID = null;
                exposicionID = null;
                predio = null;
                paraje = null;
                tipoTenenciaID = null;
                accesible = null;
                gradosLatitud = null;
                minutosLatitud = null;
                segundosLatitud = null;
                gradosLongitud = null;
                minutosLongitud = null;
                segundosLongitud = null;
                datum = null;
                errorPresicion = null;
                azimut = null;
                distancia = null;
                otroTipoInaccesibilidad = null;
                explicacionInaccesibilidad = null;
                informacionContacto = null;
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla UPM_UPM", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla UPM_UPM", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

//2
    public void importarPC(String ruta) {
        this.querySelect = "SELECT UPMID, Descripcion, Paraje, GradosLatitud, MinutosLatitud, SegundosLatitud, GradosLongitud, "
                + "MinutosLongitud, SegundosLongitud, ErrorPresicion, Datum, Azimut, Distancia FROM PC_PuntoControl";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer upmID = rs.getInt("UPMID");
                String descripcion = rs.getString("Descripcion");
                String paraje = rs.getString("Paraje");
                Integer gradosLatitud = rs.getInt("GradosLatitud");
                Integer minutosLatitud = rs.getInt("MinutosLatitud");
                Float segundosLatitud = rs.getFloat("SegundosLatitud");
                Integer gradosLongitud = rs.getInt("GradosLongitud");
                Integer minutosLongitud = rs.getInt("MinutosLongitud");
                Float segundosLongitud = rs.getFloat("SegundosLongitud");
                Integer errorPresicion = rs.getInt("ErrorPresicion");
                String datum = rs.getString("Datum");
                Integer azimut = rs.getInt("Azimut");
                Float distancia = rs.getFloat("Distancia");
                ps.executeUpdate("INSERT INTO PC_PuntoControl(UPMID, Descripcion, Paraje, GradosLatitud, MinutosLatitud, SegundosLatitud, GradosLongitud, "
                        + "MinutosLongitud, SegundosLongitud, ErrorPresicion, Datum, Azimut, Distancia)VALUES(" + upmID + ", '" + descripcion + "', '" + paraje + "', " + gradosLatitud
                        + ", " + minutosLatitud + ", " + segundosLatitud + ", " + gradosLongitud + ", " + minutosLongitud + ", " + segundosLongitud + ", " + errorPresicion + ", '"
                        + datum + "', " + azimut + ", " + distancia + ")");
                this.baseDatosLocal.commit();
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla PC_PuntoControl", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla PC_PuntoControl", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

//3
    public void importarAccesibilidadPC(String ruta) {
        this.querySelect = "SELECT UPMID, MedioTransporteID, ViaAccesibilidadID, Distancia, CondicionAccesibilidadID FROM PC_Accesibilidad";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer upmID = rs.getInt("UPMID");
                Integer medioTransporteID = rs.getInt("MedioTransporteID");
                Integer viaAccesibilidadID = rs.getInt("ViaAccesibilidadID");
                Float distancia = rs.getFloat("Distancia");
                Integer condicionAccesibilidadID = rs.getInt("CondicionAccesibilidadID");
                ps.executeUpdate("INSERT INTO PC_Accesibilidad(UPMID, MedioTransporteID, ViaAccesibilidadID, Distancia, CondicionAccesibilidadID)"
                        + "VALUES(" + upmID + ", " + medioTransporteID + ", " + viaAccesibilidadID + ", " + distancia + ", " + condicionAccesibilidadID + ")");
                this.baseDatosLocal.commit();
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla PC_Accesibilidad", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla PC_Accesibilidad", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

//4
    public void importarSitios(String ruta) {
        this.querySelect = "SELECT UPMID, SitioID, Sitio, SenialGPS, GradosLatitud, MinutosLatitud, SegundosLatitud, GradosLongitud, MinutosLongitud, "
                + "SegundosLongitud, ErrorPresicion, EvidenciaMuestreo, Datum, Azimut, Distancia, SitioAccesible, TipoInaccesibilidad, ExplicacionInaccesibilidad, CoberturaForestal, "
                + "Condicion, ClaveSerieV, FaseSucecional, ArbolFuera, Ecotono, CondicionPresenteCampo, CondicionEcotono, RepobladoFuera, PorcentajeRepoblado, "
                + "SotoBosqueFuera, PorcentajeSotoBosqueFuera, Observaciones, HipsometroBrujula, CintaClinometroBrujula, Cuadrante1, Cuadrante2, Cuadrante3, "
                + "Cuadrante4, Distancia1, Distancia2, Distancia3, Distancia4 FROM SITIOS_Sitio";
        Integer evidenciaMuestreo = null;
        Integer condicion = null;
        Integer faseSucecional = null;
        Integer azimut = null;
        Float distancia = null;
        Integer tipoInaccesibilidad = null;
        String explicacionInaccesibilidad = null;
        Integer porcentajeRepoblado = null;
        Integer porcentajeSotoBosque = null;

        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer upmID = rs.getInt("UPMID");
                Integer sitioID = rs.getInt("SitioID");
                Integer sitio = rs.getInt("Sitio");
                Integer senialGPS = rs.getInt("SenialGPS");
                Integer gradosLatitud = rs.getInt("GradosLatitud");
                Integer minutosLatitud = rs.getInt("MinutosLatitud");
                Float segundosLatitud = rs.getFloat("SegundosLatitud");
                Integer gradosLongitud = rs.getInt("GradosLongitud");
                Integer minutosLongitud = rs.getInt("MinutosLongitud");
                Float segundosLongitud = rs.getFloat("SegundosLongitud");
                Integer errorPresicion = rs.getInt("ErrorPresicion");
                if (rs.getObject("EvidenciaMuestreo") != null) {
                    evidenciaMuestreo = rs.getInt("EvidenciaMuestreo");
                }
                String datum = rs.getString("Datum");

                Integer sitioAccesible = rs.getInt("SitioAccesible");

                Integer coberturaForestal = rs.getInt("CoberturaForestal");
                if (rs.getObject("Condicion") != null) {
                    condicion = rs.getInt("Condicion");
                }
                Integer claveSerieV = rs.getInt("ClaveSerieV");
                if (rs.getObject("FaseSucecional") != null) {
                    faseSucecional = rs.getInt("FaseSucecional");
                }
                if (rs.getObject("Azimut") != null) {
                    azimut = rs.getInt("Azimut");
                }
                if (rs.getObject("Distancia") != null) {
                    distancia = rs.getFloat("Distancia");
                }
                if (rs.getObject("TipoInaccesibilidad") != null) {
                    tipoInaccesibilidad = rs.getInt("TipoInaccesibilidad");
                }
                //System.out.println(rs.getObject("ExplicacionInaccesibilidad"));
                if (rs.getObject("ExplicacionInaccesibilidad")!= null) {
                    explicacionInaccesibilidad = rs.getString("ExplicacionInaccesibilidad");
                }
                if (rs.getObject("PorcentajeRepoblado") != null) {
                    porcentajeRepoblado = rs.getInt("PorcentajeRepoblado");
                }
                if (rs.getObject("PorcentajeSotoBosqueFuera") != null) {
                    porcentajeSotoBosque = rs.getInt("PorcentajeSotoBosqueFuera");
                }
                Integer arbolFuera = rs.getInt("ArbolFuera");
                Integer ecotono = rs.getInt("Ecotono");
                String condicionPresente = rs.getString("CondicionPresenteCampo");
                String condicionEcotono = rs.getString("CondicionEcotono");
                Integer repobladoFuera = rs.getInt("RepobladoFuera");
                Integer sotoBosqueFuera = rs.getInt("SotoBosqueFuera");
                String observaciones = rs.getString("Observaciones");
                Integer hipsometroBrujula = rs.getInt("HipsometroBrujula");
                Integer cintaClinometroBrujula = rs.getInt("CintaClinometroBrujula");
                Integer cuadrante1 = rs.getInt("Cuadrante1");
                Integer cuadrante2 = rs.getInt("Cuadrante2");
                Integer cuadrante3 = rs.getInt("Cuadrante3");
                Integer cuadrante4 = rs.getInt("Cuadrante4");
                Float distancia1 = rs.getFloat("Distancia1");
                Float distancia2 = rs.getFloat("Distancia2");
                Float distancia3 = rs.getFloat("Distancia3");
                Float distancia4 = rs.getFloat("Distancia4");
                ps.executeUpdate("INSERT INTO SITIOS_Sitio(UPMID, SitioID, Sitio, SenialGPS, GradosLatitud, MinutosLatitud, SegundosLatitud, GradosLongitud, MinutosLongitud, "
                        + "SegundosLongitud, ErrorPresicion, EvidenciaMuestreo, Datum, Azimut, Distancia, SitioAccesible, TipoInaccesibilidad, ExplicacionInaccesibilidad, CoberturaForestal, "
                        + "Condicion, ClaveSerieV, FaseSucecional, ArbolFuera, Ecotono, CondicionPresenteCampo, CondicionEcotono, RepobladoFuera, PorcentajeRepoblado, "
                        + "SotoBosqueFuera, PorcentajeSotoBosqueFuera, Observaciones, HipsometroBrujula, CintaClinometroBrujula, Cuadrante1, Cuadrante2, Cuadrante3, "
                        + "Cuadrante4, Distancia1, Distancia2, Distancia3, Distancia4)VALUES(" + upmID + ", " + sitioID + ", " + sitio + ", " + senialGPS + ", " + gradosLatitud + ", " + minutosLatitud + ", " + segundosLatitud
                        + ", " + gradosLongitud + ", " + minutosLongitud + ", " + segundosLongitud + ", " + errorPresicion + ", " + evidenciaMuestreo + ", '" + datum + "', " + azimut + ", " + distancia
                        + ", " + sitioAccesible + ", " + tipoInaccesibilidad + ", '" + explicacionInaccesibilidad + "', " + coberturaForestal + ", " + condicion + ", " + claveSerieV + ", " + faseSucecional + ", " + arbolFuera
                        + ", " + ecotono + ", '" + condicionPresente + "', '" + condicionEcotono + "', " + repobladoFuera + ", " + porcentajeRepoblado + ", " + sotoBosqueFuera + ", " + porcentajeSotoBosque
                        + ", '" + observaciones + "', " + hipsometroBrujula + ", " + cintaClinometroBrujula + ", " + cuadrante1 + ", " + cuadrante2 + ", " + cuadrante3 + ", " + cuadrante4 + ", " + distancia1
                        + ", " + distancia2 + ", " + distancia3 + ", " + distancia4 + ")");
                this.baseDatosLocal.commit();
                evidenciaMuestreo = null;
                condicion = null;
                faseSucecional = null;
                azimut = null;
                distancia = null;
                tipoInaccesibilidad = null;
                explicacionInaccesibilidad = null;
                porcentajeRepoblado = null;
                porcentajeSotoBosque = null;
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla SITIOS_Sitio", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla SITIOS_Sitio", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

//5
    public void importarSitiosCoberturaSuelo(String ruta) {
        this.querySelect = "SELECT CoberturaID, SitioID, Gramineas, Helechos, Musgos, Liquenes, Hierbas, Roca, SueloDesnudo, Hojarasca, Grava, Otros FROM SITIOS_CoberturaSuelo";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer coberturaID = rs.getInt("CoberturaID");
                Integer sitioID = rs.getInt("SitioID");
                Integer gramineas = rs.getInt("Gramineas");
                Integer helechos = rs.getInt("Helechos");
                Integer musgos = rs.getInt("Musgos");
                Integer liquenes = rs.getInt("Liquenes");
                Integer hierbas = rs.getInt("Hierbas");
                Integer rocas = rs.getInt("Roca");
                Integer sueloDesnudo = rs.getInt("SueloDesnudo");
                Integer hojarasca = rs.getInt("Hojarasca");
                Integer grava = rs.getInt("Grava");
                Integer otros = rs.getInt("Otros");
                ps.executeUpdate("INSERT INTO SITIOS_CoberturaSuelo(CoberturaID, SitioID, Gramineas, Helechos, Musgos, Liquenes, Hierbas, Roca, SueloDesnudo, Hojarasca, Grava, Otros)"
                        + "VALUES(" + coberturaID + ", " + sitioID + ", " + gramineas + ", " + helechos + ", " + musgos + ", " + liquenes + ", " + hierbas + ", " + rocas
                        + ", " + sueloDesnudo + ", " + hojarasca + ", " + grava + ", " + otros + ")");
                this.baseDatosLocal.commit();
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla SITIOS_CoberturaSuelo", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla SITIOS_CoberturaSuelo", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

//5
    public void importarFotografiaHemisferica(String ruta) {
        this.querySelect = "SELECT FotografiaHemisfericaID, SitioID, CoberturaArborea, TomaFotografia, Hora, DeclinacionMagnetica FROM SITIOS_FotografiaHemisferica";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);

            while (rs.next()) {
                Integer fotografiaHemisfericaID = rs.getInt("FotografiaHemisfericaID");
                Integer sitioID = rs.getInt("SitioID");
                Integer coberturaArborea = rs.getInt("CoberturaArborea");
                Integer tomaFotografia = rs.getInt("TomaFotografia");
                String hora = rs.getString("Hora");
                Integer declinacionMagnetica = rs.getInt("DeclinacionMagnetica");
                ps.executeUpdate("INSERT INTO SITIOS_FotografiaHemisferica(FotografiaHemisfericaID, SitioID, CoberturaArborea, TomaFotografia, Hora, DeclinacionMagnetica)"
                        + "VALUES(" + fotografiaHemisfericaID + ", " + sitioID + ", " + coberturaArborea + ", " + tomaFotografia + ", '" + hora + "', " + declinacionMagnetica + ")");
                this.baseDatosLocal.commit();
                ps.close();
            }

            this.sqlExterno.close();
            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla SITIOS_FotografiaHemisferica", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla SITIOS_FotografiaHemisferica", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //6
    public void importarTransponder(String ruta) {
        this.querySelect = "SELECT TransponderID, SitioID, TipoColocacionID, Especifique, Observaciones FROM SITIOS_Transponder";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {

                Integer transponderID = rs.getInt("TransponderID");
                Integer sitioID = rs.getInt("SitioID");
                Integer tipoColocacionID = rs.getInt("TipoColocacionID");
                String especifique = rs.getString("Especifique");
                String observaciones = rs.getString("Observaciones");
                ps.executeUpdate("INSERT INTO SITIOS_Transponder(TransponderID,SitioID, TipoColocacionID, Especifique, Observaciones)"
                        + "VALUES(" + transponderID + ", " + sitioID + ", " + tipoColocacionID + ", '" + especifique + "', '" + observaciones + "')");
                this.baseDatosLocal.commit();
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla SITIOS_Transponder", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla SITIOS_Transponder", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

//7
    public void importarParametrosFisicoQuimicos(String ruta) {
        this.querySelect = "SELECT ParametrosFQID, SitioID, TipoAgua, Salinidad, Temperatura, ConductividadElectrica, Ph, PotencialRedox, Profundidad, Observaciones FROM "
                + "SITIOS_ParametrosFisicoQuimicos";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer parametrosFQID = rs.getInt("ParametrosFQID");
                Integer sitioID = rs.getInt("SitioID");
                Integer tipoAgua = rs.getInt("TipoAgua");
                Float salinidad = rs.getFloat("Salinidad");
                Float temperatura = rs.getFloat("Temperatura");
                Float conductividadElectrica = rs.getFloat("ConductividadElectrica");
                Float ph = rs.getFloat("Ph");
                Float potencialRedox = rs.getFloat("PotencialRedox");
                Float profundidad = rs.getFloat("Profundidad");
                String observaciones = rs.getString("Observaciones");
                ps.executeUpdate("INSERT INTO SITIOS_ParametrosFisicoQuimicos(ParametrosFQID, SitioID, TipoAgua, Salinidad, Temperatura, ConductividadElectrica, Ph, PotencialRedox, "
                        + "Profundidad, Observaciones)VALUES(" + parametrosFQID + ", " + sitioID + ", " + tipoAgua + ", " + salinidad + ", " + temperatura + ", " + conductividadElectrica + ", " + ph
                        + ", " + potencialRedox + ", " + profundidad + ", '" + observaciones + "')");
                this.baseDatosLocal.commit();
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla SITIOS_ParametrosFisicoQuimicos", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla SITIOS_ParametrosFisicoQuimicos", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //8
    public void importarSueloCanalillo(String ruta) {
        this.querySelect = "SELECT CanalilloID, SitioID, Numero, Ancho, Profundidad FROM SUELO_Canalillo";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer canalilloID = rs.getInt("CanalilloID");
                Integer sitioID = rs.getInt("SitioID");
                Integer numero = rs.getInt("Numero");
                Float ancho = rs.getFloat("Ancho");
                Float profundidad = rs.getFloat("Profundidad");
                ps.executeUpdate("INSERT INTO SUELO_Canalillo(CanalilloID, SitioID, Numero, Ancho, Profundidad)VALUES(" + canalilloID + ", " + sitioID + ", " + numero + ", " + ancho + ", " + profundidad + ")");
                this.baseDatosLocal.commit();
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla SUELO_Canalillo", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla SUELO_Canalillo", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //9
    public void importarSueloCarcava(String ruta) {
        this.querySelect = "SELECT CarcavaID, SitioID, Numero, Ancho, Profundidad FROM SUELO_Carcava";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer carcavaID = rs.getInt("CarcavaID");
                Integer sitioID = rs.getInt("SitioID");
                Integer numero = rs.getInt("Numero");
                Float ancho = rs.getFloat("Ancho");
                Float profundidad = rs.getFloat("Profundidad");
                ps.executeUpdate("INSERT INTO SUELO_Carcava(CarcavaID, SitioID, Numero, Ancho, Profundidad)VALUES(" + carcavaID + ", " + sitioID + ", " + numero + ", " + ancho + ", " + profundidad + ")");
                this.baseDatosLocal.commit();
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla SUELO_Carcava", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla SUELO_Carcava", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //10
    public void importarSueloCobertura(String ruta) {
        this.querySelect = "SELECT CoberturaSueloID, SitioID, Transecto, Pendiente FROM SUELO_CoberturaSuelo";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer coberturaSueloID = rs.getInt("CoberturaSueloID");
                Integer sitioID = rs.getInt("SitioID");
                Integer transecto = rs.getInt("Transecto");
                Integer pendiente = rs.getInt("Pendiente");
                ps.executeUpdate("INSERT INTO SUELO_CoberturaSuelo(CoberturaSueloID, SitioID, Transecto, Pendiente)VALUES(" + coberturaSueloID + ", " + sitioID + ", " + transecto + ", " + pendiente + ")");
                this.baseDatosLocal.commit();
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla SUELO_CoberturaSuelo", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla SUELO_CoberturaSuelo", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //11
    public void importarSueloCostras(String ruta) {
        this.querySelect = "SELECT CostrasID, SitioID, Numero, Diametro FROM SUELO_Costras";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer costrasID = rs.getInt("CostrasID");
                Integer sitioID = rs.getInt("SitioID");
                Integer numero = rs.getInt("Numero");
                Float diametro = rs.getFloat("Diametro");
                ps.executeUpdate("INSERT INTO SUELO_Costras(CostrasID, SitioID, Numero, Diametro)VALUES(" + costrasID + ", " + sitioID + ", " + numero + ", " + diametro + ")");
                this.baseDatosLocal.commit();
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla SUELO_Costras", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla SUELO_Costras", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //12
    public void importarSueloDeformacionViento(String ruta) {
        this.querySelect = "SELECT DeformacionVientoID, SitioID, Medicion, Altura, Diametro, Longitud, Distancia, Azimut FROM SUELO_DeformacionViento";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer deformacionVientoID = rs.getInt("DeformacionVientoID");
                Integer sitioID = rs.getInt("SitioID");
                Integer medicion = rs.getInt("Medicion");
                Float altura = rs.getFloat("Altura");
                Float diametro = rs.getFloat("Diametro");
                Float longitud = rs.getFloat("Longitud");
                Float distancia = rs.getFloat("Distancia");
                Integer azimut = rs.getInt("Azimut");
                ps.executeUpdate("INSERT INTO SUELO_DeformacionViento(DeformacionVientoID, SitioID, Medicion, Altura, Diametro, Longitud, Distancia, Azimut)VALUES(" + deformacionVientoID + ", " + sitioID + ", " + medicion + ", " + altura + ", " + diametro + ", " + longitud + ", " + distancia + ", " + azimut + ")");
                this.baseDatosLocal.commit();
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla SUELO_DeformacionViento", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla SUELO_DeformacionViento", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //13
    public void importarSueloErosionHidricaCanalillo(String ruta) {
        this.querySelect = "SELECT ErosionCanalilloID, SitioID, Medicion, Profundidad, Ancho, Distancia, Azimut FROM SUELO_ErosionHidricaCanalillo";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer erosionCanalilloID = rs.getInt("ErosionCanalilloID");
                Integer sitioID = rs.getInt("SitioID");
                Integer medicion = rs.getInt("Medicion");
                Float profundidad = rs.getFloat("Profundidad");
                Float ancho = rs.getFloat("Ancho");
                Float distancia = rs.getFloat("Distancia");
                Integer azimut = rs.getInt("Azimut");
                ps.executeUpdate("INSERT INTO SUELO_ErosionHidricaCanalillo(ErosionCanalilloID, SitioID, Medicion, Profundidad, Ancho, Distancia, Azimut)"
                        + "VALUES(" + erosionCanalilloID + ", " + sitioID + ", " + medicion + ", " + profundidad + ", " + ancho + ", " + distancia + ", " + azimut + ")");
                this.baseDatosLocal.commit();
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla SUELO_ErosionHidricaCanalillo", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla SUELO_ErosionHidricaCanalillo", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //14
    public void importarSueloErosionHidricaCarcava(String ruta) {
        this.querySelect = "SELECT ErosionCarcavaID,SitioID, Medicion, Profundidad, Ancho, Distancia, Azimut FROM SUELO_ErosionHidricaCarcava";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer erosionCarcavaID = rs.getInt("ErosionCarcavaID");
                Integer sitioID = rs.getInt("SitioID");
                Integer medicion = rs.getInt("Medicion");
                Float profundidad = rs.getFloat("Profundidad");
                Float ancho = rs.getFloat("Ancho");
                Float distancia = rs.getFloat("Distancia");
                Integer azimut = rs.getInt("Azimut");
                ps.executeUpdate("INSERT INTO SUELO_ErosionHidricaCarcava(ErosionCarcavaID,SitioID, Medicion, Profundidad, Ancho, Distancia, Azimut)"
                        + "VALUES(" + erosionCarcavaID + ", " + sitioID + ", " + medicion + ", " + profundidad + ", " + ancho + ", " + distancia + ", " + azimut + ")");
                this.baseDatosLocal.commit();
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla SUELO_ErosionHidricaCarcava", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla SUELO_ErosionHidricaCarcava", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //15
    public void importarSueloErosionLaminar(String ruta) {
        this.querySelect = "SELECT ErosionLaminarID, SitioID, Numero, Ancho, Largo FROM SUELO_ErosionLaminar";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer erosionLaminarID = rs.getInt("ErosionLaminarID");
                Integer sitioID = rs.getInt("SitioID");
                Integer numero = rs.getInt("Numero");
                Float ancho = rs.getFloat("Ancho");
                Float largo = rs.getFloat("Largo");
                ps.executeUpdate("INSERT INTO SUELO_ErosionLaminar(ErosionLaminarID, SitioID, Numero, Ancho, Largo)VALUES(" + erosionLaminarID + ", " + sitioID + ", " + numero + ", " + ancho + ", " + largo + ")");
                this.baseDatosLocal.commit();
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla SUELO_ErosionLaminar", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla SUELO_ErosionLaminar", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //16
    public void importarSueloEvidenciaErosion(String ruta) {
        this.querySelect = "SELECT EvidenciaErosionID, CoberturaSueloID, Punto, Dosel, LecturaTierraID FROM SUELO_EvidenciaErosion";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer evidenciaErosionID = rs.getInt("EvidenciaErosionID");
                Integer coberturaSueloID = rs.getInt("CoberturaSueloID");
                Integer punto = rs.getInt("Punto");
                Integer dosel = rs.getInt("Dosel");
                Integer lecturaTierraID = rs.getInt("LecturaTierraID");
                ps.executeUpdate("INSERT INTO SUELO_EvidenciaErosion(EvidenciaErosionID, CoberturaSueloID, Punto, Dosel, LecturaTierraID)VALUES(" + evidenciaErosionID + ", " + coberturaSueloID + ", " + punto + ", " + dosel + ", " + lecturaTierraID + ")");
                this.baseDatosLocal.commit();
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla SUELO_EvidenciaErosion", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla SUELO_EvidenciaErosion", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //17
    public void importarSueloHojarasca(String ruta) {
        this.querySelect = "SELECT HojarascaID, SitioID, Punto, TipoHojarascaID, EspesorHO, EspesorF, PesoTotalHO, PesoTotalF, PesoMuestraHO, PesoMuestraF, "
                + "Observaciones FROM SUELO_Hojarasca";
        Float espesorF = null;
        Float pesoTotalF = null;
        Float pesoMuestraF = null;
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer hojarascaID = rs.getInt("HojarascaID");
                Integer sitioID = rs.getInt("SitioID");
                Integer punto = rs.getInt("Punto");
                Integer tipoHojarascaID = rs.getInt("TipoHojarascaID");
                Float espesorHO = rs.getFloat("EspesorHO");
                if (rs.getObject("EspesorF") != null) {
                    espesorF = rs.getFloat("EspesorF");
                }
                Float pesoTotalHO = rs.getFloat("PesoTotalHO");
                if (rs.getObject("PesoTotalF") != null) {
                    pesoTotalF = rs.getFloat("PesoTotalF");
                }
                Float pesoMuestraHO = rs.getFloat("PesoMuestraHO");
                if (rs.getObject("PesoMuestraF") != null) {
                    pesoMuestraF = rs.getFloat("PesoMuestraF");
                }
                String observaciones = rs.getString("Observaciones");
                ps.executeUpdate("INSERT INTO SUELO_Hojarasca(HojarascaID, SitioID, Punto, TipoHojarascaID, EspesorHO, EspesorF, PesoTotalHO, PesoTotalF, PesoMuestraHO, PesoMuestraF, "
                        + "Observaciones)VALUES(" + hojarascaID + ", " + sitioID + ", " + punto + ", " + tipoHojarascaID + ", " + espesorHO + ", " + espesorF + ", " + pesoTotalHO + ", " + pesoTotalF + ", " + pesoMuestraHO + ", " + pesoMuestraF + ", '" + observaciones + "')");
                this.baseDatosLocal.commit();
                espesorF = null;
                pesoTotalF = null;
                pesoMuestraF = null;
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla SUELO_Hojarasca"
                    + e.getClass().getName() + " : " + e.getMessage(), "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla SUELO_Hojarasca", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //18
    public void importarSueloLongitudCanalillo(String ruta) {
        this.querySelect = "SELECT LongitudCanalilloID, SitioID, CampoLongitud, Longitud FROM SUELO_LongitudCanalillo";
        this.queryInsert = "INSERT INTO SUELO_LongitudCanalillo(LongitudCanalilloID, SitioID, CampoLongitud, Longitud)VALUES(?,?, ?, ?)";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer longitudCanalilloID = rs.getInt("LongitudCanalilloID");
                Integer sitioID = rs.getInt("SitioID");
                Integer campoLongitud = rs.getInt("CampoLongitud");
                Float longitud = rs.getFloat("Longitud");
                ps.executeUpdate("INSERT INTO SUELO_LongitudCanalillo(LongitudCanalilloID, SitioID, CampoLongitud, Longitud)"
                        + "VALUES(" + longitudCanalilloID + ", " + sitioID + ", " + campoLongitud + ", " + longitud + ")");
                this.baseDatosLocal.commit();
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla SUELO_LongitudCanalillo", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla SUELO_LongitudCanalillo", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //19
    public void importarSueloLongitudCarcava(String ruta) {
        this.querySelect = "SELECT LongitudCarcavaID, SitioID, CampoLongitud, Longitud FROM SUELO_LongitudCarcava";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer longitudCarcavaID = rs.getInt("LongitudCarcavaID");
                Integer sitioID = rs.getInt("SitioID");
                Integer campoLongitud = rs.getInt("CampoLongitud");
                Float longitud = rs.getFloat("Longitud");
                ps.executeUpdate("INSERT INTO SUELO_LongitudCarcava(LongitudCarcavaID, SitioID, CampoLongitud, Longitud)VALUES(" + longitudCarcavaID + ", " + sitioID + ", " + campoLongitud + ", " + longitud + ")");
                this.baseDatosLocal.commit();
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla SUELO_LongitudCarcava", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla SUELO_LongitudCarcava", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //20
    public void importarSueloLongitudMonticulo(String ruta) {
        this.querySelect = "SELECT LongitudMonticuloID, SitioID, CampoLongitud, Longitud FROM SUELO_LongitudMonticulo";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer longitudMonticuloID = rs.getInt("LongitudMonticuloID");
                Integer sitioID = rs.getInt("SitioID");
                Integer campoLongitud = rs.getInt("CampoLongitud");
                Float longitud = rs.getFloat("Longitud");
                ps.executeUpdate("INSERT INTO SUELO_LongitudMonticulo(LongitudMonticuloID, SitioID, CampoLongitud, Longitud)VALUES(" + longitudMonticuloID + ", " + sitioID + ", " + campoLongitud + ", " + longitud + ")");
                this.baseDatosLocal.commit();
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla SUELO_LongitudMonticulo", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla SUELO_LongitudMonticulo", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //21
    public void importarSueloMedicionCanalillos(String ruta) {
        this.querySelect = "SELECT MedicionCanalillosID, SitioID, NumeroCanalillos, ProfundidadPromedio, Longitud, Volumen FROM SUELO_MedicionCanalillos";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer medicionCanalillosID = rs.getInt("MedicionCanalillosID");
                Integer sitioID = rs.getInt("SitioID");
                Integer numeroCanalillos = rs.getInt("NumeroCanalillos");
                Float profundidadPromedio = rs.getFloat("ProfundidadPromedio");
                Float longitud = rs.getFloat("Longitud");
                Float volumen = rs.getFloat("Volumen");
                ps.executeUpdate("INSERT INTO SUELO_MedicionCanalillos(MedicionCanalillosID, SitioID, NumeroCanalillos, ProfundidadPromedio, Longitud, Volumen)"
                        + "VALUES(" + medicionCanalillosID + "," + sitioID + ", " + numeroCanalillos + ", " + profundidadPromedio + ", " + longitud + ", " + volumen + ")");
                this.baseDatosLocal.commit();
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla SUELO_MedicionCanalillos", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla SSUELO_MedicionCanalillos", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //22
    public void importarSueloMedicionCarcavas(String ruta) {
        this.querySelect = "SELECT MedicionCarcavasID, SitioID, NumeroCarcavas, ProfundidadPromedio, AnchoPromedio, Longitud, Volumen FROM SUELO_MedicionCarcavas";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer medicionCarcavasID = rs.getInt("MedicionCarcavasID");
                Integer sitioID = rs.getInt("SitioID");
                Integer numeroCarcavas = rs.getInt("NumeroCarcavas");
                Float profundidadPromedio = rs.getFloat("ProfundidadPromedio");
                Float anchoPromedio = rs.getFloat("AnchoPromedio");
                Float longitud = rs.getFloat("Longitud");
                Float volumen = rs.getFloat("Volumen");
                ps.executeUpdate("INSERT INTO SUELO_MedicionCanalillos(MedicionCarcavasID, SitioID, NumeroCarcavas, ProfundidadPromedio, AnchoPromedio, Longitud, Volumen)"
                        + "VALUES(" + medicionCarcavasID + ", " + sitioID + ", " + numeroCarcavas + ", " + profundidadPromedio + ", " + anchoPromedio + ", " + longitud + ", " + volumen + ")");
                this.baseDatosLocal.commit();
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla SUELO_MedicionCarcavas ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla SSUELO_MedicionCarcavas ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //23
    public void importarSueloMedicionDunas(String ruta) {
        this.querySelect = "SELECT MedicionDunas, SitioID, NumeroDunas, AlturaPromedio, AnchoPromedio, Longitud, Volumen FROM SUELO_MedicionDunas";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer medicionDunas = rs.getInt("MedicionDunas");
                Integer sitioID = rs.getInt("SitioID");
                Integer numeroDunas = rs.getInt("NumeroDunas");
                Float alturaPromedio = rs.getFloat("AlturaPromedio");
                Float anchoPromedio = rs.getFloat("AnchoPromedio");
                Float longitud = rs.getFloat("Longitud");
                Float volumen = rs.getFloat("Volumen");
                ps.executeUpdate("INSERT INTO SUELO_MedicionCanalillos(MedicionDunas, SitioID, NumeroCarcavas, ProfundidadPromedio, AnchoPromedio, Longitud, Volumen)"
                        + "VALUES(" + medicionDunas + ", " + sitioID + ", " + numeroDunas + ", " + alturaPromedio + ", " + anchoPromedio + ", " + longitud + ", " + volumen + ")");
                this.baseDatosLocal.commit();
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla SUELO_MedicionCarcavas ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla SSUELO_MedicionCarcavas ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //24
    public void importarSueloMuestras(String ruta) {
        this.querySelect = "SELECT MuestrasID, SitioID, ProfundidadID,Muestras, PesoMuestra, Lectura1, Lectura2, Lectura3, Lectura4, "
                + "Promedio, ClaveColecta FROM SUELO_Muestras";
        Float lectura1 = null;
        Float lectura2 = null;
        Float lectura3 = null;
        Float lectura4 = null;
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer muestrasID = rs.getInt("MuestrasID");
                Integer sitioID = rs.getInt("SitioID");
                Integer profundidadID = rs.getInt("ProfundidadID");
                Integer muestras = rs.getInt("Muestras");
                Float pesoMuestra = rs.getFloat("PesoMuestra");
                if (rs.getObject("Lectura1") != null) {
                    lectura1 = rs.getFloat("Lectura1");
                }
                if (rs.getObject("Lectura2") != null) {
                    lectura2 = rs.getFloat("Lectura2");
                }
                if (rs.getObject("Lectura3") != null) {
                    lectura3 = rs.getFloat("Lectura3");
                }
                if (rs.getObject("Lectura4") != null) {
                    lectura4 = rs.getFloat("Lectura4");
                }
                Float promedio = rs.getFloat("Promedio");
                String claveColecta = rs.getString("ClaveColecta");
                ps.executeUpdate("INSERT INTO SUELO_Muestras(MuestrasID, SitioID, ProfundidadID, Muestras, PesoMuestra, Lectura1, Lectura2, Lectura3, Lectura4, "
                        + "Promedio, ClaveColecta)VALUES(" + muestrasID + ", " + sitioID + ", " + profundidadID+ ", " +  muestras + ", " + pesoMuestra + ", " + lectura1
                        + ", " + lectura2 + ", " + lectura3 + ", " + lectura4 + ", " + promedio + ", '" + claveColecta + "')");
                this.baseDatosLocal.commit();
                lectura1 = null;
                lectura2 = null;
                lectura3 = null;
                lectura4 = null;
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla SUELO_Muestras ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla SUELO_Muestras ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //25
    public void importarSueloMuestrasPerfil(String ruta) {
        this.querySelect = "SELECT MuestrasPerfilID, SitioID, GradosLatitud, MinutosLatitud, SegundosLatitud, GradosLongitud, MinutosLongitud, SegundosLongitud, Elevacion, "
                + "DiametroInterno, DiametroExterno, Altura, Observaciones FROM SUELO_MuestrasPerfil";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer muestrasPerfilID = rs.getInt("MuestrasPerfilID");
                Integer sitioID = rs.getInt("SitioID");
                Integer gradosLatitud = rs.getInt("GradosLatitud");
                Integer minutosLatitud = rs.getInt("MinutosLatitud");
                Float segundosLatitud = rs.getFloat("SegundosLatitud");
                Integer gradosLongitud = rs.getInt("GradosLongitud");
                Integer minutosLongitud = rs.getInt("MinutosLongitud");
                Float segundosLongitud = rs.getFloat("SegundosLongitud");
                Float elevacion = rs.getFloat("Elevacion");
                Float diametroInterno = rs.getFloat("DiametroInterno");
                Float diametroExterno = rs.getFloat("DiametroExterno");
                Float altura = rs.getFloat("Altura");
                String observaciones = rs.getString("Observaciones");
                ps.executeUpdate("INSERT INTO SUELO_MuestrasPerfil(MuestrasPerfilID, SitioID, GradosLatitud, MinutosLatitud, SegundosLatitud, GradosLongitud, MinutosLongitud, SegundosLongitud, Elevacion, "
                        + "DiametroInterno, DiametroExterno, Altura, Observaciones)VALUES(" + muestrasPerfilID + ", " + sitioID + ", " + gradosLatitud + ", " + minutosLatitud + ", " + segundosLatitud + ", " + gradosLongitud
                        + ", " + minutosLongitud + ", " + segundosLongitud + ", " + elevacion + ", " + diametroInterno + ", " + diametroExterno + ", " + altura + ", '" + observaciones + "')");
                this.baseDatosLocal.commit();
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla SUELO_MuestrasPerfil ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla SUELO_MuestrasPerfil ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //26
    public void importarSueloPavimentos(String ruta) {
        this.querySelect = "SELECT PavimentoErosionID, SitioID, Numero, Diametro FROM SUELO_PavimentoErosion";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer pavimentoErosionID = rs.getInt("PavimentoErosionID");
                Integer sitioID = rs.getInt("SitioID");
                Integer numero = rs.getInt("Numero");
                Float diametro = rs.getFloat("Diametro");
                ps.executeUpdate("INSERT INTO SUELO_PavimentoErosion(PavimentoErosionID, SitioID, Numero, Diametro)VALUES(" + pavimentoErosionID + ", " + sitioID + ", " + numero + ", " + diametro + ")");
                this.baseDatosLocal.commit();
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla SUELO_PavimentosErosión", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla SUELO_PavimentosErosión", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //27
    public void importarSueloPedestal(String ruta) {
        this.querySelect = "SELECT PedestalID, SitioID, Numero, Altura FROM SUELO_Pedestal";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer pedestalID = rs.getInt("PedestalID");
                Integer sitioID = rs.getInt("SitioID");
                Integer numero = rs.getInt("Numero");
                Float altura = rs.getFloat("Altura");
                ps.executeUpdate("INSERT INTO SUELO_Pedestal(PedestalID, SitioID, Numero, Altura)VALUES(" + pedestalID + ", " + sitioID + ", " + numero + ", " + altura + ")");
                this.baseDatosLocal.commit();
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla SUELO_Pedestal", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla SUELO_Pedestal", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //28
    public void importarSueloProfundidad(String ruta) {
        this.querySelect = "SELECT ProfundidadSueloID, SitioID, Punto, Profundidad030, Profundidad3060, PesoTotal030, PesoTotal3060, Equipo030, Equipo3060, Clave030 ,Clave3060,"
                + "Observaciones FROM SUELO_Profundidad";
        Float profundidad3060 = null;
        Float pesoTotal3060 = null;
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer profundidadSueloID = rs.getInt("ProfundidadSueloID");
                Integer sitioID = rs.getInt("SitioID");
                Integer punto = rs.getInt("Punto");
                Float profundidad030 = rs.getFloat("Profundidad030");
                if (rs.getObject("Profundidad3060") != null) {
                    profundidad3060 = rs.getFloat("Profundidad3060");
                }
                Float pesoTotal030 = rs.getFloat("PesoTotal030");
                if (rs.getObject("PesoTotal3060") != null) {
                    pesoTotal3060 = rs.getFloat("PesoTotal3060");
                }
                Float equipo030 = rs.getFloat("Equipo030");
                Float equipo3060 = rs.getFloat("Equipo3060");
                String clave030 = rs.getString("Clave030");
                String clave3060 = rs.getString("Clave3060");
                String observaciones = rs.getString("Observaciones");
                ps.executeUpdate("INSERT INTO SUELO_Profundidad(ProfundidadSueloID, SitioID, Punto, Profundidad030, Profundidad3060, PesoTotal030, PesoTotal3060, Equipo030, Equipo3060,Clave030,Clave3060, "
                        + "Observaciones)VALUES(" + profundidadSueloID + ", " + sitioID + ", " + punto + ", " + profundidad030 + ", " + profundidad3060 + ", " + pesoTotal030 + ", " + pesoTotal3060 + ", '" + equipo030 + "', '" + equipo3060 + "', '" + clave030 + "', '" + clave3060 + "', '" + observaciones + "')");
                this.baseDatosLocal.commit();
                profundidad3060 = null;
                pesoTotal3060 = null;
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla SUELO_Profundidad ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla SUELO_Profundidad ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

//29
    public void importarSueloInformacion(String ruta) {
        this.querySelect = "SELECT SueloID, SitioID, UsoSueloID, OtroUsoSuelo, Espesor, PendienteDominante, Observaciones, NumeroCanalillos, ProfundidadPromedioCanalillos, "
                + "AnchoPromedioCanalillos, LongitudCanalillos, VolumenCanalillos, NumeroCarcavas, ProfundidadPromedioCarcavas, AnchoPromedioCarcavas, LongitudCarcavas, VolumenCarcavas, "
                + "NumeroMonticulos, AlturaPromedioMonticulos, AnchoPromedioMonticulos, LongitudPromedioMonticulos, VolumenMonticulos FROM SUELO_Suelo";
        Integer numeroCanalillos = null;
        Float profundidadPromedioCanalillos = null;
        Float anchoPromedioCanalillos = null;
        Float longitudCanalillos = null;
        Float volumenCanalillos = null;
        Integer numeroCarcavas = null;
        Float profundidadPromedioCarcavas = null;
        Float anchoPromedioCarcavas = null;
        Float longitudPromedioCarcavas = null;
        Float volumenCarcavas = null;
        Integer numeroMonticulos = null;
        Float alturaPromedioMonticulos = null;
        Float anchoPromedioMoticulos = null;
        Float longitudPromedioMonticulos = null;
        Float volumenMonticulos = null;
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer sueloID = rs.getInt("SueloID");
                Integer sitioID = rs.getInt("SitioID");
                Integer usoSueloID = rs.getInt("UsoSueloID");
                String otroUsoSuelo = rs.getString("OtroUsoSuelo");
                Float espesor = rs.getFloat("Espesor");
                Integer pendienteDominante = rs.getInt("PendienteDominante");
                String observaciones = rs.getString("Observaciones");
                if (rs.getObject("NumeroCanalillos") != null) {
                    numeroCanalillos = rs.getInt("NumeroCanalillos");
                }
                if (rs.getObject("ProfundidadPromedioCanalillos") != null) {
                    profundidadPromedioCanalillos = rs.getFloat("ProfundidadPromedioCanalillos");
                }
                if (rs.getObject("AnchoPromedioCanalillos") != null) {
                    anchoPromedioCanalillos = rs.getFloat("AnchoPromedioCanalillos");
                }
                if (rs.getObject("LongitudCanalillos") != null) {
                    longitudCanalillos = rs.getFloat("LongitudCanalillos");
                }
                if (rs.getObject("VolumenCanalillos") != null) {
                    volumenCanalillos = rs.getFloat("VolumenCanalillos");
                }
                if (rs.getObject("NumeroCarcavas") != null) {
                    numeroCarcavas = rs.getInt("NumeroCarcavas");
                }
                if (rs.getObject("ProfundidadPromedioCarcavas") != null) {
                    profundidadPromedioCarcavas = rs.getFloat("ProfundidadPromedioCarcavas");
                }
                if (rs.getObject("AnchoPromedioCarcavas") != null) {
                    anchoPromedioCarcavas = rs.getFloat("AnchoPromedioCarcavas");
                }
                if (rs.getObject("LongitudCarcavas") != null) {
                    longitudPromedioCarcavas = rs.getFloat("LongitudCarcavas");
                }
                if (rs.getObject("VolumenCarcavas") != null) {
                    volumenCarcavas = rs.getFloat("VolumenCarcavas");
                }
                if (rs.getObject("NumeroMonticulos") != null) {
                    numeroMonticulos = rs.getInt("NumeroMonticulos");
                }
                if (rs.getObject("AlturaPromedioMonticulos") != null) {
                    alturaPromedioMonticulos = rs.getFloat("AlturaPromedioMonticulos");
                }
                if (rs.getObject("AnchoPromedioMonticulos") != null) {
                    anchoPromedioMoticulos = rs.getFloat("AnchoPromedioMonticulos");
                }
                if (rs.getObject("LongitudPromedioMonticulos") != null) {
                    longitudPromedioMonticulos = rs.getFloat("LongitudPromedioMonticulos");
                }
                if (rs.getObject("VolumenMonticulos") != null) {
                    volumenMonticulos = rs.getFloat("VolumenMonticulos");
                }
                ps.executeUpdate("INSERT INTO SUELO_Suelo(SueloID, SitioID, UsoSueloID, OtroUsoSuelo, Espesor, PendienteDominante, Observaciones, NumeroCanalillos, ProfundidadPromedioCanalillos, "
                        + "AnchoPromedioCanalillos, LongitudCanalillos, VolumenCanalillos, NumeroCarcavas, ProfundidadPromedioCarcavas, AnchoPromedioCarcavas, LongitudCarcavas, VolumenCarcavas, "
                        + "NumeroMonticulos, AlturaPromedioMonticulos, AnchoPromedioMonticulos, LongitudPromedioMonticulos, VolumenMonticulos)"
                        + "VALUES(" + sueloID + ", " + sitioID + ", " + usoSueloID + ", '" + otroUsoSuelo + "', " + espesor + ", " + pendienteDominante + ", '" + observaciones + "', " + numeroCanalillos
                        + ", " + profundidadPromedioCanalillos + ", " + anchoPromedioCanalillos + ", " + longitudCanalillos + ", " + volumenCanalillos + ", " + numeroCarcavas + ", " + profundidadPromedioCarcavas
                        + ", " + anchoPromedioCarcavas + ", " + longitudPromedioCarcavas + ", " + volumenCarcavas + ", " + numeroMonticulos + ", " + alturaPromedioMonticulos + ", " + anchoPromedioMoticulos
                        + ", " + longitudPromedioMonticulos + ", " + volumenMonticulos + ")");
                this.baseDatosLocal.commit();
                numeroCanalillos = null;
                profundidadPromedioCanalillos = null;
                anchoPromedioCanalillos = null;
                longitudCanalillos = null;
                volumenCanalillos = null;
                numeroCarcavas = null;
                profundidadPromedioCarcavas = null;
                anchoPromedioCarcavas = null;
                longitudPromedioCarcavas = null;
                volumenCarcavas = null;
                numeroMonticulos = null;
                alturaPromedioMonticulos = null;
                anchoPromedioMoticulos = null;
                longitudPromedioMonticulos = null;
                volumenMonticulos = null;
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla SUELO_Suelo", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla SUELO_Suelo", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

//30
    public void importarSueloVarillasErosion(String ruta) {
        this.querySelect = "SELECT VarillaID, SitioID, NoVarilla, Azimut, Distancia, Profundidad FROM SUELO_VarillaErosion";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        Integer azimut = null;
        Float distancia = null;
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer varillaID = rs.getInt("VarillaID");
                Integer sitioID = rs.getInt("SitioID");
                Integer noVarilla = rs.getInt("NoVarilla");
                if (rs.getObject("Azimut") != null) {
                    azimut = rs.getInt("Azimut");
                }
                if (rs.getObject("Distancia") != null) {
                    distancia = rs.getFloat("Distancia");
                }
                Float profundidad = rs.getFloat("Profundidad");
                ps.executeUpdate("INSERT INTO SUELO_VarillaErosion(VarillaID, SitioID, NoVarilla, Azimut, Distancia, Profundidad)"
                        + "VALUES(" + varillaID + ", " + sitioID + ", " + noVarilla + ", " + azimut + ", " + distancia + ", " + profundidad + ")");
                this.baseDatosLocal.commit();
                azimut = null;
                distancia = null;
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla SUELO_VarillasErosion", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla SUELO_VarillasErosion", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

//31
    public void importarCarbonoCoberturaDosel(String ruta) {
        this.querySelect = "SELECT CoberturaDoselID, SitioID, Transecto, Punto1, Punto2, Punto3, Punto4, Punto5, Punto6, Punto7, "
                + "Punto8, Punto9, Punto10 FROM CARBONO_CoberturaDosel";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer coberturaDoselID = rs.getInt("CoberturaDoselID");
                Integer sitioID = rs.getInt("SitioID");
                Integer transecto = rs.getInt("Transecto");
                Integer punto1 = rs.getInt("Punto1");
                Integer punto2 = rs.getInt("Punto2");
                Integer punto3 = rs.getInt("Punto3");
                Integer punto4 = rs.getInt("Punto4");
                Integer punto5 = rs.getInt("Punto5");
                Integer punto6 = rs.getInt("Punto6");
                Integer punto7 = rs.getInt("Punto7");
                Integer punto8 = rs.getInt("Punto8");
                Integer punto9 = rs.getInt("Punto9");
                Integer punto10 = rs.getInt("Punto10");
                ps.executeUpdate("INSERT INTO CARBONO_CoberturaDosel(CoberturaDoselID, SitioID, Transecto, Punto1, Punto2, Punto3, Punto4, Punto5, Punto6, Punto7, "
                        + "Punto8, Punto9, Punto10 )VALUES(" + coberturaDoselID + ", " + sitioID + ", " + transecto + ", " + punto1 + ", " + punto2 + ", " + punto3 + ", " + punto4
                        + ", " + punto5 + ", " + punto6 + ", " + punto7 + ", " + punto8 + ", " + punto9 + ", " + punto10 + ")");
                this.baseDatosLocal.commit();
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla CARBONO_CoberturaDosel", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla CARBONO_CoberturaDosel", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //32
    public void importarCarbonoCubiertaVegetal(String ruta) {
        this.querySelect = "SELECT CubiertaVegetalID, SitioID, Transecto, ComponenteID, Altura5, Altura10 FROM CARBONO_CubiertaVegetal";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer cubiertaVegetalID = rs.getInt("CubiertaVegetalID");
                Integer sitioID = rs.getInt("SitioID");
                Integer transecto = rs.getInt("Transecto");
                Integer componenteID = rs.getInt("ComponenteID");
                Float altura5 = rs.getFloat("Altura5");
                Float altura10 = rs.getFloat("Altura10");
                ps.executeUpdate("INSERT INTO CARBONO_CubiertaVegetal(CubiertaVegetalID, SitioID, Transecto, ComponenteID, Altura5, Altura10)"
                        + "VALUES(" + cubiertaVegetalID + ", " + sitioID + ", " + transecto + ", " + componenteID + ", " + altura5 + ", " + altura10 + ")");
                this.baseDatosLocal.commit();
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla CARBONO_CubiertaVegetal", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla CARBONO_CubiertaVegetal", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

//33
    public void importarCarbonoLongitudComponente(String ruta) {
        this.querySelect = "SELECT LongitudComponenteID, SitioID, Consecutivo, Transecto, ComponenteID, FamiliaID, GeneroID, EspecieID, InfraespecieID, NombreComun, Segmento1, Segmento2, Segmento3, Segmento4, "
                + "Segmento5, Segmento6, Segmento7, Segmento8, Segmento9, Segmento10, Total, ClaveColecta FROM CARBONO_LongitudComponente";
        Integer segmento1 = null;
        Integer segmento2 = null;
        Integer segmento3 = null;
        Integer segmento4 = null;
        Integer segmento5 = null;
        Integer segmento6 = null;
        Integer segmento7 = null;
        Integer segmento8 = null;
        Integer segmento9 = null;
        Integer segmento10 = null;
        Integer familiaID = null;
        Integer generoID = null;
        Integer especieID = null;
        Integer infraespecieID = null;
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer longitudComponenteID = rs.getInt("LongitudComponenteID");
                Integer sitioID = rs.getInt("SitioID");
                Integer consecutivo = rs.getInt("Consecutivo");
                Integer transecto = rs.getInt("Transecto");
                Integer componenteID = rs.getInt("ComponenteID");
                if (rs.getObject("FamiliaID") != null) {
familiaID = rs.getInt("FamiliaID");
}
if (rs.getObject("GeneroID") != null) {
generoID= generoID = rs.getInt("GeneroID");
}
if (rs.getObject("EspecieID") != null) {
especieID =especieID = rs.getInt("EspecieID");
}
if (rs.getObject("InfraespecieID") != null) {
infraespecieID = infraespecieID = rs.getInt("InfraespecieID");
}
                String nombreComun = rs.getString("NombreComun");
                if (rs.getObject("Segmento1") != null) {
                    segmento1 = rs.getInt("Segmento1");
                }
                if (rs.getObject("Segmento2") != null) {
                    segmento2 = rs.getInt("Segmento2");
                }
                if (rs.getObject("Segmento3") != null) {
                    segmento3 = rs.getInt("Segmento3");
                }
                if (rs.getObject("Segmento4") != null) {
                    segmento4 = rs.getInt("Segmento4");
                }
                if (rs.getObject("Segmento5") != null) {
                    segmento5 = rs.getInt("Segmento5");
                }
                if (rs.getObject("Segmento6") != null) {
                    segmento6 = rs.getInt("Segmento6");
                }
                if (rs.getObject("Segmento7") != null) {
                    segmento7 = rs.getInt("Segmento7");
                }
                if (rs.getObject("Segmento8") != null) {
                    segmento8 = rs.getInt("Segmento8");
                }
                if (rs.getObject("Segmento9") != null) {
                    segmento9 = rs.getInt("Segmento9");
                }
                if (rs.getObject("Segmento10") != null) {
                    segmento10 = rs.getInt("Segmento10");
                }
                Integer total = rs.getInt("Total");
                String claveColecta = rs.getString("ClaveColecta");
                ps.executeUpdate("INSERT INTO CARBONO_LongitudComponente(LongitudComponenteID, SitioID, Consecutivo, Transecto, ComponenteID, FamiliaID, GeneroID, EspecieID, InfraespecieID, NombreComun, Segmento1, Segmento2, Segmento3, Segmento4, "
                        + "Segmento5, Segmento6, Segmento7, Segmento8, Segmento9, Segmento10, Total, ClaveColecta)VALUES(" + longitudComponenteID + ", " + sitioID + ", " + consecutivo + ", " + transecto + ", " + componenteID + ", " + familiaID + ", " + generoID
                        + ", " + especieID + ", " + infraespecieID + ", '" + nombreComun + "', " + segmento1 + ", " + segmento2 + ", " + segmento3 + ", " + segmento4 + ", " + segmento5 + ", " + segmento6 + ", " + segmento7 + ", " + segmento8
                        + ", " + segmento9 + ", " + segmento10 + ", " + total + ", '" + claveColecta + "')");
                this.baseDatosLocal.commit();
                segmento1 = null;
                segmento2 = null;
                segmento3 = null;
                segmento4 = null;
                segmento5 = null;
                segmento6 = null;
                segmento7 = null;
                segmento8 = null;
                segmento9 = null;
                segmento10 = null;
                familiaID = null;
generoID = null;
especieID = null;
infraespecieID = null;
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla CARBONO_LongitudComponente", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla CARBONO_LongitudComponente"
                        + e.getClass().getName() + " : " + e.getMessage(), "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //34
    public void importarCarbonoMaterialLenioso100(String ruta) {
        this.querySelect = "SELECT MaterialLenioso100ID, SitioID, Transecto, Pendiente, UnaHora, DiezHoras, CienHoras FROM CARBONO_MaterialLenioso100";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer materialLenioso100ID = rs.getInt("MaterialLenioso100ID");
                Integer sitioID = rs.getInt("SitioID");
                Integer transecto = rs.getInt("Transecto");
                Integer pendiente = rs.getInt("Pendiente");
                Integer unaHora = rs.getInt("UnaHora");
                Integer diezhoras = rs.getInt("DiezHoras");
                Integer cienHoras = rs.getInt("CienHoras");
                ps.executeUpdate("INSERT INTO CARBONO_MaterialLenioso100(MaterialLenioso100ID, SitioID, Transecto, Pendiente, UnaHora, DiezHoras, CienHoras)VALUES"
                        + "(" + materialLenioso100ID + ", " + sitioID + ", " + transecto + ", " + pendiente + ", " + unaHora + ", " + diezhoras + ", " + cienHoras + ")");
                this.baseDatosLocal.commit();
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla CARBONO_MaterialLenioso100", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla CARBONO_MaterialLenioso100", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

//35
    public void importarCarbonoMaterialLenioso1000(String ruta) {
        this.querySelect = "SELECT MaterialLenioso1000ID, SitioID, Transecto, Diametro, Grado FROM CARBONO_MaterialLenioso1000";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer materialLenioso1000ID = rs.getInt("MaterialLenioso1000ID");
                Integer sitioID = rs.getInt("SitioID");
                Integer transecto = rs.getInt("Transecto");
                Float diametro = rs.getFloat("Diametro");
                Integer grados = rs.getInt("Grado");
                ps.executeUpdate("INSERT INTO CARBONO_MaterialLenioso1000(MaterialLenioso1000ID, SitioID, Transecto, Diametro, Grado)VALUES(" + materialLenioso1000ID + ", " + sitioID + ", " + transecto + ", " + diametro + ", " + grados + ")");
                this.baseDatosLocal.commit();
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla CARBONO_MaterialLenioso1000", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla CARBONO_MaterialLenioso1000", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

//36
    public void importarTaxonomiaArbolado(String ruta) {
        this.querySelect = "SELECT SitioID ,ArboladoID ,Consecutivo ,NoIndividuo ,NoRama ,Azimut ,Distancia ,FamiliaID ,GeneroID ,EspecieID ,InfraespecieID ,NombreComun ,EsColecta ,EsSubmuestra ,FormaVidaID ,FormaFusteID ,CondicionID ,MuertoPieID ,GradoPutrefaccionID ,TipoToconID ,DiametroNormal ,DiametroBasal ,AlturaTotal ,AnguloInclinacion ,AlturaFusteLimpio ,AlturaComercial ,DiametroCopaNS ,DiametroCopaEO ,ProporcionCopaVivaID ,ExposicionCopaID ,PosicionCopaID ,DensidadCopaID ,MuerteRegresivaID ,TransparenciaFollajeID ,VigorID ,NivelVigorID ,ClaveColecta FROM TAXONOMIA_Arbolado ";
        Float diametroNormal = null;
        Integer diametroBasal = null;
        Float alturaTotal = null;
        Float anguloInclinacion = null;
        Float alturaFusteLimpio = null;
        Float alturaComercial = null;
        Float diametroCopaNS = null;
        Float diametroCopaEO = null;
        Integer familiaID = null;
        Integer generoID = null;
        Integer especieID = null;
        Integer infraespecieID = null;
        Integer muertoPieID = null;
        Integer gradoPutrefaccionID = null;
        Integer tipoToconID = null;
        Integer proporcionCopaVivaID = null;
        Integer exposicionCopaID = null;
        Integer posicionCopaID = null;
        Integer densidadCopaID = null;
        Integer muerteRegresivaID = null;
        Integer transparenciaFollajeID = null;
        Integer vigorID = null;
        Integer nivelVigor = null;
        String claveColecta = null;
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer arboladoID = rs.getInt("ArboladoID");
                Integer sitioID = rs.getInt("SitioID");
                Integer consecutivo = rs.getInt("Consecutivo");
                Integer noIndividuo = rs.getInt("NoIndividuo");
                Integer noRama = rs.getInt("NoRama");
                Integer azimut = rs.getInt("Azimut");
                Float distancia = rs.getFloat("Distancia");
                String nombreComun = rs.getString("NombreComun");
                Integer esSubmuestra = rs.getInt("EsSubmuestra");
                Integer formaVidaID = rs.getInt("FormaVidaID");
                Integer formaFusteID = rs.getInt("FormaFusteID");
                Integer condicionID = rs.getInt("CondicionID");

                if (rs.getObject("DiametroNormal") != null) {
                    diametroNormal = rs.getFloat("DiametroNormal");
                }
                if (rs.getObject("DiametroBasal") != null) {
                    diametroBasal = rs.getInt("DiametroBasal");
                }
                if (rs.getObject("AlturaTotal") != null) {
                    alturaTotal = rs.getFloat("AlturaTotal");
                }
                if (rs.getObject("AnguloInclinacion") != null) {
                    anguloInclinacion = rs.getFloat("AnguloInclinacion");
                }
                if (rs.getObject("AlturaFusteLimpio") != null) {
                    alturaFusteLimpio = rs.getFloat("AlturaFusteLimpio");
                }
                if (rs.getObject("AlturaComercial") != null) {
                    alturaComercial = rs.getFloat("AlturaComercial");
                }
                if (rs.getObject("DiametroCopaNS") != null) {
                    diametroCopaNS = rs.getFloat("DiametroCopaNS");
                }
                if (rs.getObject("DiametroCopaEO") != null) {
                    diametroCopaEO = rs.getFloat("DiametroCopaEO");
                }
                if (rs.getObject("FamiliaID") != null) {
                    familiaID = rs.getInt("FamiliaID");
                }
                if (rs.getObject("GeneroID") != null) {
                    generoID= generoID = rs.getInt("GeneroID");
                }
                if (rs.getObject("EspecieID") != null) {
                    especieID =especieID = rs.getInt("EspecieID");
                }
                if (rs.getObject("InfraespecieID") != null) {
                    infraespecieID = infraespecieID = rs.getInt("InfraespecieID");
                }
                if (rs.getObject("MuertoPieID") != null) {
                    muertoPieID = rs.getInt("MuertoPieID");
                }
                if (rs.getObject("GradoPutrefaccionID") != null) {
                    gradoPutrefaccionID = rs.getInt("GradoPutrefaccionID");
                }
                if (rs.getObject("TipoToconID") != null) {
                    tipoToconID = rs.getInt("TipoToconID");
                }
                if (rs.getObject("ProporcionCopaVivaID") != null) {
                    proporcionCopaVivaID = rs.getInt("ProporcionCopaVivaID");
                }
                if (rs.getObject("ExposicionCopaID") != null) {
                    exposicionCopaID = rs.getInt("ExposicionCopaID");
                }
                if (rs.getObject("PosicionCopaID") != null) {
                    posicionCopaID = rs.getInt("PosicionCopaID");
                }
                if (rs.getObject("DensidadCopaID") != null) {
                    densidadCopaID = rs.getInt("DensidadCopaID");
                }
                if (rs.getObject("MuerteRegresivaID") != null) {
                    muerteRegresivaID = rs.getInt("MuerteRegresivaID");
                }
                if (rs.getObject("TransparenciaFollajeID") != null) {
                    transparenciaFollajeID = rs.getInt("TransparenciaFollajeID");
                }
                if (rs.getObject("VigorID") != null) {
                    vigorID = rs.getInt("VigorID");
                }
                if (rs.getObject("NivelVigorID") != null) {
                    nivelVigor = rs.getInt("NivelVigorID");
                }
                if (rs.getObject("ClaveColecta") != null) {
                    claveColecta = rs.getString("ClaveColecta");
                }
                ps.executeUpdate("INSERT INTO TAXONOMIA_Arbolado(ArboladoID, SitioID, Consecutivo, NoIndividuo, NoRama, Azimut, Distancia, FamiliaID, GeneroID, EspecieID, InfraespecieID, "
                        + "NombreComun, EsSubmuestra, FormaVidaID, FormaFusteID, CondicionID, MuertoPieID, GradoPutrefaccionID, TipoToconID, DiametroNormal, "
                        + "DiametroBasal, AlturaTotal, AnguloInclinacion, AlturaFusteLimpio, AlturaComercial, DiametroCopaNS, DiametroCopaEO, ProporcionCopaVivaID, ExposicionCopaID, "
                        + "PosicionCopaID, DensidadCopaID, MuerteRegresivaID, TransparenciaFollajeID, VigorID,NivelVigorID , ClaveColecta)VALUES(" + arboladoID + ", " + sitioID + ", " + consecutivo + ", " + noIndividuo
                        + ", " + noRama + ", " + azimut + ", " + distancia + ", " + familiaID + ", " + generoID + ", " + especieID + ", " + infraespecieID + ", '" + nombreComun + "'"
                        + ", " + esSubmuestra + ", " + formaVidaID + ", " + formaFusteID + ", " + condicionID + ", " + muertoPieID + ", " + gradoPutrefaccionID + ", " + tipoToconID
                        + ", " + diametroNormal + ", " + diametroBasal + ", " + alturaTotal + ", " + anguloInclinacion + ", " + alturaFusteLimpio + ", " + alturaComercial + ", " + diametroCopaNS
                        + ", " + diametroCopaEO + ", " + proporcionCopaVivaID + ", " + exposicionCopaID + ", " + posicionCopaID + ", " + densidadCopaID + ", " + muerteRegresivaID + ", " + transparenciaFollajeID
                        + ", " + vigorID + ", " + nivelVigor + ", '" + claveColecta + "')");

                this.baseDatosLocal.commit();
                diametroNormal = null;
                diametroBasal = null;
                alturaTotal = null;
                anguloInclinacion = null;
                alturaFusteLimpio = null;
                alturaComercial = null;
                diametroCopaNS = null;
                diametroCopaEO = null;
                familiaID = null;
                generoID = null;
                especieID = null;
                infraespecieID = null;
                muertoPieID = null;
                gradoPutrefaccionID = null;
                tipoToconID = null;
                proporcionCopaVivaID = null;
                exposicionCopaID = null;
                posicionCopaID = null;
                densidadCopaID = null;
                muerteRegresivaID = null;
                transparenciaFollajeID = null;
                vigorID = null;
                nivelVigor = null;
                claveColecta = null;
                diametroNormal = null;
                diametroBasal = null;
                alturaTotal = null;
                anguloInclinacion = null;
                alturaFusteLimpio = null;
                alturaComercial = null;
                diametroCopaNS = null;
                diametroCopaEO = null;
                familiaID = null;
                generoID = null;
                especieID = null;
                infraespecieID = null;
                muertoPieID = null;
                gradoPutrefaccionID = null;
                tipoToconID = null;
                proporcionCopaVivaID = null;
                exposicionCopaID = null;
                posicionCopaID = null;
                densidadCopaID = null;
                muerteRegresivaID = null;
                transparenciaFollajeID = null;
                vigorID = null;
                nivelVigor = null;
                claveColecta = null;
                diametroNormal = null;
                diametroBasal = null;
                alturaTotal = null;
                anguloInclinacion = null;
                alturaFusteLimpio = null;
                alturaComercial = null;
                diametroCopaNS = null;
                diametroCopaEO = null;
                familiaID = null;
                generoID = null;
                especieID = null;
                infraespecieID = null;
                muertoPieID = null;
                gradoPutrefaccionID = null;
                tipoToconID = null;
                proporcionCopaVivaID = null;
                exposicionCopaID = null;
                posicionCopaID = null;
                densidadCopaID = null;
                muerteRegresivaID = null;
                transparenciaFollajeID = null;
                vigorID = null;
                nivelVigor = null;
                claveColecta = null;
                diametroNormal = null;
                diametroBasal = null;
                alturaTotal = null;
                anguloInclinacion = null;
                alturaFusteLimpio = null;
                alturaComercial = null;
                diametroCopaNS = null;
                diametroCopaEO = null;
                familiaID = null;
                generoID = null;
                especieID = null;
                infraespecieID = null;
                muertoPieID = null;
                gradoPutrefaccionID = null;
                tipoToconID = null;
                proporcionCopaVivaID = null;
                exposicionCopaID = null;
                posicionCopaID = null;
                densidadCopaID = null;
                muerteRegresivaID = null;
                transparenciaFollajeID = null;
                vigorID = null;
                nivelVigor = null;
                claveColecta = null;
                diametroNormal = null;
                diametroBasal = null;
                alturaTotal = null;
                anguloInclinacion = null;
                alturaFusteLimpio = null;
                alturaComercial = null;
                diametroCopaNS = null;
                diametroCopaEO = null;
                familiaID = null;
                generoID = null;
                especieID = null;
                infraespecieID = null;
                muertoPieID = null;
                gradoPutrefaccionID = null;
                tipoToconID = null;
                proporcionCopaVivaID = null;
                exposicionCopaID = null;
                posicionCopaID = null;
                densidadCopaID = null;
                muerteRegresivaID = null;
                transparenciaFollajeID = null;
                vigorID = null;
                nivelVigor = null;
                claveColecta = null;
                diametroNormal = null;
                diametroBasal = null;
                alturaTotal = null;
                anguloInclinacion = null;
                alturaFusteLimpio = null;
                alturaComercial = null;
                diametroCopaNS = null;
                diametroCopaEO = null;
                familiaID = null;
                generoID = null;
                especieID = null;
                infraespecieID = null;
                muertoPieID = null;
                gradoPutrefaccionID = null;
                tipoToconID = null;
                proporcionCopaVivaID = null;
                exposicionCopaID = null;
                posicionCopaID = null;
                densidadCopaID = null;
                muerteRegresivaID = null;
                transparenciaFollajeID = null;
                vigorID = null;
                nivelVigor = null;
                claveColecta = null;
                diametroNormal = null;
                diametroBasal = null;
                alturaTotal = null;
                anguloInclinacion = null;
                alturaFusteLimpio = null;
                alturaComercial = null;
                diametroCopaNS = null;
                diametroCopaEO = null;
                familiaID = null;
                generoID = null;
                especieID = null;
                infraespecieID = null;
                muertoPieID = null;
                gradoPutrefaccionID = null;
                tipoToconID = null;
                proporcionCopaVivaID = null;
                exposicionCopaID = null;
                posicionCopaID = null;
                densidadCopaID = null;
                muerteRegresivaID = null;
                transparenciaFollajeID = null;
                vigorID = null;
                nivelVigor = null;
                claveColecta = null;
                diametroNormal = null;
                diametroBasal = null;
                alturaTotal = null;
                anguloInclinacion = null;
                alturaFusteLimpio = null;
                alturaComercial = null;
                diametroCopaNS = null;
                diametroCopaEO = null;
                familiaID = null;
                generoID = null;
                especieID = null;
                infraespecieID = null;
                muertoPieID = null;
                gradoPutrefaccionID = null;
                tipoToconID = null;
                proporcionCopaVivaID = null;
                exposicionCopaID = null;
                posicionCopaID = null;
                densidadCopaID = null;
                muerteRegresivaID = null;
                transparenciaFollajeID = null;
                vigorID = null;
                nivelVigor = null;
                claveColecta = null;
                diametroNormal = null;
                diametroBasal = null;
                alturaTotal = null;
                anguloInclinacion = null;
                alturaFusteLimpio = null;
                alturaComercial = null;
                diametroCopaNS = null;
                diametroCopaEO = null;
                familiaID = null;
                generoID = null;
                especieID = null;
                infraespecieID = null;
                muertoPieID = null;
                gradoPutrefaccionID = null;
                tipoToconID = null;
                proporcionCopaVivaID = null;
                exposicionCopaID = null;
                posicionCopaID = null;
                densidadCopaID = null;
                muerteRegresivaID = null;
                transparenciaFollajeID = null;
                vigorID = null;
                nivelVigor = null;
                claveColecta = null;
                diametroNormal = null;
                diametroBasal = null;
                alturaTotal = null;
                anguloInclinacion = null;
                alturaFusteLimpio = null;
                alturaComercial = null;
                diametroCopaNS = null;
                diametroCopaEO = null;
                familiaID = null;
                generoID = null;
                especieID = null;
                infraespecieID = null;
                muertoPieID = null;
                gradoPutrefaccionID = null;
                tipoToconID = null;
                proporcionCopaVivaID = null;
                exposicionCopaID = null;
                posicionCopaID = null;
                densidadCopaID = null;
                muerteRegresivaID = null;
                transparenciaFollajeID = null;
                vigorID = null;
                nivelVigor = null;
                claveColecta = null;
                diametroNormal = null;
                diametroBasal = null;
                alturaTotal = null;
                anguloInclinacion = null;
                alturaFusteLimpio = null;
                alturaComercial = null;
                diametroCopaNS = null;
                diametroCopaEO = null;
                familiaID = null;
                generoID = null;
                especieID = null;
                infraespecieID = null;
                muertoPieID = null;
                gradoPutrefaccionID = null;
                tipoToconID = null;
                proporcionCopaVivaID = null;
                exposicionCopaID = null;
                posicionCopaID = null;
                densidadCopaID = null;
                muerteRegresivaID = null;
                transparenciaFollajeID = null;
                vigorID = null;
                nivelVigor = null;
                claveColecta = null;
                diametroNormal = null;
                diametroBasal = null;
                alturaTotal = null;
                anguloInclinacion = null;
                alturaFusteLimpio = null;
                alturaComercial = null;
                diametroCopaNS = null;
                diametroCopaEO = null;
                familiaID = null;
                generoID = null;
                especieID = null;
                infraespecieID = null;
                muertoPieID = null;
                gradoPutrefaccionID = null;
                tipoToconID = null;
                proporcionCopaVivaID = null;
                exposicionCopaID = null;
                posicionCopaID = null;
                densidadCopaID = null;
                muerteRegresivaID = null;
                transparenciaFollajeID = null;
                vigorID = null;
                nivelVigor = null;
                claveColecta = null;
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla TAXONOMIA_Arbolado", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla TAXONOMIA_Arbolado", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

//37
    public void importarSubmuestra(String ruta) {
        this.querySelect = "SELECT SubmuestraID, SitioID, ArboladoID, DiametroBasal, Edad, NumeroAnillos25, LongitudAnillos10, GrozorCorteza FROM ARBOLADO_Submuestra";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer submuestraID = rs.getInt("SubmuestraID");
                Integer sitioID = rs.getInt("SitioID");
                Integer arboladoID = rs.getInt("ArboladoID");
                Float diametroBasal = rs.getFloat("DiametroBasal");
                Integer edad = rs.getInt("Edad");
                Integer numeroAnillos25 = rs.getInt("NumeroAnillos25");
                Float longitudAnillos10 = rs.getFloat("LongitudAnillos10");
                Float grozorCorteza = rs.getFloat("GrozorCorteza");
                ps.executeUpdate("INSERT INTO ARBOLADO_Submuestra(SubmuestraID, SitioID, ArboladoID, DiametroBasal, Edad, NumeroAnillos25, LongitudAnillos10, GrozorCorteza)VALUES(" + submuestraID + ","
                        + sitioID + ", " + arboladoID + ", " + diametroBasal + ", " + edad + ", " + numeroAnillos25 + ", " + longitudAnillos10 + ", " + grozorCorteza + ")");
                this.baseDatosLocal.commit();
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla ARBOLADO_Submuestra", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla ARBOLADO_Submuestra"
                        + e.getClass().getName() + " : " + e.getMessage(), "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

//38
    public void importarSubmuestraTroza(String ruta) {
        this.querySelect = "SELECT TrozaID, SubmuestraID, NoTroza, TipoTrozaID FROM ARBOLADO_Troza";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer trozaID = rs.getInt("TrozaID");
                Integer submuestraID = rs.getInt("SubmuestraID");
                Integer noTroza = rs.getInt("NoTroza");
                Integer tipoTrozaID = rs.getInt("TipoTrozaID");
                ps.executeUpdate("INSERT INTO ARBOLADO_Troza(TrozaID, SubmuestraID, NoTroza, TipoTrozaID)VALUES(" + trozaID + ", " + submuestraID + ", " + noTroza + ", " + tipoTrozaID + ")");
                this.baseDatosLocal.commit();
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla ARBOLADO_Troza", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla ARBOLADO_Troza", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void importarSubmuestraObservaciones(String ruta) {
        this.querySelect = "SELECT SubmuestraObservacionesID, SitioID, Observaciones FROM SUBMUESTRA_Observaciones";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer submuestraObservacionesID = rs.getInt("SubmuestraObservacionesI");
                Integer sitioID = rs.getInt("SitioID");
                String observaciones = rs.getString("Observaciones");
                ps.executeUpdate("INSERT INTO SUBMUESTRA_Observaciones(SubmuestraObservacionesID, SitioID, Observaciones)VALUES(" + submuestraObservacionesID + ", " + sitioID + ", '" + observaciones + ")");
                this.baseDatosLocal.commit();
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla SUBMUESTRA_Observaciones", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla SUBMUESTRA_Observaciones", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

//39
    public void importarArboladoDanioSeveridad(String ruta) {
        this.querySelect = "SELECT DanioSeveridadID, ArboladoID, NumeroDanio, AgenteDanioID, SeveridadID FROM ARBOLADO_DanioSeveridad";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);

        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer danioSeveridadID = rs.getInt("DanioSeveridadID");
                Integer arbolID = rs.getInt("ArboladoID");
                Integer noDanio = rs.getInt("NumeroDanio");
                Integer agenteDanioID = rs.getInt("AgenteDanioID");
                Integer severidadID = null;
                if (rs.getObject("SeveridadID") == null) {
                    severidadID = null;
                } else {
                    severidadID = rs.getInt("SeveridadID");
                }
                ps.executeUpdate("INSERT INTO ARBOLADO_DanioSeveridad(DanioSeveridadID, ArboladoID, NumeroDanio, AgenteDanioID, SeveridadID)VALUES(" + danioSeveridadID + ", " + arbolID + ", "
                        + noDanio + ", " + agenteDanioID + ", " + severidadID + ")");
                this.baseDatosLocal.commit();
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla ARBOLADO_DanioSeveridad", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla ARBOLADO_DanioSeveridad", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //40
    public void importarTaxonomiaColectaBotanica(String ruta) {
        this.querySelect = "SELECT ColectaBotanicaID, UPMID, FamiliaID, GeneroID, EspecieID, InfraespecieID, NombreComun, Sitio, SeccionID, Consecutivo, ClaveColecta, ContraFuertes, Exudado, IndicarExudado, Color, IndicarColor, CambioColor, AceitesVolatiles, ColorFlor, IndicarColorFlor, HojasTejidoVivo, FotoFlor, FotoFruto, FotoHojasArriba, FotoHojasAbajo, FotoArbolCompleto, FotoCorteza, VirutaIncluida, "
                + "CortezaIncluida, MaderaIncluida, Observaciones FROM TAXONOMIA_ColectaBotanica";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        Integer familiaID = null;
        Integer generoID = null;
        Integer especieID = null;
        String infraespecieID = null;
        String nombreComun = null;
        Integer sitio = null;
        Integer seccionID = null;
        Integer consecutivo = null;
        String claveColecta = null;
        Integer contraFuertes = null;
        Integer exudado = null;
        String indicarExudado = null;
        Integer color = null;
        String indicarColor = null;
        Integer cambioColor = null;
        Integer aceitesVolatiles = null;
        Integer colorFlor = null;
        String indicarColorFlor = null;
        Integer hojasTejidoVivo = null;
        Integer fotoFlor = null;
        Integer fotoFruto = null;
        Integer fotoHojasArriba = null;
        Integer fotoHojasAbajo = null;
        Integer fotoArbolCompleto = null;
        Integer fotoCorteza = null;
        Integer virutaIncluida = null;
        Integer cortezaIncluida = null;
        Integer maderaIncluida = null;
        String observaciones = null;

        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer colectaBotanicaID = rs.getInt("ColectaBotanicaID");
                Integer UPMID = rs.getInt("UPMID");
                if (rs.getObject("FamiliaID") != null) {
                    familiaID = rs.getInt("FamiliaID");
                }
                if (rs.getObject("GeneroID") != null) {
                    generoID= generoID = rs.getInt("GeneroID");
                }
                if (rs.getObject("EspecieID") != null) {
                    especieID =especieID = rs.getInt("EspecieID");
                }
                if (rs.getObject("InfraespecieID") != null) {
                    infraespecieID = infraespecieID = rs.getString("InfraespecieID");
                }
                if (rs.getObject("NombreComun") != null) {
                    nombreComun = rs.getString("NombreComun");
                }
                if (rs.getObject("Sitio") != null) {
                    sitio = rs.getInt("Sitio");
                }
                if (rs.getObject("SeccionID") != null) {
                    seccionID = rs.getInt("SeccionID");
                }
                if (rs.getObject("Consecutivo") != null) {
                    consecutivo = rs.getInt("Consecutivo");
                }
                if (rs.getObject("ClaveColecta") != null) {
                    claveColecta = rs.getString("ClaveColecta");
                }
                if (rs.getObject("ContraFuertes") != null) {
                    contraFuertes = rs.getInt("ContraFuertes");
                }
                if (rs.getObject("Exudado") != null) {
                    exudado = rs.getInt("Exudado");
                }
                if (rs.getObject("IndicarExudado") != null) {
                    indicarExudado = rs.getString("IndicarExudado");
                }
                if (rs.getObject("Color") != null) {
                    color = rs.getInt("Color");
                }
                if (rs.getObject("IndicarColor") != null) {
                    indicarColor = rs.getString("IndicarColor");
                }
                if (rs.getObject("CambioColor") != null) {
                    cambioColor = rs.getInt("CambioColor");
                }
                if (rs.getObject("AceitesVolatiles") != null) {
                    aceitesVolatiles = rs.getInt("AceitesVolatiles");
                }
                if (rs.getObject("ColorFlor") != null) {
                    colorFlor = rs.getInt("ColorFlor");
                }
                if (rs.getObject("IndicarColorFlor") != null) {
                    indicarColorFlor = rs.getString("IndicarColorFlor");
                }
                if (rs.getObject("HojasTejidoVivo") != null) {
                    hojasTejidoVivo = rs.getInt("HojasTejidoVivo");
                }
                if (rs.getObject("FotoFlor") != null) {
                    fotoFlor = rs.getInt("FotoFlor");
                }
                if (rs.getObject("FotoFruto") != null) {
                    fotoFruto = rs.getInt("FotoFruto");
                }
                if (rs.getObject("FotoHojasArriba") != null) {
                    fotoHojasArriba = rs.getInt("FotoHojasArriba");
                }
                if (rs.getObject("FotoHojasAbajo") != null) {
                    fotoHojasAbajo = rs.getInt("FotoHojasAbajo");
                }
                if (rs.getObject("FotoArbolCompleto") != null) {
                    fotoArbolCompleto = rs.getInt("FotoArbolCompleto");
                }
                if (rs.getObject("FotoCorteza") != null) {
                    fotoCorteza = rs.getInt("FotoCorteza");
                }
                if (rs.getObject("VirutaIncluida") != null) {
                    virutaIncluida = rs.getInt("VirutaIncluida");
                }
                if (rs.getObject("CortezaIncluida") != null) {
                    cortezaIncluida = rs.getInt("CortezaIncluida");
                }
                if (rs.getObject("MaderaIncluida") != null) {
                    maderaIncluida = rs.getInt("MaderaIncluida");
                }
                if (rs.getObject("Observaciones") != null) {
                    observaciones = rs.getString("Observaciones");
                }
                ps.executeUpdate("INSERT INTO TAXONOMIA_ColectaBotanica(ColectaBotanicaID, UPMID, FamiliaID, GeneroID, EspecieID, InfraespecieID, NombreComun, Sitio, SeccionID, Consecutivo, ClaveColecta, ContraFuertes, Exudado, IndicarExudado, Color, IndicarColor, CambioColor, AceitesVolatiles, ColorFlor, IndicarColorFlor, HojasTejidoVivo, FotoFlor, FotoFruto, FotoHojasArriba, FotoHojasAbajo, FotoArbolCompleto, FotoCorteza, "
                        + "VirutaIncluida, CortezaIncluida, MaderaIncluida, Observaciones)VALUES(" + colectaBotanicaID + ", " + UPMID + ", " + familiaID + ", " + generoID + ", " + especieID + ", " + infraespecieID + ", '" + nombreComun + "'," + sitio + ", " + seccionID + ", " + consecutivo + ", '" + claveColecta + "', " + contraFuertes + ", " + exudado + ", '" + indicarExudado + "', " + color + ", '" + indicarColor + "'"
                        + ", " + cambioColor + ", " + aceitesVolatiles + ", " + colorFlor + ", '" + indicarColorFlor + "', " + hojasTejidoVivo + ", " + fotoFlor + ", " + fotoFruto + ", " + fotoHojasArriba + ", " + fotoHojasAbajo + ", " + fotoArbolCompleto + ", " + fotoCorteza + ", " + virutaIncluida + ", " + cortezaIncluida + ", " + maderaIncluida
                        + ", '" + observaciones + "')");
                this.baseDatosLocal.commit();
                familiaID = null;
                generoID = null;
                especieID = null;
                infraespecieID = null;
                nombreComun = null;
                sitio = null;
                seccionID = null;
                consecutivo = null;
                claveColecta = null;
                contraFuertes = null;
                exudado = null;
                indicarExudado = null;
                color = null;
                indicarColor = null;
                cambioColor = null;
                aceitesVolatiles = null;
                colorFlor = null;
                indicarColorFlor = null;
                hojasTejidoVivo = null;
                fotoFlor = null;
                fotoFruto = null;
                fotoHojasArriba = null;
                fotoHojasAbajo = null;
                fotoArbolCompleto = null;
                fotoCorteza = null;
                virutaIncluida = null;
                cortezaIncluida = null;
                maderaIncluida = null;
                observaciones = null;
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla TAXONOMIA_ColectaBotanica", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla TAXONOMIA_ColectaBotanica", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

//41
    public void importarTaxonomiaRepoblado(String ruta) {
        this.querySelect = "SELECT RepobladoID, SitioID, Consecutivo, FamiliaID, GeneroID, EspecieID, InfraespecieID, NombreComun, Frecuencia025150, Edad025150, Frecuencia151275, Edad151275, "
                + "Frecuencia275, Edad275, VigorID, DanioID, PorcentajeDanio, ClaveColecta FROM TAXONOMIA_Repoblado";
        Integer frecuencia025150 = null;
        Integer edad025150 = null;
        Integer frecuencia151275 = null;
        Integer edad151275 = null;
        Integer frecuencia275 = null;
        Integer edad275 = null;
        Integer porcentajeDanio = null;
        Integer familiaID = null;
        Integer generoID = null;
        Integer especieID = null;
        Integer infraespecieID = null;
        String claveColecta = null;
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer repobladoID = rs.getInt("RepobladoID");
                Integer sitioID = rs.getInt("SitioID");
                Integer consecutivo = rs.getInt("Consecutivo");
                if (rs.getObject("FamiliaID") != null) {
                   familiaID = rs.getInt("FamiliaID");
                }

                if (rs.getObject("GeneroID") != null) {
                   generoID= rs.getInt("GeneroID");
                }

                if (rs.getObject("EspecieID") != null) {
                    especieID =rs.getInt("EspecieID");
                }

                if (rs.getObject("InfraespecieID") != null) {
                   infraespecieID = rs.getInt("InfraespecieID");
                }

                String nombreComun = rs.getString("NombreComun");
                if (rs.getObject("Frecuencia025150") != null) {
                    frecuencia025150 = rs.getInt("Frecuencia025150");
                }
                if (rs.getObject("Edad025150") != null) {
                    edad025150 = rs.getInt("Edad025150");
                }
                if (rs.getObject("Frecuencia151275") != null) {
                    frecuencia151275 = rs.getInt("Frecuencia151275");
                }
                if (rs.getObject("Edad151275") != null) {
                    edad151275 = rs.getInt("Edad151275");
                }
                if (rs.getObject("Frecuencia275") != null) {
                    frecuencia275 = rs.getInt("Frecuencia275");
                }
                if (rs.getObject("Edad275") != null) {
                    edad275 = rs.getInt("Edad275");
                }
                Integer vigorID = rs.getInt("VigorID");
                Integer danioID = rs.getInt("DanioID");
                if (rs.getObject("PorcentajeDanio") != null) {
                    porcentajeDanio = rs.getInt("PorcentajeDanio");
                }
                if (rs.getObject("ClaveColecta") != null) {
                    claveColecta = rs.getString("ClaveColecta");
                }
                ps.executeUpdate("INSERT INTO TAXONOMIA_Repoblado(RepobladoID, SitioID, Consecutivo, FamiliaID, GeneroID, EspecieID, InfraespecieID, NombreComun, Frecuencia025150, Edad025150, Frecuencia151275, Edad151275, "
                        + "Frecuencia275, Edad275, VigorID, DanioID, PorcentajeDanio, ClaveColecta)VALUES(" + repobladoID + ", " + sitioID + ", " + consecutivo + ", " + familiaID + ", " + generoID + ", " + especieID + ", " + infraespecieID + ""
                        + ", '" + nombreComun + "', " + frecuencia025150 + ", " + edad025150 + ", " + frecuencia151275 + ", " + edad151275 + ", " + frecuencia275 + ", " + edad275 + ", " + vigorID + ", " + danioID
                        + ", " + porcentajeDanio + ", '" + claveColecta + "')");
                this.baseDatosLocal.commit();
                frecuencia025150 = null;
                edad025150 = null;
                frecuencia151275 = null;
                edad151275 = null;
                frecuencia275 = null;
                edad275 = null;
                porcentajeDanio = null;
                familiaID = null;
                generoID = null;
                especieID = null;
                infraespecieID = null;
                claveColecta = null;
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla TAXONOMIA_Repoblado", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla TAXONOMIA_Repoblado", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

//42
    public void importarTaxonomiaRepobladoVM(String ruta) {
        this.querySelect = "SELECT RepobladoVMID, SitioID, Consecutivo, FormaVidaID, FamiliaID, GeneroID, EspecieID, InfraespecieID, NombreComun, Frecuencia50, PorcentajeCobertura50, Frecuencia51200, PorcentajeCobertura51200,"
                + "Frecuencia200, PorcentajeCobertura200, VigorID, ClaveColecta FROM TAXONOMIA_RepobladoVM";
        Integer frecuencia50 = null;
        Integer porcentajeCobertura50 = null;
        Integer frecuencia51200 = null;
        Integer porcentajeCobertura51200 = null;
        Integer frecuencia200 = null;
        Integer porcentajeCobertura200 = null;
        Integer familiaID = null;
        Integer generoID = null;
        Integer especieID = null;
        Integer infraespecieID = null;
        String claveColecta = null;
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer repobladoVMID = rs.getInt("RepobladoVMID");
                Integer sitioID = rs.getInt("SitioID");
                Integer consecutivo = rs.getInt("Consecutivo");
                Integer formaVidaID = rs.getInt("FormaVidaID");
                if (rs.getObject("FamiliaID") != null) {
                   familiaID= rs.getInt("FamiliaID");
                }

                if (rs.getObject("GeneroID") != null) {
                    generoID= rs.getInt("GeneroID");
                }

                if (rs.getObject("EspecieID") != null) {
                    especieID =rs.getInt("EspecieID");
                }

                if (rs.getObject("InfraespecieID") != null) {
                    infraespecieID = rs.getInt("InfraespecieID");
                }
                String nombreComun = rs.getString("NombreComun");
                if (rs.getObject("Frecuencia50") != null) {
                    frecuencia50 = rs.getInt("Frecuencia50");
                }
                if (rs.getObject("PorcentajeCobertura50") != null) {
                    porcentajeCobertura50 = rs.getInt("PorcentajeCobertura50");
                }
                if (rs.getObject("Frecuencia51200") != null) {
                    frecuencia51200 = rs.getInt("Frecuencia51200");
                }
                if (rs.getObject("PorcentajeCobertura51200") != null) {
                    porcentajeCobertura51200 = rs.getInt("PorcentajeCobertura51200");
                }
                if (rs.getObject("Frecuencia200") != null) {
                    frecuencia200 = rs.getInt("Frecuencia200");
                }
                if (rs.getObject("PorcentajeCobertura200") != null) {
                    porcentajeCobertura200 = rs.getInt("PorcentajeCobertura200");
                }
                Integer vigorID = rs.getInt("VigorID");
                if (rs.getObject("ClaveColecta") != null) {
                    claveColecta = rs.getString("ClaveColecta");
                }
                ps.executeUpdate("INSERT INTO TAXONOMIA_RepobladoVM(RepobladoVMID, SitioID, Consecutivo, FormaVidaID, FamiliaID, GeneroID, EspecieID, InfraespecieID, NombreComun, Frecuencia50, PorcentajeCobertura50, Frecuencia51200, PorcentajeCobertura51200, "
                        + "Frecuencia200, PorcentajeCobertura200, VigorID, ClaveColecta)VALUES(" + repobladoVMID + ", " + sitioID + ", " + consecutivo + ", " + formaVidaID + ", " + familiaID + ", " + generoID + ", " + especieID + ", " + infraespecieID + ", '" + nombreComun + "'"
                        + ", " + frecuencia50 + ", " + porcentajeCobertura50 + ", " + frecuencia51200 + ", " + porcentajeCobertura51200 + ", " + frecuencia200 + ", " + porcentajeCobertura200 + ", " + vigorID + ", '" + claveColecta + "')");
                this.baseDatosLocal.commit();
                frecuencia50 = null;
                porcentajeCobertura50 = null;
                frecuencia51200 = null;
                porcentajeCobertura51200 = null;
                frecuencia200 = null;
                porcentajeCobertura200 = null;
                familiaID = null;
                generoID = null;
                especieID = null;
                infraespecieID = null;
                claveColecta = null;
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla TAXONOMIA_RepobladoVM", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla TAXONOMIA_RepobladoVM", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

//43
    public void importarTaxonomiaSotoBosque(String ruta) {
        this.querySelect = "SELECT SotoBosqueID, SitioID, Consecutivo, FamiliaID, GeneroID, EspecieID, InfraespecieID, NombreComun, Frecuencia025150, Cobertura025150, Frecuencia151275, Cobertura151275, Frecuencia275, Cobertura275, VigorID, DanioID, "
                + "PorcentajeDanio, ClaveColecta FROM TAXONOMIA_SotoBosque";
        Integer frecuencia025150 = null;
        Integer cobertura025150 = null;
        Integer frecuencia151275 = null;
        Integer cobertura151275 = null;
        Integer frecuencia275 = null;
        Integer cobertura275 = null;
        Integer porcentajeDanio = null;
        Integer familiaID = null;
        Integer generoID = null;
        Integer especieID = null;
        Integer infraespecieID = null;
        String claveColecta = null;
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer sotoBosqueID = rs.getInt("SotoBosqueID");
                Integer sitioID = rs.getInt("SitioID");
                Integer consecutivo = rs.getInt("Consecutivo");
                if (rs.getObject("FamiliaID") != null) {
                   familiaID= rs.getInt("FamiliaID");
                }

                if (rs.getObject("GeneroID") != null) {
                    generoID= rs.getInt("GeneroID");
                }

                if (rs.getObject("EspecieID") != null) {
                    especieID =rs.getInt("EspecieID");
                }

                if (rs.getObject("InfraespecieID") != null) {
                    infraespecieID = rs.getInt("InfraespecieID");
                }
                String nombreComun = rs.getString("NombreComun");
                if (rs.getObject("Frecuencia025150") != null) {
                    frecuencia025150 = rs.getInt("Frecuencia025150");
                }
                if (rs.getObject("Cobertura025150") != null) {
                    cobertura025150 = rs.getInt("Cobertura025150");
                }
                if (rs.getObject("Frecuencia151275") != null) {
                    frecuencia151275 = rs.getInt("Frecuencia151275");
                }
                if (rs.getObject("Cobertura151275") != null) {
                    cobertura151275 = rs.getInt("Cobertura151275");
                }
                if (rs.getObject("Frecuencia275") != null) {
                    frecuencia275 = rs.getInt("Frecuencia275");
                }
                if (rs.getObject("Cobertura275") != null) {
                    cobertura275 = rs.getInt("Cobertura275");
                }
                Integer vigorID = rs.getInt("VigorID");
                Integer danioID = rs.getInt("DanioID");
                if (rs.getObject("PorcentajeDanio") != null) {
                    porcentajeDanio = rs.getInt("PorcentajeDanio");
                }
                if (rs.getObject("ClaveColecta") != null) {
                    claveColecta = rs.getString("ClaveColecta");
                }
                ps.executeUpdate("INSERT INTO TAXONOMIA_SotoBosque(SotoBosqueID, SitioID, Consecutivo, FamiliaID, GeneroID, EspecieID, InfraespecieID, NombreComun, Frecuencia025150, Cobertura025150, Frecuencia151275, Cobertura151275, Frecuencia275, Cobertura275, VigorID, DanioID, "
                        + "PorcentajeDanio, ClaveColecta)VALUES(" + sotoBosqueID + ", " + sitioID + ", " + consecutivo + ", " + familiaID + ", " + generoID + ", " + especieID + ", " + infraespecieID + ", '" + nombreComun + "', " + frecuencia025150 + ", " + cobertura025150 + ", " + frecuencia151275
                        + ", " + cobertura151275 + ", " + frecuencia275 + ", " + cobertura275 + ", " + vigorID + ", " + danioID + ", " + porcentajeDanio + ", '" + claveColecta + "')");
                this.baseDatosLocal.commit();
                frecuencia025150 = null;
                cobertura025150 = null;
                frecuencia151275 = null;
                cobertura151275 = null;
                frecuencia275 = null;
                cobertura275 = null;
                porcentajeDanio = null;
                familiaID = null;
                generoID = null;
                especieID = null;
                infraespecieID = null;
                claveColecta = null;
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla TAXONOMIA_SotoBosque", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla TAXONOMIA_SotoBosque", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

//44
    public void importarTaxonomiaVegetacionMayorGregarios(String ruta) {
        this.querySelect = "SELECT VegetacionMayorID, SitioID, Consecutivo, NoIndividuo, FormaVidaID, CondicionID, FamiliaID, GeneroID, EspecieID, InfraespecieID, NombreComun, FormaCrecimientoID, DensidadColoniaID, AlturaTotalMaxima, AlturaTotalMedia, AlturaTotalMinima, DiametroCoberturaMayor, "
                + "DiametroCoberturaMenor, VigorID, ClaveColecta FROM TAXONOMIA_VegetacionMayorGregarios";
        Float alturaTotalMaxima = null;
        Float alturaTotalMedia = null;
        Float alturaTotalMinima = null;
        Float diametroCoberturaMayor = null;
        Float diametroCoberturaMenor = null;
        Integer familiaID = null;
        Integer generoID = null;
        Integer especieID = null;
        Integer infraespecieID = null;
        String claveColecta = null;
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer vegetacionMayorID = rs.getInt("VegetacionMayorID");
                Integer sitioID = rs.getInt("SitioID");
                Integer consecutivo = rs.getInt("Consecutivo");
                Integer noIndividuo = rs.getInt("NoIndividuo");
                Integer formaVidaID = rs.getInt("FormaVidaID");
                Integer condicionID = rs.getInt("CondicionID");
                if (rs.getObject("FamiliaID") != null) {
                   familiaID= rs.getInt("FamiliaID");
                }

                if (rs.getObject("GeneroID") != null) {
                    generoID= rs.getInt("GeneroID");
                }

                if (rs.getObject("EspecieID") != null) {
                    especieID =rs.getInt("EspecieID");
                }

                if (rs.getObject("InfraespecieID") != null) {
                    infraespecieID = rs.getInt("InfraespecieID");
                }
                String nombreComun = rs.getString("NombreComun");
                Integer formaCrecimientoID = rs.getInt("FormaCrecimientoID");
                Integer densidadColoniaID = rs.getInt("DensidadColoniaID");
                if (rs.getObject("AlturaTotalMaxima") != null) {
                    alturaTotalMaxima = rs.getFloat("AlturaTotalMaxima");
                }
                if (rs.getObject("AlturaTotalMedia") != null) {
                    alturaTotalMedia = rs.getFloat("AlturaTotalMedia");
                }
                if (rs.getObject("AlturaTotalMinima") != null) {
                    alturaTotalMinima = rs.getFloat("AlturaTotalMinima");
                }
                if (rs.getObject("DiametroCoberturaMayor") != null) {
                    diametroCoberturaMayor = rs.getFloat("DiametroCoberturaMayor");
                }
                if (rs.getObject("DiametroCoberturaMenor") != null) {
                    diametroCoberturaMenor = rs.getFloat("DiametroCoberturaMenor");
                }
                Integer vigorID = rs.getInt("VigorID");
                if (rs.getObject("ClaveColecta") != null) {
                    claveColecta = rs.getString("ClaveColecta");
                }
                ps.executeUpdate("INSERT INTO TAXONOMIA_VegetacionMayorGregarios(VegetacionMayorID, SitioID, Consecutivo, NoIndividuo, FormaVidaID, CondicionID, FamiliaID, GeneroID, EspecieID, InfraespecieID, NombreComun, FormaCrecimientoID, DensidadColoniaID, AlturaTotalMaxima, AlturaTotalMedia, AlturaTotalMinima, DiametroCoberturaMayor, "
                        + "DiametroCoberturaMenor, VigorID, ClaveColecta)VALUES(" + vegetacionMayorID + ", " + sitioID + ", " + consecutivo + ", " + noIndividuo + ", " + formaVidaID + ", " + condicionID + ", " + familiaID + ", " + generoID + ", " + especieID + ", " + infraespecieID + ", '" + nombreComun + "', " + formaCrecimientoID + ", " + densidadColoniaID
                        + ", " + alturaTotalMaxima + ", " + alturaTotalMedia + ", " + alturaTotalMinima + ", " + diametroCoberturaMayor + ", " + diametroCoberturaMenor + ", " + vigorID + ", '" + claveColecta + "')");
                this.baseDatosLocal.commit();
                alturaTotalMaxima = null;
                alturaTotalMedia = null;
                alturaTotalMinima = null;
                diametroCoberturaMayor = null;
                diametroCoberturaMenor = null;
                familiaID = null;
                generoID = null;
                especieID = null;
                infraespecieID = null;
                claveColecta = null;
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla TAXONOMIA_VegetacionMayorGregarios", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla TAXONOMIA_VegetacionMayorGregarios", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

//45
    public void importarVegetacionMayorGDanioSeveridad(String ruta) {
        this.querySelect = "SELECT DanioSeveridadID, VegetacionMayorID, NumeroDanio, AgenteDanioID, SeveridadID FROM VEGETACIONMAYORG_DanioSeveridad";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        Integer severidadID = null;
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer danioSeveridadID = rs.getInt("DanioSeveridadID");
                Integer vegetacionMayorID = rs.getInt("VegetacionMayorID");
                Integer numeroDanio = rs.getInt("NumeroDanio");
                Integer agenteDanioID = rs.getInt("AgenteDanioID");
                if (rs.getObject("SeveridadID") != null) {
                    severidadID = rs.getInt("SeveridadID");
                }
                ps.executeUpdate("INSERT INTO VEGETACIONMAYORG_DanioSeveridad(DanioSeveridadID, VegetacionMayorID, NumeroDanio, AgenteDanioID, SeveridadID)VALUES(" + danioSeveridadID + ", "
                        + vegetacionMayorID + ", " + numeroDanio + ", " + agenteDanioID + ", " + severidadID + ")");
                this.baseDatosLocal.commit();
                severidadID = null;
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla VEGETACIONMAYORG_DanioSeveridad", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla VEGETACIONMAYORG_DanioSeveridad", "Conexion BD", JOptionPane.ERROR_MESSAGE);

            }
        }
    }

//46
    public void importarTaxonomiaVegetacionMayorIndividual(String ruta) {
        this.querySelect = "SELECT VegetacionMayorID, SitioID, Consecutivo, NoIndividuo, FormaVidaID, CondicionID, FamiliaID, GeneroID, EspecieID, InfraespecieID, NombreComun, FormaGeometricaID, DensidadFollajeID, DiametroBase, AlturaTotal, DiametroCoberturaMayor, DiametroCoberturaMenor, VigorID, ClaveColecta FROM TAXONOMIA_VegetacionMayorIndividual";
        Float diametroBase = null;
        Float alturaTotal = null;
        Float diametroCoberturaMayor = null;
        Float diametroCoberturaMenor = null;
        Integer familiaID = null;
Integer generoID = null;
Integer especieID = null;
Integer infraespecieID = null;
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer vegetacionMayorID = rs.getInt("VegetacionMayorID");
                Integer sitioID = rs.getInt("SitioID");
                Integer consecutivo = rs.getInt("Consecutivo");
                Integer noIndividuo = rs.getInt("NoIndividuo");
                Integer formaVidaID = rs.getInt("FormaVidaID");
                Integer condicionID = rs.getInt("CondicionID");
                if (rs.getObject("FamiliaID") != null) {
familiaID = rs.getInt("FamiliaID");
}
if (rs.getObject("GeneroID") != null) {
generoID= generoID = rs.getInt("GeneroID");
}
if (rs.getObject("EspecieID") != null) {
especieID =especieID = rs.getInt("EspecieID");
}
if (rs.getObject("InfraespecieID") != null) {
infraespecieID = infraespecieID = rs.getInt("InfraespecieID");
}
                String nombreComun = rs.getString("NombreComun");
                Integer formaGeometricaID = rs.getInt("FormaGeometricaID");
                Integer densidadFollajeID = rs.getInt("DensidadFollajeID");
                if (rs.getObject("DiametroBase") != null) {
                    diametroBase = rs.getFloat("DiametroBase");
                }
                if (rs.getObject("AlturaTotal") != null) {
                    alturaTotal = rs.getFloat("AlturaTotal");
                }
                if (rs.getObject("DiametroCoberturaMayor") != null) {
                    diametroCoberturaMayor = rs.getFloat("DiametroCoberturaMayor");
                }
                if (rs.getObject("DiametroCoberturaMenor") != null) {
                    diametroCoberturaMenor = rs.getFloat("DiametroCoberturaMenor");
                }
                Integer vigorID = rs.getInt("VigorID");
                String claveColecta = rs.getString("ClaveColecta");
                ps.executeUpdate("INSERT INTO TAXONOMIA_VegetacionMayorIndividual(VegetacionMayorID, SitioID, Consecutivo, NoIndividuo, FormaVidaID, CondicionID, FamiliaID, GeneroID, EspecieID, InfraespecieID, NombreComun, FormaGeometricaID, DensidadFollajeID, DiametroBase, AlturaTotal, DiametroCoberturaMayor, DiametroCoberturaMenor, VigorID, ClaveColecta)"
                        + "VALUES(" + vegetacionMayorID + ", " + sitioID + ", " + consecutivo + ", " + noIndividuo + ", " + formaVidaID + ", " + condicionID + ", " + familiaID + ", " + generoID + ", " + especieID + ", " + infraespecieID + ", '" + nombreComun + "', " + formaGeometricaID + ", " + densidadFollajeID + ", " + diametroBase + ",  " + alturaTotal
                        + ", " + diametroCoberturaMayor + ", " + diametroCoberturaMenor + ", " + vigorID + ", '" + claveColecta + "')");
                this.baseDatosLocal.commit();
                diametroBase = null;
                alturaTotal = null;
                diametroCoberturaMayor = null;
                diametroCoberturaMenor = null;
                familiaID = null;
generoID = null;
especieID = null;
infraespecieID = null;
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla TAXONOMIA_VegetacionMayorIndividual", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla TAXONOMIA_VegetacionMayorIndividual", "Conexion BD", JOptionPane.ERROR_MESSAGE);

            }
        }
    }

//47
    public void importarVegetacionMayorIDanioSeveridad(String ruta) {
        this.querySelect = "SELECT DanioSeveridadID, VegetacionMayorID, NumeroDanio, AgenteDanioID, SeveridadID FROM VEGETACIONMAYORI_DanioSeveridad";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        Integer severidadID = null;
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer danioSeveridadID = rs.getInt("DanioSeveridadID");
                Integer vegetacionMayorID = rs.getInt("VegetacionMayorID");
                Integer numeroDanio = rs.getInt("NumeroDanio");
                Integer agenteDanio = rs.getInt("AgenteDanioID");
                if (rs.getObject("SeveridadID") != null) {
                    severidadID = rs.getInt("SeveridadID");
                }
                ps.executeUpdate("INSERT INTO VEGETACIONMAYORI_DanioSeveridad(DanioSeveridadID, VegetacionMayorID, NumeroDanio, AgenteDanioID, SeveridadID)VALUES(" + danioSeveridadID + ", "
                        + vegetacionMayorID + ", " + numeroDanio + ", " + agenteDanio + ", " + severidadID + ")");
                this.baseDatosLocal.commit();
                severidadID = null;
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla VEGETACIONMAYORI_DanioSeveridad", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla VEGETACIONMAYORI_DanioSeveridad", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

//48
    public void importarTaxonomiaVegetacionMenor(String ruta) {
        this.querySelect = "SELECT VegetacionMenorID, SitioID, Consecutivo, FamiliaID, GeneroID, EspecieID, InfraespecieID, NombreComun, FormaVidaID, CondicionID, Numero0110, Numero1125, Numero5175, Numero76100, Numero101125, Numero126150, Numero150, PorcentajeCobertura, VigorID, ClaveColecta FROM TAXONOMIA_VegetacionMenor";
        Integer numero0110 = null;
        Integer numero1125 = null;
        Integer numero5175 = null;
        Integer numero76100 = null;
        Integer numero101125 = null;
        Integer numero126150 = null;
        Integer numero150 = null;
        Integer porcentajeCobertura = null;
        Integer familiaID = null;
        Integer generoID = null;
        Integer especieID = null;
        Integer infraespecieID = null;
        String claveColecta = null;
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer vegetacionMenorID = rs.getInt("VegetacionMenorID");
                Integer sitioID = rs.getInt("SitioID");
                Integer consecutivo = rs.getInt("Consecutivo");
                if (rs.getObject("FamiliaID") != null) {
                   familiaID= rs.getInt("FamiliaID");
                }

                if (rs.getObject("GeneroID") != null) {
                    generoID= rs.getInt("GeneroID");
                }

                if (rs.getObject("EspecieID") != null) {
                    especieID =rs.getInt("EspecieID");
                }

                if (rs.getObject("InfraespecieID") != null) {
                    infraespecieID = rs.getInt("InfraespecieID");
                }
                if (rs.getObject("ClaveColecta") != null) {
                    claveColecta = rs.getString("ClaveColecta");
                }
                String nombreComun = rs.getString("NombreComun");
                Integer formaVidaID = rs.getInt("FormaVidaID");
                Integer condicionID = rs.getInt("CondicionID");
                if (rs.getObject("Numero0110") != null) {
                    numero0110 = rs.getInt("Numero0110");
                }
                if (rs.getObject("Numero1125") != null) {
                    numero1125 = rs.getInt("Numero1125");
                }
                if (rs.getObject("Numero5175") != null) {
                    numero5175 = rs.getInt("Numero5175");
                }
                if (rs.getObject("Numero76100") != null) {
                    numero76100 = rs.getInt("Numero76100");
                }
                if (rs.getObject("Numero101125") != null) {
                    numero101125 = rs.getInt("Numero101125");
                }
                if (rs.getObject("Numero126150") != null) {
                    numero126150 = rs.getInt("Numero126150");
                }
                if (rs.getObject("Numero150") != null) {
                    numero150 = rs.getInt("Numero150");
                }
                if (rs.getObject("PorcentajeCobertura") != null) {
                    porcentajeCobertura = rs.getInt("PorcentajeCobertura");
                }
                Integer vigorID = rs.getInt("VigorID");

                ps.executeUpdate("INSERT INTO TAXONOMIA_VegetacionMenor(VegetacionMenorID, SitioID, Consecutivo, FamiliaID, GeneroID, EspecieID, InfraespecieID, NombreComun, FormaVidaID, CondicionID, Numero0110, Numero1125, Numero5175, Numero76100, Numero101125, Numero126150, Numero150, PorcentajeCobertura, VigorID, ClaveColecta)VALUES(" + vegetacionMenorID
                        + ", " + sitioID + ", " + consecutivo + ", " + familiaID + ", " + generoID + ", " + especieID + ", " + infraespecieID + ", '" + nombreComun + "', " + formaVidaID + ", " + condicionID + ", " + numero0110 + ", " + numero1125 + ", " + numero5175 + ", " + numero76100 + ", " + numero101125
                        + ", " + numero126150 + ", " + numero150 + ", " + porcentajeCobertura + ", " + vigorID + ", '" + claveColecta + "')");
                this.baseDatosLocal.commit();
                numero0110 = null;
                numero1125 = null;
                numero5175 = null;
                numero76100 = null;
                numero101125 = null;
                numero126150 = null;
                numero150 = null;
                familiaID = null;
                generoID = null;
                especieID = null;
                infraespecieID = null;
                claveColecta = null;
                porcentajeCobertura = null;
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla TAXONOMIA_VegetacionMenor", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla TAXONOMIA_VegetacionMenor", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

//49
    public void importarVegetacionMenorDanioSeveridad(String ruta) {
        this.querySelect = "SELECT DanioSeveridadVMID, VegetacionMenorID, NumeroDanio, AgenteDanioID, SeveridadID FROM VEGETACIONMENOR_DanioSeveridad";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        Integer severidadID = null;
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer danioSeveridadVMID = rs.getInt("DanioSeveridadVMID");
                Integer vegetacionMenorID = rs.getInt("VegetacionMenorID");
                Integer numeroDanio = rs.getInt("NumeroDanio");
                Integer agenteDanioID = rs.getInt("AgenteDanioID");

                if (rs.getObject("SeveridadID") != null) {
                    severidadID = rs.getInt("SeveridadID");
                }
                ps.executeUpdate("INSERT INTO VEGETACIONMENOR_DanioSeveridad(DanioSeveridadVMID, VegetacionMenorID, NumeroDanio, AgenteDanioID, SeveridadID)"
                        + "VALUES(" + danioSeveridadVMID + ", " + vegetacionMenorID + ", " + numeroDanio + ", " + agenteDanioID + ", " + severidadID + ")");
                this.baseDatosLocal.commit();
                ps.close();
            }
            this.sqlExterno.close();
            severidadID = null;
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla VEGETACIONMENOR_DanioSeveridad", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla VEGETACIONMENOR_DanioSeveridad", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void importarRepobladoDanioSeveridad(String ruta) {
        this.querySelect = "SELECT RepobladoDanioID, RepobladoVMID, NumeroDanio, AgenteDanioID, SeveridadID FROM REPOBLADO_AgenteDanio";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        Integer severidadID= null;
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer repobladoDanioID = rs.getInt("RepobladoDanioID");
                Integer repobladoVMID = rs.getInt("RepobladoVMID");
                Integer numeroDanio = rs.getInt("NumeroDanio");
                Integer agenteDanioID = rs.getInt("AgenteDanioID");
                if(rs.getObject("SeveridadID") != null){
                    severidadID = rs.getInt("SeveridadID");
                }
                
                ps.executeUpdate("INSERT INTO REPOBLADO_AgenteDanio(RepobladoDanioID, RepobladoVMID, NumeroDanio, AgenteDanioID, SeveridadID)"
                        + "VALUES(" + repobladoDanioID + ", " + repobladoVMID + ", " + numeroDanio + ", " + agenteDanioID + ", " + severidadID + ")");
                this.baseDatosLocal.commit();
                severidadID= null;
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla REPOBLADO_AgenteDanio", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla REPOBLADO_AgenteDanio", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //50
    public void importarUPMContacto(String ruta) {
        this.querySelect = "SELECT ContactoID, UPMID, TipoContacto, Nombre, Direccion, TipoTelefono, NumeroTelefono, TieneCorreo, DireccionCorreo, "
                + "TieneRadio, Canal, Frecuencia ,Observaciones FROM UPM_Contacto";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer contactoID = rs.getInt("ContactoID");
                Integer upmID = rs.getInt("UPMID");
                Integer tipoContacto = rs.getInt("TipoContacto");
                String nombre = rs.getString("Nombre");
                String direccion = rs.getString("Direccion");
                Integer tipoTelefono = rs.getInt("TipoTelefono");
                String numeroTelefono = rs.getString("NumeroTelefono");
                Integer tieneCorreo = rs.getInt("TieneCorreo");
                String direccionCorreo = rs.getString("DireccionCorreo");
                Integer tieneRadio = rs.getInt("TieneRadio");
                String canal = rs.getString("Canal");
                String frecuencia = rs.getString("Frecuencia");
String observaciones = rs.getString("Observaciones");
                ps.executeUpdate("INSERT INTO UPM_Contacto(ContactoID, UPMID, TipoContacto, Nombre, Direccion, TipoTelefono, NumeroTelefono, TieneCorreo, DireccionCorreo, "
                        + "TieneRadio, Canal, Frecuencia, Observaciones)VALUES(" + contactoID + ", " + upmID + ", " + tipoContacto + " , '" + nombre + "', '" + direccion + "', " + tipoTelefono
                        + ", '" + numeroTelefono + "', " + tieneCorreo + ", '" + direccionCorreo + "' , " + tieneRadio + ", '" + canal + "', '" + frecuencia + "', '" + observaciones + "')");
                this.baseDatosLocal.commit();
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla UPM_Contacto", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla UPM_Contacto", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //51
    public void importarUPMEpifitas(String ruta) {
        this.querySelect = "SELECT EpifitaID, UPMID, ClaseTipoID, PresenciaTroncosID, PresenciaRamasID FROM UPM_Epifita";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer epifitaID = rs.getInt("EpifitaID");
                Integer upmID = rs.getInt("UPMID");
                Integer claseTipoID = rs.getInt("ClaseTipoID");
                Integer presenciaTroncosID = rs.getInt("PresenciaTroncosID");
                Integer presenciaRamasID = rs.getInt("PresenciaRamasID");
                ps.executeUpdate("INSERT INTO UPM_Epifita(EpifitaID, UPMID, ClaseTipoID, PresenciaTroncosID, PresenciaRamasID)VALUES(" + epifitaID + ", " + upmID + ", " + claseTipoID
                        + ", " + presenciaTroncosID + ", " + presenciaRamasID + ")");
                this.baseDatosLocal.commit();
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla UPM_Epifita", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla UPM_Epifita", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //53
    public void importarSecuencias(String ruta) {
        this.querySelect = "SELECT SecuenciaCapturaID, SecuenciaID, UPMID, Sitio, FormatoID, Estatus FROM SYS_SecuenciaCaptura";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer secuenciaCapturaID = rs.getInt("SecuenciaCapturaID");
                Integer secuenciaID = rs.getInt("SecuenciaID");
                Integer UPMID = rs.getInt("UPMID");
                Integer sitio = rs.getInt("Sitio");
                Integer formatoID = rs.getInt("FormatoID");
                Integer estatus = rs.getInt("Estatus");
                ps.executeUpdate("INSERT INTO SYS_SecuenciaCaptura(SecuenciaCapturaID, SecuenciaID, UPMID, Sitio, FormatoID, Estatus)"
                        + "VALUES(" + secuenciaCapturaID + ", " + secuenciaID + ", " + UPMID + ", " + sitio + ", " + formatoID + ", " + estatus + ")");
                this.baseDatosLocal.commit();
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla SYS_SecuenciaCaptura", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla SYS_SecuenciaCaptura", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void importarObservacionesSitio(String ruta) {
        //state=" importarObservaciones Sitio";
        this.querySelect = "SELECT ObservacionesID ,SitioID ,FormatoID, Observaciones FROM  SITIOS_Observaciones ";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer observacionesID = rs.getInt("ObservacionesID");
                Integer sitioID = rs.getInt("SitioID");
                Integer formatoID = rs.getInt("FormatoID");
                String observaciones = rs.getString("Observaciones");
                ps.executeUpdate(
                        "INSERT INTO SITIOS_Observaciones(ObservacionesID,SitioID,FormatoID,Observaciones)VALUES("
                        + observacionesID + ", " + sitioID + ", " + formatoID +",'"+observaciones+ "')");
                this.baseDatosLocal.commit();
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null,
                        "Error! al cerrar la base de datos en la importación de la tabla ARBOLADO_Submuestra"
                        + e.getClass().getName() + " : " + e.getMessage(),
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void importarUPMRevision(String ruta) {
        this.querySelect = "SELECT RevisionID, UPMID, SitioID, Sitio, SecuenciaID FROM SYS_UPM_Revision";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer revisionID = rs.getInt("RevisionID");
                Integer UPMID = rs.getInt("UPMID");
                Integer sitioID = rs.getInt("SitioID");
                Integer sitio = rs.getInt("Sitio");
                Integer secuenciaID = rs.getInt("SecuenciaID");
                ps.executeUpdate("INSERT INTO SYS_UPM_Revision(RevisionID, UPMID, SitioID, Sitio, SecuenciaID)"
                        + "VALUES(" + revisionID + ", " + UPMID + ", " + sitioID + ", " + sitio + ", " + secuenciaID + ")");
                this.baseDatosLocal.commit();
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla SYS_UPM_Revision", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla SYS_UPM_Revision", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //54
    public void importarBrigadas(String ruta) {
        this.querySelect = "SELECT BrigadaID, UPMID, BrigadistaID, PuestoID, EmpresaID FROM UPM_Brigada";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer brigadaID = rs.getInt("BrigadaID");
                Integer UPMID = rs.getInt("UPMID");
                Integer brigadistaID = rs.getInt("BrigadistaID");
                Integer puestoID = rs.getInt("PuestoID");
                Integer empresaID = rs.getInt("EmpresaID");
                ps.executeUpdate("INSERT INTO UPM_Brigada(BrigadaID, UPMID, BrigadistaID, PuestoID, EmpresaID)"
                        + "VALUES(" + brigadaID + ", " + UPMID + ", " + brigadistaID + ", " + puestoID + ", " + empresaID + ")");
                this.baseDatosLocal.commit();
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla UPM_Brigada", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla UPM_Brigada", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void eliminarPorUPM(int upm) {
        String query = "DELETE FROM UPM_UPM WHERE UPMID =" + upm;
        //System.out.println(query);
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! no se pudo eliminar la informacion del upm seleccionado", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos al eliminar los datos del upm seleccionado ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}
