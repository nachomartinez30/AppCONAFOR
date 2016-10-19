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
            JOptionPane.showMessageDialog(null, "Error! no se pudo revisar la importacion de la tabla UPM_UPM", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
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
            JOptionPane.showMessageDialog(null, "Error! no se pudo revisar la importacion de la tabla UPM_UPM", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            return false;
        } finally {
            try {
                baseDatosLocal.close();
            } catch (SQLException e) {
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
            JOptionPane.showMessageDialog(null, "Error! no se pudo eliminar la información del UPMID repetido ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
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
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer UPMID = rs.getInt("UPMID");
                String fechaInicio = rs.getString("FechaInicio");
                String fechaFin = rs.getString("FechaFin");
                Integer tipoUpmID = rs.getInt("TipoUPMID");
                Float altitud = rs.getFloat("Altitud");
                Integer pendiente = rs.getInt("PendienteRepresentativa");
                Integer fisiografiaID = rs.getInt("FisiografiaID");
                Integer exposicionID = rs.getInt("ExposicionID");
                String predio = rs.getString("Predio");
                String paraje = rs.getString("Paraje");
                Integer tipoTenenciaID = rs.getInt("TipoTenenciaID");
                Integer accesible = rs.getInt("Accesible");
                Integer gradosLatitud = rs.getInt("GradosLatitud");
                Integer minutosLatitud = rs.getInt("MinutosLatitud");
                Float segundosLatitud = rs.getFloat("SegundosLatitud");
                Integer gradosLongitud = rs.getInt("GradosLongitud");
                Integer minutosLongitud = rs.getInt("MinutosLongitud");
                Float segundosLongitud = rs.getFloat("SegundosLongitud");
                String datum = rs.getString("Datum");
                Integer errorPresicion = rs.getInt("ErrorPresicion");
                Integer azimut = rs.getInt("Azimut");
                Float distancia = rs.getFloat("Distancia");
                Integer tipoInaccesibilidadID = rs.getInt("TipoInaccesibilidadID");
                String otroTipoInaccesibilidad = rs.getString("OtroTipoInaccesibilidad");
                String explicacionInaccesibilidad = rs.getString("ExplicacionInaccesibilidad");
                Integer informacionContacto = rs.getInt("InformacionContacto");
                ps.executeUpdate("INSERT INTO UPM_UPM(UPMID, FechaInicio, FechaFin, TipoUPMID, Altitud, PendienteRepresentativa, "
                        + "FisiografiaID, ExposicionID, 'Predio', 'Paraje', TipoTenenciaID, Accesible, GradosLatitud, MinutosLatitud, "
                        + "SegundosLatitud, GradosLongitud, MinutosLongitud, SegundosLongitud, Datum, ErrorPresicion, "
                        + "Azimut, Distancia, TipoInaccesibilidadID, OtroTipoInaccesibilidad, ExplicacionInaccesibilidad, "
                        + "InformacionContacto)VALUES(" + UPMID + ", '" + fechaInicio + "', '" + fechaFin + "', " + tipoUpmID + ", " + altitud + ", " + pendiente + " , " + fisiografiaID + ", " + exposicionID
                        + ", '" + predio + "', '" + paraje + "', " + tipoTenenciaID + ", " + accesible + ", " + gradosLatitud + ", " + minutosLatitud + ", " + segundosLatitud + ", " + gradosLongitud + ", " + minutosLongitud + ", " + segundosLongitud + " , '"
                        + datum + "', " + errorPresicion + ", " + azimut + ", " + distancia + ", " + tipoInaccesibilidadID + ", '" + otroTipoInaccesibilidad + "', '" + explicacionInaccesibilidad + "', " + informacionContacto + ")");
                this.baseDatosLocal.commit();
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla UPM_UPM", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
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
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla PC_PuntoControl", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
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
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla PC_Accesibilidad", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla PC_Accesibilidad", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

//4
    public void importarSitios(String ruta) {
        this.querySelect = "SELECT UPMID, Sitio, SenialGPS, GradosLatitud, MinutosLatitud, SegundosLatitud, GradosLongitud, MinutosLongitud, "
                + "SegundosLongitud, ErrorPresicion, EvidenciaMuestreo, Datum, Azimut, Distancia, SitioAccesible, TipoInaccesibilidad, ExplicacionInaccesibilidad, CoberturaForestal, "
                + "Condicion, ClaveSerieV, FaseSucecional, ArbolFuera, Ecotono, CondicionPresenteCampo, CondicionEcotono, RepobladoFuera, PorcentajeRepoblado, "
                + "SotoBosqueFuera, PorcentajeSotoBosqueFuera, Observaciones, HipsometroBrujula, CintaClinometroBrujula, Cuadrante1, Cuadrante2, Cuadrante3, "
                + "Cuadrante4, Distancia1, Distancia2, Distancia3, Distancia4 FROM SITIOS_Sitio";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer upmID = rs.getInt("UPMID");
                Integer sitio = rs.getInt("Sitio");
                Integer senialGPS = rs.getInt("SenialGPS");
                Integer gradosLatitud = rs.getInt("GradosLatitud");
                Integer minutosLatitud = rs.getInt("MinutosLatitud");
                Float segundosLatitud = rs.getFloat("SegundosLatitud");
                Integer gradosLongitud = rs.getInt("GradosLongitud");
                Integer minutosLongitud = rs.getInt("MinutosLongitud");
                Float segundosLongitud = rs.getFloat("SegundosLongitud");
                Integer errorPresicion = rs.getInt("ErrorPresicion");
                Integer evidenciaMuestreo = rs.getInt("EvidenciaMuestreo");
                String datum = rs.getString("Datum");
                Integer azimut = rs.getInt("Azimut");
                Float distancia = rs.getFloat("Distancia");
                Integer sitioAccesible = rs.getInt("SitioAccesible");
                Integer tipoInaccesibilidad = rs.getInt("TipoInaccesibilidad");
                String explicacionInaccesibilidad = rs.getString("ExplicacionInaccesibilidad");
                Integer coberturaForestal = rs.getInt("CoberturaForestal");
                Integer condicion = rs.getInt("Condicion");
                Integer claveSerieV = rs.getInt("ClaveSerieV");
                Integer faseSucecional = rs.getInt("FaseSucecional");
                Integer arbolFuera = rs.getInt("ArbolFuera");
                Integer ecotono = rs.getInt("Ecotono");
                String condicionPresente = rs.getString("CondicionPresenteCampo");
                String condicionEcotono = rs.getString("CondicionEcotono");
                Integer repobladoFuera = rs.getInt("RepobladoFuera");
                Integer porcentajeRepoblado = rs.getInt("PorcentajeRepoblado");
                Integer sotoBosqueFuera = rs.getInt("SotoBosqueFuera");
                Integer porcentajeSotoBosque = rs.getInt("PorcentajeSotoBosqueFuera");
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
                ps.executeUpdate("INSERT INTO SITIOS_Sitio(UPMID, Sitio, SenialGPS, GradosLatitud, MinutosLatitud, SegundosLatitud, GradosLongitud, MinutosLongitud, "
                        + "SegundosLongitud, ErrorPresicion, EvidenciaMuestreo, Datum, Azimut, Distancia, SitioAccesible, TipoInaccesibilidad, ExplicacionInaccesibilidad, CoberturaForestal, "
                        + "Condicion, ClaveSerieV, FaseSucecional, ArbolFuera, Ecotono, CondicionPresenteCampo, CondicionEcotono, RepobladoFuera, PorcentajeRepoblado, "
                        + "SotoBosqueFuera, PorcentajeSotoBosqueFuera, Observaciones, HipsometroBrujula, CintaClinometroBrujula, Cuadrante1, Cuadrante2, Cuadrante3, "
                        + "Cuadrante4, Distancia1, Distancia2, Distancia3, Distancia4)VALUES(" + upmID + ", " + sitio + ", " + senialGPS + ", " + gradosLatitud + ", " + minutosLatitud + ", " + segundosLatitud
                        + ", " + gradosLongitud + ", " + minutosLongitud + ", " + segundosLongitud + ", " + errorPresicion + ", " + evidenciaMuestreo + ", '" + datum + "', " + azimut + ", " + distancia
                        + ", " + sitioAccesible + ", " + tipoInaccesibilidad + ", '" + explicacionInaccesibilidad + "', " + coberturaForestal + ", " + condicion + ", " + claveSerieV + ", " + faseSucecional + ", " + arbolFuera
                        + ", " + ecotono + ", '" + condicionPresente + "', '" + condicionEcotono + "', " + repobladoFuera + ", " + porcentajeRepoblado + ", " + sotoBosqueFuera + ", " + porcentajeSotoBosque
                        + ", '" + observaciones + "', " + hipsometroBrujula + ", " + cintaClinometroBrujula + ", " + cuadrante1 + ", " + cuadrante2 + ", " + cuadrante3 + ", " + cuadrante4 + ", " + distancia1
                        + ", " + distancia2 + ", " + distancia3 + ", " + distancia4 + ")");
                this.baseDatosLocal.commit();
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla SITIOS_Sitio", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla SITIOS_Sitio", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

//5
    public void importarSitiosCoberturaSuelo(String ruta) {
        this.querySelect = "SELECT SitioID, Gramineas, Helechos, Musgos, Liquenes, Hierbas, Roca, SueloDesnudo, Hojarasca, Grava, Otros FROM SITIOS_CoberturaSuelo";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer sitioID = rs.getInt("SitioID");
                Integer gramineas = rs.getInt("Gramineas");
                Integer helechos = rs.getInt("Helechos");
                Integer musgos = rs.getInt("Musgos");
                Integer liquenes = rs.getInt("Liquenes");
                Integer hierbas = rs.getInt("Hierbas");
                Integer rocas = rs.getInt("Roca");
                Integer sueloDesnuedo = rs.getInt("SueloDesnudo");
                Integer hojarasca = rs.getInt("Hojarasca");
                Integer grava = rs.getInt("Grava");
                Integer otros = rs.getInt("Otros");
                ps.executeUpdate("INSERT INTO SITIOS_CoberturaSuelo(SitioID, Gramineas, Helechos, Musgos, Liquenes, Hierbas, Roca, SueloDesnudo, Hojarasca, Grava, Otros)"
                        + "VALUES(" + sitioID + ", " + gramineas + ", " + helechos + ", " + musgos + ", " + liquenes + ", " + hierbas + ", " + rocas
                        + ", " + sueloDesnuedo + ", " + hojarasca + ", " + grava + ", " + otros + ")");
                this.baseDatosLocal.commit();
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla SITIOS_CoberturaSuelo", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla SITIOS_CoberturaSuelo", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

//5
    public void importarFotografiaHemisferica(String ruta) {
        this.querySelect = "SELECT SitioID, CoberturaArborea, TomaFotografia, Hora, DeclinacionMagnetica FROM SITIOS_FotografiaHemisferica";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer sitioID = rs.getInt("SitioID");
                Integer coberturaArborea = rs.getInt("CoberturaArborea");
                Integer tomaFotografia = rs.getInt("TomaFotografia");
                String hora = rs.getString("Hora");
                Integer declinacionMagnetica = rs.getInt("DeclinacionMagnetica");
                ps.executeUpdate("INSERT INTO SITIOS_FotografiaHemisferica(SitioID, CoberturaArborea, TomaFotografia, Hora, DeclinacionMagnetica)"
                        + "VALUES(" + sitioID + ", " + coberturaArborea + ", " + tomaFotografia + ", '" + hora + "', " + declinacionMagnetica + ")");
                this.baseDatosLocal.commit();
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla SITIOS_FotografiaHemisferica", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla SITIOS_FotografiaHemisferica", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //6
    public void importarTransponder(String ruta) {
        this.querySelect = "SELECT SitioID, TipoColocacionID, Especifique, Observaciones FROM SITIOS_Transponder";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer sitioID = rs.getInt("SitioID");
                Integer tipoColocacionID = rs.getInt("TipoColocacionID");
                String especifique = rs.getString("Especifique");
                String observaciones = rs.getString("Observaciones");
                ps.executeUpdate("INSERT INTO SITIOS_Transponder(SitioID, TipoColocacionID, Especifique, Observaciones)"
                        + "VALUES(" + sitioID + ", " + tipoColocacionID + ", '" + especifique + "', '" + observaciones + "')");
                this.baseDatosLocal.commit();
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla SITIOS_Transponder", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla SITIOS_Transponder", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

//7
    public void importarParametrosFisicoQuimicos(String ruta) {
        this.querySelect = "SELECT SitioID, TipoAgua, Salinidad, Temperatura, ConductividadElectrica, Ph, PotencialRedox, Profundidad, Observaciones FROM "
                + "SITIOS_ParametrosFisicoQuimicos";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer sitioID = rs.getInt("SitioID");
                Integer tipoAgua = rs.getInt("TipoAgua");
                Float salinidad = rs.getFloat("Salinidad");
                Float temperatura = rs.getFloat("Temperatura");
                Float conductividadElectrica = rs.getFloat("ConductividadElectrica");
                Float ph = rs.getFloat("Ph");
                Float potencialRedox = rs.getFloat("PotencialRedox");
                Float profundidad = rs.getFloat("Profundidad");
                String observaciones = rs.getString("Observaciones");
                ps.executeUpdate("INSERT INTO SITIOS_ParametrosFisicoQuimicos(SitioID, TipoAgua, Salinidad, Temperatura, ConductividadElectrica, Ph, PotencialRedox, "
                        + "Profundidad, Observaciones)VALUES(" + sitioID + ", " + tipoAgua + ", " + salinidad + ", " + temperatura + ", " + conductividadElectrica + ", " + ph
                        + ", " + potencialRedox + ", " + profundidad + ", '" + observaciones + "')");
                this.baseDatosLocal.commit();
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla SITIOS_ParametrosFisicoQuimicos", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla SITIOS_ParametrosFisicoQuimicos", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //8
    public void importarSueloCanalillo(String ruta) {
        this.querySelect = "SELECT SitioID, Numero, Ancho, Profundidad FROM SUELO_Canalillo";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer sitioID = rs.getInt("SitioID");
                Integer numero = rs.getInt("Numero");
                Float ancho = rs.getFloat("Ancho");
                Float profundidad = rs.getFloat("Profundidad");
                ps.executeUpdate("INSERT INTO SUELO_Canalillo(SitioID, Numero, Ancho, Profundidad)VALUES(" + sitioID + ", " + numero + ", " + ancho + ", " + profundidad + ")");
                this.baseDatosLocal.commit();
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla SUELO_Canalillo", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla SUELO_Canalillo", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //9
    public void importarSueloCarcava(String ruta) {
        this.querySelect = "SELECT SitioID, Numero, Ancho, Profundidad FROM SUELO_Carcava";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer sitioID = rs.getInt("SitioID");
                Integer numero = rs.getInt("Numero");
                Float ancho = rs.getFloat("Ancho");
                Float profundidad = rs.getFloat("Profundidad");
                ps.executeUpdate("INSERT INTO SUELO_Carcava(SitioID, Numero, Ancho, Profundidad)VALUES(" + sitioID + ", " + numero + ", " + ancho + ", " + profundidad + ")");
                this.baseDatosLocal.commit();
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla SUELO_Carcava", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla SUELO_Carcava", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //10
    public void importarSueloCobertura(String ruta) {
        this.querySelect = "SELECT SitioID, Transecto, Pendiente FROM SUELO_CoberturaSuelo";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer sitioID = rs.getInt("SitioID");
                Integer transecto = rs.getInt("Transecto");
                Integer pendiente = rs.getInt("Pendiente");
                ps.executeUpdate("INSERT INTO SUELO_CoberturaSuelo(SitioID, Transecto, Pendiente)VALUES(" + sitioID + ", " + transecto + ", " + pendiente + ")");
                this.baseDatosLocal.commit();
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla SUELO_CoberturaSuelo", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla SUELO_CoberturaSuelo", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //11
    public void importarSueloCostras(String ruta) {
        this.querySelect = "SELECT SitioID, Numero, Diametro FROM SUELO_Costras";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer sitioID = rs.getInt("SitioID");
                Integer numero = rs.getInt("Numero");
                Float diametro = rs.getFloat("Diametro");
                ps.executeUpdate("INSERT INTO SUELO_Costras(SitioID, Numero, Diametro)VALUES(" + sitioID + ", " + numero + ", " + diametro + ")");
                this.baseDatosLocal.commit();
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla SUELO_Costras", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla SUELO_Costras", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //12
    public void importarSueloDeformacionViento(String ruta) {
        this.querySelect = "SELECT SitioID, Medicion, Altura, Diametro, Longitud, Distancia, Azimut FROM SUELO_DeformacionViento";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer sitioID = rs.getInt("SitioID");
                Integer medicion = rs.getInt("Medicion");
                Float altura = rs.getFloat("Altura");
                Float diametro = rs.getFloat("Diametro");
                Float longitud = rs.getFloat("Longitud");
                Float distancia = rs.getFloat("Distancia");
                Integer azimut = rs.getInt("Azimut");
                ps.executeUpdate("INSERT INTO SUELO_DeformacionViento(SitioID, Medicion, Altura, Diametro, Longitud, Distancia, Azimut)VALUES(" + sitioID + ", " + medicion + ", " + altura + ", " + diametro + ", " + longitud + ", " + distancia + ", " + azimut + ")");
                this.baseDatosLocal.commit();
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla SUELO_DeformacionViento", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla SUELO_DeformacionViento", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //13
    public void importarSueloErosionHidricaCanalillo(String ruta) {
        this.querySelect = "SELECT SitioID, Medicion, Profundidad, Ancho, Distancia, Azimut FROM SUELO_ErosionHidricaCanalillo";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer sitioID = rs.getInt("SitioID");
                Integer medicion = rs.getInt("Medicion");
                Float profundidad = rs.getFloat("Profundidad");
                Float ancho = rs.getFloat("Ancho");
                Float distancia = rs.getFloat("Distancia");
                Integer azimut = rs.getInt("Azimut");
                ps.executeUpdate("INSERT INTO SUELO_ErosionHidricaCanalillo(SitioID, Medicion, Profundidad, Ancho, Distancia, Azimut)"
                        + "VALUES(" + sitioID + ", " + medicion + ", " + profundidad + ", " + ancho + ", " + distancia + ", " + azimut + ")");
                this.baseDatosLocal.commit();
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla SUELO_ErosionHidricaCanalillo", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla SUELO_ErosionHidricaCanalillo", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //14
    public void importarSueloErosionHidricaCarcava(String ruta) {
        this.querySelect = "SELECT SitioID, Medicion, Profundidad, Ancho, Distancia, Azimut FROM SUELO_ErosionHidricaCarcava";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer sitioID = rs.getInt("SitioID");
                Integer medicion = rs.getInt("Medicion");
                Float profundidad = rs.getFloat("Profundidad");
                Float ancho = rs.getFloat("Ancho");
                Float distancia = rs.getFloat("Distancia");
                Integer azimut = rs.getInt("Azimut");
                ps.executeUpdate("INSERT INTO SUELO_ErosionHidricaCarcava(SitioID, Medicion, Profundidad, Ancho, Distancia, Azimut)"
                        + "VALUES(" + sitioID + ", " + medicion + ", " + profundidad + ", " + ancho + ", " + distancia + ", " + azimut + ")");
                this.baseDatosLocal.commit();
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla SUELO_ErosionHidricaCarcava", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla SUELO_ErosionHidricaCarcava", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //15
    public void importarSueloErosionLaminar(String ruta) {
        this.querySelect = "SELECT SitioID, Numero, Ancho, Largo FROM SUELO_ErosionLaminar";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer sitioID = rs.getInt("SitioID");
                Integer numero = rs.getInt("Numero");
                Float ancho = rs.getFloat("Ancho");
                Float largo = rs.getFloat("Largo");
                ps.executeUpdate("INSERT INTO SUELO_ErosionLaminar(SitioID, Numero, Ancho, Largo)VALUES(" + sitioID + ", " + numero + ", " + ancho + ", " + largo + ")");
                this.baseDatosLocal.commit();
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla SUELO_ErosionLaminar", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla SUELO_ErosionLaminar", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //16
    public void importarSueloEvidenciaErosion(String ruta) {
        this.querySelect = "SELECT CoberturaSueloID, Punto, Dosel, LecturaTierraID FROM SUELO_EvidenciaErosion";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer coberturaSueloID = rs.getInt("CoberturaSueloID");
                Integer punto = rs.getInt("Punto");
                Integer dosel = rs.getInt("Dosel");
                Integer lecturaTierraID = rs.getInt("LecturaTierraID");
                ps.executeUpdate("INSERT INTO SUELO_EvidenciaErosion(CoberturaSueloID, Punto, Dosel, LecturaTierraID)VALUES(" + coberturaSueloID + ", " + punto + ", " + dosel + ", " + lecturaTierraID + ")");
                this.baseDatosLocal.commit();
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla SUELO_EvidenciaErosion", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla SUELO_EvidenciaErosion", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //17
    public void importarSueloHojarasca(String ruta) {
        this.querySelect = "SELECT SitioID, Punto, TipoHojarascaID, EspesorHO, EspesorF, PesoTotalHO, PesoTotalF, PesoMuestraHO, PesoMuestraF, "
                + "Observaciones FROM SUELO_Hojarasca";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer sitioID = rs.getInt("SitioID");
                Integer punto = rs.getInt("Punto");
                Integer tipoHojarascaID = rs.getInt("TipoHojarascaID");
                Float espesorHO = rs.getFloat("EspesorHO");
                Float espesorF = rs.getFloat("EspesorF");
                Float pesoTotalHO = rs.getFloat("PesoTotalHO");
                Float pesoTotalF = rs.getFloat("PesoTotalF");
                Float pesoMuestraHO = rs.getFloat("PesoMuestraHO");
                Float pesoMuestraF = rs.getFloat("PesoMuestraF");
                String observaciones = rs.getString("Observaciones");
                ps.executeUpdate("INSERT INTO SUELO_Hojarasca(SitioID, Punto, TipoHojarascaID, EspesorHO, EspesorF, PesoTotalHO, PesoTotalF, PesoMuestraHO, PesoMuestraF, "
                        + "Observaciones)VALUES(" + sitioID + ", " + punto + ", " + tipoHojarascaID + ", " + espesorHO + ", " + espesorF + ", " + pesoTotalHO + ", " + pesoTotalF + ", " + pesoMuestraHO + ", " + pesoMuestraF + ", '" + observaciones + "')");
                this.baseDatosLocal.commit();
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla SUELO_Hojarasca"
                    + e.getClass().getName() + " : " + e.getMessage(), "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla SUELO_Hojarasca", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //18
    public void importarSueloLongitudCanalillo(String ruta) {
        this.querySelect = "SELECT SitioID, CampoLongitud, Longitud FROM SUELO_LongitudCanalillo";
        this.queryInsert = "INSERT INTO SUELO_LongitudCanalillo(SitioID, CampoLongitud, Longitud)VALUES(?, ?, ?)";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer sitioID = rs.getInt("SitioID");
                Integer campoLongitud = rs.getInt("CampoLongitud");
                Float longitud = rs.getFloat("Longitud");
                ps.executeUpdate("INSERT INTO SUELO_LongitudCanalillo(SitioID, CampoLongitud, Longitud)"
                        + "VALUES(" + sitioID + ", " + campoLongitud + ", " + longitud + ")");
                this.baseDatosLocal.commit();
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla SUELO_LongitudCanalillo", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla SUELO_LongitudCanalillo", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //19
    public void importarSueloLongitudCarcava(String ruta) {
        this.querySelect = "SELECT SitioID, CampoLongitud, Longitud FROM SUELO_LongitudCarcava";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer sitioID = rs.getInt("SitioID");
                Integer campoLongitud = rs.getInt("CampoLongitud");
                Float longitud = rs.getFloat("Longitud");
                ps.executeUpdate("INSERT INTO SUELO_LongitudCarcava(SitioID, CampoLongitud, Longitud)VALUES(" + sitioID + ", " + campoLongitud + ", " + longitud + ")");
                this.baseDatosLocal.commit();
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla SUELO_LongitudCarcava", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla SUELO_LongitudCarcava", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //20
    public void importarSueloLongitudMonticulo(String ruta) {
        this.querySelect = "SELECT SitioID, CampoLongitud, Longitud FROM SUELO_LongitudMonticulo";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer sitioID = rs.getInt("SitioID");
                Integer campoLongitud = rs.getInt("CampoLongitud");
                Float longitud = rs.getFloat("Longitud");
                ps.executeUpdate("INSERT INTO SUELO_LongitudMonticulo(SitioID, CampoLongitud, Longitud)VALUES(" + sitioID + ", " + campoLongitud + ", " + longitud + ")");
                this.baseDatosLocal.commit();
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla SUELO_LongitudMonticulo", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla SUELO_LongitudMonticulo", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //21
    public void importarSueloMedicionCanalillos(String ruta) {
        this.querySelect = "SELECT SitioID, NumeroCanalillos, ProfundidadPromedio, Longitud, Volumen FROM SUELO_MedicionCanalillos";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer sitioID = rs.getInt("SitioID");
                Integer numeroCanalillos = rs.getInt("NumeroCanalillos");
                Float profundidadPromedio = rs.getFloat("ProfundidadPromedio");
                Float longitud = rs.getFloat("Longitud");
                Float volumen = rs.getFloat("Volumen");
                ps.executeUpdate("INSERT INTO SUELO_MedicionCanalillos(SitioID, NumeroCanalillos, ProfundidadPromedio, Longitud, Volumen)"
                        + "VALUES(" + sitioID + ", " + numeroCanalillos + ", " + profundidadPromedio + ", " + longitud + ", " + volumen + ")");
                this.baseDatosLocal.commit();
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla SUELO_MedicionCanalillos", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla SSUELO_MedicionCanalillos", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //22
    public void importarSueloMedicionCarcavas(String ruta) {
        this.querySelect = "SELECT SitioID, NumeroCarcavas, ProfundidadPromedio, AnchoPromedio, Longitud, Volumen FROM SUELO_MedicionCarcavas";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer sitioID = rs.getInt("SitioID");
                Integer numeroCarcavas = rs.getInt("NumeroCarcavas");
                Float profundidadPromedio = rs.getFloat("ProfundidadPromedio");
                Float anchoPromedio = rs.getFloat("AnchoPromedio");
                Float longitud = rs.getFloat("Longitud");
                Float volumen = rs.getFloat("Volumen");
                ps.executeUpdate("INSERT INTO SUELO_MedicionCanalillos(SitioID, NumeroCarcavas, ProfundidadPromedio, AnchoPromedio, Longitud, Volumen)"
                        + "VALUES(" + sitioID + ", " + numeroCarcavas + ", " + profundidadPromedio + ", " + anchoPromedio + ", " + longitud + ", " + volumen + ")");
                this.baseDatosLocal.commit();
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla SUELO_MedicionCarcavas ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla SSUELO_MedicionCarcavas ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //23
    public void importarSueloMedicionDunas(String ruta) {
        this.querySelect = "SELECT SitioID, NumeroDunas, AlturaPromedio, AnchoPromedio, Longitud, Volumen FROM SUELO_MedicionDunas";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer sitioID = rs.getInt("SitioID");
                Integer numeroDunas = rs.getInt("NumeroDunas");
                Float alturaPromedio = rs.getFloat("AlturaPromedio");
                Float anchoPromedio = rs.getFloat("AnchoPromedio");
                Float longitud = rs.getFloat("Longitud");
                Float volumen = rs.getFloat("Volumen");
                ps.executeUpdate("INSERT INTO SUELO_MedicionCanalillos(SitioID, NumeroCarcavas, ProfundidadPromedio, AnchoPromedio, Longitud, Volumen)"
                        + "VALUES(" + sitioID + ", " + numeroDunas + ", " + alturaPromedio + ", " + anchoPromedio + ", " + longitud + ", " + volumen + ")");
                this.baseDatosLocal.commit();
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla SUELO_MedicionCarcavas ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla SSUELO_MedicionCarcavas ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //24
    public void importarSueloMuestras(String ruta) {
        this.querySelect = "SELECT SitioID, ProfundidadID, PesoMuestra, Lectura1, Lectura2, Lectura3, Lectura4, "
                + "Promedio, ClaveColecta FROM SUELO_Muestras";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer sitioID = rs.getInt("SitioID");
                Integer profundidadID = rs.getInt("ProfundidadID");
                Float pesoMuestra = rs.getFloat("PesoMuestra");
                Float lectura1 = rs.getFloat("Lectura1");
                Float lectura2 = rs.getFloat("Lectura2");
                Float lectura3 = rs.getFloat("Lectura3");
                Float lectura4 = rs.getFloat("Lectura4");
                Float promedio = rs.getFloat("Promedio");
                String claveColecta = rs.getString("ClaveColecta");
                ps.executeUpdate("INSERT INTO SUELO_Muestras(SitioID, ProfundidadID, PesoMuestra, Lectura1, Lectura2, Lectura3, Lectura4, "
                        + "Promedio, 'ClaveColecta')VALUES(" + sitioID + ", " + profundidadID + ", " + pesoMuestra + ", " + lectura1
                        + ", " + lectura2 + ", " + lectura3 + ", " + lectura4 + ", " + promedio + ", " + claveColecta + ")");
                this.baseDatosLocal.commit();
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla SUELO_Muestras ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla SUELO_Muestras ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //25
    public void importarSueloMuestrasPerfil(String ruta) {
        this.querySelect = "SELECT SitioID, GradosLatitud, MinutosLatitud, SegundosLatitud, GradosLongitud, MinutosLongitud, SegundosLongitud, Elevacion, "
                + "DiametroInterno, DiametroExterno, Altura, Observaciones FROM SUELO_MuestrasPerfil";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
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
                ps.executeUpdate("INSERT INTO SUELO_MuestrasPerfil(SitioID, GradosLatitud, MinutosLatitud, SegundosLatitud, GradosLongitud, MinutosLongitud, SegundosLongitud, Elevacion, "
                        + "DiametroInterno, DiametroExterno, Altura, Observaciones)VALUES(" + sitioID + ", " + gradosLatitud + ", " + minutosLatitud + ", " + segundosLatitud + ", " + gradosLongitud
                        + ", " + minutosLongitud + ", " + segundosLongitud + ", " + elevacion + ", " + diametroInterno + ", " + diametroExterno + ", " + altura + ", '" + observaciones + "')");
                this.baseDatosLocal.commit();
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla SUELO_MuestrasPerfil ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla SUELO_MuestrasPerfil ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //26
    public void importarSueloPavimentos(String ruta) {
        this.querySelect = "SELECT SitioID, Numero, Ancho, Profundidad FROM SUELO_Carcava";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer sitioID = rs.getInt("SitioID");
                Integer numero = rs.getInt("Numero");
                Float ancho = rs.getFloat("Ancho");
                Float profundidad = rs.getFloat("Profundidad");
                ps.executeUpdate("INSERT INTO SUELO_Carcava(SitioID, Numero, Ancho, Profundidad)VALUES(" + sitioID + ", " + numero + ", " + ancho + ", " + profundidad + ")");
                this.baseDatosLocal.commit();
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla SUELO_PavimentosErosión", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla SUELO_PavimentosErosión", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //27
    public void importarSueloPedestal(String ruta) {
        this.querySelect = "SELECT SitioID, Numero, Altura FROM SUELO_Pedestal";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer sitioID = rs.getInt("SitioID");
                Integer numero = rs.getInt("Numero");
                Float altura = rs.getFloat("Altura");
                ps.executeUpdate("INSERT INTO SUELO_Pedestal(SitioID, Numero, Altura)VALUES(" + sitioID + ", " + numero + ", " + altura + ")");
                this.baseDatosLocal.commit();
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla SUELO_Pedestal", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla SUELO_Pedestal", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //28
    public void importarSueloProfundidad(String ruta) {
        this.querySelect = "SELECT SitioID, Punto, Profundidad030, Profundidad3060, PesoTotal030, PesoTotal3060, Equipo030, Equipo3060, "
                + "Observaciones FROM SUELO_Profundidad";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer sitioID = rs.getInt("SitioID");
                Integer punto = rs.getInt("Punto");
                Float profundidad030 = rs.getFloat("Profundidad030");
                Float profundidad3060 = rs.getFloat("Profundidad3060");
                Float pesoTotal030 = rs.getFloat("PesoTotal030");
                Float pesoTotal3060 = rs.getFloat("PesoTotal3060");
                Float equipo030 = rs.getFloat("Equipo030");
                Float equipo3060 = rs.getFloat("Equipo3060");
                String observaciones = rs.getString("Observaciones");
                ps.executeUpdate("INSERT INTO SUELO_Profundidad(SitioID, Punto, Profundidad030, Profundidad3060, PesoTotal030, PesoTotal3060, Equipo030, Equipo3060, "
                        + "Observaciones)VALUES(" + sitioID + ", " + punto + ", " + profundidad030 + ", " + profundidad3060 + ", " + pesoTotal030 + ", " + pesoTotal3060 + ", '" + equipo030 + "', '" + equipo3060 + "', '" + observaciones + "')");
                this.baseDatosLocal.commit();
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla SUELO_Profundidad ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla SUELO_Profundidad ", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

//29
    public void importarSueloInformacion(String ruta) {
        this.querySelect = "SELECT SitioID, UsoSueloID, OtroUsoSuelo, Espesor, PendienteDominante, Observaciones, NumeroCanalillos, ProfundidadPromedioCanalillos, "
                + "AnchoPromedioCanalillos, LongitudCanalillos, VolumenCanalillos, NumeroCarcavas, ProfundidadPromedioCarcavas, AnchoPromedioCarcavas, LongitudCarcavas, VolumenCarcavas, "
                + "NumeroMonticulos, AlturaPromedioMonticulos, AnchoPromedioMonticulos, LongitudPromedioMonticulos, VolumenMonticulos FROM SUELO_Suelo";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer sitioID = rs.getInt("SitioID");
                Integer usoSueloID = rs.getInt("UsoSueloID");
                String otroUsoSuelo = rs.getString("OtroUsoSuelo");
                Float espesor = rs.getFloat("Espesor");
                Integer pendienteDominante = rs.getInt("PendienteDominante");
                String observaciones = rs.getString("Observaciones");
                Integer numeroCanalillos = rs.getInt("NumeroCanalillos");
                Float profundidadPromedioCanalillos = rs.getFloat("ProfundidadPromedioCanalillos");
                Float anchoPromedioCanalillos = rs.getFloat("AnchoPromedioCanalillos");
                Float longitudCanalillos = rs.getFloat("LongitudCanalillos");
                Float volumenCanalillos = rs.getFloat("VolumenCanalillos");
                Integer numeroCarcavas = rs.getInt("NumeroCarcavas");
                Float profundidadPromedioCarcavas = rs.getFloat("ProfundidadPromedioCarcavas");
                Float anchoPromedioCarcavas = rs.getFloat("AnchoPromedioCarcavas");
                Float longitudPromedioCarcavas = rs.getFloat("LongitudCarcavas");
                Float volumenCarcavas = rs.getFloat("VolumenCarcavas");
                Integer numeroMonticulos = rs.getInt("NumeroMonticulos");
                Float alturaPromedioMonticulos = rs.getFloat("AlturaPromedioMonticulos");
                Float anchoPromedioMoticulos = rs.getFloat("AnchoPromedioMonticulos");
                Float longitudPromedioMonticulos = rs.getFloat("LongitudPromedioMonticulos");
                Float volumenMonticulos = rs.getFloat("VolumenMonticulos");
                ps.executeUpdate("INSERT INTO SUELO_Suelo(SitioID, UsoSueloID, OtroUsoSuelo, Espesor, PendienteDominante, Observaciones, NumeroCanalillos, ProfundidadPromedioCanalillos, "
                        + "AnchoPromedioCanalillos, LongitudCanalillos, VolumenCanalillos, NumeroCarcavas, ProfundidadPromedioCarcavas, AnchoPromedioCarcavas, LongitudCarcavas, VolumenCarcavas, "
                        + "NumeroMonticulos, AlturaPromedioMonticulos, AnchoPromedioMonticulos, LongitudPromedioMonticulos, VolumenMonticulos)"
                        + "VALUES(" + sitioID + ", " + usoSueloID + ", '" + otroUsoSuelo + "', " + espesor + ", " + pendienteDominante + ", '" + observaciones + "', " + numeroCanalillos
                        + ", " + profundidadPromedioCanalillos + ", " + anchoPromedioCanalillos + ", " + longitudCanalillos + ", " + volumenCanalillos + ", " + numeroCarcavas + ", " + profundidadPromedioCarcavas
                        + ", " + anchoPromedioCarcavas + ", " + longitudPromedioCarcavas + ", " + volumenCarcavas + ", " + numeroMonticulos + ", " + alturaPromedioMonticulos + ", " + anchoPromedioMoticulos
                        + ", " + longitudPromedioMonticulos + ", " + volumenMonticulos + ")");
                this.baseDatosLocal.commit();
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla SUELO_Suelo", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla SUELO_Suelo", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

//30
    public void importarSueloVarillasErosion(String ruta) {
        this.querySelect = "SELECT SitioID, NoVarilla, Azimut, Distancia, Profundidad FROM SUELO_VarillaErosion";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer sitioID = rs.getInt("SitioID");
                Integer noVarilla = rs.getInt("NoVarilla");
                Integer azimut = rs.getInt("Azimut");
                Float distancia = rs.getFloat("Distancia");
                Float profundidad = rs.getFloat("Profundidad");
                ps.executeUpdate("INSERT INTO SUELO_VarillaErosion(SitioID, NoVarilla, Azimut, Distancia, Profundidad)"
                        + "VALUES(" + sitioID + ", " + noVarilla + ", " + azimut + ", " + distancia + ", " + profundidad + ")");
                this.baseDatosLocal.commit();
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla SUELO_VarillasErosion", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla SUELO_VarillasErosion", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

//31
    public void importarCarbonoCoberturaDosel(String ruta) {
        this.querySelect = "SELECT SitioID, Transecto, Punto1, Punto2, Punto3, Punto4, Punto5, Punto6, Punto7, "
                + "Punto8, Punto9, Punto10 FROM CARBONO_CoberturaDosel";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
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
                ps.executeUpdate("INSERT INTO CARBONO_CoberturaDosel(SitioID, Transecto, Punto1, Punto2, Punto3, Punto4, Punto5, Punto6, Punto7, "
                        + "Punto8, Punto9, Punto10 )VALUES(" + sitioID + ", " + transecto + ", " + punto1 + ", " + punto2 + ", " + punto3 + ", " + punto4
                        + ", " + punto5 + ", " + punto6 + ", " + punto7 + ", " + punto8 + ", " + punto9 + ", " + punto10 + ")");
                this.baseDatosLocal.commit();
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla CARBONO_CoberturaDosel", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla CARBONO_CoberturaDosel", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //32
    public void importarCarbonoCubiertaVegetal(String ruta) {
        this.querySelect = "SELECT SitioID, Transecto, ComponenteID, Altura5, Altura10 FROM CARBONO_CubiertaVegetal";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer sitioID = rs.getInt("SitioID");
                Integer transecto = rs.getInt("Transecto");
                Integer componenteID = rs.getInt("ComponenteID");
                Float altura5 = rs.getFloat("Altura5");
                Float altura10 = rs.getFloat("Altura10");
                ps.executeUpdate("INSERT INTO CARBONO_CubiertaVegetal(SitioID, Transecto, ComponenteID, Altura5, Altura10)"
                        + "VALUES(" + sitioID + ", " + transecto + ", " + componenteID + ", " + altura5 + ", " + altura10 + ")");
                this.baseDatosLocal.commit();
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla CARBONO_CubiertaVegetal", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla CARBONO_CubiertaVegetal", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

//33
    public void importarCarbonoLongitudComponente(String ruta) {
        this.querySelect = "SELECT SitioID, Consecutivo, Transecto, ComponenteID, FamiliaID, GeneroID, EspecieID, InfraespecieID, NombreComun, Segmento1, Segmento2, Segmento3, Segmento4, "
                + "Segmento5, Segmento6, Segmento7, Segmento8, Segmento9, Segmento10, Total, ClaveColecta FROM CARBONO_LongitudComponente";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer sitioID = rs.getInt("SitioID");
                Integer consecutivo = rs.getInt("Consecutivo");
                Integer transecto = rs.getInt("Transecto");
                Integer componenteID = rs.getInt("ComponenteID");
                Integer familiaID = rs.getInt("FamiliaID");
                Integer generoID = rs.getInt("GeneroID");
                Integer especieID = rs.getInt("EspecieID");
                Integer infraespecieID = rs.getInt("InfraespecieID");
                String nombreComun = rs.getString("NombreComun");
                Integer segmento1 = rs.getInt("Segmento1");
                Integer segmento2 = rs.getInt("Segmento2");
                Integer segmento3 = rs.getInt("Segmento3");
                Integer segmento4 = rs.getInt("Segmento4");
                Integer segmento5 = rs.getInt("Segmento5");
                Integer segmento6 = rs.getInt("Segmento6");
                Integer segmento7 = rs.getInt("Segmento7");
                Integer segmento8 = rs.getInt("Segmento8");
                Integer segmneto9 = rs.getInt("Segmento9");
                Integer segmento10 = rs.getInt("Segmento10");
                Integer total = rs.getInt("Total");
                String claveColecta = rs.getString("ClaveColecta");
                ps.executeUpdate("INSERT INTO CARBONO_LongitudComponente(SitioID, Consecutivo, Transecto, ComponenteID, FamiliaID, GeneroID, EspecieID, InfraespecieID, NombreComun, Segmento1, Segmento2, Segmento3, Segmento4, "
                        + "Segmento5, Segmento6, Segmento7, Segmento8, Segmento9, Segmento10, Total, ClaveColecta)VALUES(" + sitioID + ", " + consecutivo + ", " + transecto + ", " + componenteID + ", " + familiaID + ", " + generoID
                        + ", " + especieID + ", " + infraespecieID + ", '" + nombreComun + "', " + segmento1 + ", " + segmento2 + ", " + segmento3 + ", " + segmento4 + ", " + segmento5 + ", " + segmento6 + ", " + segmento7 + ", " + segmento8
                        + ", " + segmneto9 + ", " + segmento10 + ", " + total + ", '" + claveColecta + "')");
                this.baseDatosLocal.commit();
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla CARBONO_LongitudComponente", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla CARBONO_LongitudComponente"
                        + e.getClass().getName() + " : " + e.getMessage(), "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //34
    public void importarCarbonoMaterialLenioso100(String ruta) {
        this.querySelect = "SELECT SitioID, Transecto, Pendiente, UnaHora, DiezHoras, CienHoras FROM CARBONO_MaterialLenioso100";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer sitioID = rs.getInt("SitioID");
                Integer transecto = rs.getInt("Transecto");
                Integer pendiente = rs.getInt("Pendiente");
                Integer unaHora = rs.getInt("UnaHora");
                Integer diezhoras = rs.getInt("DiezHoras");
                Integer cienHoras = rs.getInt("CienHoras");
                ps.executeUpdate("INSERT INTO CARBONO_MaterialLenioso100(SitioID, Transecto, Pendiente, UnaHora, DiezHoras, CienHoras)VALUES"
                        + "(" + sitioID + ", " + transecto + ", " + pendiente + ", " + unaHora + ", " + diezhoras + ", " + cienHoras + ")");
                this.baseDatosLocal.commit();
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla CARBONO_MaterialLenioso100", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla CARBONO_MaterialLenioso100", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

//35
    public void importarCarbonoMaterialLenioso1000(String ruta) {
        this.querySelect = "SELECT SitioID, Transecto, Diametro, Grado FROM CARBONO_MaterialLenioso1000";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer sitioID = rs.getInt("SitioID");
                Integer transecto = rs.getInt("Transecto");
                Float diametro = rs.getFloat("Diametro");
                Integer grados = rs.getInt("Grado");
                ps.executeUpdate("INSERT INTO CARBONO_MaterialLenioso1000(SitioID, Transecto, Diametro, Grado)VALUES(" + sitioID + ", " + transecto + ", " + diametro + ", " + grados + ")");
                this.baseDatosLocal.commit();
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla CARBONO_MaterialLenioso1000", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla CARBONO_MaterialLenioso1000", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

//36
    public void importarTaxonomiaArbolado(String ruta) {
        this.querySelect = "SELECT SitioID, Consecutivo, NoIndividuo, NoRama, Azimut, Distancia, FamiliaID, GeneroID, EspecieID, InfraespecieID, "
                + "NombreComun, EsSubmuestra, FormaVidaID, FormaFusteID, CondicionID, MuertoPieID, GradoPutrefaccionID, TipoToconID, DiametroNormal, "
                + "DiametroBasal, AlturaTotal, AnguloInclinacion, AlturaFusteLimpio, AlturaComercial, DiametroCopaNS, DiametroCopaEO, ProporcionCopaVivaID, ExposicionCopaID, "
                + "PosicionCopaID, DensidadCopaID, MuerteRegresivaID, TransparenciaFollajeID, VigorID, ClaveColecta FROM TAXONOMIA_Arbolado";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer sitioID = rs.getInt("SitioID");
                Integer consecutivo = rs.getInt("Consecutivo");
                Integer noIndividuo = rs.getInt("NoIndividuo");
                Integer noRama = rs.getInt("NoRama");
                Integer azimut = rs.getInt("Azimut");
                Float distancia = rs.getFloat("Distancia");
                Integer familiaID = rs.getInt("FamiliaID");
                Integer generoID = rs.getInt("GeneroID");
                Integer especieID = rs.getInt("EspecieID");
                Integer infraespecieID = rs.getInt("InfraespecieID");
                String nombreComun = rs.getString("NombreComun");
                Integer esSubmuestra = rs.getInt("EsSubmuestra");
                Integer formaVidaID = rs.getInt("FormaVidaID");
                Integer formaFusteID = rs.getInt("FormaFusteID");
                Integer condicionID = rs.getInt("CondicionID");
                Integer muertoPieID = rs.getInt("MuertoPieID");
                Integer gradoPutrefaccionID = rs.getInt("GradoPutrefaccionID");
                Integer tipoToconID = rs.getInt("TipoToconID");
                Float diametroNormal = rs.getFloat("DiametroNormal");
                Integer diametroBasal = rs.getInt("DiametroBasal");
                Float alturaTotal = rs.getFloat("AlturaTotal");
                Float anguloInclinacion = rs.getFloat("AnguloInclinacion");
                Float alturaFusteLimpio = rs.getFloat("AlturaFusteLimpio");
                Float alturaComercial = rs.getFloat("AlturaComercial");
                Float diametroCopaNS = rs.getFloat("DiametroCopaNS");
                Float diametroCopaEO = rs.getFloat("DiametroCopaEO");
                Integer proporcionCopaVivaID = rs.getInt("ProporcionCopaVivaID");
                Integer exposicionCopaID = rs.getInt("ExposicionCopaID");
                Integer posicionCopaID = rs.getInt("PosicionCopaID");
                Integer densidadCopaID = rs.getInt("DensidadCopaID");
                Integer muerteRegresivaID = rs.getInt("MuerteRegresivaID");
                Integer transparenciaFollajeID = rs.getInt("TransparenciaFollajeID");
                Integer vigorID = rs.getInt("VigorID");
                String claveColecta = rs.getString("ClaveColecta");
                ps.executeUpdate("INSERT INTO TAXONOMIA_Arbolado(SitioID, Consecutivo, NoIndividuo, NoRama, Azimut, Distancia, FamiliaID, GeneroID, EspecieID, InfraespecieID, "
                        + "NombreComun, EsSubmuestra, FormaVidaID, FormaFusteID, CondicionID, MuertoPieID, GradoPutrefaccionID, TipoToconID, DiametroNormal, "
                        + "DiametroBasal, AlturaTotal, AnguloInclinacion, AlturaFusteLimpio, AlturaComercial, DiametroCopaNS, DiametroCopaEO, ProporcionCopaVivaID, ExposicionCopaID, "
                        + "PosicionCopaID, DensidadCopaID, MuerteRegresivaID, TransparenciaFollajeID, VigorID, ClaveColecta)VALUES(" + sitioID + ", " + consecutivo + ", " + noIndividuo
                        + ", " + noRama + ", " + azimut + ", " + distancia + ", " + familiaID + ", " + generoID + ", " + especieID + ", " + infraespecieID + ", '" + nombreComun + "'"
                        + ", " + esSubmuestra + ", " + formaVidaID + ", " + formaFusteID + ", " + condicionID + ", " + muertoPieID + ", " + gradoPutrefaccionID + ", " + tipoToconID
                        + ", " + diametroNormal + ", " + diametroBasal + ", " + alturaTotal + ", " + anguloInclinacion + ", " + alturaFusteLimpio + ", " + alturaComercial + ", " + diametroCopaNS
                        + ", " + diametroCopaEO + ", " + proporcionCopaVivaID + ", " + exposicionCopaID + ", " + posicionCopaID + ", " + densidadCopaID + ", " + muerteRegresivaID + ", " + transparenciaFollajeID
                        + ", " + vigorID + ", '" + claveColecta + "')");
                this.baseDatosLocal.commit();
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla TAXONOMIA_Arbolado", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla TAXONOMIA_Arbolado", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

//37
    public void importarSubmuestra(String ruta) {
        this.querySelect = "SELECT SitioID, ArboladoID, DiametroBasal, Edad, NumeroAnillos25, LongitudAnillos10, GrozorCorteza FROM ARBOLADO_Submuestra";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer sitioID = rs.getInt("SitioID");
                Integer arboladoID = rs.getInt("ArboladoID");
                Float diametroBasal = rs.getFloat("DiametroBasal");
                Integer edad = rs.getInt("Edad");
                Integer numeroAnillos25 = rs.getInt("NumeroAnillos25");
                Float longitudAnillos10 = rs.getFloat("LongitudAnillos10");
                Float grozorCorteza = rs.getFloat("GrozorCorteza");
                ps.executeUpdate("INSERT INTO ARBOLADO_Submuestra(SitioID, ArboladoID, DiametroBasal, Edad, NumeroAnillos25, LongitudAnillos10, GrozorCorteza)VALUES("
                        + sitioID + ", " + arboladoID + ", " + diametroBasal + ", " + edad + ", " + numeroAnillos25 + ", " + longitudAnillos10 + ", " + grozorCorteza + ")");
                this.baseDatosLocal.commit();
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla ARBOLADO_Submuestra", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla ARBOLADO_Submuestra"
                        + e.getClass().getName() + " : " + e.getMessage(), "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

//38
    public void importarSubmuestraTroza(String ruta) {
        this.querySelect = "SELECT SubmuestraID, NoTroza, TipoTrozaID FROM ARBOLADO_Troza";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer submuestraID = rs.getInt("SubmuestraID");
                Integer noTroza = rs.getInt("NoTroza");
                Integer tipoTrozaID = rs.getInt("TipoTrozaID");
                ps.executeUpdate("INSERT INTO ARBOLADO_Troza(SubmuestraID, NoTroza, TipoTrozaID)VALUES(" + submuestraID + ", " + noTroza + ", " + tipoTrozaID + ")");
                this.baseDatosLocal.commit();
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla ARBOLADO_Troza", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla ARBOLADO_Troza", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void importarSubmuestraObservaciones(String ruta){
        this.querySelect = "SELECT SitioID, Observaciones FROM SUBMUESTRA_Observaciones";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer sitioID = rs.getInt("SitioID");
                String observaciones = rs.getString("Observaciones");
                ps.executeUpdate("INSERT INTO SUBMUESTRA_Observaciones(SitioID, Observaciones)VALUES(" + sitioID + ", '" + observaciones + ")");
                this.baseDatosLocal.commit();
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla SUBMUESTRA_Observaciones", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla SUBMUESTRA_Observaciones", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

//39
    public void importarArboladoDanioSeveridad(String ruta) {
        this.querySelect = "SELECT ArboladoID, NumeroDanio, AgenteDanioID, SeveridadID FROM ARBOLADO_DanioSeveridad";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer arbolID = rs.getInt("ArboladoID");
                Integer noDanio = rs.getInt("NumeroDanio");
                Integer agenteDanioID = rs.getInt("AgenteDanioID");
                Integer severidadID = rs.getInt("SeveridadID");
                ps.executeUpdate("INSERT INTO ARBOLADO_DanioSeveridad(ArboladoID, NumeroDanio, AgenteDanioID, SeveridadID)VALUES(" + arbolID + ", "
                        + noDanio + ", " + agenteDanioID + ", " + severidadID + ")");
                this.baseDatosLocal.commit();
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla ARBOLADO_DanioSeveridad", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla ARBOLADO_DanioSeveridad", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //40
    public void importarTaxonomiaColectaBotanica(String ruta) {
        this.querySelect = "SELECT UPMID, FamiliaID, GeneroID, EspecieID, Infraespecie, NombreComun ClaveColecta, ContraFuertes, Exudado, IndicarExudado, Color, IndicarColor, CambioColor, AceitesVolatiles, ColorFlor, IndicarColorFlor, HojasTejidoVivo, FotoFlor, FotoFruto, FotoHojasArriba, FotoHojasAbajo, FotoArbolCompleto, FotoCorteza, VirutaIncluida, "
                + "CortezaIncluida, MaderaIncluida, Observaciones FROM TAXONOMIA_COlectaBotanica";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer UPMID = rs.getInt("UPMID");
                Integer familiaID = rs.getInt("FamiliaID");
                Integer generoID = rs.getInt("GeneroID");
                Integer especieID = rs.getInt("EspecieID");
                String infraespecie = rs.getString("Infraespecie");
                String nombreComun = rs.getString("NombreComun");
                String claveColecta = rs.getString("ClaveColecta");
                Integer contraFuertes = rs.getInt("ContraFuertes");
                Integer exudado = rs.getInt("Exudado");
                String indicarExudado = rs.getString("IndicarExudado");
                Integer color = rs.getInt("Color");
                String indicarColor = rs.getString("IndicarColor");
                Integer cambioColor = rs.getInt("CambioColor");
                Integer aceitesVolatiles = rs.getInt("AceitesVolatiles");
                Integer colorFlor = rs.getInt("ColorFlor");
                String indicarColorFlor = rs.getString("IndicarColorFlor");
                Integer hojasTejidoVivo = rs.getInt("HojasTejidoVivo");
                Integer fotoFlor = rs.getInt("FotoFlor");
                Integer fotoFruto = rs.getInt("FotoFruto");
                Integer fotoHojasArriba = rs.getInt("FotoHojasArriba");
                Integer fotoHojasAbajo = rs.getInt("FotoHojasAbajo");
                Integer fotoArbolCompleto = rs.getInt("FotoArbolCompleto");
                Integer fotoCorteza = rs.getInt("FotoCorteza");
                Integer virutaIncluida = rs.getInt("VirutaIncluida");
                Integer cortezaIncluida = rs.getInt("CortezaIncluida");
                Integer maderaIncluida = rs.getInt("MaderaIncluida");
                String observaciones = rs.getString("Observaciones");
                ps.executeUpdate("INSERT INTO TAXONOMIA_ColectaBotanica(UPMID, FamiliaID, GeneroID, EspecieID, Infraespecie, NombreComun, ClaveColecta, ContraFuertes, Exudado, IndicarExudado, Color, IndicarColor, CambioColor, AceitesVolatiles, ColorFlor, IndicarColorFlor, FotoFruto, FotoHojasArriba, FotoHojasAbajo, FotoArbolCompleto, FotoCorteza, "
                        + "VirutaIncluida, CortezaIncluida, MaderaIncluida, Observaciones)VALUES(" + UPMID + ", " + familiaID + ", " + generoID + ", " + especieID + ", '" + infraespecie + "', '" + nombreComun + "', '" + claveColecta + "', " + contraFuertes + ", " + exudado + ", '" + indicarExudado + "', " + color + ", '" + indicarColor + "'"
                        + ", " + cambioColor + ", " + aceitesVolatiles + ", " + colorFlor + ", '" + indicarColorFlor + "', " + hojasTejidoVivo + ", " + fotoFlor + ", " + fotoFruto + ", " + fotoHojasArriba + ", " + fotoHojasAbajo + ", " + fotoArbolCompleto + ", " + fotoCorteza + ", " + virutaIncluida + ", " + cortezaIncluida + ", " + maderaIncluida
                        + ", " + observaciones + ")");
                this.baseDatosLocal.commit();
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla TAXONOMIA_ColectaBotanica", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla TAXONOMIA_ColectaBotanica", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

//41
    public void importarTaxonomiaRepoblado(String ruta) {
        this.querySelect = "SELECT SitioID, Consecutivo, FamiliaID, GeneroID, EspecieID, InfraespecieID, NombreComun, Frecuencia025150, Edad025150, Frecuencia151275, Edad151275, "
                + "Frecuencia275, Edad275, VigorID, DanioID, PorcentajeDanio, ClaveColecta FROM TAXONOMIA_Repoblado";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer sitioID = rs.getInt("SitioID");
                Integer consecutivo = rs.getInt("Consecutivo");
                Integer familiaID = rs.getInt("FamiliaID");
                Integer generoID = rs.getInt("GeneroID");
                Integer especieID = rs.getInt("EspecieID");
                Integer infraespecieID = rs.getInt("InfraespecieID");
                String nombreComun = rs.getString("NombreComun");
                Integer frecuencia025150 = rs.getInt("Frecuencia025150");
                Integer edad025150 = rs.getInt("Edad025150");
                Integer frecuencia151275 = rs.getInt("Frecuencia151275");
                Integer edad151275 = rs.getInt("Edad151275");
                Integer frecuencia275 = rs.getInt("Frecuencia275");
                Integer edad275 = rs.getInt("Edad275");
                Integer vigorID = rs.getInt("VigorID");
                Integer danioID = rs.getInt("DanioID");
                Integer porcentajeDanio = rs.getInt("PorcentajeDanio");
                String claveColecta = rs.getString("ClaveColecta");
                ps.executeUpdate("INSERT INTO TAXONOMIA_Repoblado(SitioID, Consecutivo, FamiliaID, GeneroID, EspecieID, InfraespecieID, NombreComun, Frecuencia025150, Edad025150, Frecuencia151275, Edad151275, "
                        + "Frecuencia275, Edad275, VigorID, DanioID, PorcentajeDanio, ClaveColecta)VALUES(" + sitioID + ", " + consecutivo + ", " + familiaID + ", " + generoID + ", " + especieID + ", " + infraespecieID + ""
                        + ", '" + nombreComun + "', " + frecuencia025150 + ", " + edad025150 + ", " + frecuencia151275 + ", " + edad151275 + ", " + frecuencia275 + ", " + edad275 + ", " + vigorID + ", " + danioID
                        + ", " + porcentajeDanio + ", '" + claveColecta + "')");
                this.baseDatosLocal.commit();
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla TAXONOMIA_Repoblado", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla TAXONOMIA_Repoblado", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

//42
    public void importarTaxonomiaRepobladoVM(String ruta) {
        this.querySelect = "SELECT SitioID, Consecutivo, FormaVidaID, FamiliaID, GeneroID, EspecieID, InfraespecieID, NombreComun, Frecuencia50, PorcentajeCobertura50, Frecuencia51200, PorcentajeCobertura51200,"
                + "Frecuencia200, PorcentajeCobertura200, VigorID, ClaveColecta FROM TAXONOMIA_RepobladoVM";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer sitioID = rs.getInt("SitioID");
                Integer consecutivo = rs.getInt("Consecutivo");
                Integer formaVidaID = rs.getInt("FormaVidaID");
                Integer familiaID = rs.getInt("FamiliaID");
                Integer generoID = rs.getInt("GeneroID");
                Integer especieID = rs.getInt("EspecieID");
                Integer infraespecieID = rs.getInt("InfraespecieID");
                String nombreComun = rs.getString("NombreComun");
                Integer frecuencia50 = rs.getInt("Frecuencia50");
                Integer porcentajeCobertura50 = rs.getInt("PorcentajeCobertura50");
                Integer frecuencia51200 = rs.getInt("Frecuencia51200");
                Integer porcentajeCobertura51200 = rs.getInt("PorcentajeCobertura51200");
                Integer frecuencia200 = rs.getInt("Frecuencia200");
                Integer porcentajeCobertura200 = rs.getInt("PorcentajeCobertura200");
                Integer vigorID = rs.getInt("VigorID");
                String claveColecta = rs.getString("ClaveColecta");
                ps.executeUpdate("INSERT INTO TAXONOMIA_RepobladoVM(SitioID, Consecutivo, FormaVidaID, FamiliaID, GeneroID, EspecieID, InfraespecieID, NombreComun, Frecuencia50, PorcentajeCobertura50, Frecuencia51200, PorcentajeCobertura51200, "
                        + "Frecuencia200, PorcentajeCobertura200, VigorID, ClaveColecta)VALUES(" + sitioID + ", " + consecutivo + ", " + formaVidaID + ", " + familiaID + ", " + generoID + ", " + especieID + ", " + infraespecieID + ", '" + nombreComun + "'"
                        + ", " + frecuencia50 + ", " + porcentajeCobertura50 + ", " + frecuencia51200 + ", " + porcentajeCobertura51200 + ", " + frecuencia200 + ", " + porcentajeCobertura200 + ", " + vigorID + ", '" + claveColecta + "')");
                this.baseDatosLocal.commit();
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla TAXONOMIA_RepobladoVM", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla TAXONOMIA_RepobladoVM", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

//43
    public void importarTaxonomiaSotoBosque(String ruta) {
        this.querySelect = "SELECT SitioID, Consecutivo, FamiliaID, GeneroID, EspecieID, InfraespecieID, NombreComun, Frecuencia025150, Cobertura025150, Frecuencia151275, Cobertura151275, Frecuencia275, Cobertura275, VigorID, DanioID, "
                + "PorcentajeDanio, ClaveColecta FROM TAXONOMIA_SotoBosque";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer sitioID = rs.getInt("SitioID");
                Integer consecutivo = rs.getInt("Consecutivo");
                Integer familiaID = rs.getInt("FamiliaID");
                Integer generoID = rs.getInt("GeneroID");
                Integer especieID = rs.getInt("EspecieID");
                Integer infraespecieID = rs.getInt("InfraespecieID");
                String nombreComun = rs.getString("NombreComun");
                Integer frecuencia025150 = rs.getInt("Frecuencia025150");
                Integer cobertura025150 = rs.getInt("Cobertura025150");
                Integer frecuencia151275 = rs.getInt("Frecuencia151275");
                Integer cobertura151275 = rs.getInt("Cobertura151275");
                Integer frecuencia275 = rs.getInt("Frecuencia275");
                Integer cobertura275 = rs.getInt("Cobertura275");
                Integer vigorID = rs.getInt("VigorID");
                Integer danioID = rs.getInt("DanioID");
                Integer porcentajeDanio = rs.getInt("PorcentajeDanio");
                String claveColecta = rs.getString("ClaveColecta");
                ps.executeUpdate("INSERT INTO TAXONOMIA_SotoBosque(SitioID, Consecutivo, FamiliaID, GeneroID, EspecieID, InfraespecieID, NombreComun, Frecuencia025150, Cobertura025150, Frecuencia151275, Cobertura151275, Frecuencia275, Cobertura275, VigorID, DanioID, "
                        + "PorcentajeDanio, ClaveColecta)VALUES(" + sitioID + ", " + consecutivo + ", " + familiaID + ", " + generoID + ", " + especieID + ", " + infraespecieID + ", '" + nombreComun + "', " + frecuencia025150 + ", " + cobertura025150 + ", " + frecuencia151275
                        + ", " + cobertura151275 + ", " + frecuencia275 + ", " + cobertura275 + ", " + vigorID + ", " + danioID + ", " + porcentajeDanio + ", '" + claveColecta + "')");
                this.baseDatosLocal.commit();
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla TAXONOMIA_SotoBosque", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla TAXONOMIA_SotoBosque", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

//44
    public void importarTaxonomiaVegetacionMayorGregarios(String ruta) {
        this.querySelect = "SELECT SitioID, Consecutivo, NoIndividuo, FormaVidaID, CondicionID, FamiliaID, GeneroID, EspecieID, InfraespecieID, NombreComun, FormaCrecimientoID, DensidadColoniaID, AlturaTotalMaxima, AlturaTotalMedia, AlturaTotalMinima, DiametroCoberturaMayor, "
                + "DiametroCoberturaMenor, VigorID, ClaveColecta FROM TAXONOMIA_VegetacionMayorGregarios";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer sitioID = rs.getInt("SitioID");
                Integer consecutivo = rs.getInt("Consecutivo");
                Integer noIndividuo = rs.getInt("NoIndividuo");
                Integer formaVidaID = rs.getInt("FormaVidaID");
                Integer condicionID = rs.getInt("CondicionID");
                Integer familiaID = rs.getInt("FamiliaID");
                Integer generoID = rs.getInt("GeneroID");
                Integer especieID = rs.getInt("EspecieID");
                Integer infraespecieID = rs.getInt("InfraespecieID");
                String nombreComun = rs.getString("NombreComun");
                Integer formaCrecimientoID = rs.getInt("FormaCrecimientoID");
                Integer densidadColoniaID = rs.getInt("DensidadColoniaID");
                Float alturaTotalMaxima = rs.getFloat("AlturaTotalMaxima");
                Float alturaTotalMedia = rs.getFloat("AlturaTotalMedia");
                Float alturaTotalMinima = rs.getFloat("AlturaTotalMinima");
                Float diametroCoberturaMayor = rs.getFloat("DiametroCoberturaMayor");
                Float diametroCoberturaMenor = rs.getFloat("DiametroCoberturaMenor");
                Integer vigorID = rs.getInt("VigorID");
                String claveColecta = rs.getString("ClaveColecta");
                ps.executeUpdate("INSERT INTO TAXONOMIA_VegetacionMayorGregarios(SitioID, Consecutivo, NoIndividuo, FormaVidaID, CondicionID, FamiliaID, GeneroID, EspecieID, InfraespecieID, NombreComun, FormaCrecimientoID, DensidadColoniaID, AlturaTotalMaxima, AlturaTotalMedia, AlturaTotalMinima, DiametroCoberturaMayor, "
                        + "DiametroCoberturaMenor, VigorID, ClaveColecta)VALUES(" + sitioID + ", " + consecutivo + ", " + noIndividuo + ", " + formaVidaID + ", " + condicionID + ", " + familiaID + ", " + generoID + ", " + especieID + ", " + infraespecieID + ", '" + nombreComun + "', " + formaCrecimientoID + ", " + densidadColoniaID
                        + ", " + alturaTotalMaxima + ", " + alturaTotalMedia + ", " + alturaTotalMinima + ", " + diametroCoberturaMayor + ", " + diametroCoberturaMenor + ", " + vigorID + ", '" + claveColecta + "')");
                this.baseDatosLocal.commit();
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla TAXONOMIA_VegetacionMayorGregarios", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla TAXONOMIA_VegetacionMayorGregarios", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

//45
    public void importarVegetacionMayorGDanioSeveridad(String ruta) {
        this.querySelect = "SELECT VegetacionMayorID, NumeroDanio, AgenteDanioID, SeveridadID FROM VEGETACIONMAYORG_DanioSeveridad";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer vegetacionMayorID = rs.getInt("VegetacionMayorID");
                Integer numeroDanio = rs.getInt("NumeroDanio");
                Integer agenteDanioID = rs.getInt("AgenteDanioID");
                Integer severidadID = rs.getInt("SeveridadID");
                ps.executeUpdate("INSERT INTO VEGETACIONMAYORG_DanioSeveridad(VegetacionMayorID, NumeroDanio, AgenteDanioID, SeveridadID)VALUES("
                        + "" + vegetacionMayorID + ", " + numeroDanio + ", " + agenteDanioID + ", " + severidadID + ")");
                this.baseDatosLocal.commit();
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla VEGETACIONMAYORG_DanioSeveridad", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla VEGETACIONMAYORG_DanioSeveridad", "Conexion BD", JOptionPane.ERROR_MESSAGE);

            }
        }
    }

//46
    public void importarTaxonomiaVegetacionMayorIndividual(String ruta) {
        this.querySelect = "SELECT SitioID, Consecutivo, NoIndividuo, FormaVidaID, CondicionID, FamiliaID, GeneroID, EspecieID, InfraespecieID, NombreComun, FormaGeometricaID, DensidadFollajeID, DiametroBase, AlturaTotal, DiametroCoberturaMayor, DiametroCoberturaMenor, VigorID, ClaveColecta FROM TAXONOMIA_VegetacionMayorIndividual";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer sitioID = rs.getInt("SitioID");
                Integer consecutivo = rs.getInt("Consecutivo");
                Integer noIndividuo = rs.getInt("NoIndividuo");
                Integer formaVidaID = rs.getInt("FormaVidaID");
                Integer condicionID = rs.getInt("CondicionID");
                Integer familiaID = rs.getInt("FamiliaID");
                Integer generoID = rs.getInt("GeneroID");
                Integer especieID = rs.getInt("EspecieID");
                String infraespecieID = rs.getString("InfraespecieID");
                String nombreComun = rs.getString("NombreComun");
                Integer formaGeometricaID = rs.getInt("FormaGeometricaID");
                Integer densidadFollajeID = rs.getInt("DensidadFollajeID");
                Float diametroBase = rs.getFloat("DiametroBase");
                Float alturaTotal = rs.getFloat("AlturaTotal");
                Float diametroCoberturaMayor = rs.getFloat("DiametroCoberturaMayor");
                Float diametroCoberturaMenor = rs.getFloat("DiametroCoberturaMenor");
                Integer vigorID = rs.getInt("VigorID");
                String claveColecta = rs.getString("ClaveColecta");
                ps.executeUpdate("INSERT INTO TAXONOMIA_VegetacionMayorIndividual(SitioID, Consecutivo, NoIndividuo, FormaVidaID, CondicionID, FamiliaID, GeneroID, EspecieID, InfraespecieID, NombreComun, FormaGeometricaID, DensidadFollajeID, DiametroBase, AlturaTotal, DiametroCoberturaMayor, DiametroCoberturaMenor, VigorID, ClaveColecta)"
                        + "VALUES(" + sitioID + ", " + consecutivo + ", " + noIndividuo + ", " + formaVidaID + ", " + condicionID + ", " + familiaID + ", " + generoID + ", " + especieID + ", " + infraespecieID + ", '" + nombreComun + "', " + formaGeometricaID + ", " + densidadFollajeID + ", " + diametroBase + ",  " + alturaTotal
                        + ", " + diametroCoberturaMayor + ", " + diametroCoberturaMenor + ", " + vigorID + ", '" + claveColecta + "')");
                this.baseDatosLocal.commit();
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla TAXONOMIA_VegetacionMayorIndividual", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla TAXONOMIA_VegetacionMayorIndividual", "Conexion BD", JOptionPane.ERROR_MESSAGE);

            }
        }
    }

//47
    public void importarVegetacionMayorIDanioSeveridad(String ruta) {
        this.querySelect = "SELECT VegetacionMayorID, NumeroDanio, AgenteDanioID, SeveridadID FROM VEGETACIONMAYORI_DanioSeveridad";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer vegetacionMayorID = rs.getInt("VegetacionMayorID");
                Integer numeroDanio = rs.getInt("NumeroDanio");
                Integer agenteDanio = rs.getInt("AgenteDanioID");
                Integer severidadID = rs.getInt("SeveridadID");
                ps.executeUpdate("INSERT INTO VEGETACIONMAYORI_DanioSeveridad(VegetacionMayorID, NumeroDanio, AgenteDanioID, SeveridadID)VALUES("
                        + vegetacionMayorID + ", " + numeroDanio + ", " + agenteDanio + ", " + severidadID + ")");
                this.baseDatosLocal.commit();
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla VEGETACIONMAYORI_DanioSeveridad", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla VEGETACIONMAYORI_DanioSeveridad", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

//48
    public void importarTaxonomiaVegetacionMenor(String ruta) {
        this.querySelect = "SELECT SitioID, Consecutivo, FamiliaID, GeneroID, EspecieID, InfraespecieID, NombreComun, FormaVidaID, CondicionID, Numero0110, Numero1125, Numero5175, Numero76100, Numero101125, Numero126150, Numero150, PorcentajeCobertura, VigorID, ClaveColecta FROM TAXONOMIA_VegetacionMenor";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer sitioID = rs.getInt("SitioID");
                Integer consecutivo = rs.getInt("Consecutivo");
                Integer familiaID = rs.getInt("FamiliaID");
                Integer generoID = rs.getInt("GeneroID");
                Integer especieID = rs.getInt("EspecieID");
                Integer infraespecieID = rs.getInt("InfraespecieID");
                String nombreComun = rs.getString("NombreComun");
                Integer formaVidaID = rs.getInt("FormaVidaID");
                Integer condicionID = rs.getInt("CondicionID");
                Integer numero0110 = rs.getInt("Numero0110");
                Integer numero1125 = rs.getInt("Numero1125");
                Integer numero5175 = rs.getInt("Numero5175");
                Integer numero76100 = rs.getInt("Numero76100");
                Integer numero101125 = rs.getInt("Numero101125");
                Integer numero126150 = rs.getInt("Numero126150");
                Integer numero150 = rs.getInt("Numero150");
                Integer porcentajeCobertura = rs.getInt("PorcentajeCobertura");
                Integer vigorID = rs.getInt("VigorID");
                String claveColecta = rs.getString("ClaveColecta");
                ps.executeUpdate("INSERT INTO TAXONOMIA_VegetacionMenor(SitioID, Consecutivo, FamiliaID, GeneroID, EspecieID, InfraespecieID, NombreComun, FormaVidaID, CondicionID, Numero0110, Numero1125, Numero5175, Numero76100, Numero101125, Numero126150, Numero150, PorcentajeCobertura, VigorID, ClaveColecta)VALUES("
                        + "" + sitioID + ", " + consecutivo + ", " + familiaID + ", " + generoID + ", " + especieID + ", " + infraespecieID + ", '" + nombreComun + "', " + formaVidaID + ", " + condicionID + ", " + numero0110 + ", " + numero1125 + ", " + numero5175 + ", " + numero76100 + ", " + numero101125
                        + ", " + numero126150 + ", " + numero150 + ", " + porcentajeCobertura + ", " + vigorID + ", '" + claveColecta + "')");
                this.baseDatosLocal.commit();
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla TAXONOMIA_VegetacionMenor", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla TAXONOMIA_VegetacionMenor", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

//49
    public void importarVegetacionMenorDanioSeveridad(String ruta) {
        this.querySelect = "SELECT VegetacionMenorID, NumeroDanio, AgenteDanioID, SeveridadID FROM VEGETACIONMENOR_DanioSeveridad";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer vegetacionMenorID = rs.getInt("VegetacionMenorID");
                Integer numeroDanio = rs.getInt("NumeroDanio");
                Integer agenteDanioID = rs.getInt("AgenteDanioID");
                Integer severidadID = rs.getInt("SeveridadID");
                ps.executeUpdate("INSERT INTO VEGETACIONMENOR_DanioSeveridad(VegetacionMenorID, NumeroDanio, AgenteDanioID, SeveridadID)"
                        + "VALUES(" + vegetacionMenorID + ", " + numeroDanio + ", " + agenteDanioID + ", " + severidadID + ")");
                this.baseDatosLocal.commit();
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla VEGETACIONMENOR_DanioSeveridad", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla VEGETACIONMENOR_DanioSeveridad", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void importarRepobladoDanioSeveridad(String ruta) {
        this.querySelect = "SELECT RepobladoVMID, NumeroDanio, AgenteDanioID, SeveridadID FROM REPOBLADO_AgenteDanio";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer repobladoVMID = rs.getInt("RepobladoVMID");
                Integer numeroDanio = rs.getInt("NumeroDanio");
                Integer agenteDanioID = rs.getInt("AgenteDanioID");
                Integer severidadID = rs.getInt("SeveridadID");
                ps.executeUpdate("INSERT INTO REPOBLADO_AgenteDanio(RepobladoVMID, NumeroDanio, AgenteDanioID, SeveridadID)"
                        + "VALUES(" + repobladoVMID + ", " + numeroDanio + ", " + agenteDanioID + ", " + severidadID + ")");
                this.baseDatosLocal.commit();
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla REPOBLADO_AgenteDanio", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla REPOBLADO_AgenteDanio", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //50
    public void importarUPMContacto(String ruta) {
        this.querySelect = "SELECT UPMID, TipoContacto, Nombre, Direccion, TipoTelefono, NumeroTelefono, TieneCorreo, DireccionCorreo, "
                + "TieneRadio, Canal, Frecuencia FROM UPM_Contacto";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
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

                ps.executeUpdate("INSERT INTO UPM_Contacto(UPMID, TipoContacto, Nombre, Direccion, TipoTelefono, NumeroTelefono, TieneCorreo, DireccionCorreo, "
                        + "TieneRadio, Canal, Frecuencia)VALUES(" + upmID + ", " + tipoContacto + " , '" + nombre + "', '" + direccion + "', " + tipoTelefono
                        + ", '" + numeroTelefono + "', " + tieneCorreo + ", '" + direccionCorreo + "' , " + tieneRadio + ", '" + canal + "', '" + frecuencia + "')");
                this.baseDatosLocal.commit();
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla UPM_Contacto", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla UPM_Contacto", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //51
    public void importarUPMEpifitas(String ruta) {
        this.querySelect = "SELECT UPMID, ClaseTipoID, PresenciaTroncosID, PresenciaRamasID FROM UPM_Epifita";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer upmID = rs.getInt("UPMID");
                Integer claseTipoID = rs.getInt("ClaseTipoID");
                Integer presenciaTroncosID = rs.getInt("PresenciaTroncosID");
                Integer presenciaRamasID = rs.getInt("PresenciaRamasID");
                ps.executeUpdate("INSERT INTO UPM_Epifita(UPMID, ClaseTipoID, PresenciaTroncosID, PresenciaRamasID)VALUES(" + upmID + ", " + claseTipoID
                        + ", " + presenciaTroncosID + ", " + presenciaRamasID + ")");
                this.baseDatosLocal.commit();
                ps.close();
            }
            this.sqlExterno.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla UPM_Epifita", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla UPM_Epifita", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //53
    public void importarSecuencias(String ruta) {
        this.querySelect = "SELECT SecuenciaID, UPMID, Sitio, FormatoID, Estatus FROM SYS_SecuenciaCaptura";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer secuenciaID = rs.getInt("SecuenciaID");
                Integer UPMID = rs.getInt("UPMID");
                Integer sitio = rs.getInt("Sitio");
                Integer formatoID = rs.getInt("FormatoID");
                Integer estatus = rs.getInt("Estatus");
                ps.executeUpdate("INSERT INTO SYS_SecuenciaCaptura(SecuenciaID, UPMID, Sitio, FormatoID, Estatus)"
                        + "VALUES(" + secuenciaID + ", " + UPMID + ", " + sitio + ", " + formatoID + ", " + estatus + ")");
                this.baseDatosLocal.commit();
                ps.close();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla SYS_SecuenciaCaptura", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla SYS_SecuenciaCaptura", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void importarUPMRevision(String ruta) {
        this.querySelect = "SELECT UPMID, SitioID, Sitio, SecuenciaID FROM SYS_UPM_Revision";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer UPMID = rs.getInt("UPMID");
                Integer sitioID = rs.getInt("SitioID");
                Integer sitio = rs.getInt("Sitio");
                Integer secuenciaID = rs.getInt("SecuenciaID");
                ps.executeUpdate("INSERT INTO SYS_UPM_Revision(UPMID, SitioID, Sitio, SecuenciaID)"
                        + "VALUES(" + UPMID + ", " + sitioID + ", " + sitio + ", " + secuenciaID + ")");
                this.baseDatosLocal.commit();
                ps.close();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla SYS_UPM_Revision", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla SYS_UPM_Revision", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    //54
    public void importarBrigadas(String ruta) {
        this.querySelect = "SELECT UPMID, BrigadistaID, PuestoID, EmpresaID FROM UPM_Brigada";
        this.baseDatosLocal = LocalConnection.getConnection();
        this.baseDatosExterna = ExternalConnection.getConnection(ruta);
        try {
            this.sqlExterno = this.baseDatosExterna.createStatement();
            Statement ps = this.baseDatosLocal.createStatement();
            ResultSet rs = sqlExterno.executeQuery(this.querySelect);
            while (rs.next()) {
                Integer UPMID = rs.getInt("UPMID");
                Integer brigadistaID = rs.getInt("BrigadistaID");
                Integer puestoID = rs.getInt("PuestoID");
                Integer empresaID = rs.getInt("EmpresaID");
                ps.executeUpdate("INSERT INTO UPM_Brigada(UPMID, BrigadistaID, PuestoID, EmpresaID)"
                        + "VALUES(" + UPMID + ", " + brigadistaID + ", " + puestoID + ", " + empresaID + ")");
                this.baseDatosLocal.commit();
                ps.close();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo importar la información de la tabla UPM_Brigada", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                baseDatosLocal.close();
                baseDatosExterna.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error! al cerrar la base de datos en la importación de la tabla UPM_Brigada", "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void eliminarBaseDatos() {
        String query = "DELETE FROM UPM_UPM";
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo eliminar la informacion de la base de datos", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos al eliminar los datos de la base de datos ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void eliminarPorUPM(int upm) {
        String query = "DELETE FROM UPM_UPM WHERE UPMID =" + upm;
        Connection conn = LocalConnection.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.commit();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error! no se pudo eliminar la informacion del upm seleccionado", "Conexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null, "Error! al cerrar la base de datos al eliminar los datos del upm seleccionado ",
                        "Conexion BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
}
